import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
/*
 * TODO:
 * 解决生成第二个传送门之后出现鼠标框选
 * */
public class MakeGameMap extends JPanel implements MouseMotionListener,MouseListener,ActionListener{

	//鼠标框选的起点和终点
	Point startPoint;
	Point endPoint;
	int xStart=1;
	int yStart=1;
	int xEnd=1;
	int yEnd=1;
	//传送门
	Point doorPoint=new Point();
	boolean click=false;
	int xDoor=-1;
	int yDoor=-1;
	int doorNum=2;
	boolean paint=false;//是否正在绘图
	GameMap newMap=new GameMap();//新建地图
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
//		p.setLayout(new GridLayout(8,1));//7行1列水平间距10，垂直间距60
		JLabel label=new JLabel("地图名:");
		label.setFont(new Font("楷书",Font.PLAIN,20));
		label.setForeground(Color.red);  //更改前景色，就是改变标签字体颜色
		label.setBounds(1050, 40, 100, 30);
		this.add(label);
		//文本框
		JTextField text = new JTextField();  //文本框最多可显示内容的列数为16
	    text.setFont(new Font("楷书",Font.BOLD,20));  //可以设置输入框字体的样式及大小
		text.setBounds(1050, 80, 120, 40);
        this.add(text);
		
		//有无边框
		JButton btnBorder=new JButton("有无边框");
		btnBorder.addActionListener(this);
		btnBorder.setBounds(1050,150,120,50);
		this.add(btnBorder);
		//增加障碍物
		JButton btnAddWall=new JButton("添加障碍物");
		btnAddWall.addActionListener(this);
		btnAddWall.setBounds(1050,230,120,50);
		this.add(btnAddWall);
		//增加一对传送门
		JButton btnAddDoor=new JButton("添加传送门");
		btnAddDoor.addActionListener(this);
		btnAddDoor.setBounds(1050,310,120,50);
		this.add(btnAddDoor);
		//清除物体
		JButton btnClearWall=new JButton("清除物体");
		btnClearWall.addActionListener(this);
		btnClearWall.setBounds(1050,390,120,50);
		this.add(btnClearWall);
		//清空地图
		JButton btnClearMap=new JButton("清空地图");
		btnClearMap.addActionListener(this);
		btnClearMap.setBounds(1050,470,120,50);
		this.add(btnClearMap);
		//保存地图	
		JButton btnSave=new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(1050,550,120,50);
		this.add(btnSave);
		
	}
	
	public void paintComponent(Graphics g){//画图
		super.paintComponent(g);
		
		g.drawRect(25, 40, 1000, 700);//实际游戏界面为1000*700
		//网格线
		for(int i = 0; i <= 28; i++)
		{
			g.drawLine(25, i*25+40, 1025, i*25+40);
		}
		for(int i = 0; i <= 40; i++) {
			g.drawLine(i*25+25, 40, i*25+25, 740);
		}
		//重画地图
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
		//鼠标选中区域
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
		if(!paint&&!click&&xStart>=0&&yStart>=0&xEnd>=0&&yEnd>=0){//该判断语句后面是为了解决页面初始化的bug
			g.fillRect(xStart*25+25, yStart*25+40, (xEnd-xStart+1)*25, (yEnd-yStart+1)*25);
		}
		
		System.out.println(xStart+" "+yStart+" "+xEnd+" "+yEnd);
		
		paint=false;//good
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf=new JFrame("自定义地图");
		jf.setLayout(null);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setBounds(200, 150, 1200,800);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().add(new MakeGameMap());
		
        
	}

	public void mouseDragged(MouseEvent e) {//拖动
		// TODO Auto-generated method stub
		endPoint.setLocation(e.getX(), e.getY());
		
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		startPoint.setLocation(e.getX(),e.getY());
	}

	int border=0;//是否有边框
	public void actionPerformed(ActionEvent e) {
		paint=true;
		// TODO Auto-generated method stub
//		if(e.getSource()==btnBorder){
//			
//		}
		if(e.getActionCommand()=="有无边框"){
			border=border^1;
			for(int i=0;i<40;i++){
				newMap.set(0, i, border);
				newMap.set(27,i,border);
			}
			for(int i=0;i<28;i++){
				newMap.set(i, 0, border);
				newMap.set(i, 39, border);
			}
		}else if(e.getActionCommand()=="添加障碍物"){
			for(int i=yStart;i<=yEnd;i++){
				for(int j=xStart;j<=xEnd;j++){
					newMap.set(i, j, 1);
				}
			}
		}else if(e.getActionCommand()=="清除物体"){
			
			for(int i=yStart;i<=yEnd;i++){
				for(int j=xStart;j<=xEnd;j++){
					if(newMap.get(i, j)>1){
						for(int m=0;m<28;m++){//若该范围内有传送门，则也将这一对传送门消除
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
		}else if(e.getActionCommand()=="添加传送门"){
			click=true;
		}else if(e.getActionCommand()=="清空地图"){
			for(int i=0;i<28;i++){
				for(int j=0;j<40;j++){
					newMap.set(i, j, 0);
				}
			}
		}else if(e.getActionCommand()=="保存"){
			/*
			 * 判断文件名是否已给
			 * */
			File file = new File("D:\\ccc.txt");
			newMap.writeMap(file);
		}
		repaint();
	}

	public void mouseClicked(MouseEvent e) {//点击
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