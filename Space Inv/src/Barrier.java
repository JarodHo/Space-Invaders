import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Barrier{
	private int x, y;
	private Image img; 	
	private AffineTransform tx;

	public Barrier(int x, int y) {
		img = getImage("/imgs/Green Barrier.png"); //load the image for Tree
		//put the background here^^^^
		this.x = x;
		this.y = y;
		
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		tx.scale(.05, .05);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		update();
	}
	
	private void update()
	{	
		tx.setToTranslation(x, y);
		tx.scale(.05, .05);
	}
	
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.05, .05);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Barrier.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
