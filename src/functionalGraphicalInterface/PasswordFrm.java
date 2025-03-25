package functionalGraphicalInterface;

import java.awt.*;
import javax.swing.*;

import dao.LoginDao;

import java.awt.event.*;

public class PasswordFrm extends JFrame implements ActionListener {

	JLabel l2 = new JLabel("������ԭ����:");
	JLabel l3 = new JLabel("������������:");

	JLabel l5 = new JLabel("���ٴ�����������");

	JPasswordField tf2 = new JPasswordField();
	JPasswordField tf3 = new JPasswordField();
	JPasswordField tf4 = new JPasswordField();

	String number, identity;

	JButton jb1 = new JButton("ȷ���޸�");
	JButton jb2 = new JButton("ȡ��");

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
		setTitle("�����޸�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {

			if (tf2.getText().equals("") || tf3.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "��Ϣ������", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else if (!tf3.getText().equals(tf4.getText())) {
				JOptionPane.showMessageDialog(null, "������������벻һ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else if (tf3.getText().length() > 10) {
				JOptionPane.showMessageDialog(null, "���볤�Ȳ��ó���ʮλ", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				if (identity.equals("�޹�")) {
					int i = LoginDao.QueryLogin(number, tf2.getText(), "houseparent");
					if (i == 2) {
						if (LoginDao.UpdatePassword(number, tf3.getText(), "houseparent") == 1) {
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�����ʹ���������¼", "��ʾ", JOptionPane.PLAIN_MESSAGE);
							new LoginFrm();
							this.dispose();
						} else
							JOptionPane.showMessageDialog(null, "�޸�ʧ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);

					} else if (i == 0) {
						JOptionPane.showMessageDialog(null, "�û�������", "��ʾ", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "�������", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						tf2.setText(null);
						tf3.setText(null);
						tf4.setText(null);
					}
				} else if(identity.equals("ѧ��")){
					int i = LoginDao.QueryLogin(number, tf2.getText(), "student");
					if (i == 2) {
						if (LoginDao.UpdatePassword(number, tf3.getText(), "student") == 1) {
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�����ʹ���������¼", "��ʾ", JOptionPane.PLAIN_MESSAGE);
							new LoginFrm();
							this.dispose();
						} else
							JOptionPane.showMessageDialog(null, "�޸�ʧ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else if (i == 0) {
						JOptionPane.showMessageDialog(null, "�û�������", "��ʾ", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "�û������������", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						tf2.setText(null);
						tf3.setText(null);
						tf4.setText(null);
					}
				}
				else {
					int i = LoginDao.QueryLogin(number, tf2.getText(), "god");
					if (i == 2) {
						if (LoginDao.UpdatePassword(number, tf3.getText(), "god") == 1) {
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�����ʹ���������¼", "��ʾ", JOptionPane.PLAIN_MESSAGE);
							new LoginFrm();
							this.dispose();
						} else
							JOptionPane.showMessageDialog(null, "�޸�ʧ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else if (i == 0) {
						JOptionPane.showMessageDialog(null, "�û�������", "��ʾ", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "�û������������", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						tf2.setText(null);
						tf3.setText(null);
						tf4.setText(null);
					}
				}
			}
		}
		if (e.getSource() == jb2) {
			if (identity.equals("�޹�")) {
				this.dispose();
				new HJTabbedPane(number);
			} else if(identity.equals("ѧ��")){
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
