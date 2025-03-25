package functionalGraphicalInterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import toolkit.SQLHelper;

public class SMenuFrm extends JFrame implements ActionListener{

    JLabel l1=new JLabel("�Ϻ�������ѧ�޹�ϵͳ");
	
	JButton jb1=new JButton("ѧ����Ϣ��ѯ");
	JButton jb2=new JButton("����鿴");
	JButton jb3=new JButton("���ϱ���");
	JButton jb4=new JButton("�����޸�");
	JButton jb5=new JButton("�˳���¼");


    JPanel j1=new JPanel(null);
    JPanel j2=new JPanel();
    
    String snumber;
    String sfloor;
    
    SMenuFrm(String snumber){
    	
    	this.snumber=snumber;
    	sfloor=SQLHelper.executeSingleQuery("select *from student where no='"+snumber+"'", "sfloor");
    	
    	j1.setBorder(BorderFactory.createTitledBorder("ѧ������"));
    	j1.add(jb1);jb1.setBounds(100, 50, 200, 100);
    	j1.add(jb2);jb2.setBounds(100, 180, 200, 100);
    	j1.add(jb3);jb3.setBounds(100,310 , 200, 100);
    	add(j1,BorderLayout.CENTER);

    	
    	j2.add(jb4);j2.add(jb5);
    	add(j2,BorderLayout.SOUTH);

    	l1.setFont(new Font("����",Font.BOLD,36));
    	add(l1,BorderLayout.NORTH);

    	jb1.addActionListener(this);
    	jb2.addActionListener(this);
    	jb3.addActionListener(this);
    	jb4.addActionListener(this);
    	jb5.addActionListener(this);
    	
    	setVisible(true);
    	setBounds(100,100,400,600);
		setTitle("ѧ�����ܲ˵�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==jb1) {
    		new SQueryFrm();
    	}
    	if(e.getSource()==jb2) {
    		new  SAfficheFrm(sfloor);
    	}
    	if(e.getSource()==jb3) {
    		new  SRepairFrm();
    	}
    	if(e.getSource()==jb4) {
    		new  PasswordFrm(snumber,"ѧ��");
    		this.dispose();
    	}
    	if(e.getSource()==jb5) {
    		new  LoginFrm();
    		this.dispose();
    	}
    }
}
