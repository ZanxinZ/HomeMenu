import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ImageWin extends JFrame {
	public ImageWin(JButton btn,Win lastWin) {
		// TODO Auto-generated constructor stub
		this.setVisible(true);
		setSize(400,300);
		setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		ImageWin tempWin=this;
		JPanel panel=new JPanel(new FlowLayout());
		JLabel label=new JLabel();
		Container container=getContentPane();
		int i=new Integer(btn.getName())/lastWin.gridCol,j=new Integer(btn.getName())%lastWin.gridCol;
		String fileName="pic/"+i+"/"+lastWin.menuBlockLabels[i][j]+".jpg";
		//System.out.println(fileName);
		
		File input = new File(fileName);
		ImageIcon myIcon=new ImageIcon(fileName);
		
		label.setIcon(myIcon);
		panel.add(label);
		container.add(panel);
		
		label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tempWin.dispose();
			}
		});
	}
	
}
