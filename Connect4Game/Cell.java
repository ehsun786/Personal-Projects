import java.lang.*;
import java.util.*;
public class Cell{
	boolean isWin;
	int owner;
	
       public Cell() {
		owner = ConnectSome.NONE;
       }
       
       public void setOwner(int player) {
		owner = player;
       }
       
       public int getOwner() {
		return owner;
       }
       
       public void setPartOfWin() {
		isWin = true;
		//System.out.println("The cell is part of a winning combination");
       }
       
       public String toString() {
	       if (owner == 0 && isWin == true) {
			return "O";  
		} else if (owner == 0 && isWin == false) {
			return "o";
		} else if (owner == 1 && isWin == true) {
			return "X";
		} else if (owner == 1 && isWin == false) {
			return "x";
		} else {
			return ".";
		}
       }
       
       
       
       
}	
