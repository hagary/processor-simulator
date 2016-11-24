
public class Memory {
private int s;
private int l;
private Line[] lines;

public Memory(int l){
	this.s=64*1024;
	this.l=l;
}
public int getS() {
	return s;
}
public void setS(int s) {
	this.s = s;
}
public int getL() {
	return l;
}
public void setL(int l) {
	this.l = l;
}

public Line readInMemory(int lineAddress){
	return lines[lineAddress];
	
}
public Line[] getLines() {
	return lines;
}
public void setLines(Line[] lines) {
	this.lines = lines;
}



}
