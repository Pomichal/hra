package mesta;

import budovy.*;

public class Mesto implements ZakladMesta{
	private boolean moje;  //ovladnutie mestskeho trhu
	private int[] mnozstvo={0,0,0,0}; //tipy poloziek:0:bavlna, 1:drevo, 2:kamen, 3:mramor(?)
	private int[] cena={0,0,0,0};  //cena tovarov
	private static int peniaze=50;  //zdroje hraca
	private int[] vzdialenost={0,0,0,0}; //vzdialenost do ostatnych miest (v kolach)
	protected Budova[] budovy = new Budova[3];
	
	
	
	public Mesto(boolean moje, int b, int d, int k, int m){ //konstruktor
		int i;
		setMoje(moje);
		int[] a={b,d,k,m};
		for(i=0;i<4;i++){
			setTovar(i,a[i]);
		}
	}
	public void postavBudovu(int typ){
		switch(typ){
		case 0: this.budovy[0]=new Tovaren(1);
				break;
		case 1: this.budovy[1]= new Stajna(1);
				break;
//		case 2: this.budovy[2]=new Stajna(1);
//				break;
		}
	}
	public Budova getBudova(int i){
		return budovy[i];
	}
	public void setMoje(boolean moje){
		this.moje=moje;
	}
	public void zmenMoje(){
		this.moje=!this.moje;
	}
	public boolean getMoje(){
		return moje;
	}
	public void setTovar(int cislo, int mnozstvo){
		this.mnozstvo[cislo]=mnozstvo;
	}
	public void zvysTovar(int cislo, int mnozstvo){
		this.mnozstvo[cislo]+=mnozstvo;
	}
	public void znizTovar(int cislo, int mnozstvo){
		this.mnozstvo[cislo]-=mnozstvo;
	}
	public int getTovar(int cislo){
		return mnozstvo[cislo];
	}
	public int getCena(int cislo){
		return cena[cislo];
	}
	public void setCeny(int b, int d, int k, int m){
		int i;
		int[] a={b,d,k,m};
		for(i=0;i<4;i++){
			this.cena[i]=a[i];
		}
	}
	public void setVzdialenost(int b, int d, int k, int m){
		int i;
		int[] a={b,d,k,m};
		for(i=0;i<4;i++){
			this.vzdialenost[i]=a[i];
		}
	}
	public int getVzdialenost(int cislo){
		return vzdialenost[cislo];
	}
	public int getPeniaze(){
		return peniaze;
	}
	public void zvysPeniaze(int peniaze){
		Mesto.peniaze+=peniaze;
	}
	public void znizPeniaze(int peniaze){
		Mesto.peniaze-=peniaze;
	}

	
	public void vyroba(int druh){ //pre kazdy tip mesta specificke
		if(this.budovy[0]!=null)
			this.budovy[0].vyroba(this, druh);
	}
	

}
