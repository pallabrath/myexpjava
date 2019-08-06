package com.core;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class CircularArray<Integer> extends ArrayList<Integer> {

	private int index = -1;
	private int capacity;

	public CircularArray(int capacity) {		
		this.capacity = capacity;
	}

	public void set(Integer val) {
		if (size() < capacity)
		{
			add(val);
		}
		else
		{
			index = (index + 1) % capacity;
			super.set(index, val);
		}
	}

	public int sum() {
		int sum = 0;
		for (Integer i : this) {
			sum = sum + (int) i;
		}
		return sum;
	}
}
