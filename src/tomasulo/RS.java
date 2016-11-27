package tomasulo;

public class RS {
	private Op op;
	private boolean busy;
	private int Vj;
	private int Vx;
	private ROBEntry Qj;
	private ROBEntry Qk;
	private int address;
	
	private ROBEntry dest;
	public RS(Op op) 
	{
		this.op = op;
	}
	public Op getOp() {
		return op;
	}
	public void setOp(Op op) {
		this.op = op;
	}
	public boolean isBusy() {
		return busy;
	}
	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	public int getVj() {
		return Vj;
	}
	public void setVj(int vj) {
		Vj = vj;
	}
	public int getVx() {
		return Vx;
	}
	public void setVx(int vx) {
		Vx = vx;
	}
	public ROBEntry getQj() {
		return Qj;
	}
	public void setQj(ROBEntry qj) {
		Qj = qj;
	}
	public ROBEntry getQk() {
		return Qk;
	}
	public void setQk(ROBEntry qk) {
		Qk = qk;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public ROBEntry getDest() {
		return dest;
	}
	public void setDest(ROBEntry dest) {
		this.dest = dest;
	}
	
}
