import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/*
 * TODO:
 * 1.�ҳ��ı䰴ťλ�õķ���
 * 2.˼���Ե�ʳ������峤��������ʽ�Ƿ����
 * 3.���ʳ��ĸ�������������Ϸʱ�䡢��Ϸ����
 * 4.���˫��ģʽ
 * 5.���Сbug
 * ...
 */
public class GameStart extends JFrame{

	public GameStart(){
//		JFrame frame=new JFrame();
//		frame.setTitle("̰����");
//		frame.setResizable(false);//���ɵ������ڴ�С
//		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
//		frame.add(new Greddy());
//		frame.setSize(1200,900);//���ڴ�С
//		frame.setLocationRelativeTo(null);//���ھ���
//		frame.setVisible(true);//�ɼ�
//		setLayout(null);
		add(new GreddySnake());
		setTitle("̰����");
		setSize(1060,800);
		setLocationRelativeTo(null);//���ھ�����ʾ
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		
		//�������ֲ����࣬ѭ�����ű�������
		PlayMusic m=new PlayMusic();
		m.playLoop("music\\background.wav");
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameStart();
	}

}
