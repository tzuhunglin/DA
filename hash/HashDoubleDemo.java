import java.io.*;

class DataItem
{
	private int iData;

	public DataItem(int ii)
	{
		iData = ii;
	}

	public int getKey()
	{
		return iData;
	}
}

class HashTable
{
	private DataItem[] hashArray;
	private int arraySize;
	private DataItem nonItem;

	HashTable(int size)
	{
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1);
	}

	public void displayTable()
	{
		System.out.print("Table: ");
		for(int j=0; j<arraySize; j++)
		{
			if(hashArray[j] != null)
			{
				System.out.print(hashArray[j].getKey()+ " ");
			}
			else
			{
				System.out.print("** ");
			}
		}
		System.out.println(" ");
	}


	public int hashFunc1(int key)
	{
		return key % arraySize;//用原本的數值除以陣列長度取餘
	}

	public int hashFunc2(int key)
	{
		return 5-key%5;//用原本的數值除以5取餘當作步長
	}

	public void insert(int key, DataItem item)
	{
		int hashVal = hashFunc1(key);//哈希值
		int stepSize = hashFunc2(key);//步長

		while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1)//當陣列中位置有數據時
		{
			hashVal += stepSize;//哈希值為原本的哈希值加上步長	
			hashVal %= arraySize;//哈希值為原本的哈希值除以陣列長度
		}

		hashArray[hashVal] = item;
	}

	public DataItem delete(int key)
	{
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		while(hashArray[hashVal] != null)
		{
			if(hashArray[hashVal].getKey()==key)
			{
				DataItem temp = hashArray[hashVal];
				hashArray[hashVal] = nonItem;
				return temp;
			}
			hashVal += stepSize;
			hashVal %= arraySize;
		}
		return null;
	}

	public DataItem find(int key)
	{
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		while(hashArray[hashVal] != null)
		{
			if(hashArray[hashVal].getKey()==key)
			{
				return hashArray[hashVal];
			}
			hashVal += stepSize;
			hashVal %= arraySize;
		}
		return null;
	}
}


class HashDoubleDemo
{
	public static void main(String[] args) throws IOException
	{
		int aKey;
		DataItem aDataItem;
		int size, n;

		System.out.print("Enter size of hash table: ");
		size = getInt();
		System.out.print("Enter initial number of items: ");
		n = getInt();
		HashTable theHashTable = new HashTable(size);
		for(int j = 0; j<n ; j++)
		{
			aKey = (int)(java.lang.Math.random()*2*size);
			aDataItem = new DataItem(aKey);
			theHashTable.insert(aKey, aDataItem);
		}

		while(true)
		{
			System.out.print("Enter first letter of");
			System.out.print("show, insert, delete, or find: ");
			char choice = getChar();
			switch(choice)
			{
				case 's':
					theHashTable.displayTable();
					break;
				case 'i':
					System.out.print("Enter key value to insert: ");
					aKey = getInt();
					aDataItem = new DataItem(aKey);
					theHashTable.insert(aKey, aDataItem);
				case 'd':
					System.out.print("Enter key value to delete: ");
					aKey = getInt();
					theHashTable.delete(aKey);
					break;
				case 'f':
					System.out.print("Enter key value to find: ");
					aKey = getInt();
					aDataItem = theHashTable.find(aKey);
					if(aDataItem!=null)
					{
						System.out.print("Found "+ aKey);
					}
					else
					{
						System.out.print("Could not find\n" +aKey);
					}
					break;
				default:
					System.out.print("Invalid entry\n");
			}

		}
	}


	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
}


/*
為了消除哈希表內的劇集，我們可以使用在哈希法。再哈希法，顧名思義就是當一個哈希值的位置已經存在數據了，
那就將這個哈希值再次哈希化取得一個新的哈希值，一個新的哈希值位置距離原本的哈西值位置可能很遠，也可能很近，
這種狀況可以防止多項第一次哈西值相近的數據形成聚集。

在哈希法除了需要計算哈希值外，還要計算步長。當第一次哈希值的位置有數據時，會用原本的哈希值加上步長再除以陣列的長度。

public int hashFunc1(int key)
{
	return key % arraySize;//用原本的數值除以陣列長度取餘
}
這是機算哈西值的函式，就是用原始值除以陣列長度取餘數取得哈希值。

public int hashFunc2(int key)
{
	return 5-key%5;//用原本的數值除以5取餘當作步長
}

在範例中步長的算法是用原始值除以5取餘數，為了避免結果為0(步長如果為0那目標位置將不會改變)所以必須用5減餘數。

public void insert(int key, DataItem item)
{
	int hashVal = hashFunc1(key);//哈希值
	int stepSize = hashFunc2(key);//步長

	while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1)//當陣列中位置有數據時
	{
		hashVal += stepSize;//哈希值為原本的哈希值加上步長	
		hashVal %= arraySize;//哈希值為原本的哈希值除以陣列長度
	}
	hashArray[hashVal] = item;
}

再哈希法的方式因為多了步長的的計算，讓哈希值的目標位置不再是一格一格的往後移動，而是依照步長的計算結果移動。
雖然不同的哈希值加上不同的步長也有可能會遇到同樣的位置，但是在哈希的做法可以更有效地避免聚集。

當建立一個再哈希法的哈希表時會將陣列的長度設為一個質數以避免在位置探測實在已探測過的位置無限循環。
例如一個哈希表的表長為20，從0到19。一個哈希值為10，步長為5，那他接下來探測的位置將會是15、0、5、10、15一直循環下去。
而使用質數作為除數，要整除他是不可能的，所以他最後會探測完整張哈希表，只要表中有空格就可以探測得到。




*/























