package tomasulo;
  
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
 	
  }
