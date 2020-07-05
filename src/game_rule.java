//package menu_view;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class game_rule extends JFrame {
	public game_rule() {
		setBounds(650,150, 550, 700); 
		setTitle("��Ϸ����");
		setResizable(false);     //�����Ƿ�����û����ڴ�С
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JTextArea jt = new JTextArea(25,60);
		jt.setTabSize(2);  // ����Tab�����ľ���
		jt.setLineWrap(true);//�����Զ����й���
		jt.setWrapStyleWord(true);  //����Ϻ������ֹ���
		jt.setFont(new Font("����",Font.BOLD,25));
		
		File f=new File("file/rule.txt");   
		String path = f.getAbsolutePath();
		path = path.replace("/", "//");
		readTxt(path,jt);    //��ȡtxt�ļ��е����ݣ�����ʾ���ı����С�
		
		
		jt.setBackground(Color.decode("#99e6e6"));
		c.add(jt,BorderLayout.CENTER);  //�ı�����ж���
		
		//���ذ�ť
		JButton b1 = new JButton(new ImageIcon("img/n17.png"));
		//b1.setSize(500,60);
		JLabel jl = new JLabel("  ��  �� �� Ϸ �� ʼ �� ҳ ��");
		 jl.setFont(new Font("����",Font.PLAIN,30));
		 jl.setForeground(Color.white);
		
		 b1.add(jl);
		 c.add(b1,BorderLayout.SOUTH);
	     b1.addActionListener(new ActionListener() {
			
/***/			//@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
//				new view1();
				
			}
		});
		
		setVisible(true);
	}

	private void readTxt(String filepath,JTextArea jt) {
		try {
			String encoding="GBK";
			File file = new File(filepath);
			if(file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null){
					jt.append(lineTxt+"\n");
				}
				jt.setEditable(false);
				read.close();
			}
			else {
				System.out.println("û����ص���Ϸ��������ļ���");
			}
			
		}catch (Exception e) {
			System.out.println("���ļ�����");
			e.printStackTrace();
		}
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new  game_rule();
	}

}
