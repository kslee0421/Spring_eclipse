package batis;

import java.sql.*;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class webpage {
	
	@Autowired //XML에 등록된 bean에 대한 id 값을 가져올 때 사용함 :의존성주입
	BasicDataSource datasource; 
	
	//xml에 id에 이름을 불러와서 로드하여 해당 SQL 정보를 class에 전달하게 됩니다.
	@SuppressWarnings("unused")
	
	@Inject //xml에 대한 데이터를 가져올 때 사용하는 의존성 주입
	private SqlSessionFactory sqlsessionfactory;
	
	@Resource // @Autowired의 확장형
	private SqlSessionTemplate sqlsession;
	
	//해당페이지에 데이터 갯수가 출력되도록 mybatis를 사용
	@RequestMapping("data_select.do")
	public String data_select() throws Exception{
		SqlSession se = sqlsessionfactory.openSession();
		datavo dv = se.selectOne("reviewDB.counting"); //던질값이 없으면 그냥 뒤에 비워 두어도 된다
		System.out.println(dv.getCnt());
		return null;
	}
	
	//@ModelAttribute : parameter, method형태를 구성하게 됩니다. vo ,dto형태로 구성원칙
	//해당 name값 vo, dto에 동일하게 셋팅을 하며, Database Field명과 동일 할 경우
	//요청한 값을 모두 DB에 저장 시킬 수 있습니다.
	/*
	 @ModelAttribute 별명 명칭
	 */
	@RequestMapping("data_insert.do")
	public String data_insert(@ModelAttribute("review") datavo datavo) throws Exception{ // review는 닉네임이다 바꿔도 됨 mvc 쓰기위해  @ModelAttribute 만 써도됨
		SqlSession se = sqlsessionfactory.openSession();
		//System.out.println("insert"+datavo);
		int a = se.insert("reviewDB.review_insert",datavo);
		if(a>0) {
			System.out.println("정상적으로 리뷰가 등록 되었습니다.");
		}
		else {
			System.out.println("SQL 문법오류 발생");
		}
		
		return "/WEB-INF/jsp/login";
	}
	
	@RequestMapping("login.do")
	public String logins() { //BasicDataSource 만 사용 
		try {
			Connection con = datasource.getConnection();
			String sql = "select count(*) as cnt from air_reserve";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String result = rs.getString("cnt");
			System.out.println(con);
			rs.close();
			ps.close();
			con.close();
			
		}
		catch(Exception e) {
			System.out.println("Database 접속오류!!");
		}
		return "/WEB-INF/jsp/login";
	}
	
}
