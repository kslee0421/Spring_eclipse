package sp1;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class dto_air {
	String aidz, aid, aname, aport, acode, atell, aplain, anumber, atotal, adate;
	
	
	//Database => 1차원 배열 => return 2차원 배열로 전달
	public ArrayList<String> listdata(){
		ArrayList<String> al = new ArrayList<String>();
		al.add(getAidz());
		al.add(getAid());
		al.add(getAname());
		al.add(getAport());
		al.add(getAcode());
		al.add(getAtell());
		al.add(getAplain());
		al.add(getAnumber());
		al.add(getAtotal());
		al.add(getAdate());
		return al;  //-> controller로 가서 modul과 맞추기
	}
}

