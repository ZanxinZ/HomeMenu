import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//设置菜品的子窗口
public class ChooseWin extends JFrame{
	private static int buttonCount=10;
	private static int gridRow=2,gridCol=5;
	ChooseWin tempWin=this;
	Win lastWin;
	JButton lastBtn;
	
	private int currentRow=-1;//当前选中按钮所在行
	
	Container container=getContentPane();
	JButton []chooseBtn=new JButton [buttonCount];//按钮总数10
	private int []btnLabel=new int [buttonCount];
	
	
	public ChooseWin(JButton btn,Win lastWin) {
		// TODO Auto-generated constructor stub
		setTitle("修改菜品");
		JPanel panel=new JPanel(new GridLayout(gridRow,gridCol));//窗口画板
		System.out.println("now enter button :"+btn.getName());
		this.lastWin=lastWin;
		this.lastBtn=btn;
		this.currentRow=new Integer(btn.getName())/(lastWin.gridCol);
		
		Dimension d=new Dimension();
		d.setSize(800,300);
        setSize(d);        //设置组件大小
        setBackground(Color.WHITE);   //背景色设置为白色
        Point p=new Point(400,100);    //指定组件显示位置
		setLocation(p);     //设dispatchEvent();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//添加关闭的监听
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(tempWin, "不做修改？", "返回", JOptionPane.WARNING_MESSAGE)==0){
					lastWin.setEnabled(true);
					dispose();
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//初始化按钮
		for (int i = 0; i < buttonCount; i++) {
			chooseBtn[i]=new JButton();	
			chooseBtn[i].setName(new Integer(i).toString());
			addListener(chooseBtn[i]);
			panel.add(chooseBtn[i]);
			btnLabel[i]=i;
		}
		showPic();
		container.add(panel);
		setVisible(true);
		
	}
	private void showPic(){
		String fileName="pic/"+new Integer(currentRow).toString()+"/";
		//System.out.println(fileName);
		for (int i = 0; i < 10; i++) {
			MyImageIcon myIcon=new MyImageIcon(fileName+i+".jpg");
			File file=new File(fileName+i+".jpg");
			if(!file.exists()){
				//System.out.println(fileName+i+".jpg");
				myIcon=new MyImageIcon(fileName+(-2)+".jpg");
				btnLabel[i]=-2;
			}
			chooseBtn[i].setIcon(myIcon.getIcon());
			
		}
		System.out.println("chooseWin show");
	}
	private void addListener(JButton chooseBtn){
		chooseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lastWin.setEnabled(true);
				System.out.println("after change:"+lastBtn.getName()+"->"+btnLabel[new Integer(chooseBtn.getName())]);//选中要修改的菜
				lastWin.menuBlockLabels[new Integer(lastBtn.getName())/lastWin.gridCol][new Integer(lastBtn.getName())%lastWin.gridCol]=btnLabel[new Integer(chooseBtn.getName())];
				lastWin.updateImage(new Integer(lastBtn.getName()));
				lastWin.saveData();
				dispose();//关闭当前窗口
				
			}
		});
	}
	
}
