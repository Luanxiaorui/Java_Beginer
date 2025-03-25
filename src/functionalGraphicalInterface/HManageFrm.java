package functionalGraphicalInterface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import dao.HouseParentDao;
import dao.QueryExistDao;
import toolkit.SQLHelper;

import java.awt.event.*;

public class HManageFrm extends JPanel implements ActionListener, ItemListener {

	JLabel l1 = new JLabel("ѧ��");
	JLabel l2 = new JLabel("����");
	JLabel l3 = new JLabel("�Ա�");
	JLabel l4 = new JLabel("ѧԺ");
	JLabel l5 = new JLabel("רҵ");
	JLabel l6 = new JLabel("�������ƺ�");
	JLabel l7 = new JLabel("�绰");
	JLabel l8 = new JLabel("�꼶");

	JTextField tf1 = new JTextField(11);
	JTextField tf2 = new JTextField(11);
	JTextField tf3 = new JTextField(11);

	JComboBox<String> cb1 = new JComboBox<String>();
	JComboBox<String> cb2 = new JComboBox<String>();

	JTextField tf6 = new JTextField(11);
	JTextField tf7 = new JTextField(11);
	JTextField tf8 = new JTextField(11);

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	JButton jb1 = new JButton("ɾ��");
	JButton jb2 = new JButton("���");
	JButton jb3 = new JButton("��ѯ");
	JButton jb4 = new JButton("����");
	JButton jb5 = new JButton("����");

	JTable t;
	Object[][] row;
	Object[] colum = { "ѧ��", "����", "�Ա�", "ѧԺ", "רҵ", "�������ƺ�", "�绰", "�꼶" };
    
	String hfloor;
	
	HManageFrm(String title) {

		this.hfloor=title;
		
		cb1.addItem("��ѡ��ѧԺ");
		cb1.addItem("�������ѧ�뼼��ѧԺ");
		cb1.addItem("����ѧԺ");
		cb1.addItem("��������ѧԺ");
		cb1.addItemListener(this);

		cb2.addItem("��ѡ��רҵ");

		p1.setBorder(new TitledBorder("ѧ����Ϣ"));
		p1.add(l1);
		p1.add(tf1);
		p1.add(l2);
		p1.add(tf2);
		p1.add(l3);
		p1.add(tf3);
		p1.add(l4);
		p1.add(cb1);
		p1.add(l5);
		p1.add(cb2);
		p1.add(l6);
		p1.add(tf6);
		p1.add(l7);
		p1.add(tf7);
		p1.add(l8);
		p1.add(tf8);
        setLayout(new BorderLayout());
		tf1.addActionListener(this);

		String sql2 = "select*from student where sfloor='" + hfloor + "'";
		updateTable(sql2, true);

		p2.add(jb1);
		p2.add(jb3);
		p2.add(jb2);
		p2.add(jb4);
		p2.add(jb5);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);

		
		setVisible(true);
		setBounds(300, 300, 1500, 500);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// ѡ�б�ɫ
	public void tableRowSelected() {
		t.setSelectionBackground(Color.BLUE);
		t.setSelectionForeground(Color.white);
		int row = t.getSelectedRow();
		int colCount = t.getColumnCount();

		String[] data = new String[colCount];
		System.out.println(row + "," + colCount);
		for (int i = 0; i < colCount; i++)
			data[i] = t.getModel().getValueAt(row, i).toString();

		tf1.setText(data[0]);
		tf2.setText(data[1]);
		tf3.setText(data[2]);
		cb1.setSelectedItem(data[3]);
		cb2.setSelectedItem(data[4]);
		tf6.setText(data[5]);
		tf7.setText(data[6]);
		tf8.setText(data[7]);
		tf1.setEditable(false);
	}

	// �������
	public void updateTable(String sql, boolean flag) {
		row = HouseParentDao.executeQueryArray(sql);
		t = new JTable(row, colum);
		this.removeAll();
		add(new JScrollPane(t), BorderLayout.CENTER);
		t.addMouseListener(new mouse());
		delete();
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH);
		validate();
	}

	// ����¼�����
	class mouse extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == t)
				tableRowSelected();
		}
	}

	// ��ťActionEvent�¼���������
	public void actionPerformed(ActionEvent e) {
		
		// ����ɾ��
		if (e.getSource() == jb1) {
			String mysql = "delete from student where no='" + tf1.getText() + "'";
			if (SQLHelper.executeUpdate(mysql) == 1) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				String sql2 = "select*from student where sfloor='" + hfloor + "'";
				SQLHelper.closeConnection();
				updateTable(sql2, true);
			} else {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		// ��������ı���
		if (e.getSource() == jb2) {
			delete();
		}

		// ������ѯ
		if (e.getSource() == jb3) {
			
				String a = "'" + tf3.getText() + "'", // �Ա�
					b, // ѧԺ
					c = "'" + tf8.getText() + "'", // �꼶
					d, f = "'" + hfloor + "'",
					mysql;// רҵ
				if (tf1.getText().equals("")  && tf2.getText().equals("")
					&& tf3.getText().equals("")&& tf6.getText().equals("")
					&& cb1.getSelectedItem().toString().equals("��ѡ��Ժϵ")&& tf7.getText().equals("")
					&& tf8.getText().equals("")) {
				}
			else {
			if (tf3.getText().equals(""))
				a = "ANY(SELECT ssex from student)";
			if (tf8.getText().equals(""))
				c = "ANY(select sgrade from student)";
			if (cb1.getSelectedItem().toString().equals("��ѡ��ѧԺ"))
				b = "ANY(select sfaculty from student)";
			else
				b = "'" + cb1.getSelectedItem().toString() + "'";
			if (cb2.getSelectedItem().toString().equals("��ѡ��רҵ")||cb2.getSelectedItem().toString().equals("����ѡ��ѧԺ"))
				d = "ANY(select smajor from student)";
			else
				d = "'" + cb2.getSelectedItem().toString() + "'";
			
			// ѧ�Ų�ѯ
			if (!tf1.getText().equals("")) 
				mysql="select*from student where no='" + tf1.getText() + "'";
			
			// �ֻ��Ų�ѯ
			else if (!tf7.getText().equals("")) {
				if (tf2.getText().equals(""))
					mysql= "select*from student where sphone like'" + tf7.getText() + "%'" + " and ssex=" + a
							+ " and sgrade=" + c + " and sfaculty=" + b + "and smajor=" + d;
				else
				mysql= "select*from student where sphone like'" + tf7.getText() + "%'" + " and ssex=" + a
							+ " and sgrade=" + c + " and sfaculty=" + b + " and smajor=" + d + " and sname like '%"
							+ tf2.getText() + "%'";
			}

			// ���Ҳ�ѯ
			else if (!tf6.getText().equals("")) {
				if (tf2.getText().equals(""))
					mysql = "select*from student where sbed like'" + tf6.getText() + "%'" + " and ssex=" + a
							+ " and sgrade=" + c + " and sfaculty=" + b + "and smajor=" + d + " and sfloor=" + f;
				else
					mysql = "select*from student where sbed like'" + tf6.getText() + "%'" + " and ssex=" + a
							+ " and sgrade=" + c + " and sfaculty=" + b + " and smajor=" + d + " and sname like '%"
							+ tf2.getText() + "%'" + " and sfloor=" + f;
			}
			
			// ���ֲ�ѯ
			else if (!tf2.getText().equals("")) 
				mysql = "select*from student where  ssex=" + a + " and sgrade=" + c + " and sfaculty=" + b
						+ " and smajor=" + d + " and sname like '%" + tf2.getText() + "%'" + " and sfloor=" + f;
			//ʣ�����
			 else 
				 mysql = "select*from student where  ssex=" + a + " and sgrade=" + c + " and sfaculty=" + b
						+ " and smajor=" + d + " and sfloor=" + f;
			//ִ��
				if (QueryExistDao.QueryExist(mysql))
					updateTable(mysql, false);
				else {
					JOptionPane.showMessageDialog(null, "������", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					delete();
				}
			}
		}
		
		// ��������
		if (e.getSource() == jb4) {
			if (tf1.getText().equals("")  || tf2.getText().equals("")
					|| tf3.getText().equals("") || tf6.getText().equals("")
					|| cb1.getSelectedItem().toString().equals("��ѡ��Ժϵ")|| tf7.getText().equals("")
					|| cb2.getSelectedItem().toString().equals("��ѡ��רҵ")|| tf6.getText().equals("")) 
				JOptionPane.showMessageDialog(null, "��Ϣ������", "��ʾ", JOptionPane.WARNING_MESSAGE);
			else if(tf7.getText().trim().length()!=11) {
				JOptionPane.showMessageDialog(null, "�绰�����ʽ����", "��ʾ", JOptionPane.WARNING_MESSAGE);
			}		
			else {
			if (HouseParentDao.studentUpdate(tf1.getText(), tf2.getText(), tf3.getText(), tf7.getText(),
					cb1.getSelectedItem().toString(), cb2.getSelectedItem().toString(), tf6.getText(),
					tf8.getText()) == 1) {
				JOptionPane.showMessageDialog(null, "���³ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				String sql2 = "select*from student where sfloor='" + hfloor + "'";
				SQLHelper.closeConnection();
				updateTable(sql2, true);
			} else {
				JOptionPane.showMessageDialog(null, "����ʧ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
		// ������ѯ
		if (e.getSource() == tf1) {
			String mysql = "select *from student where no='" + tf1.getText() + "'";
			if (!QueryExistDao.QueryExist(mysql, tf1.getText(), "no")) {
				JOptionPane.showMessageDialog(null, "������", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				delete();
			} else {
				String txt = tf1.getText().trim();
				for (int i = 0; i < row.length; i++) {
					if (row[i][0].equals(txt)) {
						t.setRowSelectionInterval(i, i);
						tableRowSelected();
						System.out.println("the selected row:" + i);
					}
				}
			}
		}

		
		if (e.getSource() == jb5) {
			String sql = "select*from student where sfloor='" + hfloor + "'";
			updateTable(sql, true);
		}
	}

	public void delete() {
		tf1.setText(null);
		tf2.setText(null);
		tf3.setText(null);
		cb1.setSelectedItem("��ѡ��ѧԺ");
		cb2.removeAllItems();
		cb2.addItem("����ѡ��ѧԺ");
		cb2.setSelectedItem("����ѡ��ѧԺ");
		tf6.setText(null);
		tf7.setText(null);
		tf8.setText(null);
	}

	public void itemStateChanged(ItemEvent ee) {
		// TODO �Զ����ɵķ������
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
