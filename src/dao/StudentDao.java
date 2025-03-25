package dao;

import java.sql.*;

import toolkit.SQLHelper;

public class StudentDao {
	///////////////////////////
	public static Object[][] executeQueryArray(String sql) {
		ResultSet rs = null;
		Object t[][] = null;
		try {
			rs = SQLHelper.executeQuery(sql);
			rs.last();
			t = new Object[rs.getRow()][6];
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				t[i][0] = rs.getObject("no");
				t[i][1] = rs.getObject("sname");
				t[i][2] = rs.getObject("ssex");
				t[i][3] = rs.getObject("sfloor");
				t[i][4] = rs.getObject("sbed");
				t[i][5] = rs.getObject("sphone");
				i++;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return t;
		}
	/////////////////////////////////
	public static int repairInsert(String no, String content, String sbed,String date,String sfloor) {

		String mysql2 = "insert into Repair values('"+no+"','"+sfloor+"','"+sbed+"','"+content+"','"+date+"',' ',null)";
		int k = 0;
		try {
			k = SQLHelper.executeUpdate(mysql2);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		System.out.println(mysql2);
		return k;
	}
	/////////////////////////////
	public static Object[][] executeRepairArray(String sql) {
		ResultSet rs = null;
		Object t[][] = null;
		try {
			rs = SQLHelper.executeQuery(sql);
			rs.last();
			t = new Object[rs.getRow()][5];
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				t[i][0] = rs.getObject("no");
				t[i][1] = rs.getObject(3);
				t[i][2] = rs.getObject(4);
				t[i][3] = rs.getObject(5);
				if(rs.getString("deal").equals(""))
				t[i][4] = "´ýÎ¬ÐÞ";
				else
				t[i][4]="ÒÑÎ¬ÐÞ";
				i++;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return t;
		}

}
