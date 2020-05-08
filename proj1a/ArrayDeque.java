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

	private int plusOne(int index){
		return (index+1)%getLength();
	}

	private int minusOne(int index){
		return (index+getLength()-1)%getLength();
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

	private boolean isSpare(){
		return items.length>=16&&size<(items.length/4);
	}

	private void resize(int x) {
		T[] newitems = (T[]) new Object[x];
		int p = plusOne(nextFirst);
		for(int newIndex =0;newIndex<size; newIndex++){
			newitems[newIndex] = items[p];
			p = plusOne(p);
		}
		nextFirst = x - 1;
		nextLast = size;
		items = newitems;
	}

	public void addFirst(T item) {
		if (isFull()) {
			upSize();
		}

		items[nextFirst] = item;
		nextFirst = minusOne(nextFirst);
		size++;
	}

	public void addLast(T item) {
		if(isFull()){
			upSize();
		}
		items[nextLast] = item;
		nextLast = plusOne(nextLast);
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void printDeque() {
		int p = plusOne(nextFirst);
		while (p != nextLast) {
			System.out.print(items[p] + " ");
			p = plusOne(p);
		}
		System.out.println();
	}

	public T removeFirst() {
		if (isEmpty()){
			return null;
		}
		if (isSpare()) {
			downSize();
		}
		T temp = items[plusOne(nextFirst)];
		nextFirst = plusOne(nextFirst);
		items[nextFirst] = null;
		size--;
		return temp;
	}

	public T removeLast() {
		if (isEmpty()) {
			return null;
		}
		if(isSpare()){
			downSize();
		}
		nextLast = minusOne(nextLast);
		T temp = items[nextLast];
		items[nextLast] = null;
		size--;
		return temp;
	}

	public T get(int index) {

		if (index >= size) return null;

		return items[(nextFirst + 1 + index) % getLength()];
	}

}
