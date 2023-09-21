import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udp_client {

	public static void main(String[] args) {
		client_udp cu = new client_udp();
		cu.cudp();

	}

}

class client_udp{
	private int port = 0;
	private String ip = null;
	private int myport = 0;
	public DatagramSocket ds= null; //udp socket
	public DatagramPacket dp = null;
	public InetAddress ia = null; //ip
	public BufferedReader br =null;
	public String msg = null;
	
	public client_udp() {
		this.ip= "192.168.110.216";
		this.port =7000;
		//this.myport = 7001;
		//랜덤을 이용한 다중 접속
		this.myport = (int)Math.ceil(Math.random()*1000)+10000;
	}
	
	public void cudp() {
		try {
			String id = "";
			String pw = "";
			int ck = 0;
			this.ia = InetAddress.getByName(this.ip); //서버 ip를 가져옴
			//자신의 port에 대한 소켓을 오픈
			this.ds = new DatagramSocket(this.myport);
			
			while(true) {
				if(ck == 0) {					
					System.out.println("아이디를 입력하세요 : ");
					this.br = new BufferedReader(new InputStreamReader(System.in));
					id = this.br.readLine().intern();
					
					System.out.println("패스워드를 입력하세요 : ");
					this.br = new BufferedReader(new InputStreamReader(System.in));
					pw = this.br.readLine().intern();
					
					
					if(id =="hong" || pw=="a1234") {
						System.out.println("아이디와 패스워드가 잘못입력되었습니다.");
						System.exit(0);
					}
					else {
							System.out.println("환영합니다.");
					}
				}
				else {
					System.out.println("메세지를 입력하세요 : ");
				}		
				
				this.br = new BufferedReader(new InputStreamReader(System.in));
				this.msg = this.br.readLine().intern();
				byte by[] =this.msg.getBytes();
			
				//서버로 해당 메세지를 패킷을 이용해서 전송
				//DatagramPacket(byte배열,배열크기, 서버ip, 서버포트)
				this.dp = new DatagramPacket(by,by.length,this.ia,this.port);
				this.ds.send(dp); //서버로 전송
				System.out.println("메세지 전송 완료!!");
				
				//서버에서 전송된 값을 받는 역할
				byte[] by2 = new byte[200];
				this.dp =new DatagramPacket(by2, by2.length);
				this.ds.receive(this.dp);
				System.out.println(new String(this.dp.getData()));
				ck++;
			}
		}
		catch(Exception e ) {
			System.out.println("서버 접속 오류!!");
		}
	}
}