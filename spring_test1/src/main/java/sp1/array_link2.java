package sp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

//List : ArrayList, LinkedList, Vector, Stack

public class array_link2 {

	public static void main(String[] args) {
		String a[] = {"A","B","C"};
		List<String> z = Arrays.asList(a); //추가안됨
		System.out.println(z);
		
		List<String> zz = new ArrayList<String>(Arrays.asList(a));
		zz.add("D");
		zz.add(4 ,"J"); // 추가
		zz.set(4, "H"); // 해당하는 데이터를 변경해버림
		
		System.out.println(zz);
		
		//Vector : 쓰레드를 활용함 배열애 대한 안정성이 확보됨 LinkedList보다 빠름 ArrayList보다 느림 
		List<String> zzz = new Vector<String>(Arrays.asList(a));
		zzz.add("10");
		zzz.set(0, "AAA");
		System.out.println(zzz);
		
		//자료형이 없는 경우 (Object)
		List k = new ArrayList<>(Arrays.asList(a)); //자료형이 없으면 디폴트: Object
		k.add(110);
		System.out.println(k);
		
		
		List l = new ArrayList<String>(Arrays.asList(a));//String을 넣어도 들어감
		l.add(110);
		l.add("홍길동");
		System.out.println(l);
		
		ArrayList al = new ArrayList(Arrays.asList(a));
		System.out.println(al); //데이터 베이스 만들때 이렇게 자주 씀
		
		ArrayList<?> ak = new ArrayList<Object>(Arrays.asList(a));
		System.out.println(ak); // <?> : Object : 오브젝트 기호
		
		new datalist().abc(); 
	}	

}

class datalist{
	public void abc() {
		String member [][] = {
				{"홍길동","SKT","45"},
				{"이순신","LGK","55"},
				{"강감찬","SKT","45"}
		};
		
		//System.out.println(Arrays.toString(member[0]));
		//String[] , Integer[] : List 모든 배열을 2차 배열형태로 구성함
		ArrayList<String[]> al = new ArrayList<String[]>(Arrays.asList(member)); //2차배열을 ArrayList에 넣기
		System.out.println (al);
		int w =0;
		while(w <al.size()) {
			System.out.println(al.get(w)[0]);
			w++;
		}
		//ArrayList<Integer[]> aq = new ArrayList<Integer[]>(); //1차 빈배열만들기
		//ArrayList<Integer[]> aw = new ArrayList();  //2차 빈배열 만들기 ver1
		ArrayList<Integer>[] ai = new ArrayList[100];  //2차 빈배열 만들기 ver2
		
		int ww = 0;
		while (ww < 10 ) {
			ai[ww] = new ArrayList<Integer>();
			ww++;
		}
		ai[0].add(10);
		ai[1].add(20);
		ai[2].add(30);
		ai[3].add(40);
		//System.out.println(ai[0].get(0));
		//System.out.println(Arrays.toString(ai));
		
		//2차원 배열 ArrayList 
		//2차원 빈 배열 값 생성
		ArrayList<ArrayList<String>> k = new ArrayList<ArrayList<String>>();
		//2차원 배열에 그룹으로 값을 삽입하기 위한 1차원 빈배열 생성
		System.out.println(Arrays.deepToString(k.toArray()));
		ArrayList<String> kk = new ArrayList<String>();
		kk.add("홍길동");
		kk.add("25");
		kk.add("hong@nate.com");
	
		k.add(kk);  //2차원 배열에 해당 값을 삽입
		
		//새로운 1차원 배열을 생성 후 데이터 입력 
		ArrayList<String> kk2 = new ArrayList<String>();
		kk2.add("이순신");
		kk2.add("36");
		kk2.add("lee@nate.com");
		//새로운 데이터를 2차원 배열로 값을 삽입
		//System.out.println(kk);
		k.add(kk2);
		System.out.println(k);
		//System.out.println(k.get(0).get(2)); // 2차원 배열 중 (그룹, 데이터 index숫자) 
		k.get(1).remove(2); //2차원 배열 중 해당 그룹에서 데이터를 삭제하는 방식( get으로 가지고 와서 3번째 지우기)
		System.out.println(k);
		//데이터 추가
		k.get(1).add("lee@gmail.com");
		System.out.println(k);
		
	}
}

