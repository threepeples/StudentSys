package cn.scnu.com.service;

import cn.scnu.com.pojo.Courses;
import cn.scnu.com.pojo.CoursesClasses;
import cn.scnu.com.util.EasyUIResult;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 课程大类信息表 服务类
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
public interface ICoursesService {

    //获取全部可选课表数据
    List<CoursesClasses> getSelectableClasses(String majors_id, Integer semester);
    //获得选课班的已选人数
    Integer getClassNumberLeft(String class_id);

    void joinClass(Integer student_id, String classes_id);

    //获取全部已选选课班id
    Set<String> getSelectedClassId(Integer student_id);

    void dropClass(Integer student_id, String classes_id);

    //判断学生是否加入了一个课程
    Boolean getSelectedOne(Integer student_id, String classes_id);
    //判断学生是否加入了一个class
    Boolean getSelectedOneClass(Integer student_id, String classes_id);

    //获取可选课表id
    Set<String> getSelectableId(String majors_id, Integer semester);

    //通过classid获取class详细信息
    CoursesClasses getClassesInfo(String classes_id);

    //判断该年级是否可选该课表classes_id
    boolean isSelectable(String classes_id, String majors_id, Integer semester);





    void closeSelect();
    //设定选课
    void startSelect(Calendar calendar);

    //全写回
    void writebackRedisAll();














    //新增课程
    public void CoursesSave(Courses courses);

    //更新课程
    public void CourseUpdate(Courses courses);
    //查询单条课程
    public  Courses queryById(Integer courses_id);

    //交给sql语句的惨呼一般只有1个，2个也可以使用#{}
    //多个参数传递给sqlSession格局映射文件#{}拼接数据时，可以采用@Param注解定义参数变量名称
    public EasyUIResult queryByPage(@Param("start")Integer start, @Param("rows")Integer rows);//分页查看

    //删除课程
    public void CourseDelete(Integer courses_id);
}
