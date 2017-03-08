package Mesta;

public class Bavlnovo extends Mesto {
	
	public Bavlnovo(boolean moje, int b, int d, int k, int m){
		super(moje,b,d,k,m);
	}
	
	public void vyroba(){
		this.zvysTovar(0,1); 
	}
}
