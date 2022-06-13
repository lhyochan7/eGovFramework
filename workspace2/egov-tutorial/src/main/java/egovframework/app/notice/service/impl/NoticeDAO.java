package egovframework.app.notice.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.app.notice.vo.NoticeVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository
public class NoticeDAO extends EgovComAbstractDAO {

    public void insertNotice(NoticeVO noticeVO) {
        insert("NoticeDAO.insertNotice", noticeVO);
    }

    public List<EgovMap> selectNoticeList() {
        // TODO Auto-generated method stub
        return selectList("NoticeDAO.selectNoticeList");
    }
}
