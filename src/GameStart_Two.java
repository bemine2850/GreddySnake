import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/*
 * TODO:
 * 1.找出改变按钮位置的方法
 * 2.思考吃到食物后身体长度增长方式是否合理
 * 3.添加食物的个数、分数、游戏时间、游戏结算
 * 4.添加双人模式
 * 5.解决小bug
 * ...
 */
public class GameStart_Two extends JFrame{

	public GameStart_Two(GameMap map){
//		JFrame frame=new JFrame();
//		frame.setTitle("贪吃蛇");
//		frame.setResizable(false);//不可调整窗口大小
//		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
//		frame.add(new Greddy());
//		frame.setSize(1200,900);//窗口大小
//		frame.setLocationRelativeTo(null);//窗口居中
//		frame.setVisible(true);//可见
//		setLayout(null);
		add(new GreddySnake_Two(map));
		setTitle("贪吃蛇-双人模式");
		setSize(1060,800);
		setLocationRelativeTo(null);//窗口居中显示
		setResizable(false);
		//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		
		//创建音乐播放类，循环播放背景音乐
		PlayMusic m=new PlayMusic();
		m.playLoop("music\\background.wav");
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameStart_Two(new GameMap("D:\\ccc.txt"));
	}

}
