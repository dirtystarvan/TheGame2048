package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GameHelper {
    public static List<Integer> moveAndMergeEqual(List<Integer> list) {
        if (list.isEmpty())
            return list;

        Integer first = null, second = null;
        List<Integer> result = new ArrayList<>(list.size()); // список пуст, null надо докидывать отдельно

        int i, j, resultPointer = 0;
        boolean nextFound = false;

        for (i = 0; i < list.size(); i++) {
            first = list.get(i);

            if (first == null)
                continue;

            for (j = i+1; j < list.size(); j++) {
                second = list.get(j);

                if (second != null) {
                    nextFound = true;
                    break;
                }
            }

            if (nextFound)
                if (first.equals(second)) {
                    result.add(first + second);
                    j++;
                }
                else {
                    result.add(first);
                    //result.add(second);
                }
            else {
                result.add(first);
            }

            i = j - 1;
            nextFound = false;
        }

        System.out.print("Before nulls: ");
        System.out.println(result);

        int additional = list.size() - result.size();

        for (int k = 0; k < additional; k++)
            result.add(null);

        System.out.print("After nulls: ");
        System.out.println(result);

        return result;
    }
}

