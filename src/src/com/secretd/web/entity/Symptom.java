package src.com.secretd.web.entity;
import java.util.Date;

public class Symptom {

	private String num;
	private String content;
	
	
	public Symptom() {
		// TODO Auto-generated constructor stub
	}


	public Symptom(String num, String content) {
		super();
		this.num = num;
		this.content = content;
	}


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	
	
	
	
}