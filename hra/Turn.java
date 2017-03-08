package hra;
//import java.util.Scanner;
import Mesta.*;

public class Turn {
	
	public static String vypis(Mesto[] Mesta){ //vypis tovaru pre kontrolu
		int i,j;
		String sprava="";
		for(i=0;i<4;i++){  
			sprava= sprava + Mesta[i].toString() +": ";
			for(j=0;j<4;j++)
				sprava= sprava + Mesta[i].getTovar(j) + ", ";
				sprava= sprava + "\n";
		}
		return sprava;
	}
	
	public static void vypis(Vyprava[] vypravy){  //vypis vyprav
		int i;
		String smer;
		for(i=0;vypravy[i]!=null;i++){  
			if(vypravy[i].getMnozstvo()==0) smer="Vracujuci sa";
			else smer="Na ceste s tovarom";
			System.out.println((i+1) + ". vyprava, prichod o: " + vypravy[i].getPrichod() + " kol(o)" + ", smer:" + smer);
		}
	}
	
/*	public static void main(String[] args) {
		Mesto[] Mesta = new Mesto[4];         //pocet miest
		Vyprava[] vypravy = new Vyprava[15];  //pocet karavanov
		Nastav(Mesta,vypravy);
		int i,turn, odpoved;
		Scanner sc = new Scanner(System.in);
		for(turn=1;turn<1000;turn++){       		//zaciatok kola
			System.out.println(turn+". KOLO");
			if(vypravy[0]!=null) vypravy[0].prichody(vypravy, Mesta);  //najde a vyhodnoti vypravy, co dorazili do ciela
			//Turn.vypis(Mesta);
			//Turn.vypis(vypravy);
			for(i=0;Mesta[i].getMoje();i++){   //vyroba v ovladanych mestach
				Mesta[i].vyroba();
			}
			System.out.println("Zdroje:" + Mesta[0].getPeniaze() + "zlatych");
			odpoved=0;
			while(odpoved!=2){
			System.out.println("Co chcete robit? (0-vyslat vypravu(predaj), 1-vyslat vypravu(presun), 2-koniec kola)");
			odpoved=sc.nextInt();
				switch(odpoved){	
				case 0:	Mesta[0].vyslatVypravu(vypravy, Mesta);  //moznost vyslat vypravu
						break;
				case 1: Mesta[0].presunTovaru(vypravy, Mesta);  //moznost presunut tovar
						break;
				}
			}
			System.out.println("koniec kola\n");
			for(i=0;vypravy[i]!=null;i++){  //vypravy sa blizia k cielu
				vypravy[i].znizPrichod();
			}
		}
		sc.close();
	}
	*/
	public static String Nastav(){  //nastavenie hodnot na zaciatok hry
		int i;
		Mesto[] Mesta = new Mesto[4];         //pocet miest
		Vyprava[] vypravy = new Vyprava[15];  //pocet karavanov
		Mesta[0]=new Bavlnovo(true,20,20,20,20);
		Mesta[1]=new Drevovo(false,20,20,20,20);
		Mesta[2]=new Kamenovo(false,20,20,20,20);
		Mesta[3]=new Mramorovo(false,20,20,20,20);
		Mesta[0].setCeny(3,5,10,15);
		Mesta[1].setCeny(5, 3, 10, 15);
		Mesta[2].setCeny(15,5,3,10);
		Mesta[3].setCeny(10,15,5,3);
		Mesta[0].setVzdialenost(0,2,3,3);				//nastavenie zaciatocnych hodnot
		Mesta[1].setVzdialenost(2, 0, 2, 4);
		Mesta[2].setVzdialenost(3,2,0,4);
		Mesta[3].setVzdialenost(3,4,4,0);
		for(i=0;i<15;i++){
			vypravy[i]=null;
		}
		return vypis(Mesta);
		
	}
}
