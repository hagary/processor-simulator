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
		else if(i instanceof Sub)
		{
			params.setRegB(rF.readReg(i.getRegB()));
			params.setRegC(rF.readReg(i.getRegC()));
		}
		short res = 0;
		if(!(i instanceof Store))
			 res= i.execute(params);
		else {
			res = rF.readReg(i.getRegA());
			findROBEntry(i).setDest(i.execute(params));;
		}
				/* --------------MISPREDICTION CHECK-----------*/
		if(i.getOP() == Op.BEQ){
			boolean equal = i.isEquality();
			ROBEntry branchEntry = findROBEntry(i);
			if(i.getImm() < 0 ){ //I predicted taken
				if(!equal) //misprediction
				{
					flush(i);
					Simulator.getPC().setData((short)(i.getPc()+1));
					return;
				}
				else { 
					//correct prediction
					//remove branch
					branchEntry.flush();
					i.getRS().flush();
				}
			}
			else {
				//I predicted not taken
				if(equal) {
					flush(i);
					Simulator.getPC().setData((short)(res));
					return;
				}
				else{
					//correct prediction
					//remove branch
					branchEntry.flush();
					i.getRS().flush();
				}
			}		
		}
		/*-------------END--------------*/

		ROBEntry myEntry = findROBEntry(i);
		myEntry.setReady(true);
		myEntry.setValue(res);
		i.setState(state.WRITTEN);
		ArrayList<RS> rsTable = Simulator.getRSSet().getRSarray();
		//broadcast my value to others in need
		for (RS rs : rsTable) {
			if(rs.getQj() == myEntry)
			{
				rs.setQj(null);
				rs.setVj(res);
			}
			if(rs.getQk() == myEntry)
			{
				if(i.getID() == 3)
					System.out.println("say hello");
				rs.setQk(null);
				rs.setVk(res);
			}
		}
		i.getRS().flush();
	}
	public static ROBEntry findROBEntry(Instruction ins){
		ROBEntry res = null;
		ArrayList<ROBEntry> rob = Simulator.getROB().getROBTable();
		for (ROBEntry robEntry : rob) {
			if(robEntry.getInstruction() == ins)
				res = robEntry;
		}
		return res;
	}
	public static void flush(Instruction ins){ //flush anything after i
		//flush any newer instructions in ROB
		ArrayList<ROBEntry> rob = Simulator.getROB().getROBTable();
		int branchIndex = rob.indexOf(findROBEntry(ins));
		for (int i = branchIndex; i < rob.size(); i++) {
			rob.get(i).flush();
			rob.get(i).getInstruction().getRS().flush();
		}
	}
}
