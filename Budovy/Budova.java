package Budovy;

import Mesta.*;

public interface Budova {
	void zvysUroven(int uroven);
	int getUroven();
	void setUroven(int uroven);
	void vyroba(Mesto mesto);
}
