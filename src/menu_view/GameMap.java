import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class GameMap {

	int width=40;
	int height=28;
	int [][]dot =new int[height][width];
	File gameMap;
	public GameMap(){
		
	}
	public GameMap(String gameName) {
		// TODO Auto-generated constructor stub
		gameMap=new File(gameName);
		readMap(gameMap);
	}
	public int get(int x,int y){//获取地图坐标信息
		return dot[x][y];
	}
	/**
	 * thing:
	 * 0：空地
	 * 1：墙
	 * >1:传送门（一对）
	 * */
	public void set(int x,int y,int thing){//编辑地图
		dot[x][y]=thing;
	}
	
	public void writeMap(File file){//保存地图
        System.out.println(file.exists());//输出为false，因为本地没有ccc.txt
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);//先创建ccc.txt（若存在，则不会创建）
        } catch (FileNotFoundException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        for(int i=0;i<28;i++)
        {
        	for(int j=0;j<40;j++)
        	{
        		pw.print(this.get(i, j)+" ");
        	}
        	pw.println();
        }
        pw.close();
	}
	
	public void readMap(File file){//读取地图
		Scanner in = null;
		try {
			in=new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<28;i++)
        {
        	for(int j=0;j<40;j++)
        	{
        		this.set(i,j,in.nextInt());
        	}
        }
        in.close();
	}
}
