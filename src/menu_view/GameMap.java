import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class GameMap {

	int [][]dot =new int[28][40];
	
	public int get(int x,int y){//��ȡ��ͼ������Ϣ
		return dot[x][y];
	}
	/**
	 * thing:
	 * 0���յ�
	 * 1��ǽ
	 * >1:�����ţ�һ�ԣ�
	 * */
	public void set(int x,int y,int thing){//�༭��ͼ
		dot[x][y]=thing;
	}
	
	public void writeMap(File file){//�����ͼ
        System.out.println(file.exists());//���Ϊfalse����Ϊ����û��ccc.txt
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);//�ȴ���ccc.txt�������ڣ��򲻻ᴴ����
        } catch (FileNotFoundException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        for(int i=0;i<28;i++)
        {
        	for(int j=0;j<40;j++)
        	{
        		pw.print(this.get(i, j)+" ");
        	}
        	pw.println();
        }
        pw.close();
	}
	
	public void readMap(File file){//��ȡ��ͼ
		Scanner in = null;
		try {
			in=new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<28;i++)
        {
        	for(int j=0;j<40;j++)
        	{
        		this.set(i,j,in.nextInt());
        	}
        }
        in.close();
	}
}
