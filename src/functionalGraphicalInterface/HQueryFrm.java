package functionalGraphicalInterface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import dao.HouseParentDao;
import dao.QueryExistDao;
import functionalGraphicalInterface.HManageFrm.mouse;
import toolkit.SQLHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HQueryFrm extends JPanel implements ActionListener, ItemListener {

	JLabel l1 = new JLabel("ѧ��");
	JLabel l2 = new JLabel("����");
	JLabel l3 = new JLabel("�Ա�");
	JLabel l4 = new JLabel("ѧԺ");
	JLabel l5 = new JLabel("רҵ");
	JLabel l6 = new JLabel("�������ƺ�");
	JLabel l7 = new JLabel("�绰");
	JLabel l8 = new JLabel("�꼶");

	JTextField tf1 = new JTextField(11);
	JTextField tf2 = new JTextField(11);
	JTextField tf3 = new JTextField(11);

	JComboBox cb1 = new JComboBox<String>();
	JComboBox cb2 = new JComboBox<String>();
	// JTextField tf4 = new JTextField(18);
	// JTextField tf5 = new JTextField(18);
	JTextField tf6 = new JTextField(11);
	JTextField tf7 = new JTextField(11);
	JTextField tf8 = new JTextField(11);

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	JButton jb1 = new JButton("�����ѯ");
	JButton jb2 = new JButton("����");

	JTable t;
	Object[][] row;
	Object[] colum = { "ѧ��", "����", "�Ա�", "ѧԺ", "רҵ", "�������ƺ�", "�绰", "�꼶" };

	HQueryFrm() {

		cb1.addItem("��ѡ��ѧԺ");
		cb1.addItem("�������ѧ�뼼��ѧԺ");
		cb1.addItem("����ѧԺ");
		cb1.addItem("��������ѧԺ");
		cb1.addItemListener(this);

		cb2.addItem("��ѡ��רҵ");

		p1.setBorder(new TitledBorder("ѧ����Ϣ"));
		p1.add(l1);
		p1.add(tf1);
		p1.add(l2);
		p1.add(tf2);
		p1.add(l3);
		p1.add(tf3);
		p1.add(l4);
		p1.add(cb1);
		p1.add(l5);
		p1.add(cb2);
		p1.add(l6);
		p1.add(tf6);
		p1.add(l7);
		p1.add(tf7);
		p1.add(l8);
		p1.add(tf8);

		tf1.addActionListener(this);
		tf7.addActionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);

		p2.add(jb1);
		p2.add(jb2);

		setLayout(new BorderLayout());
		String mysql = "select *from student";
		updateTable(mysql);

		
		setVisible(true);
		setBounds(100, 100, 1500, 800);

	}

	public void updateTable(String sql) {
		row = HouseParentDao.executeQueryArray(sql);
		t = new JTable(row, colum);
		this.removeAll();
		add(new JScrollPane(t), BorderLayout.CENTER);
		t.setEnabled(false);
		delete();
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH);
		validate();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jb1) {

			String a = "'" + tf3.getText() + "'", // �Ա�
					b, // ѧԺ
					c = "'" + tf8.getText() + "'", // �꼶
					d;// רҵ

			if (tf3.getText().equals(""))
				a = "ANY(SELECT ssex from student)";
			if (tf8.getText().equals(""))
				c = "ANY(select sgrade from student)";
			if (cb1.getSelectedItem().toString().equals("��ѡ��ѧԺ"))
				b = "ANY(select sfaculty from student)";
			else
				b = "'" + cb1.getSelectedItem().toString() + "'";
			if (cb2.getSelectedItem().toString().equals("��ѡ��רҵ") || cb2.getSelectedItem().toString().equals("����ѡ��ѧԺ"))
				d = "ANY(select smajor from student)";
			else
				d = "'" + cb2.getSelectedItem().toString() + "'";

			if (tf1.getText().equals("") && tf2.getText().equals("") && tf3.getText().equals("")
					&& tf6.getText().equals("") && cb1.getSelectedItem().toString().equals("��ѡ��Ժϵ")
					&& tf7.getText().equals("") && tf8.getText().equals("")) {
			} else {
				String mysql;
				// ѧ�Ų�ѯ
				if (!tf1.getText().equals(""))
					mysql = "select*from student where no='" + tf1.getText() + "'";

				// �ֻ��Ų�ѯ
				else if (!tf7.getText().equals("")) {

					if (tf2.getText().equals(""))
						mysql = "select*from student where sphone like'" + tf7.getText() + "%'" + " and ssex=" + a
								+ " and sgrade=" + c + " and sfaculty=" + b + "and smajor=" + d;
					else
						mysql = "select*from student where sphone like'" + tf7.getText() + "%'" + " and ssex=" + a
								+ " and sgrade=" + c + " and sfaculty=" + b + " and smajor=" + d + " and sname like '%"
								+ tf2.getText() + "%'";
				}

				// ���Ҳ�ѯ
				else if (!tf6.getText().equals("")) {
					if (tf2.getText().equals(""))
						mysql = "select*from student where sbed like'" + tf6.getText() + "%'" + " and ssex=" + a
								+ " and sgrade=" + c + " and sfaculty=" + b + "and smajor=" + d;
					else
						mysql = "select*from student where sbed like'" + tf6.getText() + "%'" + " and ssex=" + a
								+ " and sgrade=" + c + " and sfaculty=" + b + " and smajor=" + d + " and sname like '%"
								+ tf2.getText() + "%'";
					System.out.print(mysql);
				}
				// ���ֲ�ѯ
				else if (!tf2.getText().equals(""))
					mysql = "select*from student where  ssex=" + a + " and sgrade=" + c + " and sfaculty=" + b
							+ " and smajor=" + d + " and sname like '%" + tf2.getText() + "%'";
				else
					mysql = "select*from student where  ssex=" + a + " and sgrade=" + c + " and sfaculty=" + b
							+ " and smajor=" + d;

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

	public void delete() {
		tf1.setText(null);
		tf2.setText(null);
		tf3.setText(null);
		cb1.setSelectedItem("��ѡ��ѧԺ");
		cb2.removeAllItems();
		cb2.addItem("��ѡ��רҵ");
		cb2.setSelectedItem("��ѡ��רҵ");
		tf6.setText(null);
		tf7.setText(null);
		tf8.setText(null);
	}

	public void itemStateChanged(ItemEvent ee) {
		if (ee.getSource() == cb1) {
			if (cb1.getSelectedItem().toString().equals("�������ѧ�뼼��ѧԺ")) {
				cb2.removeAllItems();
				cb2.addItem("��ѡ��רҵ");
				cb2.addItem("�������");
				cb2.addItem("��Ϣ��ȫ");
				cb2.addItem("�������ѧ�뼼��");
				cb2.addItem("���繤��");
				cb2.setSelectedItem("��ѡ��רҵ");
			}
			if (cb1.getSelectedItem().toString().equals("����ѧԺ")) {
				cb2.removeAllItems();
				cb2.addItem("��ѡ��רҵ");
				cb2.addItem("��Ϣ������ѧ");
				cb2.addItem("Ӧ������ѧ");
				cb2.setSelectedItem("��ѡ��רҵ");
			}
			if (cb1.getSelectedItem().toString().equals("��������ѧԺ")) {
				cb2.removeAllItems();
				cb2.addItem("��ѡ��רҵ");
				cb2.addItem("�������̼����Զ���");
				cb2.setSelectedItem("��ѡ��רҵ");
			}
		}
	}
}
