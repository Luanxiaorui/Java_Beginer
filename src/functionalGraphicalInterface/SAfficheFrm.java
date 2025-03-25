package functionalGraphicalInterface;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import dao.AfficheDao;
import subjectObject.Affiche;

public class SAfficheFrm extends JPanel {

	JLabel l1 = new JLabel("����");
	JTextArea text = new JTextArea();
//	JButton jb1=new JButton("����֪����ȷ��");
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
			text.append("    ������:" + af.getid() + "\n");
			text.append("    ��������:\n     " + af.getinformation() + "\n");
			text.append("\n\n\t" + af.gethfloor() + "\n");
			text.append("\t" + "  ��������:" + af.getsin());
			k++;
		} //
		// add(jb1,BorderLayout.SOUTH);
		add(text, BorderLayout.CENTER);
		add(l1, BorderLayout.NORTH);
		l1.setFont(new Font("����", Font.PLAIN, 36));
		text.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		text.setEditable(false);

	}

}
