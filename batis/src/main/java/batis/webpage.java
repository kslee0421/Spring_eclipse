package batis;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/*@Autowired : XML에 등록된 bean에 대한 id 값을 가져올 때 사용함 :의존성주입 리소스와 인젝트 썼으면 안해도 됨
 *@SuppressWarnings : xml에 id에 이름을 불러와서 로드하여 해당 SQL 정보를 class에 전달하게 됩니다.
 *@Inject : xml에 대한 데이터를 가져올 때 사용하는 의존성 주입
 *@Resource : @Autowired의 확장형
 */

@SuppressWarnings("unused")  //노란색 자꾸 띄우지 말고 사용하지 않는 코드만 경고해주셈
@Controller
public class webpage {
	datavo vo = new datavo();
	
	@Autowired		//의존성 주입 : xml에 등록된 bean에 대한 id값을 가져올 때 사용함, batis할땐 필요없음 예시 때문에 씀
	BasicDataSource datasource;
	//xml에 id에 이름을 로드하여 해당 SQL 정보를 class에 전달하게 됩니다.
	
	
	@Inject		//xml에 대한 데이터를 가져올 때 사용하는 의존성 주입
	private SqlSessionFactory sqlsessionfactory;
	
	//@Resource	//@Autowired 확장형
	//private SqlSessionTemplate sqlsession;
	
	@RequestMapping("/fileupok.do")
	public void upload(MultipartFile[] mfile, HttpServletRequest req) throws Exception{ //서버에 저장할꺼니까 req 필요
		SqlSession se = sqlsessionfactory.openSession();
		//System.out.println(mfile.length);
		//System.out.println(mfile[1].getOriginalFilename());
		int ea = mfile.length;
		String filename = null; //첨부파일 실제 이름
		String type = null; //첨부파일 type 가져오는 역할
		//String type = mfile[0].getContentType();
		
		//System.out.println(type.lastIndexOf(".") + 1);
		String url = req.getServletContext().getRealPath("/files/"); //웹서버
		//System.out.println(url);
		Date dt = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		/*동일한 파일명을 제거하기 위해서 만들어주는 코드*/
		
		//StringBuffer sb = new StringBuffer();
		ArrayList<String> sb = new ArrayList<String>();
		
		String rename = null;
		int w= 0;
		while (w <ea) {
			filename = mfile[w].getOriginalFilename();
			type = filename.substring(filename.lastIndexOf("."));
			rename = sf.format(dt) + "_" + w + type;
			FileCopyUtils.copy(mfile[w].getBytes(), new File(url + rename));
			//sb.append("./files/"+rename+",");
			sb.add("./files/"+rename);
			Thread.sleep(1000); //작업을 1000=1초 일시정지
			
			w++;
		}
		//String.join(배열을 문자열로 변경) : ArrayList, List , LinkedList 에서 사용가능
		String dbfilename = String.join(",", sb);
		int result = se.insert("reviewDB.file_upload",dbfilename);
		if(result > 0) {
			System.out.println("정상적으로 입력 완료 되었습니다.");
		}
		else {
			System.out.println("데이터 저장에 문제가 발생 하였습니다.");
		}
	}
	//E:\spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\batis\files\
	
	
	//리뷰 수정 완료 페이지
	@RequestMapping("/data_modify.do")
	public void modifyok(@ModelAttribute("review") datavo datavo) throws Exception {
		SqlSession se = sqlsessionfactory.openSession();
		int result = se.update("reviewDB.review_update",datavo);	//확인하고 조회할 값이 여러개면 vo를 사용해야한다
		if(result > 0) {
			System.out.println("정상적으로 수정완료");
		}
		else {
			System.out.println("Database 문법 오류");
		}
		se.close();
	}
	
	//리뷰 수정 전 페이지
	@RequestMapping("/review_modify.do")
	public String review_view(Model m ,HttpServletResponse res,@RequestParam(required = false) String ridx) {
		res.setContentType("text/html; charset=utf-8");
		SqlSession se = null;
		try{
			se = sqlsessionfactory.openSession();
			//List<datavo> list = se.selectList("reviewDB.selectone",ridx); //배열로 받음 (리스트를 통째로 불러옴)
			vo = se.selectOne("reviewDB.selectone",ridx);	//지정된 한가지에 데이터만 받아옴  //각각 받음 ex) m.addAttribute("id",vo.getRid())....
			ArrayList<String> onedata = new ArrayList<String>();	//각각 안받고 데이터를 배열로 받기위한 빈 배열
			onedata.add(vo.getRidx());
			onedata.add(vo.getRname());
			onedata.add(vo.getRpass());
			onedata.add(vo.getRtext());
			onedata.add(vo.getRindate());
			m.addAttribute("one",onedata); //배열로 날리기
		}
		catch(Exception e) {
			System.out.println("SQL 문법 오류 발생!");
		}
		finally {
			se.close();
		}
		return "/WEB-INF/jsp/review_view";
	}
	
	
	
	
	//리뷰 삭제
	@RequestMapping("/review_delete.do")
	public String review_del(HttpServletResponse res ,@RequestParam(required = false) String ridx) {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter pw = null;
		SqlSession se = null;
		try {
			se = sqlsessionfactory.openSession();
			int result = se.delete("reviewDB.review_delete",ridx);
			if(result > 0){
				pw = res.getWriter();
				pw.write("<script> "
						+ "alert('정상적으로 삭제 완료 되었습니다.'); "
						+ "location.href='./review_list.do';"
						+ "</script>");
				System.out.println("정상적으로 삭제 완료 되었습니다.");
			}
			else {
				System.out.println("SQL 문법 오류 발생!");
			}
		}
		catch(Exception e) {
			System.out.println("올바른 접근 방식이 아닙니다.");
		}
		finally {
			se.close();
		}
		return null;
	}
	
	
	//@RequestParam(required = false) 타입이 문자형 String일때 , @RequestParam(defaultValue = "0") 타입이 숫자형 int 일때
	//리뷰 리스트 출력
	@RequestMapping("/review_list.do")
	public void review_list(@RequestParam(required = false) String search,@RequestParam(defaultValue = "0") int ridx,HttpServletRequest req, HttpServletResponse res) {
		SqlSession se = null;
		List<datavo> data = null;
		try {
			se = sqlsessionfactory.openSession();
			String ct = se.selectOne("reviewDB.counting");
			if(ridx < 2) {
				ridx = 0;				
			}
			else {
				//현재페이지 / (페이징의 번호 * 페이징의 갯수) + 1  , ridx = (ridx * 2) - 2;
				ridx = ((ridx - 1) * 5);
			}
			if(search == null || search == "null" || search == "") {
				data = se.selectList("reviewDB.selectall",ridx);				
			}
			else {
				data = se.selectList("reviewDB.selectsearch",search);
			}
			
			req.setAttribute("ct", ct);
			req.setAttribute("data", data);
			req.setAttribute("search", search);
			//System.out.println(data.get(0).getRname());	//0번째의 이름을 getter로 불러와야 값이 조회됌
			RequestDispatcher ds = req.getRequestDispatcher("/WEB-INF/jsp/review_list.jsp");
			ds.forward(req, res);
			se.close();
		}
		catch(Exception e) {
			System.out.println("Database 접속 오류!");
		}
	}
	
	
	
	//selectList : 대량 조회(해당 데이터를 범위 안에서 모두 가져올 때)
	//selectOne : 단순 조회 (하나의 데이터만 가져올 때)
	//해당 페이지에 데이터 갯수가 출력되도록 mybatis를 사용
	@RequestMapping("/data_select.do")
	public String data_select(Model m) throws Exception {
		SqlSession se = sqlsessionfactory.openSession();
		datavo dv = se.selectOne("reviewDB.counting");
		String count = dv.getCnt();
		m.addAttribute("count",count);
		return null;
	}
	
	
	//@ModelAttribute : parameter, method 형태를 구성하게 됩니다. vo.dto형태로 구성원칙
	//해당 name값 vo.dto에 동일하게 셋팅을 하며, Database Field명과 동일 할 경우 요청한 값을 모두 DB에 저장 시킬 수 있습니다.
	//insert 인자값에 사용한 이유는 집어넣어야 하기 때문 update에서도 필요함
	/*
		@ModelAttribute(별명 명칭) - 1개 이상 사용시 , 여러개 사용할때 별명을 사용하면 hidden 쓸때 key값으로 사용할 수 있다.
	*/
	@RequestMapping("/data_insert.do")
	public String data_insert(@ModelAttribute("review") datavo datavo) throws Exception {
		//System.out.println(datavo.getRname());
		SqlSession se = sqlsessionfactory.openSession();
		int a = se.insert("reviewDB.review_insert",datavo);
		if(a > 0) {
			System.out.println("정상적으로 리뷰가 등록되었습니다.");
		}
		else {
			System.out.println("SQL 문법 오류 발생");
		}
		return "/WEB-INF/jsp/login";
	}
	
	//dbconfig.xml 사용해서 항공 갯수 확인해봄
	@RequestMapping("/login.do")
	public String logins() {		//BasicDataSource만 사용
		try {
			Connection con = datasource.getConnection();
			String sql = "select count(*) as cnt from air_reserve";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String result = rs.getString("cnt");
			System.out.println(result);
			rs.close();
			ps.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println("Database 접속 오류!!");
		}
		return "/WEB-INF/jsp/login";
	}
}
