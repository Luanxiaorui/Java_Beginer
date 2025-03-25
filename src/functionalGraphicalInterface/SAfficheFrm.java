package functionalGraphicalInterface;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import dao.AfficheDao;
import subjectObject.Affiche;

public class SAfficheFrm extends JPanel {

	JLabel l1 = new JLabel("公告");
	JTextArea text = new JTextArea();
//	JButton jb1=new JButton("我已知晓，确定");
	String sfloor;

	SAfficheFrm(String sfloor) {
		setLayout(new BorderLayout());
		this.sfloor = sfloor;
		// add(jb1,BorderLayout.SOUTH);
		// add(text,BorderLayout.CENTER);
		// (l1,BorderLayout.NORTH);

		updateTextArea();
		setVisible(true);
		setBounds(500, 500, 1000, 500);
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void updateTextArea() {

		ArrayList<Affiche> aff = AfficheDao.studentQueryAll(sfloor);
		// this.removeAll();
		int k = 0;
		while (k < aff.size()) {
			Affiche af = aff.get(k);
			// String str=af.getinformation();
			text.append("\n\n\n/////////////////////////////////\n\n\n");
			text.append("    公告编号:" + af.getid() + "\n");
			text.append("    公告内容:\n     " + af.getinformation() + "\n");
			text.append("\n\n\t" + af.gethfloor() + "\n");
			text.append("\t" + "  发布日期:" + af.getsin());
			k++;
		} //
		// add(jb1,BorderLayout.SOUTH);
		add(text, BorderLayout.CENTER);
		add(l1, BorderLayout.NORTH);
		l1.setFont(new Font("黑体", Font.PLAIN, 36));
		text.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		text.setEditable(false);

	}

}
