/*package toolkit;

import java.awt.Insets;

import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;

//import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class BeautyUI {
	public  static void beautyUI(){
	    try
	    {
	        //��ν���л����뷨���°���������
	        System.setProperty("sun.java2d.noddraw", "true");
	        //�������Լ��ɣ�true��ʾʹ��ToolBar.background��ɫʵ�ִ�
	        //ɫ��䱳����BeautyEye�д�����Ĭ����false
	        UIManager.put("ToolBar.isPaintPlainBackground", Boolean.TRUE);
	        //�Զ���JToolBar ui��border
	        Border bd = new org.jb2011.lnf.beautyeye.ch8_toolbar.BEToolBarUI.ToolBarBorder(
	                UIManager.getColor("ToolBar.shadow")     //Floatableʱ�������ɫ
	                , UIManager.getColor("ToolBar.highlight")//Floatableʱ�������Ӱ��ɫ
	                , new Insets(0, 0, 0, 0));              //border��Ĭ��insets
	        UIManager.put("ToolBar.border",new BorderUIResource(bd));
	        //JTabbedPane������
	        //�ı�InsetsUIResource������ֵ����ʵ��
	        UIManager.put("TabbedPane.tabAreaInsets"
	                , new javax.swing.plaf.InsetsUIResource(0,0,0,0));
	        //���ñ����Խ��ı䴰�ڱ߿���ʽ����
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	        UIManager.put("RootPane.setupButtonVisible", false);
	        BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
	}
}
*/