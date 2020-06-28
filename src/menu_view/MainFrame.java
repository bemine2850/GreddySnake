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
	
	private SnakeD snake;//蛇
	private JPanel jPanel;//游戏棋盘
	private Timer timer;//定时器，在规定的时间内调用蛇移动的方法
	private Node food;//食物
	
	public MainFrame() throws HeadlessException{
		//初始化窗体参数
		initFrame();
		//初始化游戏棋盘
		initGamePanel();
		//初始化蛇
		initSnake();
		//初始化食物
		initFood();
		//初始化定时器
		intiTimer();
		//设置键盘监听
		setKeyListener();
	}
	
	private void initFood() {
		food = new Node();
		food.random();
	}

	//设置键盘监听
	private void setKeyListener() {
		addKeyListener(new KeyAdapter() {
			//当键盘按下时，会自动调用此方法
			public void keyPressed(KeyEvent e) {
				//键盘中的一个键都有一个编号
				switch(e.getKeyCode()) {
					case KeyEvent.VK_UP://上键
						if(snake.getDirection1() != Direction.DOWN) {
							snake.setDirection1(Direction.UP);
						}
						break;
					case KeyEvent.VK_DOWN://下键
						if(snake.getDirection1() != Direction.UP) {
							snake.setDirection1(Direction.DOWN);
						}
						break;
					case KeyEvent.VK_LEFT://左键
						if(snake.getDirection1() != Direction.RIGHT) {
							snake.setDirection1(Direction.LEFT);
						}
						break;
					case KeyEvent.VK_RIGHT://右键
						if(snake.getDirection1() != Direction.LEFT) {
							snake.setDirection1(Direction.RIGHT);
						}
						break;
						
				}
				
					switch(e.getKeyCode()) {
					case KeyEvent.VK_W://上键
						if(snake.getDirection2() != Direction.S) {
							snake.setDirection2(Direction.W);
						}
						break;
					case KeyEvent.VK_S://下键
						if(snake.getDirection2() != Direction.W) {
							snake.setDirection2(Direction.S);
						}
						break;
					case KeyEvent.VK_A://左键
						if(snake.getDirection2() != Direction.D) {
							snake.setDirection2(Direction.A);
						}
						break;
					case KeyEvent.VK_D://右键
						if(snake.getDirection2() != Direction.A) {
							snake.setDirection2(Direction.D);
						}
						break;	
					}
			}		
		});
	}

	//初始化定时器
	private void intiTimer() {
		timer = new Timer();
		//初始化定时任务
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				snake.move();
				//判断蛇头是否和食物重合
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
				//重绘游戏棋盘
				 jPanel.repaint();
			}
		};
		//每100ms，执行一次定时任务
		timer.scheduleAtFixedRate(timerTask, 0, 200);
		
	}

	private void initSnake() {
		snake = new SnakeD();
	}
	
	//初始化窗体参数
	private void initFrame() {
		//设置窗体宽高(width,height)
		setSize(1060,800);
		//设置窗体位置(x,y)
		setLocation(200, 200);
		//设置关闭按钮
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗体大小不可变
		setResizable(false);
	}
	
	//初始化游戏棋盘
	private void initGamePanel() {
		
		jPanel = new JPanel(){
			
			//绘制棋盘中的内容
			@Override
			public void paint(Graphics g) {
				//清空棋盘
				g.clearRect(25, 40, 1000, 700);
				
				//Graphics g 可以看做一个画笔，提供了很多方法可以绘制一些基本的图形（直线、矩形）
				//绘制40条直线
/*				for(int i = 0; i <= 35; i++)
				{
					g.drawLine(25, i*20+40, 1025, i*20+40);
				}
				for(int i = 0; i <= 50; i++) {
					g.drawLine(i*20+25, 40, i*20+25, 740);
			}
*/					
				//绘制蛇
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
				
				//绘制食物
				g.setColor(Color.yellow);
				g.fillRect(food.getX()*20+25, food.getY()*20+40, 20, 20);
				
				
			}
		};
		
		add(jPanel);
	}

	public static void main(String[] args) {
		//创建窗体对象并显示
		new MainFrame().setVisible(true);
	}
	
}
