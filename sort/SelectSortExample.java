class ArraySelect
{
	private long[] arrSelect;
	private int numValues;
	protected int cntSwap;


	public ArraySelect(int max)
	{
		arrSelect = new long[max];
		numValues = 0;

	}

	public void insert(long value)
	{
		arrSelect[numValues] =value;
		numValues++;
	}

	public void show()
	{
		for (int j = 0; j<numValues; j++) 
		{
			System.out.print(arrSelect[j]+" ");	
		}
		System.out.println(" ");
	}

	public void selectionSort()
	{
		int out,in, min;

		for (out = 0; out<numValues; out++) 
		{
			min = out;
			for (in = out+1; in<numValues; in++) {
				if(arrSelect[in]<arrSelect[min])
				{
					min = in;
				}
			}
			swap(out,min);
		}
		show();
		System.out.println("It swapped "+cntSwap+" times");

	}

	private void swap(int value1, int value2)
	{
		long temp = arrSelect[value1];
		arrSelect[value1] = arrSelect[value2];
		arrSelect[value2] = temp;
		cntSwap ++;

	}
}



class SelectSortExample
{
	public static void main(String[] args)
	{
		ArraySelect arrTest = new ArraySelect(7);

		arrTest.insert(6);
		arrTest.insert(0);
		arrTest.insert(3);
		arrTest.insert(4);
		arrTest.insert(2);
		arrTest.insert(1);
		arrTest.insert(5);

		arrTest.show();
		arrTest.selectionSort();
	}
}






















