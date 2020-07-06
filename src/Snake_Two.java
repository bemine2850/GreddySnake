import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * TODO:
 * 传送门穿越方法
 * 计分方式，速度是否可变
 * 没有墙壁时蛇的移动方式
 * */

public class Snake_Two {

	//创建音乐播放类
	PlayMusic m=new PlayMusic();
	int score=0;//得分
	List<SnakeBody> bodyList = new ArrayList<SnakeBody>();//保存蛇的整个身体
	GameMap map;
	Food_Two food;
	Snake_Two snake;
	
//	private Direction direction;
	
	public Snake_Two(GameMap map){
		this.map=map;
	}
	
	public void addFood(Food_Two food){
		this.food=food;
	}
	
	public Snake_Two(int x,int y){
		SnakeBody body=new SnakeBody(x,y);
		bodyList.add(body);
	}
	
	public void move(int x,int y,Snake_Two snake){
		this.snake=snake;
		if(die(x,y,snake)){//在移动之前预判下一步会不会挂
			m.playSound("music\\death.wav");//播放死亡音效
			return;
		}
		for(int i=0;i<food.foodNum;i++){
			if(bodyList.get(0).getX()+x==food.foodX[i]&&bodyList.get(0).getY()+y==food.foodY[i])
			{
				eat(x,y);
				food.addFood(i);//补充食物
				return;
			}
		}
		if(map.get(bodyList.get(0).getY()+y, bodyList.get(0).getX()+x)>1){//进入传送门
			for(int i=0;i<map.height;i++){
				for(int j=0;j<map.width;j++){
					if(map.get(i, j)>1 && i!=bodyList.get(0).getY()+y && j!=bodyList.get(0).getX()+x){
//						bodyList.set(0, new SnakeBody(j,i));
						
						SnakeBody body=new SnakeBody(bodyList.get(0));
						bodyList.get(0).setX(j+x);
						bodyList.get(0).setY(i+y);
						bodyList.add(1,body);
						bodyList.remove(bodyList.size()-1);
						return;
					}
				}
			}
		}
		SnakeBody body=new SnakeBody(bodyList.get(0));
		bodyList.get(0).setX(bodyList.get(0).getX()+x);
		bodyList.get(0).setY(bodyList.get(0).getY()+y);
		bodyList.add(1,body);
		bodyList.remove(bodyList.size()-1);
		
	}
	
	
	
	public boolean die(int x,int y,Snake_Two snake){//判断蛇是否死亡
		this.snake=snake;
		SnakeBody head=new SnakeBody(bodyList.get(0));
		if(map.get(head.getY()+y,head.getX()+x)==1){
			GreddySnake_Two.start=false;
			System.out.print(head.getX()+x+" "+head.getY()+y);
			return true;
		}
		for(int i=1;i<bodyList.size()-1;i++){//是否咬自己
			if(head.getX()+x==bodyList.get(i).getX()&&head.getY()+y==bodyList.get(i).getY()){
				GreddySnake_Two.start=false;
				return true;
			}
		}
		
		for(int i=1;i<snake.bodyList.size()-1;i++) {//两条小蛇有没有碰撞
			if(head.getX()+x==snake.bodyList.get(i).getX()&&head.getY()+y==snake.bodyList.get(i).getY()) {
				GreddySnake_Two.start=false;
				return true;
			}
		}
		
		return false;
	}
	
	public void eat(int x,int y){
		m.playSound("music\\eat.wav");//播放吃到食物音效
		score+=5;
		SnakeBody newBody=new SnakeBody(bodyList.get(0).getX()+x,bodyList.get(0).getY()+y);
		bodyList.add(0,newBody);
		
	}
	
/**	 public Direction getDirection() {
			return getDirection();
	}
	 
	 public void setDirection(Direction direction) {
		 this.direction = direction;
	}
*/
}
