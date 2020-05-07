public class ArrayDeque<T> {

	private T[] items;
	private int size;
	private int nextFirst;
	private int nextLast;

//	private int loadFactor;
	public ArrayDeque(){
		items = (T[]) new Object[8];
		size = 0;
		nextFirst = 0;
		nextLast = 0;
	}

	public ArrayDeque(ArrayDeque<T> other){
		items = (T[]) new Object[other.getLength()];
		size = other.size;
		nextFirst = other.nextFirst;
		nextLast = other.nextLast;
		int p = (other.nextFirst+1)%other.getLength();
		while (p!=other.nextLast){
			items[p] = other.items[p];
			p = (p+1)%getLength();
		}
	}

	public int getLength(){
		return items.length;
	}

	private int getNextFirst(){
		return (nextFirst-1+getLength())%getLength();
	}

	private int getNextLast(){
		return (nextLast+1)%getLength();
	}

	private void resize(int x){
		T[] newitems = (T[]) new Object[x];
		int p = (nextFirst+1)%getLength();
		int i = 0;
		while (p!=nextLast){
			newitems[i] = items[p];
			i++;
			p = (p+1)%getLength();
		}
		nextFirst = newitems.length-1;
		nextLast = size;
		items = newitems;
	}

	public void addFirst(T item){
		if (size == items.length-2) {
			resize(items.length*2);
		}
		if (isEmpty()) {
			items[nextFirst] = item;
			nextFirst = getNextFirst();
			nextLast = getNextLast();
		}else {
			items[nextFirst] = item;
			nextFirst = getNextFirst();
		}
		size++;
	}

	public void addLast(T item){
		if (size == items.length-2) {
			resize(items.length*2);
		}
		if (isEmpty()) {
			items[nextLast] = item;
			nextFirst = getNextFirst();
			nextLast = getNextLast();
		}else {
			items[nextLast] = item;
			nextLast = getNextLast();
		}
		size++;
	}

	public boolean isEmpty(){
		return size==0;
	}

	public int size(){
		return size;
	}

	public void printDeque(){
		int p = (nextFirst+1)%getLength();
		while (p!=nextLast){
			System.out.print(items[p]+ " ");
			p = (p+1)%getLength();
		}
		System.out.println();
	}

	public T removeFirst(){
		if(isEmpty()) {
			return null;
		}
		size--;
		if(size*4<items.length){
			resize(getLength()/2);
		}
		nextFirst = (nextFirst+1)%getLength();
		return items[nextFirst];
	}

	public T removeLast(){
		if(isEmpty()) {
			return null;
		}
		size--;
		if(size*4<items.length){
			resize(getLength()/2);
		}
		nextLast = (nextLast+getLength()-1)%getLength();
		return items[nextLast];
	}

	public T get(int index){
		if(isEmpty()) return null;
		if (index>size-1) return null;
		int p = (nextFirst+1 +index)%getLength();
		return items[p];
	}

//	public static void main(String[] args) {
//		ArrayDeque<String> huangD = new ArrayDeque<>();
//		huangD.addFirst("huang");
//		huangD.addLast("jian");
//		huangD.addFirst("wei");
//		huangD.addFirst("wei1");
//		huangD.addFirst("wei2");
//		huangD.addFirst("wei3");
//		huangD.addFirst("wei4");
//		huangD.addFirst("wei5");
//		huangD.addFirst("wei6");
//		huangD.addFirst("wei7");
//		huangD.printDeque();
//		ArrayDeque<String> jianD = new ArrayDeque<>(huangD);
//		huangD.removeLast();
//		huangD.printDeque();
//		jianD.printDeque();
//		huangD.removeFirst();
//		huangD.removeLast();
//		huangD.removeFirst();
//		huangD.removeLast();
//		huangD.removeFirst();
//		huangD.removeLast();
//		huangD.removeFirst();
//		huangD.printDeque();
//	}

}
