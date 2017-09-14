package src.com.secretd.web.controller.guest.survey;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.secretd.web.dao.SurveyDao;
import src.com.secretd.web.dao.jdbc.JdbcSurveyDao;
import src.com.secretd.web.entity.Survey;

@WebServlet("/guest/survey/list")
public class SurveyListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest  request, HttpServletResponse response) throws ServletException, IOException {
	
		String _nextNum = request.getParameter("nextNum");
		String nextNum="1";
		String ansCheck= request.getParameter("ansCheck");
		String _ansNum = request.getParameter("ansNum");
     	String ansNum="0";
     	int number=0;
     	String[] nums=new String[20];
		if( _nextNum != null  && ! _nextNum.equals("") )
			nextNum =  _nextNum;
	
		if( _ansNum != null  && ! _ansNum.equals("") )
			ansNum =  _ansNum;
	
	System.out.println("ansNum : "+ansNum);
		SurveyDao surveydao = new JdbcSurveyDao();
		
		request.setAttribute("s", surveydao.get(nextNum));
		System.out.println(nextNum);
		System.out.println(ansNum);
		List <Survey> s= null;
		s=surveydao.getList(nextNum); //nextNumÀÌ 8ÀÏ ¶§ 
		if(s.get(0).getD1()==null) {
		request.setAttribute("list",surveydao.getList(nextNum));
		}
		else
		{

			number=	Integer.parseInt(ansNum) +1;
		 nums = request.getParameterValues("number");
	    	if(Integer.parseInt(ansNum)>=2) {
	    	
	     	int result=0;
	       result=nums.length;
			
	    	request.setAttribute("count",surveydao.getNum(result,nextNum,Integer.toString(number)) );
	    
	    	
	     	}
			request.setAttribute("list2",surveydao.getSymptomList(nextNum,Integer.toString(number)));
		
		
		}
		
		request.getRequestDispatcher("/WEB-INF/views/guest/survey/list.jsp").forward(request, response);
	}
	
	


	protected void doPost(
         HttpServletRequest request, 
         HttpServletResponse response) throws ServletException, IOException {
	
	
	
	
	
	}
	
	
	
	
	
}
