package instructions.types;

import instructions.Instruction;
import tomasulo.InstructionParameters;
import tomasulo.Op;

public class Nand extends Instruction{
	public Nand (){
		this.setOP(Op.NAND);
	}
	public short execute(InstructionParameters IP){
		short regB = IP.getRegB();
		short regC = IP.getRegC();
		return (short)~(regB & regC);
	}
}
