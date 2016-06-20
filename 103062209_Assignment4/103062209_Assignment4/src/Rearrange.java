import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Rearrange {
	private static String[][] out = new String [16][16];
	
	public static void readFile(String fileName){
		try{
			
			FileReader fw = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fw);
			String line, tmpString;
			String[] tmpArray = new String[2];
			
			int row, col;
			String name = new String();
			while(br.ready()){
				line = br.readLine();
				tmpString = line;
				tmpArray = tmpString.split("-");		
				row = Integer.parseInt(tmpArray[0]);
				tmpString = tmpArray[1];
				tmpArray = tmpString.split("\\.");
				col = Integer.parseInt(tmpArray[0]);
				tmpString = tmpArray[1];
				if(tmpString.equals("png ")){
					name = "_";
				}
				else{
					tmpArray = tmpString.split("\\s");
					name = tmpArray[1];
				}
				out[row][col] = name;
			}
			fw.close();
			
		}catch(IOException e){
			System.out.println(fileName+" input error");
		}
	}
	
	public static void main(String[] args){
		
		readFile(args[0]);
		readFile("./src/known_words.txt");
		int i, j;
		for(i=1; i<=15; i++){
			for(j=1; j<=15; j++){
				if(out[i][j] == null)
					System.out.print("_ ");
				else
					System.out.print(out[i][j]+" ");
			}
			System.out.println();
		}
	
		
	}
	
	
}
