package first;

import java.util.LinkedList;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 *Snake类表示蛇
 *一条蛇有多个节点使用linkedlist集合储存Node节点，蛇出生的时候有三个节点
 */

public class SnakeD {
	//蛇的身体
	private LinkedList<Node>body1;
	private LinkedList<Node>body2;
	//蛇的运动方向，默认向左
	private Direction direction1 = Direction.LEFT;
	private Direction direction2 = Direction.A;
	//蛇是否活着
	private boolean isLiving1 = true;
	private boolean isLiving2 = true;
	
	//构建方法，在创建Snake对象时执行
	public SnakeD() {
		//初始化蛇身体
		initSnake();
	}
	
	
	private void initSnake() {
		//创建集合
		body1=new LinkedList<>();
		body2=new LinkedList<>();
		//创建六个节点
		body1.add(new Node(36,20));
		body1.add(new Node(37,20));
		body1.add(new Node(38,20));
		body1.add(new Node(39,20));
		body1.add(new Node(40,20));
		
		body2.add(new Node(36,10));
		body2.add(new Node(37,10));
		body2.add(new Node(38,10));
		body2.add(new Node(39,10));
		body2.add(new Node(40,10));
	}
	
	//蛇会沿着蛇的蛇头方向移动
	//控制蛇移动，在蛇头的运动方向添加一个节点，然后把蛇尾的节点删除
	public void move() {
		if(isLiving1) {
			//获取蛇头
			Node head1 = body1.getFirst();
			switch (direction1) {
			case UP:
				//在蛇头的上面添加一个节点
				body1.addFirst(new Node(head1.getX(),head1.getY()-1));
				break;
			case DOWN:
				body1.addFirst(new Node(head1.getX(),head1.getY()+1));
				break;
			case LEFT:
				body1.addFirst(new Node(head1.getX()-1,head1.getY()));
				break;
			case RIGHT:
				body1.addFirst(new Node(head1.getX()+1,head1.getY()));
				break;
			}
			//删除最后的节点
			body1.removeLast();
			
			//判断蛇是否撞墙
			head1 = body1.getFirst();
			if(head1.getX()<1 || head1.getY()<1 || head1.getX()>=49 || head1.getY()>=34){
				isLiving1 = false;
			}
			
			//判断蛇是否碰到自己的身体
			for(int i = 1; i < body1.size(); i++) {
				Node node = body1.get(i);
				if(head1.getX() == node.getX() && head1.getY() == node.getY()) {
					isLiving1 = false;
				}
			}
			
			//判断蛇有没有互相碰撞
			for(int i = 1; i <body2.size(); i++) {
				Node node = body2.get(i);
				if(head1.getX() == node.getX() && head1.getY() == node.getY()) {
					isLiving1 = false;
				}
			}
			
		}
		if(isLiving2) {
			Node head2 = body2.getFirst();
			switch (direction2) {
			case W:
				//在蛇头的上面添加一个节点
				body2.addFirst(new Node(head2.getX(),head2.getY()-1));
				break;
			case S:
				body2.addFirst(new Node(head2.getX(),head2.getY()+1));
				break;
			case A:
				body2.addFirst(new Node(head2.getX()-1,head2.getY()));
				break;
			case D:
				body2.addFirst(new Node(head2.getX()+1,head2.getY()));
				break;
			}
			//删除最后的节点
			body2.removeLast();
			
			//判断蛇是否撞墙
			head2 = body2.getFirst();
			if(head2.getX()<1 || head2.getY()<1 || head2.getX()>=49 || head2.getY()>=34){
				isLiving2 = false;
			}
			
			//判断蛇是否碰到自己的身体
			for(int i = 1; i < body2.size(); i++) {
				Node node = body2.get(i);
				if(head2.getX() == node.getX() && head2.getY() == node.getY()) {
					isLiving2 = false;
				}
			}
			
			//判断蛇有没有互相碰撞
			for(int i = 1; i <body1.size(); i++) {
				Node node = body1.get(i);
				if(head2.getX() == node.getX() && head2.getY() == node.getY()) {
					isLiving2 = false;
				}
			}

		}
	}

	public LinkedList<Node> getBody1() {
		// TODO Auto-generated method stub
		return body1;
	}
	
	public LinkedList<Node> getBody2() {
		// TODO Auto-generated method stub
		return body2;
	}
	
	public void setBody1(LinkedList<Node>body) {
		this.body1 = body1;
	}
	
	public void setBody2(LinkedList<Node>body) {
		this.body2 = body2;
	}
	
	 public Direction getDirection1() {
		return direction1;
	}
	 
	 public Direction getDirection2() {
			return direction2;
	}
	 
	 public void setDirection1(Direction direction) {
		 this.direction1 = direction;
	}
	 
	 public void setDirection2(Direction direction) {
		 this.direction2 = direction;
	}

	 //吃食物，延蛇头方向增加一个节点
	public void eat1(Node food) {
		Node head1 = body1.getFirst();
		switch (direction1) {
		case UP:
			//在蛇头的上面添加一个节点
			body1.addFirst(new Node(head1.getX(),head1.getY()-1));
			break;
		case DOWN:
			body1.addFirst(new Node(head1.getX(),head1.getY()+1));
			break;
		case LEFT:
			body1.addFirst(new Node(head1.getX()-1,head1.getY()));
			break;
		case RIGHT:
			body1.addFirst(new Node(head1.getX()+1,head1.getY()));
			break;
		}	
	}
	public void eat2(Node food) {
		Node head2 = body2.getFirst();
		switch (direction2) {
		case W:
		//在蛇头的上面添加一个节点
			body2.addFirst(new Node(head2.getX(),head2.getY()-1));
			break;
		case S:
			body2.addFirst(new Node(head2.getX(),head2.getY()+1));
			break;
		case A:
			body2.addFirst(new Node(head2.getX()-1,head2.getY()));
			break;
		case D:
			body2.addFirst(new Node(head2.getX()+1,head2.getY()));
			break;
		}
	}
}
