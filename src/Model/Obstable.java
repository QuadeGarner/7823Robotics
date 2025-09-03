package Model;

public class Obstable {
    private float width;
    private float length;
    //center of the obstabl
    private double x;
    private double y;

    public Obstable (){
    }
    public Obstable (float length, float width, double x, double y){
        this.width = width;
        this.length = length;
        getArea();
        this.x = x;
        this.y = y;
    }
    public float getArea(){
        return width * length;
    }
    public float getLength(){
        return length;
    }
    public float getWidth(){
        return width;
    }
    public void setLength(float length){
        this.length = length;
    }
    public void setWidth(float width){
        this.width = width;
    }
    public double getObstableX(){
        return x;
    }
    public void setObstableX(double x){
        this.x = x;
    }
    public double getObstableY(){
        return y;
    }
    public void setObstableY(double y ){
        this.y = y;
    }

}
