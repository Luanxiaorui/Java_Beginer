package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import toolkit.SQLHelper;

public class QueryExistDao {
	
	public static boolean QueryExist(String sql){
		ResultSet rs = SQLHelper.executeQuery(sql);
		boolean flag=true;
		try{
			rs.last();
		if(rs.getRow()==0)
	        flag=false;
		}catch(SQLException e) {
			System.out.println(e);
		}
		SQLHelper.closeConnection();
	return flag;
}
	public static boolean QueryExist(String sql, String query, String queryin) {
		boolean flag = false;
		ResultSet rs = SQLHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				if (query.equals(rs.getString(queryin)))
					flag = true;
			}
			SQLHelper.closeConnection();
		} catch (SQLException we) {
			System.out.println(we);
		}
		return flag;
	}
}
