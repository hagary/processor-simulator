package instructions.types;

import instructions.Instruction;
import registers.RegisterFile;
import tomasulo.InstructionParameters;

public class Add extends Instruction{
	
	public short execute(InstructionParameters params){
		return (short)(params.getRegB() + params.getRegC());	
	}
	
}
