package snake;

public class Location {
    private int x;
    private int y;
    Location(int x,int y){
        this.x=x;
        this.y=y;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
    void setX(int x){
        this.x=x;
    }
    void setY(int y){
        this.y=y;
    }
    public boolean equalOrRev(Location e){
        return ((e.getX()==getX())&&(e.getY()==getY()))
                ||((e.getX()==getX())&&(e.getY()==-1*getY()))
                ||((e.getX()==-1*getX())&&(e.getY()==getY()));

    }
    public boolean equals(Location e){
        return(e.getX()==getX())&&(e.getY()==getY());
    }

    public boolean reverse(Location e){
        return ((e.getX()==getX())&&(e.getY()==-1*getY()))
                ||((e.getX()==-1*getX())&&(e.getY()==getY()));
    }
}

