package cn.scnu.com.microservicecourse.service;

import org.springframework.stereotype.Service;

/**
 * @ClassName CourseService
 * @Description
 * @date 2022/7/13 11:56
 * @Version 1.0
 * @Author HJW
 */
@Service
public class CourseService {
    @Autowired
    private CoursesMapper coursesMapper;//注入CoursesMapper
    //直接注入JedisCluster,在方法中就可以实现缓存redis数据库使用。
    @Autowired
    private JedisCluster
    //增加课程
    public void CoursesSave(Courses courses){

    }
    //删除课程
    public void DelCourses(String courses_id);
    //修改课程
    public void CourseUpdate(Courses courses);
    //查询单条课程
    public  Courses queryById(String courses_id);
    //查询全部课程
    public Integer queryTotal();

}