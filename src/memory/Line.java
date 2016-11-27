package memory;
import java.io.Serializable;
import org.apache.commons.lang3.SerializationUtils;

public class Line implements Serializable{
	private Word[] lineData; //size of l
	private static short lineSize; //size in words

	public Line(){
		lineData = new Word[lineSize]; //l is in words
	}
	public Word getWord(int wordAddress){
		int wordIndex  = wordAddress % lineData.length;
		return lineData[wordIndex];
	}
	
	public void modifyLine(int wordAddress, Word word){
		//wordAdress is the ABSOLUTE address
		int wordIndex = wordAddress % lineData.length;
		lineData[wordIndex] = SerializationUtils.clone(word);
	}
	
	public int getLineSize(){
		return this.lineSize;
	}
	public static void setLineSize(short lineSize) {
		Line.lineSize = lineSize;
	}
}
