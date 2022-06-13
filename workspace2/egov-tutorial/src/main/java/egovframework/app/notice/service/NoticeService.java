package egovframework.app.notice.service;

import java.util.List;

import egovframework.app.member.vo.SearchVO;
import egovframework.app.notice.vo.NoticeDto;
import egovframework.app.notice.vo.NoticeVO;

public interface NoticeService {
    void write(NoticeVO noticeVO);
    List<NoticeDto> list(SearchVO searchVO);
    int count(SearchVO searchVO);
    NoticeDto detail(int noticeSeq);
}
