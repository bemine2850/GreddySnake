import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class GreddySnake_Two extends JPanel implements ActionListener,KeyListener,Runnable{
	public static final int gameBorderWidth=40;//游戏板宽度
	public static final int gameBorderHeight=28;//游戏板高度
	public static final int gameBorderUnit=25;//游戏板单元格大小
	public static final int gameBorderLeft=25;//游戏板距离左边边界距离
	public static final int gameBorderUp=40;//游戏板距离左边边界距离
	
	GameMap map;
	Food_Two food;
	static boolean start=false;
	static int foodX;
	static int foodY;
	
	Snake_Two snake1,snake2;
	Random ran=new Random();
	Thread thread;
	static int key1=0;//记录键盘
	static int key2=0;
	
	public GreddySnake_Two(GameMap map){
		this.map=map;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));//流式布局，默认布局组件会居中
		this.addKeyListener(this);//键盘监听
		this.setFocusable(true);
		init();//初始化
	}
	
	public void init(){//初始化
		key1=0;
		key2=0;
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
		snake1=new Snake_Two(map);
		snake1.bodyList.add(new SnakeBody());
		snake1.bodyList.get(0).setX(23);
		snake1.bodyList.get(0).setY(25);
		
		snake2=new Snake_Two(map);
		snake2.bodyList.add(new SnakeBody());
		snake2.bodyList.get(0).setX(13);
		snake2.bodyList.get(0).setY(25);
		//初始化食物位置
//		foodX=ran.nextInt(gameBorderWidth);
//		foodY=ran.nextInt(gameBorderHeight);
		
		food=new Food_Two(map,snake1,snake2);
		snake1.addFood(food);
		snake2.addFood(food);
		
		
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
		ImageIcon doorIcon = new ImageIcon("img/door1.png");
		Image imgDoor= doorIcon.getImage();
		for(int i=0;i<gameBorderHeight;i++){
			for(int j=0;j<gameBorderWidth;j++){
				if(map.get(i, j)==1){
					ImageIcon wallIcon = new ImageIcon("img/wall.png");
					Image imgWall= wallIcon.getImage();
//					g.drawImage(imgWall, j*gameBorderUnit+gameBorderLeft-2, i*gameBorderUnit+gameBorderUp-2, 30,30, null);
					g.drawImage(imgWall, j*gameBorderUnit+gameBorderLeft, i*gameBorderUnit+gameBorderUp, 25,25, null);
				}else if(map.get(i, j)>1){
					g.setColor(new Color(255, 0, 0));
//					g.fillRect(j*25+25,i*25+40 , 25, 25);
					g.drawImage(imgDoor, j*gameBorderUnit+gameBorderLeft, i*gameBorderUnit+gameBorderUp, gameBorderUnit,gameBorderUnit, null);
				}
			}
		}
 
		//画食物
		ImageIcon foodIcon = new ImageIcon("img/food.png");
		Image imgFood= foodIcon.getImage();
		
//		g.setColor(Color.yellow);
		for(int i=0;i<food.foodNum;i++){
			g.drawImage(imgFood, food.foodX[i]*gameBorderUnit+gameBorderLeft, food.foodY[i]*gameBorderUnit+gameBorderUp, gameBorderUnit,gameBorderUnit, null);
		}
			
		//画小蛇
//		g.setColor(Color.blue);
			
		ImageIcon head1 = new ImageIcon("img/head.png");
		Image imgHead1= head1.getImage();
		ImageIcon body1 = new ImageIcon("img/body.png");
		Image imgBody1= body1.getImage();
			
		ImageIcon head2 = new ImageIcon("img/head2.png");
		Image imgHead2= head2.getImage();
		ImageIcon body2 = new ImageIcon("img/body2.png");
		Image imgBody2= body2.getImage();
			
			
			
		g.drawImage(imgHead1, snake1.bodyList.get(0).getX()*gameBorderUnit+gameBorderLeft, snake1.bodyList.get(0).getY()*gameBorderUnit+gameBorderUp, gameBorderUnit,gameBorderUnit, null);
		g.drawImage(imgHead2, snake2.bodyList.get(0).getX()*gameBorderUnit+gameBorderLeft, snake2.bodyList.get(0).getY()*gameBorderUnit+gameBorderUp, gameBorderUnit,gameBorderUnit, null);
			
		for(int i=1;i<snake1.bodyList.size();i++){
//			g.fillRect(snake.bodyList.get(i).getX()*gameBorderUnit+gameBorderLeft, snake.bodyList.get(i).getY()*gameBorderUnit+gameBorderUp, gameBorderUnit, gameBorderUnit);
			g.drawImage(imgBody1, snake1.bodyList.get(i).getX()*gameBorderUnit+gameBorderLeft, snake1.bodyList.get(i).getY()*gameBorderUnit+gameBorderUp, gameBorderUnit,gameBorderUnit, null);
			}
		for(int i=1;i<snake2.bodyList.size();i++){
//			g.fillRect(snake.bodyList.get(i).getX()*gameBorderUnit+gameBorderLeft, snake.bodyList.get(i).getY()*gameBorderUnit+gameBorderUp, gameBorderUnit, gameBorderUnit);
			g.drawImage(imgBody2, snake2.bodyList.get(i).getX()*gameBorderUnit+gameBorderLeft, snake2.bodyList.get(i).getY()*gameBorderUnit+gameBorderUp, gameBorderUnit,gameBorderUnit, null);
		}
	}
	
	AchievementRecord ar1=new AchievementRecord();//新建成就记录类
	AchievementRecord ar2=new AchievementRecord();
	boolean firstClick=true;
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="开始"){
/**			init();
			thread=new Thread(this);
			thread.start();
			start=true;
		}else{
			start=false;
			thread=null;
		}
		repaint();
		this.requestFocus();//返回焦点到上一层
*/
			if(!firstClick){
				init();
			}
			firstClick=false;
			thread=new Thread(this);
			thread.start();
			start=true;
			//开始计时
			ar1.startTiming();
			ar2.startTiming();
		}else{
			start=false;
			thread=null;
			//结束计时并打印成绩
			ar1.endTiming();
			ar2.endTiming();
			ar1.score=snake1.score;
			ar2.score=snake2.score;
			try {
				ar1.setTitle("PLAY1");
				ar1.printScoreTime();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ar2.setTitle("PLAY2");
				ar2.printScoreTime();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		repaint();
		this.requestFocus();//返回焦点到上一层，很重要
	}

		
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(start){
			int oldKey1=key1;
			int keycode=e.getKeyCode();
			if(keycode>=37&&keycode<=40)
				key1=keycode;
			if(key1==KeyEvent.VK_LEFT&&oldKey1!=KeyEvent.VK_RIGHT){
				snake1.move(-1,0,snake2);
			}else if(key1==KeyEvent.VK_RIGHT&&oldKey1!=KeyEvent.VK_LEFT){
				snake1.move(1,0,snake2);
			}else if(key1==KeyEvent.VK_UP&&oldKey1!=KeyEvent.VK_DOWN){
				snake1.move(0,-1,snake2);
			}else if(key1==KeyEvent.VK_DOWN&&oldKey1!=KeyEvent.VK_UP){
				snake1.move(0,1,snake2);
			}
			repaint();
			
			int oldKey2=key2;
			if(keycode==65||keycode==68||keycode==83||keycode==87)
				key2=keycode;
			if(key2==KeyEvent.VK_A&&oldKey2!=KeyEvent.VK_D){
				snake2.move(-1,0,snake1);
			}else if(key2==KeyEvent.VK_D&&oldKey2!=KeyEvent.VK_A){
				snake2.move(1,0,snake1);
			}else if(key2==KeyEvent.VK_W&&oldKey2!=KeyEvent.VK_S){
				snake2.move(0,-1,snake1);
			}else if(key2==KeyEvent.VK_S&&oldKey2!=KeyEvent.VK_W){
				snake2.move(0,1,snake1);
			}
			repaint();
			
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void run() {
		// TODO Auto-generated method stub
/**		while(start){
			int oldKey1=key1;
			if(key1==KeyEvent.VK_LEFT){
				snake1.move(-1,0);
			}else if(key1==KeyEvent.VK_RIGHT){
				snake1.move(1,0);
			}else if(key1==KeyEvent.VK_UP){
				snake1.move(0,-1);
			}else if(key1==KeyEvent.VK_DOWN){
				snake1.move(0,1);
			}
			repaint();
			
			int oldKey2=key2;
			if(key2==KeyEvent.VK_A){
				snake2.move(-1,0);
			}else if(key2==KeyEvent.VK_D){
				snake2.move(1,0);
			}else if(key2==KeyEvent.VK_W){
				snake2.move(0,-1);
			}else if(key2==KeyEvent.VK_S){
				snake2.move(0,1);
			}
			repaint();
**/				
		while(start){
			int oldKey1=key1;
			if(key1==KeyEvent.VK_LEFT&&oldKey1!=KeyEvent.VK_RIGHT){
				snake1.move(-1,0,snake2);
			}else if(key1==KeyEvent.VK_RIGHT&&oldKey1!=KeyEvent.VK_LEFT){
				snake1.move(1,0,snake2);
			}else if(key1==KeyEvent.VK_UP&&oldKey1!=KeyEvent.VK_DOWN){
				snake1.move(0,-1,snake2);
			}else if(key1==KeyEvent.VK_DOWN&&oldKey1!=KeyEvent.VK_UP){
				snake1.move(0,1,snake2);
			}
			repaint();
			
/**			switch(key1) {
			case KeyEvent.VK_UP://上键
				if(key1 != oldKey1) {
					snake1.move(0,1);
				}
				break;
			case KeyEvent.VK_DOWN://下键
				if(key1 != oldKey1) {
					snake1.move(0,-1);
				}
				break;
			case KeyEvent.VK_LEFT://左键
				if(key1 != oldKey1) {
					snake1.move(1,0);
				}
				break;
			case KeyEvent.VK_RIGHT://右键
				if(key1 != oldKey1) {
					snake1.move(-1,0);
				}
				break;
				
		}
*/
			int oldKey2=key2;
			if(key2==KeyEvent.VK_A&&oldKey2!=KeyEvent.VK_D){
				snake2.move(-1,0,snake1);
			}else if(key2==KeyEvent.VK_D&&oldKey2!=KeyEvent.VK_A){
				snake2.move(1,0,snake1);
			}else if(key2==KeyEvent.VK_W&&oldKey2!=KeyEvent.VK_S){
				snake2.move(0,-1,snake1);
			}else if(key2==KeyEvent.VK_S&&oldKey2!=KeyEvent.VK_W){
				snake2.move(0,1,snake1);
			}
			repaint();

			
			try {
				thread.sleep(400);
			} catch (InterruptedException g) {
				// TODO Auto-generated catch block
				g.printStackTrace();
			}
		}
	}
}
