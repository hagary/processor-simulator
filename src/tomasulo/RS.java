package tomasulo;

public class RS {
	private Op op;
	private boolean busy;
	private int Vj;
	private int Vk;
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
	public int getVk() {
		return Vk;
	}
	public void setVk(int vk) {
		Vk = vk;
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
