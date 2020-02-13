/*
The purpose of this class is to execture the heapSort algorithm on an integer array
*/

public class heapSort
{
  int heapsize;       //tracks the heapsize

  /*public constructor when called implement the heapsort algorithm
    @param integer array a
  */
  public heapSort(int[] A)
  {
    int i,largest;      //instance variables

    heapsize = 1;       //starts off at 1 as per the algorithm
    i = 1;
    while(i<A.length)   //inserts the array into the heap
    {
      insert(A,A[i]);   //calls the insert methiod
      heapsize++;       //increments heapsize
      i++;              //increments the loop counter
    }
    i = A.length-1;     //starts the process of deletion of give us the array in order
    while(i>0)
    {
      largest = deleteMax(A);   //deletes the larges element
      A[i]= largest;            //adds it to the array
      i--;                    //decrements the index
    }
  }

  /*implements the insert algorithm of a heap in two parts by adding and then restoring order
    @param int array a
    @param integer to add
    @throws FullHeapException if the size of the heap is met
  */
  public void insert(int[] A,int k)
  {
    int j,tmp;

    //runs while we have not filled up the array
    if(heapsize<A.length)
    {
      A[heapsize] = k;    //adds to the end of the heap
      heapsize++;         //increments size of the heap

      j = heapsize-1;     //start of putting it in order
      while((j>0)&&(A[j]>A[parent(j)])) //runs when the parent is less than the child
      {
        tmp = A[j];         //placeholder
        A[j]= A[parent(j)]; //swaps the parent and child to restore the max heap property
        A[parent(j)] = tmp;
        j = parent(j);      //moves the index to the parent  to check if we need to move up again
      }
    }
    else
    {
      throw new FullHeapException();    //if the heap is already filled
    }
  }

  /*implements the deletion of a heap element to maintian the maxheap property
    @param integer array a
    @throws an EmptyHeapException if the heapsize is 0
  */
  public int deleteMax(int[] A)
  {
    int max;
    if(heapsize>0)      //if we have somehting to delete
    {
      max = A[0];       //saves the value we are deleting
      A[0]= A[heapsize-1];  //sets the new head to the element at the bottom of the heap
      heapsize--;       //decrements the size of the heap

      int j= 0;
      int l,r,large;
      while(j<heapsize)     //purs it back in order
      {
        l = left(j);        //calls the left method to find the left child in array
        r = right(j);       //finds the right child in the array
        large = j;          //sets the current largest to the current index

        if((l<heapsize)&&(A[l]>A[large]))   //if left child is larger
        {
          large = l;        //changes large to the left child
        }
        if((r<heapsize)&&(A[r]>A[large])) //if the right child is larger
        {
          large =r;     //changes large to the right child
        }
        if(large!=j)      //swaps the values if the large value isnt what is was to starts
        {
          int tmp = A[j];     //swaps the parent and child depending on what side was larger
          A[j] = A[large];
          A[large] = tmp;
          j = large;
        }
        else
        {
          j = heapsize;     //stops the loop if we have nothing to swap
        }
      }
      return max;     //returns what we deleted to put it back in the array in order
    }
    else
    {
      throw new EmptyHeapException();     //if we have an empty heap
    }
  }
  public int parent(int k)          //gets the parent
  {
    return((k-1)/2);
  }
  public int right(int k)         //gets the right child
  {
    return((2*k)+2);
  }
  public int left(int k)        //gets the left child
  {
    return((2*k)+1);
  }
}
