package functionalGraphicalInterface;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import dao.GodDao;
import dao.HouseParentDao;
import dao.QueryExistDao;
import functionalGraphicalInterface.HManageFrm.mouse;
import toolkit.SQLHelper;

public class GodHManageFrm  extends JPanel implements ActionListener{
	
	JLabel l1 = new JLabel("工号");
	JLabel l2 = new JLabel("姓名");
	JLabel l3 = new JLabel("性别");
	JLabel l4 = new JLabel("所管楼号");

	JTextField tf1 = new JTextField(11);
	JTextField tf2 = new JTextField(11);
	JTextField tf3 = new JTextField(11);
	JTextField tf4 = new JTextField(11);


	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	JButton jb1 = new JButton("删除");
	JButton jb2 = new JButton("清空");
	JButton jb3 = new JButton("查询");
	JButton jb4 = new JButton("更新");
	JButton jb5 = new JButton("返回");

	JTable t;
	Object[][] row;
	Object[] colum = { "工号","姓名","性别","所管楼号" };
    
	GodHManageFrm() {
		

		p1.setBorder(new TitledBorder("宿管信息"));
		p1.add(l1);
		p1.add(tf1);
		p1.add(l2);
		p1.add(tf2);
		p1.add(l3);
		p1.add(tf3);
		p1.add(l4);
		p1.add(tf4);

        setLayout(new BorderLayout());
		tf1.addActionListener(this);

		String sql2 = "select*from houseparent";
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
		tf4.setText(data[3]);
		tf1.setEditable(false);
	}

	// 更新面板
	public void updateTable(String sql, boolean flag) {
		row = GodDao.executeQueryArray(sql);
		t = new JTable(row, colum);
		this.removeAll();
		add(new JScrollPane(t), BorderLayout.CENTER);
		t.addMouseListener(new mouse());
		delete();
		tf1.setEditable(flag);
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

	public void delete() {
		tf1.setText(null);
		tf2.setText(null);
		tf3.setText(null);
        tf4.setText(null);
        tf1.setEditable(true);
	}

	public void actionPerformed(ActionEvent e) {
		// 触发删除
				if (e.getSource() == jb1) {
					String mysql = "delete from houseparent where no='" + tf1.getText() + "'";
					if (SQLHelper.executeUpdate(mysql) == 1) {
						JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
						String sql2 = "select*from houseparent";
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
							b="'"+tf4.getText()+"'", // 所管楼号
							mysql;
						if (tf1.getText().equals("")  && tf2.getText().equals("")
							&& tf3.getText().equals("")&& tf4.getText().equals("")) {
						}
					else {
					if (tf3.getText().equals(""))
						a = "ANY(SELECT hsex from houseparent)";
					if (tf4.getText().equals(""))
						b = "ANY(select hfloor from houseparent)";
		
					// 工号查询
					if (!tf1.getText().equals("")) 
						mysql="select*from houseparent where no='" + tf1.getText() + "'";
					else if(tf2.getText().equals(""))
						mysql="select*from houseparent where hsex="+a+" and hfloor="+b;
					else
						mysql="select*from houseparent where hsex="+a+" and hfloor="+b+" and hname like '%"+tf2.getText()+"%'";
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
							|| tf3.getText().equals("") ||tf4.getText().equals("")) 
						JOptionPane.showMessageDialog(null, "信息不完整", "提示", JOptionPane.WARNING_MESSAGE);	
					else {
					if (GodDao.executeUpdate(tf1.getText(),tf2.getText(),tf3.getText(),tf4.getText())==1) {
						JOptionPane.showMessageDialog(null, "更新成功", "提示", JOptionPane.PLAIN_MESSAGE);
						String sql2 = "select*from houseparent";
						SQLHelper.closeConnection();
						updateTable(sql2, true);
					} else {
						JOptionPane.showMessageDialog(null, "更新失败", "提示", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
				
				// //////////////触发查询
				if (e.getSource() == tf1) {
					String mysql = "select *from houseparent where no='" + tf1.getText() + "'";
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

				/////////////////////
				if (e.getSource() == jb5) {
					String sql = "select*from houseparent";
					updateTable(sql, true);
				}
	}

}
