import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameStart_Two extends JFrame{

	public GameStart_Two(GameMap map){
		add(new GreddySnake_Two(map));
		setTitle("̰����-˫��ģʽ");
		setSize(1060,800);//���ڴ�С
		setLocationRelativeTo(null);//���ھ�����ʾ
		setResizable(false);//���ɵ������ڴ�С
		//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);//�ɼ�
		
		//�������ֲ����࣬ѭ�����ű�������
		PlayMusic m=new PlayMusic();
		m.playLoop("music\\background.wav");
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameStart_Two(new GameMap("D:\\ccc.txt"));
	}

}
