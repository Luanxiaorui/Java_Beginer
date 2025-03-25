package functionalGraphicalInterface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import dao.HouseParentDao;
import dao.QueryExistDao;
import toolkit.SQLHelper;

import java.awt.event.*;

public class HManageFrm extends JPanel implements ActionListener, ItemListener {

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

	JComboBox<String> cb1 = new JComboBox<String>();
	JComboBox<String> cb2 = new JComboBox<String>();

	JTextField tf6 = new JTextField(11);
	JTextField tf7 = new JTextField(11);
	JTextField tf8 = new JTextField(11);

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	JButton jb1 = new JButton("删除");
	JButton jb2 = new JButton("清空");
	JButton jb3 = new JButton("查询");
	JButton jb4 = new JButton("更新");
	JButton jb5 = new JButton("返回");

	JTable t;
	Object[][] row;
	Object[] colum = { "学号", "姓名", "性别", "学院", "专业", "寝室门牌号", "电话", "年级" };
    
	String hfloor;
	
	HManageFrm(String title) {

		this.hfloor=title;
		
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
        setLayout(new BorderLayout());
		tf1.addActionListener(this);

		String sql2 = "select*from student where sfloor='" + hfloor + "'";
		updateTable(sql2, true);

		p2.add(jb1);
		p2.add(jb3);
		p2.add(jb2);
		p2.add(jb4);
		p2.add(jb5);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);

		
		setVisible(true);
		setBounds(300, 300, 1500, 500);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// 选中变色
	public void tableRowSelected() {
		t.setSelectionBackground(Color.BLUE);
		t.setSelectionForeground(Color.white);
		int row = t.getSelectedRow();
		int colCount = t.getColumnCount();

		String[] data = new String[colCount];
		System.out.println(row + "," + colCount);
		for (int i = 0; i < colCount; i++)
			data[i] = t.getModel().getValueAt(row, i).toString();

		tf1.setText(data[0]);
		tf2.setText(data[1]);
		tf3.setText(data[2]);
		cb1.setSelectedItem(data[3]);
		cb2.setSelectedItem(data[4]);
		tf6.setText(data[5]);
		tf7.setText(data[6]);
		tf8.setText(data[7]);
		tf1.setEditable(false);
	}

	// 更新面板
	public void updateTable(String sql, boolean flag) {
		row = HouseParentDao.executeQueryArray(sql);
		t = new JTable(row, colum);
		this.removeAll();
		add(new JScrollPane(t), BorderLayout.CENTER);
		t.addMouseListener(new mouse());
		delete();
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH);
		validate();
	}

	// 点击事件触发
	class mouse extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == t)
				tableRowSelected();
		}
	}

	// 按钮ActionEvent事件触发处理
	public void actionPerformed(ActionEvent e) {
		
		// 触发删除
		if (e.getSource() == jb1) {
			String mysql = "delete from student where no='" + tf1.getText() + "'";
			if (SQLHelper.executeUpdate(mysql) == 1) {
				JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
				String sql2 = "select*from student where sfloor='" + hfloor + "'";
				SQLHelper.closeConnection();
				updateTable(sql2, true);
			} else {
				JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		// 触发清除文本框
		if (e.getSource() == jb2) {
			delete();
		}

		// 触发查询
		if (e.getSource() == jb3) {
			
				String a = "'" + tf3.getText() + "'", // 性别
					b, // 学院
					c = "'" + tf8.getText() + "'", // 年级
					d, f = "'" + hfloor + "'",
					mysql;// 专业
				if (tf1.getText().equals("")  && tf2.getText().equals("")
					&& tf3.getText().equals("")&& tf6.getText().equals("")
					&& cb1.getSelectedItem().toString().equals("请选择院系")&& tf7.getText().equals("")
					&& tf8.getText().equals("")) {
				}
			else {
			if (tf3.getText().equals(""))
				a = "ANY(SELECT ssex from student)";
			if (tf8.getText().equals(""))
				c = "ANY(select sgrade from student)";
			if (cb1.getSelectedItem().toString().equals("请选择学院"))
				b = "ANY(select sfaculty from student)";
			else
				b = "'" + cb1.getSelectedItem().toString() + "'";
			if (cb2.getSelectedItem().toString().equals("请选择专业")||cb2.getSelectedItem().toString().equals("请先选择学院"))
				d = "ANY(select smajor from student)";
			else
				d = "'" + cb2.getSelectedItem().toString() + "'";
			
			// 学号查询
			if (!tf1.getText().equals("")) 
				mysql="select*from student where no='" + tf1.getText() + "'";
			
			// 手机号查询
			else if (!tf7.getText().equals("")) {
				if (tf2.getText().equals(""))
					mysql= "select*from student where sphone like'" + tf7.getText() + "%'" + " and ssex=" + a
							+ " and sgrade=" + c + " and sfaculty=" + b + "and smajor=" + d;
				else
				mysql= "select*from student where sphone like'" + tf7.getText() + "%'" + " and ssex=" + a
							+ " and sgrade=" + c + " and sfaculty=" + b + " and smajor=" + d + " and sname like '%"
							+ tf2.getText() + "%'";
			}

			// 寝室查询
			else if (!tf6.getText().equals("")) {
				if (tf2.getText().equals(""))
					mysql = "select*from student where sbed like'" + tf6.getText() + "%'" + " and ssex=" + a
							+ " and sgrade=" + c + " and sfaculty=" + b + "and smajor=" + d + " and sfloor=" + f;
				else
					mysql = "select*from student where sbed like'" + tf6.getText() + "%'" + " and ssex=" + a
							+ " and sgrade=" + c + " and sfaculty=" + b + " and smajor=" + d + " and sname like '%"
							+ tf2.getText() + "%'" + " and sfloor=" + f;
			}
			
			// 名字查询
			else if (!tf2.getText().equals("")) 
				mysql = "select*from student where  ssex=" + a + " and sgrade=" + c + " and sfaculty=" + b
						+ " and smajor=" + d + " and sname like '%" + tf2.getText() + "%'" + " and sfloor=" + f;
			//剩余情况
			 else 
				 mysql = "select*from student where  ssex=" + a + " and sgrade=" + c + " and sfaculty=" + b
						+ " and smajor=" + d + " and sfloor=" + f;
			//执行
				if (QueryExistDao.QueryExist(mysql))
					updateTable(mysql, false);
				else {
					JOptionPane.showMessageDialog(null, "不存在", "提示", JOptionPane.PLAIN_MESSAGE);
					delete();
				}
			}
		}
		
		// 触发更新
		if (e.getSource() == jb4) {
			if (tf1.getText().equals("")  || tf2.getText().equals("")
					|| tf3.getText().equals("") || tf6.getText().equals("")
					|| cb1.getSelectedItem().toString().equals("请选择院系")|| tf7.getText().equals("")
					|| cb2.getSelectedItem().toString().equals("请选择专业")|| tf6.getText().equals("")) 
				JOptionPane.showMessageDialog(null, "信息不完整", "提示", JOptionPane.WARNING_MESSAGE);
			else if(tf7.getText().trim().length()!=11) {
				JOptionPane.showMessageDialog(null, "电话号码格式不对", "提示", JOptionPane.WARNING_MESSAGE);
			}		
			else {
			if (HouseParentDao.studentUpdate(tf1.getText(), tf2.getText(), tf3.getText(), tf7.getText(),
					cb1.getSelectedItem().toString(), cb2.getSelectedItem().toString(), tf6.getText(),
					tf8.getText()) == 1) {
				JOptionPane.showMessageDialog(null, "更新成功", "提示", JOptionPane.PLAIN_MESSAGE);
				String sql2 = "select*from student where sfloor='" + hfloor + "'";
				SQLHelper.closeConnection();
				updateTable(sql2, true);
			} else {
				JOptionPane.showMessageDialog(null, "更新失败", "提示", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
		// 触发查询
		if (e.getSource() == tf1) {
			String mysql = "select *from student where no='" + tf1.getText() + "'";
			if (!QueryExistDao.QueryExist(mysql, tf1.getText(), "no")) {
				JOptionPane.showMessageDialog(null, "不存在", "提示", JOptionPane.PLAIN_MESSAGE);
				delete();
			} else {
				String txt = tf1.getText().trim();
				for (int i = 0; i < row.length; i++) {
					if (row[i][0].equals(txt)) {
						t.setRowSelectionInterval(i, i);
						tableRowSelected();
						System.out.println("the selected row:" + i);
					}
				}
			}
		}

		
		if (e.getSource() == jb5) {
			String sql = "select*from student where sfloor='" + hfloor + "'";
			updateTable(sql, true);
		}
	}

	public void delete() {
		tf1.setText(null);
		tf2.setText(null);
		tf3.setText(null);
		cb1.setSelectedItem("请选择学院");
		cb2.removeAllItems();
		cb2.addItem("请先选择学院");
		cb2.setSelectedItem("请先选择学院");
		tf6.setText(null);
		tf7.setText(null);
		tf8.setText(null);
	}

	public void itemStateChanged(ItemEvent ee) {
		// TODO 自动生成的方法存根
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
