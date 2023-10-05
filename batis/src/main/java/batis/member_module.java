package batis;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("members")
public class member_module {

	@Resource(name="template")
	private SqlSessionTemplate tm;
	
	public memberdto search_id(String userid) {
		memberdto dto = tm.selectOne("memberDB.search_id",userid);
		return dto;
	}
}
