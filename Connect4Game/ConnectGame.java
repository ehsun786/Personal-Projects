import java.util.*;

public class ConnectGame{

public static void main(String[] args){

	int column = 0;
	Scanner scanner = new Scanner(System.in);
	System.out.print("Enter number of connected pieces needed for a win: ");
	int size = scanner.nextInt();
	ConnectSome game = new ConnectSome(size);
	while(!game.isFinished()){
		System.out.println(game);
		boolean wrongMove = true;
		int win=0;
		while(wrongMove){
			System.out.print("Enter column to play in: ");
			column = scanner.nextInt();
			if(game.play(column)){
				wrongMove = false;
				System.out.println(game);
				game.updateWinner(ConnectSome.YELLOW);
			}
		}
		if(game.getWinner() == ConnectSome.YELLOW)
			System.out.println("You win");
		else{
			win = game.winningMove(ConnectSome.YELLOW);
			if(win>=0)
				System.out.println("If red isn't careful, yellow wins by playing in column "+ win);
			else
				System.out.println("You could NOT win if it was your move now.");
			System.out.println("\nRed plays a piece.\n");
			//game.playDumb();
			// for testing you can replace the above line by
			game.playSmart();
			game.updateWinner(ConnectSome.RED);
			if(game.getWinner() == ConnectSome.RED){
				System.out.println(game);
				System.out.println("I win");
			}
			else if(game.boardIsFull()){
				game.setFinished();
				System.out.println(game);
				System.out.println("Draw");
			}
			else{
				win = game.winningMove(ConnectSome.YELLOW);
				if(win>=0){
					System.out.println("You can win now, you know..."+ win);
				}
				win = game.winningMove(ConnectSome.RED);
				if(win>=0){
					System.out.println("You'd better look out! "+win);
				}
				
			}
		}
	}	
}
}
