package tomasulo;

import java.util.ArrayList;
import java.util.Queue;

import javax.swing.plaf.metal.MetalBorders.Flush3DBorder;

import registers.RegisterFile;
import simulator.Simulator;
import instructions.Instruction;
import instructions.state;
import instructions.types.*;
public class Writer {

	public static boolean canWrite(Instruction i)
	{
		if (i.getState()==state.EXECUTED)
			return true;
		return false;
	}
	public static void write(Instruction i){
		InstructionParameters params = new InstructionParameters();
		RegisterFile rF = Simulator.getRegisterFile();
		if (i instanceof Add)
		{		
			params.setRegB(rF.readReg(i.getRegB()));
			params.setRegC(rF.readReg(i.getRegC()));
		}
		else if (i instanceof Addi)
		{
			params.setRegB(rF.readReg(i.getRegB()));
			params.setImm((short) i.getImm());
		}
		else if (i instanceof Beq)
		{
			params.setRegA(rF.readReg(i.getRegA()));
			params.setRegB(rF.readReg(i.getRegB()));
			params.setImm((short) i.getImm());
		}
		else if (i instanceof Jalr)
		{
			params.setRegA((short) i.getRegA());
			params.setRegB(rF.readReg(i.getRegB()));
		}
		else if (i instanceof Jmp)
		{
			params.setRegA(rF.readReg(i.getRegA()));
			params.setImm((short) i.getImm());
		}
		else if (i instanceof Load)
		{
			params.setRegA((short) i.getRegA());
			params.setRegB(rF.readReg(i.getRegB()));
			params.setImm((short) i.getImm());
		}
		else if (i instanceof Mul)
		{
			params.setRegB(rF.readReg(i.getRegB()));
			params.setRegC(rF.readReg(i.getRegC()));
		}
		else if (i instanceof Nand)
		{
			params.setRegB(rF.readReg(i.getRegB()));
			params.setRegC(rF.readReg(i.getRegC()));
		}
		else if (i instanceof Ret)
		{
			params.setRegA(rF.readReg(i.getRegA()));
		}
		else if (i instanceof Store)
		{
			params.setRegA(rF.readReg(i.getRegA()));
			params.setRegB(rF.readReg(i.getRegB()));
			params.setImm((short) i.getImm());
		}
		short res = i.execute(params);

		/* --------------MISPREDICTION CHECK-----------*/
		if(i.getOP() == Op.BEQ){
			boolean equal = i.getEquality();;
			if(i.getImm() < 0 ){ //I predicted taken
				if(!equal) //misprediction
				{
					flush(i);
					Simulator.getPC().setData((short)(i.getPc()+1));
					return;
				}
			}
			else {
				//I predicted not taken
				if(equal) {
					flush(i);
					Simulator.getPC().setData((short)(res));
					return;
				}
			}		
		}
		/*-------------END--------------*/
		ROB rob = Simulator.getROB();
		ArrayList<ROBEntry> robQ = rob.getROBTable();
		ROBEntry myEntry = null;
		for (int j=0;j<robQ.size();j++)
		{
			if (robQ.get(j).getInstruction()==i)
				myEntry = robQ.get(j);

		}
		myEntry.setReady(true);
		myEntry.setValue(res);
	}

	public static void flush(Instruction ins){ //flush anything after i
		//flush any newer instructions in ROB
		ArrayList<ROBEntry> rob = Simulator.getROB().getROBTable();
		int branchIndex = -1;
		for (int j = 0; j < rob.size(); j++) {
			if(rob.get(j).getInstruction() == ins){
				branchIndex = j;
				break;
			}
		}
		for (int i = branchIndex; i < rob.size(); i++) {
			rob.get(i).flush();
			rob.get(i).getInstruction().getRS().flush();
		}
	}
}
