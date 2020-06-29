package first;

import java.util.LinkedList;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 *Snake���ʾ��
 *һ�����ж���ڵ�ʹ��linkedlist���ϴ���Node�ڵ㣬�߳�����ʱ���������ڵ�
 */

public class SnakeD {
	//�ߵ�����
	private LinkedList<Node>body1;
	private LinkedList<Node>body2;
	//�ߵ��˶�����Ĭ������
	private Direction direction1 = Direction.LEFT;
	private Direction direction2 = Direction.A;
	//���Ƿ����
	private boolean isLiving1 = true;
	private boolean isLiving2 = true;
	
	//�����������ڴ���Snake����ʱִ��
	public SnakeD() {
		//��ʼ��������
		initSnake();
	}
	
	
	private void initSnake() {
		//��������
		body1=new LinkedList<>();
		body2=new LinkedList<>();
		//���������ڵ�
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
	
	//�߻������ߵ���ͷ�����ƶ�
	//�������ƶ�������ͷ���˶��������һ���ڵ㣬Ȼ�����β�Ľڵ�ɾ��
	public void move() {
		if(isLiving1) {
			//��ȡ��ͷ
			Node head1 = body1.getFirst();
			switch (direction1) {
			case UP:
				//����ͷ���������һ���ڵ�
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
			//ɾ�����Ľڵ�
			body1.removeLast();
			
			//�ж����Ƿ�ײǽ
			head1 = body1.getFirst();
			if(head1.getX()<1 || head1.getY()<1 || head1.getX()>=49 || head1.getY()>=34){
				isLiving1 = false;
			}
			
			//�ж����Ƿ������Լ�������
			for(int i = 1; i < body1.size(); i++) {
				Node node = body1.get(i);
				if(head1.getX() == node.getX() && head1.getY() == node.getY()) {
					isLiving1 = false;
				}
			}
			
			//�ж�����û�л�����ײ
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
				//����ͷ���������һ���ڵ�
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
			//ɾ�����Ľڵ�
			body2.removeLast();
			
			//�ж����Ƿ�ײǽ
			head2 = body2.getFirst();
			if(head2.getX()<1 || head2.getY()<1 || head2.getX()>=49 || head2.getY()>=34){
				isLiving2 = false;
			}
			
			//�ж����Ƿ������Լ�������
			for(int i = 1; i < body2.size(); i++) {
				Node node = body2.get(i);
				if(head2.getX() == node.getX() && head2.getY() == node.getY()) {
					isLiving2 = false;
				}
			}
			
			//�ж�����û�л�����ײ
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

	 //��ʳ�����ͷ��������һ���ڵ�
	public void eat1(Node food) {
		Node head1 = body1.getFirst();
		switch (direction1) {
		case UP:
			//����ͷ���������һ���ڵ�
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
		//����ͷ���������һ���ڵ�
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
