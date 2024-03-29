package gamePac;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 游戏物体的父类
 * @author 陈庆龙
 *
 */
public class GameObject {
 Image img;
 double x,y;
  public int speed;
 int width,height;

 public void setspeed(int s) {
	 speed=s;
 }
 public void drawSelf(Graphics g) {
	 g.drawImage(img, (int)x, (int)y,null);
	 
 }

public GameObject(Image img, double x, double y, int speed, int width, int height) {
	super();
	this.img = img;
	this.x = x;
	this.y = y;
	this.speed = speed;
	this.width = width;
	this.height = height;
}

public GameObject(Image img, double x, double y) {
	super();
	this.img = img;
	this.x = x;
	this.y = y;
}
 
 public GameObject() {
	 //空构造器
}
 /**
  * 返回物体所在矩形，用于碰撞检测
  * @return
  */
 public Rectangle getRect() {
	 return new Rectangle((int)x,(int)y,width,height);
 }
 

}
