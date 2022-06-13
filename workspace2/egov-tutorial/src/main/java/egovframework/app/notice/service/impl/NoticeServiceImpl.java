package egovframework.app.notice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.app.notice.vo.NoticeVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service
public class NoticeServiceImpl implements NoticeService{

    private final NoticeDAO noticeDAO;
    
    @Autowired
    public NoticeServiceImpl(NoticeDAO noticeDAO) {
        super();
        this.noticeDAO = noticeDAO;
    }
    
    @Override
    public void write(NoticeVO noticeVO) {
        // TODO Auto-generated method stub
        noticeDAO.insertNotice(noticeVO);
    }

    @Override
    public List<EgovMap> selectNoticeList() {
        // TODO Auto-generated method stub
        return noticeDAO.selectNoticeList();
    }
    
   

}
