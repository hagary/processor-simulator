import java.util.LinkedList;
public class MemoryHierarchy {
	private LinkedList<Cache> cacheLevels ;
	
	public Word readWord(int wordAddress){
		return null;   //should call cacheLevels.getFirst().readWord()
	}
	
	public Word readLine(int lineAddress){
		return null;   //should call cacheLevels.getFirst().readLine()
	}
	
	public void writeWord(int wordAddress, boolean[] wordData){
		return ;   //should call cacheLevels.getFirst().writeWord()
	}
	
	public void writeLine(int wordAddress, boolean[] lineData){
		return ;   //should call cacheLevels.getFirst().writeLine()
	}

	public LinkedList<Cache> getCacheLevels() {
		return cacheLevels;
	}

	public void setCacheLevels(LinkedList<Cache> cacheLevels) {
		this.cacheLevels = cacheLevels;
	}
	

}
