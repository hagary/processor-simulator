
public class CacheEntry {
	private boolean dirty;
	private int tag;
	private Word[] lineData;  //size of l
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
	public Word[] getLineData() {
		return lineData;
	}
	public void setLineData(Word[] lineData) {
		this.lineData = lineData;
	}

}
