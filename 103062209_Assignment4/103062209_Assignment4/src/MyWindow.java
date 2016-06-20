import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private GameStage game;
	private Typing t;
	public static JTextField txt = new JTextField(15);;
	//public static Words word = new Words();
	
	public MyWindow(){
		
		this.setLayout(null);
		
		game = new GameStage();
		game.setPreferredSize(new Dimension(800,600));
		game.setBounds(400, 0, 800, 600);
		
		
		t = new Typing();
		t.setPreferredSize(new Dimension(382,600));
		t.setBounds(0, 0, 400, 550);
		
		
		
		txt.setBounds(50, 550, 300, 30);
		
		this.add(txt);
		this.add(t);
		this.add(game);
		
		//用thread讓左右兩邊(打字的Typing跟小鴨動畫的GameStage同時運作)
		Thread thread1 = new Thread(game);
		Thread thread2 = new Thread(t);
		
		thread1.start();
		thread2.start();
		
		
	}
}
