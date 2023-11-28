package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SnakePanel extends JPanel implements Runnable,KeyListener{
	JFrame parent=new JFrame();
	private int row=50;   //网格行数
	private int col=60;   //列数
	private JPanel[][] gridsPanel;   //面板网格
	private Location direction;//方向定位
	private SnakeModel snake;  //贪吃蛇
	private LinkedList snakeBody;  //蛇的身体
	private LinkedList otherBlocks; //其他区域
	private LocationRO snakeHead;   //蛇的头部
	private LocationRO snakeFood;	 //目标食物
	private Color bodyColor=Color.orange;//蛇的身体颜色
	private Color headColor=Color.black; //蛇的头部颜色
	private Color foodColor=Color.red;   //目标食物颜色
	private Color othersColor=Color.lightGray;//其他区域颜色
	private int gameScore=0; //总分
	private long speed;      //速度（难度设置）
	private boolean AddScore;//加分
	private Thread t;          //线程
	private boolean isEnd;   //暂停
	private static boolean notExit;
     //	构造器，初始化操作
	public SnakePanel(SnakeFrame parent){
		this.parent=parent;
		gridsPanel=new JPanel[row][col];
		otherBlocks=new LinkedList();
		snakeBody=new LinkedList();
		snakeHead=new LocationRO(0,0);
		snakeFood=new LocationRO(0,0);
		direction=new Location(0,1);
        // 布局
		setLayout(new GridLayout(row,col,1,1));
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				gridsPanel[i][j]=new JPanel();
				gridsPanel[i][j].setBackground(othersColor);
				add(gridsPanel[i][j]);
			}
		}
		addKeyListener(this);
	}
        //	开始游戏
	public void newGame(long speed){
		this.speed=speed;
				
		if (notExit) {
		
			snake.init();
		}else{
			snake=new SnakeModel(row,col);
			notExit=true;
			t=new Thread(this);
			t.start();
		}
		requestFocus();
		direction.setX(0);
		direction.setY(1);
		gameScore=0;
		updateTextFiled(""+gameScore);
		isEnd=false;
		
	}
      //	暂停游戏
	public void stopGame(){
		
		requestFocus();
		isEnd=true;
	}
    //	继续
	public void returnGame(){
		
		requestFocus();	
		isEnd=false;	
	}
    //	获得总分
	public int getGameScore(){
		return gameScore;
	}
	//更新总分
	private void updateTextFiled(String str){
		((SnakeFrame)parent).scoreField.setText(str);
	}
	//更新各相关单元颜色
	private void updateColors(){
      // 设定蛇身颜色
		snakeBody=snake.getSnake();
		Iterator i =snakeBody.iterator();
		while(i.hasNext()){
			LocationRO t=(LocationRO)(i.next());
			gridsPanel[t.getX()][t.getY()].setBackground(bodyColor);
		}
		//设定蛇头颜色
		snakeHead=snake.getSnakeHead();
		gridsPanel[snakeHead.getX()][snakeHead.getY()].setBackground(headColor);
		//设定背景颜色
		otherBlocks=snake.getOthers();
		i =otherBlocks.iterator();
		while(i.hasNext()){
			LocationRO t=(LocationRO)(i.next());
			gridsPanel[t.getX()][t.getY()].setBackground(othersColor);
		}
		//设定临时块的颜色
		snakeFood=snake.getSnakeFood();
		gridsPanel[snakeFood.getX()][snakeFood.getY()].setBackground(foodColor);		
	}
	
	public boolean isFocusTraversable()
	{  
		return true; 
	}	
	
	//实现Runnable接口
	public void run(){
		 
			while(true){
				
			try{
				Thread.sleep(speed);
			}catch (InterruptedException e){}
			  if(!isEnd){
				isEnd=!snake.move(direction);
				updateColors();
				   if(snake.getAddScore()){
						gameScore+=10;
						updateTextFiled(""+gameScore);
					}
				}
			 }			
	}

	//实现KeyListener接口
	public void keyPressed(KeyEvent event)
	      {  
	         int keyCode = event.getKeyCode();

			 if(notExit){
		         if (keyCode == KeyEvent.VK_LEFT) {           //向左
		         	direction.setX(0);
		         	direction.setY(-1);		
		         }
		         else if (keyCode == KeyEvent.VK_RIGHT) {     //向右
		         	direction.setX(0);
		         	direction.setY(1);		         		
		         }
		         else if (keyCode == KeyEvent.VK_UP) {        //向上
		         	direction.setX(-1);
		         	direction.setY(0);		         		
		          }
		         else if (keyCode == KeyEvent.VK_DOWN) {     //向下
		         	direction.setX(1);
		         	direction.setY(0);		         		
		          }			 	
				 else if (keyCode == KeyEvent.VK_SPACE){     //空格键
				 	isEnd=!isEnd;
				 }			 	
			 }
		 }
		public void keyReleased(KeyEvent event){}
		public void keyTyped(KeyEvent event){}	
}