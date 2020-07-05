import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameStart_Two extends JFrame{

	public GameStart_Two(GameMap map){
		add(new GreddySnake_Two(map));
		setTitle("贪吃蛇-双人模式");
		setSize(1060,800);//窗口大小
		setLocationRelativeTo(null);//窗口居中显示
		setResizable(false);//不可调整窗口大小
		//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);//可见
		
		//创建音乐播放类，循环播放背景音乐
		PlayMusic m=new PlayMusic();
		m.playLoop("music\\background.wav");
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameStart_Two(new GameMap("D:\\ccc.txt"));
	}

}
