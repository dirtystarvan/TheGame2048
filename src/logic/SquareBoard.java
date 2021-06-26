package logic;

import java.util.*;

public class SquareBoard<V> extends Board<Key, V> {
    public SquareBoard(int size) {
        super(size, size);
    }

    /**
     * раскладывает переданный лист значений в борду по порядку
     * */

    @Override
    public void fillBoard(List<V> list) {
        if (list.size() > weight)
            throw new RuntimeException("Initialization error");

        Iterator<V> item = list.iterator();

        for (int i = 0; i < height; i++)
            for (int j = 0; j < weight; j++)
                if (item.hasNext())
                    board.put(new Key(i, j), item.next());

        for (Map.Entry<Key, V> pair: board.entrySet())
            System.out.println(pair.getKey() + " " + pair.getValue());
    }


    /** Возвращаем все ключи, у которых значение null.*/
    @Override
    public List<Key> availableSpace() {
        List<Key> emptyCells = new ArrayList<>();

        for (Map.Entry<Key, V> pair: board.entrySet())
            if (pair.getValue() == null)
                emptyCells.add(pair.getKey());

        return emptyCells;
    }

    /** Добавляем элемент {@param value} по ключу {@param key}. */

    @Override
    public void addItem(Key key, V value) {
        board.put(key, value);
    }

    /** Ищем уже существующий ключ по координатам {@param i} {@param j}. */
    @Override
    public Key getKey(int i, int j) {
        Key current;

        for (Map.Entry<Key, V> pair: board.entrySet()) {
            current = pair.getKey();

            if (current.getI() == i && current.getJ() == j)
                return current;
        }

        return null;
    }

    /** Получаем значение по {@param key} */

    @Override
    public V getValue(Key key) {
        return board.get(key);
    }

    /** Получаем столбец ключей, по которым потом можно будет выбрать значения. */

    @Override
    public List<Key> getColumn(int j) {
        Key current;
        List<Key> column = new ArrayList<>();

        for (Map.Entry<Key, V> pair: board.entrySet()) {
            current = pair.getKey();

            if (current.getJ() == j)
                column.add(current);
        }

        column.sort(Comparator.comparingInt(Key::getI));

        return column;
    }

    /** Получаем строку ключей, по которым потом можно будет выбрать значения. */

    @Override
    public List<Key> getRow(int i) {
        Key current;
        List<Key> row = new ArrayList<>();

        for (Map.Entry<Key, V> pair: board.entrySet()) {
            current = pair.getKey();

            if (current.getI() == i)
                row.add(current);
        }

        row.sort(Comparator.comparingInt(Key::getJ));

        return row;
    }

    /** Проверяем, есть ли такое значение на поле. */

    @Override
    public boolean hasValue(V value) {
        return board.containsValue(value);
    }

    /** Получаем строку значений по строке ключей. */

    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> result = new ArrayList<>();

        for (Key item: keys)
            result.add(board.get(item));

        return result;
    }
}
