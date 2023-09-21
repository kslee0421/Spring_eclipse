import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*응용문제 client와 server가 지속적으로 채팅되도록 코드변경
 * 아이디 입력 = >server전송 =>메세지를 client전송 => client메세지를전송
 * 
 * 응용문제2 client에서 아이디와 패스워드를 모두 입력합니다.
 * 단, 아이지 hong 패스워드 : a1234 해당 입력시
 * 채팅이 시작되며, 두, 입력값 중 하나라도 틀릴경우 프로세서는 종료 됩니다
 * */
public class udp_server {
//	UDP - server 통신 1:1
	public static void main(String[] args) {
		server_chat sc =new server_chat();
		sc.udp();
	}

}

class server_chat {
	private int port = 0; //서버 ip
	private String ip = null; //서버 udp 포트
	public DatagramSocket ds= null; //udp socket
	public DatagramPacket dp = null; //메세지 송수신 패킷
	public InetAddress ia = null; //서버 ip, 접소자 ip 기록
	String msg = ""; //메세지
	public BufferedReader br =null;
	
	/*
	 udp 포크가 서버 포트 별도, 자신이 접속하는 포트 별도
	 중복 발생시 접속 차단
	  */
	
	public server_chat() {
		this.ip= "192.168.110.216";
		this.port =7000; //1:1 통신
	}
	
	public void udp() {
		try {			
			this.ia = InetAddress.getByName(this.ip); //서버 ip
			System.out.println("udp server 오픈!!");
			this.ds= new DatagramSocket(this.port); //udp 포트 오픈
			
			while(true) {
				byte[] by = new byte[200]; //메세지 크기
				this.dp = new DatagramPacket(by, by.length); //client에서 오는 패킷 크기를 정함 
				System.out.println("채팅시작!!");
				this.ds.receive(this.dp); // client 에서 보낸 메세지를 서버에서 받는 역할
				
				this.msg =new String (this.dp.getData());
				System.out.println(this.msg);
				
				/*클라이언트로 메세지를 서버에서 전송하는 형태*/
				System.out.println("메세지를 입력하세요 : ");
				/*클라이언트 ip ,udp port 정보 가져옴 */
				InetAddress ia2 = this.dp.getAddress();
				int port2 = this.dp.getPort();
								
				this.br = new BufferedReader(new InputStreamReader(System.in));
				this.msg = this.br.readLine();
				byte by2[] = this.msg.getBytes();
				
				this.dp = new DatagramPacket(by,by.length,ia2,port2);
				this.ds.send(this.dp); //서버에서 클라이언트로 메세지를 전송
			}
			//System.out.println(this.ia);
			//System.out.println("[Udp chatting!!]");
			//String msg = this.sc.nextLine();
		}
		catch(Exception e) {
			System.out.println("UDP 서버 오픈 오류!!");
		}
	}
}