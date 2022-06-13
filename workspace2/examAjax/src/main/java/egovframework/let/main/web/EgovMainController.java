package egovframework.let.main.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.let.main.service.EgovMainService;
import egovframework.let.main.vo.IntteVO;
import egovframework.let.main.vo.MemberVO;
//import egovframework.let.cop.bbs.service.BoardVO;
//import egovframework.let.cop.bbs.service.EgovBBSManageService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

/**
 * 템플릿 메인 페이지 컨트롤러 클래스(Sample 소스)
 * 
 * @author 실행환경 개발팀 JJY
 * @since 2011.08.31
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.08.31  JJY            최초 생성
 *
 *      </pre>
 */
@Controller
@SessionAttributes(types = ComDefaultVO.class)
public class EgovMainController {

    /**
     * EgovBBSManageService
     */
//    @Resource(name = "EgovBBSManageService")
//    private EgovBBSManageService bbsMngService;

    @Resource(name = "EgovMainService")
    private EgovMainService mainService;

    private MemberVO loginData;

    /**
     * 메인 페이지에서 각 업무 화면으로 연계하는 기능을 제공한다.
     *
     * @param request
     * @param commandMap
     * @exception Exception Exception
     */
    @RequestMapping(value = "/cmm/forwardPage.do")
    public String forwardPageWithMenuNo(HttpServletRequest request, @RequestParam Map<String, Object> commandMap)
            throws Exception {
        return "";
    }

    /**
     * 템플릿 메인 페이지 조회
     * 
     * @return 메인페이지 정보 Map [key : 항목명]
     *
     * @param request
     * @param model
     * @exception Exception Exception
     */
    @RequestMapping(value = "/cmm/main/mainPage.do")
    public String getMgtMainPage(HttpServletRequest request, ModelMap model, IntteVO intteVO) throws Exception {

        // 공지사항 메인 컨텐츠 조회 시작 ---------------------------------
//        BoardVO boardVO = new BoardVO();
//        boardVO.setPageUnit(5);
//        boardVO.setPageSize(10);
//        boardVO.setBbsId("BBSMSTR_AAAAAAAAAAAA");
//
//        PaginationInfo paginationInfo = new PaginationInfo();
//
//        paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
//        paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
//        paginationInfo.setPageSize(boardVO.getPageSize());
//
//        boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//        boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
//        boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//
//        Map<String, Object> map = bbsMngService.selectBoardArticles(boardVO, "BBSA02");
//        model.addAttribute("notiList", map.get("resultList"));
//
//        boardVO.setBbsId("BBSMSTR_BBBBBBBBBBBB");
//        map = bbsMngService.selectBoardArticles(boardVO, "BBSA02");
//
//        System.out.println(">>>>> reqType : " + request.getParameter("reqType"));
//        if ("test".equals(request.getParameter("reqType"))) {
//            List<EgovMap> dataList = new ArrayList<EgovMap>();
//            for (int i = 0; i < 5; i++) {
//                EgovMap dataMap = new EgovMap();
//                dataMap.put("notiTitle", "공지사항 - " + i);
//                dataList.add(dataMap);
//            }
//
//            model.addAttribute("galList", dataList);
//        } else {
//            model.addAttribute("galList", map.get("resultList"));
//        }

        // 공지사항 메인컨텐츠 조회 끝 -----------------------------------

        // return "main/EgovMainView";

        return "main/LoginPage";
    }

    @ResponseBody
    @RequestMapping(value = "/test/ajax.do")
    public byte[] getNotiList(HttpServletRequest request, ModelMap model) throws Exception {
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("success", Boolean.FALSE);

        List<EgovMap> dataList = new ArrayList<EgovMap>();
        for (int i = 0; i < 5; i++) {
            EgovMap dataMap = new EgovMap();
            dataMap.put("notiTitle", "공지사항 - " + i);
            dataList.add(dataMap);
        }

        rtn.put("notiList", dataList);

        return new Gson().toJson(rtn).getBytes("UTF-8");
    }

    @ResponseBody
    @RequestMapping(value = "/main/intteClsList.do")
    public byte[] getClsList(HttpServletRequest request, ModelMap model, IntteVO vo, HttpSession session, Locale locale)
            throws Exception {
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("success", Boolean.FALSE);

        List<EgovMap> clsList = mainService.getIntteClsList(vo);

        rtn.put("clsList", clsList);

        return new Gson().toJson(rtn).getBytes("UTF-8");
    }

    @ResponseBody
    @RequestMapping(value = "/main/intteNameList.do")
    public byte[] getIntteNameList(HttpServletRequest request, ModelMap model, IntteVO vo, HttpSession session,
            Locale locale) throws Exception {
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("success", Boolean.FALSE);

        
        List<EgovMap> nameList = mainService.getIntteNameList(vo);

        rtn.put("nameList", nameList);

        return new Gson().toJson(rtn).getBytes("UTF-8");
    }

    @RequestMapping("/main/actionLogin.do")
    public String login(HttpServletRequest request, MemberVO memberVO, ModelMap model) throws Exception {
        
        System.out.println("memberVO = " + memberVO);
        EgovMap checkedMember = mainService.checkUser(memberVO);
        
        System.out.println("########################### 체크한다  " + checkedMember);
        
        if(checkedMember == null) {
            model.addAttribute("message", "false");
            return "main/LoginPage";
        } else {
            model.addAttribute("message", "true");
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("intteSeq", memberVO.getIntteSeq());
        session.setAttribute("userId", memberVO.getUserId());
        
        
        return "main/LoginPage";
    }
    
    

    @ResponseBody
    @RequestMapping(value = "/main/actionSearch.do")
    public byte[] getIntteNameList(HttpServletRequest request, @RequestParam Map<String, String> map, ModelMap model, HttpSession session,
            Locale locale) throws Exception {
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("success", Boolean.FALSE);
        
        System.out.println("Search AJAX");

        System.out.println(map.toString());
        
        String searchText = map.get("searchText");
        Integer searchOpt = Integer.parseInt(map.get("searchOpt"));
        
        
        
        // Search 실행 (searchOpt, searchText 둘다 null시 모든 데이터 출력)
        Map<String, String> searchMap = new HashMap<>();
        
        searchMap.put("searchOpt", String.valueOf(searchOpt));
        searchMap.put("searchText", searchText);
        searchMap.put("intteSeq", String.valueOf(session.getAttribute("intteSeq")));

        System.out.println("SearchMap = " + searchMap);
        
        try {
            List<EgovMap> clsList = mainService.searchCls(searchMap);
            System.out.println("################ CLS LIST = " + clsList);
            rtn.put("success", Boolean.TRUE);
            rtn.put("clsList", clsList);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return new Gson().toJson(rtn).getBytes("UTF-8");
    }
    
    
    @RequestMapping("/main/mainPage.do")
    public String mainPage(HttpServletRequest request, MemberVO memberVO, 
            HttpSession session, ModelMap model) throws Exception {
        
        // not login
        if(session.getAttribute("intteSeq") == null) {
            return "main/LoginPage";
        }
        
        return "main/MainPage";
    }

}