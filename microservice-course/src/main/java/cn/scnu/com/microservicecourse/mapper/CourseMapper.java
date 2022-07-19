package cn.scnu.com.microservicecourse.mapper;

/**
 * @ClassName CourseMapper
 * @Description
 * @date 2022/7/13 11:58
 * @Version 1.0
 * @Author HJW
 */

public interface CourseMapper {
    //增加课程
    public void CoursesSave(Courses courses);
    //删除单条课程
    public void DelCourses(Integer courses_id);
    //更新课程
    public void CourseUpdate(Courses courses);
    //查询单条课程
    public  Courses queryById(Integer courses_id);
    //查询全部课程
    public Integer queryTotal();

}