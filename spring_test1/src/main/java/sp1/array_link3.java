package sp1;

import java.util.ArrayList;
import java.util.Arrays;

//2차원 배열에 대한 응용편
/*
 A조 김경민 배유미 김승균
 B조 이철의 장진호 홍사라
 C조 박병준 전정호 이경선
 D조 최현제 서강인 염무원
 
 [결과]
 [[A조,김경민 , 배유미, 김승균 ]],[B조, 이철의, 장진호, 홍사라]...]
 */
public class array_link3 {

	public static void main(String[] args) {
		//코드 및 결과 출력
		//2차원 배열에 그룹으로 값을 삽입하기 위한 1차원 빈배열 생성
		String member [] = {"김경민", "배유미", "김승균", "이철의","장진호","홍사라","박병준","전정호","이경선","최현제","서강인","염무원"};
		ArrayList<ArrayList<String>> k = new ArrayList<ArrayList<String>>();
		ArrayList<String> a = new ArrayList<String>();
		a.add("A조");
		a.add("김경민");
		a.add("배유미");
		a.add("김승균");
		k.add(a);  //2차원 배열에 해당 값을 삽입
		
		//새로운 1차원 배열을 생성 후 데이터 입력 
		ArrayList<String> b = new ArrayList<String>();
		b.add("B조");
		b.add("이철의");
		b.add("장진호");
		b.add("홍사라");
		k.add(b);  //2차원 배열에 해당 값을 삽입
		
		ArrayList<String> c = new ArrayList<String>();
		c.add("C조");
		c.add("박병준");
		c.add("전정호");
		c.add("이경선");
		k.add(c);  //2차원 배열에 해당 값을 삽입
		
		ArrayList<String> d = new ArrayList<String>();
		d.add("D조");
		d.add("최현제");
		d.add("서강인");
		d.add("염무원");
		k.add(d);  //2차원 배열에 해당 값을 삽입
		System.out.println(k);
	
	}

}
