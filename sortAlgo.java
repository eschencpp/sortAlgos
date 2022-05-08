/**
 * Eric Chen
 * CS3310 Project 2
 */
public class sortAlgo {

    /**
     * Merge Sort O(nlogn)
     * @param   
     */
    static int pivotposition = 0;

     
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
     * Recursive Quick Sort
     * @param   
     */
    public static int[] RecurQuickSort(int[] arr,int p, int q){
        if(p < q){
            Partition(arr, p,q, pivotposition);
            RecurQuickSort(arr, p,Partition(arr, p,q, pivotposition) - 1 );
            RecurQuickSort(arr,Partition(arr, p,q, pivotposition) + 1,q);
        }
        return arr;
    }  


    /**
     * Quick Sort Partition
     * @param   
     */
    public static int Partition(int[] arr,int low, int high, int pivot){
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
     * Iterative Quick Sort
     * @param  n  number of elements
     * @param  k key
     * @return the smallest number
     */
    public static int IterQuickSort(int[] arr, int n, int k){
        int m = 0;
        int j = n;
        int pivot = arr.length - 1;
        for(int i = 0; i <= n ; i++ ){
            Partition(arr, m, j, pivot);
            if(k == Partition(arr, m, j, pivot)){
                return arr[k];
            }

            if(k < Partition(arr, m, j, pivot)){
                j = Partition(arr, m, j, pivot) - 1;
            } else{
                m = Partition(arr, m, j, pivot) + 1;
                //k = k - pivot;
            }
            
        }

        return arr[k];
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
        int[] testArr = {16, 4, 5,23};
        int[] testArr2 = {13,27,5,26,547,43,2,46,23};
        //System.out.println("From the main method:");
        //printArr(mergeSort(testArr, 0, 6));
        //printArr(RecurQuickSort(testArr, 0, testArr.length));
        for(int i = 0; i < testArr2.length; i++)
        System.out.println("The lowest number in iterQuick is: "+IterQuickSort(testArr2, testArr2.length, i));
    }

    
}