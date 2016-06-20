import java.io.*;
import java.util.*;

public class Words {
	
	public String[] imageName;				//���ɦW
	public String[] imageContent;			//���ϩҥN����r
	public static String[] unknownName;		//�|�L�k���Ѫ�����
	public static HashMap<String, String> map = new HashMap<String, String>(); //�s���������ɦW(key)�P���������r(value)
	
	public Words(){
		read();	
	}
	
	public static void outputFile() throws IOException{
		int i;
		FileWriter fw = new FileWriter("output.txt");
		for(i=0; i<73; i++)
			fw.write(unknownName[i]+" "+map.get(unknownName[i])+"\r\n");
		fw.close();
	}
	
	public void read(){
		//Ū��
		try{
			FileReader fw = new FileReader("./src/known_words.txt");
			FileReader fw2 = new FileReader("./src/unknown_words.txt");
			BufferedReader br = new BufferedReader(fw); 
			BufferedReader br2 = new BufferedReader(fw2); 
			String line, tmpString;
			String[] tmpArray = new String[2];
			
			int i=0;
			imageName = new String[100];
			imageContent = new String[100];
			unknownName = new String[100];
			
			
			while(br.ready()){
				line = br.readLine();
				tmpString = line;
				tmpArray = tmpString.split("\\s");		//�Ϊťդ��}�A�ťիe���ɦW(eg. 01-01.png)�A��ᬰ��r
				imageName[i] = tmpArray[0];
				imageContent[i] = tmpArray[1];
				map.put(imageName[i], imageContent[i]);				
				i++;
			}
			i=0;
			while(br2.ready()){
				line = br2.readLine();
				unknownName[i] = line;
				map.put(unknownName[i], "");	
				i++;
				//System.out.println(i);
			}
			fw.close();
			fw2.close();
		}catch(IOException e){}
	}
	
}
