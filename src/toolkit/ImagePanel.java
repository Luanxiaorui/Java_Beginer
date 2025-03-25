package toolkit;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class ImagePanel extends JPanel{
    
    private Image image = null;  
   
     public ImagePanel(Image image) {  
         this.image = image;  
     }  
  
    // �̶�����ͼƬ���������JPanel������ͼƬ������������  
    protected void paintComponent(Graphics g) {  
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
    } 
}
