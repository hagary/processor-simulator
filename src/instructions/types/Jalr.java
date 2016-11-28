package instructions.types;

import instructions.Instruction;
import simulator.Simulator; 
import tomasulo.InstructionParameters;

public class Jalr extends Instruction{
	
	public short execute(InstructionParameters params){
		//RegisterFile.writeReg(this.getRegA(), params.getPc()+1);
		if(params == null){
			short regA = Simulator.getRegisterFile().readReg(this.getRegA());
			short PC = Simulator.getPC().getData();
			short address  = (short)(regA + PC + 1);
			return address;	
		}
		return -1;
    }
}
