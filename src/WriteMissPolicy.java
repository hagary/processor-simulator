
public enum WriteMissPolicy {
	WRITEAROUND(0), WRITEALLOCATE(1);
	private final int value;

    private WriteMissPolicy(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
