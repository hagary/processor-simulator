package tomasulo;

import instructions.Instruction;
import instructions.state;
public class Issuer {
	private int PipelineWidth;
	
	public boolean canIssue(Instruction i){
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
	
	public void issue(Instruction i){
		
		if(canIssue(i)){
			Master.getROB().insertROBEntery(i);
		//	Master.getRSSet().insertIntoRSSet(i); hntcheck 3la l op w n set l 7agat fl RS bona2an 3la l ROb enteries lw fi 7aga bt write 3la wa7d mn my operands w hya still msh ready
			i.setState(state.ISSUED);
		}
		else{
			Master.getInstructionBuffer().insert(i);
		}
		
	}

	public int getPipelineWidth() {
		return PipelineWidth;
	}

	public void setPipelineWidth(int pipelineWidth) {
		PipelineWidth = pipelineWidth;
	}
}
