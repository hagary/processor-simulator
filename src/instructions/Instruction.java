package instructions;

import tomasulo.InstructionParameters;

public class Instruction {
	
	private int regA;
	private int regB;
	private int regC;
	private int pc;
	private int imm;
	//private state state;
		
	public short execute(InstructionParameters params){
		return 0;
	}
	
	public void commit(short regData, int regA){
		return;
	}

	public int getRegA() {
		return regA;
	}

	public void setRegA(int regA) {
		this.regA = regA;
	}

	public int getRegB() {
		return regB;
	}

	public void setRegB(int regB) {
		this.regB = regB;
	}

	public int getRegC() {
		return regC;
	}

	public void setRegC(int regC) {
		this.regC = regC;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getImm() {
		return imm;
	}

	public void setImm(int imm) {
		this.imm = imm;
	}

//	public state getState() {
//		return state;
//	}
//
//	public void setState(state state) {
//		this.state = state;
//	}
}