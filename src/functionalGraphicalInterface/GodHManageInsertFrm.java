package functionalGraphicalInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import dao.GodDao;
import dao.QueryExistDao;
import toolkit.SQLHelper;

public class GodHManageInsertFrm extends JPanel implements ActionListener{
	
	
	Border titleBorder1 = BorderFactory.createTitledBorder("宿管信息录入");

	JLabel l1 = new JLabel("工号");
	JLabel l3 = new JLabel("性别");
	JLabel l2 = new JLabel("姓名");
	JLabel l4 = new JLabel("管理楼号");

	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();

    JPanel jp=new JPanel(new GridLayout(4,2));
    JPanel jp2=new JPanel();
    JButton jb=new JButton("确认录入");
    JButton jb1=new JButton("清空");

	GodHManageInsertFrm() {
		
		this.setLayout(new BorderLayout());
		
       jp.add(l1);
       jp.add(tf1);
       jp.add(l2);
       jp.add(tf2);
       jp.add(l3);
       jp.add(tf3);
       jp.add(l4);
       jp.add(tf4);
       
       jp2.add(jb);
       jp2.add(jb1);
       jb.addActionListener(this);
       jb1.addActionListener(this);
       
       add(jp,BorderLayout.CENTER);
       add(jp2,BorderLayout.SOUTH);

		validate();
		setVisible(true);
		setBounds(500, 300, 800, 600);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb) {
			if (tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("")
					|| tf4.getText().equals(""))
				JOptionPane.showMessageDialog(null, "信息不完整", "提示", JOptionPane.WARNING_MESSAGE);
			else if (QueryExistDao.QueryExist("select*from houseparent where no='" + tf1.getText() + "'")) {
				JOptionPane.showMessageDialog(null, "工号已经存在", "提示", JOptionPane.WARNING_MESSAGE);
			}
			else {
				if (GodDao.executeInsert(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText()) == 1) {
					JOptionPane.showMessageDialog(null, "录入信息成功", "提示", JOptionPane.PLAIN_MESSAGE);
					SQLHelper.closeConnection();
				} else {
					JOptionPane.showMessageDialog(null, "录入信息失败", "提示", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
		if(e.getSource()==jb1) {
			tf1.setText(null);
			tf2.setText(null);
			tf3.setText(null);
			tf4.setText(null);
		}
	}
}
