import java.lang.*;
import java.io.*;
import java.util.*;

class player
{
	public int size;
	public int current;
	public char[][] playingBoard;
	public int[][] fixedBoard;
	public HashMap < Pair<Integer,Integer> , Pair< Pair<Integer,Integer>,Pair<Integer,Integer> > > targets;
	public player(int size)
	{
		targets = new HashMap < Pair<Integer,Integer> , Pair< Pair<Integer,Integer>, Pair<Integer,Integer> > >();
		this.size=size;
		fixedBoard = new int[size][size];
		playingBoard = new char[size][size];
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{
				fixedBoard[i][j]=0;
				playingBoard[i][j]='o';
			}
		}
		current = 0;
	}
	public int getFixedBoardVal(int i, int j)
	{
		return fixedBoard[i][j];
	}
	public char getPlayingBoardVal(int i, int j)
	{
		return playingBoard[i][j];
	}
	public void setPlayingBoard(int i, int j)
	{
		if(fixedBoard[i][j] != 1)
			playingBoard[i][j] = 'M' ;
		else
			{
				playingBoard[i][j] = 'x';
				checkTarget(i,j);
			}
	}
	public void checkTarget(int i, int j)
	{
		int flag = 0;
		int check = 1;
		Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> final1 = targets.get(new Pair(i,j));
		if((final1.getKey()).getKey() == final1.getValue().getKey())
			flag =1;		
		if(flag == 1)
		{
			for(int x =final1.getKey().getValue(); x<=final1.getValue().getValue(); x++)
			{
				if(playingBoard[i][x] == 'o')
					{
						check=0;
						break;
					}
			}

		}
		else
		{
			for(int x =final1.getKey().getKey(); x<=final1.getValue().getKey(); x++)
			{
				if(playingBoard[x][j] == 'o')
					{
						check=0;
						break;
					}
			}
		}

		if(check == 1 && flag == 1)
		{
			for(int x =final1.getKey().getValue(); x<=final1.getValue().getValue(); x++)
			{
				playingBoard[i][x] = 'F';
			}
			current++;
		}
		if(check == 1 && flag == 0)
		{
			for(int x =final1.getKey().getKey(); x<=final1.getValue().getKey(); x++)
			{
				playingBoard[x][j] = 'F';
			}
			current++;
		}
	}
	public void printPlayingBoard()
	{
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
				System.out.print(playingBoard[i][j] + " ");
			System.out.println();
		}
	}
	public void printFixedBoard()
	{
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
				System.out.print(fixedBoard[i][j] + " ");
			System.out.println();
		}
	}
	public void printHashMap()
	{
		Iterator it = targets.entrySet().iterator();
   		while (it.hasNext())
   		{
         HashMap.Entry p = (HashMap.Entry)it.next();
         Pair<Integer,Integer> pair = (Pair<Integer,Integer>)p.getKey(); 
         Pair< Pair <Integer,Integer> , Pair <Integer,Integer> > pp = (Pair< Pair <Integer,Integer> , Pair <Integer,Integer> >)p.getValue();
         Pair<Integer,Integer> pair2 = (Pair<Integer,Integer>)pp.getValue(); 
         Pair<Integer,Integer> pair3 = (Pair<Integer,Integer>)pp.getKey(); 
         
         System.out.println(pair.getKey() + " " + pair.getValue() + " Start : (" + pair3.getKey() + ", " + pair3.getValue() + ") " + " End : (" + pair2.getKey() + ", " + pair2.getValue() + ") ");
    	}
	}

	public int checkCurrent()
	{
		return current;
	}

}