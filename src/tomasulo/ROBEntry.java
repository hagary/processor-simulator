package tomasulo;

import instructions.Instruction;

public class ROBEntry {
	private Op op;
	private int dest;
	private Instruction instruction;
	private int value;
	private boolean ready;
	public ROBEntry(Op op, int dest, Instruction instruction, int value, boolean ready)
	{
		this.op = op;
		this.dest = dest;
		this.instruction = instruction;
		this.value = value;
		this.ready = ready;
	}
	public Op getOp() {
		return op;
	}
	public void setOp(Op op) {
		this.op = op;
	}
	public int getDest() {
		return dest;
	}
	public void setDest(int dest) {
		this.dest = dest;
	}
	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
}
