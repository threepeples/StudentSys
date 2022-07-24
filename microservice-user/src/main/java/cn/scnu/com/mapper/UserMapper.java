package cn.scnu.com.mapper;


import cn.scnu.com.pojo.Students;
import cn.scnu.com.pojo.Teachers;
import cn.scnu.com.pojo.Users;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description
 * @date 2022/7/13 11:59
 * @Version 1.0
 * @Author HJW
 */
public interface UserMapper {
    //根据账号和密码找到用户
    Users queryUserByAccountAndPwd(Users user);
    //根据账号找到用户角色
    String queryUserRoleByAccount(Integer account);
    //修改密码
    void changePwd(Users user);
    //根据账号找到学生用户详细信息
    Students queryStudentDetailsByAccount(Integer account);
    //导入老师信息
    void insertTeacherInfo(List<Teachers> teachers);
    //导入学生信息
    void insertStudentInfo(List<Students> students);
    //通过major获取majorId
    Integer queryMajorIdByMajor(String major);
    //生成账号
    void insertStudentUser(List<Users> users);
    //学生修改信息里的description
    void changeStuInfoByStu(@Param("description") String description,@Param("account") Integer account);
    //管理员修改学生信息
    void changeStuInfoByAdmin(Students s);
    //通过名字找到老师
    Teachers queryTeacherByTeacherName(String name);
}
