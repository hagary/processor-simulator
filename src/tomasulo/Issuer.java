package tomasulo;

import java.util.ArrayList;

import instructions.Instruction;
import instructions.state;
import simulator.Simulator;
public class Issuer {
	private static int pipelineWidth;

	public static boolean canIssue(Instruction i){
		if(i.getState()==state.FETCHED){
			if(!(Simulator.getROB().isFull())){
				for(int j=0;j<Simulator.getRSSet().getRSarray().size();j++){
					if(Simulator.getRSSet().getRSarray().get(j).getOp()==i.getOP())
						if(!Simulator.getRSSet().getRSarray().get(j).isBusy()){
							i.setRS(Simulator.getRSSet().getRSarray().get(j));
							Simulator.getRSSet().getRSarray().get(j).setBusy(true);
							return true;
						}

				}
			}
		}

		return false;
	}

	public static void issue(Instruction ins){		
		Simulator.getROB().enqueue(ins);
		ArrayList<RS> rsTable = Simulator.getRSSet().getRSarray();
		for (RS rs : rsTable) {
			switch(ins.getOP()){
			//All ins that need regB and regC as operands
			case ADD: 
			case SUB:
			case MUL:
			case NAND:
				if(ins.getRS() == rs){
					//check if operands are ready
					for(ROBEntry re : Simulator.getROB().getROBTable() )
					{
						//checking on regB
						if(re.getInstruction() != ins && re.getDest() == ins.getRegB()){ 
							if(!re.isReady()){
								//regB not ready --> set Qj
								rs.setQj(re);
							}
							else{
								rs.setVj(re.getValue());
							}
						}
						else{//regB ready --> set Vj
							rs.setVj(ins.getRegB());
						}
						//checking on regC
						if(re.getInstruction() != ins && re.getDest() == ins.getRegC()){ //regC not ready --> set Qk
							if(!re.isReady()){
								//regB not ready --> set Qk
								rs.setQk(re);
							}
							else{
								rs.setVk(re.getValue());
							}
						}
						else{//regC --> set Vk
							rs.setVk(ins.getRegC());
						}
					}
				}
				break;
			case LOAD:
			case ADDI:
				if(ins.getRS() == rs){
					//check if operands are ready
					for(ROBEntry re : Simulator.getROB().getROBTable() )
					{
						//checking on regB
						if(re.getInstruction() != ins && re.getDest() == ins.getRegB()){ 
							if(!re.isReady()){
								//regB not ready --> set Qj
								rs.setQj(re);
							}
							else{
								rs.setVj(re.getValue());
							}
						}
						else{//regB ready --> set Vj
							rs.setVj(ins.getRegB());
						}
						//setting imm
						rs.setVk(ins.getImm());
					}
				}
				break;

			case BEQ:
			case STORE:
				if(ins.getRS() == rs){
					//check if operands are ready
					for(ROBEntry re : Simulator.getROB().getROBTable() )
					{
						//checking on regB
						if(	re.getInstruction() != ins && re.getDest() == ins.getRegB()){ 
							if(!re.isReady()){
								//regB not ready --> set Qj
								rs.setQj(re);
							}
							else{
								rs.setVj(re.getValue());
							}
						}
						else{//regB ready --> set Vj
							rs.setVj(ins.getRegB());
						}
						
						//check on regA
						if(re.getInstruction() != ins && re.getDest() == ins.getRegA()){ 
							if(!re.isReady()){
								rs.setQk(re);
							}
							else{
								rs.setVk(re.getValue());
							}
						}
						else{
							rs.setVk(ins.getRegA());
						}
						
						//setting imm in address
						rs.setAddress(ins.getImm());
					}
				}
				break;

			}
			ins.setState(state.ISSUED);
		}
	}

	public static int getPipelineWidth() {
		return pipelineWidth;
	}

	public static void setPipelineWidth(int p) {
		pipelineWidth = p;
	}
}
