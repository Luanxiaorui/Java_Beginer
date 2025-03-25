package functionalGraphicalInterface;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.Border;

import dao.HouseParentDao;
import dao.QueryExistDao;
import functionalGraphicalInterface.HManageFrm.mouse;
import toolkit.SQLHelper;

public class HVisitorFrm extends JPanel implements ActionListener{

	Border titleBorder1 = BorderFactory.createTitledBorder("�ÿ���Ϣ¼��");
	Border titleBorder2 = BorderFactory.createTitledBorder(" �ÿ���Ϣ��ѯ");

	JLabel l1 = new JLabel("֤����");
	JLabel l8 = new JLabel("����");
	JLabel l4 = new JLabel("��ע");
	JLabel l5 = new JLabel("�ܷ���ѧ��");
	JLabel l6=new JLabel("��ϵ");
	
	JLabel l7=new JLabel("���ʱ��");
	JLabel l9=new JLabel("�뿪ʱ��");
	
	JLabel l_1 = new JLabel("�ܷ���ѧ��");
	JLabel l_3 = new JLabel("��������");

	JComboBox cb5 = new JComboBox();
	JComboBox cb6 = new JComboBox();
	JComboBox cb7 = new JComboBox();

	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf5 = new JTextField();
	JTextField tf4 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf6 = new JTextField();
	
	JTextField tf7 = new JTextField(10);
	JTextField tf8 = new JTextField(10);

	JButton jb1 = new JButton("�ϱ���Ϣ"), jb2 = new JButton("���"), jb3 = new JButton("��Ϣ��ѯ"),jb4=new JButton("����ʱ��"),jb5=new JButton("���");

	JPanel p4 = new JPanel(new GridLayout(5, 2)), p5 = new JPanel(new BorderLayout()),
			p6 = new JPanel(new BorderLayout()), p7 = new JPanel(new GridLayout(2, 2)),
			p8 = new JPanel(new GridLayout(1, 1)), p9 = new JPanel(), p10 = new JPanel(),
			p1 = new JPanel(new GridLayout(1, 3)), p2 = new JPanel(new GridLayout(1, 3)),
			p0 = new JPanel(new GridLayout(2, 1)), p11 = new JPanel(new GridLayout(1, 1)),p12=new JPanel(new BorderLayout()),p13=new JPanel();

	JTable t;
	Object[][] row;
	Object[] column = { "���ʱ��","�ÿ�֤����", "�ÿ�����", "�ܷ���ѧ��", "��������", "����ʱ��", "�뿪ʱ��" ,"��ע"};

	Calendar calendar = Calendar.getInstance();
	String hfloor, htime;

	HVisitorFrm(String hfloor) {

		this.hfloor = hfloor;
//		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm"); 
//		htime = sdfTime.format(new Date());

		date(p1, cb5, cb6, cb7);
		// date(p2, cb8, cb9, cb0);

		p4.add(l1);
		p4.add(tf1);
		p4.add(l8);
		p4.add(tf2);
		p4.add(l5);
		p4.add(tf3);
		p4.add(l4);
		p4.add(tf5);
		p4.add(l6);
		p4.add(tf6);

		p5.setBorder(titleBorder1);
		p8.add(p4);

		p9.add(jb1);
		jb1.addActionListener(this);
		p9.add(jb2);
		jb2.addActionListener(this);

		p13.add(l7);
		p13.add(tf7);
		p13.add(l9);
		p13.add(tf8);
		
		p12.add(p13,BorderLayout.NORTH);
		
		p7.setBorder(titleBorder2);
		p7.add(l_3);
		p7.add(p1);
		p7.add(l_1);
		p7.add(tf4);
		
		p0.add(p7);
		p0.add(p12);

		setLayout(new BorderLayout());
	    String mysql="select*from visitor where sfloor='"+hfloor+"'"; 
	    updateTable(mysql);

	    t.addMouseListener(new mouse());
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);

		p5.add(p8, BorderLayout.CENTER);
		p5.add(p9, BorderLayout.SOUTH);
		p10.add(jb3);p10.add(jb4);
		p10.add(jb5);

		p6.add(p0, BorderLayout.CENTER);
		p6.add(p10, BorderLayout.SOUTH);

		JSplitPane i = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, p5, p6);
		i.setDividerLocation(300);
		add(i, BorderLayout.CENTER);
		validate();
		setVisible(true);
		setBounds(500, 300, 1000, 600);
		//setResizable(false);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void date(JPanel l, JComboBox cbb4, JComboBox cbb5, JComboBox cbb6) {
		for (int i = calendar.get(Calendar.YEAR); i >= 2005; i--) {
			cbb4.addItem(i);
		}
		for (int i = 1; i <= 12; i++) {
			cbb5.addItem(i);
		}
		for (int i = 1; i <= 31; i++) {
			cbb6.addItem(i);
		}
		cbb4.setSelectedItem(calendar.get(Calendar.YEAR));
		cbb5.setSelectedItem(calendar.get(Calendar.MONTH) + 1);
		cbb6.setSelectedItem(calendar.get(Calendar.DATE));

		l.add(cbb4);
		l.add(cbb5);
		l.add(cbb6);
	}

	public void updateTable(String sql) {
		row = HouseParentDao.executeVisitorArray(sql);
		t = new JTable(row, column);
		t.addMouseListener(new mouse());
		p12.removeAll();
		p12.add(new JScrollPane(t),BorderLayout.CENTER);
		p12.add(p13,BorderLayout.NORTH);
		//t.setEnabled(false); // ����ı���
		validate();
	}

	public void actionPerformed(ActionEvent e) {
		
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm"); 
		htime = sdfTime.format(new Date());
		//������Ϣ
		if (e.getSource() == jb1) {
			int i = calendar.get(Calendar.MONTH) + 1;
			String c = calendar.get(Calendar.YEAR) + "-" + i + "-" + calendar.get(Calendar.DATE);

			if (tf1.getText().equals("") || tf2.getText().equals("") || tf5.getText().equals("")
					|| tf3.getText().equals("")||tf6.getText().equals(""))
				JOptionPane.showMessageDialog(null, "��Ϣ������", "��ʾ", JOptionPane.WARNING_MESSAGE);
			else if (!QueryExistDao.QueryExist("select*from student where no='" + tf3.getText() + "'")) {
				JOptionPane.showMessageDialog(null, "ѧ�Ų�����", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else if (!QueryExistDao.QueryExist("select*from student where no='" + tf3.getText() + "' and sfloor='" + hfloor + "'")) {
				JOptionPane.showMessageDialog(null, "���Ǹ�����¥ѧ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				// �ϱ���Ϣ
				String mysql = "insert into visitor values('" + tf1.getText() + "','" + tf3.getText() + "','" + tf6.getText()
						+ "','" +tf2.getText() + "','" + c + "','" + htime + "','"+" "+"','"+tf5.getText()+"',null,'"+hfloor+"')";
						 System.out.println(mysql);
				if (SQLHelper.executeUpdate(mysql) == 1)
					JOptionPane.showMessageDialog(null, "��Ϣ�ϱ��ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "��Ϣ�ϱ�ʧ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
				SQLHelper.closeConnection();
			}
		}
		//��ղ�����
		if (e.getSource() == jb2) {
			tf1.setText(null);
			tf2.setText(null);
			tf5.setText(null);
			tf3.setText(null);
			tf6.setText(null);
		}
		//��ѯ
		if (e.getSource() == jb3) {
			String c = cb5.getSelectedItem().toString() + "-" + cb6.getSelectedItem().toString() + "-"
					+ cb7.getSelectedItem().toString(), d;
			if (tf4.getText().equals(""))
				d = "select*from visitor where sin='" + c + "' and sfloor='"+hfloor+"'";
			else
				d = "select*from visitor where no='" + tf4.getText() + "'" + "and sin='" + c + "' and sfloor='"+hfloor+"'";
			System.out.println(d);
			if (!QueryExistDao.QueryExist(d))
				JOptionPane.showMessageDialog(null, "δ��ѯ���������", "��ʾ", JOptionPane.WARNING_MESSAGE);
			else
				updateTable(d);
		}
		
		if(e.getSource()==jb4) {
			if(!tf7.getText().equals("")) {
				String mysql="update visitor set sout='"+htime+"' where id='"+tf7.getText()+"'";
				System.out.println(mysql);
				if(SQLHelper.executeUpdate(mysql)==1) {
					JOptionPane.showMessageDialog(null, "���³ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				    String mysql2="select*from visitor where sfloor='"+hfloor+"'"; 
				    updateTable(mysql2);
				}
				else
					JOptionPane.showMessageDialog(null, "����ʧ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);	
			}
		}
		
		if(e.getSource()==jb5) {
			tf7.setText(null);
			tf8.setText(null);
			tf7.setEditable(true);
			tf8.setEditable(true);
		}
		
	}
		public void tableRowSelected() {
		t.setSelectionBackground(Color.BLUE);
		t.setSelectionForeground(Color.white);
		int row = t.getSelectedRow();
		int colCount = t.getColumnCount();
		System.out.println(row + "," + colCount);
		tf7.setText(t.getModel().getValueAt(row, 0).toString());
		tf8.setText(t.getModel().getValueAt(row, 6).toString());
		tf7.setEditable(false);
		tf8.setEditable(false);
	}
	class mouse extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == t)
				tableRowSelected();
		}
	}

}
