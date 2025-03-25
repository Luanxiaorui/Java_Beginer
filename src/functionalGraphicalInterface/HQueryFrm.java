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

	JLabel l1 = new JLabel("学号");
	JLabel l2 = new JLabel("姓名");
	JLabel l3 = new JLabel("性别");
	JLabel l4 = new JLabel("学院");
	JLabel l5 = new JLabel("专业");
	JLabel l6 = new JLabel("寝室门牌号");
	JLabel l7 = new JLabel("电话");
	JLabel l8 = new JLabel("年级");

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

	JButton jb1 = new JButton("点击查询");
	JButton jb2 = new JButton("返回");

	JTable t;
	Object[][] row;
	Object[] colum = { "学号", "姓名", "性别", "学院", "专业", "寝室门牌号", "电话", "年级" };

	HQueryFrm() {

		cb1.addItem("请选择学院");
		cb1.addItem("计算机科学与技术学院");
		cb1.addItem("数理学院");
		cb1.addItem("电气工程学院");
		cb1.addItemListener(this);

		cb2.addItem("请选择专业");

		p1.setBorder(new TitledBorder("学生信息"));
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

			String a = "'" + tf3.getText() + "'", // 性别
					b, // 学院
					c = "'" + tf8.getText() + "'", // 年级
					d;// 专业

			if (tf3.getText().equals(""))
				a = "ANY(SELECT ssex from student)";
			if (tf8.getText().equals(""))
				c = "ANY(select sgrade from student)";
			if (cb1.getSelectedItem().toString().equals("请选择学院"))
				b = "ANY(select sfaculty from student)";
			else
				b = "'" + cb1.getSelectedItem().toString() + "'";
			if (cb2.getSelectedItem().toString().equals("请选择专业") || cb2.getSelectedItem().toString().equals("请先选择学院"))
				d = "ANY(select smajor from student)";
			else
				d = "'" + cb2.getSelectedItem().toString() + "'";

			if (tf1.getText().equals("") && tf2.getText().equals("") && tf3.getText().equals("")
					&& tf6.getText().equals("") && cb1.getSelectedItem().toString().equals("请选择院系")
					&& tf7.getText().equals("") && tf8.getText().equals("")) {
			} else {
				String mysql;
				// 学号查询
				if (!tf1.getText().equals(""))
					mysql = "select*from student where no='" + tf1.getText() + "'";

				// 手机号查询
				else if (!tf7.getText().equals("")) {

					if (tf2.getText().equals(""))
						mysql = "select*from student where sphone like'" + tf7.getText() + "%'" + " and ssex=" + a
								+ " and sgrade=" + c + " and sfaculty=" + b + "and smajor=" + d;
					else
						mysql = "select*from student where sphone like'" + tf7.getText() + "%'" + " and ssex=" + a
								+ " and sgrade=" + c + " and sfaculty=" + b + " and smajor=" + d + " and sname like '%"
								+ tf2.getText() + "%'";
				}

				// 寝室查询
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
				// 名字查询
				else if (!tf2.getText().equals(""))
					mysql = "select*from student where  ssex=" + a + " and sgrade=" + c + " and sfaculty=" + b
							+ " and smajor=" + d + " and sname like '%" + tf2.getText() + "%'";
				else
					mysql = "select*from student where  ssex=" + a + " and sgrade=" + c + " and sfaculty=" + b
							+ " and smajor=" + d;

				if (QueryExistDao.QueryExist(mysql))
					updateTable(mysql);
				else {
					JOptionPane.showMessageDialog(null, "学生不存在", "提示", JOptionPane.INFORMATION_MESSAGE);
					delete();
				}
			}
		}
		if (e.getSource() == jb2) {
			String mysql = "select*from student";
			updateTable(mysql); // 更新面板
		}
		if (e.getSource() == tf1) {
			if (!tf1.getText().equals("")) {
				if (QueryExistDao.QueryExist("select*from student", tf1.getText(), "no"))
					updateTable("select*from student where no='" + tf1.getText() + "'"); // 更新面板
				else {
					JOptionPane.showMessageDialog(null, "学生不存在", "提示", JOptionPane.INFORMATION_MESSAGE);
					delete();
				}
			}
		}
	}

	public void delete() {
		tf1.setText(null);
		tf2.setText(null);
		tf3.setText(null);
		cb1.setSelectedItem("请选择学院");
		cb2.removeAllItems();
		cb2.addItem("请选择专业");
		cb2.setSelectedItem("请选择专业");
		tf6.setText(null);
		tf7.setText(null);
		tf8.setText(null);
	}

	public void itemStateChanged(ItemEvent ee) {
		if (ee.getSource() == cb1) {
			if (cb1.getSelectedItem().toString().equals("计算机科学与技术学院")) {
				cb2.removeAllItems();
				cb2.addItem("请选择专业");
				cb2.addItem("软件工程");
				cb2.addItem("信息安全");
				cb2.addItem("计算机科学与技术");
				cb2.addItem("网络工程");
				cb2.setSelectedItem("请选择专业");
			}
			if (cb1.getSelectedItem().toString().equals("数理学院")) {
				cb2.removeAllItems();
				cb2.addItem("请选择专业");
				cb2.addItem("信息与计算科学");
				cb2.addItem("应用物理学");
				cb2.setSelectedItem("请选择专业");
			}
			if (cb1.getSelectedItem().toString().equals("电气工程学院")) {
				cb2.removeAllItems();
				cb2.addItem("请选择专业");
				cb2.addItem("电气工程及其自动化");
				cb2.setSelectedItem("请选择专业");
			}
		}
	}
}
