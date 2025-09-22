import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    enum Cell{
        cross('X'),
        zero('O'),
        empty('_')
        ;

        char Val;

        Cell(char val){
            this.Val = val;
        }
    }

    enum  Player{
        user("Пользователь"),
        computer("Компьютер")
        ;
        String Val;
        Player(String val){
            this.Val =val;
        }
    }
    static char[][] fieldGame = new char[3][3];
    static int count = 0;
    static int x,y;

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);
        Random R = new Random();
        Player activePlayer,Winner;
        Cell activeStep;
        boolean IsWin = false;

        //Рисуем поле
        for (int i = 0; i < fieldGame.length; i++) {
            for (int j = 0; j < fieldGame.length; j++) {
                fieldGame[i][j]=Cell.empty.Val;
                System.out.print("|"+fieldGame[i][j] + "|");
            }
            System.out.println();
        }

        int NumberOfPlayer = R.nextInt(0,2);
        if (NumberOfPlayer == 1){
            activePlayer = Player.computer;
        }else activePlayer = Player.user;

        activeStep = Cell.cross;
        System.out.println("Первый игорок: "+activePlayer.Val);

        while (IsWin == false) {
            System.out.println(activePlayer.Val);
            if (activePlayer == Player.user) {

                do {
                    System.out.print("Введите координаты по горизонтали (0-2):");
                    x = S.nextInt();
                    System.out.print("Введите координаты по вертикали (0-2):");
                    y = S.nextInt();
                } while (fieldGame[x][y] != Cell.empty.Val);

                printPlace(x,y,activeStep);


                IsWin = checkWin(activeStep);
                if (!IsWin) {
                    activePlayer = Player.computer;
                }

            }else{
                do {
                    x = R.nextInt(0, 3);
                    y = R.nextInt(0, 3);
                } while (fieldGame[x][y] != Cell.empty.Val);

                printPlace(x,y,activeStep);
                IsWin = checkWin(activeStep);
                if (!IsWin) {
                    activePlayer = Player.user;
                }
            }
            if (activeStep == Cell.cross) {
                activeStep = Cell.zero;
            }else  activeStep = Cell.cross;

            System.out.println();
        }

        if (IsWin) {
            System.out.println("Победитель: "+activePlayer.Val);
        }

    }

    public static boolean checkWin(Cell V){

        for (int i = 0; i < fieldGame.length; i++) {
            count = 0;
            for (int j = 0; j < fieldGame.length; j++) {
                if (V != Cell.empty && fieldGame[i][j]== V.Val) {
                    count++;
                }
                if (count==3){
                    return true;
                }
            }
        }

        for (int i = 0; i < fieldGame.length; i++) {
            count = 0;
            for (int j = 0; j < fieldGame.length; j++) {
                if (V != Cell.empty && fieldGame[j][i]== V.Val) {
                    count++;
                }
                if (count==3){
                    return true;
                }
            }
        }

        count = 0;
        for (int i = 0; i < fieldGame.length; i++) {
            if (fieldGame[i][i] == V.Val) {
                count++;
            }
        }
        if (count ==3) return true;

        count=0;
        for (int i = 0; i < fieldGame.length; i++) {
            if (fieldGame[i][fieldGame.length - 1 - i] == V.Val) {
                count++;
            }
        }
        if (count ==3) return true;

        return false;
    }

    public static void printPlace(int x, int y, Cell ActiveCell){
        for (int i = 0; i < fieldGame.length; i++) {
            for (int j = 0; j < fieldGame.length; j++) {
                if ((i == x && j == y) && fieldGame[i][j] == Cell.empty.Val) {
                    fieldGame[i][j] = ActiveCell.Val;
                }
                System.out.print("|" + fieldGame[i][j] + "|");
            }
            System.out.println();
        }
    }
}
