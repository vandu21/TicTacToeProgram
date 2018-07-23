import java.util.Scanner;

public class TicTacToeTest {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner s = new Scanner(System.in);
        game.initializeBoard();
        System.out.println("Tic-Tac-Toe!");
//int x;
//int y;
        do{

            System.out.println("Current Board Layout");
            game.printBoard();
            int row=0;
            int col=0;

            do{
                System.out.println("Player " + game.getCurrentPlayer() + ",enter an empty row or column to place your mark!");

                row = s.nextInt()-1;
                col = s.nextInt()-1;
            }while(!game.placeMark(row,col));

            game.changePlayer();

        }while(!game.checkForWin() && !game.isBoardFull());


        if(game.isBoardFull() && !game.checkForWin()){
            System.out.println("The game was tie");
//System.exit(0);
        }
        else{
            System.out.println("Current Board Layout:");
            game.printBoard();
            game.changePlayer();
            System.out.println(Character.toUpperCase(game.getCurrentPlayer()) + " wins!");
        }
    }
}

class TicTacToe{

    private char[][] board;
    private char currentPlayerMark;

    public TicTacToe(){
        board = new char[3][3];
        currentPlayerMark = 'x';
        initializeBoard();
    }

    public char getCurrentPlayer(){
        return currentPlayerMark;
    }

    public void initializeBoard(){
        for(int i=0; i<3; i++){
            for(int j=0;j<3;j++){
                board[i][j] = '-';
            }
        }
    }

    public void printBoard(){
        System.out.println("-----------");
        for(int i=0;i<3;i++){
            System.out.print("| ");
            for(int j=0;j<3;j++){
                System.out.print(board[i][j] + "| ");
            }
            System.out.println();
            System.out.println("-----------");
        }
    }

    public boolean isBoardFull(){
        boolean isFull = true;

        for(int i=0; i<3; i++){
            for(int j=0;j<3;j++){
                if(board[i][j] == '-')
                    isFull = false;
            }
        }
        return isFull;
    }

    private boolean checkRowsForWin(){
        for(int i=0;i<3;i++){
            if(checkRowCol(board[0][i],board[1][i],board[2][i]) == true){
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin(){
        for(int i=0;i<3;i++){
            if(checkRowCol(board[i][0],board[i][1],board[i][2]) == true){
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin(){
        return((checkRowCol(board[0][0], board[1][1], board[2][2])==true) || (checkRowCol(board[0][2], board[1][1], board[2][0])==true));
    }

    private boolean checkRowCol(char c1, char c2, char c3){
        return((c1 != '-') && (c1==c2) && (c2==c3));
    }

    public boolean checkForWin(){
        return(checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    public void changePlayer(){
        if(currentPlayerMark == 'x'){
            currentPlayerMark = '0';
        }
        else{
            currentPlayerMark = 'x';
        }
    }

    public boolean placeMark(int row, int col){
        if((row>=0) && (row<3)){
            if((col>=0) && (col<3)){
                if(board[row][col] == '-'){
                    board[row][col] = currentPlayerMark;
                    return true;
                }
            }
        }
        return false;
    }

}
