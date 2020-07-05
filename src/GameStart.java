import javax.swing.JFrame;

/*
 * TODO:
 * 1.找出改变按钮位置的方法
 * 2.思考吃到食物后身体长度增长方式是否合理
 * 3.添加食物的个数、分数、游戏时间、游戏结算
 * 4.添加双人模式
 * 5.解决小bug
 * ...
 */
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
		PlayMusic m=new PlayMusic();
		m.playLoop("music\\background.wav");
		
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new GameStart(new GameMap("D:\\ccc.txt"));
//	}

}
