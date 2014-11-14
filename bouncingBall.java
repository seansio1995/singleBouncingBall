//Chufan Xiao
//2014/11/14

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class bouncingBall extends JPanel{
	private final int boxWidth=640;
	private final int boxHeight=640;
	
	private float ballRadius=100;
	private float ballX=45+ballRadius;
	private float ballY=45+ballRadius;
	
	private float ballSpeedX=15;
	private float ballSpeedY=8;
	
	private final int updateRate=60;
	
	public bouncingBall(){
		this.setPreferredSize(new Dimension(boxWidth,boxHeight));
		Thread gameThread=new Thread(){
		public void run(){
			while(true){
				ballX+=ballSpeedX;
				ballY+=ballSpeedY;
				
				if(ballX-ballRadius<0)
				{
					ballSpeedX=-ballSpeedX;
					ballX=ballRadius;
				}
				else if (ballX+ballRadius>boxWidth)
				{
					ballSpeedX=-ballSpeedX;
					ballX=boxWidth-ballRadius;
				}
				
				if (ballY-ballRadius<0)
				{
					ballSpeedY=-ballSpeedY;
					ballY=ballRadius;
				}
				else if (ballY+ballRadius>boxHeight)
				{
					ballSpeedY=-ballSpeedY;
					ballY=boxHeight-ballRadius;
				}
			
			repaint();
			try{
				Thread.sleep(1000/updateRate);
			} catch(InterruptedException ex){}
		}
		}
	};
	 gameThread.start();
	}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, boxWidth, boxHeight);
			
			g.setColor(Color.RED);
			g.fillOval((int)(ballX-ballRadius), (int)(ballY-ballRadius),(int) ballRadius*2, (int)ballRadius*2);
		}
		
		public static void main(String[] args){
			javax.swing.SwingUtilities.invokeLater(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					JFrame frame=new JFrame("Bouncing Ball Game");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setContentPane(new bouncingBall());
					frame.pack();
					frame.setVisible(true);
				}
				
			});
		}
		
	}


