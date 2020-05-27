import java.util.*;

public class MyHashMap<K,V> implements Map61B<K,V>{

	private static final int INITIAL_SIZE = 16;
	private static final double LOAD_FACTOR = 0.75;
	private int capacity;
	private double loadFactor;
	private BucketsEntity<K,V>[] buckets;
	private int size;
	private int threshold;


	class BucketsEntity<K,V>{
		K key;
		V value;
		BucketsEntity<K,V> next;

		BucketsEntity(K key,V value){
			this.key = key;
			this.value = value;
			this.next = null;
		}

		K getKey(){return this.key;}

	}

	public MyHashMap() {
		buckets = new BucketsEntity[INITIAL_SIZE];
		capacity = INITIAL_SIZE;
		loadFactor = LOAD_FACTOR;
		threshold = (int) (loadFactor*capacity);
		size = 0;
	}
	public MyHashMap(int initialSize){
		capacity = initialSize;
		buckets = new BucketsEntity[capacity];
		loadFactor = LOAD_FACTOR;
		threshold = (int) (loadFactor*capacity);
		size = 0;
	}

	public MyHashMap(int initialSize, double loadFactor) {
		capacity = initialSize;
		this.loadFactor = loadFactor;
		buckets = new BucketsEntity[capacity];
		threshold = (int) (loadFactor*capacity);
		size = 0;
	}
	@Override
	public void clear() {
		buckets = new BucketsEntity[capacity];
		size = 0;
	}

	private int indexOf(K key){
		return (key.hashCode() & 0x7fffffff)%capacity;
	}

	@Override
	public boolean containsKey(K key) {
		if (key == null) return false;
		int indexOfBuckets = indexOf(key);
		BucketsEntity<K,V> temp = buckets[indexOfBuckets];
		if (temp == null) {

		} else {
			while (temp!= null) {

				if (key.equals(temp.key)) {
					return true;
				}
				temp = temp.next;
			}
		}
		return false;
	}

	@Override
	public V get(K key) {
		if (key == null) return null;
		if (buckets[indexOf(key)] ==null) {

		} else {
			BucketsEntity<K,V> temp = buckets[indexOf(key)];
			while (temp != null) {
				if (key.equals(temp.key)) {
					return temp.value;
				}
				temp = temp.next;
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	private void resize() {
		capacity = capacity*2;
		BucketsEntity<K,V>[] newBuckets = new BucketsEntity[capacity];
		for (BucketsEntity<K,V> bucketsEntity : buckets) {
			if (bucketsEntity!=null) {
				BucketsEntity<K,V> temp = bucketsEntity;
				while (temp!=null) {
					putToNew(temp.key,temp.value,newBuckets);
					temp = temp.next;
				}
			}
		}
		buckets = newBuckets;
		threshold = (int) (loadFactor*capacity);
	}


	@Override
	public void put(K key, V value) {
		if (key == null) return;
		int indexOfPut = indexOf(key);
		BucketsEntity<K,V> temp = buckets[indexOfPut];
		if (temp == null) {
			buckets[indexOfPut] = new BucketsEntity<>(key,value);
		} else {
			if (key.equals(temp.key)) {
				temp.value = value;
				return;
			}
			while (temp.next != null){
				temp = temp.next;
				if (key.equals(temp.key)) {
					temp.value = value;
					return;
				}
			}
			temp.next = new BucketsEntity<>(key, value);
		}
		size++;
		if (size>threshold) {
			resize();
		}
	}

	private void putToNew(K key ,V value, BucketsEntity<K,V>[] buckets) {
		int indexOfKey = indexOf(key);
		BucketsEntity<K,V> temp = buckets[indexOfKey];
		if (temp == null) {
			buckets[indexOfKey] = new BucketsEntity<>(key,value);
		} else {
			while (temp.next!= null) {
				temp = temp.next;
			}
			temp.next = new BucketsEntity<>(key, value);
		}
	}



	@Override
	public Set<K> keySet() {
		HashSet<K> hashSet = new HashSet<>();
		for (int i=0 ;i< buckets.length;i++) {
			if (buckets[i]!=null) {
				BucketsEntity<K,V> temp = buckets[i];
				while (temp!=null){
					hashSet.add(temp.key);
					temp = temp.next;
				}
			}
		}
		return hashSet;
	}

	@Override
	public V remove(K key) {
		throw new UnsupportedOperationException();

	}

	@Override
	public V remove(K key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<K> iterator() {

		return keySet().iterator();
	}

	public static void main(String[] args) {
		System.out.println("waterYouDoingHere".hashCode());
	}
}
