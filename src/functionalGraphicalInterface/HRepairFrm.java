package functionalGraphicalInterface;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import dao.HouseParentDao;
import dao.QueryExistDao;
import toolkit.SQLHelper;


public class HRepairFrm extends JPanel implements ActionListener{



	JLabel l1 =  new JLabel("报修人学号");
	JLabel l4 =  new JLabel("维修日期");
	JLabel l3 =  new JLabel("寝室门牌号");
	JLabel l2 =  new JLabel("报修日期");
	JLabel l5=   new JLabel("报修编号");
	
	JTextField tf1 = new JTextField(10);
	JTextField tf2 = new JTextField(10);
	JTextField tf3 = new JTextField(10);
	JTextField tf4 = new JTextField(10);
	JTextField tf5 = new JTextField(10);

	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	//JButton jb1 = new JButton("删除");
	JButton jb2 = new JButton("清空");
	JButton jb3 = new JButton("查询");
	JButton jb4 = new JButton("更新");
	JButton jb5 = new JButton("返回");

	JTable t;
	Object[][] row;
	Object[] colum = { "报修编号","报修人学号", "所住楼号", "寝室门牌号", "报修内容", "报修时间", "维修时间" };
	
	Calendar calendar = Calendar.getInstance();

    String hfloor;
	
	HRepairFrm(String hfloor) {

		this.hfloor=hfloor;
		
		p1.setBorder(new TitledBorder("报修信息"));
		
		p1.add(l5);
		p1.add(tf5);
		p1.add(l1);
		p1.add(tf1);
		p1.add(l2);
		p1.add(tf2);
		p1.add(l3);
		p1.add(tf3);
		p1.add(l4);
		p1.add(tf4);

		tf1.addActionListener(this);

		setLayout(new BorderLayout());
		String sql2 = "select*from Repair where sfloor='" + hfloor + "'";
		updateTable(sql2);

		p2.add(jb3);
		p2.add(jb2);
		p2.add(jb4);
		p2.add(jb5);
		
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);

		setVisible(true);
		setBounds(500, 300, 1200, 500);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void updateTable(String sql) {
		row = HouseParentDao.executeRepairArray(sql);
		t = new JTable(row, colum);
		this.removeAll();
		add(new JScrollPane(t), BorderLayout.CENTER);
		t.addMouseListener(new mouse());
		tf1.setText(null);tf2.setText(null);
		tf3.setText(null);tf4.setText(null);
		tf5.setText(null);
		tf5.setEditable(true);
		tf1.setEditable(true);
		tf2.setEditable(true);
		tf3.setEditable(true);
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH);
		validate();
	}
	
	public void tableRowSelected() {
		t.setSelectionBackground(Color.BLUE);
		t.setSelectionForeground(Color.white);
		int row = t.getSelectedRow();
		int colCount = t.getColumnCount();

		String[] data = new String[colCount];
		System.out.println(row + "," + colCount);
		for (int i = 0; i < colCount; i++) 	
		data[i] = t.getModel().getValueAt(row, i).toString();
		tf1.setText(data[1]);
		tf2.setText(data[5]);
		tf3.setText(data[3]);
		tf4.setText(data[6]);
		tf5.setText(data[0]);
		tf1.setEditable(false);
		tf2.setEditable(false);
		tf3.setEditable(false);
		tf5.setEditable(false);
	}
	
	class mouse extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == t)
				tableRowSelected();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//清空
		if(e.getSource()==jb2) {
			tf1.setText(null);
			tf2.setText(null);
			tf3.setText(null);
			tf4.setText(null);
			tf1.setEditable(true);
			tf2.setEditable(true);
			tf3.setEditable(true);
			tf5.setEditable(false);
		}
		//查询
		if(e.getSource()==jb3) {
			String mysql = null,
					c=" and sfloor='"+hfloor+"'";
			    
			if(!tf5.getText().equals("")) 
				mysql="select*from repair where id='"+tf5.getText()+"'";
			else if (tf1.getText().equals("")) {
				if (tf2.getText().equals("")) {
					if (tf3.getText().equals("")) {
                         
					} else {
                         mysql="select*from repair where sbed='"+tf3.getText()+"'"+c;
					}
				} else {
					if (tf3.getText().equals("")) {
						  mysql="select*from repair where report='"+tf2.getText()+"'"+c;
					} else {
						 mysql="select*from repair where report='"+tf2.getText()+"' and sbed='"+tf3.getText()+"'"+c;
					}
				}
			} else {

				if (tf2.getText().equals("")) {
					if (tf3.getText().equals("")) {
						  mysql="select*from repair where no='"+tf1.getText()+"'"+c;  
					} else {
						 mysql="select*from repair where no='"+tf1.getText()+"' and sbed='"+tf3.getText()+"'"+c;
					}
				} else {
					if (tf3.getText().equals("")) {
						  mysql="select*from repair where report='"+tf2.getText()+"' and no='"+tf1.getText()+"'"+c;
					} else {
						 mysql="select*from repair where report='"+tf2.getText()+"' and sbed='"+tf3.getText()+"' and no='"+tf1.getText()+"'"+c;
					}
				}
			}
			if(QueryExistDao.QueryExist(mysql)) {
				updateTable(mysql);
			}
			else {
				JOptionPane.showMessageDialog(null, "不存在", "提示", JOptionPane.PLAIN_MESSAGE);
				tf1.setText(null);
				tf2.setText(null);
				tf3.setText(null);
				tf4.setText(null);
			}
		}
		//更新
		if(e.getSource()==jb4) {
			
			int i = calendar.get(Calendar.MONTH) + 1;
			String date = calendar.get(Calendar.YEAR) + "-" + i + "-" + calendar.get(Calendar.DATE);
			if(tf5.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "信息不完整", "提示", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				String mysql="update repair set deal='"+date+"' where id='"+tf5.getText()+"'";
				if(SQLHelper.executeUpdate(mysql)==1)	{
					JOptionPane.showMessageDialog(null, "更新成功", "提示", JOptionPane.PLAIN_MESSAGE);
					String mysql2="select*from repair where sfloor='"+hfloor+"'";
					updateTable(mysql2);
				}
				else
					JOptionPane.showMessageDialog(null, "更新失败", "提示", JOptionPane.PLAIN_MESSAGE);
				System.out.println(mysql);
			}	
			
		}
		//返回
		if(e.getSource()==jb5) {
			String mysql="select*from repair where sfloor='"+hfloor+"'";
			updateTable(mysql);

		}
	}
	
	/*
	 * public static void main(String args[]) { new HRepairFrm("一号楼"); }
	 */
	  
}
