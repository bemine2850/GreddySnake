/*
 * 选择关卡页面，可根据主菜单选择的单双人模式进行跳转
 * */
import javax.swing.*;   //图形界面包

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class choose extends JFrame {
	static List<String> diyList = new ArrayList<String>();//保存自定义关卡名
	//弹出式菜单
	JPopupMenu popup=new JPopupMenu();
	JLabel l;
	JPanel p2;
	String diyFileName;//保存鼠标右键点击的自定义地图名
	JLabel labelSelected;//保存鼠标右键点击的自定义标签
	public choose() {
		
		readDIY();//读取自定义关卡列表
		setBounds(950,120,495, 770); 
		setTitle("游戏选关页面");
		setResizable(false);     //窗体是否可由用户调节大小
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//设置背景面板
		JPanel p=(JPanel)this.getContentPane();
		p.setLayout(null); 
		p.setVisible(true);   
		Icon icon = new ImageIcon("img/b2.png");
		l= new JLabel(icon);      //之后的按钮和面板存放在这个标签里
		l.setBounds(0, 0,icon.getIconWidth(), icon.getIconHeight());
		p.add(l);
		l.setVisible(true);
		//设置存放关卡星标的面板
		JPanel p1 = new JPanel();     
		p1.setBounds(20,90, 440, 150);
		p1.setOpaque(false);
		//p1.setBackground(Color.white);
		l.add(p1);
		p1.setLayout(new GridLayout(2,4,6,5));   //4行6列，水平间距5，垂直间距5
		
		for(int i=0;i<10;i++)
		{
//			final JLabel label = new JLabel(i+1+"",new ImageIcon("img/star1.png"), JLabel.CENTER);
			final JLabel label = new JLabel(new ImageIcon("img/star1.png"));
			label.setName(i+1+"");
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.addMouseListener(new MouseAdapter(){
				//点击标签会切换到背景star2.png并且跳转到游戏界面
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1) {
//						System.out.println(m.choose_number);
/**/				//		if(m.choose_number == 1) {
						
//						if(e.getModifiersEx()=="第1关"){
//							System.out.println(e.getComponent().getY());
						System.out.println(((JLabel) e.getSource()).getName());
//						}
						
						if(menu.choose_number==1){
//							label.setIcon(new ImageIcon("img/star2.png"));
/**/		//					setVisible(false);  //窗口隐藏
							new GameStart(new GameMap("file\\第"+((JLabel) e.getSource()).getName()+"关.txt"));; //进入单人游戏
						}
/**/				//		if(m.choose_number == 2) {
						if(menu.choose_number==2){
							setVisible(false);  //窗口隐藏
							new GameStart_Two(new GameMap("file\\第"+((JLabel) e.getSource()).getName()+"关.txt")); //进入双人游戏
						}
					}
				}
			
			});
			p1.add(label);
		}
		p1.setVisible(true);
		
		
		showDIYmap();
		//弹出去式菜单
		JMenuItem itemEdit=new JMenuItem("编辑");
		itemEdit.setActionCommand("edit");
		itemEdit.addActionListener(actionListener);
		popup.add(itemEdit);
		
		JMenuItem itemDelete=new JMenuItem("删除");
		itemDelete.setActionCommand("delete");
		itemDelete.addActionListener(actionListener);
		popup.add(itemDelete);
		
		
		
		//  l里面添加按钮
		JButton b1 = new JButton(new ImageIcon("img/bn1.png"));
		JButton b2 = new JButton(new ImageIcon("img/bn1.png"));
		JLabel la1 = new JLabel("自 定 义 地 图");
		la1.setForeground(Color.white);
		la1.setFont(new Font("隶书",Font.PLAIN,23));
		
		JLabel la2 = new JLabel(" 退 出 选 关");
		la2.setForeground(Color.white);
		la2.setFont(new Font("隶书",Font.PLAIN,23));
		
		b1.setBounds(28, 465, 210, 60);
		b2.setBounds(255, 465, 210, 60);
		b1.add(la1);b2.add(la2);
		l.add(b1);l.add(b2);
/*-------------------------按钮和标签监听事件------------------------------*/
		//跳到自定义地图模块MakeGameMap
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();      //当前页面关闭
				JFrame jf=new JFrame("自定义地图");
				jf.setResizable(false);
				jf.setVisible(true);
				jf.setBounds(200, 150, 1200,800);
				jf.getContentPane().add(new MakeGameMap());
				
			}
		});
		//退出选关回到主菜单menu页面
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();      //当前页面关闭
				new menu();
			}
		});
		//选中关卡之后星标改变
		
		this.setVisible(true);
	}
	//动态显示自定义关卡
	public void showDIYmap(){
		//设置存放自定义地图星标
		p2 = new JPanel();    
		p2.setBounds(20,240, 440, 200);
		p2.setOpaque(false);//透明
		//p2.setBackground(Color.white);
		p2.setLayout(new GridLayout(5,4,7,5));  
		l.add(p2);
				
		for(int i=0;i<diyList.size();i++){
					//TODO：试一下setName（）和getName（）
//					JLabel label = new JLabel(i+1+"",new ImageIcon("img/star2.png"), JLabel.CENTER);
			JLabel label = new JLabel(new ImageIcon("img/star2.png"));
			label.setName(i+1+"");
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1) {//鼠标左键
						if(menu.choose_number==1){//diyList.get(e.getSource()).getName())
							new GameStart(new GameMap("file\\"+diyList.get( Integer.valueOf(((JLabel) e.getSource()).getName())-1)+".txt")); //进入单人游戏
						}
						if(menu.choose_number==2){
							new GameStart_Two(new GameMap("file\\"+diyList.get( Integer.valueOf(((JLabel) e.getSource()).getName())-1)+".txt")); //进入双人游戏
						}
					}else if(e.getButton() == MouseEvent.BUTTON3){//鼠标右键
//								setVisible(false);
						System.out.println(e);
						diyFileName=diyList.get( Integer.valueOf(((JLabel) e.getSource()).getName())-1);
						popup.show(e.getComponent(), e.getX(), e.getY());
					}
				}
					
			});
			p2.add(label);
		}
		p2.setVisible(true);
	}
	//读取自定义关卡列表
	public void readDIY(){
		Scanner in = null;
		try {
			in=new Scanner(new File("file\\DIYList.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(in.hasNext()){
			diyList.add(in.nextLine());
			//System.out.println(in.nextLine());
		}
        in.close();
	}
	//将最新的自定义菜单列表写入文件
	public void writeDIY(){//保存地图
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("file\\DIYList.txt"));//先创建ccc.txt（若存在，则不会创建）
        } catch (FileNotFoundException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        for(int i=0;i<diyList.size();i++)
        {
        	pw.println(diyList.get(i));
        }
        pw.close();
	}
	//右键菜单列表命令监听
	public ActionListener actionListener=new ActionListener() {
		 
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action=e.getActionCommand();
		
			if(action.equals("edit")){//编辑地图
				dispose();      //当前页面关闭
				JFrame jf=new JFrame("自定义地图");
				jf.setResizable(false);
				jf.setVisible(true);
				jf.setBounds(200, 150, 1200,800);
				MakeGameMap gameMap=new MakeGameMap(new GameMap("file\\"+diyFileName+".txt"));
				gameMap.text.setText(diyFileName);
				jf.getContentPane().add(gameMap);
			}else if(action.equals("delete")){//删除地图
//				System.out.println()
				for(int i=0;i<diyList.size();i++){
					if(diyList.get(i).equals(diyFileName)){
						diyList.remove(i);
						System.out.println(diyList.size());
						break;
					}
				}
				writeDIY();
				p2.setVisible(false);
				showDIYmap();
			}
		}
	};
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new choose();
	}

}
