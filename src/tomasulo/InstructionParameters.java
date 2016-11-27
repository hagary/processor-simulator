package tomasulo;

public class InstructionParameters {
	
	private short regA;
	private short regB;
	private short regC;
	private short imm;
	private short pc;
	

	public short getRegA() {
		return regA;
	}
	public void setRegA(short regA) {
		this.regA = regA;
	}
	public short getRegB() {
		return regB;
	}
	public void setRegB(short regB) {
		this.regB = regB;
	}
	public short getRegC() {
		return regC;
	}
	public void setRegC(short regC) {
		this.regC = regC;
	}
	public short getImm() {
		return imm;
	}
	public void setImm(short imm) {
		this.imm = imm;
	}
	public short getPc() {
		return pc;
	}
	public void setPc(short pc) {
		this.pc = pc;
	}
	
}
