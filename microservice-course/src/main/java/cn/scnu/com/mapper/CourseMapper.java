package cn.scnu.com.mapper;

import cn.scnu.com.pojo.ClassSelections;
import cn.scnu.com.pojo.Courses;
import cn.scnu.com.pojo.CoursesClasses;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @ClassName CourseMapper
 * @Description
 * @date 2022/7/13 11:58
 * @Version 1.0
 * @Author HJW
 */

@Repository
@Mapper
public interface CourseMapper {
    //增加课程
    public void CoursesSave(Courses courses);

    //更新课程
    public void CourseUpdate(Courses courses);
    //查询单条课程
    public  Courses queryById(Integer courses_id);
    //查询全部课程
    public Integer queryTotal();
    //交给sql语句的惨呼一般只有1个，2个也可以使用#{}
    //多个参数传递给sqlSession格局映射文件#{}拼接数据时，可以采用@Param注解定义参数变量名称
    public List<Courses> queryByPage(@Param("start")Integer start,@Param("rows")Integer rows);//分页查看
    //删除课程
    public void deleteById(Integer courses_id);

//________________________


    List<CoursesClasses> getSelectableClasses(@Param("majors_id")String majors_id, @Param("semester")int semester);

    List<CoursesClasses> getClassesInfoByCourseId(String courses_id);


    //获取class详细信息
    CoursesClasses getClassesInfo(String classes_id);

    Integer getClassNumberLeft(String class_id);

    //获取从学生id获取该学生全部已选课程id
    Set<String> getSelectedClassId(Integer student_id);

    //检查课程是否下有其他已选的课程
    List<ClassSelections> getSelectedOne(@Param("student_id")Integer student_id, @Param("classes_id")String classes_id);

    //检查课程是否选了某个固定class
    List<ClassSelections> getSelectedOneClass(@Param("student_id")Integer student_id, @Param("classes_id")String classes_id);

    void joinClass(@Param("student_id")Integer student_id, @Param("classes_id")String classes_id);

    void dropClass(@Param("student_id")Integer student_id,@Param("classes_id") String classes_id);

    //这是加入了课程，对应剩余人数减少
    void DeclineClassNumberLeft(String classes_id);

    //这是退选了课程，对应剩余人数增多
    void AddClassNumberLeft(String classes_id);


    //获得所有可选courese_Id
    Set<String> getSelectableCoursesId(@Param("majors_id")String majors_id, @Param("semester")Integer semester);

    //获得所有可选classId
    Set<String> getSelectableClassId(@Param("majors_id")String majors_id, @Param("semester")Integer semester);


}