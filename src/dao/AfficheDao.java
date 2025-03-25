package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import subjectObject.Affiche;
import toolkit.SQLHelper;


public class AfficheDao {

	public static ArrayList<Affiche> studentQueryAll(String floor){
		String mysql  = "SELECT * FROM affiche where hfloor='"+floor+"'";
		ArrayList<Affiche> affList=new ArrayList<Affiche>();
		try {
			ResultSet rs = SQLHelper.executeQuery(mysql);
			while (rs.next()) {
				Affiche aff=new Affiche();
			    aff.setId(rs.getInt(5));
			    aff.sethfloor(rs.getString(2));
			    aff.setinformation(rs.getString(4));
			    aff.setno(rs.getString(1));
			    aff.setsin(rs.getString(3));
				affList.add(aff);
			}
			SQLHelper.closeConnection();

		} catch (SQLException ee) {
			System.out.println(ee);
		}
		return affList;		
	}
	
}
