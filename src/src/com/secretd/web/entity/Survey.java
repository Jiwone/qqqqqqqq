package src.com.secretd.web.entity;
import java.util.Date;

public class Survey {
	private String num;
	private String queNum;
	private String question;
	private String ansNum;
	private String ans;
	private String nextNum;
	private String d1;
	private String d2;
	
	
	public Survey() {
		// TODO Auto-generated constructor stub
	}


	public Survey(String num, String queNum, String question, String ansNum, String ans, String nextNum, String d1,
			String d2) {
		super();
		this.num = num;
		this.queNum = queNum;
		this.question = question;
		this.ansNum = ansNum;
		this.ans = ans;
		this.nextNum = nextNum;
		this.d1 = d1;
		this.d2 = d2;
	}


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	public String getQueNum() {
		return queNum;
	}


	public void setQueNum(String queNum) {
		this.queNum = queNum;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getAnsNum() {
		return ansNum;
	}


	public void setAnsNum(String ansNum) {
		this.ansNum = ansNum;
	}


	public String getAns() {
		return ans;
	}


	public void setAns(String ans) {
		this.ans = ans;
	}


	public String getNextNum() {
		return nextNum;
	}


	public void setNextNum(String nextNum) {
		this.nextNum = nextNum;
	}


	public String getD1() {
		return d1;
	}


	public void setD1(String d1) {
		this.d1 = d1;
	}


	public String getD2() {
		return d2;
	}


	public void setD2(String d2) {
		this.d2 = d2;
	}
	
	
	
}