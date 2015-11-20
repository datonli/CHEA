import java.util.List;
import java.util.ArrayList;

class listTest {
	public static void main(String[] args) {
		List<int[]> l = new ArrayList<int[]>(2);
		int[] a = {1,2};
		l.add(a);
		int[] b = {4,5};
		l.add(b);
		System.out.println(l.get(0)[1]);
	}
}
