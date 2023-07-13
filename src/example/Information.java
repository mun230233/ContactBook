package example;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;

public class Information {
	
	private String name;
	private String number;
	private String mail_adress;
	
	public String get_name() {
		return name;
	}
	
	public String get_num() {
		return number;
	}
	
	public String get_mail() {
		return mail_adress;
	}
	
	public Information(String name, String number, String mail_adress) {
		this.name = name;
		this.number = number;
		this.mail_adress = mail_adress;
	}
	
	public void save(int num) {
		
		try (BufferedWriter fw = new BufferedWriter(new FileWriter("info.csv", (num==0?false:true)))) {
			
			fw.write(name + "," + number + "," + mail_adress + "\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
