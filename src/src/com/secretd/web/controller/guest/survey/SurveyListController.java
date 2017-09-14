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
public class SurveyListController extends HttpServlet {
	int ans[][] = new int[16][2];
	@Override
	protected void doGet(HttpServletRequest  request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		String _nextNum = request.getParameter("nextNum");
		String nextNum="1";
		String ansCheck= request.getParameter("ansCheck");
		String _ansNum = request.getParameter("ansNum");
     	String ansNum="1";
     	int number=0;
     	
     	
     	
     	String[] nums = request.getParameterValues("number");
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
		s=surveydao.getList(nextNum); //nextNum이 8일 때 
		if(s.get(0).getD1()==null)  {
		request.setAttribute("list",surveydao.getList(nextNum));
		}
		else
		{
			String nNum =surveydao.getNextNum(nextNum,ansNum);
			System.out.println("nNum : "+nNum);
			
	    	if(Integer.parseInt(ansNum)>=2) {
	    	
	     	int result=0;
	       result=nums.length;

			
	    	int hh=surveydao.getNum(result,nextNum,Integer.toString(Integer.parseInt(ansNum)-1)) ;	    	
	    	ans[Integer.parseInt(ansNum)-1][0]=Integer.parseInt(ansNum)-1;
	    	ans[Integer.parseInt(ansNum)-1][1]=hh;	    	
	    	System.out.println("배열 : "+ans[Integer.parseInt(ansNum)-1][0]);
	    	System.out.println("배열2 : "+ans[Integer.parseInt(ansNum)-1][1]);    
	    	
	    	for(int i=0;i<16;i++)
				for(int j=0;j<2;j++)
					System.out.println("hey ans["+i+"]["+j+"] : "+ans[i][j]);
	     	}
			request.setAttribute("list2",surveydao.getSymptomList(nextNum,ansNum));
			if(nNum.equals("END")) {
				String dis1="";
				String dis2="";
				String dis3="";
				int tmp0=0;
				int tmp1=0;
				/*for(int i=0;i<16;i++)
					for(int j=0;j<2;j++)
						System.out.println("ans["+i+"]["+j+"] : "+ans[i][j]);*/
				int cnt;
				for(int i=1; i<15; i++)
					for(int j=1;j<=15-i;j++)
						if(ans[j][1]<ans[j+1][1]) {
							tmp0=ans[j+1][0];
							tmp1=ans[j+1][1];
							ans[j+1][0]=ans[j][0];
							ans[j+1][1]=ans[j][1];
							ans[j][0]=tmp0;
							ans[j][1]=tmp1;
						}
				dis1=Integer.toString(ans[1][0]);
				dis2=Integer.toString(ans[2][0]);
				dis3=Integer.toString(ans[3][0]);
				
				System.out.println("dis1 : "+dis1);
				System.out.println("dis2 : "+dis2);			
				System.out.println("dis3 : "+dis3);
				
				response.sendRedirect("result?dis1="+dis1+"&dis2="+dis2+"dis3="+dis3+"nextNum="+nextNum);
			}
		}	
		request.getRequestDispatcher("/WEB-INF/views/guest/survey/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
