package instructions.types;

import instructions.Instruction;
import registers.RegisterFile;

public class Add extends Instruction{
	
	public short execute(int regB, int regC){
		//return (short)(RegisterFile.readReg(regB) + RegisterFile.readReg(regC));	
		return -1; //to be removed
	}
	
	public void commit(short regData, int regA){
		//RegisterFile.writeReg(regA, regData);
		return;	
	}
	
}
