package hra;
import Mesta.*;

public class Turn {
	private static int kolo=0;
	
	public static String vypis(Mesto[] Mesta){ //vypis tovaru pre kontrolu
		int i,j;
		String[] mena={"Bavlnovo", "Drevovo", "Kamenovo", "Mramorovo"};
		String sprava="";
		for(i=0;i<4;i++){  
			sprava= sprava + mena[i] +": ";
			for(j=0;j<4;j++)
				sprava= sprava + Mesta[i].getTovar(j) + ", ";
				sprava= sprava + "\n";
		}
		return sprava;
	}
	
	public static int Kolo(Mesto[] Mesta){
		int i;
		for(i=0;i<4; i++){
			Mesta[i].vyroba();
		}
		return ++kolo;
	}
	
	public static Mesto[] Nastav(){  //nastavenie hodnot na zaciatok hry, TREBA OPRAVIT
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
		return Mesta;
	}
}
