import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GameStage extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private int currentScore;			//紀錄小鴨現在的位置
	private int afterScore;				//現在的分數
	private int scoreToWin;				//破關分數
	private int duckCurrentY;			//小鴨Y位置
	private int duckCurrentX;			//小鴨X位置
	private int duckAnchorY = 300;		//小鴨平衡位置
	private int duckAnchorX = 100;		//小鴨X初始位置
	private int duckDirection;			//小鴨方向，向上為1向下為0
	private int backCurrentX;			//背景X位置
	private int ballCurrentX;			//球X位置
	private BufferedImage duckImage;	
	private BufferedImage backImage;
	private BufferedImage ballImage;
	private BufferedImage winImage;
	public JLabel score;
	private int i;
	
	public GameStage(){
		
		//讀圖
		try{
			duckImage = ImageIO.read(new File("./src/res/duck.png"));
			backImage = ImageIO.read(new File("./src/res/h.png"));
			ballImage = ImageIO.read(new File("./src/res/b.png"));
			winImage = ImageIO.read(new File("./src/res/win.png"));
			
		}catch(IOException e) {
			System.out.println("Image input error.");
		}
		
		//位置初始化
		ballCurrentX = 800;
		backCurrentX = 0;
		duckCurrentY = duckAnchorY;
		duckCurrentX = duckAnchorX;
		
		//建立label顯示分數
		score = new JLabel("Score : "+afterScore);
		score.setFont(new  Font("Dialog", 0, 36));
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(score);
		scoreToWin = 10;
		
	}
	
	public void duckMove(){
		
		//小鴨飄移
		if (duckDirection == 1 && this.duckCurrentY > this.duckAnchorY+10) {
			duckDirection = 0;
		} else if (duckDirection == 0 && this.duckCurrentY < this.duckAnchorY-10){
			duckDirection = 1;
		}
			// if direction is UP, increase duckCurrentY, otherwise decrease duckCurrentY
		if (duckDirection == 1) this.duckCurrentY ++;
		
		//球的漂移方向跟小鴨相反
		else if( duckDirection == 0) this.duckCurrentY --;
		
	}
	
	public void backMove(){
		
		//背景移動，每1分移動20單位，afterScore會記錄現在真正的分數，currentScore移動前的分數，當移動完20單位時，currentScore才會往上加
		//若afterScore == currentScore代表小鴨已達到目前分數的位置，則不再往前進。
		if(backCurrentX > -315 && currentScore != afterScore){
			if(i<20){
				backCurrentX --;
				ballCurrentX --;
				i++;
			}else{
				currentScore++;
				i=0;
			}
		}
		//當背景移到底時，換小鴨自行移動，製造出不斷前進的感覺
		else if(duckCurrentX != ballCurrentX -120 && currentScore != afterScore) {
			if(i<20){
				duckCurrentX++;
				i++;
			}
			else{
				currentScore++;	
				i=0;
			}
		}
		//System.out.println(backCurrentX);
	}
	
	public void add(){
		
		//當輸入的值是正確的時候，分數往上加並把correct設回初始值false
		if(Typing.correct == true){
			afterScore++;
			Typing.correct = false;
		}
	}
	
	@Override
	public void run() {
		
		duckCurrentY = duckAnchorY;
		int i=0;
		while(true){
			
			duckMove();
			backMove();
			if(afterScore <= scoreToWin)//遊戲還沒結束時(現在分數尚未高過破關分數)，檢查是否符合加分條件
				add();
			score.setText("Score : "+afterScore);
			this.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println(currentScore+"**"+afterScore);
			if(afterScore >= scoreToWin){
				try {
					Words.outputFile();
				} catch (IOException e) {}
				
			}
		}
		
	}
	
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(Color.cyan);
		
		g.drawImage(this.backImage, backCurrentX, 0, this);
		g.drawImage(this.duckImage, duckCurrentX, duckCurrentY, this);
		g.drawImage(this.ballImage, ballCurrentX, 2*duckAnchorY-duckCurrentY, this);
		if(afterScore >= scoreToWin)
			g.drawImage(this.winImage, 200, 150, this);	
		
	}
	
}
