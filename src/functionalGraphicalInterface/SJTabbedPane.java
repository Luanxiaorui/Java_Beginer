package functionalGraphicalInterface;

import javax.swing.*;

import toolkit.ImagePanel;
import toolkit.SQLHelper;

import java.awt.*;
import java.awt.event.*;

public class SJTabbedPane extends JFrame implements ActionListener{

	String snumber,sfloor;
	JTabbedPane tabbedPane;

	JMenuBar menu = new JMenuBar();
	JMenu m1 = new JMenu("�û�");

	JMenuItem mt1 = new JMenuItem("ע��");
	JMenuItem mt2 = new JMenuItem("�޸�����");

	
	SJTabbedPane(String snumber){
		
		this.snumber=snumber;
    	sfloor=SQLHelper.executeSingleQuery("select *from student where no='"+snumber+"'", "sfloor");
    	
    	Image image = new ImageIcon("suep3.jpg").getImage();
		ImagePanel t=new ImagePanel(image);
		JLabel l1=new JLabel("��ӭ�㣬��¼�������ϵͳ");
		t.add(l1);l1.setFont(new Font("����", Font.PLAIN, 36));
		
    	tabbedPane=new JTabbedPane();
    	menu.add(Box.createHorizontalGlue());
		menu.add(m1);
		m1.add(mt1);
		m1.add(mt2);

		mt1.addActionListener(this);
		mt2.addActionListener(this);
		
		SAfficheFrm l=new SAfficheFrm(sfloor);
		SQueryFrm j=new SQueryFrm();
		SRepairFrm k=new SRepairFrm();		
		
		tabbedPane.addTab("��ӭ", t);
		tabbedPane.addTab("����鿴", l);
		tabbedPane.addTab("ѧ����Ϣ��ѯ", j);
		tabbedPane.addTab("����ά��", k);
		
		tabbedPane.setPreferredSize(new Dimension(430, 340));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		
		add(tabbedPane, BorderLayout.CENTER);
		add(menu, BorderLayout.NORTH);
		setVisible(true);
		setTitle("�������ϵͳ����ѧ����");
		setBounds(100, 100, 1500, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
	}
	
	
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == mt1) {
			this.dispose();
			new LoginFrm();
		}
		if (e.getSource() == mt2) {
			this.dispose();
			new PasswordFrm(snumber, "ѧ��");
		}
	}
	public static void main(String args[]) {
		new SJTabbedPane("20221536");
	}
}
