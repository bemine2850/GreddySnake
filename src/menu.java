//package menu_view;
import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class menu extends JFrame {
	private boolean flag=true; //用来进行判断音乐播放的标志
/**/	@SuppressWarnings("deprecation")
	public menu() {
		this.setTitle("游戏菜单页");
		this.setBounds(350,80, 1100, 810);
		this.setResizable(false);   
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setVisible(true);
		
		JPanel p=(JPanel)this.getContentPane();
		p.setLayout(null);    //最大的面板是绝对布局
		p.setVisible(true);
		
		Icon icon = new ImageIcon("img/mview.jpg");
		JLabel l= new JLabel(icon); 
		l.setBounds(0, 0,icon.getIconWidth(), icon.getIconHeight());
		p.add(l);
		l.setVisible(true);
		
		JPanel p1 = new JPanel();      //这个面板存放选择按钮
		p1.setBounds(650,160, 220, 600);
		//p1.setBackground(Color.white);
		p1.setOpaque(false);  //背景设为透明
		l.add(p1);
		p1.setLayout(new GridLayout(6,1,10,60));    //11行4列，每行高60，每列宽110,水平间距10，垂直间距60
		p1.setVisible(true);
/*---------------------------------选择按钮布局-----------------------------------------*/		
		//JButton btn = new JButton("sdc");
		JButton btn_1 = new JButton(new ImageIcon("img/n4.png"));
		JButton btn_2 = new JButton(new ImageIcon("img/n6.png"));
		JButton btn_3 = new JButton(new ImageIcon("img/n7.png"));
		JButton btn_4 = new JButton(new ImageIcon("img/n8.png"));
		JButton btn_5 = new JButton(new ImageIcon("img/n5.png"));
	   // p1.add(btn);
		 JLabel label_1 = new_font(" 单 人 模 式");
		 JLabel label_2 = new_font(" 双 人 模 式");
		 JLabel label_3 =  new_font(" 积 分 排 行 ");
		 JLabel label_4 = new_font(" 制 作 地 图");
		 JLabel label_5 = new_font(" 退 出 游 戏");
		 btn_1.add(label_1);btn_2.add(label_2);btn_3.add(label_3);btn_4.add(label_4);btn_5.add(label_5);
		p1.add(btn_1);p1.add(btn_2);p1.add(btn_3);p1.add(btn_4);p1.add(btn_5);
		
/*---------------------------------音乐开关-----------------------------------------*/	
		
		File f = new File("music/Hello.wav");//这个是绝对路径测试的时候修改一下
		URL url = null;
		try {
			 url = f.toURL();
	
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
/**/		final AudioClip audioclip = Applet.newAudioClip(url);
/**/		final JLabel l5 = new JLabel(new ImageIcon("img/音乐.png"));
		l5.setBounds(920, 20, 100, 100);
		l.add(l5);	
		l5.setVisible(true);
		audioclip.loop();
		
/*---------------------------------各种监听事件-----------------------------------------*/	
		//单人游戏
/**/		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();   //当前页面关闭
				audioclip.stop();
				new GameStart();
				
			}
		});
		//双人游戏
/**/		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();   //当前页面关闭
				audioclip.stop();
//				JFrame frame=new JFrame();
//				MainFrame mainframe=new MainFrame();
//				mainframe.main(null);
//				frame.add(new MainFrame());
//				frame.setVisible(true);
				new MainFrame();
//				JFrame jframe=new JFrame();
//				jframe.getContentPane().add(new MainFrame());
			}
		});
			//制作地图S
			btn_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						JFrame jf=new JFrame("自定义地图");
						jf.setResizable(false);
						jf.setVisible(true);
						jf.setBounds(200, 150, 1200,800);
						jf.getContentPane().add(new MakeGameMap());
						
					}
				});
		//返回游戏
	    btn_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();   //当前页面关闭
					audioclip.stop();
					new view1();
					
				}
			});
	//音乐播放监听
	    l5.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				if(flag) {
					l5.setIcon(new ImageIcon("img/bin.png"));
					audioclip.stop();
					flag = false;
					
				}
				else {
					l5.setIcon(new ImageIcon("img/音乐.png"));
					audioclip.play();
					flag = true;
				}
				
			
			}
		});
		
	  this.setVisible(true);
	}
	
/*---------------------------------其他函数-----------------------------------------*/		
	private JLabel new_font(String text) {
		 JLabel l = new JLabel(text);
		 l.setFont(new Font("隶书",Font.PLAIN,28));
	 	 l.setForeground(Color.white);
	 	 return l;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      new menu() ;
	}

}
//	static void playmusic() {
//		try {			
//			URL cb;		
////			File f=new File("music/Hello.wav");   
////			String path = f.getAbsolutePath();
//			//path = path.replace("/", "//");
//			File f = new File("G:\\eclipse-workspace\\Menu\\music\\Hello.wav"); // 引号里面的是音乐文件所在的路径			
//			cb = f.toURL();			
//			AudioClip aau;			
//			aau = Applet.newAudioClip(cb);					
//			//aau.play();				
//			aau.loop();//循环播放			
//			System.out.println("可以播放");			
//			// 循环播放 aau.play()			
//			//单曲 aau.stop()停止播放 		
//			} catch (MalformedURLException e)
//			{						
//				e.printStackTrace();		
//				}
//		}
		
	


