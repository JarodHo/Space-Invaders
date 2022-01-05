import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	//creating objects such as characters, background, music/sound effects...
	Background background = new Background(0, 0);
	Player p = new Player(400, 700);
	Enemy[][] enemy = new Enemy[11][2];
	Enemy2[][] enemy2 = new Enemy2[11][2];
	Enemy3[] enemy3 = new Enemy3[11];
	UFO ufo = new UFO(900,0);
	Lazer lazer = new Lazer(1000,1000);
	boolean hitWall = false;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//have objects paint themselves
		//painting background
		background.paint(g);
		//paint player
		p.paint(g);
		//paint enemies
		ufo.paint(g);
		lazer.paint(g);
		g.drawRect(15, 220, 45, 60);
		
		//hit detection
		
		for(int i = 0; i < enemy2.length; i++) {
			for(int j = 0; j < enemy2[0].length; j++) {
				if(lazer.getX() >= enemy2[i][j].getX()+15 && lazer.getX() <= enemy2[i][j].getX()+60) {
					//START HERE
					//might want to create a 2d array of booleans to keep track of enemies that were hit?
				}
			}
		}
		
		for(int i = 0; i < enemy.length; i++) {
			for(int j = 0; j < enemy[0].length; j++) {
				enemy[i][j].paint(g);
				
			}
		}
		for(int i = 0; i < enemy2.length; i++) {
			for(int j = 0; j < enemy2[0].length; j++) {
				enemy2[i][j].paint(g);
			}
		}
		for(int i = 0; i < enemy3.length; i++) {
			enemy3[i].paint(g);
		}
		
		//sync movement
		if(enemy[10][0].getX() >= 840) {
			hitWall = true;
		for(int i = 0; i < enemy.length; i++) {
			for(int j = 0; j < enemy[0].length; j++) {
				enemy[i][j].setY(enemy[i][j].getY()+20);
				enemy2[i][j].setY(enemy2[i][j].getY()+20);
				enemy3[i].setY(enemy3[i].getY()+10);
			}
		}
		
		}else if (enemy[0][0].getX() <= 0) {
			hitWall = false;
			for(int i = 0; i < enemy.length; i++) {
				for(int j = 0; j < enemy[0].length; j++) {
					enemy[i][j].setY(enemy[i][j].getY()+20);
					enemy2[i][j].setY(enemy2[i][j].getY()+20);
					enemy3[i].setY(enemy3[i].getY()+10);
				}
			}
		}
		
		if(hitWall) {
			for(int i = 0; i < enemy.length; i++) {
				for(int j = 0; j < enemy[0].length; j++) {
					enemy[i][j].setSpeedX(-0);
					enemy2[i][j].setSpeedX(-0);
					enemy3[i].setSpeedX(-0);
				}
			}
		}else if (!hitWall) {
			for(int i = 0; i < enemy.length; i++) {
				for(int j = 0; j < enemy[0].length; j++) {
					enemy[i][j].setSpeedX(0);
					enemy2[i][j].setSpeedX(0);
					enemy3[i].setSpeedX(0);
				}
		}
		}
		//ufo movement
		if(ufo.getX() <= -2400) {
			ufo.setX(1200);
		}
	}
	
	
	
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();

	}
	
	public Frame() {
		JFrame f = new JFrame("Space Invaders");
		f.setSize(new Dimension(900, 900));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		for(int i = 0; i < enemy.length; i++) {
			for(int j = 0; j < enemy[0].length; j++) {
				enemy[i][j] = new Enemy((i*60)-10,100+(j*60));
			}
		}
		for(int i = 0; i < enemy2.length; i++) {
			for(int j = 0; j < enemy2[0].length; j++) {
				enemy2[i][j] = new Enemy2((i*60),220+(j*60));
			}
		}
		for(int i = 0; i < enemy3.length; i++) {
			enemy3[i] = new Enemy3((i*60)+1,40);
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		
		if(arg0.getKeyCode() == 37) {
			p.setX(p.getX()-7);
		}
		if(arg0.getKeyCode() == 39) {
			p.setX(p.getX()+7);
		}
		if(arg0.getKeyCode() == 32) {
			lazer.setX(p.getX()+30);
			lazer.setY(p.getY()-40);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
