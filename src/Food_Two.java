import java.util.Random;


public class Food_Two {
	int foodNum=20;
	int []foodX=new int[foodNum];
	int []foodY=new int[foodNum];
	GameMap map;
	Snake_Two snake1,snake2;
	Random random=new Random();
	
	public Food_Two(GameMap map,Snake_Two snake1,Snake_Two snake2){
		this.map=map;
		this.snake1=snake1;
		this.snake2=snake2;
		boolean flag;
		for(int i=0;i<foodNum;){
			foodX[i]=random.nextInt(GreddySnake_Two.gameBorderWidth);
			foodY[i]=random.nextInt(GreddySnake_Two.gameBorderHeight);
			//保证生成的食物出现在地图空白处且不和其他食物重合
			flag=true;
			for(int j=0;j<snake2.bodyList.size();j++)
			{
				if(snake2.bodyList.get(j).getX()==foodX[i]&&snake2.bodyList.get(j).getY()==foodY[i]){
					flag=false;
					break;
				}
			}
			for(int j=0;j<snake1.bodyList.size();j++)
			{
				if(snake1.bodyList.get(j).getX()==foodX[i]&&snake1.bodyList.get(j).getY()==foodY[i]){
					flag=false;
					break;
				}
			}
			if(map.get(foodY[i], foodX[i])>0){
				flag=false;
			}
			for(int j=0;flag&&j<i;j++){
				if(foodX[j]==foodX[i]&&foodY[j]==foodY[i]){
					flag=false;
				}
			}
			if(flag){
				i++;
			}
		}
	}
	
	public void addFood(int i){//传入参数为被吃掉的食物的坐标
		boolean flag=true;
		do{
			foodX[i]=random.nextInt(GreddySnake_Two.gameBorderWidth);
			foodY[i]=random.nextInt(GreddySnake_Two.gameBorderHeight);
			flag=true;
			if(map.get(foodY[i], foodX[i])>0){
				flag=false;
			}
			for(int j=0;flag&&j<snake2.bodyList.size();j++)
			{
				if(snake2.bodyList.get(j).getX()==foodX[i]&&snake2.bodyList.get(j).getY()==foodY[i]){
					flag=false;
					break;
				}
			}
			for(int j=0;flag&&j<snake1.bodyList.size();j++)
			{
				if(snake1.bodyList.get(j).getX()==foodX[i]&&snake1.bodyList.get(j).getY()==foodY[i]){
					flag=false;
					break;
				}
			}

			
			for(int j=0;flag&&j<i;j++){
				if(foodX[j]==foodX[i]&&foodY[j]==foodY[i]){
					flag=false;
				}
			}
		}while(!flag);
	}
}
