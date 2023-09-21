package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//16
public class product_modify {
	Connection con = null;
	private void dbcon() { //데이터 베이스를 바꿀 수 없게 private
		try {
			this.con = dbconfig.info(); //dconfig 안의 info를 가지고오자
		}
		catch(Exception e) {
			System.out.println("DB 접속오류!!");
		}
	}

	protected ArrayList<String> view_ok(String idx){
		//20
		ArrayList<String> onelist = new ArrayList<String>();
		PreparedStatement ps = null;
		try {
		this.dbcon(); //여기서 대신 불러와서 실행 할 수 있게 DB메소드를 실행
		String sql = "select * from product where pidx=?";
		ps = this.con.prepareStatement(sql);
		ps.setString(1, idx);
		ResultSet rs = ps.executeQuery();
		rs.next(); //데이터 하나만 가지고 올것이므로 while문 안돌림
		//DTO를 안쓰고 바로 가지고 오기 (데이터를 하나만 끌고 올것이기 때문에
		onelist.add(rs.getString("pidx"));
		onelist.add(rs.getString("pcode"));
		onelist.add(rs.getString("pname"));
		onelist.add(rs.getString("pmoney"));
		onelist.add(rs.getString("pimg"));
		onelist.add(rs.getString("psale"));
		onelist.add(rs.getString("puse"));
		ps.close();
		this.con.close();
		}
		catch(Exception e) {
			System.out.println("sql 문법오류!!");
		}
		return onelist; //배열 리턴
	}
}
