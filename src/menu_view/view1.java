/*游戏初始页面包含分工，游戏规则介绍以及进入游戏主菜单页面的按钮及跳转
 * */
import javax.swing.*;   //图形界面包
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class view1 extends JFrame  {
	public view1() {
		setBounds(450,200, 900, 730); 
		setTitle("游戏初始界面");
		setResizable(false);     //窗体是否可由用户调节大小
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);  //绝对布局
		c.setBackground(Color.decode("#e5f8ff"));
		c.validate();//验证容器中的组件，刷新    
        this.setContentPane(c);//窗体重新载入容器
		
		JLabel label = new JLabel();  //标签
		label.setIcon(new ImageIcon("img/monster.jpg"));	//添加图片
		label.setBounds(70, 30, 760, 420);
	    c.add(label);
	    
	    JButton b1 = new JButton(new ImageIcon("img/按钮2.png"));
	    JButton b2 = new JButton(new ImageIcon("img/按钮2.png"));
	    JButton b3 = new JButton(new ImageIcon("img/按钮2.png"));
	    JButton b4 = new JButton(new ImageIcon("img/按钮2.png"));
	    b1.setBounds(210, 500, 200, 60);
	    b2.setBounds(470, 500, 200, 60);
	    b3.setBounds(210, 590, 200, 60);
	    b4.setBounds(470, 590, 200, 60);
	
	    JLabel label_1 = new_font(" 贪 吃 一 下");
	    JLabel label_2 = new_font(" 游 戏 规 则");
	    JLabel label_3=  new_font(" 分         工 ");
	    JLabel label_4 = new_font(" 其          他");
	     
	    b1.add(label_1);b2.add(label_2);b3.add(label_3);b4.add(label_4);
	    c.add(b1);c.add(b2);c.add(b3);c.add(b4);
	    
	    b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();   //当前页面关闭
				new menu();
				
			}
		});
	    b2.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent e) {
	 				new game_rule();
	 				
	 			}
	 		});
	    b3.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				new teamwork();
 				
 			}
 		});
	    b4.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				JFrame f1 = new JFrame();
 				JLabel lab = new JLabel("呀呀呀，被骗了吧，这儿啥都没有，嘻嘻~~~");
 				f1.getContentPane().add(lab);
 				f1.setBounds(750,600,350, 150); 
 				f1.setVisible(true);
 			}
 		});

	    
	    
		setVisible(true);
	}

	private JLabel new_font(String text) {
		 JLabel l = new JLabel(text);
		 l.setFont(new Font("楷书",Font.BOLD,30));
	 	 l.setForeground(Color.decode("#ccffff"));
	 	 return l;
		
	}
	

	public static void main(String[] args)
	{
		new view1();
	
	
	}

}
