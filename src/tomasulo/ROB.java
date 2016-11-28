package tomasulo;

import instructions.Instruction;

import java.util.ArrayList;

public class ROB {
	private ArrayList<ROBEntry> ROBTable;
	private int size;

	public ROB(int size)
	{
		ROBTable = new ArrayList<ROBEntry>();
		this.size = size;
	}
	
	public boolean isFull(){
		return ROBTable.size() == size;
	}
	public boolean isEmpty(){
		return ROBTable.size() == 0;
	}
	public void enqueue(Instruction i){
		ROBEntry r = new ROBEntry(i.getOP(), i.getRegA(), i, 0, false);
		if(!this.isFull())
			ROBTable.add(r);
	}
	public ROBEntry dequeue(){
		return ROBTable.remove(0);
	}
	public ROBEntry peek(){
		return ROBTable.get(0);
	}
	public ArrayList<ROBEntry> getROBTable() {
		return ROBTable;
	}

	public void setROBTable(ArrayList<ROBEntry> rOBTable) {
		ROBTable = rOBTable;
	}

}