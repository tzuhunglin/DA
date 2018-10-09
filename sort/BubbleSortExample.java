class ArrayBubble{
	private long[] arrBubble;
	private int numValues;
	protected int cntSwap;

	public ArrayBubble(int max)
	{
		arrBubble = new long[max];
		numValues = 0; 
	}

	public void insert(long value)
	{
		arrBubble[numValues] = value;
		numValues++;
	}

	public void bubbleSort()
	{
		/*
		外迴圈的指標out表示這一圈比較的終點，在這一圈
		*/
		for (int out = numValues-1; out>1; out--) //外迴圈的索引表示最大值最後會被移動到的位址，也表示這個位置的值以及這個位置之前的值為這一圈比教的對象。		
		{
			for (int in=0; in<out; in++)//內回圈會抓出索引位置的直以及索引位置後一位得值進行比較
			{
				if(arrBubble[in] > arrBubble[in+1])
				{
					swap(in, in+1);
				}	
			}	
		}
		show();
		System.out.println("It swapped "+cntSwap+" times");

	}

	public void show()
	{
		for (int i=0; i<numValues; i++) 
		{
			System.out.print(arrBubble[i]+" ");	
		}
		System.out.println("");
	}

	private void swap(int value1, int value2)
	{
		long temp = arrBubble[value1];
		arrBubble[value1] = arrBubble[value2];
		arrBubble[value2] = temp;
		cntSwap ++;
	}
}

class BubbleSortExample
{
	public static void main(String[] args)
	{
		ArrayBubble arrTest = new ArrayBubble(6);

		arrTest.insert(6);
		arrTest.insert(1);
		arrTest.insert(4);
		arrTest.insert(2);
		arrTest.insert(3);
		arrTest.insert(5);

		arrTest.show();
		arrTest.bubbleSort();

	}
}
/*
非常慢 簡單
*/











