package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class product_ok {
	Connection con = null;
	private void dbcon() {
		try {
			this.con = dbconfig.info(); //dconfig 안의 info를 가지고오자
		}
		catch(Exception e) {
			System.out.println("DB 접속오류!!");
		}
	}
	protected int modify_sql(String pidx, String pcode, String pmoney, String pname,  String pimg,  String psale, String puse) {
		int sign = 0;
		try {
			this.dbcon();
			String sql = "update product set pname=?, pmoney=?, psale=?, puse=? where pidx=? and pcode=?";
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setString(1, pname);
			ps.setString(2, pmoney);
			ps.setString(3, psale);
			ps.setString(4, puse);
			ps.setString(5, pidx);
			ps.setString(6, pcode);
			sign = ps.executeUpdate();
			ps.close();
			this.con.close();
			
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("SQL 문법 오류 발생");
		}
		
		return sign;
	}
}
