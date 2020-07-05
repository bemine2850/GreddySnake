import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameStart extends JFrame{

	public GameStart(GameMap map){
		add(new GreddySnake(map));
		setTitle("贪吃蛇");
		setSize(1060,800);
		setLocationRelativeTo(null);//窗口居中显示
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口退出程序
		setVisible(true);
		
		//创建音乐播放类，循环播放背景音乐
		final PlayMusic m=new PlayMusic();
		m.playLoop("music\\background.wav");
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
//				System.out.println("over");
				m.stop();
			}
		});
		
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new GameStart(new GameMap("D:\\ccc.txt"));
//	}

}
