import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Typing extends JPanel implements KeyListener, Runnable{
	private static final long serialVersionUID = 1L;
	public static String known = new String(), unknown = new String();
	private Words word = new Words();
	private BufferedImage knownImage;
	private BufferedImage unknownImage;
	private int currentY = 30;
	public static String knownAns = new String();
	public static String unknownAns = new String();
	public static boolean correct;
	

	public Typing(){
		MyWindow.txt.addKeyListener(this);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		generateImage();
		
	}
	
	public void addScore(){
		String a = new String(), b = new String();
		
		a = word.map.get(known);
		b = knownAns;
	
		if(a.equals(b)){			//���J���r��(b)����ɪ��ҦW�۲ŮɡAcorrect�]��true
			correct = true;
		}
		
	}
	
	public void generateImage(){
		while(true){
			currentY = 30;
			Random random = new Random();						//�Ϥ��ʵe�A��random���O��known��unknownn�ӧ���@�i�Ϥ�
			known = word.imageName[random.nextInt(51)];
			unknown = word.unknownName[random.nextInt(73)];
			
			//Ū��
			try{
			
				knownImage = ImageIO.read(new File("./src/img/known/"+known));
				unknownImage = ImageIO.read(new File("./src/img/unknown/"+unknown));
			
			}catch(IOException e) {
				System.out.println("Image input error.");
			}
			//�ϩ��U��
			while(currentY < 500){
				currentY++;
				this.repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				addScore();
				//����N���X���U�@��
				if(correct == true) break;
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String[] tmpArray = new String[2];
		String line = new String();
		String tmpString = new String();
		
		if(arg0.getKeyCode() == 10){
			//���Uenter�ɨ��Xjtextfield������
			line = MyWindow.txt.getText();
			tmpString = line;
			tmpArray = tmpString.split("\\s");
			knownAns = tmpArray[0];
			unknownAns = tmpArray[1];
			Words.map.put(unknown, unknownAns);
			MyWindow.txt.setText("");	//�M��
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(null);
		g.drawImage(this.knownImage, 60, currentY, this);
		g.drawImage(this.unknownImage, 200, currentY, this);
		
	}

	
}
