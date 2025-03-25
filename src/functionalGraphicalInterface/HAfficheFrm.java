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
	
	//JLabel l1=new JLabel("发布者工号");
	JLabel l2=new JLabel("公告编号");
	JLabel l3=new JLabel("历史公告");
	
	//JTextField tf1=new JTextField();
	JTextField tf2=new JTextField();
	
	JTextArea text1=new JTextArea(),
			  text2=new JTextArea();
	
	JButton jb1=new JButton("发布");
	JButton jb2=new JButton("删除");
	JButton jb3=new JButton("清空");
	
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
		//l1.setFont(new Font("宋体", Font.PLAIN, 16));
		l2.setFont(new Font("宋体", Font.PLAIN, 16));
		
		p3.add(jb1);
		p3.add(jb2);
		p3.add(jb3);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		
		p2.add(p1,BorderLayout.NORTH);
		p2.add(new JScrollPane(text1),BorderLayout.CENTER);
		text1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		p2.add(p3,BorderLayout.SOUTH);
		
		p4.add(l3,BorderLayout.NORTH);
		l3.setFont(new Font("黑体", Font.PLAIN, 36));
		p4.add(new JScrollPane(text2),BorderLayout.CENTER);
		text2.setFont(new Font("微软雅黑", Font.PLAIN, 16));

		setLayout(new BorderLayout());
		updateTextArea();
			
		JSplitPane i = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,p2,p4);
	    i.setDividerLocation(300);
		add(i,BorderLayout.CENTER);
		setVisible(true);
		setBounds(100,100,800,500);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
			
	}
	
	
////////////////////从数据库读入输出到文本区域,更新面板
	public void updateTextArea() {

		ArrayList<Affiche> aff=AfficheDao.studentQueryAll(hfloor);
		p4.removeAll();
		int k=0;
		while(k<aff.size())
		{
			Affiche af=aff.get(k);
			//String str=af.getinformation();
		text2.append("\n\n\n/////////////////////////////////\n\n\n");
		text2.append("    公告编号:"+af.getid()+"\n");
		text2.append("    公告内容:\n     "+af.getinformation()+"\n");
		text2.append("\n\n\t"+af.gethfloor()+"\n");
		text2.append("\t"+"  发布日期:"+af.getsin());
		k++;
		}
		p4.add(l3,BorderLayout.NORTH);
		l3.setFont(new Font("黑体", Font.PLAIN, 36));
		p4.add(new JScrollPane(text2),BorderLayout.CENTER);
		text2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		text2.setEditable(false);
	}

	//////////////////////
public void actionPerformed(ActionEvent e) {
	
	int i = calendar.get(Calendar.MONTH) + 1;
	String c = calendar.get(Calendar.YEAR) + "-" + i + "-" + calendar.get(Calendar.DATE);

	////////////发布公告
	if(e.getSource()==jb1) {

      if(!text1.getText().equals("")) {
			if(JOptionPane.showConfirmDialog(null, "确认信息无误，并且发布吗?", "提示",JOptionPane.OK_CANCEL_OPTION)==0)
			{
				String mysql="insert into affiche values('"+hnumber+"','"+hfloor+"','"+c+"','"+text1.getText()+"',"+"null)";
				if(SQLHelper.executeUpdate(mysql)==1) {
					JOptionPane.showMessageDialog(null, "发布成功", "提示", JOptionPane.PLAIN_MESSAGE);
					updateTextArea();
				}
				else
					JOptionPane.showMessageDialog(null, "发布失败", "提示", JOptionPane.PLAIN_MESSAGE);
			}
			SQLHelper.closeConnection();      
			}
      else
			JOptionPane.showMessageDialog(null, "请先编辑公告", "提示", JOptionPane.PLAIN_MESSAGE);

	}
	
	///////////删除公告
	if(e.getSource()==jb2) {
		if(!tf2.getText().equals("")) {
			if(QueryExistDao.QueryExist("select*from affiche where id='"+tf2.getText()+"'")) {
				String mysql="delete from affiche where id='"+tf2.getText()+"'";
				if(SQLHelper.executeUpdate(mysql)==1) {
					JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
					text2.setText(null);
			         updateTextArea();
						}
				else
					JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.PLAIN_MESSAGE);	
				SQLHelper.closeConnection(); 
			}
			else
				JOptionPane.showMessageDialog(null, "该公告不存在", "提示", JOptionPane.PLAIN_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null, "请先输入公告编号", "提示", JOptionPane.PLAIN_MESSAGE);

	}
	///////////清空text区域
	if(e.getSource()==jb3) {
		text1.setText(null);
	}
}

	public static void main(String args[]) {
		new HAfficheFrm("一号楼","001");
	}





































//
//JTree tree;
//JTextArea text1,text2;
//JButton jb1=new JButton("新建");
//JButton jb2=new JButton("发布");
//JButton jb3=new JButton("删除");
//JButton jb4=new JButton("清空");
//JButton jb5=new JButton("更新");
//
//JTextField tf1=new JTextField(10);
//JLabel l1=new JLabel("发布人工号");
//
//JPanel p1=new JPanel(new BorderLayout());
//JPanel p2=new JPanel(),p3=new JPanel();
//
//HAfficheFrm(){
//	
//	DefaultMutableTreeNode root=new DefaultMutableTreeNode("公告");
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
//		text1.append("公告编号:"+s.getid()+"\n"+"公告内容:"+s.getinformation()+"\n");
//	}
//	else
//		text1.setText(null);	
//}
//public void actionPerformed(ActionEvent e) {
/////////新建节点，编辑公告. 
//	Affiche s=new Affiche();
//	if(e.getSource()==jb1) {
//		
//		if(tree.getSelectionPath()!=null) {
//		DefaultMutableTreeNode selectedNode =(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//		 String nodeName = JOptionPane.showInputDialog("请输入节点名称："); 
//		 s.setname(nodeName);
//		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(s);
//		 selectedNode.add(newNode); 
//		((DefaultTreeModel) tree.getModel()).nodeStructureChanged(selectedNode);
//		}
//	}
////////发布公告
//	if(e.getSource()==jb2) {
//		
//		/////公告存入数据库
//		
//		
//	}
//////////删除公告
//	if(e.getSource()==jb3) {
//		deleteSelectedNode();
//		//////在数据库中删除
//		
//	}
/////////清空文本区域
//	if(e.getSource()==jb4) {
//		text1.setText(null);
//	}
//////////更新公告
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
