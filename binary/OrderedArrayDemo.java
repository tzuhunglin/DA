class OrdArray
{
	private long[] a;
	private int nElems;

	public OrdArray(int max)
	{
		a = new long[max];
		nElems = 0;
	}

	public int size()
	{
		return nElems;
	}

	public int find(long searchKey)
	{
		int lowerBound = 0;//下標設為陣列中第一位的位置
		int upperBound = nElems -1;//上標設為陣列中最後一位的位置
		int curIn;//當前指標位置

		while(true)
		{
			curIn = (lowerBound + upperBound) /2;//總數量為基數時，cutIn的位置會在中位數。偶數時會在前半部分的最後一位
			if(a[curIn]==searchKey)//找到目標值，返回
			{
				return curIn;
			}
			else if(lowerBound> upperBound)//當下標超過上標表示找不到
			{
				return nElems;
			}
			else
			{
				if(a[curIn]<searchKey)//當目標值比當前位置得值大時
				{
					lowerBound = curIn+1;//從目前位置+1繼續找
				}else{				  //當目標值比當前位置得值小時
					upperBound = curIn -1;//從目前位置-1繼續找
				}
			}
		}
	}

	public void insert(long value)
	{
		int j;
		for(j=0; j<nElems; j++)//找到比j大的值後停止
		{
			if(a[j] > value)
			{
				break;
			}
		}

		for(int k = nElems; k>j; k--)//將所有筆j小的值向後移動
		{
			a[k] = a[k-1];
		}
		a[j] = value;//將j插入空格
		nElems++;
	}

	public boolean delete(long value)
	{
		int j = find(value);
		if(j==nElems)
		{
			return false;
		}else{
			for(int k=j; k<nElems; k++)
			{
				a[k] = a[k+1];
			}
			nElems--;
			return true;
		}
	}

	public void display()
	{
		for(int j=0; j<nElems;j++)
		{
			System.out.print(a[j]+" ");
		}
		System.out.println(" ");
	}
}

class OrderedArrayDemo
{
	public static void main(String[] args)
	{
		int maxSize = 100;
		OrdArray arr;
		arr = new OrdArray(maxSize);

		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);

		int searchKey =55;
		if(arr.find(searchKey) != arr.size())
		{
			System.out.println("Found " + searchKey);
		}else{
			System.out.println("Can't find " +searchKey);
		}

		arr.display();

		arr.delete(00);
		arr.delete(55);
		arr.delete(99);
		arr.display();



	}
}


