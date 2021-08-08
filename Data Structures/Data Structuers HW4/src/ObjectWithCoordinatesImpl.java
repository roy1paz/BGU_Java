public class ObjectWithCoordinatesImpl implements ObjectWithCoordinates{
    int x;
    int y;
    private Object data;

    ObjectWithCoordinatesImpl(){
    }

    ObjectWithCoordinatesImpl(int x, int y){
        this.x = x;
        this.y = y;
    }

    ObjectWithCoordinatesImpl(Object data, int x, int y){
        this.x = x;
        this.y = y;
        this.data = data;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString(){
        return this.getData().toString() + " X=" +this.getX() + " Y=" + this.getY();
    }
}
