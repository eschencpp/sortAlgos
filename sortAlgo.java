/**
 * Eric Chen
 * CS3310 Project 2
 */
public class sortAlgo {

    /**
     * Merge Sort O(nlogn)
     * @param   
     */
    public static int[] mergeSort(int[] arr,int low, int high){
        int mid = 0;
        if(low < high){
            mid = ((low + high) / 2);
            mergeSort(arr,low, mid);
            mergeSort(arr,mid+1, high);
            merge(arr, low, mid, high);
        }
        return arr;
    }

    public static void merge(int[]arr,int low, int mid, int high){
        int i = low;
        int j = mid + 1;
        int k = low;

        int[] sol = new int[arr.length];
        
        while(i <= mid && j <= high){
            if(arr[i] < arr[j]){
                sol[k] = arr[i];
                i++;
            } else{
                sol[k] = arr[j];
                j++;
            }
            k++;
        }
        
        while(j <= high){
            sol[k] = arr[j];
            j++;
            k++;
        }

        while(i <= mid){
            sol[k] = arr[i];
            i++;
            k++;
        }
        
        for(int p = low; p <= high; p++){
            arr[p] = sol[p];
        }
    }

    /**
     * Iterative Quick Sort
     * @param   
     */
    public static double iterQuickSort(){

    }

    /**
     * Recursive Quick Sort
     * @param   
     */
    public static double recurQuickSort(){

    }

    /**
     * Median of Medians
     * @param   
     */
    public static double mmSort(){

    }

    public static void printArr(int[] arr){
        //System.out.println("The sorted array is: ");
        for(int i = 0; i<arr.length; i++){
            System.out.printf("%d  ",arr[i]);
        }
    }
    public static void main(String[] args) {
        int[] testArr = {3, 4, 5,23,123,64,6};
        //System.out.println("From the main method:");
        printArr(mergeSort(testArr, 0, 6));
    }

    
}