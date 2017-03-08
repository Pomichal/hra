package Mesta;
import java.util.Scanner;
import hra.Vyprava;
import hra.Turn;

public class Mesto implements ZakladMesta {
	private boolean moje;  //ovladnutie mestskeho trhu
	private int[] mnozstvo={0,0,0,0}; //tipy poloziek:0:bavlna, 1:drevo, 2:kamen, 3:mramor(?)
	private int[] cena={0,0,0,0};  //cena tovarov
	private static int peniaze=50;  //zdroje hraca
	private int[] vzdialenost={0,0,0,0}; //vzdialenost do ostatnych miest (v kolach)
	
	
	//pridat budovy(?)
	
	
	public Mesto(boolean moje, int b, int d, int k, int m){ //konstruktor
		int i;
		setMoje(moje);
		int[] a={b,d,k,m};
		for(i=0;i<4;i++){
			setTovar(i,a[i]);
		}
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
	
	public void vyroba(){ //pre kazdy tip mesta specificke
		
	}
	
	
	public void vyslatVypravu(Vyprava[] vypravy, Mesto[] Mesta){
		int typ, mnozstvo, ciel,start,j=0;
			start=zistitStart(); //zistit start,ciel, typ tovaru, mnozstvo a vzdialenost
			if(start<3){
			if(Mesta[start].getMoje()){
				ciel=zistitCiel();
				int dialka= this.getVzdialenost(ciel);
				System.out.println("Vzdialenost (1 zlato na jednotku/kolo): "+ dialka);
				typ=zistitTyp();
				mnozstvo=zistitMnozstvo();
				if(this.getTovar(typ)<mnozstvo) System.out.println("Nedostatok tovaru");
				else if(mnozstvo*this.getVzdialenost(ciel)>this.getPeniaze())
					System.out.println("Nedostatok zlatych");
				else{
					while(j<15 && vypravy[j]!=null) j++;
						if(j<15){
						Mesta[start].znizTovar(typ, mnozstvo);
						vypravy[j]=new Vyprava(dialka, typ, mnozstvo,start, ciel);
						if(vypravy[j].overPrichod(Mesta)) vypravy[j]=null;
						znizPeniaze(Mesta[start].getVzdialenost(ciel)*mnozstvo);   //odpocita naklady na cestu
						}
						else {
							System.out.println("Nemas volny karavan!");
							return;
						}
				}
			} else 	System.out.println("Toto mesto neovladas");
		}
	}
	
	public void presunTovaru(Vyprava[] vypravy, Mesto[] Mesta){
		int typ, mnozstvo, ciel,start,j=0;
			start=zistitStart(); //zistit start,ciel, typ tovaru, mnozstvo a vzdialenost
			if(start<3){
			if(Mesta[start].getMoje()){
				ciel=zistitCiel();
				if(Mesta[ciel].getMoje()){
					int dialka= this.getVzdialenost(ciel);
					System.out.println("Vzdialenost (1 zlato na jednotku/kolo): "+ dialka);
					typ=zistitTyp();
					mnozstvo=zistitMnozstvo();
					if(this.getTovar(typ)<mnozstvo) System.out.println("Nedostatok tovaru");
						else if(mnozstvo*this.getVzdialenost(ciel)>this.getPeniaze())
							System.out.println("Nedostatok zlatych");
						else{
							while(j<15 && vypravy[j]!=null) j++;
								if(j<15){
								Mesta[start].znizTovar(typ, mnozstvo);
								vypravy[j]=new Vyprava(dialka, typ, mnozstvo,start, ciel);
								vypravy[j].setCena(-1);
								if(vypravy[j].overPrichod(Mesta)) vypravy[j]=null;
								znizPeniaze(Mesta[start].getVzdialenost(ciel)*mnozstvo);   //odpocita naklady na cestu
								}
								else {
									System.out.println("Nemas volny karavan!");
									return;
								}
					}
				} else System.out.println("Cielove mesto neovladas");
			} else 	System.out.println("Toto mesto neovladas");
		}
	}
	
	/*public boolean zistitZamer(){
		boolean a;
		System.out.println("Chcete poslat vypravu? (1-ano, 0-nie)");
		Scanner scan = new Scanner(System.in);
		if(scan.nextInt()==1) a=true; 
			else a=false;
		//scan.close();
		return a; 
	}*/
	
	public int zistitStart(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Z ktoreho mesta vyslat vypravu? (0:Bavlnovo, 1:Drevovo, 2:Kamenovo, 3:Mramorovo, 4:zrusit)");
		int start=scan.nextInt();
		//scan.close();
		return start;
	}
	public int zistitCiel(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Do ktoreho mesta? (0:Bavlnovo, 1:Drevovo, 2:Kamenovo, 3:Mramorovo)");
		int ciel=scan.nextInt();
		//scan.close();
		return ciel;
	}
	public int zistitTyp(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Aky tovar? (0:bavlna, 1:drevo, 2:kamen, 3:mramor)");
		int typ=scan.nextInt();
		//scan.close();
		return typ;
	}
	public int zistitMnozstvo(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Ake mnozstvo?");
		int mnozstvo=scan.nextInt();
		//scan.close();
		return mnozstvo;
	}
}	
	
	
	
	
	
	/*
	 * public void vyslatVypravu(Vyprava[] vypravy, Mesto[] Mesta){
		int odpoved, typ, mnozstvo, ciel,start,j=0;
		Turn.vypis(Mesta);
		Turn.vypis(vypravy);
		System.out.println("Chcete poslat vypravu? (1-ano, 0-nie)");
		Scanner scan = new Scanner(System.in);
		odpoved=scan.nextInt();
		if(odpoved==1){  //zistit start,ciel, typ tovaru, mnozstvo a vzdialenost
			System.out.println("Z ktoreho mesta? (0:Bavlnovo, 1:Drevovo, 2:Kamenovo, 3:Mramorovo)");
			start=scan.nextInt();
			if(Mesta[start].getMoje()){
				System.out.println("Do ktoreho mesta? (0:Bavlnovo, 1:Drevovo, 2:Kamenovo, 3:Mramorovo)");
				ciel=scan.nextInt();
				int dialka= this.getVzdialenost(ciel);
				System.out.println("Vzdialenost (1 zlato na jednotku/kolo): "+ dialka);
				System.out.println("Aky tovar? (0:bavlna, 1:drevo, 2:kamen, 3:mramor)");
				typ=scan.nextInt();
				System.out.println("Ake mnozstvo?");
				mnozstvo=scan.nextInt();
				if(this.getTovar(typ)<mnozstvo) System.out.println("Nedostatok tovaru");
				else{
					while(j<15 && vypravy[j]!=null) j++;
						if(j<15){
						this.znizTovar(typ, mnozstvo);
						vypravy[j]=new Vyprava(dialka, typ, mnozstvo,start, ciel);
						if(vypravy[j].overPrichod(Mesta)) vypravy[j]=null;
						znizPeniaze(vzdialenost[ciel]*mnozstvo);   //odpocita naklady na cestu
						}
						else {
							System.out.println("Nemas volny karavan!");
							return;
						}
				}
			} else 	System.out.println("Toto mesto neovladas");
			vyslatVypravu(vypravy, Mesta);
		}
	}
	
	
	
	
	public int[] predaj(Mesto[] Mesta, int start){ //predaj tovaru
		int odpoved, typ, mnozstvo, ciel;
		Scanner sc = new Scanner(System.in);
		Turn.vypis(Mesta);
		System.out.println(this.toString() + ":Chcete poslat vypravu? (1-ano, 0-nie)");
		if((odpoved=sc.nextInt())==0){
			sc.close();
			return null;
		}
		else if(odpoved==1){
			System.out.println("Do ktoreho mesta? (0:Bavlnovo, 1:Drevovo, 2:Kamenovo, 3:Mramorovo)");
			ciel=sc.nextInt();
			int prichod= Mesta[start].getVzdialenost(ciel);
			System.out.println("Vzdialenost (1 zlato na jednotku): "+ prichod);
			System.out.println("Aky tovar? (0:bavlna, 1:drevo, 2:kamen, 3:mramor)");
			typ=sc.nextInt();
			System.out.println("Ake mnozstvo?");
			mnozstvo=sc.nextInt();
			if(this.getTovar(typ)>=mnozstvo){
				System.out.println("Zisk:"+ Mesta[ciel].getCena(typ)*mnozstvo + " zlatych, Vydaje:" + prichod + " Zdroje:" + this.getPeniaze() + " zlatych");
				int[] A={typ,mnozstvo,ciel,start,prichod};
				sc.close();
			    return A;
			}
			else{
				System.out.println("Nedostatok tovaru");
				predaj(Mesta, start);
			}
		}
		sc.close();
		return null;
	}
	public void presun(Mesto[] Mesta){ //presun tovaru do ineho mesta
		int odpoved, typ, mnozstvo,ciel;
		Scanner sc = new Scanner(System.in);
		Turn.vypis(Mesta);
		System.out.println(this.toString() + ":Chcete presunut tovar? (1-ano, 0-nie)");
		if((odpoved=sc.nextInt())==0){
			sc.close();
			return;
		}
		else if(odpoved==1){
			System.out.println("Do ktoreho mesta? (0:Bavlnovo, 1:Drevovo, 2:Kamenovo, 3:Mramorovo)");
			ciel=sc.nextInt();
			System.out.println("Vzdialenost (1 zlato na jednotku): "+ this.vzdialenost[ciel]);
			if(Mesta[ciel].getMoje()){
			System.out.println("Aky tovar? (0:bavlna, 1:drevo, 2:kamen, 3:mramor)");
			typ=sc.nextInt();
			System.out.println("Ake mnozstvo?");
			mnozstvo=sc.nextInt();
			if(this.getTovar(typ)>=mnozstvo){
				this.znizTovar(typ, mnozstvo);
				Mesta[ciel].zvysTovar(typ, mnozstvo);
				znizPeniaze(vzdialenost[ciel]);   //odpocita naklady na cestu
				System.out.println("Presunute:"+ mnozstvo + " ks tovaru, Vydaje:" + vzdialenost[ciel] + " zlatych, Zdroje:" + getPeniaze() + " zlatych");
			}
			else{
				System.out.println("Nedostatok tovaru");
				presun(Mesta);
			}
			}else{
				System.out.println("Toto mesto neovladas");
				presun(Mesta);
			}
			sc.close();
		}
	}*/
