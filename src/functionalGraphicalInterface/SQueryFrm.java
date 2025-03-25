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

	JLabel l1 = new JLabel("学号");
	JLabel l2 = new JLabel("姓名");
	JLabel l3 = new JLabel("性别");
	JLabel l4 = new JLabel("所住楼号");
	JLabel l5 = new JLabel("寝室门牌号");
	JLabel l6 = new JLabel("电话");

	JTextField tf1 = new JTextField(11);
	JTextField tf2 = new JTextField(11);
	JTextField tf3 = new JTextField(11);
	JTextField tf4 = new JTextField(11);
	JTextField tf5 = new JTextField(11);
	JTextField tf6 = new JTextField(11);

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	JButton jb1 = new JButton("点击查询");
	JButton jb2 = new JButton("返回");

	JTable t;
	Object[][] row;
	Object[] colum = { "学号", "姓名", "性别", "所住楼号", "寝室门牌号", "电话" };

	SQueryFrm() {

		setLayout(new BorderLayout());
		p1.setBorder(new TitledBorder("学生信息"));
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
		delete(); // 清空文本框
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH);
		validate();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jb1) {

			String a = "'" + tf3.getText() + "'", // 性别
					b = "'" + tf4.getText() + "'";// 所在楼层
			if (tf3.getText().equals(""))
				a = "ANY(SELECT ssex from student)";
			if (tf4.getText().equals(""))
				b = "ANY(SELECT sfloor from student)";

			if (tf1.getText().equals("") && tf2.getText().equals("") && tf3.getText().equals("")
					&& tf6.getText().equals("") && tf4.getText().equals("") && tf5.getText().equals(""))
				System.out.println();
			else {
				String mysql;
				// 学号查询
				if (!tf1.getText().equals(""))
					mysql = "select*from student where no='" + tf1.getText() + "'";

				// 手机号查询
				else if (!tf6.getText().equals(""))
					mysql = "select*from student where sphone like'" + tf6.getText() + "%'" + " and ssex=" + a
							+ " and sfloor=" + b;

				// 寝室查询
				else if (!tf5.getText().equals("")) {
					if (tf2.getText().equals(""))
						mysql = "select*from student where sbed like'" + tf5.getText() + "%'" + " and ssex=" + a
								+ " and sfloor=" + b;
					else
						mysql = "select*from student where sbed like'" + tf5.getText() + "%'" + " and ssex=" + a
								+ " and sfloor=" + b + " and sname like '%" + tf2.getText() + "%'";
					System.out.print(mysql);
				}
				// 名字查询
				else if (!tf2.getText().equals(""))
					mysql = "select*from student where  ssex=" + a + " and sname like '%" + tf2.getText() + "%'"
							+ " and sfloor=" + b;
				else
					mysql = "select*from student where  ssex=" + a + " and sfloor=" + b;

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
}
