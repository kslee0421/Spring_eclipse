package batis;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("filem")
public class filemodule {

	@Resource(name="sqlsession")
	private SqlSessionTemplate session;
	
	public List<filevo> select() {
		List<filevo> fv = session.selectList("reviewDB.file_select");
		return fv;
	}
}
