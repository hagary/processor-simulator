package instructions.types;

import instructions.Instruction;
import tomasulo.InstructionParameters;

public class Jalr extends Instruction{
	
	public short execute(InstructionParameters params){
		//RegisterFile.writeReg(this.getRegA(), params.getPc()+1);
	    return params.getRegB();
    }
}
