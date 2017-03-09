package Budovy;

import Mesta.*;

public class Tovaren implements Budova {
   int uroven;
   
   public Tovaren(int uroven){
	   setUroven(uroven);
   }   
   public void zvysUroven(int uroven){
	   this.uroven+=uroven;
   }
	public int getUroven(){
		return uroven;
	}
	public void setUroven(int uroven){
		this.uroven=uroven;
	}
	
	
	public void vyroba(Bavlnovo mesto){
		if(mesto.getMoje())
		mesto.zvysTovar(0, uroven);
	}
	public void vyroba(Drevovo mesto){
		if(mesto.getMoje())
		mesto.zvysTovar(1, uroven);
	}
	public void vyroba(Kamenovo mesto){
		if(mesto.getMoje())
		mesto.zvysTovar(2, uroven);
	}
	public void vyroba(Mramorovo mesto){
		if(mesto.getMoje())
		mesto.zvysTovar(3, uroven);
	}
}
