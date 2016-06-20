import java.io.*;
import java.util.*;

public class Words {
	
	public String[] imageName;				//圖檔名
	public String[] imageContent;			//此圖所代表的文字
	public static String[] unknownName;		//尚無法辨識的圖檔
	public static HashMap<String, String> map = new HashMap<String, String>(); //存取全部圖檔名(key)與其對應的文字(value)
	
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
		//讀檔
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
				tmpArray = tmpString.split("\\s");		//用空白分開，空白前為檔名(eg. 01-01.png)，其後為文字
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
