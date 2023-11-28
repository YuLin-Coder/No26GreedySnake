package snake;

public class LocationRO{ 
	private int x;
	private int y;
	LocationRO(int x,int y){
		this.x=x;
		this.y=y;
	}
	int getX(){
		return x;
	} 
	int getY(){
		return y;
	}
	public boolean equalOrRev(LocationRO e){
		return ((e.getX()==getX())&&(e.getY()==getY()))
		||((e.getX()==getX())&&(e.getY()==-1*getY()))	
		||((e.getX()==-1*getX())&&(e.getY()==getY()));

	}
	public boolean equals(LocationRO e){
		return(e.getX()==getX())&&(e.getY()==getY());
	}
	
	public boolean reverse(LocationRO e){
		return ((e.getX()==getX())&&(e.getY()==-1*getY()))	
		||((e.getX()==-1*getX())&&(e.getY()==getY()));
	}	
}