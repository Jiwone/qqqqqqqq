package src.com.secretd.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.com.secretd.web.dao.NoticeDao;
import src.com.secretd.web.entity.NoticeView;

public class JdbcNoticeDao implements NoticeDao{

	public List<NoticeView> getList(int page, String query) {
		List<NoticeView> list = null;
		String sql = "SELECT * FROM NoticeView WHERE title like ? order by regDate DESC limit ?,10";
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		int offset = (page - 1) * 10;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + query + "%");
			st.setInt(2, offset);

			ResultSet rs = st.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				NoticeView n = new NoticeView();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setWriterId(rs.getString("WRITERID"));
				n.setWriterName(rs.getString("writerID"));
				n.setHit(rs.getInt("HIT"));
				n.setContent(rs.getString("CONTENT"));
				n.setRegDate(rs.getDate("REGDATE"));
				n.setCountCmt(rs.getInt("countCmt"));

				list.add(n);
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getCount() {
		int count = 0;
		String sqlCount = "SELECT COUNT(id) as count FROM Notice";

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ??Ό?΄λ²? λ‘λ
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ?°κ²? / ?Έμ¦?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ?€?
			Statement stCount = con.createStatement();

			// κ²°κ³Ό κ°?? Έ?€κΈ?
			ResultSet rsCount = stCount.executeQuery(sqlCount);

			rsCount.next();

			// Model
			count = rsCount.getInt("count");

			// κ²°κ³Ό ?¬?©?κΈ?

			rsCount.close();
			stCount.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	public NoticeView get(String id) {

		// ------------------μΆλ ₯-----------------
		NoticeView n = null;
		String sql = "SELECT * FROM NoticeView WHERE id = ?";
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ??Ό?΄λ²? λ‘λ
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// ?°κ²? / ?Έμ¦?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			// ?€?
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			// κ²°κ³Ό κ°?? Έ?€κΈ?
			ResultSet rs = st.executeQuery();
			// κ²°κ³Ό ?¬?©?κΈ?
			if (rs.next()) {
				n = new NoticeView();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setHit(rs.getInt("HIT"));
				n.setContent(rs.getString("CONTENT"));
				n.setRegDate(rs.getDate("REGDATE"));
				n.setWriterId(rs.getString("WRITERID"));
				n.setWriterName(rs.getString("WRITERNAME"));
				n.setCountCmt(rs.getInt("COUNTCMT"));
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public void edit(String id, String title, String content) {
		String sql = "UPDATE Notice SET title= ?,content = ? WHERE id = ?";
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		// JDBC ??Ό?΄λ²? λ‘λ
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// ?°κ²? / ?Έμ¦?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, id);
			int result = st.executeUpdate();
			if (result == 1)
				System.out.println("?? ?λ£?");
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String title, String content) {
		String sql = "INSERT INTO Notice(id,title, content, writerId) VALUES((select ifnull(max(cast(id as signed integer)),0)+1 from Notice as b),?,?,?)";
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, "robin");// !!!!!λ‘κ·Έ?Έ? ??΄?λ‘? λ°κΎΈκΈ?
			int result = st.executeUpdate();
			if(result==1)
				System.out.println("κΈ??±λ‘? ?λ£?");
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String id) {
		String sql = "DELETE FROM Notice WHERE id = ?";
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		// JDBC ??Ό?΄λ²? λ‘λ
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// ?°κ²? / ?Έμ¦?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			int result = st.executeUpdate();
			if (result == 1)
				System.out.println("?­? ?λ£?");
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
