package cn.scnu.com.controller;

import cn.scnu.com.pojo.Notice;
import cn.scnu.com.service.MessageService;
import cn.scnu.com.util.Result.Result;
import cn.scnu.com.vo.NoticeList;
import cn.scnu.com.vo.OwnCurrentCourses;
import cn.scnu.com.vo.OwnGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @ClassName MessageController
 * @Description
 * @date 2022/7/13 11:45
 * @Version 1.0
 * @Author HJW
 */
@CrossOrigin
@RestController
@RequestMapping("/message/manage")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 查看课表
     */
    @RequestMapping("ownclasses/{id}")
    public Result getOwnClasses( @PathVariable Integer id){
        List<OwnCurrentCourses> ownCurrentCourses =
            messageService.getCurrentCourses(id);
        return Result.success(ownCurrentCourses);
    }
    /**
     * 查看成绩信息
     */
    @RequestMapping("grades/{id}")
    public Result getOwnGrades( @PathVariable Integer id){
        List<OwnGrade> grades = messageService.getGrades(id);
        return Result.success(grades);
    }
    /**
     * 获取通知列表
     */
    @RequestMapping("notices")
    public Result getNotice(){
        List<NoticeList> notices = messageService.getNotice();
        return Result.success(notices);
    }
    /**
     * 查看通知
     */
    @RequestMapping("notice_detail")
    public Result getNoticeDetail(Integer id){
        Notice notice = messageService.getNoticeDetail(id);
        return Result.success(notice);
    }
}