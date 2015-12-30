package cn.cttic.sysframe.common.support.structure;

public class Pair<K, T> {
	K first;
	T last;

	public Pair(K first, T last) {
		this.first = first;
		this.last = last;
	}

	public K getFirst() {
		return first;
	}

	public T getLast() {
		return last;
	}
	
    public void setFirst(K first) {
    	this.first = first;
    }

	
    public void setLast(T last) {
    	this.last = last;
    }

}