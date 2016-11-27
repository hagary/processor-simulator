package memory;

public class CacheEntry {
	private boolean dirty;
	private short tag;
	private Line line;  //size of l
	
	public CacheEntry(boolean dirty,short tag,Line lineData){
		this.dirty=dirty;
		this.tag=tag;
		this.line=lineData;
	}
	public boolean isDirty() {
		return dirty;
	}
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	public short getTag() {
		return tag;
	}
	public void setTag(short tag) {
		this.tag = tag;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line lineData) {
		this.line = lineData;
	}

}
