package functionalGraphicalInterface;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.Border;

import dao.HouseParentDao;
import dao.QueryExistDao;
import dao.StudentDao;

public class SRepairFrm extends JPanel implements ActionListener {

	Border titleBorder1 = BorderFactory.createTitledBorder("报修信息");
	Border titleBorder2 = BorderFactory.createTitledBorder(" 报修进度查询");

	JLabel l1 = new JLabel("学号");
	JLabel l5 = new JLabel("报修内容");
	JLabel l4 = new JLabel("所住楼号");
	JLabel l8 = new JLabel("寝室门牌号");
	JLabel l9 = new JLabel("报修时间");
	JLabel l_1 = new JLabel("学号");
	JLabel l_3 = new JLabel("报修时间");
	JLabel l_2 = new JLabel("寝室门牌号");

	JComboBox cb5 = new JComboBox();
	JComboBox cb6 = new JComboBox();
	JComboBox cb7 = new JComboBox();

	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf5 = new JTextField();
	JTextField tf4 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf6 = new JTextField();

	JButton jb1 = new JButton("上报信息"), jb2 = new JButton("清除"), jb3 = new JButton("信息查询");

	JPanel p4 = new JPanel(new GridLayout(4, 2)), p5 = new JPanel(new BorderLayout()),
			p6 = new JPanel(new BorderLayout()), p7 = new JPanel(new GridLayout(3, 2)),
			p8 = new JPanel(new GridLayout(1, 1)), p9 = new JPanel(), p10 = new JPanel(),
			p1 = new JPanel(new GridLayout(1, 3)), p2 = new JPanel(new GridLayout(1, 3)),
			p0 = new JPanel(new GridLayout(2, 1)),p11 = new JPanel(new GridLayout(1, 1));
	
	JTable t;
	Object[][] row;
	Object[]   column={"报修人学号","寝室门牌号","报修内容","报修时间","维修进度"};

	Calendar calendar = Calendar.getInstance();

	SRepairFrm() {

		
		setLayout(new BorderLayout());
		date(p1, cb5, cb6, cb7);
		//date(p2, cb8, cb9, cb0);

		p4.add(l1);
		p4.add(tf1);
		p4.add(l8);
		p4.add(tf5);
		p4.add(l5);
		p4.add(tf2);
		p4.add(l4);
		p4.add(tf3);

		p5.setBorder(titleBorder1);
		p8.add(p4);

		p9.add(jb1);
		jb1.addActionListener(this);
		p9.add(jb2);
		jb2.addActionListener(this);

		p7.setBorder(titleBorder2);
        p7.add(l_3);
		p7.add(p1);
		p7.add(l_1);
		p7.add(tf4);
		p7.add(l_2);
		p7.add(tf6);
		
		
		p0.add(p7);

		jb3.addActionListener(this);

		p5.add(p8, BorderLayout.CENTER);
		p5.add(p9, BorderLayout.SOUTH);
		p10.add(jb3);
		
		p6.add(p0, BorderLayout.CENTER);
		p6.add(p10, BorderLayout.SOUTH);

		JSplitPane i = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, p5, p6);
		i.setDividerLocation(300);
		add(i, BorderLayout.CENTER);
		validate();
		setVisible(true);
		setBounds(500, 300, 800, 600);
		//setResizable(false);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void date(JPanel l, JComboBox cbb4, JComboBox cbb5, JComboBox cbb6) {
		for (int i = calendar.get(Calendar.YEAR); i >= 2005; i--) {
			cbb4.addItem(i);
		}
		for (int i = 1; i <= 12; i++) {
			cbb5.addItem(i);
		}
		for (int i = 1; i <= 31; i++) {
			cbb6.addItem(i);
		}
		cbb4.setSelectedItem(calendar.get(Calendar.YEAR));
		cbb5.setSelectedItem(calendar.get(Calendar.MONTH) + 1);
		cbb6.setSelectedItem(calendar.get(Calendar.DATE));

		l.add(cbb4);
		l.add(cbb5);
		l.add(cbb6);
	}


	public void actionPerformed(ActionEvent e) {
		
	
		if(e.getSource()==jb1) {
			
				int i=calendar.get(Calendar.MONTH) + 1;
	            String c =  calendar.get(Calendar.YEAR) + "-" +i+ "-"
				+ calendar.get(Calendar.DATE) ;
				
			if(tf1.getText().equals("")||tf2.getText().equals("")||tf5.getText().equals("")) 
			JOptionPane.showMessageDialog(null, "信息不完整", "提示", JOptionPane.WARNING_MESSAGE);
			else if (!QueryExistDao.QueryExist("select*from student where no='" + tf1.getText() + "'")) {
				JOptionPane.showMessageDialog(null, "学号不存在", "提示", JOptionPane.WARNING_MESSAGE);
			} else { //上报信息
				  if(StudentDao.repairInsert(tf1.getText(), tf2.getText(), tf5.getText(), c,tf3.getText())==1)
						JOptionPane.showMessageDialog(null, "信息上报成功，请耐心等待修理", "提示", JOptionPane.PLAIN_MESSAGE);
				  else
						JOptionPane.showMessageDialog(null, "信息上报失败", "提示", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()==jb2) {
			tf1.setText(null);
			tf2.setText(null);
			tf5.setText(null);
			tf3.setText(null);
		}
	    if(e.getSource()==jb3) {
	    	String c=cb5.getSelectedItem().toString()+"-"+cb6.getSelectedItem().toString()+"-"+cb7.getSelectedItem().toString();
	    	String d = null;
	    	if(tf4.getText().equals("")&&tf6.getText().equals(""))
	    	JOptionPane.showMessageDialog(null, "信息不完整", "提示", JOptionPane.WARNING_MESSAGE);
	    	else {
	     if(tf4.getText().equals("")) {
	    		if(tf6.getText().equals(""))
	    		 d="select*from Repair where report='"+c+"'";
	    		else
	    			d="select*from Repair where report='"+c+"' and sbed='"+tf6.getText()+"'";
	    	}
	    	else {
	    		if(tf6.getText().equals(""))
	    		 d="select*from Repair where no='" + tf4.getText() + "'"+"and report='"+c+"'";
	    		else
	    			d="select*from Repair where no='"+tf4.getText()+"' and report='"+c+"' and sbed='"+tf6.getText()+"'";
	    	}
	    	System.out.println(d);
	    		if (!QueryExistDao.QueryExist(d)) 
				JOptionPane.showMessageDialog(null, "未查询到相关内容", "提示", JOptionPane.WARNING_MESSAGE);
	    	else
	    		updateTable(d);
	    }
	    }
	}

	public void updateTable(String sql) {
		row = StudentDao.executeRepairArray(sql);
		t = new JTable(row, column);
		p0.removeAll();
		p0.add(p7);p0.add(new JScrollPane(t));
		t.setEnabled(false);                // 清空文本框
		validate();
	}

	
	 // public static void main(String args[]) { new SRepairFrm(); }
	 
}