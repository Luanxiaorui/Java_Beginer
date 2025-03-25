package functionalGraphicalInterface;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import toolkit.ImagePanel;
import toolkit.SQLHelper;

public class HJTabbedPane extends JFrame implements ActionListener {

	JTabbedPane tabbedPane;

	String hnumber, hfloor;

	JMenuBar menu = new JMenuBar();
	JMenu m1 = new JMenu("�û�");

	JMenuItem mt1 = new JMenuItem("ע��");
	JMenuItem mt2 = new JMenuItem("�޸�����");

	HJTabbedPane(String number) {

		menu.add(Box.createHorizontalGlue());
		menu.add(m1);
		m1.add(mt1);
		m1.add(mt2);

		mt1.addActionListener(this);
		mt2.addActionListener(this);

		this.hnumber = number;
		String sql = "select*from houseparent where no='" + hnumber + "'";
		hfloor = SQLHelper.executeSingleQuery(sql, "hfloor");

		tabbedPane = new JTabbedPane();

		// ʵ����JPanel
		HManageFrm i = new HManageFrm(hfloor);
		HLateFrm j = new HLateFrm(hfloor);
		HAfficheFrm k = new HAfficheFrm(hfloor, hnumber);
		HInsertStudent l = new HInsertStudent(hfloor);
		HQueryFrm o = new HQueryFrm();
		HRepairFrm q = new HRepairFrm(hfloor);
		HVisitorFrm r = new HVisitorFrm(hfloor);
		Image image = new ImageIcon("suep3.jpg").getImage();
		ImagePanel t=new ImagePanel(image);
		JLabel l1=new JLabel("��ӭ�㣬��¼�������ϵͳ");
		t.add(l1);l1.setFont(new Font("����", Font.PLAIN, 36));
		//t.setSize(1000, 800);
		// PasswordFrm s=new PasswordFrm(hnumber,"�޹�");

		// titleΪ���(ѡ�)�ı�ǩ,componentΪ���(ѡ�)ʵ��
		tabbedPane.addTab("��ӭ", t);
		tabbedPane.addTab("ѧ����Ϣ����", i);
		tabbedPane.addTab("�����Ϣ����", j);
		tabbedPane.addTab("ѧ����Ϣ��ѯ", o);
		tabbedPane.addTab("������Ϣ����", q);
		tabbedPane.addTab("���淢������", k);
		tabbedPane.addTab("����ѧ����Ϣ", l);
		tabbedPane.addTab("�ÿ���Ϣ����", r);
		// tabbedPane.addTab("�޸�����", s);

		tabbedPane.setPreferredSize(new Dimension(430, 340));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		// tabbedPane.addTab("����", );
		// tabbedPane.addTab("ɾ��",);
		// tabbedPane.addTab("����", );
		// tabbedPane.addTab("�鿴", );
		// tabbedPane.addTab("ɢ��ֵ������",);

		add(tabbedPane, BorderLayout.CENTER);
		add(menu, BorderLayout.NORTH);
		setVisible(true);
		setTitle("�������ϵͳ�����޹ܶ�");
		setBounds(100, 100, 1500, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mt1) {
			this.dispose();
			new LoginFrm();
		}
		if (e.getSource() == mt2) {
			
			new PasswordFrm(hnumber, "�޹�");
			this.dispose();
		}
	}

	public static void main(String args[]) {
		new HJTabbedPane("001");
	}
}
