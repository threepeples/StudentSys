package cn.scnu.com.mapper;

import cn.scnu.com.pojo.Notice;
import cn.scnu.com.vo.NoticeList;
import cn.scnu.com.vo.OwnCurrentCourses;
import cn.scnu.com.vo.OwnGrade;

import java.util.List;

/**
 * @ClassName MessageMapper
 * @Description
 * @date 2022/7/13 11:56
 * @Version 1.0
 * @Author HJW
 */

public interface MessageMapper {
    List<NoticeList> getNotices();

    Notice getNoticeDetail(Integer id);

    List<OwnCurrentCourses> getCurrentCourses(Integer id);

    List<OwnGrade> getGrades(Integer id);
}