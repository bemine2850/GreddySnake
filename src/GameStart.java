import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameStart extends JFrame{

	public GameStart(GameMap map){
		add(new GreddySnake(map));
		setTitle("̰����");
		setSize(1060,800);
		setLocationRelativeTo(null);//���ھ�����ʾ
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ����˳�����
		setVisible(true);
		
		//�������ֲ����࣬ѭ�����ű�������
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
