package hra;

import Mesta.*;

public class Vyprava {
	private int prichod, typ, mnozstvo,start, ciel, cena;
	
	public Vyprava(int prichod, int typ, int mnozstvo,int start, int ciel){
		this.prichod=prichod;
		this.typ=typ;
		this.mnozstvo=mnozstvo;
		this.start=start;
		this.ciel=ciel;
	}
	public void setPrichod(int prichod){
		this.prichod=prichod;
	}
	public int getPrichod(){
		return prichod;
	}
	public int getCiel(){
		return ciel;
	}
	public int getStart(){
		return start;
	}
	public void setMnozstvo(int mnozstvo){
		this.mnozstvo=mnozstvo;
	}
	public int getMnozstvo(){
		return mnozstvo;
	}
	public void znizPrichod(){
		this.prichod-=1;
	}
	public void zvysCena(int cena){
		this.cena=cena;
	}
	public int getCena(){
		return cena;
	}
	public void setCena(int cena){
		this.cena=cena;
	}
	public boolean overPrichod(Mesto[] Mesta){
		if(this.getPrichod()==0 && this.getMnozstvo()!=0 && this.getCena()!=(-1)){  //predaj tovaru
			this.zvysCena(Mesta[ciel].getCena(typ)*this.getMnozstvo()); 
			System.out.println("Karavan dorazil\nDoruceny tovar:"+ this.getMnozstvo());
			this.setMnozstvo(0);
			this.setPrichod(Mesta[ciel].getVzdialenost(start));
			return false;
		}
		else if(this.getPrichod()==0 && this.getMnozstvo()!=0){  //presun tovaru
			Mesta[ciel].zvysTovar(typ, mnozstvo);
			this.setCena(0);
			this.setMnozstvo(0);
			return false;
		}
		else if(this.getPrichod()==0 && this.getMnozstvo()==0){  //navrat vypravy
			Mesta[start].zvysPeniaze(cena);
			System.out.println("Karavan sa vratil\nZisk:"+ this.getCena());
			return true;
		} else
			return false;
	}
	
	public void prichody(Vyprava[] vypravy, Mesto[] Mesta){
		int i;
		for(i=0;vypravy[i]!=null;i++){			//hlada karavan, co dorazil do ciela
			if(vypravy[i].overPrichod(Mesta)){
				vypravy[i]=null;
			int j=i+1;
			while(vypravy[j]!=null){
				vypravy[j-1]=vypravy[j];
				j++;
			}
			vypravy[j-1]=null;
			i--;
			}
		}
	}
}
