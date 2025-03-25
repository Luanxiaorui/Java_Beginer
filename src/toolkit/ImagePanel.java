package toolkit;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class ImagePanel extends JPanel{
    
    private Image image = null;  
   
     public ImagePanel(Image image) {  
         this.image = image;  
     }  
  
    // 固定背景图片，允许这个JPanel可以在图片上添加其他组件  
    protected void paintComponent(Graphics g) {  
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
    } 
}
