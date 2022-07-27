package cn.scnu.com.service;

import cn.scnu.com.mapper.MessageMapper;
import cn.scnu.com.pojo.Notice;
import cn.scnu.com.vo.NoticeList;
import cn.scnu.com.vo.OwnCurrentCourses;
import cn.scnu.com.vo.OwnGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MessageService
 * @Description
 * @date 2022/7/13 11:55
 * @Version 1.0
 * @Author HJW
 */
@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public List<NoticeList> getNotice() {
        return messageMapper.getNotices();
    }

    public Notice getNoticeDetail(Integer id) {
        return messageMapper.getNoticeDetail(id);
    }

    public List<OwnCurrentCourses> getCurrentCourses(Integer id) {
        return messageMapper.getCurrentCourses(id);
    }

    public List<OwnGrade> getGrades(Integer id) {
        return messageMapper.getGrades(id);
    }
}