package egovframework.app.notice.service.impl;

import java.util.List;

import egovframework.app.notice.vo.NoticeVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface NoticeService {
    void write(NoticeVO noticeVO);
    List<EgovMap> selectNoticeList();
}
