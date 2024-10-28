public class Main {
	public static void main(String[] args) {
		Linky ll = new Linky();
		for (int i = 0; i < 10; i++) {
			ll.insert(i);
		}

		ll.printLinky(ll.head);
	}
}
