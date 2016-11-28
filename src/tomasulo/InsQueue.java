package tomasulo;

import java.util.LinkedList;
import java.util.Queue;

import instructions.Instruction;

public class InsQueue {
	private Queue<Instruction> buffer;
	private int size; 
	
	public InsQueue(int size){
		this.size = size;
	}
	public void enqueue(Instruction ins){
		buffer.add(ins);
	}
	public Instruction dequeue(){
		return buffer.remove();
	}
	public Instruction peek(){
		return buffer.peek();
	}
	public boolean isEmpty(){
		return buffer.size() == 0;
	}
	public boolean isFull(){
		return buffer.size() == size;
	}
}