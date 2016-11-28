package instructions.types;

import instructions.Instruction;
import registers.RegisterFile;
import tomasulo.InstructionParameters;
import tomasulo.Op;

public class Addi extends Instruction{
	public Addi (){
		this.setOP(Op.ADDI);
	}
	public short execute(InstructionParameters params){
		return (short)(params.getRegB() + params.getImm());	
	}
	
}
