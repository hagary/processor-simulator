package instructions.types;

import instructions.Instruction;
import registers.RegisterFile;
import tomasulo.InstructionParameters;
import tomasulo.Op;

public class Add extends Instruction{
	public Add (){
		this.setOP(Op.ADD);
	}
	public short execute(InstructionParameters params){
		return (short)(params.getRegB() + params.getRegC());	
	}
	
}
