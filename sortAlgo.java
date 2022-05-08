/**
 * Eric Chen
 * CS3310 Project 2
 */
public class sortAlgo {

    /**
     * Merge Sort O(nlogn)
     * @param   
     */
    static int pivotposition;

     
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
    public static int[] QuickSort(int[] arr,int p, int q){
        if(p < q){
            iterQuickSortPart(arr, p,q, pivotposition);
            QuickSort(arr, p,iterQuickSortPart(arr, p,q, pivotposition) - 1 );
            QuickSort(arr,iterQuickSortPart(arr, p,q, pivotposition) + 1,q);
        }
        return arr;
    }  


    /**
     * Iterative Quick Sort Partition
     * @param   
     */
    public static int iterQuickSortPart(int[] arr,int low, int high, int pivot){
        int v = arr[low];
        int j = low;
        int temp = 0;
        for(int i = low + 1;i < high;i++){
            if(arr[i] < v){
                j++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        pivot = j;
        
        temp = arr[low];
        arr[low] = arr[pivot];
        arr[pivot] = temp;
        return pivot;
    }

    /**
     * Recursive Quick Sort Partition
     * @param   
     */
    public static double recurQuickSort(){
        return 0;
    }

    /**
     * Median of Medians
     * @param   
     */
    public static double mmSort(){
        return 0;
    }

    public static void printArr(int[] arr){
        //System.out.println("The sorted array is: ");
        for(int i = 0; i<arr.length; i++){
            System.out.printf("%d  ",arr[i]);
        }
    }
    public static void main(String[] args) {
        int[] testArr = {3, 4, 5,23,123,64,6};
        int[] testArr2 = {13,27,5,26,547,43,23,46};
        //System.out.println("From the main method:");
        //printArr(mergeSort(testArr, 0, 6));
        printArr(QuickSort(testArr, 0, testArr.length));
    }

    
}