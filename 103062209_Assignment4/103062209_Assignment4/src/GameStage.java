import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GameStage extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private int currentScore;			//�����p�n�{�b����m
	private int afterScore;				//�{�b������
	private int scoreToWin;				//�}������
	private int duckCurrentY;			//�p�nY��m
	private int duckCurrentX;			//�p�nX��m
	private int duckAnchorY = 300;		//�p�n���Ŧ�m
	private int duckAnchorX = 100;		//�p�nX��l��m
	private int duckDirection;			//�p�n��V�A�V�W��1�V�U��0
	private int backCurrentX;			//�I��X��m
	private int ballCurrentX;			//�yX��m
	private BufferedImage duckImage;	
	private BufferedImage backImage;
	private BufferedImage ballImage;
	private BufferedImage winImage;
	public JLabel score;
	private int i;
	
	public GameStage(){
		
		//Ū��
		try{
			duckImage = ImageIO.read(new File("./src/res/duck.png"));
			backImage = ImageIO.read(new File("./src/res/h.png"));
			ballImage = ImageIO.read(new File("./src/res/b.png"));
			winImage = ImageIO.read(new File("./src/res/win.png"));
			
		}catch(IOException e) {
			System.out.println("Image input error.");
		}
		
		//��m��l��
		ballCurrentX = 800;
		backCurrentX = 0;
		duckCurrentY = duckAnchorY;
		duckCurrentX = duckAnchorX;
		
		//�إ�label��ܤ���
		score = new JLabel("Score : "+afterScore);
		score.setFont(new  Font("Dialog", 0, 36));
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(score);
		scoreToWin = 10;
		
	}
	
	public void duckMove(){
		
		//�p�n�Ʋ�
		if (duckDirection == 1 && this.duckCurrentY > this.duckAnchorY+10) {
			duckDirection = 0;
		} else if (duckDirection == 0 && this.duckCurrentY < this.duckAnchorY-10){
			duckDirection = 1;
		}
			// if direction is UP, increase duckCurrentY, otherwise decrease duckCurrentY
		if (duckDirection == 1) this.duckCurrentY ++;
		
		//�y���}����V��p�n�ۤ�
		else if( duckDirection == 0) this.duckCurrentY --;
		
	}
	
	public void backMove(){
		
		//�I�����ʡA�C1������20���AafterScore�|�O���{�b�u�������ơAcurrentScore���ʫe�����ơA���ʧ�20���ɡAcurrentScore�~�|���W�[
		//�YafterScore == currentScore�N��p�n�w�F��ثe���ƪ���m�A�h���A���e�i�C
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
		//��I�����쩳�ɡA���p�n�ۦ沾�ʡA�s�y�X���_�e�i���Pı
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
		
		//���J���ȬO���T���ɭԡA���Ʃ��W�[�ç�correct�]�^��l��false
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
			if(afterScore <= scoreToWin)//�C���٨S������(�{�b���Ʃ|�����L�}������)�A�ˬd�O�_�ŦX�[������
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
