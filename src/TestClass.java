import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(2);

        board.fillBoard(Arrays.asList(1,2,3,null));
//        Game game2048 = new Game2048(board);
//        System.out.println(game2048.canMove());

    }
}
