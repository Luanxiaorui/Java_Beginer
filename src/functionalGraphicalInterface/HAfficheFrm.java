package functionalGraphicalInterface;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.tree.*;

import dao.AfficheDao;
import dao.HouseParentDao;
import dao.QueryExistDao;

import javax.swing.event.*;
import subjectObject.Affiche;
import toolkit.SQLHelper;

public class HAfficheFrm extends JPanel implements ActionListener{
	
	//JLabel l1=new JLabel("�����߹���");
	JLabel l2=new JLabel("������");
	JLabel l3=new JLabel("��ʷ����");
	
	//JTextField tf1=new JTextField();
	JTextField tf2=new JTextField();
	
	JTextArea text1=new JTextArea(),
			  text2=new JTextArea();
	
	JButton jb1=new JButton("����");
	JButton jb2=new JButton("ɾ��");
	JButton jb3=new JButton("���");
	
	JPanel p1=new JPanel(new GridLayout(1,1)),
		    p2=new JPanel(new BorderLayout()),
			p3=new JPanel(),
			p4=new JPanel(new BorderLayout());
	
	String hfloor,hnumber;
	Calendar calendar = Calendar.getInstance();
	HAfficheFrm(String hfloor,String number){
		
	
		this.hfloor=hfloor;
		this.hnumber=number;
		p1.add(l2);
		p1.add(tf2);
		//l1.setFont(new Font("����", Font.PLAIN, 16));
		l2.setFont(new Font("����", Font.PLAIN, 16));
		
		p3.add(jb1);
		p3.add(jb2);
		p3.add(jb3);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		
		p2.add(p1,BorderLayout.NORTH);
		p2.add(new JScrollPane(text1),BorderLayout.CENTER);
		text1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		p2.add(p3,BorderLayout.SOUTH);
		
		p4.add(l3,BorderLayout.NORTH);
		l3.setFont(new Font("����", Font.PLAIN, 36));
		p4.add(new JScrollPane(text2),BorderLayout.CENTER);
		text2.setFont(new Font("΢���ź�", Font.PLAIN, 16));

		setLayout(new BorderLayout());
		updateTextArea();
			
		JSplitPane i = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,p2,p4);
	    i.setDividerLocation(300);
		add(i,BorderLayout.CENTER);
		setVisible(true);
		setBounds(100,100,800,500);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
			
	}
	
	
////////////////////�����ݿ����������ı�����,�������
	public void updateTextArea() {

		ArrayList<Affiche> aff=AfficheDao.studentQueryAll(hfloor);
		p4.removeAll();
		int k=0;
		while(k<aff.size())
		{
			Affiche af=aff.get(k);
			//String str=af.getinformation();
		text2.append("\n\n\n/////////////////////////////////\n\n\n");
		text2.append("    ������:"+af.getid()+"\n");
		text2.append("    ��������:\n     "+af.getinformation()+"\n");
		text2.append("\n\n\t"+af.gethfloor()+"\n");
		text2.append("\t"+"  ��������:"+af.getsin());
		k++;
		}
		p4.add(l3,BorderLayout.NORTH);
		l3.setFont(new Font("����", Font.PLAIN, 36));
		p4.add(new JScrollPane(text2),BorderLayout.CENTER);
		text2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		text2.setEditable(false);
	}

	//////////////////////
public void actionPerformed(ActionEvent e) {
	
	int i = calendar.get(Calendar.MONTH) + 1;
	String c = calendar.get(Calendar.YEAR) + "-" + i + "-" + calendar.get(Calendar.DATE);

	////////////��������
	if(e.getSource()==jb1) {

      if(!text1.getText().equals("")) {
			if(JOptionPane.showConfirmDialog(null, "ȷ����Ϣ���󣬲��ҷ�����?", "��ʾ",JOptionPane.OK_CANCEL_OPTION)==0)
			{
				String mysql="insert into affiche values('"+hnumber+"','"+hfloor+"','"+c+"','"+text1.getText()+"',"+"null)";
				if(SQLHelper.executeUpdate(mysql)==1) {
					JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					updateTextArea();
				}
				else
					JOptionPane.showMessageDialog(null, "����ʧ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
			}
			SQLHelper.closeConnection();      
			}
      else
			JOptionPane.showMessageDialog(null, "���ȱ༭����", "��ʾ", JOptionPane.PLAIN_MESSAGE);

	}
	
	///////////ɾ������
	if(e.getSource()==jb2) {
		if(!tf2.getText().equals("")) {
			if(QueryExistDao.QueryExist("select*from affiche where id='"+tf2.getText()+"'")) {
				String mysql="delete from affiche where id='"+tf2.getText()+"'";
				if(SQLHelper.executeUpdate(mysql)==1) {
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					text2.setText(null);
			         updateTextArea();
						}
				else
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);	
				SQLHelper.closeConnection(); 
			}
			else
				JOptionPane.showMessageDialog(null, "�ù��治����", "��ʾ", JOptionPane.PLAIN_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null, "�������빫����", "��ʾ", JOptionPane.PLAIN_MESSAGE);

	}
	///////////���text����
	if(e.getSource()==jb3) {
		text1.setText(null);
	}
}

	public static void main(String args[]) {
		new HAfficheFrm("һ��¥","001");
	}





































//
//JTree tree;
//JTextArea text1,text2;
//JButton jb1=new JButton("�½�");
//JButton jb2=new JButton("����");
//JButton jb3=new JButton("ɾ��");
//JButton jb4=new JButton("���");
//JButton jb5=new JButton("����");
//
//JTextField tf1=new JTextField(10);
//JLabel l1=new JLabel("�����˹���");
//
//JPanel p1=new JPanel(new BorderLayout());
//JPanel p2=new JPanel(),p3=new JPanel();
//
//HAfficheFrm(){
//	
//	DefaultMutableTreeNode root=new DefaultMutableTreeNode("����");
//	DefaultMutableTreeNode node=new DefaultMutableTreeNode("2022-6-20");
//  root.add(node);
//  tree=new JTree(root);
//  tree.addTreeSelectionListener(this);
//  
//  text1=new JTextArea();
//  
//  p2.add(jb1);p2.add(jb2);
//  p2.add(jb3);p2.add(jb4);
//  p2.add(jb5);
//  
//  p3.add(l1);p3.add(tf1);
//  
//  jb1.addActionListener(this);
//  jb2.addActionListener(this);
//  jb3.addActionListener(this);
//  jb4.addActionListener(this);
//  
//  p1.add(new JScrollPane(text1),BorderLayout.CENTER);
//  p1.add(p2,BorderLayout.SOUTH);
//  p1.add(p3,BorderLayout.NORTH);
//  
//	JSplitPane i = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tree),p1 );
//	i.setDividerLocation(180);
//	add(i);
//	setVisible(true);
//	setBounds(100,100,800,500);
//	validate();
//	
//}
//
//	
//public void valueChanged(TreeSelectionEvent e) {
//	DefaultMutableTreeNode node=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
//	if(node.isLeaf()) {
//		Affiche s=(Affiche)node.getUserObject();
//		text1.append("������:"+s.getid()+"\n"+"��������:"+s.getinformation()+"\n");
//	}
//	else
//		text1.setText(null);	
//}
//public void actionPerformed(ActionEvent e) {
/////////�½��ڵ㣬�༭����. 
//	Affiche s=new Affiche();
//	if(e.getSource()==jb1) {
//		
//		if(tree.getSelectionPath()!=null) {
//		DefaultMutableTreeNode selectedNode =(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//		 String nodeName = JOptionPane.showInputDialog("������ڵ����ƣ�"); 
//		 s.setname(nodeName);
//		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(s);
//		 selectedNode.add(newNode); 
//		((DefaultTreeModel) tree.getModel()).nodeStructureChanged(selectedNode);
//		}
//	}
////////��������
//	if(e.getSource()==jb2) {
//		
//		/////����������ݿ�
//		
//		
//	}
//////////ɾ������
//	if(e.getSource()==jb3) {
//		deleteSelectedNode();
//		//////�����ݿ���ɾ��
//		
//	}
/////////����ı�����
//	if(e.getSource()==jb4) {
//		text1.setText(null);
//	}
//////////���¹���
//	if(e.getSource()==jb5) {
//		
//	}
//}
//private void deleteSelectedNode() 
//{
//	 DefaultMutableTreeNode selectedNode =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent(); 
//	 DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent(); 
//	 selectedNode.removeFromParent();
//	 ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(parentNode);
//}
}
