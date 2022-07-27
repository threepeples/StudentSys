package cn.scnu.com.service;

import cn.scnu.com.mapper.UserMapper;
import cn.scnu.com.pojo.Students;
import cn.scnu.com.pojo.Teachers;
import cn.scnu.com.pojo.Users;
import cn.scnu.com.utils.MD5Util;
import cn.scnu.com.utils.MapperUtil;
import cn.scnu.com.utils.PrefixKey;
import cn.scnu.com.vo.stuVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName  UserService
 * @Description
 * @date 2022/7/13 11:59
 * @Version 1.0
 * @Author HJW
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisCluster jedis;

    private final ObjectMapper mapper= MapperUtil.MP;

    //获取用户角色
    public String queryUserRoleByAccount(Integer account) {
        return userMapper.queryUserRoleByAccount(account);
    }

    /**
     * 登录业务
     * @param user 前端传来的账号密码
     * @return ticket
     */
    public String login(Users user) {
        try {
            //加密
            user.setPwd(MD5Util.md5(user.getPwd()));
            //利用user数据查询数据库，判断登录是否合法
            Users exist=userMapper.queryUserByAccountAndPwd(user);
            if(exist==null) {
                System.out.println("用户不存在");
                return "";
            }else {
                //为了后续访问能够获取到User对象的数据，需要创建一个key值，将userJson作为value
                //存储在redis中，key值返回前端
                //前端页面下次访问就可以懈怠生成一个cookie将要写到回去的ticket
                String ticket= PrefixKey.USER_LOGIN_TICKET+System.currentTimeMillis()+"_"+user.getAccount();
                String userJson;
                userJson=mapper.writeValueAsString(exist);//jackson的代码，将已存在的exist用户对象转换成json

                //单用户顶替
				String existTicket=jedis.get(PrefixKey.USER_LOGINED_CHECK_PREFIX+"_"+exist.getAccount());
				if(!"".equals(existTicket)&&existTicket!=null) {
					jedis.del(existTicket);
				}
				//定义当前客户端登录的信息userAccount:ticket
				jedis.setex(PrefixKey.USER_LOGINED_CHECK_PREFIX+"_"+exist.getAccount(),60*15,ticket);
                //将userJson存储在redis中,设置登录超时
                jedis.setex(ticket, 60*15, userJson);
                return ticket;
            }
        }catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 查看当前登录用户，并延长redis时限
     * @param ticket 前端传来的tikect
     * @return userJson
     */
    public String queryUserJson(String ticket) {
        String userJson="";
        try {
            //首先判断超时剩余时间
            Long leftTime = jedis.pttl(ticket);
            if(leftTime < 1000*60*10l) {
                jedis.pexpire(ticket, leftTime+1000*60*5);
            }
            userJson=jedis.get(ticket);
            return userJson;
        }catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 修改密码
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param account 账号（学生学号）
     * @return boolean
     */
    public boolean changePwd(String oldPwd,String newPwd,Integer account) {
        Users user = new Users();
        user.setAccount(account);
        user.setPwd(MD5Util.md5(oldPwd));
        user = userMapper.queryUserByAccountAndPwd(user);
        if(user == null){
            return false;
        }else{
            user.setPwd(MD5Util.md5(newPwd));
            userMapper.changePwd(user);
            return true;
        }
    }

    /**
     * 通过账号获取学生详细信息
     * @param account 账号
     * @return Studeens
     */
    public stuVo queryStuDetailsByAccount(Integer account) {
        Students stu = userMapper.queryStudentDetailsByAccount(account);
//        System.out.println(stu.toString());
        stuVo stuVo = new stuVo();
        stuVo.setDescription(stu.getDescription());
        stuVo.setGradeId(stu.getGradeId());
        stuVo.setName(stu.getName());
        stuVo.setSemester(stu.getSemester());
        stuVo.setStudentId(stu.getStudentId());
        stuVo.setMajor(userMapper.queryMajorByMajorId(stu.getMajorsId()));
        stuVo.setTimeEnrollment(stu.getTimeEnrollment());
        return stuVo;
    }

    /**
     * 批量导入学生信息,并生成账号
     * @param stuVoList 学生传输单元列表
     */
    public String insertStudent(List<stuVo> stuVoList) {
        String result = "成功导入";
        String result2 = "";
        int flag = 1;
        List<Students> studentsList = new ArrayList<>();
        List<Users> stuUsersList = new ArrayList<>();

        for(stuVo stu : stuVoList){
            //检查是否已经存在该账号
            if(userMapper.queryStudentDetailsByAccount(stu.getStudentId())!=null){
                flag = 2;
                result2 += stu.getName()+"已存在,";
                continue;
            }

            Students s = new Students();
            //把属性copy过去，关于id的找到对应id在copy过去，得到实体类Students
            s.setStudentId(stu.getStudentId());
            s.setName(stu.getName());
            s.setDescription(stu.getDescription());
            s.setGradeId(stu.getGradeId());
            s.setSemester(stu.getSemester());
            Integer majorId = userMapper.queryMajorIdByMajor(stu.getMajor());
            if(majorId == null){
                result2 += stu.getMajor()+"专业不存在,";
                flag = 2;
                continue;
            }
            s.setMajorsId(majorId);
            s.setTimeEnrollment(stu.getTimeEnrollment());
            //添加到列表
            studentsList.add(s);

            //得到实体类Users
            Users u = new Users();
            u.setPwd("202cb962ac59075b964b07152d234b70");//默认密码为123
            u.setAccount(stu.getStudentId());
            u.setRole("student");
            //添加到列表
            stuUsersList.add(u);
        }
        if(studentsList.isEmpty()){
            return result2 + "已存在学生的无需重复导入，专业不存在的导入不成功，请联系管理添加专业或者重新输入，其余无异常的成功导入";
        }
        //导入学生表
        userMapper.insertStudentInfo(studentsList);
        //导入用户表
        userMapper.insertStudentUser(stuUsersList);
        if(flag==1){
            return result;
        }
        else{
            return result2 + "已存在学生的无需重复导入，专业不存在的导入不成功，请联系管理添加专业或者重新输入，其余无异常的成功导入";
        }

    }

    /**
     * 学生修改信息里的description
     * @param description
     */
    public void changeStuInfoByStu( String description, Integer account) {
        userMapper.changeStuInfoByStu(description,account);
    }

    /**
     * 管理员修改学生信息
     * @param stuVo 学生信息传输单元
     * @return 用户导入情况
     */
    public String changeStuInfoByAdmin(stuVo stuVo) {
        Students s = userMapper.queryStudentDetailsByAccount(stuVo.getStudentId());
        if(s==null){
            return "用户不存在";
        }else{
            //把VO的属性copy给实体类Students
            s.setStudentId(stuVo.getStudentId());
            s.setName(stuVo.getName());
            s.setDescription(stuVo.getDescription());
            s.setGradeId(stuVo.getGradeId());
            s.setSemester(stuVo.getSemester());
            s.setMajorsId(userMapper.queryMajorIdByMajor(stuVo.getMajor()));
            s.setTimeEnrollment(stuVo.getTimeEnrollment());
            userMapper.changeStuInfoByAdmin(s);
            return "修改成功";
        }
    }

    /**
     * test
     * @param major 专业
     * @return 专业号
     */
    public Integer getMajorId(String major) {
        return userMapper.queryMajorIdByMajor(major);
    }

    /**
     *
     * @param list 老师信息表
     * @return 老师导入情况
     */
    public String insertTeacher(List<Teachers> list) {
        String result = "导入成功";

        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(dTF);
        LocalDateTime createTime = LocalDateTime.parse(time,dTF);

        for(int i=0;i<list.size();i++){
            list.get(i).setCreate_time(createTime);
            if (userMapper.queryTeacherByTeacherName(list.get(i).getName())!=null){
                list.remove(i);
                i--;
            }
        }
        if(list.isEmpty()) {
            return "所有老师都存在,无需重复导入";
        }
        userMapper.insertTeacherInfo(list);

        return result;
    }
}