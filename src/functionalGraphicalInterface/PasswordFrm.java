package functionalGraphicalInterface;

import java.awt.*;
import javax.swing.*;

import dao.LoginDao;

import java.awt.event.*;

public class PasswordFrm extends JFrame implements ActionListener {

	JLabel l2 = new JLabel("请输入原密码:");
	JLabel l3 = new JLabel("请输入新密码:");

	JLabel l5 = new JLabel("请再次输入新密码");

	JPasswordField tf2 = new JPasswordField();
	JPasswordField tf3 = new JPasswordField();
	JPasswordField tf4 = new JPasswordField();

	String number, identity;

	JButton jb1 = new JButton("确认修改");
	JButton jb2 = new JButton("取消");

	PasswordFrm(String number, String identity) {

		this.number = number;
		this.identity = identity;

		this.setLayout(null);

		add(l2);
		l2.setBounds(100, 30, 100, 25);
		add(tf2);
		tf2.setBounds(100, 55, 300, 50);
		add(l3);
		l3.setBounds(100, 130, 100, 25);
		add(tf3);
		tf3.setBounds(100, 155, 300, 50);
		add(l5);
		l5.setBounds(100, 230, 100, 25);
		add(tf4);
		tf4.setBounds(100, 255, 300, 50);
		add(jb1);
		jb1.setBounds(80, 330, 330, 50);
		jb1.addActionListener(this);
		add(jb2);
		jb2.setBounds(80, 420, 330, 50);
		jb2.addActionListener(this);

		setVisible(true);
		setBounds(100, 100, 500, 580);
		setTitle("密码修改");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {

			if (tf2.getText().equals("") || tf3.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "信息不完整", "提示", JOptionPane.WARNING_MESSAGE);
			} else if (!tf3.getText().equals(tf4.getText())) {
				JOptionPane.showMessageDialog(null, "两次输入的密码不一致", "提示", JOptionPane.WARNING_MESSAGE);
			} else if (tf3.getText().length() > 10) {
				JOptionPane.showMessageDialog(null, "密码长度不得超过十位", "提示", JOptionPane.WARNING_MESSAGE);
			} else {
				if (identity.equals("宿管")) {
					int i = LoginDao.QueryLogin(number, tf2.getText(), "houseparent");
					if (i == 2) {
						if (LoginDao.UpdatePassword(number, tf3.getText(), "houseparent") == 1) {
							JOptionPane.showMessageDialog(null, "修改成功，请使用新密码登录", "提示", JOptionPane.PLAIN_MESSAGE);
							new LoginFrm();
							this.dispose();
						} else
							JOptionPane.showMessageDialog(null, "修改失败", "提示", JOptionPane.PLAIN_MESSAGE);

					} else if (i == 0) {
						JOptionPane.showMessageDialog(null, "用户不存在", "提示", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "密码错误", "提示", JOptionPane.PLAIN_MESSAGE);
						tf2.setText(null);
						tf3.setText(null);
						tf4.setText(null);
					}
				} else if(identity.equals("学生")){
					int i = LoginDao.QueryLogin(number, tf2.getText(), "student");
					if (i == 2) {
						if (LoginDao.UpdatePassword(number, tf3.getText(), "student") == 1) {
							JOptionPane.showMessageDialog(null, "修改成功，请使用新密码登录", "提示", JOptionPane.PLAIN_MESSAGE);
							new LoginFrm();
							this.dispose();
						} else
							JOptionPane.showMessageDialog(null, "修改失败", "提示", JOptionPane.PLAIN_MESSAGE);
					} else if (i == 0) {
						JOptionPane.showMessageDialog(null, "用户不存在", "提示", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.PLAIN_MESSAGE);
						tf2.setText(null);
						tf3.setText(null);
						tf4.setText(null);
					}
				}
				else {
					int i = LoginDao.QueryLogin(number, tf2.getText(), "god");
					if (i == 2) {
						if (LoginDao.UpdatePassword(number, tf3.getText(), "god") == 1) {
							JOptionPane.showMessageDialog(null, "修改成功，请使用新密码登录", "提示", JOptionPane.PLAIN_MESSAGE);
							new LoginFrm();
							this.dispose();
						} else
							JOptionPane.showMessageDialog(null, "修改失败", "提示", JOptionPane.PLAIN_MESSAGE);
					} else if (i == 0) {
						JOptionPane.showMessageDialog(null, "用户不存在", "提示", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.PLAIN_MESSAGE);
						tf2.setText(null);
						tf3.setText(null);
						tf4.setText(null);
					}
				}
			}
		}
		if (e.getSource() == jb2) {
			if (identity.equals("宿管")) {
				this.dispose();
				new HJTabbedPane(number);
			} else if(identity.equals("学生")){
				this.dispose();
				new SJTabbedPane(number);
			}
			else {
				this.dispose();
				new GodJTabbedPane(number);
			}
		}
	}
}
