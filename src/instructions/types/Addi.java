package instructions.types;

import instructions.Instruction;

public class Addi extends Instruction{
	
	public short execute(int regB, int imm){
		//return (short)(RegisterFile.readReg(regB) + (short)(imm));	
		return -1; //to be removed
	}
	
	public void commit(short regData, int regA){
		//RegisterFile.writeReg(regA, regData);
		return;	
	}

}
