package ru.ac1dtest;

public interface Game {
    void init();
    boolean canMove();
    boolean move(Direction direction);
    void addItem() throws NotEnoughSpace;
    Board getGameBoard();
    boolean hasWin();
}
