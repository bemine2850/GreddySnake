import javax.swing.JFrame;

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

	public GameStart(GameMap map){
		add(new GreddySnake(map));
		setTitle("̰����");
		setSize(1060,800);
		setLocationRelativeTo(null);//���ھ�����ʾ
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ����˳�����
		setVisible(true);
		
		//�������ֲ����࣬ѭ�����ű�������
		PlayMusic m=new PlayMusic();
		m.playLoop("music\\background.wav");
		
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new GameStart(new GameMap("D:\\ccc.txt"));
//	}

}
