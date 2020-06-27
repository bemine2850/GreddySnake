import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;

public class GreddySnake extends JPanel implements ActionListener,KeyListener,Runnable{
	static boolean start=false;
	static int foodX;
	static int foodY;
	Snake snake;
	Random ran=new Random();
	Thread thread;
	static int key=0;//记录键盘
	
	public GreddySnake(){
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
		//初始化食物位置
		foodX=ran.nextInt(50);
		foodY=ran.nextInt(35);
		//创建小蛇，给它加一节身体，并指定位置
		snake=new Snake();
		snake.bodyList.add(new SnakeBody());
		snake.bodyList.get(0).setX(17);
		snake.bodyList.get(0).setY(25);
		
		
	}
	
	public void paintComponent(Graphics g){//画图
		super.paintComponent(g);
		g.drawRect(25, 40, 1000, 700);//实际游戏界面为1000*700
		for(int i = 0; i <= 35; i++)
		{
			g.drawLine(25, i*20+40, 1025, i*20+40);
		}
		for(int i = 0; i <= 50; i++) {
			g.drawLine(i*20+25, 40, i*20+25, 740);
		}
 
		if(start){//游戏中
			//画食物
			g.setColor(Color.yellow);
			g.fillRect(foodX*20+25, foodY*20+40, 20, 20);
			//画小蛇
			g.setColor(Color.blue);
			
//			for(int i=0;i<snake.bodyList.size();i++){
//				g.fillRect(snake.bodyList.get(i).getX()*20+25, snake.bodyList.get(i).getY()*20+40, 20, 20);
//			}
			ImageIcon head = new ImageIcon("img/head.png");
			Image imgHead= head.getImage();
			ImageIcon body = new ImageIcon("img/body.png");
			Image imgBody= body.getImage();
			g.drawImage(imgHead, snake.bodyList.get(0).getX()*20+25, snake.bodyList.get(0).getY()*20+40, 20,20, null);
			for(int i=1;i<snake.bodyList.size();i++){
//				g.fillRect(snake.bodyList.get(i).getX()*20+25, snake.bodyList.get(i).getY()*20+40, 20, 20);
				g.drawImage(imgBody, snake.bodyList.get(i).getX()*20+25, snake.bodyList.get(i).getY()*20+40, 20,20, null);
			}

		}
//		else{
//			g.setColor(Color.green);
//			g.setFont(new Font("Courier",Font.BOLD,50));
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
			int keycode=e.getKeyCode();
			if(keycode>=37&&keycode<=40)
				key=keycode;
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
				thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
}
