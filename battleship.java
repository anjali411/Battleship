import java.lang.*;
import java.util.*;
import java.io.*;

class battleship{
	public int size;
	public player Player1;
	public player Player2;
	public int[] shipsize;
	public battleship(int size)
	{
		this.size = size;
		Player1 = new player(size);
		Player2 = new player(size);
		shipsize = new int[size/2];
	}
	public void setShipSizes(int maxLength)
	{
		Random ran = new Random();
		for(int i=0; i<shipsize.length; i++)
			shipsize[i] = ran.nextInt(maxLength) + 1;
		Arrays.sort(shipsize);
	}
	
	public void randomGenerate(player Player)
	{
		int curr = 0;
		Random ran = new Random();
		for(int i = 0; i<shipsize.length; i++)
		{
			int success =0;
			while(success == 0)
			{
				int runfurther = 1;
				if(curr == 0)
				{
					int y;
					int row = ran.nextInt(size) + 0;
					int col = ran.nextInt(size-shipsize[i]) + 0;
					for(y = col; y<col+shipsize[i]; y++)
						{
							if(Player.fixedBoard[row][y] == 1)
								runfurther=0;
						}
					if(y == col+shipsize[i] && runfurther==1)
					{
						success = 1;
						curr = 1-curr;
						for(int x = col; x<col+shipsize[i]; x++)
						{
							Player.fixedBoard[row][x] = 1;
							Pair<Integer,Integer> q1 = new Pair<Integer,Integer>(row,x);
							Pair<Integer,Integer> q2 = new Pair<Integer,Integer>(row,col);
							Pair<Integer,Integer> q3 = new Pair<Integer,Integer>(row,col+shipsize[i]-1);
							Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> q4 = new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(q2,q3);
							Player.targets.put(q1,q4);
						}
					}
					
				}
				else
				{
					int y;
					int col = ran.nextInt(size) + 0;
					int row = ran.nextInt(size-shipsize[i]) + 0;
					for(y = row; y<row+shipsize[i]; y++)
						{
							if(Player.fixedBoard[y][col] == 1)
								runfurther=0;
						}
					if(y == row+shipsize[i] && runfurther == 1)
					{
						success =1;
						for(int x = row; x<row+shipsize[i]; x++)
						{
							Player.fixedBoard[x][col] = 1;
							Pair<Integer,Integer> q1 = new Pair<Integer,Integer>(x,col);
							Pair<Integer,Integer> q2 = new Pair<Integer,Integer>(row,col);
							Pair<Integer,Integer> q3 = new Pair<Integer,Integer>(row+shipsize[i]-1,col);
							Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> q4 = new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(q2,q3);
							Player.targets.put(q1,q4);
						}
						curr = 1-curr;
					}
					
				}
			}
		}
	}
	public void setBoards()
	{
		int[] shipSize = new int[size-2];
		Random ran = new Random();
		int maxLength = size/2;
		setShipSizes(maxLength 	);
		randomGenerate(this.Player1);
		//print statements
		randomGenerate(this.Player2);
		//setShip();
		System.out.println("Player1 fixedBoard: ");
		Player1.printFixedBoard();
		System.out.println("-----------");
		System.out.println("Player2 fixedBoard: ");
		Player2.printFixedBoard();
		System.out.println("-----------");	
		System.out.println("Game begins");
	}
	public void playGame()
	{
		int p1 = 0, p2 = 0;
		int curr=0;
		int row=0,col=0;
		Random ran = new Random();
		while( p1!=shipsize.length && p2!=shipsize.length)
		{
			if(curr == 1)
			{
				System.out.println("Player2 playing ... Player1 board:");
				int l=1;
				while(l==1)
				{
					row = ran.nextInt(size) + 0;
					col = ran.nextInt(size) + 0;
					if(Player1.getPlayingBoardVal(row,col) == 'o')
						l=0;
				}
				Player1.setPlayingBoard(row,col);
				Player1.printPlayingBoard();
				p1 = Player1.checkCurrent();
				curr =1-curr;
				
			}
			else
			{
				System.out.println("Player1 playing ... Player2 board:");
				int l=1;
				while(l==1)
				{
					row = ran.nextInt(size) + 0;
					col = ran.nextInt(size) + 0;
					if(Player2.getPlayingBoardVal(row,col) == 'o')
						l=0;
				}
				Player2.setPlayingBoard(row,col);
				p2=Player2.checkCurrent();
				Player2.printPlayingBoard();
				curr = 1-curr;
			}
		}
		if(p1>p2)
			System.out.println("Player2 won");
		else
			System.out.println("Player1 won");
	}
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to Battleship\n" + "Rules:\n1)Unplayed position: o\n2)Target on point: x\n3)Ship found: F\n"+"Enter the size of the board that you want");
		int a = s.nextInt();
		battleship game = new battleship(a);
		game.setBoards();
		game.playGame();
	}
}
