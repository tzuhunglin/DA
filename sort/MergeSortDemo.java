class DArray
{
	private long[] theArray;
	private int nElems;

	public DArray(int max)
	{
		theArray = new long[max];
		nElems = 0;
	}

	public void insert(long value)
	{
		theArray[nElems] = value;
		nElems++;
	}

	public void display()
	{
		for(int j=0; j<nElems; j++)
		{
			System.out.print(theArray[j]+" ");
		}
		System.out.println("");
	}

	public void mergeSort()
	{
		long[] workSpace = new long[nElems];
		recMergeSort(workSpace, 0, nElems-1);
	}

	private void recMergeSort(long[] workSpace, int lowerBound, int upperBound)
	{
		if(lowerBound == upperBound)
		{
			return;
		}else{
			int mid = (lowerBound+upperBound)/2;//取整數
			
			recMergeSort(workSpace, lowerBound, mid);
			recMergeSort(workSpace, mid+1, upperBound);
			merge(workSpace, lowerBound, mid+1, upperBound);
		}
	}

	private void merge(long[] workSpace, int lowPtr, int highPtr, int upperBound)
	{
		int j = 0;
		int lowerBound = lowPtr;
		int mid = highPtr-1;
		int n = upperBound-lowerBound+1;

		while(lowPtr <= mid && highPtr <= upperBound)
		{
			if(theArray[lowPtr] < theArray[highPtr])
			{
				workSpace[j++] = theArray[lowPtr++];
			}else{
				workSpace[j++] = theArray[highPtr++];
			}
		}

		while(lowPtr <= mid)
		{
			workSpace[j++] = theArray[lowPtr++];
		}

		while(highPtr <= upperBound)
		{
			workSpace[j++] = theArray[highPtr++];
		}

		for(j=0; j<n; j++)
		{
			theArray[lowerBound+j] = workSpace[j];
		}
	}
}

class MergeSortDemo
{
	public static void main(String[] args)
	{
		int maxSize = 100;
		DArray arr;
		arr = new DArray(maxSize);
		arr.insert(2);
		arr.insert(8);
		arr.insert(16);
		arr.insert(9);
		arr.insert(11);
		arr.insert(14);
		arr.insert(3);
		arr.insert(15);
		arr.insert(4);
		arr.insert(12);
		arr.insert(19);
		arr.insert(1);
		arr.insert(18);
		arr.insert(13);
		arr.insert(5);
		arr.insert(7);
		arr.insert(10);
		arr.insert(17);
		arr.insert(0);
		arr.insert(6);

		arr.display();
		arr.mergeSort();
		arr.display();
	}
}

























