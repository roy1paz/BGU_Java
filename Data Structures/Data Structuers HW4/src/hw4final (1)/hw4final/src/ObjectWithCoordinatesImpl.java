public class ObjectWithCoordinatesImpl implements ObjectWithCoordinates {
    int XCoordinate;
    int YCoordinate;
    Object Data;

    public ObjectWithCoordinatesImpl(Object Data,int XCoordinate, int YCoordinate){
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.Data = Data;
    }
    public int getX() {return XCoordinate;}
    public int getY(){return YCoordinate;}
    public Object getData(){return Data;}
    public String toString() {
        return Data + " X="+XCoordinate+" Y="+YCoordinate;
    }

}