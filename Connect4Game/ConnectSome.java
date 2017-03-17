public class ConnectSome{
    
    public static final int YELLOW = 0;
    public static final int RED = 1;
    public static final int NONE = -1;
    public static int width;
    public static int playerToMove;
    public static int height; 	
    public static int winner = NONE;
    public static int size1;
    public static boolean finished = false; 
    static Cell[][] playBoard;
	
    
    public ConnectSome(int size) {
		
		if (size < 1 || size > 9) {
			size = 4;
		}
		width = ((2*size) -1);
		height = size + 2;
		playBoard = new Cell[height][width]; // [rows] [columns]
		//playBoard[0][0] =  YELLOW;
		playerToMove = YELLOW;
		
		for (int i =0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				playBoard[i][j] = new Cell();
			}
		}
		size1 = size;
    }
    
    public  boolean boardIsFull() {
		for (int i = 0; i < playBoard.length; i++) {
			for (int j = 0; j < playBoard[i].length; j++) {
				if (playBoard[i][j].getOwner() == NONE) {
					return false;
				}
			}
		}
		return true;
					
    }
    
    public int getGoal() {
    	return size1;
    }
    
    public int getPieceAt(int row, int column) {
    	return playBoard[row][column].getOwner();
    }
    
    public int getPlayerToMove() {
	return playerToMove;
	/*
	int rowNum = 0;
    	int columnNum = 0;
    	for (int i = 0; i < playBoard.length; i++) {
			for (int j = 0; j < playBoard[i].length; j++) {
				 if (playBoard[i][j] == "." ) {
					 rowNum = i;
					 ColumnNum = j;
					 i = playBoard.length;
					 j = playBoard[i].length;
				 }
			}
		}
    	
    	if (columnNum == 0) {
    		rowNum = rowNum - 1;
    		columnNum = playBoard[i].length - 1;
    		if (playBoard[rowNum][columnNum] == )
    	} else {
    		columnNum = playBoard[i].length - 1;
    		return playBoard
    	}
	*/
    }
    
    public int getWinner() {
	return winner;
    }
    
    public boolean isFinished() {
	return finished;
    }
    
    public boolean play(int column) {
	int counter1 = 0;
	if (column < 0 || column > width-1 || boardIsFull() == true) {
		return false;
	} else {
		for (int i = 0;  i < height; i++) {
			if (playBoard[i][column].getOwner() == NONE) {
			counter1++;
			}
		}
		
		if (counter1 == 0) {
			return false;
	        } else {
			playBoard[counter1-1][column].setOwner(playerToMove);
			if (playerToMove == YELLOW) {
				playerToMove = RED;
			} else {
				playerToMove = YELLOW;
			}	
			 return true;
		}
	}

       
	
	
    }
    
    public boolean playDumb() {
	int counter1 = 0;
	int column = 0;
	    
	if  (boardIsFull() == true) {
		return false;
	} else {
		for (int j = 0; j < width; j++) { 
			for (int i = 0;  i < height; i++) {
				if (playBoard[i][j].getOwner() == NONE) {
				counter1++;
			        }
			}
			if  (counter1 != 0) {
				playBoard[counter1-1][j].setOwner(playerToMove);
				if (playerToMove == YELLOW) {
					playerToMove = RED;
				} else {
					playerToMove = YELLOW;
				}	
				return true;
			}
		}
	}
	if (playerToMove == YELLOW) {
		playerToMove = RED;
	} else {
		playerToMove = YELLOW;
	}	
	return false;
	
    }
    
    public boolean playSmart() {
	boolean flag1 = false;
	boolean flag2 = false;
	
	if (playerToMove == YELLOW) {
		if (winningMove(YELLOW) != -1) {
			if (play(winningMove(YELLOW)) == true) {
			return true;
		        }  
		} else {
			if (winningMove(RED) != -1) {
				if (play(winningMove(RED)) == true) {
				return true;
				}
			} else {
				flag1 = true;
			}
		}			
	} else {
		if (winningMove(RED) != -1) {
			if (play(winningMove(RED)) == true) {
			return true;
			}
		} else {
			if (winningMove(YELLOW) != -1) {
				if (play(winningMove(YELLOW)) == true) {
				return true;
				}
			} else {
				flag2 = true;
			}
		}	
	}
	
	if (flag2 == true && flag1== true) {
		playDumb();
		return false;
	} else {
		return true;
	}
		
    }
    
    public void setFinished() {
	finished = true;
    }
    
    public void setPlayerToMove() {
    	if (playerToMove == YELLOW) {
		playerToMove = RED;
	} else {
		playerToMove = YELLOW;
	}
    }
    
    public String toString() {
	StringBuffer buf = new StringBuffer("");
	for (int i = 0; i < playBoard.length; i++) {
		buf.append("|");
		for (int j = 0; j < playBoard[i].length; j++) {
				buf.append(playBoard[i][j].toString());
		}
		buf.append("|\n");
	}
	buf.append("+");
	for (int i1 = 0; i1 < width; i1++) {
		buf.append("-");
	}		
	buf.append("+");
	return buf.toString();
    }
    
    public boolean unplay(int column) {
	int counter1 = 0;
	
	if (boardIsFull() == true) {
		return false;
	} else {
		
		for (int i = 0; i < height; i++) {
			if (playBoard[i][column].getOwner() == NONE) {
			counter1++;
			}
		}
		
		if (counter1 == height) {
			return false;
		} else {
			playBoard[counter1][column].setOwner(NONE);
			if (playerToMove == YELLOW) {
				playerToMove = RED;
			} else {
				playerToMove = YELLOW;
			}
			return true;
		}
		
		
	}
	    
	
	
	
	
    }
    
    public void updateWinner(int player) {
	if (columnWithGoalVertical(player) == size1) {
		winner  = player;
	} else if (rowWithGoalHorizontal(player) == size1) {
		winner = player;
	} else {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (lengthOfDownDiagonal(player, i, j) == size1 || lengthOfUpDiagonal(player, i, j) == size1) {
					winner = player;
					break;
				}
			}				
		}
	}
    }
    
    public int winningMove(int player) {
	int  x = 0;
	int playerToMove1 = playerToMove;
		while (x < width) {
		if (player == YELLOW) {
		playerToMove = YELLOW;
		} else {
		playerToMove = RED;
		}
		if (play(x)) {
			if (columnWithGoalVertical(player) != -2) {
				unplay(x);
				playerToMove = playerToMove1;
				return x;
			} else if (rowWithGoalHorizontal(player) != -2)  {
				unplay(x);
				playerToMove = playerToMove1;
				return x;
			} else {
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (lengthOfDownDiagonal(player, i, j) >= size1) {           //|| lengthOfUpDiagonal(player, i, j) == size1-1) {
							unplay(x);
							playerToMove = playerToMove1;
							return x;
						}
					}				
				}
				for (int k = 0; k < height; k++) {
					for (int l = 0; l < width; l++) {
						if (lengthOfUpDiagonal(player, k, l) >= size1) {
							unplay(x);
							playerToMove = playerToMove1;
							return x;
						}
					}
				}
			
			}
		}
		unplay(x);
		x++;
	}
	playerToMove = playerToMove1;
	return -1;
    }
    
    
    public int rowWithGoalHorizontal(int player) {
	int counter1 = 0;
	int i =0;
	while (i < playBoard.length) {
       		for (int j = 0; j < playBoard[i].length; j++) {
       			if (playBoard[i][j].getOwner() == player) {
				counter1++;
			} else {
				counter1=0;
			}
       		}
		if (counter1 >= size1) {
			return i;
		}
		i++;
       	}
	return -2;
    }
   
      public int columnWithGoalVertical(int player) {
	int counter1 = 0;
	int j = 0;
	while (j < width) {
		for (int i = 0; i < playBoard.length; i++) {
			if (playBoard[i][j].getOwner() == player) {
				counter1++;
			} else {
				counter1 = 0;
			}
		}
		if (counter1 >= size1) {
			return j;
		} 
		j++;
	}
	return -2;
     }
     
     public int lengthOfDownDiagonal(int player, int row, int column) {
	int counter1 = 0;
	for (int i = row, j = column; i < height && j >=0 ; i++, j--) {
		if (playBoard[i][j].getOwner() == player) {
			counter1++;
		} else {
			counter1 = 0;
		}
	}		
	return counter1;
     }
     
     public int lengthOfUpDiagonal(int player, int row, int column) {
	int counter1 = 0;
	for (int i = row, j = column; i >= 0 && j >= 0; i--,j--) {
		if (playBoard[i][j].getOwner() == player) {
			counter1++;
		} else {
			counter1=0;
		}
	}		
	return counter1;
     }
     
     public void FindWinningPieces(int player) {
	
     }
     
     public static void main(String [] args) {
		ConnectSome a = new ConnectSome(2);
		//a.play(1);
		a.play(2);
		a.play(2);
		//a.play(3);
		//a.play(3);
	     System.out.print(a.winningMove(RED));
		/*
		a.play(1);
		a.play(3);
		a.play(2);
		a.play(3);
		a.play(0);
		a.play(2);
		a.play(4);
		a.play(0);
		a.play(1);
		a.play(3);
		a.play(4);
		a.play(3);
		a.play(4);
		a.play(4);
		a.play(4);
		*/
		System.out.println(a.toString());
		
     }
     
}


