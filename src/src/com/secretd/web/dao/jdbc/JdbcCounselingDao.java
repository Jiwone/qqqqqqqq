package src.com.secretd.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.com.secretd.web.dao.CounselingDao;
import src.com.secretd.web.entity.Comment;
import src.com.secretd.web.entity.Counseling;

public class JdbcCounselingDao implements CounselingDao{

	@Override
	public List<Counseling> getList() {
		
		List<Counseling> list = null;
		  Counseling c = null;
		  int n=0;
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";	
		String sql = "SELECT * FROM Counseling WHERE hit > 90 order by hit desc";
	
	     try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection(url, "soonface", "2014");
	        Statement st = conn.createStatement();
	       //  PreparedStatement st = conn.prepareStatement(sql);
	      //   st.setString(1, "%"+name+"%");
	         ResultSet rs = st.executeQuery(sql);
	         
	         list = new ArrayList<>();
	         
	    
	         
	         while (rs.next()) {
	        
	        	c = new Counseling(); 
	        	c.setNumber(rs.getString("number"));
	        	c.setTitle(rs.getString("title"));
	        	c.setHit(rs.getInt("hit"));
	            n = getCount(rs.getString("number"));
	        	c.setCount(n);

	            list.add(c);
	         }

	         rs.close();
	         st.close();
	         conn.close();

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		

	     System.out.println(n);
	     System.out.println(list.get(0).getCount());
	     
		return list;
	}

	@Override
	public List<Counseling> getList(String category, int page) {
		
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";	
		   String sql2 = "SELECT * FROM Counseling WHERE category LIKE ? order by number limit ?,10";
		   
		     int n=0;
		   List<Counseling> list2 = null;
		   Counseling c = null;
		     int offset =(page-1) *3;
		     System.out.println("offset= " + offset);
		     System.out.println("page = "+page);
		    
		     try {
		         Class.forName("com.mysql.jdbc.Driver");
		         Connection conn = DriverManager.getConnection(url, "soonface", "2014");
		         // Statement st = conn.createStatement();
		         PreparedStatement st2 = conn.prepareStatement(sql2);
		         st2.setString(1, "%"+category+"%");
		         st2.setInt(2, offset);
		         ResultSet rs2 = st2.executeQuery();

		         list2 = new ArrayList<>();
		  
		         
		         while (rs2.next()) {
		        
		        	c = new Counseling(); 
		          	c.setNumber(rs2.getString("number"));
		        	c.setTitle(rs2.getString("title"));
		        	c.setHit(rs2.getInt("hit"));
		         n = getCount(rs2.getString("number"));
			       c.setCount(n);
		      
		            list2.add(c);
		         }

		         rs2.close();
		         st2.close();
		         conn.close();
		        
		      } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }

		return list2;
	}

	@Override
	public Counseling get(String number) {
		
		
		  Counseling c = null;
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";	
		String sql = "SELECT * FROM Counseling WHERE number LIKE ? ";
		
	     try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection(url,"soonface", "2014");
	         // Statement st = conn.createStatement();
	         PreparedStatement st = conn.prepareStatement(sql);
	         st.setString(1, number);
	         ResultSet rs = st.executeQuery();
	        
	         if (rs.next()) {
	        
	        	c = new Counseling(); 
	        	c.setNumber(rs.getString("number"));
	        	c.setCategory(rs.getString("category"));// 분류
	        	c.setTitle(rs.getString("title")); //제목
	        	c.setWriter_id(rs.getString("writer_id"));//작성자
	        	c.setDate(rs.getDate("date")); //날짜
	            //m.setPwd(rs.getString("pwd")); //첨부파일
	            c.setContent(rs.getString("content"));//내용
	   
	         }

	         rs.close();
	         st.close();
	         conn.close();

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		
		
		return c;
	}

	
	
	public List<Comment> getComment(String number) {
		
		List<Comment> list = null;
		Comment co=null;
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";	
		String sql2 = "select c.number,comm.content,m.nickname from soonfacedb.Counseling c right outer join soonfacedb.Comment comm on c.number= comm.textnumber\r\n" + 
				" left outer join soonfacedb.Member m on comm.member_id=m.id where c.number=?";
		
	     try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection(url,"soonface", "2014");
	         // Statement st = conn.createStatement();
	         PreparedStatement st2 = conn.prepareStatement(sql2);
	         st2.setString(1, number);
	         ResultSet rs2 = st2.executeQuery();
	        
	         list = new ArrayList<>();
	          while(rs2.next()) {
	        
	        	co = new Comment(); 
	        	co.setNumber(rs2.getInt("number"));
	        	co.setNickname(rs2.getString("nickname"));
	        	co.setContent(rs2.getString("content"));
	        	list.add(co);
	        	
	         }

	         rs2.close();
	         st2.close();
	         conn.close();

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		return list;
	}

	@Override
	public String getNickname(String number) {
		
		String nickname="";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";	
	      String sqlnic="SELECT nickname from soonfacedb.Member where id=?";

	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection(url, "soonface", "2014");
	         // Statement st = conn.createStatement();
	         PreparedStatement stnic = conn.prepareStatement(sqlnic);
	         stnic.setString(1,"happ");
	         ResultSet rsnic = stnic.executeQuery();
		      
	         rsnic.next();
	         
	         nickname = rsnic.getString("nickname");

	         rsnic.close();
	         stnic.close();
	         conn.close();

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		return nickname;
	}

	
	@Override
	public int getCount(String number) {
		

		 int commentCount=0;
		 System.out.println("number = "+number);
		 String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";	
		 String sqlcount="select count(textnumber) count from Counseling c right outer join Comment comm on comm.textnumber= c.number where comm.textnumber=?";

		      try {
		         Class.forName("com.mysql.jdbc.Driver");
		         Connection conn = DriverManager.getConnection(url, "soonface", "2014");
		         // Statement st = conn.createStatement();
		         PreparedStatement stCount = conn.prepareStatement(sqlcount);
		         stCount.setString(1,number);
		      
		         ResultSet rscount = stCount.executeQuery();
			        
		         rscount.next();
		         
		         commentCount = rscount.getInt("count");
		         
		         rscount.close();
		         stCount.close();
		         conn.close();

		      } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }

		      System.out.println(commentCount);
		return commentCount;
	}

	@Override
	public int updateComment(String number, String content, String nickname) {
		
		int result=0;
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
	      String sql="INSERT INTO `soonfacedb`.`Comment` (`number`, `textnumber`, `content`,`date`, `member_id`) VALUES ((select ifnull(max(cast(number as unsigned)),0)+1 from soonfacedb.Comment as b),?,?,(select sysdate()),(select id from soonfacedb.Member where nickname=?))";

	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection(url, "soonface", "2014");
	         // Statement st = conn.createStatement();
	         PreparedStatement st = conn.prepareStatement(sql);
	         st.setString(1, number);
	         st.setString(2, content);
	         st.setString(3, nickname);
	         result = st.executeUpdate();

	         st.close();
	         conn.close();

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }

		
		return result;
	}

	

	@Override
	public int updateHit(String number) {
	
		
		System.out.println(number);
		int result=0;
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
	      String sql="\r\n" + 
	      		"update Counseling set hit = (hit+1) where number=?";

	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection(url, "soonface", "2014");
	         // Statement st = conn.createStatement();
	         PreparedStatement st = conn.prepareStatement(sql);
	         st.setString(1, number);
	      
	         result = st.executeUpdate();

	         st.close();
	         conn.close();

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }

		System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
		return result;
		
	}

	@Override
	public List<Counseling> getList2(String category2, String content) {
	
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";	
		  String sql2 = "select * from Counseling where category LIKE ? && title LIKE ? ";
		   
		     int n=0;
		   List<Counseling> list2 = null;
		   Counseling c = null;
		
		     try {
		         Class.forName("com.mysql.jdbc.Driver");
		         Connection conn = DriverManager.getConnection(url, "soonface", "2014");
		         // Statement st = conn.createStatement();
		         PreparedStatement st2 = conn.prepareStatement(sql2);
		         st2.setString(1,"%"+category2+"%");
		         st2.setString(2, "%"+content+"%");
		         ResultSet rs2 = st2.executeQuery();

		         list2 = new ArrayList<>();
		  
		         
		         while (rs2.next()) {
		        
		        	c = new Counseling(); 
		          	c.setNumber(rs2.getString("number"));
		        	c.setTitle(rs2.getString("title"));
		        	c.setHit(rs2.getInt("hit"));
		            n = getCount(rs2.getString("number"));
			       c.setCount(n);
		      
		            list2.add(c);
		         }

		         rs2.close();
		         st2.close();
		         conn.close();
		        
		      } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }

		return list2;
	}

	@Override
	public int getCount2(String categoryy) {


		 int Count=0;
		
		 String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";	
		 String sqlcount="SELECT COUNT(number) count FROM Counseling where category LIKE ?";

		      try {
		         Class.forName("com.mysql.jdbc.Driver");
		         Connection conn = DriverManager.getConnection(url, "soonface", "2014");
		         // Statement st = conn.createStatement();
		         PreparedStatement stCount = conn.prepareStatement(sqlcount);
		         stCount.setString(1,categoryy);
		      
		         ResultSet rscount = stCount.executeQuery();
			        
		         rscount.next();
		         
		         Count = rscount.getInt("count");
		         
		         rscount.close();
		         stCount.close();
		         conn.close();

		      } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }

		      System.out.println(Count);
		return Count;
		
	
	}

	
	}