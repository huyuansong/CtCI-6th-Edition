package Q3_06_Animal_Shelter;

import java.util.LinkedList;

public class AnimalQueue {
	LinkedList<Dog> dogs = new LinkedList<Dog>();
	LinkedList<Cat> cats = new LinkedList<Cat>();
	private int order = 0;
	
	public void enqueue(Animal a) {
		a.setOrder(order);
		order++;
		if (a instanceof Dog) {
			dogs.addLast((Dog) a);
		} else if (a instanceof Cat) {
			cats.addLast((Cat)a);
		}
	}
	// 移除并返回最老的动物
	public Animal dequeueAny() {
		if (dogs.size() == 0) {
			return dequeueCats();
		} else if (cats.size() == 0) {
			return dequeueDogs();
		}
		// 在这里偷看哪个时间比较旧 ，返回更老的
		Dog dog = dogs.peek();
		Cat cat = cats.peek(); // Retrieves, but does not remove, the head (first element) of this list.
		if (dog.isOlderThan(cat)) {
			return dogs.poll(); // Retrieves and removes the head (first element) of this list.
		} else {
			return cats.poll();
		}
	}
	
	// 仅返回最老的动物，不移除它。
	public Animal peek() {
		if (dogs.size() == 0) {
			return cats.peek(); 
		} else if (cats.size() == 0) {
			return dogs.peek();
		}
		Dog dog = dogs.peek();
		Cat cat = cats.peek();
		if (dog.isOlderThan(cat)) {
			return dog;
		} else {
			return cat;
		}
	}
	
	public int size() {
		return dogs.size() + cats.size();
	}
	
	public Dog dequeueDogs() {
		return dogs.poll();
	}
	
	public Dog peekDogs() {
		return dogs.peek();
	}
	
	public Cat dequeueCats() {
		return cats.poll();
	}
	
	public Cat peekCats() {
		return cats.peek();
	}
}
