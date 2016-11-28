package instructions.types;

import instructions.Instruction;
import simulator.Simulator; 
import tomasulo.InstructionParameters;
import tomasulo.Op;

public class Jalr extends Instruction{
	public Jalr (){
		this.setOP(Op.JALR);
	}
	public short execute(InstructionParameters params){
		if(params == null){
			short regA = Simulator.getRegisterFile().readReg(this.getRegA());
			short PC = Simulator.getPC().getData();
			short address  = (short)(regA + PC + 1);
			return address;	
		}
		return -1;
    }
}
