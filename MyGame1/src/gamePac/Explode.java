package gamePac;
/**
 * ±¬Õ¨Àà
 * @author ³ÂÇìÁú 
 */
import java.awt.Graphics;
import java.awt.Image;

public class Explode {
	double x, y;
	boolean ExplodeEnd=false;
	
	static Image[] imgs = new Image[16];
	static {
		for (int i = 0; i < 16; i++) {
			imgs[i] = GameUtil.getImage("images/explode/e" + (i + 1) + ".gif");
			imgs[i].getWidth(null);
		}
	}

	int count=0;

	public void draw(Graphics g) {
		if (count <= 15&&!ExplodeEnd) {
			g.drawImage(imgs[count], (int) x, (int) y, null);
			count++;
		}
		if(count>15) {
			ExplodeEnd=true;
		}
	}

	public Explode(double x, double y) {
		this.x = x;
		this.y = y;
	}
}