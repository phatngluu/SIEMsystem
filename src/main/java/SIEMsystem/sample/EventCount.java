package SIEMsystem.sample;

public class EventCount {
    private String name;
    private long num;

    public EventCount(String name, long num){
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }
}
