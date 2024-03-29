/**
 * Eric Chen
 * CS3310 Project 2
 */
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class sortAlgo {

    /**
     * Merge Sort O(nlogn) (kth 1)
     * @param   arr   input array
     * @param   low   lowest index
     * @param   high  highest index
     * @param   k     kth value to search for
     */
     
    public static int mergeSort(int[] arr,int low, int high, int k){
        int mid = 0;
        if(low < high){
            mid = ((low + high) / 2);
            mergeSort(arr,low, mid, k);
            mergeSort(arr,mid+1, high, k);
            merge(arr, low, mid, high);
        }
        return arr[k];
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
     * @param  arr input array
     * @param  n  number of elements
     * @param  k kth element
     * @return the kth smallest number
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
     * Recursive Quick Sort
     * @param arr  input array
     * @param p left index
     * @param q right index
     * @param pivotposition pivot point  
     */
    public static int RecurQuickSort(int[] arr,int p, int q, int pivotposition){
        if(p < q){
            Partition(arr, p,q, pivotposition);
            RecurQuickSort(arr, p,Partition(arr, p,q, pivotposition) - 1, pivotposition );
            RecurQuickSort(arr,Partition(arr, p,q, pivotposition) + 1,q,pivotposition);
        }
        return arr[pivotposition];
    }  


    /**
     * Quick Sort Partition
     * @param arr  input array
     * @param low left index
     * @param high right index
     * @param pivot pivot point
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


    public static int getMedian(int arr[], int i, int n){
        Arrays.sort(arr, i, n);
        return arr[i+(n-i)/2];  
    }

    public static int mmSort(int arr[], int low, int high, int k){
        int n = high - low + 1;
        int r = 5;

        if (k > 0 && k <= n){
            int i;

            int[] median = new int[(n + 4)/r];
            //For subarrays 6 elements
            for(i = 0; i < n/5; i++){
                median[i] = getMedian(arr, low + (i*r), low + (i*r) + 5);
            }

            //For subarrays with less than 5 elements
            if(i * 5 < n){
                median[i] = getMedian(arr, low + (i*r), low + (i*5) + (n%5));
                i++;
            }

            int v = mmSort(median, 0, i - 1, i /2);

            int pos = Partition(arr, low, high, v);

            if(pos - low == k - 1){
                return arr[pos];
            }
            if(pos - low >(k - 1)){
                return mmSort(arr, low, pos - 1, k);
            } else{
                return mmSort(arr, pos + 1, high, k - pos + low - 1);
            }
        }

        return arr[k];
    }

    //Method to randomly generate test matrices. Range of numbers from -2500 to 2500
    public static int[] fillArr(int n, int[] arr){
        Random rand = new Random();
        int randInt = 0;
        for(int i = 0; i < n; i++){
                randInt = -2500 + rand.nextInt(5000); // Random Int from -2500 to 2500
                arr[i] = randInt;
        }
        return arr;
    }

    public static void printArr(int[] arr){
        for(int i = 0; i<arr.length; i++){
            System.out.printf("%d  ",arr[i]);
        }
    }
    public static void main(String[] args) {

        //Taking user input for array size and kth value
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please enter the number of elements in the array. (10,50,100,500,1000): ");
        int arrSize = sc.nextInt();        

        int iter = 1000; //Set number iterations
        int[] arr = new int[arrSize];
        arr = fillArr(arrSize, arr); //Randomize input array
               
        long mergeStart;
        long mergeEnd;
        long iterStart;
        long iterEnd;
        long recurStart;
        long recurEnd;
        long mmStart;
        long mmEnd;

        long totalTimeMerge = 0;
        long totalTimeIter = 0;
        long totalTimeRecur =0;
        long totalTimeMM = 0;
        
        String continueSearch = "Y";
        //While loop to allow for multiple k values to be tested with the same array.
        while(continueSearch.equals("Y") || continueSearch.equals("y")){
            System.out.println("The numbers in the array are: ");
            printArr(arr);
            System.out.printf("\nEnter the kth element(1 being the least): ");
            int k = sc.nextInt();
            if(k >= 1 && arrSize > 0){
                for(int i = 0; i < iter; i++){
                    mergeStart = System.nanoTime();
                    mergeSort(arr, 0, arrSize - 1, k - 1);
                    mergeEnd = System.nanoTime();

                    iterStart = System.nanoTime();
                    IterQuickSort(arr, arrSize, k - 1);
                    iterEnd = System.nanoTime();

                    recurStart = System.nanoTime();
                    RecurQuickSort(arr, 0, arrSize - 1, k - 1);
                    recurEnd = System.nanoTime();

                    mmStart = System.nanoTime();
                    mmSort(arr,0, arrSize - 1, k);
                    mmEnd = System.nanoTime();

                    totalTimeMerge += (mergeEnd - mergeStart);
                    totalTimeRecur += (recurEnd - recurStart);
                    totalTimeIter += (iterEnd - iterStart);
                    totalTimeMM += (mmEnd - mmStart);
                }
            } else{
                System.out.println("Kth value and array size must be set to 1 or higher.");
                return;
            }

            System.out.printf("\nThe average time to find the kth smallest element using merge sort is: %f nanoseconds" , (float)(totalTimeMerge / iter));
            System.out.printf("\nThe average time to find the kth smallest element using iterative quick sort is: %f nanoseconds" , (float)(totalTimeIter/ iter));
            System.out.printf("\nThe average time to find the kth smallest element using recursive quick sort is: %f nanoseconds" , (float)(totalTimeRecur/ iter));
            System.out.printf("\nThe average time to find the kth smallest element using Median of Medians is: %f nanoseconds" , (float)(totalTimeMM/ iter));

            System.out.println("\n\nKth element for merge sort: "+mergeSort(arr, 0, arrSize - 1, k-1));
            System.out.println("Kth element for recursive quick sort: "+RecurQuickSort(arr, 0, arrSize - 1, k-1));
            System.out.println("Kth element for iterative quick sort: "+IterQuickSort(arr, arrSize, k-1));
            System.out.println("Kth element for Median of Medians: "+mmSort(arr,0, arrSize - 1, k));

            System.out.println("Would you like to search for another k value? (Y to continue).");
            continueSearch = sc.next();
        }
    }
}