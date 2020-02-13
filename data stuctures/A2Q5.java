import java.util.*;

public class A5Q2
{
  public static void main(String[]args)
  {
    Random generator = new Random();
    int x = Integer.parseInt(args[0]);
    for(int c=0;c<1000;c++)
    {
      int[] a = new int[x];
      for(int w=0;w<a.length;w++)
      {
        a[w] = generator.nextInt();
      }
      int[] sr1,sr2,sr3,sr4;
      sr1 = Arrays.copyOf(a,a.length);
      sr2 = Arrays.copyOf(a,a.length);
      sr3 = Arrays.copyOf(a,a.length);

      Sorting.insertionSort(a);
      Sorting.heapSort(sr1);
      Sorting.quicksort(sr2);
      Sorting.javSort(sr3);

    }

  }
}
