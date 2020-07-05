import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * TODO:
 * 传送门穿越方法
 * 计分方式，速度是否可变
 * 没有墙壁时蛇的移动方式
 * */

public class Snake {

	//创建音乐播放类
	PlayMusic m=new PlayMusic();
	int score=0;//得分
	List<SnakeBody> bodyList = new ArrayList<SnakeBody>();//保存蛇的整个身体
	GameMap map;
	Food food;
	public Snake(GameMap map/*,Food food*/){
		this.map=map;
//		this.food=food;
	}
	
	public void addFood(Food food){
		this.food=food;
	}
	
	public Snake(int x,int y){
		SnakeBody body=new SnakeBody(x,y);
		bodyList.add(body);
	}
	
	public void move(int x,int y){
		if(die(x,y)){//在移动之前预判下一步会不会挂
			m.playSound("music\\death.wav");//播放死亡音效
			GreddySnake.btnStart.setEnabled(true);
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
					if(map.get(i, j)==map.get(bodyList.get(0).getY()+y, bodyList.get(0).getX()+x) && (i!=bodyList.get(0).getY()+y || j!=bodyList.get(0).getX()+x)){
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
	
	public boolean die(int x,int y){//判断蛇是否死亡
		SnakeBody head=new SnakeBody(bodyList.get(0));
		if(map.get(head.getY()+y,head.getX()+x)==1){
			GreddySnake.start=false;
			GreddySnake.ar.startTiming();//停止计时
			return true;
		}
		for(int i=1;i<bodyList.size()-1;i++){//是否咬自己
			if(head.getX()+x==bodyList.get(i).getX()&&head.getY()+y==bodyList.get(i).getY()){
				GreddySnake.start=false;
				GreddySnake.ar.startTiming();//停止计时
				return true;
			}
		}
		return false;
	}
	
	public void eat(int x,int y){
		m.playSound("music\\eat.wav");//播放吃到食物音效
		score+=10;//吃到食物加10分
		SnakeBody newBody=new SnakeBody(bodyList.get(0).getX()+x,bodyList.get(0).getY()+y);
		bodyList.add(0,newBody);
		
	}
}
