package tomasulo;
import java.util.ArrayList;

public class RSSet {
	private ArrayList<RS> RSArray;
	public RSSet(int s)
{
		RSArray = new ArrayList<RS>();
	}
public void createRS(Op Op,int number)
	{
		for (int i=0;i<number;i++)
			RSArray.add(new RS(Op));
	}
	public ArrayList<RS> getRSarray() {
		return RSArray;
	}
	
	public void setRSarray(ArrayList<RS> rSarray) {
		RSArray = rSarray;
	}
	
}