package Model;

public class Field {

    // Dimensions of the field
    private int width;
    private int height;
    private Robot robot;

    public Field(int width, int height, Robot robot ) {
        // Constructor for Field class
        this.width = width;
        this.height = height;
        this.robot = robot;
    }
    public Field(Robot robot) {
        // Constructor for Field class with default dimensions
        this(144, 144, robot);
    }
    public int getWidthInInches() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeightInInches() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public Robot getRobot() {
        return robot;
    }
    public void setRobot(Robot robot) {
        this.robot = robot;
    }
    public void update(int timeStep){
        if (robot != null) {
            robot.updatePosition(timeStep);
        }
    }
    public int convertXToPixels(double x, int fieldWidth) {
        return (int) ((x /width) * fieldWidth);
    }
    public int convertYToPixels(double y, int fieldHeight) {
        return (int) ((y / height) * fieldHeight);
    }
}
