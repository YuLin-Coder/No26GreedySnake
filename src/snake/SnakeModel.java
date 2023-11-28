package snake;

import javax.swing.*;
import java.util.LinkedList;

public class SnakeModel {
	private int rows,cols;//������
	private Location snakeHead,runingDiriction;//���з���
	private LocationRO[][] locRO;//LocationRO������
	private LinkedList snake,playBlocks;//�߼����������
	private LocationRO snakeFood;//Ŀ��ʳ��
	private int gameScore=0;   //����
	private boolean AddScore=false;//�ӷ�
    //	�����ͷ
	public LocationRO getSnakeHead(){     
		return (LocationRO)(snake.getLast());
	}
	//��β
	public LocationRO getSnakeTail(){
		return (LocationRO)(snake.getFirst());
	}
	//����·��
	public Location getRuningDiriction(){
		return runingDiriction;
	}
	//�����ʵ������
	public LinkedList getSnake(){
		return snake;
	}
	//��������
	public LinkedList getOthers(){
		return playBlocks;
	}	
	//����ܷ�
	public int getScore(){
		return gameScore;
	}
	//������ӷ���
	public boolean getAddScore(){
		return AddScore;
	}
	//������ͷ����
	private void setSnakeHead(Location snakeHead){
		this.snakeHead=snakeHead;
	}
    //���Ŀ��ʳ��
	public LocationRO getSnakeFood(){
		return snakeFood;
	}
	//�������Ŀ��ʳ��
	private void setSnakeFood(){
		snakeFood=(LocationRO)(playBlocks.get((int)(Math.random()*playBlocks.size())));
	}
	//�ƶ�
	private void moveTo(Object a,LinkedList fromlist,LinkedList tolist){
		fromlist.remove(a);
		tolist.add(a);
	}
	//��ʼ����
	public void init(){
		playBlocks.clear();
		snake.clear();
		gameScore=0;
	 	for(int i=0;i<rows;i++){
	 		for(int j=0;j<cols;j++){
	 			
	 			playBlocks.add(locRO[i][j]);
	 		}
	 	}
	 	
	 	//��ʼ���ߵ���״
	 	for(int i=4;i<7;i++){
	 		moveTo(locRO[4][i],playBlocks,snake);
	 	}
	 	//��ͷλ��
	 	snakeHead=new Location(4,6);

	 	//���������
	 	snakeFood=new LocationRO(0,0);
	 	setSnakeFood();
	 	//��ʼ���˶�����
	 	runingDiriction=new Location(0,1);
	 			
	}
	//Snake������
	public SnakeModel(int rows1,int cols1){
	 	rows=rows1;
	 	cols=cols1;
	 	locRO=new LocationRO[rows][cols];
	 	snake=new LinkedList();
	 	playBlocks=new LinkedList();
	 	
	 	for(int i=0;i<rows;i++){
	 		for(int j=0;j<cols;j++){
	 			locRO[i][j]=new LocationRO(i,j);
	 		}
	 	}
	 		 	
	 	init();

	}
	
  /**���岼����move������������гɹ��򷵻�true,���򷵻�false
	*����direction��Location���ͣ�
	*direction ��ֵ:(-1,0)��ʾ���ϣ�(1,0)��ʾ���£�
	*(0,-1)��ʾ����(0,1)��ʾ���ң�
	**/
	public boolean move(Location direction){
		//�ж��趨�ķ�������з����ǲ����෴
		if (direction.reverse(runingDiriction)){
			snakeHead.setX(snakeHead.getX()+runingDiriction.getX());
			snakeHead.setY(snakeHead.getY()+runingDiriction.getY());			
		}else{
			snakeHead.setX(snakeHead.getX()+direction.getX());
			snakeHead.setY(snakeHead.getY()+direction.getY());			
		}

		//����߳Ե���Ŀ��ʳ��
		try{
		if ((snakeHead.getX()==snakeFood.getX())
			&&(snakeHead.getY()==snakeFood.getY()))
			{
			moveTo(locRO[snakeHead.getX()][snakeHead.getY()],playBlocks,snake);
			setSnakeFood();
			
			gameScore+=10;
			AddScore=true;
				
			}else{
				AddScore=false;
				//�Ƿ����
				if((snakeHead.getX()<rows)&&(snakeHead.getY()<cols)&&(snakeHead.getX()>=0&&(snakeHead.getY()>=0))){
					//��������磬�ж��Ƿ��������ཻ
					if(snake.contains(locRO[snakeHead.getX()][snakeHead.getY()])){
						//����ཻ,������Ϸ
						JOptionPane.showMessageDialog(null, "Game Over!", "��Ϸ����", JOptionPane.INFORMATION_MESSAGE);
						return false;
					}else{
						//������ཻ���Ͱ�snakeHead�ӵ�snake���棬���Ұ�β���Ƴ�
						moveTo(locRO[snakeHead.getX()][snakeHead.getY()],playBlocks,snake);
						moveTo(snake.getFirst(),snake,playBlocks);
					}
					
				}else{
					//����,��Ϸ����
					//��
					if(snakeHead.getX()==rows) {
						snakeHead.setX(0);
						}
					else if(snakeHead.getX()<0) {
						snakeHead.setX(rows-1);
						
					}
					else if(snakeHead.getY()<0) {
						snakeHead.setY(cols-1);
						
					}
					
					else if(snakeHead.getX()==0) {
						snakeHead.setX(0);
					}
						else if(snakeHead.getY()==cols) {
							snakeHead.setY(0);
						}
						
						moveTo(locRO[snakeHead.getX()][snakeHead.getY()],playBlocks,snake);
						moveTo(snake.getFirst(),snake,playBlocks);
					    
					}
				}				
		
		
		}catch(ArrayIndexOutOfBoundsException e){
			return false;
		}
			//�������з���
		if (!(direction.reverse(runingDiriction)
		||direction.equals(runingDiriction))){
				runingDiriction.setX(direction.getX());
				runingDiriction.setY(direction.getY());
			}
		
		return true;
	}
	
	
}