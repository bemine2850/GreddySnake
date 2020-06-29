package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame {
	
	private SnakeD snake;//��
	private JPanel jPanel;//��Ϸ����
	private Timer timer;//��ʱ�����ڹ涨��ʱ���ڵ������ƶ��ķ���
	private Node food;//ʳ��
	
	public MainFrame() throws HeadlessException{
		//��ʼ���������
		initFrame();
		//��ʼ����Ϸ����
		initGamePanel();
		//��ʼ����
		initSnake();
		//��ʼ��ʳ��
		initFood();
		//��ʼ����ʱ��
		intiTimer();
		//���ü��̼���
		setKeyListener();
	}
	
	private void initFood() {
		food = new Node();
		food.random();
	}

	//���ü��̼���
	private void setKeyListener() {
		addKeyListener(new KeyAdapter() {
			//�����̰���ʱ�����Զ����ô˷���
			public void keyPressed(KeyEvent e) {
				//�����е�һ��������һ�����
				switch(e.getKeyCode()) {
					case KeyEvent.VK_UP://�ϼ�
						if(snake.getDirection1() != Direction.DOWN) {
							snake.setDirection1(Direction.UP);
						}
						break;
					case KeyEvent.VK_DOWN://�¼�
						if(snake.getDirection1() != Direction.UP) {
							snake.setDirection1(Direction.DOWN);
						}
						break;
					case KeyEvent.VK_LEFT://���
						if(snake.getDirection1() != Direction.RIGHT) {
							snake.setDirection1(Direction.LEFT);
						}
						break;
					case KeyEvent.VK_RIGHT://�Ҽ�
						if(snake.getDirection1() != Direction.LEFT) {
							snake.setDirection1(Direction.RIGHT);
						}
						break;
						
				}
				
					switch(e.getKeyCode()) {
					case KeyEvent.VK_W://�ϼ�
						if(snake.getDirection2() != Direction.S) {
							snake.setDirection2(Direction.W);
						}
						break;
					case KeyEvent.VK_S://�¼�
						if(snake.getDirection2() != Direction.W) {
							snake.setDirection2(Direction.S);
						}
						break;
					case KeyEvent.VK_A://���
						if(snake.getDirection2() != Direction.D) {
							snake.setDirection2(Direction.A);
						}
						break;
					case KeyEvent.VK_D://�Ҽ�
						if(snake.getDirection2() != Direction.A) {
							snake.setDirection2(Direction.D);
						}
						break;	
					}
			}		
		});
	}

	//��ʼ����ʱ��
	private void intiTimer() {
		timer = new Timer();
		//��ʼ����ʱ����
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				snake.move();
				//�ж���ͷ�Ƿ��ʳ���غ�
				Node head1 = snake.getBody1().getFirst();
				Node head2 = snake.getBody2().getFirst();
				if(head1.getX() == food.getX() && head1.getY() == food.getY()) {
					snake.eat1(food);
					food.random();
				}
				if(head2.getX() == food.getX() && head2.getY() == food.getY()) {
					snake.eat2(food);
					food.random();
				}
				//�ػ���Ϸ����
				 jPanel.repaint();
			}
		};
		//ÿ100ms��ִ��һ�ζ�ʱ����
		timer.scheduleAtFixedRate(timerTask, 0, 200);
		
	}

	private void initSnake() {
		snake = new SnakeD();
	}
	
	//��ʼ���������
	private void initFrame() {
		//���ô�����(width,height)
		setSize(1060,800);
		//���ô���λ��(x,y)
		setLocation(200, 200);
		//���ùرհ�ť
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô����С���ɱ�
		setResizable(false);
	}
	
	//��ʼ����Ϸ����
	private void initGamePanel() {
		
		jPanel = new JPanel(){
			
			//���������е�����
			@Override
			public void paint(Graphics g) {
				//�������
				g.clearRect(25, 40, 1000, 700);
				
				//Graphics g ���Կ���һ�����ʣ��ṩ�˺ܶ෽�����Ի���һЩ������ͼ�Σ�ֱ�ߡ����Σ�
				//����40��ֱ��
/*				for(int i = 0; i <= 35; i++)
				{
					g.drawLine(25, i*20+40, 1025, i*20+40);
				}
				for(int i = 0; i <= 50; i++) {
					g.drawLine(i*20+25, 40, i*20+25, 740);
			}
*/					
				//������
				LinkedList<Node> body1 = snake.getBody1();
				for(Node node1 : body1) {
					g.setColor(Color.blue);
					g.fillRect(node1.getX()*20+25, node1.getY()*20+40, 20, 20);
				}
				
				LinkedList<Node> body2 = snake.getBody2();
				for(Node node2 : body2) {
					g.setColor(Color.green);
					g.fillRect(node2.getX()*20+25, node2.getY()*20+40, 20, 20);
				}
				
				//����ʳ��
				g.setColor(Color.yellow);
				g.fillRect(food.getX()*20+25, food.getY()*20+40, 20, 20);
				
				
			}
		};
		
		add(jPanel);
	}

	public static void main(String[] args) {
		//�������������ʾ
		new MainFrame().setVisible(true);
	}
	
}
