/*
 * ѡ��ؿ�ҳ�棬�ɸ������˵�ѡ��ĵ�˫��ģʽ������ת
 * */
import javax.swing.*;   //ͼ�ν����
import java.awt.*;
import java.awt.event.*;

public class choose extends JFrame {
    menu m = new menu();   //������Ϊ��ʹ��ȫ�ֱ������е�˫���ж�ʱ���õĿ����� �� ���Ҿ�����һ������
   
	public choose() {
		setBounds(950,120,495, 770); 
		setTitle("��Ϸѡ��ҳ��");
		setResizable(false);     //�����Ƿ�����û����ڴ�С
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//���ñ������
		JPanel p=(JPanel)this.getContentPane();
		p.setLayout(null); 
		p.setVisible(true);   
		Icon icon = new ImageIcon("img/b2.png");
		JLabel l= new JLabel(icon);      //֮��İ�ť��������������ǩ��
		l.setBounds(0, 0,icon.getIconWidth(), icon.getIconHeight());
		p.add(l);
		l.setVisible(true);
		//���ô�Źؿ��Ǳ�����
		JPanel p1 = new JPanel();     
		p1.setBounds(20,100, 440, 250);
		p1.setOpaque(false);
		//p1.setBackground(Color.white);
		l.add(p1);
		p1.setLayout(new GridLayout(4,6,5,5));   //4��6�У�ˮƽ���5����ֱ���5
		for(int i=0;i<16;i++)
		{
			final JLabel label = new JLabel(new ImageIcon("img/star1.png"));
			label.addMouseListener(new MouseAdapter(){
				//�����ǩ���л�������star2.png������ת����Ϸ����
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println(m.choose_number);
						if(m.choose_number == 1) {
							label.setIcon(new ImageIcon("img/star2.png"));
							setVisible(false);  //��������
							new GameStart(new GameMap("d:\\ccc.txt"));; //���뵥����Ϸ
						}
						if(m.choose_number == 2) {
							setVisible(false);  //��������
							new MainFrame(); //����˫����Ϸ
						}
					}
				}
			
			});
			p1.add(label);
		}
		p1.setVisible(true);
		//  l������Ӱ�ť
		JButton b1 = new JButton(new ImageIcon("img/bn1.png"));
		JButton b2 = new JButton(new ImageIcon("img/bn1.png"));
		JLabel la1 = new JLabel("�� �� �� �� ͼ");
		la1.setForeground(Color.white);
		la1.setFont(new Font("����",Font.PLAIN,23));
		
		JLabel la2 = new JLabel(" �� �� ѡ ��");
		la2.setForeground(Color.white);
		la2.setFont(new Font("����",Font.PLAIN,23));
		
		b1.setBounds(28, 450, 210, 60);
		b2.setBounds(255, 450, 210, 60);
		b1.add(la1);b2.add(la2);
		l.add(b1);l.add(b2);
/*-------------------------��ť�ͱ�ǩ�����¼�------------------------------*/
		//�����Զ����ͼģ��MakeGameMap
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();      //��ǰҳ��ر�
				JFrame jf=new JFrame("�Զ����ͼ");
				jf.setResizable(false);
				jf.setVisible(true);
				jf.setBounds(200, 150, 1200,800);
				jf.getContentPane().add(new MakeGameMap());
				
			}
		});
		//�˳�ѡ�ػص����˵�menuҳ��
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();      //��ǰҳ��ر�
				
			}
		});
		//ѡ�йؿ�֮���Ǳ�ı�
		
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new choose();
	}

}
