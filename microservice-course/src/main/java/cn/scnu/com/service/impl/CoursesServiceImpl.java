package cn.scnu.com.service.impl;

import cn.scnu.com.mapper.CourseMapper;
import cn.scnu.com.service.ICoursesService;
import cn.scnu.com.util.Constants;
import cn.scnu.com.util.SerializeUtil;
import cn.scnu.com.util.WriteBackSetUtil;
import cn.scnu.com.pojo.CoursesClasses;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



/**
 * <p>
 * 课程大类信息表 服务实现类
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
@Service("ManageService")
public class CoursesServiceImpl implements ICoursesService {
    @Autowired
    private JedisCluster jedis;

    @Autowired
    private CourseMapper courseMapper;

    //--------------------------------缓存辅助函数
    //读取缓存内的class_id对应的class详细信息函数...
    CoursesClasses getRedisClassInfos(String ClassId){
        if (jedis.exists(ClassId.getBytes())){
            //缓存存在，直接获取序列化之后的对象
            return (CoursesClasses) SerializeUtil.unSerialize(jedis.get(ClassId.getBytes()));
        }else{
            //没有需要进行数据库的读取
            CoursesClasses SelectableClassInfo =courseMapper.getClassesInfo(ClassId);

            if (SelectableClassInfo==null){
                //数据库内也没有数据
                return null;
            }

            //写入到缓存,限定时间为3小时
            jedis.set(ClassId.getBytes(),SerializeUtil.serialize(SelectableClassInfo));
            //设置过期时间
            jedis.expire(ClassId, Math.toIntExact(Constants.REDIS_EXPIRATION_TIME));
            //返回列表
            return SelectableClassInfo;
        }
    }
    //读取缓存内的可选课程class_id函数
    Set<String> getRedisClassId(String majors_id, Integer semester){
        //通过majors_id和semester作为id
        String key=majors_id+semester;

        if (jedis.exists(key)){
            return jedis.smembers(key);
        }else{
            Set<String> selectableClassIds=new HashSet<>();
            //没有id缓存，需要进行数据库的写入到缓存
            selectableClassIds =courseMapper.getSelectableClassId(majors_id, semester);

            if (selectableClassIds.isEmpty()){
                //数据库内也没有数据,返回空集合
                return selectableClassIds;
            }

            for (String ClassId:
                    selectableClassIds) {

                jedis.sadd(key, ClassId);

            }
            //设置过期时间
            jedis.expire(key, Math.toIntExact(Constants.REDIS_EXPIRATION_TIME));

            return selectableClassIds;
        }
    }
    //读取缓存内全部已选课程id
    Set<String> getRedisSelectedClassId(Integer student_id){
        String student_idS=Integer.toString(student_id);
        Set<String> ClassesIds=new HashSet<>();
        if (jedis.exists(student_idS)){
            //存在，但需要判断内部是否为空
            if(jedis.scard(student_idS)==1){
                return ClassesIds;//返回空的集合
            }
            return jedis.smembers(student_idS);
        }else{
            //数据库
            ClassesIds=courseMapper.getSelectedClassId(student_id);

            if (ClassesIds.isEmpty()){
                //数据库内也没有数据
                //防止缓存穿透，需要放到缓存中，并且将值置为null
                return ClassesIds;
            }

            for (String ClassesId:
                    ClassesIds) {
                jedis.sadd(student_idS, ClassesId);
            }
            //设置初始化标志
            jedis.sadd(student_idS, "");
            //设置过期时间
            jedis.expire(student_idS, Math.toIntExact(Constants.REDIS_EXPIRATION_TIME));

            return ClassesIds;
        }
    }
    //读取缓存内选课班人数
    Integer getRedisClassNumberLeft(String class_id){

        String key=class_id+"number";
        if (jedis.exists(key)){
            return Integer.parseInt(jedis.get(key));
        }
        //不存在，访问数据库
        Integer result=courseMapper.getClassNumberLeft(class_id);
        if (result==null){
            //数据库内也没有数据
            return result;
        }
        //数据库内存在数据，重写缓存
        jedis.set(key,String.valueOf(result));
        jedis.expire(key, Math.toIntExact(Constants.REDIS_EXPIRATION_TIME));

        return result;
    }
    //--------------------------------

    //获取全部可选课表数据
    @Override
    public List<CoursesClasses> getSelectableClasses(String majors_id, Integer semester) {

        Set<String> selectableClassIds=new HashSet<>();
        List<CoursesClasses> result = new ArrayList<>();

        //首先获取全部专业对应的可选courese_id
        selectableClassIds = getRedisClassId(majors_id,semester);

        //selectableClassIds为空说明专业id和semesterid没有相应的结果，可能魏参数错误
        if (selectableClassIds.isEmpty()){
            return null;
        }

        //然后循环获取全部可选详细信息
        for (String ClassId:
                selectableClassIds) {
            result.add(getRedisClassInfos(ClassId));
        }
        return result;
    }

    //获取全部可选课表的id
    @Override
    public Set<String> getSelectableId(String majors_id, Integer semester) {

        Set<String> result = getRedisClassId(majors_id, semester);

        return result;
    }

    //获取全部已选课程id
    @Override
    public  Set<String> getSelectedClassId(Integer student_id) {
        return getRedisSelectedClassId(student_id);
    }

    //通过id获取Class详细信息
    @Override
    public CoursesClasses getClassesInfo(String classes_id) {
        return getRedisClassInfos(classes_id);
    }

    //判断该年级是否可选该课表classes_id
    @Override
    public boolean isSelectable(String classes_id, String majors_id, Integer semester) {

        for (String SelectableId:
                getRedisClassId(majors_id,semester)) {
            if(SelectableId.equals(classes_id)){
                //System.out.println(SelectableId+"-"+classes_id);
                return true;
            }
        }
        return false;
    }

    //获得相应classid的人数
    @Override
    public Integer getClassNumberLeft(String class_id) {
        return getRedisClassNumberLeft(class_id);
    }

    //判断学生是否加入了一个课程
    public Boolean getSelectedOne(Integer student_id, String classes_id){
        Set<String> selectedClassIds=getRedisSelectedClassId(student_id);
        for (String selectedClassId:
                selectedClassIds) {
            //匹配selectedClassId和classes_id的自-前的字符
            int i=0;
            while (!"-".equals(classes_id.charAt(i))){
                if(selectedClassId.charAt(i)==classes_id.charAt(i)){
                    return Boolean.TRUE;
                }
                i++;
            }
            if(selectedClassId.charAt(i)==classes_id.charAt(i)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    //判断学生是否加入了一个class
    public Boolean getSelectedOneClass(Integer student_id, String classes_id){

        //获取学生已选课程，需要-------缓存中才是实时数据
        Set<String> selectedClassIds=getRedisSelectedClassId(student_id);

        //如果集合为空，说明这个学生什么课都没有选
        if(selectedClassIds.isEmpty()){
            return Boolean.FALSE;
        }
        for (String selectedClassId:
                selectedClassIds) {

            if(selectedClassId.equals(classes_id)){
                //已经加入课程
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    //选课接口，进行选课
    @Override
    public void joinClass(Integer student_id, String classes_id) {

        String key=classes_id+"number";
        //进行选课，修改数据库数据和缓存数据
        //更新缓存,classes_id放入key为student_id 的集合内,设置过期时间为10分钟(10分钟内会写回到数据库内，因此不用担心丢失)
        jedis.sadd(Integer.toString(student_id), classes_id);
        jedis.expire(Integer.toString(student_id), Math.toIntExact(Constants.REDIS_EXPIRATION_TIME));
        //插入数据库
        //courseMapper.joinClass(student_id,classes_id);

        //修改缓存
        jedis.decr(key);
        //修改数据库....定时修改
        WriteBackSetUtil.addId(student_id);
        //立刻修改数据库中的人数
        courseMapper.DeclineClassNumberLeft(classes_id);
    }

    //退选接口，进行退选
    @Override
    public void dropClass(Integer student_id, String classes_id) {
        String key=classes_id+"number";
        //进行选课，修改数据库数据
        //删除
        jedis.srem(Integer.toString(student_id), classes_id);
        //是否延时修改？
        //courseMapper.dropClass(student_id,classes_id);

        jedis.incr(key);
        //是否延时修改？
        WriteBackSetUtil.addId(student_id);
        courseMapper.AddClassNumberLeft(classes_id);
    }

    //--------管理写回函数--------//


    ScheduledExecutorService scheduledExecutorService ;
    //设定选课
    @Override
    @Test
    public void startSelect(Calendar calendar) {

        scheduledExecutorService = Executors.newScheduledThreadPool(5);


        scheduledExecutorService.scheduleAtFixedRate(() -> {
            //写回业务逻辑

            //使用for循环检查脏位
            Map<Integer, Integer> StudentIdMap=WriteBackSetUtil.getSet();
            for (Map.Entry<Integer, Integer> entry : StudentIdMap.entrySet()) {
                Integer StudentId = entry.getKey();
                Integer DrityED = entry.getValue();
                if (DrityED==0){
                    //是新数据，脏数据处理
                    WriteBackSetUtil.Drity(StudentId);

                    String student_idS=Integer.toString(StudentId);
                    if (jedis.exists(student_idS)){
                        //获取已选课表的id
                        Set<String> classesIdSet=jedis.smembers(student_idS);
                        //向数据库写回
                        for (String classes_id : classesIdSet) {

                            if (classes_id==null||"".equals(classes_id)){
                                continue;
                            }
                            courseMapper.joinClass(StudentId,classes_id);
                        }
                    }

                }
                //旧数据不理会
            }

            System.out.println("进行数据从缓存写回数据库"+new Date());
            //3分钟进行一次写回
            }, 60*1, 60*3, TimeUnit.SECONDS);

    }

    //关闭选课，关闭定时写回系统
    @Override
    public void closeSelect() {
        scheduledExecutorService.shutdownNow();
        //System.out.println("选课关闭");
        //BeginTime=null;
        //EndTime=null;
    }

    //全写回
    @Override
    public void writebackRedisAll() {

        //循环整个redis，进行全部数据的写回
        Map<Integer, Integer> StudentIdMap=WriteBackSetUtil.getSet();
        Set<Integer> StudentIdset = StudentIdMap.keySet();
        for (Integer StudentId: StudentIdset) {
            String student_idS=Integer.toString(StudentId);
            if (jedis.exists(student_idS)){
                //获取已选课表的id
                Set<String> classesIdSet=jedis.smembers(student_idS);



                //向数据库写回
                for (String classes_id : classesIdSet) {
                    if (classes_id==null||"".equals(classes_id)){
                        continue;
                    }

                    courseMapper.joinClass(StudentId,classes_id);
                }

            }
        }
        System.out.println("课程关闭时从缓存写回数据库"+new Date());
    }
}

