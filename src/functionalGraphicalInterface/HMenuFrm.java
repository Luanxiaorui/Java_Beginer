package functionalGraphicalInterface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
//import java.awt.Graphics;

import toolkit.SQLHelper;

import java.awt.event.*;

public class HMenuFrm extends JFrame implements ActionListener{
	
	JLabel l1=new JLabel("�Ϻ�������ѧ�޹�ϵͳ");
	
	JButton jb1=new JButton("ѧ����Ϣ��ѯ");
	JButton jb2=new JButton("ѧ����Ϣ����");
	JButton jb3=new JButton("������Ϣ����");
	JButton jb4=new JButton("�޸�����");
	JButton jb5=new JButton("�ÿ���Ϣ����");
	JButton jb6=new JButton("�����Ϣ����");
	JButton jb7=new JButton("���淢������");
	JButton jb8=new JButton("�˳���¼");
	JButton jb9=new JButton("ѧ����Ϣ����");

    JPanel j1=new JPanel(null);
    JPanel j2=new JPanel();
    
    String hnumber,hfloor;
	
    HMenuFrm(String number){
    		
    	this.hnumber=number;
    	l1.setFont(new Font("����",Font.BOLD,36));
    	add(l1,BorderLayout.NORTH);
    
    	String sql = "select*from houseparent where no='" + hnumber+ "'";
	    hfloor=SQLHelper.executeSingleQuery(sql, "hfloor");
		/*
		 * Image image = new ImageIcon("a.jpg").getImage(); j1 = new ImageDemo(image);
		 */
    	j1.setBorder(BorderFactory.createTitledBorder("�޹ܹ���"));
    	j1.add(jb1);jb1.setBounds(70,50,120,50);
    	j1.add(jb2);jb2.setBounds(210, 50, 120,50);
    	j1.add(jb3);jb3.setBounds(70, 130, 120, 50);
    	j1.add(jb5);jb5.setBounds(210,130,120,50);
    	j1.add(jb6);jb6.setBounds(70,210,120,50);
    	j1.add(jb7);jb7.setBounds(210,210,120,50);
    	j1.add(jb9);jb9.setBounds(70, 300, 260, 50);
    	add(j1,BorderLayout.CENTER);
    	
    	j2.add(jb4);j2.add(jb8);
    	add(j2,BorderLayout.SOUTH);
    	
    	jb1.addActionListener(this);
    	jb2.addActionListener(this);
    	jb3.addActionListener(this);
    	jb4.addActionListener(this);
    	jb5.addActionListener(this);
    	jb6.addActionListener(this);
    	jb7.addActionListener(this);
    	jb8.addActionListener(this);
    	jb9.addActionListener(this);
    	
    	setVisible(true);
    	setBounds(100,100,400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void   actionPerformed(ActionEvent e) {
    	
    	if(e.getSource()==jb1) {
    		new HQueryFrm();
    	}
    	if(e.getSource()==jb2) {
    		new  HManageFrm(hfloor);
    	}
    	if(e.getSource()==jb3) {
    		new  HRepairFrm(hfloor);
    	}
    	if(e.getSource()==jb4) {
    		new  PasswordFrm(hnumber,"�޹�");
    		this.dispose();
    	}
    	if(e.getSource()==jb5) {
    		new  HVisitorFrm(hfloor);
    	}
    	if(e.getSource()==jb6) {
    		new  HLateFrm(hfloor);
    	}
    	if(e.getSource()==jb7) {
    		new  HAfficheFrm(hfloor,hnumber);
    	}
    	if(e.getSource()==jb8) {
    		
    		if(JOptionPane.showConfirmDialog(null, "ȷ���˳���¼��", "��ʾ", JOptionPane.OK_CANCEL_OPTION)==0) 
    		{
    		this.dispose();
    		new  LoginFrm();
    		}
    	}
    	if(e.getSource()==jb9) {
    		new HInsertStudent(hfloor);
    	}
    	
    }
}
