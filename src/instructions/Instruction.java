package instructions;

import tomasulo.InstructionParameters;
import tomasulo.RS;
import tomasulo.Op;

public class Instruction {
	
	private int regA;
	private int regB;
	private int regC;
	private int pc;
	private int imm;
	private state state;
	private int exCycles;
	private RS RS;
	private Op OP;
	private static int reqCycles;
	private boolean equality;
	private int ID;
	static int count = 1;
	private int spannedCycles;

	public Instruction(){
		exCycles = reqCycles;
		this.setID(count);
		count++;
	}

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

	public state getState() {
		return state;
	}

	public void setState(state state) {
		this.state = state;
	}

	public int getExCycles() {
		return exCycles;
	}

	public void setExCycles(int exCycles) {
		this.exCycles = exCycles;
	}

	public RS getRS() {
		return RS;
	}

	public void setRS(RS rS) {
		RS = rS;
	}

	public Op getOP() {
		return OP;
	}

	public void setOP(Op oP) {
		OP = oP;
	}

	
	public String print(){
		return "Address : " +this.toString() + "\n" +  "regA : " + this.getRegA() + "\n" + "regB : " + this.getRegB() + "\n" + 
				"regC : " + this.getRegC() + "\n" 
				+ "imm : " + this.getImm() + "\n" + "state : " + this.getState() + "\n" 
				+ "exCycles : " + this.getExCycles() + "\n" 
				//+ "RS : " + this.getRS().toString() + "\n" 
				+"OP : "+ this.getOP().name();
	}

	public static int getReqCycles() {
		return reqCycles;
	}
	public static void setReqCycles(int reqCycles) {
		Instruction.reqCycles = reqCycles;
	}

	public boolean isEquality() {
		return equality;
	}

	public void setEquality(boolean equality) {
		this.equality = equality;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
