package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SnakePanel extends JPanel implements Runnable,KeyListener{
	JFrame parent=new JFrame();
	private int row=50;   //��������
	private int col=60;   //����
	private JPanel[][] gridsPanel;   //�������
	private Location direction;//����λ
	private SnakeModel snake;  //̰����
	private LinkedList snakeBody;  //�ߵ�����
	private LinkedList otherBlocks; //��������
	private LocationRO snakeHead;   //�ߵ�ͷ��
	private LocationRO snakeFood;	 //Ŀ��ʳ��
	private Color bodyColor=Color.orange;//�ߵ�������ɫ
	private Color headColor=Color.black; //�ߵ�ͷ����ɫ
	private Color foodColor=Color.red;   //Ŀ��ʳ����ɫ
	private Color othersColor=Color.lightGray;//����������ɫ
	private int gameScore=0; //�ܷ�
	private long speed;      //�ٶȣ��Ѷ����ã�
	private boolean AddScore;//�ӷ�
	private Thread t;          //�߳�
	private boolean isEnd;   //��ͣ
	private static boolean notExit;
     //	����������ʼ������
	public SnakePanel(SnakeFrame parent){
		this.parent=parent;
		gridsPanel=new JPanel[row][col];
		otherBlocks=new LinkedList();
		snakeBody=new LinkedList();
		snakeHead=new LocationRO(0,0);
		snakeFood=new LocationRO(0,0);
		direction=new Location(0,1);
        // ����
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
        //	��ʼ��Ϸ
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
      //	��ͣ��Ϸ
	public void stopGame(){
		
		requestFocus();
		isEnd=true;
	}
    //	����
	public void returnGame(){
		
		requestFocus();	
		isEnd=false;	
	}
    //	����ܷ�
	public int getGameScore(){
		return gameScore;
	}
	//�����ܷ�
	private void updateTextFiled(String str){
		((SnakeFrame)parent).scoreField.setText(str);
	}
	//���¸���ص�Ԫ��ɫ
	private void updateColors(){
      // �趨������ɫ
		snakeBody=snake.getSnake();
		Iterator i =snakeBody.iterator();
		while(i.hasNext()){
			LocationRO t=(LocationRO)(i.next());
			gridsPanel[t.getX()][t.getY()].setBackground(bodyColor);
		}
		//�趨��ͷ��ɫ
		snakeHead=snake.getSnakeHead();
		gridsPanel[snakeHead.getX()][snakeHead.getY()].setBackground(headColor);
		//�趨������ɫ
		otherBlocks=snake.getOthers();
		i =otherBlocks.iterator();
		while(i.hasNext()){
			LocationRO t=(LocationRO)(i.next());
			gridsPanel[t.getX()][t.getY()].setBackground(othersColor);
		}
		//�趨��ʱ�����ɫ
		snakeFood=snake.getSnakeFood();
		gridsPanel[snakeFood.getX()][snakeFood.getY()].setBackground(foodColor);		
	}
	
	public boolean isFocusTraversable()
	{  
		return true; 
	}	
	
	//ʵ��Runnable�ӿ�
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

	//ʵ��KeyListener�ӿ�
	public void keyPressed(KeyEvent event)
	      {  
	         int keyCode = event.getKeyCode();

			 if(notExit){
		         if (keyCode == KeyEvent.VK_LEFT) {           //����
		         	direction.setX(0);
		         	direction.setY(-1);		
		         }
		         else if (keyCode == KeyEvent.VK_RIGHT) {     //����
		         	direction.setX(0);
		         	direction.setY(1);		         		
		         }
		         else if (keyCode == KeyEvent.VK_UP) {        //����
		         	direction.setX(-1);
		         	direction.setY(0);		         		
		          }
		         else if (keyCode == KeyEvent.VK_DOWN) {     //����
		         	direction.setX(1);
		         	direction.setY(0);		         		
		          }			 	
				 else if (keyCode == KeyEvent.VK_SPACE){     //�ո��
				 	isEnd=!isEnd;
				 }			 	
			 }
		 }
		public void keyReleased(KeyEvent event){}
		public void keyTyped(KeyEvent event){}	
}