package tomasulo;

import instructions.Instruction;
import instructions.state;
import simulator.Simulator;
public class Issuer {
	private static int pipelineWidth;

	public static boolean canIssue(Instruction i){
		if(i.getState()==state.FETCHED){
			if(!(Master.getROB().isFull())){
				for(int j=0;j<Master.getRSSet().getRSarray().size();j++){
					if(Master.getRSSet().getRSarray().get(j).getOp()==i.getOP())
						if(!Master.getRSSet().getRSarray().get(j).isBusy()){
							i.setRS(Master.getRSSet().getRSarray().get(j));
							Master.getRSSet().getRSarray().get(j).setBusy(true);
							return true;
						}

				}
			}
		}

		return false;
	}

	public static void issue(Instruction ins){		
				if(canIssue(ins)){
						Simulator.getROB().enqueue(ins);
						for(int j = 0; j < Simulator.getRSSet().getRSarray().size(); j++){
							switch(ins.getOP()){
								case ADD: 
									if(ins.getRS().equals(Simulator.getRSSet().getRSarray().get(j))){
										//check if operands are ready
										for(int i = 0; i < Simulator.getROB().getROBTable().size(); i++){
											ROBEntry re = Simulator.getROB().getROBTable().get(i);
											//checking on regB
											if(re.getDest() == ins.getRegB()){ //regB not ready --> set Qj
												Simulator.getRSSet().getRSarray().get(j).setQj(re);
											}
											else{//regB ready --> set Vj
												Simulator.getRSSet().getRSarray().get(j).setVj(ins.getRegB());
											}
											//checking on regC
											if(re.getDest() == ins.getRegC()){ //regC not ready --> set Qk
												Simulator.getRSSet().getRSarray().get(j).setQk(re);
											}
											else{//regC --> set Vk
												Simulator.getRSSet().getRSarray().get(j).setVk(ins.getRegC());
											}
										}
									}
									break;
									
								case ADDI:
									if(ins.getRS().equals(Simulator.getRSSet().getRSarray().get(j))){
										//check if operands are ready
										for(int i = 0; i < Simulator.getROB().getROBTable().size(); i++){
											ROBEntry re = Simulator.getROB().getROBTable().get(i);
											//checking on regB
											if(re.getDest() == ins.getRegB()){ //regB not ready --> set Qj
												Simulator.getRSSet().getRSarray().get(j).setQj(re);
											}
											else{//regB ready --> set Vj
												Simulator.getRSSet().getRSarray().get(j).setVj(ins.getRegB());
											}
											//setting imm
											Simulator.getRSSet().getRSarray().get(j).setVk(ins.getImm());
										}
									}
									break;
									
								case BEQ:
									if(ins.getRS().equals(Simulator.getRSSet().getRSarray().get(j))){
										//check if operands are ready
										for(int i = 0; i < Simulator.getROB().getROBTable().size(); i++){
											ROBEntry re = Simulator.getROB().getROBTable().get(i);
											//checking on regB
											if(re.getDest() == ins.getRegB()){ //regB not ready --> set Qj
												Simulator.getRSSet().getRSarray().get(j).setQj(re);
											}
											else{//regB ready --> set Vj
												Simulator.getRSSet().getRSarray().get(j).setVj(ins.getRegB());
											}
											//checking on regC
											if(re.getDest() == ins.getRegC()){ //regC not ready --> set Qk
												Simulator.getRSSet().getRSarray().get(j).setQk(re);
											}
											else{//regC --> set Vk
												Simulator.getRSSet().getRSarray().get(j).setVk(ins.getRegC());
											}
											//setting imm in address
											Simulator.getRSSet().getRSarray().get(j).setAddress(ins.getImm());
										}
									}
									break;
									
								case LOAD:
									if(ins.getRS().equals(Simulator.getRSSet().getRSarray().get(j))){
										//check if operands are ready
										for(int i = 0; i < Simulator.getROB().getROBTable().size(); i++){
											ROBEntry re = Simulator.getROB().getROBTable().get(i);
											//checking on regB
											if(re.getDest() == ins.getRegB()){ //regB not ready --> set Qj
												Simulator.getRSSet().getRSarray().get(j).setQj(re);
											}
											else{//regB ready --> set Vj
												Simulator.getRSSet().getRSarray().get(j).setVj(ins.getRegB());
											}
											//setting offest in address
											Simulator.getRSSet().getRSarray().get(j).setAddress(ins.getImm());
										}
									}
									break;
									
								case MUL:
									if(ins.getRS().equals(Simulator.getRSSet().getRSarray().get(j))){
										//check if operands are ready
										for(int i = 0; i < Simulator.getROB().getROBTable().size(); i++){
											ROBEntry re = Simulator.getROB().getROBTable().get(i);
											//checking on regB
											if(re.getDest() == ins.getRegB()){ //regB not ready --> set Qj
												Simulator.getRSSet().getRSarray().get(j).setQj(re);
											}
											else{//regB ready --> set Vj
												Simulator.getRSSet().getRSarray().get(j).setVj(ins.getRegB());
											}
											//checking on regC
											if(re.getDest() == ins.getRegC()){ //regC not ready --> set Qk
												Simulator.getRSSet().getRSarray().get(j).setQk(re);
											}
											else{//regC --> set Vk
												Simulator.getRSSet().getRSarray().get(j).setVk(ins.getRegC());
											}
										}
									}
									break;
									
								case NAND:
									if(ins.getRS().equals(Simulator.getRSSet().getRSarray().get(j))){
										//check if operands are ready
										for(int i = 0; i < Simulator.getROB().getROBTable().size(); i++){
											ROBEntry re = Simulator.getROB().getROBTable().get(i);
											//checking on regB
											if(re.getDest() == ins.getRegB()){ //regB not ready --> set Qj
												Simulator.getRSSet().getRSarray().get(j).setQj(re);
											}
											else{//regB ready --> set Vj
												Simulator.getRSSet().getRSarray().get(j).setVj(ins.getRegB());
											}
											//checking on regC
											if(re.getDest() == ins.getRegC()){ //regC not ready --> set Qk
												Simulator.getRSSet().getRSarray().get(j).setQk(re);
											}
											else{//regC --> set Vk
												Simulator.getRSSet().getRSarray().get(j).setVk(ins.getRegC());
											}
										}
									}
									break;
									
								case STORE:
									if(ins.getRS().equals(Simulator.getRSSet().getRSarray().get(j))){
										//check if operands are ready
										for(int i = 0; i < Simulator.getROB().getROBTable().size(); i++){
											ROBEntry re = Simulator.getROB().getROBTable().get(i);
											//checking on regA
											if(re.getDest() == ins.getRegA()){ //regA not ready --> set Qk
												Simulator.getRSSet().getRSarray().get(j).setQk(re);
											}
											else{//regA ready --> set Vk
												Simulator.getRSSet().getRSarray().get(j).setVk(ins.getRegA());
											}
											//setting mem address in regB in Vj
											if(re.getDest() == ins.getRegB()){ //regB not ready --> set Qj
												Simulator.getRSSet().getRSarray().get(j).setQj(re);
											}
											else{//regB ready --> set Vj
												Simulator.getRSSet().getRSarray().get(j).setVj(ins.getRegB());
											}
											//setting offest in address
											Simulator.getRSSet().getRSarray().get(j).setAddress(ins.getImm());
										}
									}
									break;
								case SUB: 
									if(ins.getRS().equals(Simulator.getRSSet().getRSarray().get(j))){
										//check if operands are ready
										for(int i = 0; i < Simulator.getROB().getROBTable().size(); i++){
											ROBEntry re = Simulator.getROB().getROBTable().get(i);
											//checking on regB
											if(re.getDest() == ins.getRegB()){ //regB not ready --> set Qj
												Simulator.getRSSet().getRSarray().get(j).setQj(re);
											}
											else{//regB ready --> set Vj
												Simulator.getRSSet().getRSarray().get(j).setVj(ins.getRegB());
											}
											//checking on regC
											if(re.getDest() == ins.getRegC()){ //regC not ready --> set Qk
												Simulator.getRSSet().getRSarray().get(j).setQk(re);
											}
											else{//regC --> set Vk
												Simulator.getRSSet().getRSarray().get(j).setVk(ins.getRegC());
											}
										}
									}
									break;
							}
						}
						
				//		Simulator.getRSSet().insertIntoRSSet(ins);
						ins.setState(state.ISSUED);
			 		}
			 		else{
						Simulator.getInsQueue().enqueue(ins);
			 		}
	}

	public static int getPipelineWidth() {
		return pipelineWidth;
	}

	public static void setPipelineWidth(int p) {
		pipelineWidth = p;
	}
}
