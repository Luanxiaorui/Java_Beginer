package functionalGraphicalInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GodJTabbedPane extends JFrame implements ActionListener {

	JTabbedPane tabbedPane;
	JMenuBar menu = new JMenuBar();
	JMenu m1 = new JMenu("用户");

	JMenuItem mt1 = new JMenuItem("注销");
	JMenuItem mt2 = new JMenuItem("修改密码");
	String number;
	
	GodJTabbedPane(String number){
		
		this.number=number;
		tabbedPane = new JTabbedPane();
		
		menu.add(Box.createHorizontalGlue());
		menu.add(m1);
		m1.add(mt1);
		m1.add(mt2);

		mt1.addActionListener(this);
		mt2.addActionListener(this);
		
		// 实例化JPanel
	    GodHManageFrm i = new GodHManageFrm();
	    GodHManageInsertFrm k=new GodHManageInsertFrm();
	
	
	    tabbedPane.addTab("宿管信息管理", i);
	    tabbedPane.addTab("宿管信息录入", k);
	    
		tabbedPane.setPreferredSize(new Dimension(430, 340));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		
	    
		add(tabbedPane, BorderLayout.CENTER);
		add(menu, BorderLayout.NORTH);
		
		setVisible(true);
		setTitle("宿舍管理系统——软件管理端");
		setBounds(100, 100, 1500, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mt1) {
			this.dispose();
			new LoginFrm();
		}
		if (e.getSource() == mt2) {
			new PasswordFrm(number, "软件管理员");
			this.dispose();
		}
	}

	public static void main(String args[]) {
		new GodJTabbedPane("2801611461");
	}

}
