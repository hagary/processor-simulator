package instructions.types;

import instructions.Instruction;
import registers.RegisterFile;
import tomasulo.InstructionParameters;
import tomasulo.Op;

public class Sub extends Instruction{
	public Sub (){
		this.setOP(Op.SUB);
	}
	public short execute(InstructionParameters params){
		return (short)(params.getRegB() - params.getRegC());	
	}
	
}
