 package egovframework.app.member.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.app.member.service.MemberService;
import egovframework.app.member.vo.MemberForm;
import egovframework.app.member.vo.MemberVO;
import egovframework.app.member.vo.SearchVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MemberController {

    @Resource(name = "memberService")
    private MemberService memberService;

    /** cmmUseService */
    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @RequestMapping("/members/login.do")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("memberSeq") != null) {
            // 로그인이 되어 있을 경우
            return "redirect:/members/main.do";
        }
        return "egovframework/app/member/login";
    }

    @GetMapping("/members/join.do")
    public String joinPage() throws Exception {
        return "egovframework/app/member/join";
    }

    @PostMapping("/members/login.do")
    public String login(MemberForm memberForm, ModelMap model, HttpSession session) {
        System.out.println(memberForm);

        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(memberForm.getMemberId());
        memberVO.setMemberPassword(memberForm.getPassword());

        MemberVO member = memberService.login(memberVO);
        System.out.println(member);

        if (member == null) {
            // 로그인 실패
            model.addAttribute("message", "로그인 실패하였습니다");
            model.addAttribute("memberForm", memberForm);
            return "egovframework/app/member/login";
        }
        // 로그인 성공
        session.setAttribute("memberSeq", member.getMemberSeq());
        session.setAttribute("memberId", member.getMemberId());
        session.setAttribute("memberName", member.getMemberName());

        // TODO : 메인페이지로 변경
        return "redirect:/members/main.do";
    }

    @PostMapping("/members/join.do")
    public String join(MemberForm memberForm) {
        System.out.println(memberForm);

        MemberVO memberVO = new MemberVO();

        memberVO.setMemberId(memberForm.getMemberId());
        memberVO.setMemberName(memberForm.getMemberName());
        memberVO.setMemberPassword(memberForm.getPassword());
        memberVO.setMemberPhone(memberForm.getPhone());

        memberService.join(memberVO);

        return "redirect:/members/login.do";
    }

    @GetMapping("/members/main.do")
    public String mainPage(HttpSession session) {
        if (session.getAttribute("memberSeq") == null) {
            // 로그인이 되지 않았을 경우 (incognito 모드에서 session을 못불러오고 login 화면으로 전환)
            return "redirect:/members/login.do";
        }
        // 로그인이 되었을 경우
        return "egovframework/app/member/main";
    }

    @RequestMapping("/members/list.do")
    public String memberList(ModelMap model, @ModelAttribute SearchVO searchVO) {

        System.out.println(searchVO);
        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getRecordCountPerPage());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // DB에서 데이터 조회
        try {
            List<MemberVO> memberList = memberService.list(searchVO);
            int totalRecordCount = memberService.count(searchVO);
            paginationInfo.setTotalRecordCount(totalRecordCount);

            model.addAttribute("memberList", memberList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("searchVO", searchVO);

        return "egovframework/app/member/list";
    }

}