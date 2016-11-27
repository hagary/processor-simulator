package instructions.types;

import tomasulo.InstructionParameters;
import instructions.Instruction;

public class Ret extends Instruction {
	
	public short execute(InstructionParameters params){
        return params.getRegA();
    }

}
