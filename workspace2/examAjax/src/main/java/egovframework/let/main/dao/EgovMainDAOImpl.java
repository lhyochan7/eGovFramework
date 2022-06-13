package egovframework.let.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.let.main.vo.IntteVO;
import egovframework.let.main.vo.MemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("EgovMainDAO")
public class EgovMainDAOImpl extends EgovAbstractMapper implements EgovMainDAO {

    @Override
    public List<EgovMap> getIntteClsList(IntteVO vo) throws Exception {
        return selectList("EgovMainDAO.selectIntteClsList", vo);
    }

    @Override
    public List<EgovMap> getIntteNameList(IntteVO vo) {
        return selectList("EgovMainDAO.selectIntteNameList", vo);
    }

    @Override
    public EgovMap checkUser(MemberVO member) throws Exception {
        // TODO Auto-generated method stub
        //MemberVO member2 = selectOne("EgovMainDAO.checkUser", member);
        //Map<String, String> member2 = selectOne("EgovMainDAO.checkUser", member);
        
        return (EgovMap) selectOne("EgovMainDAO.checkUser", member);
    }

    @Override
    public List<EgovMap> searchCls(Map<String, String> searchMap) throws Exception {
        // TODO Auto-generated method stub
        
        try {
            List<EgovMap> list = selectList("EgovMainDAO.searchCls", searchMap);
        } catch(Exception e) {
            e.printStackTrace();
        }
            
        System.out.println(selectList("EgovMainDAO.searchCls", searchMap));
        
        return selectList("EgovMainDAO.searchCls", searchMap);
    }

}