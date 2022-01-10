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
	boolean[][] trackEnemy = new boolean[11][2];
	boolean[][] canEnemyShoot = new boolean[11][2];
	Enemy2[][] enemy2 = new Enemy2[11][2];
	boolean[][] trackEnemy2 = new boolean[11][2];
	boolean[][] canEnemy2Shoot = new boolean[11][2];
	Enemy3[] enemy3 = new Enemy3[11];
	boolean[] trackEnemy3 = new boolean[11];
	boolean[] canEnemy3Shoot = new boolean[11];
	UFO ufo = new UFO(1200,0);
	Lazer lazer = new Lazer(1000,1000);
	boolean hitWall = false;
	boolean shot = false;
	boolean started = false;
	int score = 0;
	int lives = 3;
	Lives life1 = new Lives(800-166, 785);
	Lives life2 = new Lives(800-83, 785);
	Lives life3 = new Lives(800, 785);
	boolean alive = true;
	EnemyLazer[] enemyLazers = new EnemyLazer[11];
	boolean[] enemyShot = new boolean[11];
	Barrier[] barriers = new Barrier[20];
	int[] barriersHealth = new int[20];
	Barrier[] barriers2 = new Barrier[8];
	int[] barriers2Health = new int[8];
	Barrier b = new Barrier(500, 500);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//have objects paint themselves
		//painting background
		background.paint(g);
		//paint player
		if(lives >= 1) {
		p.paint(g);
		}
		//paint enemies
		ufo.paint(g);
		Font c = new Font ("Terminal", Font.BOLD, 50);
		g.setFont(c);
		g.setColor(Color.white);
		g.drawString("SCORE: "+score, 50, 850);
		g.drawString("Lives: ", 475, 850);
		for(int i = 0; i < enemyLazers.length; i++) {
			enemyLazers[i].paint(g);
		}
		
		b.paint(g);
		
		if(lives == 3) {
			life1.paint(g);
			life2.paint(g);
			life3.paint(g);
		}else if (lives == 2) {
			life1.paint(g);
			life2.paint(g);
		}else if (lives == 1) {
			life1.paint(g);
		}else if (lives == 0) {
			alive = false;
			shot = true;
		}
		
		for(int i = 0; i < barriers.length; i++) {
			if(barriersHealth[i] > 0) {
				barriers[i].paint(g);
			}
			if(barriersHealth[i] == 4) {
				barriers[i].changePicture("/imgs/Green 2 Barrier.png");
			}else if (barriersHealth[i] == 3) {
				barriers[i].changePicture("/imgs/Yellow Barrier.png");
			}else if (barriersHealth[i] == 2) {
				barriers[i].changePicture("/imgs/Yellow 2 Barrier.png");
			}else if (barriersHealth[i] == 1) {
				barriers[i].changePicture("/imgs/Red Barrier.png");
			}
		}
		
		for(int i = 0; i < barriers2.length; i++) {
			if(barriers2Health[i] > 0) {
				//barriers2[i].paint(g);
			}
			if(barriers2Health[i] == 4) {
				barriers[i].changePicture("/imgs/Green 2 Barrier.png");
			}else if (barriers2Health[i] == 3) {
				barriers[i].changePicture("/imgs/Yellow Barrier.png");
			}else if (barriers2Health[i] == 2) {
				barriers[i].changePicture("/imgs/Yellow 2 Barrier.png");
			}else if (barriers2Health[i] == 1) {
				barriers[i].changePicture("/imgs/Red Barrier.png");
			}
		}
		
		if(shot) {
		lazer.paint(g);
		}else if(!shot) {
			lazer.setX(10000);
		}
		
		
		if(lazer.getY() <= -50) {
			shot = false;
		}
		if(!started) {
		for(int i = 0; i < trackEnemy.length; i++) {
			for(int j = 0; j < trackEnemy[0].length; j++) {
				trackEnemy[i][j] = false;
				canEnemyShoot[i][j] = false;
			}
		}
		for(int i = 0; i < trackEnemy2.length; i++) {
			for(int j = 0; j < trackEnemy2[0].length; j++) {
				trackEnemy2[i][j] = false;
				canEnemy2Shoot[i][j] = false;
			}
		}
		for(int i = 0; i < trackEnemy3.length; i++) {
			trackEnemy3[i] = false;
			canEnemy3Shoot[i] = false;
		}
		for(int i = 0; i < canEnemyShoot.length; i++) {
			canEnemyShoot[i][1] = true;
		}
		for(int i = 0; i < enemyShot.length; i++) {
			enemyShot[i] = false;
		}
		for(int i = 0; i < barriersHealth.length; i++) {
			barriersHealth[i] = 5;
		}
		for(int i = 0; i < barriers2Health.length; i++) {
			barriers2Health[i] = 5;
		}
		started = true;
		}
		
		//hit detection
		for(int i = 0; i < enemy2.length; i++) {
			for(int j = 0; j < enemy2[0].length; j++) {
				if(lazer.getX() >= enemy2[i][j].getX()+5 && lazer.getX() <= enemy2[i][j].getX()+60 && trackEnemy2[i][j] == false) {
					if(lazer.getY() <= enemy2[i][j].getY()+55 && lazer.getY() >= enemy2[i][j].getY() && trackEnemy2[i][j] == false) {
						shot = false;
						trackEnemy2[i][j] = true;
						score+=10;
					}
				}
			}
		}
		for(int i = 0; i < enemy.length; i++) {
			for(int j = 0; j < enemy[0].length; j++) {
				if(lazer.getX() >= enemy[i][j].getX()+5 && lazer.getX() <= enemy[i][j].getX()+60 && trackEnemy[i][j] == false) {
					if(lazer.getY() <= enemy[i][j].getY()+55 && lazer.getY() >= enemy[i][j].getY() && trackEnemy[i][j] == false) {
						shot = false;
						trackEnemy[i][j] = true;
						score+=20;
					}
				}
			}
		}
		for(int i = 0; i < enemy3.length; i++) {
			if(lazer.getX() >= enemy3[i].getX()+5 && lazer.getX() <= enemy3[i].getX()+60 && trackEnemy3[i] == false) {
				if(lazer.getY() <= enemy3[i].getY()+55 && lazer.getY() >= enemy3[i].getY() && trackEnemy3[i] == false) {
					shot = false;
					trackEnemy3[i] = true;
					score+=30;
				}
			}
		}
		if(lazer.getX() >= ufo.getX() && lazer.getX() <= ufo.getX()+75) {
			if(lazer.getY() >= ufo.getY() && lazer.getY() <= ufo.getY() + 45) {
				shot = false;
				score+=50;
				ufo.setX(2400);
			}
		}
		//enemy fire
		for(int i = 0; i < enemy.length; i++) {
			if(trackEnemy[i][1]) {
				canEnemyShoot[i][0] = true;
			if(trackEnemy[i][0]) {
				canEnemy2Shoot[i][1] = true;
			}
			if(trackEnemy2[i][1]) {
				canEnemy2Shoot[i][0] = true;
			}
			if(trackEnemy2[i][0]) {
				canEnemy3Shoot[i] = true;
			}
		}
	}
		for(int i = 0; i < enemy.length; i++) {
			for(int j = 0; j < enemy[0].length; j++) {
				if(canEnemyShoot[i][j] && !enemyShot[i] && !trackEnemy[i][j]) {
					int random = (int)(Math.random()*351);
					if(random == 50) {
						enemyLazers[i].setX(enemy[i][j].getX()+30);
						enemyLazers[i].setY(enemy[i][j].getY()+30);
						enemyShot[i] = true;
					}
					
				}
			}
		}
		for(int i = 0; i < enemy2.length; i++) {
			for(int j = 0; j < enemy2[0].length; j++) {
				if(canEnemy2Shoot[i][j] && !enemyShot[i] && !trackEnemy2[i][j]) {
					int random = (int)(Math.random()*301);
					if(random == 50) {
						enemyLazers[i].setX(enemy2[i][j].getX()+30);
						enemyLazers[i].setY(enemy2[i][j].getY()+30);
						enemyShot[i] = true;
					}
					
				}
			}
		}
		for(int i = 0; i < enemy3.length; i++) {
			if(canEnemy3Shoot[i] && !enemyShot[i] && !trackEnemy3[i]) {
				int random = (int)(Math.random()*251);
				if(random == 50) {
					enemyLazers[i].setX(enemy3[i].getX()+30);
					enemyLazers[i].setY(enemy3[i].getY()+30);
					enemyShot[i] = true;
				}
			}
		}
		for(int i = 0; i < enemyLazers.length; i++) {
			if(enemyLazers[i].getX() >= p.getX() && enemyLazers[i].getX() <= p.getX()+82) {
				if(enemyLazers[i].getY() >= p.getY() && enemyLazers[i].getY() <= p.getY()+78) {
					lives--;
					enemyLazers[i].setX(10000);
					enemyShot[i] = false;
				}
			}
			if(enemyLazers[i].getY() >= 1500) {
				enemyShot[i] = false;
			}
		}
		
		
		for(int i = 0; i < enemy.length; i++) {
			for(int j = 0; j < enemy[0].length; j++) {
				if(trackEnemy[i][j] == false) {
				enemy[i][j].paint(g);
				}
			}
		}
		for(int i = 0; i < enemy2.length; i++) {
			for(int j = 0; j < enemy2[0].length; j++) {
				if(trackEnemy2[i][j] == false) {
				enemy2[i][j].paint(g);
				}
			}
		}
		for(int i = 0; i < enemy3.length; i++) {
			if(trackEnemy3[i] == false) {
			enemy3[i].paint(g);
			}
		}
		
		//sync movement
		for(int n = 0; n < enemy.length; n++) {
			for(int m = 0; m < enemy[0].length; m++) {
				if(enemy[n][m].getX() >= 840 || enemy2[n][m].getX() >= 840 || enemy3[n].getX() >= 840) {
					hitWall = true;
				for(int i = 0; i < enemy.length; i++) {
					for(int j = 0; j < enemy[0].length; j++) {
						enemy[i][j].setY(enemy[i][j].getY()+20);
						enemy2[i][j].setY(enemy2[i][j].getY()+20);
						enemy3[i].setY(enemy3[i].getY()+10);
					}
				}
				} else if (enemy[n][m].getX() <= 0 || enemy2[n][m].getX() <= 0 || enemy3[n].getX() <= 0) {
			hitWall = false;
			for(int i = 0; i < enemy.length; i++) {
				for(int j = 0; j < enemy[0].length; j++) {
					enemy[i][j].setY(enemy[i][j].getY()+20);
					enemy2[i][j].setY(enemy2[i][j].getY()+20);
					enemy3[i].setY(enemy3[i].getY()+10);
				}
			}
		}else if(enemy[n][m].getY() >= 640 || enemy2[n][m].getY() >= 640 || enemy3[n].getY() >= 640) {
			lives = 0;
			alive = false;
		}
		
		if(hitWall) {
			for(int i = 0; i < enemy.length; i++) {
				for(int j = 0; j < enemy[0].length; j++) {
					enemy[i][j].setSpeedX(-1);
					enemy2[i][j].setSpeedX(-1);
					enemy3[i].setSpeedX(-1);
				}
			}
		}else if (!hitWall) {
			for(int i = 0; i < enemy.length; i++) {
				for(int j = 0; j < enemy[0].length; j++) {
					enemy[i][j].setSpeedX(1);
					enemy2[i][j].setSpeedX(1);
					enemy3[i].setSpeedX(1);
				}
		}
		}
		//ufo movement
		if(ufo.getX() <= -2800) {
			ufo.setX(1400);
		}
			}
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
				enemy[i][j] = new Enemy((i*60),220+(j*60));
			}
		}
		for(int i = 0; i < enemy2.length; i++) {
			for(int j = 0; j < enemy2[0].length; j++) {
				enemy2[i][j] = new Enemy2((i*60),100+(j*60));
			}
		}
		for(int i = 0; i < enemy3.length; i++) {
			enemy3[i] = new Enemy3((i*60)+1,40);
		}
		for(int i = 0; i < enemy3.length; i++) {
			enemyLazers[i] = new EnemyLazer(10000, 0);
		}
		
		int count = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 5; j++) {
					barriers[count] = new Barrier((j*25)+(i*200)+100,600);
					count++;
			}
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
		if(arg0.getKeyCode() == 32 && !shot && alive) {
			lazer.setX(p.getX()+33);
			lazer.setY(p.getY()-33);
			shot = true;
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
