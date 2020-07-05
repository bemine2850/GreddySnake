import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;

public class GreddySnake extends JPanel implements ActionListener,KeyListener,Runnable{
	public static final int gameBorderWidth=40;//��Ϸ����
	public static final int gameBorderHeight=28;//��Ϸ��߶�
	public static final int gameBorderUnit=25;//��Ϸ�嵥Ԫ���С
	public static final int gameBorderLeft=25;//��Ϸ�������߽߱����
	public static final int gameBorderUp=40;//��Ϸ�������߽߱����
	
	GameMap map;
	Food food;
	static boolean start=false;
	static int foodX;
	static int foodY;
	
	Snake snake;
	Random ran=new Random();
	Thread thread;
	static int key=0;//��¼����
	
	public GreddySnake(GameMap map){
		this.map=map;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));//��ʽ���֣�Ĭ�ϲ�����������
		this.addKeyListener(this);//���̼���
		this.setFocusable(true);
		init();//��ʼ��
	}
	
	public void init(){//��ʼ��
		key=0;
		//��Ӱ���
		JButton btnStart=new JButton("��ʼ");
		btnStart.setPreferredSize(new Dimension(70,30));
		btnStart.setLocation(0, 0);
//		btnStart.setBounds(500,5,70,30);
		btnStart.addActionListener((ActionListener) this);
		btnStart.setVisible(true);
		this.add(btnStart);
		
		JButton btnOver=new JButton("����");
		btnOver.setPreferredSize(new Dimension(70,30));
		btnOver.addActionListener((ActionListener) this);
		btnOver.setLocation(200, 200);
		this.add(btnOver);
		//����С�ߣ�������һ�����壬��ָ��λ��
		snake=new Snake(map);
		snake.bodyList.add(new SnakeBody());
		snake.bodyList.get(0).setX(17);
		snake.bodyList.get(0).setY(25);
		//��ʼ��ʳ��λ��
//		foodX=ran.nextInt(gameBorderWidth);
//		foodY=ran.nextInt(gameBorderHeight);
		food=new Food(map,snake);
		
		snake.addFood(food);
		
		
	}
	
	public void paintComponent(Graphics g){//��ͼ
		super.paintComponent(g);
		g.drawRect(gameBorderLeft, gameBorderUp, 1000, 700);//ʵ����Ϸ����Ϊ1000*700
		//������
		for(int i = 0; i <= gameBorderHeight; i++)
		{
			g.drawLine(gameBorderLeft, i*gameBorderUnit+gameBorderUp, 1025, i*gameBorderUnit+gameBorderUp);
		}
		for(int i = 0; i <= gameBorderWidth; i++) {
			g.drawLine(i*gameBorderUnit+gameBorderLeft, gameBorderUp, i*gameBorderUnit+gameBorderLeft, 740);
		}
		//��ͼ
		
		ImageIcon doorIcon = new ImageIcon("img/door1.png");
		Image imgDoor= doorIcon.getImage();
		for(int i=0;i<gameBorderHeight;i++){
			for(int j=0;j<gameBorderWidth;j++){
				if(map.get(i, j)==1){
					g.setColor(new Color(139, 170, 65));
					g.fillRect(j*25+25, i*25+40, 25, 25);
				}else if(map.get(i, j)>1){
					g.setColor(new Color(255, 0, 0));
//					g.fillRect(j*25+25,i*25+40 , 25, 25);
					g.drawImage(imgDoor, j*gameBorderUnit+gameBorderLeft, i*gameBorderUnit+gameBorderUp, gameBorderUnit,gameBorderUnit, null);
				}
			}
		}
 
//		if(start){//��Ϸ��
			//��ʳ��
			ImageIcon foodIcon = new ImageIcon("img/food.png");
			Image imgFood= foodIcon.getImage();
		
			g.setColor(Color.yellow);
//			g.fillRect(foodX*gameBorderUnit+gameBorderLeft, foodY*gameBorderUnit+gameBorderUp, gameBorderUnit, gameBorderUnit);
			for(int i=0;i<food.foodNum;i++){
//				g.fillRect(food.foodX[i]*gameBorderUnit+gameBorderLeft, food.foodY[i]*gameBorderUnit+gameBorderUp, gameBorderUnit, gameBorderUnit);
				g.drawImage(imgFood, food.foodX[i]*gameBorderUnit+gameBorderLeft, food.foodY[i]*gameBorderUnit+gameBorderUp, gameBorderUnit,gameBorderUnit, null);
			}
			//��С��
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
//			}

		}
//		else{
//			g.setColor(Color.green);
//			g.setFont(new Font("Courier",Font.BOLD,gameBorderWidth));
//			g.drawString("ddhjk",400,320);
//		}
	}
	AchievementRecord ar=new AchievementRecord();//�½��ɾͼ�¼��
	
	boolean firstClick=true;
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="��ʼ"){
			
			if(!firstClick){
				init();
			}
			firstClick=false;
			thread=new Thread(this);
			thread.start();
			start=true;
			//��ʼ��ʱ
			ar.startTiming();
		}else{
			start=false;
			thread=null;
			//������ʱ����ӡ�ɼ�
			ar.endTiming();
			ar.score=snake.score;
			try {
				ar.printScoreTime();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		repaint();
		this.requestFocus();//���ؽ��㵽��һ�㣬����Ҫ
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

	public void keyReleased(KeyEvent arg0) {//����ʵ��
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {//����ʵ��
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
