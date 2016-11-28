package instructions.types;

import instructions.Instruction;
import tomasulo.InstructionParameters;
import tomasulo.Op;

public class Mul extends Instruction {
	public Mul (){
		this.setOP(Op.MUL);
	}
	public short execute(InstructionParameters IP){
		short regB = IP.getRegB();
		short regC = IP.getRegC();
		return (short)(regB*regC);
	}

}
