import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AchievementRecord extends JFrame{
	class ST{//��������ڴ����а��ļ���ȡ����ʱ����ʱ�洢��Щ����
		int score;
		long time;
	}
	public int score;
	private long start;
	private long end;
	//���췽������ʼ��
	public AchievementRecord() {
		score=0;
		start=0;
		end=0;
	}
	//�ӷ�
	public void add() {
		score+=5;//��һ����5��
	}
	//��ȡ����
	public int getScore() {
		return score;
	}
	//��ʼ��ʱ
	public void startTiming() {
		start=System.currentTimeMillis();
	}
	//������ʱ
	public void endTiming() {
		end=System.currentTimeMillis();
	}
	//��÷�����ʽ��ʱ��
	public String getTime() {
		String time;
		long gap=end-start;
		long minutes=gap/(1000*60);
		long seconds=gap%(1000*60)/1000;
		time=minutes+" m "+seconds+" s";
		return time;
	}
	//���ļ���ȡ,��÷�����ʽ��ʱ��
	public String getTime(long gap) {
		String time;
		long minutes=gap/(1000*60);
		long seconds=gap%(1000*60)/1000;
		time=minutes+"m"+seconds+"s";
		return time;
	}
	//��ӡ�Լ��ķ�����ʱ��
	public void printScoreTime() throws IOException{
		
		/* 1.��ȡ���а�����
		 * 2.�����γɼ��ӽ�ȥ
		 * 3.����������
		 * 4.���д���ļ���*/
		ArrayList<ST> list=new ArrayList<ST>();//�洢ST����
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
		//�����γɼ��ӽ�ȥ
		st.score=score;
		st.time=end-start;
		list.add(st);
		//��������
		list.sort(new Comparator<ST>(){
			@Override
			public int compare(ST o1, ST o2) {
				// TODO Auto-generated method stub
				if(!(o1.score==o2.score)) {
					return o1.score>o2.score?-1:1;//�����Ӵ�С,-1��ʾ����Ҫ����˳��1��ʾ��Ҫ����˳��
				}else {
					return o1.time<o2.time?-1:1;//ʱ���С����
				}
				
			}
		});
		int myRank=0;
		for(ST st1:list) {
			if(st1.score==score&&st1.time==end-start)
				myRank=list.indexOf(st1)+1;
		}
		//д���ļ���ȥ
		FileOutputStream fos=new FileOutputStream(new File("file\\ranklist.txt"));
		DataOutputStream dos=new DataOutputStream(fos);
		for(ST st1:list) {
			dos.writeInt(st1.score);
			dos.writeLong(st1.time);
		}
		fos.close();
		dos.close();

		//������ʾ
		this.setBounds(50, 50, 1000, 500);
		this.setTitle("�ɼ�");
		this.setLayout(new BorderLayout());//�߿򲼾�
		
		JLabel title=new JLabel("���ĳɼ�");
		title.setFont(new Font("΢���ź�",0,50));
		this.add(title,BorderLayout.NORTH);
		
		JLabel score=new JLabel();
		score.setText("<html><body>������"+getScore()+"<br>   ʱ�䣺"+getTime()+"<br>���γɼ������а��е�����Ϊ��"+myRank);
		score.setFont(new Font("΢���ź�",0,50));
		this.add(score,BorderLayout.CENTER);
		
		
		JButton btn=new JButton("�鿴���а�");
		
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
	//��ӡ���а�
	public void printRanking() throws IOException {
		//������
		FileInputStream fis=new FileInputStream(new File("file\\ranklist.txt"));
		DataInputStream dis=new DataInputStream(fis);
		int score;
		long time;
		int i=1;
		String str="<html><body>����&#9����&#9ʱ��<br>=====================================<br>";
		String tmp="";
		//ֻ��ӡǰ10��
		while (dis.available()>0 && i<=10) {
			//���ļ���ȡscore��time(����)
			score=dis.readInt();
			time=dis.readLong();
			tmp="<html><body>"+i+" &#9;"+score+" &#9;"+ getTime(time)+"<br>";
			str+=tmp+"\n";
			i++;
		}
		//�ر���
		fis.close();
		dis.close();
		
		//�������а���ʾ
		this.setBounds(50, 50, 500, 500);
		this.setTitle("���а�");
		this.setLayout(new BorderLayout());//�߿򲼾�
		
		JLabel title=new JLabel("���а�");
		title.setFont(new Font("����",0,50));
		this.add(title,BorderLayout.NORTH);
		
		JLabel st=new JLabel();
		st.setText(str);
		st.setFont(new Font("����",0,20));
		this.add(st,BorderLayout.CENTER);
		
		this.setVisible(true);
		
	}
	
	
	public static void main(String[] args) throws IOException {
		AchievementRecord tmp=new AchievementRecord();
		tmp.printScoreTime();
	}
	
}
