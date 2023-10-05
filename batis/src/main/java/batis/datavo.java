package batis;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

//VO - DTO/DAO랑의 차이? 배열
@Setter
@Getter
public class datavo {
	String ridx,rname,rpass,rtext,rindate;
	String cnt;
	//file 
	String filename; 
	long filesize;
	File filesave;
	
}
