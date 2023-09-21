package batis;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/*@Autowired : XML에 등록된 bean에 대한 id 값을 가져올 때 사용함 :의존성주입 리소스와 인젝트 썼으면 안해도 됨
 *@SuppressWarnings : xml에 id에 이름을 불러와서 로드하여 해당 SQL 정보를 class에 전달하게 됩니다.
 *@Inject : xml에 대한 데이터를 가져올 때 사용하는 의존성 주입
 *@Resource : @Autowired의 확장형
 */

@Controller
public class webpage {
	datavo vo = new datavo(); //필드에 올려도 좋다
	@Autowired
	BasicDataSource datasource; 	
	@SuppressWarnings("unused")	
	@Inject
	private SqlSessionFactory sqlsessionfactory;
	@Resource
	private SqlSessionTemplate sqlsession;
	
	//리뷰 수정완료 페이지
	@RequestMapping("data_modify.do")
	public void modifyok(@ModelAttribute("review") datavo datavo) throws Exception{
		SqlSession se = sqlsessionfactory.openSession(); // 데이터베이스 연결
		int result =se.update("reviewDB.review_update",datavo); //두개를 보낼 수 없다
		if(result > 0) {
			System.out.println("정상적으로 수정완료!!");
		}
		else {
			System.out.println("데이터 베이스 문법 오류!!");
		}
		se.close();
		
	}

	
	//리뷰 수정
	@RequestMapping("review_modify.do")
	public String review_view(Model m,@RequestParam(required = false) String ridx) {
		SqlSession se =null;
		try {
			se = sqlsessionfactory.openSession(); // 데이터베이스 연결
			/* = se.selectOne("reviewDB.selectone",ridx); //값이 날라오므로 받으려면 뒤에 ridx 각각받을때
			m.addAttribute("id",vo....)
			m.addAttribute("id",vo....)
			m.addAttribute("id",vo....)  // 이런식으로,,.,
			*/
			datavo vo = se.selectOne("reviewDB.selectone",ridx);
			ArrayList<String> onedata= new ArrayList<String>();
			onedata.add(vo.getRidx());
			onedata.add(vo.getRname());
			onedata.add(vo.getRpass());
			onedata.add(vo.getRtext());
			onedata.add(vo.getRindate());
			m.addAttribute("one",onedata);
		}
		catch (Exception e) {
			System.out.println("SQL 문법오류");
		}
		finally{
			se.close();
		}
		return "/WEB-INF/jsp/review_view";
	} 
	
	
	//리뷰 삭제
	@RequestMapping ("review_delete.do")
	public String review_del(HttpServletResponse res, @RequestParam(required = false) String ridx) {  //vo로 안받고 @RequestParam로 받아도 됨 required = false: 예외처리 
		PrintWriter pw = null;
		SqlSession se =null;
		try {
			se = sqlsessionfactory.openSession(); // 데이터베이스 연결
			int result = se.delete("reviewDB.review_delete",ridx); //ridx로 값을 보내자
			if(result > 0) {
				pw = res.getWriter();
				pw.write("<script>"
						+ "alert('정상적으로 삭제 완료 되었습니다.');"
						+ "location.href='./review_list.do'"
						+ "</script>");
			}
			else{
				System.out.println("SQL 문법 오류 발생!!");
			}
		}
		catch (Exception e) {
			System.out.println("올바른 접근방식이 아닙니다.");
		}
		finally {
			se.close();
		}
		return null;
	}
	
	//리뷰 리스트 출력
	//required = false는 문자형태의 자료형일 때 사용
	//defaultValue는 숫자형태의 자료형일 때 사용
	@RequestMapping("review_list.do")
	public void review_list(@RequestParam(required = false) String search, @RequestParam(defaultValue ="0") int ridx, HttpServletRequest req, HttpServletResponse res) { //required = false는 String일때
		List<datavo> data =null;
		try {
			SqlSession se = sqlsessionfactory.openSession(); // 데이터베이스 연결
			String ct =se.selectOne("reviewDB.counting");
			int ctint =Integer.parseInt(ct);
			if(ridx<2) {
				ridx=0;
			}
			else{
				ridx = ((ridx-1)*2);
				
			}
			if(search==null || search=="null"||search=="") {
				data =se.selectList("reviewDB.selectall",ridx);
			}
			else {
				data =se.selectList("reviewDB.selectsearch",search);//mapper 이름 .id setter
			}
			
			req.setAttribute("ct", ct);
			req.setAttribute("data", data);
			req.setAttribute("search", search);
			
			//System.out.println(data.get(0).getRname()); //getter로 가지고 옴
			RequestDispatcher ds = req.getRequestDispatcher("/WEB-INF/jsp/review_list.jsp");
			ds.forward(req,res);
			se.close();
		}
		catch(Exception e) {
			System.out.println("Database 접속 오류발생!!");
		}
	} 
	
	
	//해당페이지에 데이터 갯수가 출력되도록 mybatis를 사용
	
	//selectList : 해당 데이터를 범위 안에서 모두 가져올 때 사용
	//selectOne : 하나의 데이터를 가져올 때
	
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
