import java.util.Random;
import java.util.Scanner;

class mergesort{
  public static void merge(int a[], int low, int mid, int high)
  {
    int i = low, j = mid + 1, k = low;
    int b[] = new int[a.length];
    while(i <= mid && j <= high)
    {
      if(a[i] <= a[j])
      {
        b[k] = a[i++];
      }
      else
      {
        b[k] = a[j++];
      }
      k++;
    }

    while(i <= mid)
    {
      b[k++] = a[i++];
    }
    while(j <= high)
    {
      b[k++] = a[j++];
    }

    for(i = low; i <= high; i++)
      a[i] = b[i];
  }

  public static void sort(int a[], int low, int high)
  {
    if(low < high)
    {
      int mid = (low + high)/2;
      sort(a, low, mid);
      sort(a,mid + 1, high);
      merge(a, low, mid, high);
    }
  }


  public static int[] randarray(int n)
  {
    int a[] = new int[n];
    Random r = new Random();
    for(int i = 0; i < n; i++)
    a[i] = r.nextInt(10);
    return a;
  }

  public static void main(String args[])
  {
    System.out.print("Enter size of array : ");
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    int a[] = randarray(n);
    System.out.print("Array before sorting : ");
    for(int i : a)
    System.out.print(i + "  ");

    sort(a, 0, n - 1);

    System.out.print("\nArray after sorting : ");
    for(int i : a)
    System.out.print(i + "  ");

    s.close();
  }
}