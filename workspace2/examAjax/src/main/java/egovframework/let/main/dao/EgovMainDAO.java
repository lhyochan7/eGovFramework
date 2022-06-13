package egovframework.let.main.dao;

import java.util.List;
import java.util.Map;

import egovframework.let.main.vo.IntteVO;
import egovframework.let.main.vo.MemberVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface EgovMainDAO {

    public List<EgovMap> getIntteClsList(IntteVO vo) throws Exception;
    public List<EgovMap> getIntteNameList(IntteVO vo);
    public EgovMap checkUser(MemberVO member) throws Exception;
    public List<EgovMap> searchCls(Map<String, String> searchMap) throws Exception;

}