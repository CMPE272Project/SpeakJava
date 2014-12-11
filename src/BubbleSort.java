public class BubbleSort
{

public int x[]= {123,402,12,5,61,10};

 public void bubbleSort()
 {
	 boolean flag = true;  
     int temp;  

     while ( flag )
     {
        flag= false;   
        for(int i=0;  i < x.length -1;  i++ )
        {
           if ( x[ i ] < x[i+1] )
           {
               temp = x[ i ];          
               x[ i ] = x[ i+1 ];
               x[ i+1 ] = temp;
              flag = true;             
          }
        }
      }
}

public void printArray()	
{
	for(int i=0;i<x.length;i++)
	{
	  System.out.println(x[i]);
	}
}
   
public static void main(String args[])
{
	BubbleSort bs= new BubbleSort();
	System.out.println("Array before Sorting");
	bs.printArray();
	bs.bubbleSort();
	System.out.println("Array after Sorting");
	bs.printArray();
              	
}	

 
	
}