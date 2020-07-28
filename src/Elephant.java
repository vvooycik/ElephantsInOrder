public class Elephant {

    // *** Fields ***

    public final int index;
    private int  start, end;
    private int mass;

    // *** Constructors ***

    public Elephant(int index, int mass, int end) {
        this.index = index;
        this.mass = mass;
        this.end = end;
    }

    // *** Public methods ***

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }
}
