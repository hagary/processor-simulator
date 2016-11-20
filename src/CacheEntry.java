
public class CacheEntry {
	private boolean dirty;
	private int tag;
	private Line line;  //size of l
	
	public CacheEntry(boolean dirty,int tag,Line lineData){
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
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line lineData) {
		this.line = lineData;
	}

}
