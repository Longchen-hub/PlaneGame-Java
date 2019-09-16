package gamePac;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;


/**
 * 飞机主程序
 * @author 陈庆龙
 *
 */
public class MyGameFrame extends Frame {
	Image bgImg=GameUtil.getImage("images/bg.jpg");
	Image planeImg=GameUtil.getImage("images/plane.png");
	Plane plane=new Plane(planeImg,250,250);
	Shell shell=new Shell();
	Shell[] shells=new Shell[20];
	Explode BOOM;
	Date StartTime=new Date();
	Date EndTime;
	int period=0;
	boolean live=true;
	//变量
	
		public void paint(Graphics g) {
		super.paint(g);
        g.drawImage(bgImg,0, 0, null);
        plane.drawSelf(g);//画飞机
       
        for(int i=0;i<shells.length;i++)
        {  
        	shells[i].draw(g);
        	boolean boom=shells[i].getRect().intersects(plane.getRect());
        	if(boom) {
        		EndTime=new Date();
        		if(live)
        		period=(int)(EndTime.getSeconds()-StartTime.getSeconds());
        		plane.live=false;
        		if(BOOM==null)
        	    BOOM=new Explode(plane.x,plane.y);
        		BOOM.draw(g);
        		live=false;
        	}
        	if(!live) {
        	g.setColor(Color.white);
        	g.drawString("时间"+period+"秒", (int)plane.x, (int)plane.y);
        }
        	}
		}
		
		class  PaintThread  extends  Thread  {
			@Override
			public void run() {
				while(true){
					
					repaint();		//重画
					
					try {
						Thread.sleep(40);   	//1s=1000ms
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
				}
			}
			
		}
	
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			plane.minusDirection(e);
		}
		
	}
		
		
public void launchFrame() {
	this.setTitle("陈庆龙");
	this.setVisible(true);
	this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
	this.setLocation(300,300);
	
	this.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	});
	
	new PaintThread().start();//启动重画窗口的线程
	addKeyListener(new KeyMonitor());//给窗口增加键盘的监听
	
	for(int i=0;i<shells.length;i++) {
		shells[i]=new Shell();
	}
}
	public static void main(String[] args) {
		MyGameFrame f=new MyGameFrame();
		f.launchFrame();
	}
	
	
	//双缓冲
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}
	
}
