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
	static int key=0;//��¼����
	
	public GreddySnake(){
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
		//��ʼ��ʳ��λ��
		foodX=ran.nextInt(50);
		foodY=ran.nextInt(35);
		//����С�ߣ�������һ�����壬��ָ��λ��
		snake=new Snake();
		snake.bodyList.add(new SnakeBody());
		snake.bodyList.get(0).setX(17);
		snake.bodyList.get(0).setY(25);
		
		
	}
	
	public void paintComponent(Graphics g){//��ͼ
		super.paintComponent(g);
		g.drawRect(25, 40, 1000, 700);//ʵ����Ϸ����Ϊ1000*700
		for(int i = 0; i <= 35; i++)
		{
			g.drawLine(25, i*20+40, 1025, i*20+40);
		}
		for(int i = 0; i <= 50; i++) {
			g.drawLine(i*20+25, 40, i*20+25, 740);
		}
 
		if(start){//��Ϸ��
			//��ʳ��
			g.setColor(Color.yellow);
			g.fillRect(foodX*20+25, foodY*20+40, 20, 20);
			//��С��
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
		if(e.getActionCommand()=="��ʼ"){
			init();
			thread=new Thread(this);
			thread.start();
			start=true;
		}else{
			start=false;
			thread=null;
		}
		repaint();
		this.requestFocus();//���ؽ��㵽��һ�㣬����Ҫ
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
				thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
}
