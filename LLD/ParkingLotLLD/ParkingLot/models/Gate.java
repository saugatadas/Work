import enums.GateType;

public class Gate {
    private int id;
    private GateType type;

    public Gate(int id, GateType type) {
        this.id = id;
        this.type = type;
    }

    public GateType getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
