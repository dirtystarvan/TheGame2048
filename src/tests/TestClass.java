package tests;

import logic.Board;
import logic.Key;
import logic.SquareBoard;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;


public class TestClass {
    public static void main(String[] args) {
        Board<Key, Integer> board = new SquareBoard<Integer>(2);

        board.fillBoard(Arrays.asList(1,2,3,null));
//        logic.Game game2048 = new logic.Game2048(board);
//        System.out.println(game2048.canMove());

    }
}
