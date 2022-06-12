import java.util.Scanner;

public class SortingAlgorithms {

	void BubbleSort(int[] arr) {
		// FUNCTION:
		// Sorts the array using the BubbleSort Algorithm
		// Arguments: int array arr
		
		int length = arr.length;
		int temp = 0;
		for(int i=0;i<length;i++) {
			for(int j=0;j<length-1;j++) {
				if(arr[j]>arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
			System.out.println("\nIteration "+(i+1));
			for(int k=0;k<length;k++) {
				System.out.print(arr[k]+" ");
			}	
		}
		System.out.println("");
	}
	
	void MergeSort(int[] arr, int start, int end) {
		// FUNCTION:
		// Sorts the array using the MergeSort Algorithm
		// Arguments: int array arr, int start & int end (for the splitting ranges)
		
		if(start<end) {
			int mid = (start + end)/2;
			MergeSort(arr,start,mid);
			MergeSort(arr,mid+1,end);
			Merge(arr,start,mid,end);
		}
	}
	
	void Merge(int[] arr, int start, int mid, int end) {
		// FUNCTION:
		// Merges the array for the MergeSort Algorithm
		// Arguments: int array arr, int start, int mid & int end (for the merging ranges)
		
		int range1 = mid - start +1;	//calculate the size of the two arrays
		int range2 = end - mid;
		
		int A[] = new int[range1];		// create a copy of the split arrays
		int B[] = new int[range2];
		
		for(int i=0;i<range1;i++) {		// copying the arrays
			A[i] = arr[start + i];
		}
		for(int i=0;i<range2;i++) {		// copying the arrays
			B[i] = arr[mid + 1 + i];
		}
		
	    int i, j, k;	// indexes of arrays
	    i = 0;
	    j = 0;
	    k = start;

	    while (i < range1 && j < range2) {	// loop until we reach the end of A or B, find the larger and put them in the correct position of arr
	      if (A[i] <= B[j]) {
	        arr[k] = A[i];
	        i++;
	      } else {
	        arr[k] = B[j];
	        j++;
	      }
	      k++;
	    }

	    while (i < range1) {	//when we reach the end of array A we put the remaining values to arr 
	      arr[k] = A[i];
	      i++;
	      k++;
	    }

	    while (j < range2) {	//when we reach the end of array B we put the remaining values to arr 
	      arr[k] = B[j];
	      j++;
	      k++;
	    }
	}
	
	void QuickSort(int[] arr, int start, int end) {
		// FUNCTION:
		// Sorts the array using the QuickSort Algorithm
		// Arguments: int array arr, int start & int end for the division of the arrays
		
		if (start < end) {
			int pivot = division(arr, start, end);	// pivot element of the array that has everything to its left smaller that it and greater to its right 
		    QuickSort(arr, start, pivot - 1);	// QuickSort on the left of the pivot element 
		    QuickSort(arr, pivot + 1, end);		// QuickSort on the left of the pivot element 
		    
			for(int i=0;i<arr.length;i++) {
				System.out.print(arr[i]+" ");
			}
			System.out.println("");
		}
	}

	int division(int[] arr, int start, int end) {
		// FUNCTION:
		// Return the index of the pivot element for QuickSort
		// Arguments: int array arr, int start & int end for the division of the arrays
		
	    int pivot = arr[end];	// choose the far right element as pivot
	    int i = start - 1;		// pointer for greater element

	    for (int j = start; j < end; j++) {	// loop through the array and compare pivot with each element
	      if (arr[j] <= pivot) {			// element smaller than pivot
	        i++;
	        int temp = arr[i];	// swapping greater element at i with a smaller element at j
	        arr[i] = arr[j];
	        arr[j] = temp;
	      }
	    }
	    int temp = arr[i + 1];	// swap the pivot element with the greater element at i+1
	    arr[i + 1] = arr[end];
	    arr[end] = temp;
	    
	    return (i + 1);			// return the position of the pivot
	}
	
	void InsertionSort(int[] arr) {
		// FUNCTION:
		// Sorts the array using the InsertionSort Algorithm
		// Arguments: int array arr
		
		int size = arr.length;

	    for (int step = 1; step < size; step++) {
	      int key = arr[step];	// start with the second element of the array(we assume that the first is already sorted)
	      int j = step - 1;

	      while (j >= 0 && key < arr[j]) {	//compare key with the elements on its left until we find a smaller one
	        arr[j + 1] = arr[j];
	        --j;
	      }
	      arr[j + 1] = key; 	// place key at after the smaller element than it
	      
	      for(int i=0;i<arr.length;i++) {
	    	  System.out.print(arr[i]+" ");
	      }
	      System.out.println("");
	    }
		
	}
	
	public static void main(String[] args) {
		SortingAlgorithms sa = new SortingAlgorithms();
		int run_flag = 1;
		while(run_flag == 1) {
			System.out.println("-----------User Menu--------------\n");
			System.out.println("\t1. BubbleSort\n");
			System.out.println("\t2. MergeSort\n");
			System.out.println("\t3. QuickSort\n");
			System.out.println("\t4. InsertionSort\n");
			System.out.println("\t5. End Application\n");
			System.out.println("----------------------------------\n");
			System.out.println("Select a Function: \n");
			Scanner scan = new Scanner(System.in);
			int option = scan.nextInt();
			System.out.println("Enter 10 numbers: ");
			int[] arr = new int[10];
			for(int i=0;i<10;i++) {
				arr[i] = scan.nextInt();
			}
			if(option == 1) {			// BubbleSort
				System.out.println("The Initial Array is: ");
				for(int i=0;i<arr.length;i++) {
					System.out.print(arr[i]+" ");
				}
				System.out.println("");
				sa.BubbleSort(arr);
			}
			if(option == 2) {			// MergeSort
				System.out.println("The Initial Array is: ");
				for(int i=0;i<arr.length;i++) {
					System.out.print(arr[i]+" ");
				}
				System.out.println("");
				sa.MergeSort(arr, 0, arr.length - 1);
				System.out.println("The Sorted Array is: ");
				for(int i=0;i<arr.length;i++) {
					System.out.print(arr[i]+" ");
				}
				System.out.println("");
			}	
			if(option == 3) {			// QuickSort
				System.out.println("The Initial Array is: ");
				for(int i=0;i<arr.length;i++) {
					System.out.print(arr[i]+" ");
				}
				System.out.println("\nIterations:");
				sa.QuickSort(arr,0,arr.length - 1);
				System.out.println("The Sorted Array is: ");
				for(int i=0;i<arr.length;i++) {
					System.out.print(arr[i]+" ");
				}
				System.out.println("");
			}	
			if(option == 4) {			// InsertionSort
				System.out.println("The Initial Array is: ");
				for(int i=0;i<arr.length;i++) {
					System.out.print(arr[i]+" ");
				}
				System.out.println("\nIterations:");
				sa.InsertionSort(arr);
				System.out.println("The Sorted Array is: ");
				for(int i=0;i<arr.length;i++) {
					System.out.print(arr[i]+" ");
				}
				System.out.println("");
			}	
			if(option == 5) {		// end application
				run_flag = 0;
				scan.close();
			}
		}
	}
}
