//package first;

/**
 * �ڵ��ࣺÿһ�����������ɸ��ڵ���ɵģ�ÿһ���ڵ��к���������ȷ��λ��
 */

import java.util.Random;

public class Node {
	private int x;
	private int y;
	
	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	public Node(int x,int y){
		this.x=x;
		this.y=y;
	}

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	//�������λ�õķ���
	public void random() {
		//����random����
		Random r = new Random();
		//������ɺ�����
		this.x = r.nextInt(50);
		//�������������
		this.y = r.nextInt(35);
	}
}
