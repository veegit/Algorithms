package com.vee.test;

import java.util.Hashtable;
import java.util.Iterator;

public class GameOfLife {
	Hashtable<Cell,Cell> currentShape = new Hashtable<Cell,Cell>();
	Hashtable<Cell,Cell> nextShape = new Hashtable<Cell,Cell>();
	int size;
	Cell[][] grid;
	
	public GameOfLife(int size) {
		this.size = size;
		grid = new Cell[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				grid[i][j] = new Cell(i,j);
		
	}
	
	private synchronized void getNext() {
		nextShape.clear();

		Iterator<Cell> it0 = currentShape.keySet().iterator();
		while ( it0.hasNext()) {
	      Cell cell = it0.next();
	      cell.neighbour = 0;
	    }
		
		Iterator<Cell> it = currentShape.keySet().iterator();
		while(it.hasNext()) {
			Cell cell = it.next();
			int col = cell.row;
			int row = cell.col;
			addNeighbour( col-1, row-1 );
		    addNeighbour( col, row-1 );
		    addNeighbour( col+1, row-1 );
		    addNeighbour( col-1, row );
		    addNeighbour( col+1, row );
		    addNeighbour( col-1, row+1 );
		    addNeighbour( col, row+1 );
		    addNeighbour( col+1, row+1 );
		}
		
		/* remove from CURRENT SHAPE */
		Iterator<Cell> it1 = currentShape.keySet().iterator();
		while(it1.hasNext()){
			Cell cell = it1.next();
				if(cell.neighbour != 2 && cell.neighbour!= 3) {
					//IMP if you do map.remove(cell) it breaks
					it1.remove();
				}
		}
		
		/* ADD FROM NEXT SHAPE */
		Iterator<Cell> it2 = nextShape.keySet().iterator();
		while(it2.hasNext()){
			Cell cell = it2.next();
			if(cell.neighbour == 3) {
				//setCell(cell.row,cell.col);
				currentShape.put(cell, cell);
			}
		}
	}
	
	private synchronized void addNeighbour(int row, int col){
		Cell cell = (Cell)nextShape.get( grid[row][col] );
	      if ( cell == null ) {
	        Cell c = grid[row][col];
	        c.neighbour = 1;
	        nextShape.put(c, c);
	      } else {
	        cell.neighbour++;
	      }
	}
	
	public synchronized void setCell( int col, int row) {
	   Cell cell = grid[row][col];
	   currentShape.put(cell, cell);
    }
	
	public static void main(String args[]) {
		GameOfLife g = new GameOfLife(7);
		
		//Always set the cell value
		//Dont add a new cell like currentShape.put(new Cell(),new Cell())
		g.setCell(3,3);
		g.setCell(2,3);
		g.setCell(3,2);
		g.setCell(4,3);
		g.setCell(3,4);
		
		Iterator<Cell> be = g.currentShape.keySet().iterator();
		while (be.hasNext()) {
			Cell cell = (Cell) be.next();
			System.out.print(cell.row +":" + cell.col+" ");
		}
		System.out.println();
		g.getNext();
		
		Iterator<Cell> af = g.currentShape.keySet().iterator();
		while (af.hasNext()) {
			Cell cell = (Cell) af.next();
			System.out.print(cell.row + ":" + cell.col+" ");
		}
		
	}
}

class Cell {
	int col;
	int row;
	public int neighbour=0;
	
	public Cell(int row,int col) {
		this.row = row;
		this.col = col;
	}
	
	public Cell(int row,int col, int neighbour) {
		this.row = row;
		this.col = col;
		this.neighbour = neighbour;
	}
}
