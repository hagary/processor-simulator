
public class Set {
	private int m;
	private CacheEntry[] entries;
	
	public Set(int m){
		this.m = m;
		this.entries = new CacheEntry[m];
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public CacheEntry[] getEntries() {
		return entries;
	}

	public void setEntries(CacheEntry[] entries) {
		this.entries = entries;
	}

}
