package tomasulo;

import instructions.Instruction;

import java.util.LinkedList;
import java.util.Queue;

public class ROB {
	private Queue<ROBEntry> ROBTable;

	public ROB()
	{
		ROBTable = new LinkedList<ROBEntry>();
	}
	public Queue<ROBEntry> getROBTable() {
		return ROBTable;
	}

	public void setROBTable(Queue<ROBEntry> rOBTable) {
		ROBTable = rOBTable;
	}
	
	public boolean isFull(){
		return false;
	}
	
	public void insertROBEntery(Instruction i){
		ROBEntry r= new ROBEntry(i.getOP(), i.getRegA(), i, 0, false);
	if(!this.isFull())
		ROBTable.add(r);
	}
	
}