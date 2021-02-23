import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Win extends JFrame {
	private static int buttonCount=12;
	public int gridRow=3,gridCol=4;//网格尺寸
	public int [][]menuBlockLabels=new int[gridRow][gridCol];//菜单每一小块的标签，代表某一道菜
	Win tempWin=this;
	JButton []btn=new JButton [buttonCount];//按钮总数30
	//ArrayList<MyImageIcon>im=new ArrayList<MyImageIcon>();
	
	Container container=getContentPane();
	public Win() {
		// TODO Auto-generated constructor stub
		setTitle("华美烧烤体验园");
		JPanel winPanel=new JPanel(new GridLayout(gridRow,gridCol));//窗口画板
		setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH ); 
		loadRecord();
		Dimension d=new Dimension();
		d.setSize(700,500);
        setSize(d);        //设置组件大小
        setBackground(Color.WHITE);   //背景色设置为白色
        Point p=new Point(400,100);    //指定组件显示位置
		setLocation(p);     //设dispatchEvent();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWinListener();
		
		container.add(winPanel);
		//初始化按钮
		for (int i = 0; i < buttonCount; i++) {
			btn[i]=new JButton();	
			btn[i].setName(new Integer(i).toString());
			addListener(btn[i]);
			winPanel.add(btn[i]);
		}
		updateImage(-1);//载入图片
		setVisible(true);
		
	}
	//鼠标监听
	private void addListener(JButton button){
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//鼠标单击事件
				ImageWin Mywin=new ImageWin(button,tempWin);
			}
			
		});
		button.addMouseListener(new MouseListener() {
			
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
				if(e.getButton()==MouseEvent.BUTTON3){
					System.out.println("右键");
					//if(JOptionPane.showConfirmDialog(button, "菜品确认下架？", "下架", JOptionPane.WARNING_MESSAGE)==0){
					int message=JOptionPane.showConfirmDialog(null, "修改菜品(是)  菜品下架(否)", "设置", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(message==JOptionPane.NO_OPTION){
						menuBlockLabels[new Integer(button.getName())/gridCol][new Integer(button.getName())%gridCol]=-2;
						updateImage(new Integer(button.getName()));
						saveData();
					}
					else if(message==JOptionPane.YES_OPTION){
						setEnabled(false);
						ChooseWin chooseWin=new ChooseWin(button,tempWin);
					}
					
				}
			}
		});
	}
	//退出软件监听
	private void addWinListener(){
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
				if(JOptionPane.showConfirmDialog(tempWin, "确定退出软件？", "退出", JOptionPane.WARNING_MESSAGE)==0){
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
	}
	/**
	 *加载存档
	 */
 	private void loadRecord(){
 		String fileName="pic/hello.txt";
		File file=new File(fileName);
		try {
			if(!file.exists()){
	        	file.createNewFile();
	        	StringBuffer strBuf=new StringBuffer();
	        	for (int i = 0; i < buttonCount; i++) {
					strBuf.append(i%gridCol);
					strBuf.append("\n");
				}
	        	strBuf.deleteCharAt(strBuf.length()-1);
	        	FileWriter fileWritter = new FileWriter(fileName);
	        	System.out.println(fileName);
	        	
	        	fileWritter.write(strBuf.toString());
	        	fileWritter.close();
	        	System.out.println("hello file create!");
	        	
	        }
			BufferedReader buffReader=new BufferedReader(new FileReader(file));
			int count=0;
        	while(true){
        		String a=buffReader.readLine();
        		if(a==null){break;}
        		//System.out.println(a);
        		menuBlockLabels[count/gridCol][count%gridCol]=new Integer(a);
        		count++;
        	}
        	buffReader.close();
        	//显示读取的数据
			for (int i = 0; i < gridRow; i++) {
				for (int j = 0; j < gridCol; j++) {
					System.out.print(menuBlockLabels[i][j]+"\t");
				}
				System.out.println("\n");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * 更新数据
	 * 根据储存菜名标签的数组来加载图片
	 * index:更新某一块的图片,按钮的索引0--29；若为-1，更新全部
	 */
	public void updateImage(int index){
		if(-1==index){
			int btnIndex=0;//用来遍历按钮arraylist的索引
			int mealIndex=-1;
			String fileName;
			for (int i = 0; i < gridRow; i++) {
				fileName="pic/"+new Integer(i).toString()+"/";
				//System.out.println(fileName);
				for (int j = 0; j < gridCol; j++) {
					mealIndex=menuBlockLabels[i][j];
					MyImageIcon myIcon=new MyImageIcon(fileName+new Integer(mealIndex).toString()+".jpg");
					File file=new  File(fileName+new Integer(mealIndex).toString()+".jpg");
					if(!file.exists()){
						System.out.println(fileName+new Integer(mealIndex).toString()+".jpg");
						menuBlockLabels[i][j]=-2;
						myIcon=new MyImageIcon(fileName+-2+".jpg");
						saveData();
					}
					btn[btnIndex].setIcon(myIcon.getIcon());
					btnIndex++;
				}
			}
		}
		//只更新一张
		else{
			int mealIndex=mealIndex=menuBlockLabels[index/gridCol][index%gridCol];
			String fileName="pic/"+new Integer(index/gridCol)+"/"+new Integer(mealIndex)+".jpg";
			MyImageIcon myIcon=new MyImageIcon(fileName);
			btn[index].setIcon(myIcon.getIcon());
			//System.out.println(fileName);
		}
	}
	/*
	 * 保存数据
	 */
	public void saveData(){
		StringBuffer strBuf=new StringBuffer();
		String fileName="pic/hello.txt";
		for (int i = 0; i <gridRow; i++) {
			for (int j = 0; j < gridCol; j++) {
				strBuf.append(new Integer(menuBlockLabels[i][j]).toString());
				strBuf.append("\n");
			}
		}
		File file =new File(fileName);
		try {
			FileWriter fileWritter = new FileWriter(fileName);
			if(!file.exists()){
	        	file.createNewFile();
	        }
			fileWritter.write(strBuf.toString());
	        fileWritter.close();
	        System.out.println("record save");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
