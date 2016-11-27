package instructions.types;

import instructions.Instruction;
import tomasulo.InstructionParameters;

public class Beq extends Instruction{

	public short execute(InstructionParameters params){
		if(params.getRegA() == params.getRegB()){
			return (short)(params.getPc() + 1 + params.getImm());	
		}
		return (short)(params.getPc() + 1); //to be removed
	}
		
}
