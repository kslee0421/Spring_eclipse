//외부 url 접속 정보 알아오기

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class net3 {

	public static void main(String[] args) throws Exception{
		String url = "https://attrangs.co.kr/shop/list.php?cate=0111";
		URL urls = new URL(url);
		URLConnection con = urls.openConnection();
		
		System.out.println(urls.getProtocol()); //http, https
		System.out.println(urls.getPort()); //포트정보
		System.out.println(urls.getHost()); //도메인정보
		System.out.println(urls.getFile()); //경로 +파미터값
		System.out.println(urls.getPath()); //현재 경로를 확인
		System.out.println(urls.getQuery()); //파라미터값만 가지고 올때
		
		InputStream is = urls.openStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF8"); //바꾸기위해 Reader쓴다
		BufferedReader br = new BufferedReader(isr);
		
		PrintWriter pw = new PrintWriter(new FileOutputStream("login.html"));
		String str = "";
		while((str=br.readLine())!=null) {
			pw.println(str);
		}
		pw.close();
		br.close();
		isr.close();
		is.close();

	}

}
