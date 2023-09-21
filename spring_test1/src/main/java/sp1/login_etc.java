package sp1;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;

/*
 * Thread -> 동기화 형태 프로그램 개발
 Web Programmer 
 Thread : X (Block,Lock) 
 
 APP Programmer
 Thread : o ()
 */

public class login_etc extends Thread{
	Connection con = null;
	PreparedStatement ps = null;
	String a_id = "";  //web : mname
	String b_email = ""; // web : mpass
	String c_nick = "";
	String part = "";
	int oksign = 0;
	
	//setter
	public login_etc(String a, String b ,String c, String part) {
		this.part = part;
		if(part=="web") {
			this.a_id = a;
			this.b_email = b;
			
		}
		else {
			this.a_id = a;
			this.b_email = b;
			this.c_nick = c;
			
		}
		this.start(); //Thread 실행으로 Database 저장시킴
		this.interrupt();
		// System.out.println(this.a_id + this.b_email + this.c_nick);
	}
	//getter
	protected int result() {
		System.out.println("return 메소드 : "+this.oksign);
		return this.oksign;
	}
	@Override
	public void run() { //data저장
		System.out.println(this.part);
		
		try {
			// MessageDigest : 해당 문자열을 암호화 형태 구성 클래스
			//md5, sha-1, sha-224, sha-256, sha-384, sha-512
			String pw = this.b_email;
			
			MessageDigest md = MessageDigest.getInstance("sha-1");//java.security
			md.update(pw.getBytes()); //해당 암호화 bit로 변경작업
			byte[] se = md.digest(); //byte로 저장
			StringBuilder sb = new StringBuilder();
			for( byte s: se){
				sb.append(String.format("%02x",s )); //두자리 문자로 변환
				//(%02x)2자리 문자로 변환 : 0 숫자를 넣어서 2자리를 제작 (01,02,03,04)
				//(%01x)1자리 문자로 변환 : 1자리 출력 (1,2,3,4)
			};
			
			//insert시 패스워드 저장은 cb 객체를 저장시킴
			
			
			this.con = new dbconfig().info();
			String sql = "insert into login values('0',?,?,?,?,?,?,now())";
			this.ps = this.con.prepareStatement(sql);
			/*코드작성...*/
			this.ps.setString(1, this.part);
			this.ps.setString(2, this.a_id);
			this.ps.setString(3, this.c_nick);
			this.ps.setString(4, sb.toString());
			this.ps.setString(5, this.b_email);
			this.ps.setString(6, this.c_nick);
			
			this.oksign = this.ps.executeUpdate(); //getter에서 리턴값을 만들어주는 코드
			System.out.println("Thread : "+this.oksign);
			this.ps.close();
			this.con.close();
		}
		catch(Exception e) {
			System.out.println("Database 오류발생");
			System.out.println(e);
		}
				
	}
	
}
