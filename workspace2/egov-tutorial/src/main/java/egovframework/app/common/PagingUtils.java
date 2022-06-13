package egovframework.app.common;

import egovframework.app.member.vo.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class PagingUtils {
    public static PaginationInfo getPaginationInfo(SearchVO searchVO) {
        PaginationInfo paginationInfo = new PaginationInfo();
        
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getRecordCountPerPage());
        paginationInfo.setPageSize(searchVO.getPageSize());
        
        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        return paginationInfo;
    }
}
