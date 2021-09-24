package class03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Code03_Comparator {

	public static class Student {
		public String name;
		public int id;
		public int age;

		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}
	}

	public static class IdAscendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.id - o2.id;
		}

	}

	public static class IdDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.id - o1.id;
		}

	}

	public static class AgeAscendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.age - o2.age;
		}

	}

	public static class AgeDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.age - o1.age;
		}

	}

	public static void printStudents(Student[] students) {
		for (Student student : students) {
			System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
		}
	}

	public static void printArray(Integer[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static class MyComp implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}

	}

	public static void main(String[] args) {
		Student student1 = new Student("A", 2, 23);
		Student student2 = new Student("B", 3, 21);
		Student student3 = new Student("C", 1, 22);

		Student[] students = new Student[] { student1, student2, student3 };

		Arrays.sort(students, new IdAscendingComparator());
		printStudents(students);
		System.out.println("===========================");

		Arrays.sort(students, new IdDescendingComparator());
		printStudents(students);
		System.out.println("===========================");

		Arrays.sort(students, new AgeAscendingComparator());
		printStudents(students);
		System.out.println("===========================");

		Arrays.sort(students, new AgeDescendingComparator());
		printStudents(students);
		System.out.println("===========================");
		System.out.println("===========================");
		System.out.println("===========================");
		System.out.println("===========================");

		PriorityQueue<Student> maxHeapBasedAge = new PriorityQueue<>(new AgeDescendingComparator());
		maxHeapBasedAge.add(student1);
		maxHeapBasedAge.add(student2);
		maxHeapBasedAge.add(student3);
		while (!maxHeapBasedAge.isEmpty()) {
			Student student = maxHeapBasedAge.poll();
			System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
		}
		System.out.println("===========================");

		PriorityQueue<Student> minHeapBasedId = new PriorityQueue<>(new IdAscendingComparator());
		minHeapBasedId.add(student1);
		minHeapBasedId.add(student2);
		minHeapBasedId.add(student3);
		while (!minHeapBasedId.isEmpty()) {
			Student student = minHeapBasedId.poll();
			System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
		}
		System.out.println("===========================");
		System.out.println("===========================");
		System.out.println("===========================");

		TreeSet<Student> treeAgeDescending = new TreeSet<>(new AgeDescendingComparator());
		treeAgeDescending.add(student1);
		treeAgeDescending.add(student2);
		treeAgeDescending.add(student3);

		Student studentFirst = treeAgeDescending.first();
		System.out.println("Name : " + studentFirst.name + ", Id : " + studentFirst.id + ", Age : " + studentFirst.age);

		Student studentLast = treeAgeDescending.last();
		System.out.println("Name : " + studentLast.name + ", Id : " + studentLast.id + ", Age : " + studentLast.age);
		System.out.println("===========================");

	}

}
