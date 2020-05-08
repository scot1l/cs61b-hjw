public class ArrayDeque<T> {

	private T[] items;
	private int size;
	private int nextFirst;
	private int nextLast;

	//	private int loadFactor;
	public ArrayDeque() {
		items = (T[]) new Object[8];
		size = 0;
		nextFirst = 0;
		nextLast = 1;
	}

	public ArrayDeque(ArrayDeque other) {
		items = (T[]) new Object[other.getLength()];
		size = other.size;
		nextFirst = other.nextFirst;
		nextLast = other.nextLast;
		int p = (other.nextFirst + 1) % other.getLength();
		while (p != other.nextLast) {
			items[p] = (T) other.items[p];
			p = (p + 1) % getLength();
		}
	}

	private int getLength() {
		return items.length;
	}

	private int getNextFirst() {
		return (nextFirst - 1 + getLength()) % getLength();
	}

	private int getNextLast() {
		return (nextLast + 1) % getLength();
	}

	private boolean isFull() {
		return size == items.length;
	}

	private void upSize() {
		resize(items.length * 2);
	}

	private void downSize() {
		resize(items.length / 2);
	}

	private void resize(int x) {
		T[] newitems = (T[]) new Object[x];
		int p = (nextFirst + 1) % getLength();
		int i = 0;
		while (p != nextLast) {
			newitems[i] = items[p];
			i++;
			p = (p + 1) % getLength();
		}
		nextFirst = newitems.length - 1;
		nextLast = size;
		items = newitems;
	}

	public void addFirst(T item) {
		if (isFull()) {
			upSize();
		}

		items[nextFirst] = item;
		nextFirst = getNextFirst();
		size++;
	}

	public void addLast(T item) {
		if(isFull()){
			upSize();
		}
		items[nextLast] = item;
		nextLast = getNextLast();
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void printDeque() {
		int p = (nextFirst + 1) % getLength();
		while (p != nextLast) {
			System.out.print(items[p] + " ");
			p = (p + 1) % getLength();
		}
		System.out.println();
	}

	public T removeFirst() {
		if (isEmpty()) {
			return null;
		}
		size--;
		if (size * 4 < items.length && items.length>=16) {
			downSize();
		}
		nextFirst = (nextFirst + 1) % getLength();
		return items[nextFirst];
	}

	public T removeLast() {
		if (isEmpty()) {
			return null;
		}
		size--;
		if (size * 4 < items.length && items.length>=16) {
			downSize();
		}
		nextLast = (nextLast + getLength() - 1) % getLength();
		return items[nextLast];
	}

	public T get(int index) {
		if (isEmpty()) return null;
		if (index >= size) return null;
		int p = (nextFirst + 1 + index) % getLength();
		return items[p];
	}

}
