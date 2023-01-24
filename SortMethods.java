import java.util.ArrayList;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author 
 *	@since	
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer [] arr) {
		for (int outer = arr.length -1; outer > 0; outer --)
			for (int inner = 0; inner < outer; inner++)
				if(arr[inner].compareTo(arr[inner+1]) > 0)
					swap(arr, inner, inner+1);
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) {
		Integer temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer [] arr) {
		for (int i = arr.length -1; i > 0; i --){
			int max = 0;
			int j;
			for (j = 0; j <= i; j++){
				if (arr[j].compareTo(arr[max]) > 0){
					max = j;
				}
			}
			j--;
			swap(arr, max, j);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) {
		for (int i = 1; i < arr.length; i ++){
			for (int j = i; j > 0; j--){
				if (arr[j].compareTo(arr[j-1]) < 0)
					swap(arr, j, j-1);
			}
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(Integer [] arr) {
		Integer[] temp = split(arr);
		for (int i = 0; i < arr.length; i ++)
			arr[i] = temp[i];
	}
	
	public Integer[] split(Integer[] arr){
		if (arr.length > 2){
			int splitIndex = arr.length / 2;
			Integer[] half1 = new Integer[splitIndex];
			Integer[] half2 = new Integer[arr.length - splitIndex];
			for (int i = 0; i < splitIndex; i ++){
				half1[i] = arr[i];
			}
			for (int i = splitIndex; i < arr.length; i++){
				half2[i - splitIndex] = arr[i];
			}
			return merge(split(half1), split(half2));
		} else if (arr.length == 2){
			if (arr[0].compareTo(arr[1]) > 0){
				swap(arr, 0, 1);
				return arr;
			}else 
				return arr;
		}
		return arr;
	}
	
	public Integer[] merge(Integer[] arr1, Integer[] arr2){
		Integer[] arr = new Integer[arr1.length + arr2.length];
		int pointer1 = 0;
		int pointer2 = 0;
		for (int i = 0; i < arr.length; i ++){
			if (pointer1 >= arr1.length || pointer2 >= arr2.length){
				i = arr.length;
			}
			else if (arr1[pointer1].compareTo(arr2[pointer2]) < 0){
				arr[i] = arr1[pointer1];
				pointer1++;
			} else if (arr1[pointer1].compareTo(arr2[pointer2]) > 0){
				arr[i] = arr2[pointer2];
				pointer2++;
			} else {
				arr[i] = arr1[pointer1];
				pointer1++;
			}
		}
		if (pointer1 < arr1.length){
			while (pointer1 != arr1.length){
				arr[arr2.length + pointer1] = arr1[pointer1];
				pointer1 ++;
			}
		} else if (pointer2 < arr2.length){
			while (pointer2 != arr2.length){
				arr[arr1.length + pointer2] = arr2[pointer2];
				pointer2 ++;
			}
		}
		return arr;
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	}
}
