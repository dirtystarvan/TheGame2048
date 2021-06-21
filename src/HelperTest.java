import java.util.Arrays;
import java.util.List;

public class HelperTest {
	private final static GameHelper helper = new GameHelper();

	public static void main(String[] args) {

		//helper.moveAndMergeEqual(Arrays.asList(1, 2, null, 3));

		assertEquals(helper.moveAndMergeEqual(Arrays.asList(1, 2, null, 3)), Arrays.asList(1, 2, 3, null));
		assertEquals(helper.moveAndMergeEqual(Arrays.asList(2, 2, null, 3)), Arrays.asList(4, 3, null, null));
		assertEquals(helper.moveAndMergeEqual(Arrays.asList(2, 2, 4, 4)), Arrays.asList(4, 8, null, null));
		assertEquals(helper.moveAndMergeEqual(Arrays.asList(2, 2, 2, 3)), Arrays.asList(4, 2, 3, null));
		assertEquals(helper.moveAndMergeEqual(Arrays.asList(2, null, null, 2)), Arrays.asList(4, null, null, null));
		assertEquals(helper.moveAndMergeEqual(Arrays.asList(null, null, null, null)), Arrays.asList(null, null, null, null));
		assertEquals(helper.moveAndMergeEqual(Arrays.asList(null, null, null, 2)), Arrays.asList(2, null, null, null));
		assertEquals(helper.moveAndMergeEqual(Arrays.asList(null, null, 2, 2)), Arrays.asList(4, null, null, null));

	}

	public static void assertEquals(List list1, List list2) {
		if (!list1.equals(list2)) throw new RuntimeException("List1: " + list1 + " not equals List2: " + list2);
	}
}