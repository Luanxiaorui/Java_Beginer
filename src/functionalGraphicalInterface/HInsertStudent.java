package functionalGraphicalInterface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import dao.HouseParentDao;
import dao.QueryExistDao;
import toolkit.SQLHelper;

import java.awt.event.*;
import java.util.Calendar;

public class HInsertStudent extends JPanel implements ActionListener, ItemListener {

	Border titleBorder1 = BorderFactory.createTitledBorder("������ס");
	Border titleBorder2 = BorderFactory.createTitledBorder(" ������ס");

	JLabel l1 = new JLabel("ѧ��");
	JLabel l2 = new JLabel("�Ա�");
	JLabel l3 = new JLabel("Ժϵ");
	JLabel l4 = new JLabel("רҵ");
	JLabel l5 = new JLabel("����");
	JLabel l6 = new JLabel("�ֻ�����");
	JLabel l7 = new JLabel("����");
	JLabel l8 = new JLabel("��λ");
	JLabel l9 = new JLabel("��סʱ��");
	JLabel l0 = new JLabel("�꼶");

	JLabel l_1 = new JLabel("ѧ��");
	JLabel l_2 = new JLabel("����");
	JLabel l_3 = new JLabel("��סʱ��");
	JLabel l_4 = new JLabel("����λ��");

	JTextField tf4 = new JTextField();
	JTextField tf7 = new JTextField();
	// JTextField tf8 = new JTextField();

	JComboBox cb5 = new JComboBox();
	JComboBox cb6 = new JComboBox();
	JComboBox cb7 = new JComboBox();

	JComboBox cb8 = new JComboBox();
	JComboBox cb9 = new JComboBox();
	JComboBox cb0 = new JComboBox();

	JTextField tf9 = new JTextField();

	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf5 = new JTextField();

	JMenuBar bar = new JMenuBar();
	JMenu jm1 = new JMenu("File"), jm2 = new JMenu("Edit");
	JMenuItem item1 = new JMenuItem("New"), item2 = new JMenuItem("Open"), item3 = new JMenuItem("Copy"),
			item4 = new JMenuItem("Cut");

	JComboBox<String> cb1 = new JComboBox<String>();
	JComboBox<String> cb2 = new JComboBox<String>();
	JComboBox<String> cb3 = new JComboBox<String>();
	JComboBox cb4 = new JComboBox();

	JButton jb1 = new JButton("���浽���ݿ�"), jb2 = new JButton("���"), jb3 = new JButton("���浽���ݿ�"), jb4 = new JButton("���");

	JPanel p3 = new JPanel(new GridLayout(5, 2)), p4 = new JPanel(new GridLayout(4, 2)),
			p5 = new JPanel(new BorderLayout()), p6 = new JPanel(new BorderLayout()),
			p7 = new JPanel(new GridLayout(4, 2)), p8 = new JPanel(new GridLayout(2, 1)), p9 = new JPanel(),
			p10 = new JPanel(), p1 = new JPanel(new GridLayout(1, 3)), p2 = new JPanel(new GridLayout(1, 3));

	Calendar calendar = Calendar.getInstance();

	String hfloor;
	HInsertStudent(String hfloor) {

		this.hfloor=hfloor;
		bar.add(jm1);
		bar.add(jm2);
		add(bar, BorderLayout.NORTH);

		jm1.add(item1);
		item1.addActionListener(this);
		item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		jm1.addSeparator();
		jm1.add(item2);
		item2.addActionListener(this);

		jm2.add(item3);
		item3.addActionListener(this);
		jm2.addSeparator();
		jm2.add(item4);
		item4.addActionListener(this);

		cb1.addItem("��ѡ��ѧԺ");
		cb1.addItem("�������ѧ�뼼��ѧԺ");
		cb1.addItem("����ѧԺ");
		cb1.addItem("��������ѧԺ");
		cb1.addItemListener(this);

		cb2.addItem("����ѡ��ѧԺ");
		cb3.addItem("��һ");
		cb3.addItem("���");
		cb3.addItem("����");
		cb3.addItem("����");

		cb4.addItem("��");
		cb4.addItem("Ů");

		date(p1, cb5, cb6, cb7);
		date(p2, cb8, cb9, cb0);

		p3.add(l1);
		p3.add(tf1);
		p3.add(l2);
		p3.add(cb4);
		p3.add(l5);
		p3.add(tf2);
		p3.add(l3);
		p3.add(cb1);
		p3.add(l4);
		p3.add(cb2);

		p4.add(l6);
		p4.add(tf3);
		p4.add(l8);
		p4.add(tf5);
		p4.add(l9);
		p4.add(p2);
		p4.add(l0);
		p4.add(cb3);

		p5.setBorder(titleBorder1);

		p8.add(p3);
		p8.add(p4);

		p9.add(jb1);
		jb1.addActionListener(this);
		p9.add(jb2);
		jb2.addActionListener(this);

		p7.setBorder(titleBorder2);
		p7.add(l_1);
		p7.add(tf4);
		p7.add(l_2);
		p7.add(tf7);
		p7.add(l_3);
		p7.add(p1);
		p7.add(l_4);
		p7.add(tf9);

		jb3.addActionListener(this);
		jb4.addActionListener(this);

		p5.add(p8, BorderLayout.CENTER);
		p5.add(p9, BorderLayout.SOUTH);
		p10.add(jb3);
		p10.add(jb4);
		p6.add(p7, BorderLayout.CENTER);
		p6.add(p10, BorderLayout.SOUTH);
		setLayout(new BorderLayout());

		JSplitPane i = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, p5, p6);
		add(i, BorderLayout.CENTER);
		validate();
		setVisible(true);
		//setTitle(title);
		setBounds(500, 300, 800, 600);
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

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jb3) {

			String c = "'" + cb5.getSelectedItem().toString() + "-" + cb6.getSelectedItem().toString() + "-"
					+ cb7.getSelectedItem().toString() + "'";

			if (tf4.getText().equals("") || tf7.getText().equals("") || tf9.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "��Ϣ������", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else if (!QueryExistDao.QueryExist("select*from student where no='" + tf4.getText() + "'")) {
				JOptionPane.showMessageDialog(null, "ѧ�Ų�����", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				if (JOptionPane.showConfirmDialog(null, "ȷ����Ϣ������", "��ʾ", JOptionPane.YES_NO_OPTION) == 0) {
					String mysql = "update student set sfloor='" + hfloor + "'," + "sbed='" + tf9.getText()
							+ "'," + "sin=" + c + " where no='" + tf4.getText() + "'";
					System.out.println(mysql);
					if (SQLHelper.executeUpdate(mysql) == 1) {
						JOptionPane.showMessageDialog(null, "��Ϣ�Ǽǳɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "��Ϣ�Ǽ�ʧ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		if (e.getSource() == jb4) {
			if (JOptionPane.showConfirmDialog(null, "ȷ�������?", "��ʾ", JOptionPane.YES_NO_OPTION) == 0) {
				tf4.setText(null);
				tf7.setText(null);
				tf9.setText(null);
				cb5.setSelectedItem(calendar.get(Calendar.YEAR));
				cb6.setSelectedItem(calendar.get(Calendar.MONTH) + 1);
				cb7.setSelectedItem(calendar.get(Calendar.DATE));
			}
		}

		if (e.getSource() == jb2) {
			int i = JOptionPane.showConfirmDialog(null, "ȷ�������?", "��ʾ", JOptionPane.YES_NO_OPTION);
			if (i == 0) {
				tf1.setText(null);
				tf2.setText(null);
				tf3.setText(null);
				tf5.setText(null);
				cb1.setSelectedItem("��ѡ��ѧԺ");
				cb2.removeAllItems();
				cb2.addItem("����ѡ��ѧԺ");
				cb2.setSelectedItem("����ѡ��ѧԺ");
				cb8.setSelectedItem(calendar.get(Calendar.YEAR));
				cb9.setSelectedItem(calendar.get(Calendar.MONTH) + 1);
				cb0.setSelectedItem(calendar.get(Calendar.DATE));
			}
		}
		if (e.getSource() == jb1) {

			String c = "'" + cb8.getSelectedItem().toString() + "-" + cb9.getSelectedItem().toString() + "-"
					+ cb0.getSelectedItem().toString() + "'";
			
			if (tf1.getText().equals("")  || tf2.getText().equals("")
					|| tf3.getText().equals("") || tf5.getText().equals("")
					|| cb1.getSelectedItem().toString().equals("��ѡ��ѧԺ")
					|| cb2.getSelectedItem().toString().equals("��ѡ��רҵ")) {
				JOptionPane.showMessageDialog(null, "��Ϣ������", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else if (tf3.getText().trim().length() != 11) {
				JOptionPane.showMessageDialog(null, "�ֻ�������Ϣ����", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else if (QueryExistDao.QueryExist("select*from student where no='" + tf1.getText() + "'")) {

				JOptionPane.showMessageDialog(null, "ѧ���Ѿ�����", "��ʾ", JOptionPane.WARNING_MESSAGE);

			} else {
				if (JOptionPane.showConfirmDialog(null, "ȷ����Ϣ������", "��ʾ", JOptionPane.YES_NO_OPTION) == 0) {
					String mysql = "insert into student values('" + tf1.getText() + "','" + tf2.getText() + "','"
							+ cb4.getSelectedItem().toString() + "','" + cb1.getSelectedItem().toString() + "','"
							+ cb2.getSelectedItem().toString() + "','" + tf5.getText() + "','" + tf3.getText() + "','"
							+ hfloor + "','123456','" + cb3.getSelectedItem().toString() + "',"
							+ c + ",null)";
					if (SQLHelper.executeUpdate(mysql) == 1) {
						JOptionPane.showMessageDialog(null, "��Ϣ�Ǽǳɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "��Ϣ�Ǽ�ʧ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}

	public void itemStateChanged(ItemEvent ee) {

		if (ee.getSource() == cb1) {
			if (cb1.getSelectedItem().toString().equals("�������ѧ�뼼��ѧԺ")) {
				cb2.removeAllItems();
				cb2.addItem("��ѡ��רҵ");
				cb2.addItem("�������");
				cb2.addItem("��Ϣ��ȫ");
				cb2.addItem("�������ѧ�뼼��");
				cb2.addItem("���繤��");
				cb2.setSelectedItem("��ѡ��רҵ");
			}
			if (cb1.getSelectedItem().toString().equals("����ѧԺ")) {
				cb2.removeAllItems();
				cb2.addItem("��ѡ��רҵ");
				cb2.addItem("��Ϣ������ѧ");
				cb2.addItem("Ӧ������ѧ");
				cb2.setSelectedItem("��ѡ��רҵ");
			}
			if (cb1.getSelectedItem().toString().equals("��������ѧԺ")) {
				cb2.removeAllItems();
				cb2.addItem("��ѡ��רҵ");
				cb2.addItem("�������̼����Զ���");
				cb2.setSelectedItem("��ѡ��רҵ");
			}
		}
	}
}
