/*
 * 选择关卡页面，可根据主菜单选择的单双人模式进行跳转
 * */
import javax.swing.*;   //图形界面包
import java.awt.*;
import java.awt.event.*;

public class choose extends JFrame {
    menu m = new menu();   //这里是为了使用全局变量进行单双人判断时候用的可以修 改 ，我觉得有一点问题
   
	public choose() {
		setBounds(950,120,495, 770); 
		setTitle("游戏选关页面");
		setResizable(false);     //窗体是否可由用户调节大小
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//设置背景面板
		JPanel p=(JPanel)this.getContentPane();
		p.setLayout(null); 
		p.setVisible(true);   
		Icon icon = new ImageIcon("img/b2.png");
		JLabel l= new JLabel(icon);      //之后的按钮和面板存放在这个标签里
		l.setBounds(0, 0,icon.getIconWidth(), icon.getIconHeight());
		p.add(l);
		l.setVisible(true);
		//设置存放关卡星标的面板
		JPanel p1 = new JPanel();     
		p1.setBounds(20,100, 440, 250);
		p1.setOpaque(false);
		//p1.setBackground(Color.white);
		l.add(p1);
		p1.setLayout(new GridLayout(4,6,5,5));   //4行6列，水平间距5，垂直间距5
		for(int i=0;i<16;i++)
		{
			final JLabel label = new JLabel(new ImageIcon("img/star1.png"));
			label.addMouseListener(new MouseAdapter(){
				//点击标签会切换到背景star2.png并且跳转到游戏界面
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println(m.choose_number);
						if(m.choose_number == 1) {
							label.setIcon(new ImageIcon("img/star2.png"));
							setVisible(false);  //窗口隐藏
							new GameStart(new GameMap("d:\\ccc.txt"));; //进入单人游戏
						}
						if(m.choose_number == 2) {
							setVisible(false);  //窗口隐藏
							new GameStart_Two(new GameMap("D:\\ccc.txt")); //进入双人游戏
						}
					}
				}
			
			});
			p1.add(label);
		}
		p1.setVisible(true);
		//  l里面添加按钮
		JButton b1 = new JButton(new ImageIcon("img/bn1.png"));
		JButton b2 = new JButton(new ImageIcon("img/bn1.png"));
		JLabel la1 = new JLabel("自 定 义 地 图");
		la1.setForeground(Color.white);
		la1.setFont(new Font("隶书",Font.PLAIN,23));
		
		JLabel la2 = new JLabel(" 退 出 选 关");
		la2.setForeground(Color.white);
		la2.setFont(new Font("隶书",Font.PLAIN,23));
		
		b1.setBounds(28, 450, 210, 60);
		b2.setBounds(255, 450, 210, 60);
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
				
			}
		});
		//选中关卡之后星标改变
		
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new choose();
	}

}
