/* 
Compile with: javac longestIncreasingPath.java
Execute with: java longestIncreasingPath

Finds the longest increasing integer path in 
a user defined matrix.

*/

import java.util.*;

class longestIncreasingPath{
	
	ArrayList<String> list = new ArrayList<String>();
	
	private class cell{
		public int num;
		public boolean visited;
		
		public cell(int value){
			this.num = value;
			this.visited=false;
		}
	}
	
	public static void main(String[] args){
		longestIncreasingPath lip = new longestIncreasingPath();
		Scanner in = new Scanner(System.in);
		System.out.print("Enter matrix length > ");
		int length = in.nextInt();
		System.out.print("Enter matrix width > ");
		int width = in.nextInt();		
		cell[][] board = new cell[length][width];
		board = lip.createBoard(board, length, width);
		lip.printBoard(board, length, width);
		lip.findPath(board, length, width);
	}
	
	//creates a 2D 'cell' array
	//@param length, width -> entered by the user
	private cell[][] createBoard(cell[][] board, int length, int width){
		Random rand = new Random();
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
				int a = rand.nextInt(10);
				while(a == 0){
					a = rand.nextInt(10);
				}
				board[i][j] = new cell(a);
			}
		}
		return board;
	}
	
	private void findPath(cell[][] board, int length, int width){
		String path = "";
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
				path = Integer.toString(board[i][j].num);
				findPathRecurse(board, i, j, length, width, path);
			}
			path = "";
			board = setAllVisited(board, length, width);
		}
		
		path = "";
		
		//find the longest resulting path
		for(int i=0; i<list.size(); i++){
			if(list.get(i).length() > path.length()){
				path = list.get(i);
			}
		}
		
		//find other paths of the same length if they exist
		ArrayList<String> paths = new ArrayList<String>();
		for(int i=0; i<list.size(); i++){
			if(list.get(i).length() == path.length() && Collections.frequency(paths, list.get(i)) < 1){
				paths.add(list.get(i));
			}
		}
		Collections.sort(paths);
		System.out.println();
		System.out.println("The longest increasing path(s) in this matrix are:");
		for(int i=0; i<paths.size(); i++){
			System.out.print(" ");
			char[] answer = paths.get(i).toCharArray();
			for(int j=0; j<answer.length; j++){
				if(j < answer.length - 1){
					System.out.print(answer[j] + "  ->  ");
				}
				if(j == answer.length-1){
					System.out.print(answer[j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void findPathRecurse(cell[][] board, int i, int j, int length, int width, String path){
		if(i-1>=0){
			if(board[i-1][j].num > board[i][j].num && board[i-1][j].visited == false){
				board[i-1][j].visited = true;
				findPathRecurse(board, i-1, j, length, width, path + board[i-1][j].num);
			}
		}
		if(i+1<length){
			if(board[i+1][j].num > board[i][j].num && board[i+1][j].visited == false){
				board[i+1][j].visited = true;
				findPathRecurse(board, i+1, j, length, width, path + board[i+1][j].num);
			}
		}
		if(j-1>=0){
			if(board[i][j-1].num > board[i][j].num && board[i][j-1].visited == false){
				board[i][j-1].visited = true;
				findPathRecurse(board, i, j-1, length, width, path + board[i][j-1].num);
			}
		}
		if(j+1<width){
			if(board[i][j+1].num > board[i][j].num && board[i][j+1].visited == false){
				board[i][j+1].visited = true;
				findPathRecurse(board, i, j+1, length, width, path + board[i][j+1].num);
			}
		}
		list.add(path);
		board[i][j].visited = false;
	}
	
	private cell[][] setAllVisited(cell[][] board, int length, int width){
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
				board[i][j].visited = false;
			}
		}
		return board;
	}
	
	private void printBoard(cell[][] board, int length, int width){
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
				System.out.print(board[i][j].num + " ");
			}
			System.out.println();
		}
	}
}
