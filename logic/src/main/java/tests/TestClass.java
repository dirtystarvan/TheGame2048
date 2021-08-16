package tests;

import ru.ac1dtest.Board;
import ru.ac1dtest.Key;
import ru.ac1dtest.SquareBoard;

import java.util.Arrays;


public class TestClass {
    public static void main(String[] args) {
        Board<Key, Integer> board = new SquareBoard<Integer>(2);

        board.fillBoard(Arrays.asList(1,2,3,null));
//        ru.ac1dtest.Game game2048 = new ru.ac1dtest.Game2048(board);
//        System.out.println(game2048.canMove());

    }
}
