//package menu_view;
import javax.swing.*;   //ͼ�ν����
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class view1 extends JFrame  {
	public view1() {
		setBounds(450,200, 900, 730); 
		setTitle("��Ϸ��ʼ����");
		setResizable(false);     //�����Ƿ�����û����ڴ�С
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);  //���Բ���
		c.setBackground(Color.decode("#e5f8ff"));
		//c.validate();//��֤�����е������ˢ��    
       // this.setContentPane(c);//����������������
		
		JLabel label = new JLabel();  //��ǩ
		label.setIcon(new ImageIcon("img/monster.jpg"));	//���ͼƬ
		label.setBounds(70, 30, 760, 420);
	    c.add(label);
	    
	    JButton b1 = new JButton(new ImageIcon("img/��ť2.png"));
	    JButton b2 = new JButton(new ImageIcon("img/��ť2.png"));
	    JButton b3 = new JButton(new ImageIcon("img/��ť2.png"));
	    JButton b4 = new JButton(new ImageIcon("img/��ť2.png"));
	    b1.setBounds(210, 500, 200, 60);
	    b2.setBounds(470, 500, 200, 60);
	    b3.setBounds(210, 590, 200, 60);
	    b4.setBounds(470, 590, 200, 60);
	
	    JLabel label_1 = new_font(" ̰ �� һ ��");
	    JLabel label_2 = new_font(" �� Ϸ �� ��");
	    JLabel label_3=  new_font(" ��         �� ");
	    JLabel label_4 = new_font(" ��          ��");
	     
	    b1.add(label_1);b2.add(label_2);b3.add(label_3);b4.add(label_4);
	    c.add(b1);c.add(b2);c.add(b3);c.add(b4);
	    
	    b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();   //��ǰҳ��ر�
				new menu();
				
			}
		});
	    b2.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent e) {
	 				new game_rule();
	 				
	 			}
	 		});
	    b3.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				new teamwork();
 				
 			}
 		});
	    b4.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				JFrame f1 = new JFrame();
 				JLabel lab = new JLabel("ѽѽѽ����ƭ�˰ɣ����ɶ��û�У�����~~~");
 				f1.getContentPane().add(lab);
 				f1.setBounds(750,600,350, 150); 
 				f1.setVisible(true);
 			}
 		});


		setVisible(true);
	}

	private JLabel new_font(String text) {
		 JLabel l = new JLabel(text);
		 l.setFont(new Font("����",Font.BOLD,30));
	 	 l.setForeground(Color.decode("#ccffff"));
	 	 return l;
		
	}
	

	public static void main(String[] args)
	{
		new view1();
	
	
	}

}
