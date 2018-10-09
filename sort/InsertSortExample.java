class ArrayInsert
{
	private long[] arrayInsert;
	private int numValues;

	public ArrayInsert(int max)
	{
		arrayInsert = new long[max];
		numValues = 0;
	}

	public void insert(long value)
	{
		arrayInsert[numValues] = value;
		numValues++;
	}

	public void display()
	{
		for (int j = 0; j<numValues; j++) 
		{
			System.out.print(arrayInsert[j]+ " ");	
		}
		System.out.println("");
	}

	public void insertionSort()
	{

		for ( int out = 1; out<numValues; out++) //從第一位遞增往後跑
		{
			long temp = arrayInsert[out];//存入暫存變數	
			int in = out;
			while(in > 0 && arrayInsert[in-1] >= temp)//所有的樹都跟temp比較,temp大就往後移,直到沒有將temp放入那一位	
			{
				arrayInsert[in] = arrayInsert[in-1];
				--in;
			}
			arrayInsert[in] = temp;
		}
	}
}

class InsertSortExample
{
	public static void main(String[] args)
	{
		ArrayInsert arrTest = new ArrayInsert(10);

		arrTest.insert(9);
		arrTest.insert(4);
		arrTest.insert(3);
		arrTest.insert(6);
		arrTest.insert(7);
		arrTest.insert(0);
		arrTest.insert(5);
		arrTest.insert(1);
		arrTest.insert(2);

		arrTest.display();
		arrTest.insertionSort();
		arrTest.display();
	}



}















