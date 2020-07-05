/*团队分工的介绍页面
 * */
import javax.swing.*;  
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class teamwork extends JFrame {

	public teamwork() {
		setBounds(650,150, 550, 700); 
		setTitle("项目分工");
		setResizable(false);     //窗体是否可由用户调节大小
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JTextArea jt = new JTextArea(25,60);
		jt.setTabSize(2);  // 设置Tab键调的距离
		jt.setLineWrap(true);//激活自动换行功能
		jt.setWrapStyleWord(true);  //激活断航不断字功能
		jt.setFont(new Font("隶书",Font.BOLD,25));
		
		File f=new File("file/work.txt");   
		String path = f.getAbsolutePath();
		path = path.replace("/", "//");
		readTxt(path,jt);    //获取txt文件中的内容，并显示在文本域中。
		
		
		jt.setBackground(Color.decode("#99e6e6"));
		c.add(jt,BorderLayout.CENTER);  //文本域居中对齐
		
		//返回按钮
		JButton b1 = new JButton(new ImageIcon("img/n17.png"));
		//b1.setSize(500,60);
		JLabel jl = new JLabel("  返  回 游 戏 初 始 化 页 面");
		 jl.setFont(new Font("隶书",Font.PLAIN,30));
		 jl.setForeground(Color.white);
		
		 b1.add(jl);
		 c.add(b1,BorderLayout.SOUTH);
	     b1.addActionListener(new ActionListener() {
			
/***/			//@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new view1();
				
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
				System.out.println("没有相关分工介绍！");
			}
			
		}catch (Exception e) {
			System.out.println("读文件出错！");
			e.printStackTrace();
		}
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new  teamwork();
	}

}
