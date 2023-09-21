package sp1;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;



public class air_sql {  //모듈
	Connection con = null;
	dbconfig db =new dbconfig();
	String sql = null;
	int msg = 0;
	PreparedStatement ps = null; //insert할때
	ResultSet rs = null;
	Statement st = null;// sql구문 실행하는 클래스
	//여러가지 테이블에 대한 총 갯수 값 파악하는 메소드
	
	
	public int total_sum(String tablename) throws Exception {
		this.con =this.db.info();
		this.sql = "select count(*) as cnt from " + tablename;
		this.st = this.con.createStatement();
		ResultSet rs = this.st.executeQuery(this.sql);
		rs.next();
		int sum =Integer.parseInt(rs.getString("cnt")) ;
		this.st.close();
		this.con.close();
		return sum;
		
	}
	
	
	protected ArrayList<ArrayList<String>> person_list(int vpage) throws Exception {  //?를 파라매터로 가지고 오자
		ArrayList<ArrayList<String>> alldata = new ArrayList<ArrayList<String>>();
		this.con = this.db.info();
		try {
			this.sql = "select * from air_person order by aidz desc limit "+vpage+",2";
			this.ps = this.con.prepareStatement(this.sql);
			ResultSet rs = this.ps.executeQuery();
			dto_air da = new dto_air(); //dto 1차원 배열을 로그하기 위한 호출
			while(rs.next()) {
				//System.out.println(rs.getString("aidz"));
				da.setAidz((rs.getString("aidz")));
				da.setAid((rs.getString("aid")));
				da.setAname((rs.getString("aname")));
				da.setAport((rs.getString("aport")));
				da.setAcode((rs.getString("acode")));
				da.setAtell((rs.getString("atell")));
				da.setAplain((rs.getString("aplain")));
				da.setAnumber((rs.getString("anumber")));
				da.setAtotal((rs.getString("atotal")));
				da.setAdate((rs.getString("adate")));
				alldata.add(da.listdata());
			}
			this.ps.close();
			this.con.close();
		}
		
		catch(Exception e) {
			System.out.println("SQL 오류발생!!");
		}
		return alldata;
	}
	
	protected int perinsert(String aid, String aname,String aport,String acode,String atell,String aplain,String anumber,String atotal) throws Exception{  //contrller에서 가지고옴
		this.con =this.db.info();
		this.con.setAutoCommit(false); //트랜젝션
		try {
			//SQL문 : 인원수를 확인하기 위한 aql 문법
			String count = "select count(aperson) as cnt from air_recerve where acode= '"+acode+"' and aperson >= '"+anumber+"'"; //prestatement에서만 물음표 가능 간단한 셀렉트에 좋음
			this.st =this.con.createStatement(); //물음표 쓰기 어려워짐
			ResultSet rs = this.st.executeQuery(count);
			//System.out.println(count);
			
			rs.next();
			if(rs.getString("cnt").equals("1")) { //해당 요청 인원과 여유인원이  맞을 경우 
				//사용자 정보를 입력 받아서 SQL에 저장 시킴
				this.sql =" insert into air_person values ('0',?,?,?,?,?,?,?,?,now()) ";
				this.ps = this.con.prepareStatement(this.sql);
				ps.setString(1, aid);
				ps.setString(2, aname);
				ps.setString(3, aport);
				ps.setString(4, acode);
				ps.setString(5, atell);
				ps.setString(6, aplain);
				ps.setString(7, anumber);
				ps.setString(8, atotal);
				this.msg = this.ps.executeUpdate();//select니까
				if(this.msg == 1){ //해당 정보가 정상적 입력이 되었을경우 기존 인원수를 조정
					this.sql = "update air_reserve set aperson aperson-'"+anumber+"' where acode = '"+acode+"'";
					this.st =this.con.createStatement();
					this.st.executeUpdate(this.sql); //update delete 하고 끝낼것이므로 업데이트 진행 후 종료
				}
				this.ps.close();
				
			}
			else {
				this.msg = 3; //여유좌석이 없음
			}	
			this.con.commit(); //transaction 종료
		}	
		catch(Exception e) {
			System.out.println("데이터 입력시 조건에 문제가 발생하여 입력취소");
			this.con.rollback();  //입력사항 취소
		}
		this.st.close();
		this.con.close();
		return this.msg; //Controller로 값을 넘겨줌
	}
	//항공정보 입력파트
	protected int insert(String acode,String aplain,String anation,String adepart,String aperson,String aprice,String astart,String aend) {
		try {
			this.con =this.db.info();
			this.sql =" insert into air_reserve values ('0',?,?,?,?,?,?,?,?,now()) ";
			this.con.prepareStatement(this.sql);
			ps.setString(1, acode);
			ps.setString(2, aplain);
			ps.setString(3, anation);
			ps.setString(4, adepart);
			ps.setString(5, aperson);
			ps.setString(6, aprice);
			ps.setString(7, astart);
			ps.setString(8, aend);
			this.msg =this.ps.executeUpdate();
			this.ps.close();
			this.con.close();
		}	
		catch(Exception e) {
			System.out.println("Database 문법 오류 발생 및 연결 오류!!");
		}
		return 0;
	}
}
