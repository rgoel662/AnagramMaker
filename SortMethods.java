import java.util.ArrayList;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author 
 *	@since	
 */
public class SortMethods {

	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(String[] arr, int x, int y) {
		String temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(String [] arr) {
		String[] temp = split(arr);
		for (int i = 0; i < arr.length; i ++)
			arr[i] = temp[i];
	}
	
	public String[] split(String[] arr){
		if (arr.length > 2){
			int splitIndex = arr.length / 2;
			String[] half1 = new String[splitIndex];
			String[] half2 = new String[arr.length - splitIndex];
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
	
	public String[] merge(String[] arr1, String[] arr2){
		String[] arr = new String[arr1.length + arr2.length];
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

}
