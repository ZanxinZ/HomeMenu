import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MyImageIcon {
	Image im=null;
	ImageIcon imIcon=null;
	boolean isEmpty=false;
	public MyImageIcon(String filename){
		im=new ImageIcon(filename).getImage();
		imIcon=new ImageIcon(im){
			@Override
			public synchronized void paintIcon(java.awt.Component cmp, Graphics g, int x, int y) {
				g.drawImage(getImage(), 0, 0, cmp.getWidth(), cmp.getHeight(), cmp);
			}
		};
	}
	public Icon getIcon(){
		return this.imIcon;
	}
	

}
