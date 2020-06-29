package first;

/**
 * 节点类：每一条蛇是由若干个节点组成的，每一个节点有横纵坐标来确定位置
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
	
	//随机生成位置的方法
	public void random() {
		//创建random对象
		Random r = new Random();
		//随机生成横坐标
		this.x = r.nextInt(50);
		//随机生成纵坐标
		this.y = r.nextInt(35);
	}
}
