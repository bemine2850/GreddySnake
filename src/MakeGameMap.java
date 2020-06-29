import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
/*
 * TODO:
 * ������ɵڶ���������֮���������ѡ
 * */
public class MakeGameMap extends JPanel implements MouseMotionListener,MouseListener,ActionListener{

	//����ѡ�������յ�
	Point startPoint;
	Point endPoint;
	int xStart=1;
	int yStart=1;
	int xEnd=1;
	int yEnd=1;
	//������
	Point doorPoint=new Point();
	boolean click=false;
	int xDoor=-1;
	int yDoor=-1;
	int doorNum=2;
	boolean paint=false;//�Ƿ����ڻ�ͼ
	GameMap newMap=new GameMap();//�½���ͼ
	public MakeGameMap(){
		this.setBounds(0, 0, 1200, 800);
		this.setLayout(null);
		this.setBackground(Color.decode("#e5f8ff"));
		
		init();
	
	   // this.add(p1);
		this.setVisible(true);
		startPoint=new Point(0,0);
		endPoint=new Point(0,0);
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		}
	
	public void init(){
//		JPanel p = new JPanel();
//		this.add(p);
//		p.setBounds(1050, 150, 120, 700);
//		p.setBackground(Color.white);
//		p.setLayout(new GridLayout(8,1));//7��1��ˮƽ���10����ֱ���60
		JLabel label=new JLabel("��ͼ��:");
		label.setFont(new Font("����",Font.PLAIN,20));
		label.setForeground(Color.red);  //����ǰ��ɫ�����Ǹı��ǩ������ɫ
		label.setBounds(1050, 40, 100, 30);
		this.add(label);
		//�ı���
		JTextField text = new JTextField();  //�ı���������ʾ���ݵ�����Ϊ16
	    text.setFont(new Font("����",Font.BOLD,20));  //��������������������ʽ����С
		text.setBounds(1050, 80, 120, 40);
        this.add(text);
		
		//���ޱ߿�
		JButton btnBorder=new JButton("���ޱ߿�");
		btnBorder.addActionListener(this);
		btnBorder.setBounds(1050,150,120,50);
		this.add(btnBorder);
		//�����ϰ���
		JButton btnAddWall=new JButton("�����ϰ���");
		btnAddWall.addActionListener(this);
		btnAddWall.setBounds(1050,230,120,50);
		this.add(btnAddWall);
		//����һ�Դ�����
		JButton btnAddDoor=new JButton("���Ӵ�����");
		btnAddDoor.addActionListener(this);
		btnAddDoor.setBounds(1050,310,120,50);
		this.add(btnAddDoor);
		//�������
		JButton btnClearWall=new JButton("�������");
		btnClearWall.addActionListener(this);
		btnClearWall.setBounds(1050,390,120,50);
		this.add(btnClearWall);
		//��յ�ͼ
		JButton btnClearMap=new JButton("��յ�ͼ");
		btnClearMap.addActionListener(this);
		btnClearMap.setBounds(1050,470,120,50);
		this.add(btnClearMap);
		//�����ͼ	
		JButton btnSave=new JButton("����");
		btnSave.addActionListener(this);
		btnSave.setBounds(1050,550,120,50);
		this.add(btnSave);
		
	}
	
	public void paintComponent(Graphics g){//��ͼ
		super.paintComponent(g);
		
		g.drawRect(25, 40, 1000, 700);//ʵ����Ϸ����Ϊ1000*700
		//������
		for(int i = 0; i <= 28; i++)
		{
			g.drawLine(25, i*25+40, 1025, i*25+40);
		}
		for(int i = 0; i <= 40; i++) {
			g.drawLine(i*25+25, 40, i*25+25, 740);
		}
		//�ػ���ͼ
//		g.setColor(Color.gray);
		for(int i=0;i<28;i++){
			for(int j=0;j<40;j++){
				if(newMap.get(i, j)==1){
					g.setColor(new Color(139, 170, 65));
					g.fillRect(j*25+25, i*25+40, 25, 25);
				}else if(newMap.get(i, j)>1){
					g.setColor(new Color(255, 0, 0));
					g.fillRect(j*25+25,i*25+40 , 25, 25);
				}
			}
		}
		//���ѡ������
		xStart=startPoint.x;
		yStart=startPoint.y;
		xEnd=endPoint.x;
		yEnd=endPoint.y;
		int t;
		if(xStart>xEnd)
		{
			t=xStart;
			xStart=xEnd;
			xEnd=t;
		}
		if(yStart>yEnd)
		{
			t=yStart;
			yStart=yEnd;
			yEnd=t;
		}
		xStart=(xStart-25)/25;
		yStart=(yStart-40)/25;
		xEnd=(xEnd-25)/25;
		yEnd=(yEnd-40)/25;
		if(xEnd>=40)
			xEnd=39;
		if(yEnd>=28)
			yEnd=27;
//		g.setColor(Color.blue);
		g.setColor(new Color(0,0,255,100));
		if(!paint&&!click&&xStart>=0&&yStart>=0&xEnd>=0&&yEnd>=0){//���ж���������Ϊ�˽��ҳ���ʼ����bug
			g.fillRect(xStart*25+25, yStart*25+40, (xEnd-xStart+1)*25, (yEnd-yStart+1)*25);
		}
		
		System.out.println(xStart+" "+yStart+" "+xEnd+" "+yEnd);
		
		paint=false;//good
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf=new JFrame("�Զ����ͼ");
		jf.setLayout(null);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setBounds(200, 150, 1200,800);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().add(new MakeGameMap());
		
        
	}

	public void mouseDragged(MouseEvent e) {//�϶�
		// TODO Auto-generated method stub
		endPoint.setLocation(e.getX(), e.getY());
		
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		startPoint.setLocation(e.getX(),e.getY());
	}

	int border=0;//�Ƿ��б߿�
	public void actionPerformed(ActionEvent e) {
		paint=true;
		// TODO Auto-generated method stub
//		if(e.getSource()==btnBorder){
//			
//		}
		if(e.getActionCommand()=="���ޱ߿�"){
			border=border^1;
			for(int i=0;i<40;i++){
				newMap.set(0, i, border);
				newMap.set(27,i,border);
			}
			for(int i=0;i<28;i++){
				newMap.set(i, 0, border);
				newMap.set(i, 39, border);
			}
		}else if(e.getActionCommand()=="�����ϰ���"){
			for(int i=yStart;i<=yEnd;i++){
				for(int j=xStart;j<=xEnd;j++){
					newMap.set(i, j, 1);
				}
			}
		}else if(e.getActionCommand()=="�������"){
			
			for(int i=yStart;i<=yEnd;i++){
				for(int j=xStart;j<=xEnd;j++){
					if(newMap.get(i, j)>1){
						for(int m=0;m<28;m++){//���÷�Χ���д����ţ���Ҳ����һ�Դ���������
							for(int n=0;n<40;n++){
								if(newMap.get(m, n)==newMap.get(i, j)&&i!=m&&j!=n){
									newMap.set(m, n, 0);
									break;
								}
							}
						}
					}
					newMap.set(i, j, 0);
				}
			}
		}else if(e.getActionCommand()=="���Ӵ�����"){
			click=true;
		}else if(e.getActionCommand()=="��յ�ͼ"){
			for(int i=0;i<28;i++){
				for(int j=0;j<40;j++){
					newMap.set(i, j, 0);
				}
			}
		}else if(e.getActionCommand()=="����"){
			/*
			 * �ж��ļ����Ƿ��Ѹ�
			 * */
			File file = new File("D:\\ccc.txt");
			newMap.writeMap(file);
		}
		repaint();
	}

	public void mouseClicked(MouseEvent e) {//���
		// TODO Auto-generated method stub
		startPoint.setLocation(e.getX(), e.getY());
		endPoint.setLocation(e.getX(), e.getY());
		if(click){
			doorPoint.setLocation(e.getX(), e.getY());
			xDoor=doorPoint.x;
			yDoor=doorPoint.y;
			xDoor=(xDoor-25)/25;
			yDoor=(yDoor-40)/25;
			if(doorNum%2==1)
				newMap.set(yDoor, xDoor, doorNum-1);
			else
				newMap.set(yDoor, xDoor, doorNum);
			doorNum++;
			if(doorNum%2==0)
				click=false;
		}
		repaint();
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}