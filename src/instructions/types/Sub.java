package instructions.types;

import instructions.Instruction;
import registers.RegisterFile;
import tomasulo.InstructionParameters;

public class Sub extends Instruction{
		
	public short execute(InstructionParameters params){
		//return (short)(params.getRegB() - params.getRegC());	
		return -1; //to be removed
	}
	
}
