package Mesta;

public class Drevovo extends Mesto {
	public Drevovo(boolean moje, int b, int d, int k, int m){
		super(moje,b,d,k,m);
	}
	
	public void vyroba(){
		this.zvysTovar(1,1);
	}
	
}
