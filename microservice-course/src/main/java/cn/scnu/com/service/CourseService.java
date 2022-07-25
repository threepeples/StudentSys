package cn.scnu.com.service;

import cn.scnu.com.mapper.CourseMapper;
import cn.scnu.com.pojo.Courses;
import cn.scnu.com.util.EasyUIResult;
import cn.scnu.com.util.MapperUtil;
import cn.scnu.com.util.PrefixKey;
import cn.scnu.com.util.Result.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName CourseService
 * @Description
 * @date 2022/7/13 11:56
 * @Version 1.0
 * @Author HJW
 * 仍存疑问
 */
@Service
public class CourseService {

    //注入CoursesMapper
    @Autowired
    private CourseMapper coursesMapper;
    //直接注入JedisCluster,在方法中就可以实现缓存redis数据库使用。
    @Autowired
    private JedisCluster jedisCluster;
    //增加课程
    public void CoursesSave(Courses courses){
        //此处存在疑问？CSL
        courses.setCourses_id(Integer.valueOf(UUID.randomUUID().toString()));
        coursesMapper.CoursesSave(courses);
    }

    //修改课程
    public void CourseUpdate(Courses courses){
        //更新前，对应商品缓存删除
        //删除缓存成功了，才能进行商品更新，否则用户查询到旧数据
        //加锁解决并发数据缓存与数据库不一致的问题
        String lock= PrefixKey.PRODUCT_UPDATE_PREFIX +courses.getCourses_id()+PrefixKey.PRODUCT_LOCK_PREFIX;
        Long leftTime=jedisCluster.ttl(PrefixKey.PRODUCT_QUERY_PREFIX+courses.getCourses_id());
        //锁的超时时间多久 商品旧数据剩余时间
        jedisCluster.setex(lock, Integer.parseInt(leftTime+""), "");
        jedisCluster.del(PrefixKey.PRODUCT_QUERY_PREFIX+courses.getCourses_id());
        coursesMapper.CourseUpdate(courses);
        //释放锁
        jedisCluster.del(lock);
    }
    //查询单条课程
//    public Courses queryById(Integer courses_id){
//       String courseKey=PrefixKey.PRODUCT_QUERY_PREFIX+courses_id;
//       //锁
//        String lock=PrefixKey.PRODUCT_UPDATE_PREFIX+courses_id+PrefixKey.PRODUCT_LOCK_PREFIX;
//        //查看redis中是否有商品数据，如果有直接返回控制层，如果没有向数据库中查询，返回控制器层，并存入一份redis中
//        try {//先判断锁存在与否
//            if (jedisCluster.exists(lock)) {
//                System.out.println(courses_id+coursesMapper.queryById(courses_id).toString());
//                return coursesMapper.queryById(courses_id);//疑问:courses_id是String还是Integer
//            }
//            //判断缓存是否存在数据
//            if (jedisCluster.exists(courseKey)) {
//                System.out.println(courses_id+coursesMapper.queryById(courses_id).toString());
//                String courseJson=jedisCluster.get(courseKey);
//                Courses coursed=MapperUtil.MP.readValue(courseJson, Courses.class);//疑问:是否需要单独新建一个类？
//                return coursed;
//            }else {
//                //说明缓存没有课程的json字符串数据
//                //查询数据库，并且吧查询的结果存到缓存功后续使用
//                System.out.println(courses_id+coursesMapper.queryById(courses_id).toString());
//                Courses courses=coursesMapper.queryById(courses_id);
//                //courses转化成string
//                String coursesJson= MapperUtil.MP.writeValueAsString(courses);
//                jedisCluster.setex(courseKey, 60*60*24*2, coursesJson);
//                return courses;
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public Courses queryById(Integer courses_id) {
        System.out.println(courses_id);
        return coursesMapper.queryById(courses_id);
    }
    //查询全部课程
    //分页显示
    public EasyUIResult courseByPage(Integer page,Integer rows){
        //封装对象到EasyUIResult对象
        //1.创建一个返回对象
        EasyUIResult courseResult = new EasyUIResult();
        //封装第一个属性total的数量
        Integer total=coursesMapper.queryTotal();
        //3.封装第二个属性List<Product> plist;
        //根据页数计算起始位置
        Integer start=(page - 1)*rows;
        List<Courses> cList=coursesMapper.queryByPage(start, rows);
        //4.封装对象属性
        courseResult.setTotal(total);
        courseResult.setRows(cList);
        return courseResult;
    }

}