package dao;

import java.sql.ResultSet;

import toolkit.SQLHelper;

public class GodDao {

	public static Object[][] executeQueryArray(String sql) {
		ResultSet rs = null;
		Object t[][] = null;
		try {
			rs = SQLHelper.executeQuery(sql);//得到要查询的内容
			rs.last();
			t = new Object[rs.getRow()][4];//根据需要和结果集设置二维数组
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				t[i][0] = rs.getObject("no");//得到学号，存入数组
				t[i][1] = rs.getObject("hname");//得到姓名，存入数组
				t[i][2] = rs.getObject("hsex");
				t[i][3] = rs.getObject("hfloor");
				i++;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return t;//返回数组，以供使用
	}

	public static int executeUpdate(String text, String text2, String text3, String text4) {

		String mysql = "update houseparent set hname='"+text2+"', hsex='"+text3+"', hfloor='"+text4+"' where no='"+text+"'";
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

	public static int executeInsert(String text, String text2, String text3, String text4) {
	    int k=0;
		String mysql="insert into houseparent values('"+text+"','"+text2+"','"+text3+"','"+text4+"','123456')";
		k=SQLHelper.executeUpdate(mysql);
		return k;
	}

}
