package memory;
import java.io.Serializable;


public class Word implements Serializable{
	private String data;
	
	public Word(String data){
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
