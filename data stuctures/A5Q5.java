/*
Class to run each of the sorting methods as outlined in the sorting class
@param the length of the arrays we want to Create
take 1000 arrays of that length randomly fills them and then creates a copy for
each version of the sorting algorithms calls them all before creating another array.
*/

import java.util.*;

public class A5Q5
{
  public static void main(String[]args)
  {
    //random number generator used to fill the array
    Random generator = new Random();
    int x = Integer.parseInt(args[0]);    // the length of the array taken from args
    for(int c=0;c<1000;c++)       //loops 1000 times to create 1000 arrays
    {
      int[] a = new int[x];         //declares array of length x
      for(int w=0;w<a.length;w++)   //fills the array
      {
        a[w] = generator.nextInt();   //generates a random number
      }
      int[] sr1,sr2,sr3,sr4;
      sr1 = Arrays.copyOf(a,a.length);    //creates copies before sorting
      sr2 = Arrays.copyOf(a,a.length);
      sr3 = Arrays.copyOf(a,a.length);

      Sorting.insertionSort(a);           //calls each implemented sorting method
      Sorting.heapSort(sr1);
      Sorting.quicksort(sr2);
      Sorting.javSort(sr3);

    }

  }
}
