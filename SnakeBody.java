/*
 *记录蛇的每一节身体的坐标 
 */
public class SnakeBody {
	
	private int x;
	private int y;
	
	public SnakeBody(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public SnakeBody(SnakeBody body){
		this.x=body.getX();
		this.y=body.getY();
	}
	
	public SnakeBody() {
		// TODO Auto-generated constructor stub
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
}
