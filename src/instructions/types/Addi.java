package instructions.types;

import instructions.Instruction;
import registers.RegisterFile;
import tomasulo.InstructionParameters;

public class Addi extends Instruction{
	
	public short execute(InstructionParameters params){
		return (short)(params.getRegB() + params.getImm());	
	}
	
}
