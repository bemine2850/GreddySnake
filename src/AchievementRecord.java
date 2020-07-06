import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class AchievementRecord extends JFrame{
	class ST{//��������ڴ����а��ļ���ȡ����ʱ����ʱ�洢��Щ����
		int score;
		long time;
	}
	public int score;
	private long start;
	private long end;
	
	JLabel l;
	
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
		this.setBounds(400, 200, 495, 770);
		this.setResizable(false);
//		this.setTitle("�ɼ�");
//		this.setLayout(new BorderLayout());//�߿򲼾�
		
		JPanel p=(JPanel)this.getContentPane();
		p.setLayout(null); 
		p.setVisible(true);   
		Icon icon = new ImageIcon("img/b3.png");
		l= new JLabel(icon);
		l.setBounds(0, 0,icon.getIconWidth(), icon.getIconHeight());
		p.add(l);
		l.setVisible(true);
		
		JLabel title=new JLabel("���ĳɼ�");
		title.setFont(new Font("����",Font.PLAIN,60));
		title.setForeground(Color.white);
		title.setBounds(130,20,440,200);
		l.add(title);
		
		JLabel score1=new JLabel();
		score1.setText("<html><body>������"+getScore());
		score1.setFont(new Font("����",0,50));
		score1.setForeground(Color.white);
		score1.setBounds(100,150,495,200);
		l.add(score1);
		
		JLabel score2=new JLabel();
		score2.setText("ʱ�䣺"+getTime());
		score2.setFont(new Font("����",0,50));
		score2.setForeground(Color.white);
		score2.setBounds(100,250,495,200);
		l.add(score2);
		
		JLabel score3=new JLabel();
		score3.setText("������"+myRank);
		score3.setFont(new Font("����",0,50));
		score3.setForeground(Color.white);
		score3.setBounds(100,350,495,200);
		l.add(score3);
		
		
		JButton btn=new JButton(new ImageIcon("img/bn1.png"));
		JLabel btnText = new JLabel("�� �� �� �� ��");
		btnText.setForeground(Color.white);
		btnText.setFont(new Font("����",Font.PLAIN,23));
		btn.setBounds(140, 550, 210, 60);
		btn.add(btnText);
		l.add(btn);
		
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

/**			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
*/
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
		String str="<html><body> ����&#9����&#9ʱ��<br>---------------<br>";
		String tmp="";
		//ֻ��ӡǰ10��
		while (dis.available()>0 && i<=10) {
			//���ļ���ȡscore��time(����)
			score=dis.readInt();
			time=dis.readLong();
			tmp="<html><body> "+i+" &#9;"+score+" &#9;"+ getTime(time)+"<br>";
			str+=tmp+"\n";
			i++;
		}
		//�ر���
		fis.close();
		dis.close();
		
		//�������а���ʾ
		this.setBounds(600, 100, 495, 770);
		this.setTitle("���а�");
		this.setLayout(new BorderLayout());//�߿򲼾�
		this.setResizable(false); 
		
		JPanel p=(JPanel)this.getContentPane();
		p.setLayout(null); 
		p.setVisible(true);   
		Icon icon = new ImageIcon("img/b3.png");
		l= new JLabel(icon);
		l.setBounds(0, 0,icon.getIconWidth(), icon.getIconHeight());
		p.add(l);
		l.setVisible(true);
		
		JLabel title=new JLabel("���а�");
		title.setFont(new Font("����",0,60));
		title.setForeground(Color.white);
		title.setBounds(150,0,495,200);
		l.add(title);
		
		JLabel st=new JLabel();
		st.setText(str);
		st.setFont(new Font("����",0,30));
		st.setForeground(Color.white);
		st.setBounds(120,150,495,500);
		l.add(st,BorderLayout.CENTER);
		
		this.setVisible(true);
		
	}
	
}
