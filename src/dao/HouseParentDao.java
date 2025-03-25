package dao;

import java.sql.*;

import toolkit.SQLHelper;

//import org.jdom2.*;

public class HouseParentDao {


//////////////////////////
	public static int studentUpdate(String no, String name, String sex, String sphone, String sfaculty, String major,
			String sbed,String sgrade) {

		String mysql = "update student set sname='" + name + "',sphone='" + sphone + "',ssex='" + sex + "',"
				+ "sfaculty='" + sfaculty + "'," + "smajor='" + major + "'," + "sbed='" + sbed + "',sgrade='" +sgrade+"'"+ "where no='" + no
				+ "'";

		int k = 0;
		try {
			k = SQLHelper.executeUpdate(mysql);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		System.out.println(mysql);
		return k;
	}
////////////////
	public static Object[][] executeQueryArray(String sql) {
		ResultSet rs = null;
		Object t[][] = null;
		try {
			rs = SQLHelper.executeQuery(sql);//得到要查询的内容
			rs.last();
			t = new Object[rs.getRow()][8];//根据需要和结果集设置二维数组
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				t[i][0] = rs.getObject("no");//得到学号，存入数组
				t[i][1] = rs.getObject("sname");//得到姓名，存入数组
				t[i][2] = rs.getObject("ssex");
				t[i][3] = rs.getObject("sfaculty");
				t[i][4] = rs.getObject("smajor");
				t[i][5] = rs.getObject("sbed");
				t[i][6] = rs.getObject("sphone");
				t[i][7]=rs.getObject("sgrade");
				i++;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return t;//返回数组，以供使用
		}
///////////////////////////
	public static Object[][] executeRepairArray(String sql) {
		ResultSet rs = null;
		Object t[][] = null;
		try {
			rs = SQLHelper.executeQuery(sql);//得到要查询的内容
			rs.last();
			t = new Object[rs.getRow()][7];//根据需要和结果集设置二维数组
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				t[i][0]=rs.getObject("id");
				t[i][1] = rs.getObject("no");
				t[i][2] = rs.getObject("sfloor");
				t[i][3] = rs.getObject("sbed");
				t[i][4] = rs.getObject("content");
				t[i][5] = rs.getObject("report");
				t[i][6] = rs.getObject("deal");
				i++;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return t;//返回数组，以供使用
	}
	
	////////////
	public static Object[][] executeLateArray(String sql) {
		
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
				t[i][1] = rs.getObject(3);
				t[i][2] = rs.getObject(8);
				t[i][3] = rs.getObject(4);
				t[i][4] = rs.getObject(5);
				t[i][5]=rs.getObject(6);
				i++;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return t;
}
///////////////////
	public static Object[][] executeVisitorArray(String sql) {
		
		ResultSet rs = null;
		Object t[][] = null;
		try {
			rs = SQLHelper.executeQuery(sql);
			rs.last();
			t = new Object[rs.getRow()][8];
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				t[i][0]=rs.getObject(9);
				t[i][1] = rs.getObject(1);
				t[i][2] = rs.getObject(4);
				t[i][3] = rs.getObject(2);
				t[i][4] = rs.getObject(5);
				t[i][5] = rs.getObject(6);
				t[i][6]=rs.getObject(7);
				t[i][7]=rs.getObject(8);
				i++;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return t;
}
	
}
