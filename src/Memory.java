import org.apache.commons.lang3.SerializationUtils;


public class Memory {
	private int s;
	private int l;
	private Line[] lines;
	private int cycles;

	public Memory(int l, int cycles){
		this.s=64*1024;
		this.l=l;
		this.setCycles(cycles);
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}

	public Line readInMemory(int lineAddress){
		return SerializationUtils.clone(lines[lineAddress]);

	}
	public Line[] getLines() {
		return lines;
	}
	public void setLines(Line[] lines) {
		this.lines = lines;
	}
	public int getCycles() {
		return cycles;
	}
	public void setCycles(int cycles) {
		this.cycles = cycles;
	}



}
