import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Snake {

	//�������ֲ�����
	PlayMusic m=new PlayMusic();
	int score=0;//�÷�
	List<SnakeBody> bodyList = new ArrayList<SnakeBody>();//�����ߵ���������
	
	public Snake(){
		
	}
	
	public Snake(int x,int y){
		SnakeBody body=new SnakeBody(x,y);
		bodyList.add(body);
	}
	
	public void move(int x,int y){
		if(die(x,y)){//���ƶ�֮ǰԤ����һ���᲻���
			m.playSound("music\\death.wav");//����������Ч
			return;
		}
		if(GreddySnake.foodX==bodyList.get(0).getX()+x&&GreddySnake.foodY==bodyList.get(0).getY()+y){
			eat(x,y);
			return;
		}
		SnakeBody body=new SnakeBody(bodyList.get(0));
		bodyList.get(0).setX(bodyList.get(0).getX()+x);
		bodyList.get(0).setY(bodyList.get(0).getY()+y);
		bodyList.add(1,body);
		bodyList.remove(bodyList.size()-1);
		
	}
	
	public boolean die(int x,int y){//�ж����Ƿ�����
		SnakeBody head=new SnakeBody(bodyList.get(0));
		if(head.getX()+x<0||head.getX()+x>=GreddySnake.gameBorderWidth||head.getY()+y<0||head.getY()+y>=GreddySnake.gameBorderHeight){//�Ƿ���߽�
			GreddySnake.start=false;
			return true;
		}
		for(int i=1;i<bodyList.size()-1;i++){//�Ƿ�ҧ�Լ�
			if(head.getX()+x==bodyList.get(i).getX()&&head.getY()+y==bodyList.get(i).getY()){
				GreddySnake.start=false;
				return true;
			}
		}
		return false;
	}
	
	public void eat(int x,int y){
//		if(GreddySnake.foodX==bodyList.get(0).getX()&&GreddySnake.foodY==bodyList.get(0).getY()){
//			m.playSound("music\\eat.wav");//���ųԵ�ʳ����Ч
//			SnakeBody tempAct = new SnakeBody();
//			tempAct.setX(bodyList.get(bodyList.size()-1).getX());
//			tempAct.setY(bodyList.get(bodyList.size()-1).getY());
//			bodyList.add(tempAct);
//			Random ran=new Random();
//			GreddySnake.foodX=ran.nextInt(GreddySnake.gameBorderWidth);
//			GreddySnake.foodY=ran.nextInt(GreddySnake.gameBorderHeight);
//		}
		m.playSound("music\\eat.wav");//���ųԵ�ʳ����Ч
		SnakeBody newBody=new SnakeBody(bodyList.get(0).getX()+x,bodyList.get(0).getY()+y);
		bodyList.add(0,newBody);
		Random ran=new Random();
		GreddySnake.foodX=ran.nextInt(GreddySnake.gameBorderWidth);
		GreddySnake.foodY=ran.nextInt(GreddySnake.gameBorderHeight);
	}
}
