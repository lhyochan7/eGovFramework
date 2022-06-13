package egovframework.app.notice.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.app.notice.service.impl.NoticeService;
import egovframework.app.notice.vo.NoticeForm;
import egovframework.app.notice.vo.NoticeVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class NoticeController {
    
    private final NoticeService noticeService;
    
    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
    
    @GetMapping("/notice/write.do")
    public String writePage(HttpSession session) {
        
        if(session.getAttribute("memberSeq") == null) {
            return "redirect:/members/login.do";
        }
        return "egovframework/app/notice/write";
    }
    
    @PostMapping("/notice/write.do")
    public String write(NoticeForm noticeForm, HttpSession session) {
        NoticeVO noticeVO = noticeForm.toVO();
        
        int memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());
        noticeVO.setMemberSeq(memberSeq);
        
        BeanUtils.copyProperties(noticeForm, noticeVO);
        
        System.out.println(noticeVO.getNoticeTitle());
        noticeService.write(noticeVO);
        
        return "egovframework/app/notice/list";
    }
    
    @RequestMapping("/notice/list.do")
    public String showList(HttpSession session, ModelMap map) {
        
        List<EgovMap> noticeList = noticeService.selectNoticeList();
        
        map.put("noticeList", noticeList);
        
        return "egovframework/app/notice/list";
    }
    
}
