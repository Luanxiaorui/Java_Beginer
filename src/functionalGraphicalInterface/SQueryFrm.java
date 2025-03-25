package functionalGraphicalInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import dao.HouseParentDao;
import dao.QueryExistDao;
import dao.StudentDao;

public class SQueryFrm extends JPanel implements ActionListener {

	JLabel l1 = new JLabel("ѧ��");
	JLabel l2 = new JLabel("����");
	JLabel l3 = new JLabel("�Ա�");
	JLabel l4 = new JLabel("��ס¥��");
	JLabel l5 = new JLabel("�������ƺ�");
	JLabel l6 = new JLabel("�绰");

	JTextField tf1 = new JTextField(11);
	JTextField tf2 = new JTextField(11);
	JTextField tf3 = new JTextField(11);
	JTextField tf4 = new JTextField(11);
	JTextField tf5 = new JTextField(11);
	JTextField tf6 = new JTextField(11);

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	JButton jb1 = new JButton("�����ѯ");
	JButton jb2 = new JButton("����");

	JTable t;
	Object[][] row;
	Object[] colum = { "ѧ��", "����", "�Ա�", "��ס¥��", "�������ƺ�", "�绰" };

	SQueryFrm() {

		setLayout(new BorderLayout());
		p1.setBorder(new TitledBorder("ѧ����Ϣ"));
		p1.add(l1);
		p1.add(tf1);
		p1.add(l2);
		p1.add(tf2);
		p1.add(l3);
		p1.add(tf3);
		p1.add(l4);
		p1.add(tf4);
		p1.add(l5);
		p1.add(tf5);
		p1.add(l6);
		p1.add(tf6);

		tf1.addActionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		tf6.addActionListener(this);

		p2.add(jb1);
		p2.add(jb2);

		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH);
		// String mysql = "select *from student";
		// updateTable(mysql);

		setVisible(true);
		setBounds(100, 100, 1500, 800);

	}

	public void delete() {
		tf1.setText(null);
		tf2.setText(null);
		tf3.setText(null);
		tf4.setText(null);
		tf5.setText(null);
		tf6.setText(null);
	}

	public void updateTable(String sql) {
		row = StudentDao.executeQueryArray(sql);
		t = new JTable(row, colum);
		this.removeAll();
		add(new JScrollPane(t), BorderLayout.CENTER);
		t.setEnabled(false);
		delete(); // ����ı���
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH);
		validate();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jb1) {

			String a = "'" + tf3.getText() + "'", // �Ա�
					b = "'" + tf4.getText() + "'";// ����¥��
			if (tf3.getText().equals(""))
				a = "ANY(SELECT ssex from student)";
			if (tf4.getText().equals(""))
				b = "ANY(SELECT sfloor from student)";

			if (tf1.getText().equals("") && tf2.getText().equals("") && tf3.getText().equals("")
					&& tf6.getText().equals("") && tf4.getText().equals("") && tf5.getText().equals(""))
				System.out.println();
			else {
				String mysql;
				// ѧ�Ų�ѯ
				if (!tf1.getText().equals(""))
					mysql = "select*from student where no='" + tf1.getText() + "'";

				// �ֻ��Ų�ѯ
				else if (!tf6.getText().equals(""))
					mysql = "select*from student where sphone like'" + tf6.getText() + "%'" + " and ssex=" + a
							+ " and sfloor=" + b;

				// ���Ҳ�ѯ
				else if (!tf5.getText().equals("")) {
					if (tf2.getText().equals(""))
						mysql = "select*from student where sbed like'" + tf5.getText() + "%'" + " and ssex=" + a
								+ " and sfloor=" + b;
					else
						mysql = "select*from student where sbed like'" + tf5.getText() + "%'" + " and ssex=" + a
								+ " and sfloor=" + b + " and sname like '%" + tf2.getText() + "%'";
					System.out.print(mysql);
				}
				// ���ֲ�ѯ
				else if (!tf2.getText().equals(""))
					mysql = "select*from student where  ssex=" + a + " and sname like '%" + tf2.getText() + "%'"
							+ " and sfloor=" + b;
				else
					mysql = "select*from student where  ssex=" + a + " and sfloor=" + b;

				if (QueryExistDao.QueryExist(mysql))
					updateTable(mysql);
				else {
					JOptionPane.showMessageDialog(null, "ѧ��������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					delete();
				}
			}
		}

		if (e.getSource() == jb2) {
			String mysql = "select*from student";
			updateTable(mysql); // �������
		}

		if (e.getSource() == tf1) {
			if (!tf1.getText().equals("")) {
				if (QueryExistDao.QueryExist("select*from student", tf1.getText(), "no"))
					updateTable("select*from student where no='" + tf1.getText() + "'"); // �������
				else {
					JOptionPane.showMessageDialog(null, "ѧ��������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					delete();
				}
			}
		}

	}
}
