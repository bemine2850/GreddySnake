import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class AchievementRecord extends JFrame{
	class ST{//这个类用于从排行榜文件读取数据时，临时存储这些数据
		int score;
		long time;
	}
	public int score;
	private long start;
	private long end;
	//构造方法，初始化
	public AchievementRecord() {
		score=0;
		start=0;
		end=0;
	}
	//加分
	public void add() {
		score+=5;//吃一个加5分
	}
	//获取分数
	public int getScore() {
		return score;
	}
	//开始计时
	public void startTiming() {
		start=System.currentTimeMillis();
	}
	//结束计时
	public void endTiming() {
		end=System.currentTimeMillis();
	}
	//获得分秒形式的时间
	public String getTime() {
		String time;
		long gap=end-start;
		long minutes=gap/(1000*60);
		long seconds=gap%(1000*60)/1000;
		time=minutes+" m "+seconds+" s";
		return time;
	}
	//从文件读取,获得分秒形式的时间
	public String getTime(long gap) {
		String time;
		long minutes=gap/(1000*60);
		long seconds=gap%(1000*60)/1000;
		time=minutes+"m"+seconds+"s";
		return time;
	}
	//打印自己的分数和时间
	public void printScoreTime() throws IOException{
		
		/* 1.读取排行榜数据
		 * 2.将本次成绩加进去
		 * 3.并进行排序
		 * 4.最后写到文件中*/
		ArrayList<ST> list=new ArrayList<ST>();//存储ST对象
		FileInputStream fis=new FileInputStream(new File("file\\ranklist.txt"));
		DataInputStream dis=new DataInputStream(fis);
		while(dis.available()>0) {
			ST st=new ST();
			st.score=dis.readInt();
			st.time=dis.readLong();
			list.add(st);
		}
		fis.close();
		dis.close();
		ST st=new ST();
		//将本次成绩加进去
		st.score=score;
		st.time=end-start;
		list.add(st);
		//进行排序
		list.sort(new Comparator<ST>(){
			@Override
			public int compare(ST o1, ST o2) {
				// TODO Auto-generated method stub
				if(!(o1.score==o2.score)) {
					return o1.score>o2.score?-1:1;//分数从大到小,-1表示不需要交换顺序，1表示需要交换顺序
				}else {
					return o1.time<o2.time?-1:1;//时间从小到大
				}
				
			}
		});
		int myRank=0;
		for(ST st1:list) {
			if(st1.score==score&&st1.time==end-start)
				myRank=list.indexOf(st1)+1;
		}
		//写到文件中去
		FileOutputStream fos=new FileOutputStream(new File("file\\ranklist.txt"));
		DataOutputStream dos=new DataOutputStream(fos);
		for(ST st1:list) {
			dos.writeInt(st1.score);
			dos.writeLong(st1.time);
		}
		fos.close();
		dos.close();

		//进行显示
		this.setBounds(50, 50, 1000, 500);
		this.setTitle("成绩");
		this.setLayout(new BorderLayout());//边框布局
		
		JLabel title=new JLabel("您的成绩");
		title.setFont(new Font("微软雅黑",0,50));
		this.add(title,BorderLayout.NORTH);
		
		JLabel score=new JLabel();
		score.setText("<html><body>分数："+getScore()+"<br>   时间："+getTime()+"<br>本次成绩在排行榜中的排名为："+myRank);
		score.setFont(new Font("微软雅黑",0,50));
		this.add(score,BorderLayout.CENTER);
		
		
		JButton btn=new JButton("查看排行榜");
		
		this.add(btn,BorderLayout.SOUTH);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new AchievementRecord().printRanking();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		this.setVisible(true);
		
		
	}
	//打印排行榜
	public void printRanking() throws IOException {
		//创建流
		FileInputStream fis=new FileInputStream(new File("file\\ranklist.txt"));
		DataInputStream dis=new DataInputStream(fis);
		int score;
		long time;
		int i=1;
		String str="<html><body>名次&#9分数&#9时间<br>=====================================<br>";
		String tmp="";
		//只打印前10名
		while (dis.available()>0 && i<=10) {
			//从文件读取score和time(毫秒)
			score=dis.readInt();
			time=dis.readLong();
			tmp="<html><body>"+i+" &#9;"+score+" &#9;"+ getTime(time)+"<br>";
			str+=tmp+"\n";
			i++;
		}
		//关闭流
		fis.close();
		dis.close();
		
		//进行排行榜显示
		this.setBounds(50, 50, 500, 500);
		this.setTitle("排行榜");
		this.setLayout(new BorderLayout());//边框布局
		
		JLabel title=new JLabel("排行榜");
		title.setFont(new Font("宋体",0,50));
		this.add(title,BorderLayout.NORTH);
		
		JLabel st=new JLabel();
		st.setText(str);
		st.setFont(new Font("宋体",0,20));
		this.add(st,BorderLayout.CENTER);
		
		this.setVisible(true);
		
	}
	
}
