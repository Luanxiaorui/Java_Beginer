package functionalGraphicalInterface;

import java.awt.*;

import dao.LoginDao;
//import toolkit.BeautyUI;

import javax.swing.*;


public class LoginFrm extends JFrame implements ActionListener {

	JLabel l1 = new JLabel("上海电力大学");
	JLabel l2 = new JLabel("宿管系统登录");
	JLabel l3 = new JLabel("请选择身份");
	JLabel l4 = new JLabel("学号或工号");
	JLabel l5 = new JLabel("密码");


	JComboBox cb = new JComboBox();

	JTextField tf1 = new JTextField();
	JPasswordField tf2 = new JPasswordField();

	JButton jb1 = new JButton("马上登录");

	LoginFrm() {

		cb.addItem("宿管");
		cb.addItem("学生");
		cb.addItem("软件管理员");

		this.setLayout(null);

		add(l1);
		l1.setBounds(100, 50, 300, 50);
		l1.setFont(new Font("仿宋", Font.BOLD, 42));
		add(l2);
		l2.setBounds(150, 125, 300, 50);
		l2.setFont(new Font("宋体", Font.PLAIN, 28));
		add(l3);
		l3.setBounds(100, 200, 100, 25);
		add(cb);
		cb.setBounds(100, 225, 300, 50);
		add(l4);
		l4.setBounds(100, 300, 100, 25);
		add(tf1);
		tf1.setBounds(100, 325, 300, 50);
		tf1.setFont(new Font("宋体", Font.BOLD, 18));
		add(l5);
		l5.setBounds(100, 400, 50, 25);
		add(tf2);
		tf2.setBounds(100, 425, 300, 50);
		tf2.setFont(new Font("宋体", Font.BOLD, 15));
		add(jb1);
		jb1.setBounds(80, 525, 340, 60);

		jb1.addActionListener(this);
		tf2.addActionListener(this);

		setVisible(true);
		setBounds(100, 100, 500, 680);
		this.setResizable(false);
		setTitle("宿舍管理系统登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1 || e.getSource() == tf2) {
			if (tf1.getText().equals("") || tf2.getText().equals("")) 
			{
				JOptionPane.showMessageDialog(null, "信息不完整", "提示", JOptionPane.WARNING_MESSAGE);
			} 
			else {
				if (cb.getSelectedItem().toString().equals("宿管")) {
					if (LoginDao.QueryLogin(tf1.getText(), tf2.getText(),"houseparent") == 2) {
						//new HMenuFrm(tf1.getText());
						new HJTabbedPane(tf1.getText());
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.PLAIN_MESSAGE);
						tf1.setText(null);
						tf2.setText(null);
					}
				}
				 else if(cb.getSelectedItem().toString().equals("学生")){
					if (LoginDao.QueryLogin(tf1.getText(), tf2.getText(),"student") == 2) {
						//new SMenuFrm(tf1.getText());
						new SJTabbedPane(tf1.getText());
						this.dispose();
					}

					else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.PLAIN_MESSAGE);
						tf1.setText(null);
						tf2.setText(null);
					}
			}
				 else {
					 ///////////////
					 if (LoginDao.QueryLogin(tf1.getText(), tf2.getText(),"God") == 2) {
							//new SMenuFrm(tf1.getText());
							new GodJTabbedPane(tf1.getText());
							this.dispose();
						}

						else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.PLAIN_MESSAGE);
							tf1.setText(null);
							tf2.setText(null);
						}
				 }
		}
	}
}

	public static void main(String[] args) {

		/*
		 * try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		 * catch (ClassNotFoundException | InstantiationException |
		 * IllegalAccessException | UnsupportedLookAndFeelException e) {
		 * e.printStackTrace(); }
		 */
		 
		//BeautyUI.beautyUI();
		new LoginFrm();
	}

}
