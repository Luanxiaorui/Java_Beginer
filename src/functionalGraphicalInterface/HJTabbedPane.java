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
	JMenu m1 = new JMenu("用户");

	JMenuItem mt1 = new JMenuItem("注销");
	JMenuItem mt2 = new JMenuItem("修改密码");

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

		// 实例化JPanel
		HManageFrm i = new HManageFrm(hfloor);
		HLateFrm j = new HLateFrm(hfloor);
		HAfficheFrm k = new HAfficheFrm(hfloor, hnumber);
		HInsertStudent l = new HInsertStudent(hfloor);
		HQueryFrm o = new HQueryFrm();
		HRepairFrm q = new HRepairFrm(hfloor);
		HVisitorFrm r = new HVisitorFrm(hfloor);
		Image image = new ImageIcon("suep3.jpg").getImage();
		ImagePanel t=new ImagePanel(image);
		JLabel l1=new JLabel("欢迎你，登录宿舍管理系统");
		t.add(l1);l1.setFont(new Font("宋体", Font.PLAIN, 36));
		//t.setSize(1000, 800);
		// PasswordFrm s=new PasswordFrm(hnumber,"宿管");

		// title为面板(选项卡)的标签,component为面板(选项卡)实例
		tabbedPane.addTab("欢迎", t);
		tabbedPane.addTab("学生信息管理", i);
		tabbedPane.addTab("晚归信息管理", j);
		tabbedPane.addTab("学生信息查询", o);
		tabbedPane.addTab("报修信息管理", q);
		tabbedPane.addTab("公告发布管理", k);
		tabbedPane.addTab("新增学生信息", l);
		tabbedPane.addTab("访客信息管理", r);
		// tabbedPane.addTab("修改密码", s);

		tabbedPane.setPreferredSize(new Dimension(430, 340));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		// tabbedPane.addTab("查找", );
		// tabbedPane.addTab("删除",);
		// tabbedPane.addTab("设置", );
		// tabbedPane.addTab("查看", );
		// tabbedPane.addTab("散列值计算器",);

		add(tabbedPane, BorderLayout.CENTER);
		add(menu, BorderLayout.NORTH);
		setVisible(true);
		setTitle("宿舍管理系统——宿管端");
		setBounds(100, 100, 1500, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mt1) {
			this.dispose();
			new LoginFrm();
		}
		if (e.getSource() == mt2) {
			
			new PasswordFrm(hnumber, "宿管");
			this.dispose();
		}
	}

	public static void main(String args[]) {
		new HJTabbedPane("001");
	}
}
