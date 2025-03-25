package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import toolkit.SQLHelper;

public class LoginDao {

	public static int QueryLogin(String uname, String upwd, String identity) {
		String mysql = "select* from " + identity;
		ResultSet rs = SQLHelper.executeQuery(mysql);
        int i=0;
		try {
			while (rs.next()) {
				if (uname.equals(rs.getString("no"))) {
					i=1;
					if (upwd.equals(rs.getString("pwd"))) {
						i=2;
					} 
				}
			}
			SQLHelper.closeConnection();
		} catch (SQLException ee) {
			System.out.println(ee + "wrong2");
		}
		return i;//i=0,用户名错误，i=1,密码错误，i=2，正确。
	}
	public static int UpdatePassword(String no,String password,String identity)
	{
		String mysql= "update "+ identity+"  set pwd='"+password+"'"+"where no='"+no+"'";
		int i=SQLHelper.executeUpdate(mysql);
		SQLHelper.closeConnection();
		return i;
	}
}
