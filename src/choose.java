/*
 * ѡ��ؿ�ҳ�棬�ɸ������˵�ѡ��ĵ�˫��ģʽ������ת
 * */
import javax.swing.*;   //ͼ�ν����

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class choose extends JFrame {
	static List<String> diyList;//�����Զ���ؿ���
	//����ʽ�˵�
	JPopupMenu popup=new JPopupMenu();
	JLabel l;
	JPanel p2;
	String diyFileName;//��������Ҽ�������Զ����ͼ��
	JLabel labelSelected;//��������Ҽ�������Զ����ǩ
	public choose() {
		diyList = new ArrayList<String>();
		
		readDIY();//��ȡ�Զ���ؿ��б�
		setBounds(950,120,495, 770); 
		setTitle("��Ϸѡ��ҳ��");
		setResizable(false);     //�����Ƿ�����û����ڴ�С
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//���ñ������
		JPanel p=(JPanel)this.getContentPane();
		p.setLayout(null); 
		p.setVisible(true);   
		Icon icon = new ImageIcon("img/b2.png");
		l= new JLabel(icon);      //֮��İ�ť��������������ǩ��
		l.setBounds(0, 0,icon.getIconWidth(), icon.getIconHeight());
		p.add(l);
		l.setVisible(true);
		//���ô�Źؿ��Ǳ�����
		JPanel p1 = new JPanel();     
		p1.setBounds(20,90, 440, 150);
		p1.setOpaque(false);
		//p1.setBackground(Color.white);
		l.add(p1);
		p1.setLayout(new GridLayout(2,4,6,5));   //4��6�У�ˮƽ���5����ֱ���5
		
		for(int i=0;i<10;i++)
		{
//			final JLabel label = new JLabel(i+1+"",new ImageIcon("img/star1.png"), JLabel.CENTER);
			final JLabel label = new JLabel(new ImageIcon("img/star1.png"));
			label.setName(i+1+"");
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.addMouseListener(new MouseAdapter(){
				//�����ǩ���л�������star2.png������ת����Ϸ����
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1) {
//						System.out.println(m.choose_number);
/**/				//		if(m.choose_number == 1) {
						
//						if(e.getModifiersEx()=="��1��"){
//							System.out.println(e.getComponent().getY());
						System.out.println(((JLabel) e.getSource()).getName());
//						}
						
						if(menu.choose_number==1){
//							label.setIcon(new ImageIcon("img/star2.png"));
/**/		//					setVisible(false);  //��������
							new GameStart(new GameMap("file\\��"+((JLabel) e.getSource()).getName()+"��.txt"));; //���뵥����Ϸ
						}
/**/				//		if(m.choose_number == 2) {
						if(menu.choose_number==2){
							setVisible(false);  //��������
							new GameStart_Two(new GameMap("file\\��"+((JLabel) e.getSource()).getName()+"��.txt")); //����˫����Ϸ
						}
					}
				}
			
			});
			p1.add(label);
		}
		p1.setVisible(true);
		
		
		showDIYmap();
		//����ȥʽ�˵�
		JMenuItem itemEdit=new JMenuItem("�༭");
		itemEdit.setActionCommand("edit");
		itemEdit.addActionListener(actionListener);
		popup.add(itemEdit);
		
		JMenuItem itemDelete=new JMenuItem("ɾ��");
		itemDelete.setActionCommand("delete");
		itemDelete.addActionListener(actionListener);
		popup.add(itemDelete);
		
		
		
		//  l������Ӱ�ť
		JButton b1 = new JButton(new ImageIcon("img/bn1.png"));
		JButton b2 = new JButton(new ImageIcon("img/bn1.png"));
		JLabel la1 = new JLabel("�� �� �� �� ͼ");
		la1.setForeground(Color.white);
		la1.setFont(new Font("����",Font.PLAIN,23));
		
		JLabel la2 = new JLabel(" �� �� ѡ ��");
		la2.setForeground(Color.white);
		la2.setFont(new Font("����",Font.PLAIN,23));
		
		b1.setBounds(28, 465, 210, 60);
		b2.setBounds(255, 465, 210, 60);
		b1.add(la1);b2.add(la2);
		l.add(b1);l.add(b2);
/*-------------------------��ť�ͱ�ǩ�����¼�------------------------------*/
		//�����Զ����ͼģ��MakeGameMap
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dispose();      //��ǰҳ��ر�
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
				new menu();
			}
		});
		//ѡ�йؿ�֮���Ǳ�ı�
		
		this.setVisible(true);
	}
	//��̬��ʾ�Զ���ؿ�
	public void showDIYmap(){
		//���ô���Զ����ͼ�Ǳ�
		p2 = new JPanel();    
		p2.setBounds(20,240, 440, 200);
		p2.setOpaque(false);//͸��
		//p2.setBackground(Color.white);
//		p2.setLayout(new GridLayout(5,4,7,5));  
		p2.setLayout(new FlowLayout(FlowLayout.LEFT,32,5));
		l.add(p2);
				
//		p2.add(new JLabel("  "));
		for(int i=0;i<diyList.size();i++){
					//TODO����һ��setName������getName����
//					JLabel label = new JLabel(i+1+"",new ImageIcon("img/star2.png"), JLabel.CENTER);
			JLabel label = new JLabel(new ImageIcon("img/star2.png"));
			label.setName(i+1+"");
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1) {//������
						if(menu.choose_number==1){//diyList.get(e.getSource()).getName())
							new GameStart(new GameMap("file\\"+diyList.get( Integer.valueOf(((JLabel) e.getSource()).getName())-1)+".txt")); //���뵥����Ϸ
						}
						if(menu.choose_number==2){
							new GameStart_Two(new GameMap("file\\"+diyList.get( Integer.valueOf(((JLabel) e.getSource()).getName())-1)+".txt")); //����˫����Ϸ
						}
					}else if(e.getButton() == MouseEvent.BUTTON3){//����Ҽ�
//								setVisible(false);
						System.out.println(e);
						diyFileName=diyList.get( Integer.valueOf(((JLabel) e.getSource()).getName())-1);
						popup.show(e.getComponent(), e.getX(), e.getY());
					}
				}
					
			});
			p2.add(label);
//			p2.add(new JLabel("         "));
		}
		p2.setVisible(true);
	}
	//��ȡ�Զ���ؿ��б�
	public void readDIY(){
		Scanner in = null;
		try {
			in=new Scanner(new File("file\\DIYList.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(in.hasNext()){
			diyList.add(in.nextLine());
			//System.out.println(in.nextLine());
		}
        in.close();
	}
	//�����µ��Զ���˵��б�д���ļ�
	public void writeDIY(){//�����ͼ
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("file\\DIYList.txt"));//�ȴ���ccc.txt�������ڣ��򲻻ᴴ����
        } catch (FileNotFoundException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        for(int i=0;i<diyList.size();i++)
        {
        	pw.println(diyList.get(i));
        }
        pw.close();
	}
	//�Ҽ��˵��б��������
	public ActionListener actionListener=new ActionListener() {
		 
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action=e.getActionCommand();
		
			if(action.equals("edit")){//�༭��ͼ
//				dispose();      //��ǰҳ��ر�
				JFrame jf=new JFrame("�Զ����ͼ");
				jf.setResizable(false);
				jf.setVisible(true);
				jf.setBounds(200, 150, 1200,800);
				MakeGameMap gameMap=new MakeGameMap(new GameMap("file\\"+diyFileName+".txt"));
				gameMap.text.setText(diyFileName);
				jf.getContentPane().add(gameMap);
			}else if(action.equals("delete")){//ɾ����ͼ
//				System.out.println()
				for(int i=0;i<diyList.size();i++){
					if(diyList.get(i).equals(diyFileName)){
						diyList.remove(i);
						System.out.println(diyList.size());
						break;
					}
				}
				new File("file\\"+diyFileName+".txt").delete();//ɾ���ļ�
				writeDIY();
				p2.setVisible(false);
				showDIYmap();
			}
		}
	};
	
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		new choose();
//	}

}
