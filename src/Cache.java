
public class Cache{
	private WriteHitPolicy writeHitPolicy;  //write hit policy enum
	private WriteMissPolicy writeMissPolicy;  //write miss policy enum
	private int s; //bytes
	private int numSets;  //lines/m
	private int l; //bytes
	private int lines; //#blocks = s/l
	private int m; //associativity
	private int hitCycles;
	private Set[] sets; //size=sets
	
	public Cache(int s,int l,int m,WriteHitPolicy writeHitPolicy,WriteMissPolicy writeMissPolicy){
		this.s=s;
		this.l=l;
		this.m=m;
		this.writeHitPolicy=writeHitPolicy;
		this.writeMissPolicy=writeMissPolicy;
	}
	
	public Word readWord(int wordAddress){
		return null;   
	}
	
	public Word readLine(int lineAddress){
		return null;   
	}
	
	public void writeWord(int wordAddress, boolean[] wordData){
		return ;   
	}
	
	public void writeLine(int wordAddress, boolean[] lineData){
		return ;   
	}
	
	public CacheEntry findInCache(int lineAddress){
		return null;
	}
	
	public void putInCache(int address, boolean[] line){
		return;
	}

	public WriteHitPolicy getWriteHitPolicy() {
		return writeHitPolicy;
	}

	public void setWriteHitPolicy(WriteHitPolicy writeHitPolicy) {
		this.writeHitPolicy = writeHitPolicy;
	}
	
	public WriteMissPolicy getWriteMissPolicy() {
		return writeMissPolicy;
	}

	public void setWriteMissPolicy(WriteMissPolicy writeMissPolicy) {
		this.writeMissPolicy = writeMissPolicy;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getNumSets() {
		return numSets;
	}

	public void setNumSets(int numSets) {
		this.numSets = numSets;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getHitCycles() {
		return hitCycles;
	}

	public void setHitCycles(int hitCycles) {
		this.hitCycles = hitCycles;
	}

	public Set[] getSets() {
		return sets;
	}

	public void setSets(Set[] sets) {
		this.sets = sets;
	}


}
