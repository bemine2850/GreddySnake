/*���˵�ҳ�������˫��ģʽ���Զ����ͼ���������к��˳���Ϸ��ť
 * 
 * */
import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class menu extends JFrame {
	private boolean flag=true; //���������ж����ֲ��ŵı�־
	public static int choose_number = 0;  //������ǽ�����ǵ��ˣ�1������˫��ģʽ��2��
/**/	@SuppressWarnings("deprecation")
	public menu() {
		this.setTitle("��Ϸ�˵�ҳ");
		this.setBounds(350,80, 1100, 810);
		this.setResizable(false);   
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setVisible(true);
		
		JPanel p=(JPanel)this.getContentPane();
		p.setLayout(null);    //��������Ǿ��Բ���
		p.setVisible(true);
		
		Icon icon = new ImageIcon("img/mview.jpg");
		JLabel l= new JLabel(icon); 
		l.setBounds(0, 0,icon.getIconWidth(), icon.getIconHeight());
		p.add(l);
		l.setVisible(true);
		
		JPanel p1 = new JPanel();      //��������ѡ��ť
		p1.setBounds(650,220, 220, 600);
		//p1.setBackground(Color.white);
		p1.setOpaque(false);  //������Ϊ͸��
		l.add(p1);
		p1.setLayout(new GridLayout(6,1,10,60));    //11��4�У�ÿ�и�60��ÿ�п�110,ˮƽ���10����ֱ���60
		p1.setVisible(true);
/*---------------------------------ѡ��ť����-----------------------------------------*/		
		//JButton btn = new JButton("sdc");
		JButton btn_1 = new JButton(new ImageIcon("img/n4.png"));
		JButton btn_2 = new JButton(new ImageIcon("img/n6.png"));
		JButton btn_3 = new JButton(new ImageIcon("img/n7.png"));
		//JButton btn_4 = new JButton(new ImageIcon("img/n8.png"));
		JButton btn_5 = new JButton(new ImageIcon("img/n5.png"));
	   // p1.add(btn);
		 JLabel label_1 = new_font(" �� �� ģ ʽ");
		 JLabel label_2 = new_font(" ˫ �� ģ ʽ");
		 JLabel label_3 =  new_font(" �� �� �� �� ");
		 //JLabel label_4 = new_font(" �� �� �� ͼ");
		 JLabel label_5 = new_font(" �� �� �� Ϸ");
		 btn_1.add(label_1);btn_2.add(label_2);btn_3.add(label_3);btn_5.add(label_5);
		p1.add(btn_1);p1.add(btn_2);p1.add(btn_3);p1.add(btn_5);
		//btn_4.add(label_4);p1.add(btn_4);
		
/*---------------------------------���ֿ���-----------------------------------------*/	
		
		File f = new File("music/Hello.wav");//����Ǿ���·�����Ե�ʱ���޸�һ��
		URL url = null;
		try {
			 url = f.toURL();
	
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
/**/		final AudioClip audioclip = Applet.newAudioClip(url);
/**/		final JLabel l5 = new JLabel(new ImageIcon("img/����.png"));
		l5.setBounds(920, 20, 100, 100);
		l.add(l5);	
		l5.setVisible(true);
		audioclip.loop();
		
/*---------------------------------���ּ����¼�-----------------------------------------*/	
		//������Ϸ
/**/		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();   //��ǰҳ��ر�
				audioclip.stop();   //���ֹر�
				choose_number = 1;
				new choose();
			}
		});
		//˫����Ϸ
/**/		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				audioclip.stop();
				dispose();   
				choose_number = 2;
				new choose();
			}
		});
			//������ͼS
//			btn_4.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						
//						JFrame jf=new JFrame("�Զ����ͼ");
//						jf.setResizable(false);
//						jf.setVisible(true);
//						jf.setBounds(200, 150, 1200,800);
//						jf.getContentPane().add(new MakeGameMap());
//						
//					}
//				});
		//������Ϸ
	    btn_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();   //��ǰҳ��ر�
					audioclip.stop();
					new view1();
					
				}
			});
	//���ֲ��ż���
	    l5.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				if(flag) {
					l5.setIcon(new ImageIcon("img/bin.png"));
					audioclip.stop();
					flag = false;
					
				}
				else {
					l5.setIcon(new ImageIcon("img/����.png"));
					audioclip.play();
					flag = true;
				}
				
			
			}
		});
		
	  this.setVisible(true);
	}
	
/*---------------------------------��������-----------------------------------------*/		
	private JLabel new_font(String text) {
		 JLabel l = new JLabel(text);
		 l.setFont(new Font("����",Font.PLAIN,28));
	 	 l.setForeground(Color.white);
	 	 return l;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      new menu() ;
	}

}
//	static void playmusic() {
//		try {			
//			URL cb;		
////			File f=new File("music/Hello.wav");   
////			String path = f.getAbsolutePath();
//			//path = path.replace("/", "//");
//			File f = new File("G:\\eclipse-workspace\\Menu\\music\\Hello.wav"); // ����������������ļ����ڵ�·��			
//			cb = f.toURL();			
//			AudioClip aau;			
//			aau = Applet.newAudioClip(cb);					
//			//aau.play();				
//			aau.loop();//ѭ������			
//			System.out.println("���Բ���");			
//			// ѭ������ aau.play()			
//			//���� aau.stop()ֹͣ���� 		
//			} catch (MalformedURLException e)
//			{						
//				e.printStackTrace();		
//				}
//		}
		
	


