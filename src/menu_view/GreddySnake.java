import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;

public class GreddySnake extends JPanel implements ActionListener,KeyListener,Runnable{
	public static final int gameBorderWidth=40;//游戏板宽度
	public static final int gameBorderHeight=28;//游戏板高度
	public static final int gameBorderUnit=25;//游戏板单元格大小
	public static final int gameBorderLeft=25;//游戏板距离左边边界距离
	public static final int gameBorderUp=40;//游戏板距离左边边界距离
	
	GameMap map;
	Food food;
	static boolean start=false;
	static int foodX;
	static int foodY;
	
	Snake snake;
	Random ran=new Random();
	Thread thread;
	static int key=0;//记录键盘
	
	public GreddySnake(GameMap map){
		this.map=map;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));//流式布局，默认布局组件会居中
		this.addKeyListener(this);//键盘监听
		this.setFocusable(true);
		init();//初始化
	}
	
	public void init(){//初始化
		key=0;
		//添加按键
		JButton btnStart=new JButton("开始");
		btnStart.setPreferredSize(new Dimension(70,30));
		btnStart.setLocation(0, 0);
//		btnStart.setBounds(500,5,70,30);
		btnStart.addActionListener((ActionListener) this);
		btnStart.setVisible(true);
		this.add(btnStart);
		
		JButton btnOver=new JButton("结束");
		btnOver.setPreferredSize(new Dimension(70,30));
		btnOver.addActionListener((ActionListener) this);
		btnOver.setLocation(200, 200);
		this.add(btnOver);
		//创建小蛇，给它加一节身体，并指定位置
		snake=new Snake(map);
		snake.bodyList.add(new SnakeBody());
		snake.bodyList.get(0).setX(17);
		snake.bodyList.get(0).setY(25);
		//初始化食物位置
//		foodX=ran.nextInt(gameBorderWidth);
//		foodY=ran.nextInt(gameBorderHeight);
		food=new Food(map,snake);
		
		snake.addFood(food);
		
		
	}
	
	public void paintComponent(Graphics g){//画图
		super.paintComponent(g);
		g.drawRect(gameBorderLeft, gameBorderUp, 1000, 700);//实际游戏界面为1000*700
		//网格线
		for(int i = 0; i <= gameBorderHeight; i++)
		{
			g.drawLine(gameBorderLeft, i*gameBorderUnit+gameBorderUp, 1025, i*gameBorderUnit+gameBorderUp);
		}
		for(int i = 0; i <= gameBorderWidth; i++) {
			g.drawLine(i*gameBorderUnit+gameBorderLeft, gameBorderUp, i*gameBorderUnit+gameBorderLeft, 740);
		}
		//地图
		for(int i=0;i<gameBorderHeight;i++){
			for(int j=0;j<gameBorderWidth;j++){
				if(map.get(i, j)==1){
					g.setColor(new Color(139, 170, 65));
					g.fillRect(j*25+25, i*25+40, 25, 25);
				}else if(map.get(i, j)>1){
					g.setColor(new Color(255, 0, 0));
					g.fillRect(j*25+25,i*25+40 , 25, 25);
				}
			}
		}
 
		if(start){//游戏中
			//画食物
			g.setColor(Color.yellow);
//			g.fillRect(foodX*gameBorderUnit+gameBorderLeft, foodY*gameBorderUnit+gameBorderUp, gameBorderUnit, gameBorderUnit);
			for(int i=0;i<food.foodNum;i++){
				g.fillRect(food.foodX[i]*gameBorderUnit+gameBorderLeft, food.foodY[i]*gameBorderUnit+gameBorderUp, gameBorderUnit, gameBorderUnit);
			}
			//画小蛇
			g.setColor(Color.blue);
			
//			for(int i=0;i<snake.bodyList.size();i++){
//				g.fillRect(snake.bodyList.get(i).getX()*gameBorderUnit+gameBorderLeft, snake.bodyList.get(i).getY()*gameBorderUnit+gameBorderUp, gameBorderUnit, gameBorderUnit);
//			}
			ImageIcon head = new ImageIcon("img/head.png");
			Image imgHead= head.getImage();
			ImageIcon body = new ImageIcon("img/body.png");
			Image imgBody= body.getImage();
			g.drawImage(imgHead, snake.bodyList.get(0).getX()*gameBorderUnit+gameBorderLeft, snake.bodyList.get(0).getY()*gameBorderUnit+gameBorderUp, gameBorderUnit,gameBorderUnit, null);
			for(int i=1;i<snake.bodyList.size();i++){
//				g.fillRect(snake.bodyList.get(i).getX()*gameBorderUnit+gameBorderLeft, snake.bodyList.get(i).getY()*gameBorderUnit+gameBorderUp, gameBorderUnit, gameBorderUnit);
				g.drawImage(imgBody, snake.bodyList.get(i).getX()*gameBorderUnit+gameBorderLeft, snake.bodyList.get(i).getY()*gameBorderUnit+gameBorderUp, gameBorderUnit,gameBorderUnit, null);
			}

		}
//		else{
//			g.setColor(Color.green);
//			g.setFont(new Font("Courier",Font.BOLD,gameBorderWidth));
//			g.drawString("ddhjk",400,320);
//		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="开始"){
			init();
			thread=new Thread(this);
			thread.start();
			start=true;
		}else{
			start=false;
			thread=null;
		}
		repaint();
		this.requestFocus();//返回焦点到上一层，很重要
	}

		
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(start){
			int oldKey=key;
			int keycode=e.getKeyCode();
			if(keycode>=37&&keycode<=40)
				key=keycode;
			if(key==KeyEvent.VK_LEFT&&oldKey!=KeyEvent.VK_RIGHT){
				snake.move(-1,0);
			}else if(key==KeyEvent.VK_RIGHT&&oldKey!=KeyEvent.VK_LEFT){
				snake.move(1,0);
			}else if(key==KeyEvent.VK_UP&&oldKey!=KeyEvent.VK_DOWN){
				snake.move(0,-1);
			}else if(key==KeyEvent.VK_DOWN&&oldKey!=KeyEvent.VK_UP){
				snake.move(0,1);
			}
			repaint();
		}
	}

	public void keyReleased(KeyEvent arg0) {//不用实现
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {//不用实现
		// TODO Auto-generated method stub
		
	}

	public void run() {
		// TODO Auto-generated method stub
		while(start){
			if(key==KeyEvent.VK_LEFT){
				snake.move(-1,0);
				
			}else if(key==KeyEvent.VK_RIGHT){
				snake.move(1,0);
			}else if(key==KeyEvent.VK_UP){
				snake.move(0,-1);
				
			}else if(key==KeyEvent.VK_DOWN){
				snake.move(0,1);
			}
			repaint();
			try {
				thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
