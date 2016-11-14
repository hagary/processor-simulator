
public enum WriteHitPolicy {
	WRITEBACK(0), WRITETHROUGH(1);
	private final int value;

    private WriteHitPolicy(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
