package egovframework.let.main.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.main.dao.EgovMainDAO;
import egovframework.let.main.vo.IntteVO;
import egovframework.let.main.vo.MemberVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("EgovMainService")
public class EgovMainServiceImpl implements EgovMainService {

    @Resource(name = "EgovMainDAO")
    private EgovMainDAO mainDAO;

    @Override
    public List<EgovMap> getIntteClsList(IntteVO vo) throws Exception {
        return mainDAO.getIntteClsList(vo);
    }

    @Override
    public List<EgovMap> getIntteNameList(IntteVO vo) throws Exception {
        // TODO Auto-generated method stub
        return mainDAO.getIntteNameList(vo);
    }

    @Override
    public EgovMap checkUser(MemberVO member) throws Exception {
        // TODO Auto-generated method stub
        return mainDAO.checkUser(member);
    }

    @Override
    public List<EgovMap> searchCls(Map<String, String> searchMap) throws Exception {
        return mainDAO.searchCls(searchMap);
    }

    
}