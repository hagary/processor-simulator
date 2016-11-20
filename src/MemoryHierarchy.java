import java.awt.Window.Type;
import java.util.*;
public class MemoryHierarchy {
	private LinkedList<Cache> cacheLevels ;

	public MemoryHierarchy(){
		this.cacheLevels= new LinkedList<Cache>();
	}


	public Word readWord(int wordAddress){
		return null;   //should call cacheLevels.getFirst().readWord()
	}

	public void writeWord(int wordAddress, boolean[] wordData){
		return ;   //should call cacheLevels.getFirst().writeWord()
	}

	public LinkedList<Cache> getCacheLevels() {
		return cacheLevels;
	}

	public void setCacheLevels(LinkedList<Cache> cacheLevels) {
		this.cacheLevels = cacheLevels;
	}
	public void addCacheLevel(Cache c){
		Cache lastAdded = cacheLevels.getLast();
		if(lastAdded!=null){
			lastAdded.setNextLevel(c);
			c.setPrevLevel(lastAdded);
		}
		cacheLevels.add(c);
	}


}
