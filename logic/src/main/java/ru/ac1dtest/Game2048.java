package ru.ac1dtest;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game2048 implements Game {
    public static final int GAME_SIZE = 4;
    public final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);

    GameHelper helper = new GameHelper();

    /** Метод Init должен заполнять игровое поле в соответствии с правилами.
     * При добавлении должен использовать метод addItem.
     * Начальный инит - две плитки в рандомных свободных местах на поле.
     * */

    @Override
    public void init() {
        for (int i = 0; i < GAME_SIZE; i++)
            for (int j = 0; j < GAME_SIZE; j++)
                board.addItem(new Key(i, j), null);

        try {
            for (int i = 0; i < 2; i++)
                addItem();
        } catch (NotEnoughSpace e) {

        }
    }

    private boolean hasEqualNeighbors(List<Integer> values) {
        for (int x = 0, y = 1; y < GAME_SIZE; x++, y++)
            if (values.get(x).equals(values.get(y)))
                return true;

        return false;
    }

    /**
     * Проверка
     * ещё есть свободные клетки
     * есть варианты слияния
     * */

    @Override
    public boolean canMove() {
        if (board.board.containsValue(null))
            return true;

        //получаем строки, если в строке есть смежные равные элементы - true
        for (int i = 0; i < GAME_SIZE; i++) {
            if (hasEqualNeighbors(board.getValues(board.getRow(i))))
                return true;
        }


        //получаем столбцы, если в столбце есть смежные равные элементы - true
        for (int i = 0; i < GAME_SIZE; i++) {
            if (hasEqualNeighbors(board.getValues(board.getColumn(i))))
                return true;
        }

        return false;
    }

    /**
     * Метод move(Direction direction) делает игровой ход и перемещает,
     * пользуясь методом хелпера moveAndMergeEqual, значения на игровом поле.
     * Если не было перемещений, то считается, что не было хода.
     * Если место на игровом поле закончилось и нельзя добавить новый элемент,
     * метод должен вернуть false.
     * Если ход успешно совершен (или не было перемещений), то возвращаем true.*/

    @Override
    public boolean move(Direction direction) {
        boolean result = true;

        if (canMove()) {
            int i;

            switch (direction) {
                case UP:
                    for (int y = 0; y < GAME_SIZE; y++) {
                        List<Key> keys = board.getColumn(y);

                        i = 0;
                        for (Integer cell: GameHelper.moveAndMergeEqual(board.getValues(keys)))
                            board.addItem(keys.get(i++), cell);
                    }
                    break;
                case DOWN:
                    for (int y = 0; y < GAME_SIZE; y++) {
                        List<Key> keys = board.getColumn(y);
                        Collections.reverse(keys);

                        i = 0;
                        for (Integer cell: GameHelper.moveAndMergeEqual(board.getValues(keys)))
                            board.addItem(keys.get(i++), cell);
                    }
                    break;
                case LEFT:
                    for (int y = 0; y < GAME_SIZE; y++) {
                        List<Key> keys = board.getRow(y);

                        i = 0;
                        for (Integer cell: GameHelper.moveAndMergeEqual(board.getValues(keys)))
                            board.addItem(keys.get(i++), cell);
                    }
                    break;
                case RIGHT:
                    for (int y = 0; y < GAME_SIZE; y++) {
                        List<Key> keys = board.getRow(y);
                        Collections.reverse(keys);

                        i = 0;
                        for (Integer cell: GameHelper.moveAndMergeEqual(board.getValues(keys)))
                            board.addItem(keys.get(i++), cell);
                    }
                    break;
            }
        }

//        for (int j = 0; j < GAME_SIZE; j++)
//            System.out.println(board.getValues(board.getRow(j)));
//        System.out.println();

        if (!board.availableSpace().isEmpty())
            try {
                addItem();
            } catch (NotEnoughSpace e) {
                result = false;
            }
        else
            result = false;

//        for (int j = 0; j < GAME_SIZE; j++)
//            System.out.println(board.getValues(board.getRow(j)));
//        System.out.println();

        return result;
    }

    /**
     * Добавляет новую плитку на пустое поле.
     * С вероятностью 90% - со значением 2
     * С вероятностью 10% - со значением 4
     */

    @Override
    public void addItem() throws NotEnoughSpace {
//        int x = (int)(GAME_SIZE * Math.random());
//        int y = (int)(GAME_SIZE * Math.random());
        Random random = new Random();
        List<Key> emptyCells = board.availableSpace();

        if (emptyCells.isEmpty())
            throw new NotEnoughSpace();

        int seed = (int)(101 * Math.random());
        int value = (seed <= 10) ? 4 : 2;

        board.addItem(emptyCells.get(random.nextInt(emptyCells.size())), value);
    }

    /**
     * Метод getGameBoard возвращает игровое поле.
     */

    @Override
    public Board getGameBoard() {
        return board;
    }

    /**
     * Метод hasWin проверяет, выполнено ли условие выигрыша
     * Среди плиток на поле есть плитка номиналом 2048
     * */

    @Override
    public boolean hasWin() {
        return board.board.containsValue(64);
    }
}
