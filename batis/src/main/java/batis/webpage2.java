package batis;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class webpage2 {
	@PostMapping("/ajax.do")
	public String ajax(@RequestParam(required =false) String userid, Model m) {
		m.addAttribute("mid",userid); //userid를 mid로 뷰에 보내자
		System.out.println(userid);;
		return "ajax";
	}
	
	@PostMapping("/pay2.do")
	public String pay2(@RequestParam(required = false) String product_code  //DTO가없으므로 @RequestParam로 받자
			,@RequestParam(required = false) String product_name //name값 가지고오기
			,@RequestParam(required = false) String product_money
			,@RequestParam(required = false) String product_ea
			,Model m) { 
		//addAllAttributes : Collection, Map 배열 형태로 view로 보낼때 사용)
		List<String> pd = new ArrayList<String>();
		pd.add(product_code);
		pd.add(product_name);
		pd.add(product_money);
		pd.add(product_ea);
		//System.out.println(pd);
	
		m.addAllAttributes(Arrays.asList(pd));
		//System.out.println(m);
		return "pay2";
	}
	
	@PostMapping("/pay3.do")
	public String pay3(@ModelAttribute("data") paydto paydto, HttpServletRequest req) {
		req.setAttribute("goodcode", paydto.getGoodcode()); //req가 있어야 jsp에 찍을 수 있으니까
		req.setAttribute("price", paydto.getPrice());
		req.setAttribute("buyeremail", paydto.getBuyeremail());
		req.setAttribute("buyername", paydto.getBuyername());
		req.setAttribute("buyertel", paydto.getBuyertel());
		req.setAttribute("goodea", paydto.getGoodea());
		req.setAttribute("goodname", paydto.getGoodname());
		req.setAttribute("gopaymethod", paydto.getGopaymethod());
		req.setAttribute("rec_addr", paydto.getRec_addr());
		req.setAttribute("rec_addr_dtc", paydto.getRec_addr_dtc());
		req.setAttribute("rec_post", paydto.getRec_post());
		req.setAttribute("point", paydto.getPoint());
		
		//System.out.println(paydto.getBuyeremail());
		
		return "pay3";
	};
	
	
	
	
	
	
	//파일 DB 저장 및 출력
	private filevo fv;
	
	@Resource(name="filem")
	private filemodule fm;
	
	/*ftp 관련 변수*/
	private String host; //호스트
	private String user; //ftp 사용자 아이디
	private String pass; //ftp 사용자 패스워드
	private int port; //ftp 포트
	
	//FTP CDN 연결형태 (CDN - 콘텐츠 전송 네트워크)
	@RequestMapping("/ftpcon.do")
	//MultipartFile : Stream
	public String ftpcon(MultipartFile mfile, HttpServletRequest req, Model m) { // MultipartFile mfile : 파일 받는 네임값
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("utf-8"); //서버가 utf-8이라서 여기도 utf-8 한글깨짐 방지
		FTPClientConfig cf = new FTPClientConfig();
		try {
			String filenm = mfile.getOriginalFilename();
			System.out.println(filenm);
			
			
			InetAddress inet = InetAddress.getLocalHost();
			String ip = inet.getHostAddress();
			//System.out.println(ip);
			//System.out.println(req.getRemoteAddr());
			//String ip =  req.getRemoteAddr(); //0:0:0 or 0.0.0.0.0.1 =>ip6가 출력이 된다
			/*this.host="kbsn.or.kr";
			this.user="classa";
			this.pass="java_205";
			this.port=21;*/
			
			this.host="iup.cdn1.cafe24.com";
			this.user="primewom137";
			this.pass="acts32dim@@";
			this.port=21;
			
			
			ftp.configure(cf); //FTP client로 연결을 준비하는 단계
			ftp.connect(this.host,this.port); //client로 서버에 접근 (호스트정보, 포트정보)
			
			if(ftp.login(this.user,this.pass)) { //ftp로그인(아이디,패스워드)
				int rp =ftp.getReplyCode(); //status = 200번대 : 정상
				System.out.println(rp);				
				ftp.setFileType(FTP.BINARY_FILE_TYPE); //파일 전송 유형
				//BINARY_FILE_TYPE : 이미지, 동영상, PPT
				//ASCII_FILE_TYPE : txt 파일
				System.out.println(ftp);  //접속성공시 정상적으로 해당 정보를 출력:org.apache.commons.net.ftp.FTPClient@684f5090 이런거 뜨면 잘 연결이 된거
				//ftp.makeDirectory("www"); //디렉토리 생성
				//ftp.removeDirectory("img_ks"); //디렉토리 생성
				//InputStream is = mfile.getInputstream();
				boolean result = ftp.storeFile("www/img/"+filenm,mfile.getInputStream());
				if(result == true) {
					//db 저장
					//cafe24 CDN : http://아이디.cdn1.cafe24.com/img/
					String url ="http://primewom137.cdn1.cafe24.com/img"+filenm;
					m.addAttribute("imgsrc",url);
					
					System.out.println("정상적으로 업로드 되었습니다");
					
					//FTP 파일 삭제 
					//boolean delok = ftp.deleteFile("/www/img/logo.jpg");
					//System.out.println(delok);
				}
				else {
					System.out.println("해당 디렉토리 및 파일에 문제가 발생하였습니다.");
				}
				
			}
			else {
				System.out.println("FTP 정보가 올바르지 않습니다.");
			}
			ftp.disconnect(); //ftp 접속종료
		}
		catch (Exception e) {
			System.out.println(e);
			System.out.println("FTP 접속 정보 오류 및 접속사용자 오버!!");
		}
		finally {
			try {
				ftp.disconnect();
			}
			catch (Exception e) {
				System.out.println("접속종료 오류발생");
			}
		}
		return null;
	}
	
	@RequestMapping("/fileview.do")
	public String fview(Model m) {
		List<filevo> list = fm.select();
		
		m.addAttribute("data",list);
		return null;
	}
	
	//id 중복체크
	@Resource(name="members")	//인스턴스 생성
	private member_module mm;	//객체 생성

	@RequestMapping("/idcheck.do")
	public String idcheck(Model m, @RequestParam(required = false) String userid) {
		try {
			memberdto dto = mm.search_id(userid);
			if(dto == null) {
				m.addAttribute("mid","no");
			}
			else {
				//getter로드시 null일 경우 100% 오류가 발생함
				m.addAttribute("mid","yes");				
			}
		}
		catch(Exception e) {
			System.out.println("Module 접속 오류!");
			m.addAttribute("mid","error");
		}
		
		return "/WEB-INF/jsp/idcheck";
	}
	
	@Inject
	private SqlSessionFactory factory;

	@RequestMapping("/subpage.do")
	public String subpage(Model m) {
		new topclass(m,factory);
		return "subpage";
	}
	
	@RequestMapping("/index.do")
	public String index(Model m) {
		new topclass(m,factory);
		return "index";
	}
	
}


class topclass {
	SqlSession se = null;
	
	public topclass(Model m) {
		this.top(m);
		this.footer(m);
	}
	
	public topclass(Model m,SqlSessionFactory factory) {
		this.se =factory.openSession();
		this.footer(m);
	}
	
	@RequestMapping("/topmenu.do")
	public String top(Model m) {
		m.addAttribute("data","홍길동");
		return "topmenu";
	}
	
	
	@RequestMapping("/footer.do")
	public String footer(Model m) {
		System.out.println(this.se); 
		copyrightdto dto = this.se.selectOne("memberDB.copy");
		m.addAttribute("copy",dto);
		return "footer"; //null로해도됨
	}
	
}







