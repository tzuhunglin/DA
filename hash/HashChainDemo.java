import java.io.*;

class Link
{
	private int iData;
	public Link next;

	public Link(int it)
	{
		iData = it;
	}

	public int getKey()
	{
		return iData;
	}

	public void displayLink()
	{
		System.out.print(iData + " ");
	}
}

class SortedList
{
	private Link first;
	public void SortedList()
	{
		first = null;
	}

	public void insert(Link theLink)
	{
		int key = theLink.getKey();
		Link previous = null;
		Link current = first;

		while(current != null && key> current.getKey())
		{
			previous = current;
			current = current.next;
		}

		if(previous == null)
		{
			first = theLink;
		}
		else
		{
			previous.next = theLink;
		}
		theLink.next = current;
	}

	public void delete(int key)
	{
		Link previous = null;
		Link current = first;

		while(current != null && key != current.getKey())
		{
			previous = current;
			current = current.next;
		}

		if(previous == null)
		{
			first = first.next;
		}
		else
		{
			previous.next = current.next;
		}
	}

	public Link find(int key)
	{
		Link current = first;

		while(current != null && current.getKey()<= key)
		{
			if(current.getKey()==key)
			{
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public void displayList()
	{
		System.out.print("List (first-->last):");
		Link current = first;
		while(current != null)
		{
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}
}

class HashTable
{
	private SortedList[] hashArray;
	private int arraySize;

	public HashTable(int size)
	{
		arraySize = size;
		hashArray = new SortedList[arraySize];
		for(int j=0; j<arraySize; j++)
		{
			hashArray[j]= new SortedList();
		}
	}

	public void displayTable()
	{
		for(int j=0; j<arraySize;j++)
		{
			System.out.print(j+ ". ");
			hashArray[j].displayList();
		}
	}

	public int hashFunc(int key)
	{
		return key % arraySize;
	}

	public void insert(Link theLink)
	{
		int key = theLink.getKey();
		int hashVal = hashFunc(key);
		hashArray[hashVal].insert(theLink);
	}

	public void delete(int key)
	{
		int hashVal = hashFunc(key);
		hashArray[hashVal].delete(key);
	}

	public Link find(int key)
	{
		int hashVal = hashFunc(key);
		Link theLink = hashArray[hashVal].find(key);
		return theLink;
	}
}

class HashChainDemo
{
	public static void main(String[] args) throws IOException
	{
		int aKey;
		Link aDataItem;
		int size, n, keysPerCell = 100;

		System.out.print("Enter size of hash table:");
		size = getInt();
		System.out.print("Enter initial number of items");
		n = getInt();

		HashTable theHashTable = new HashTable(size);

		for(int j=0; j<n; j++)
		{
			aKey = (int)(java.lang.Math.random()*keysPerCell*size);
			aDataItem = new Link(aKey);
			theHashTable.insert(aDataItem);
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
					aDataItem = new Link(aKey);
					theHashTable.insert(aDataItem);
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
哈希鍊表是同時結合了哈西表以及鏈表的一種資料結構，他在哈希陣列中的每一個位置都設置了一串鏈表，
當出現相同哈希值得數據時，就直接將數據插入該位置的鍊表中，不需要再額外尋求哈希表中的空位存放數據。

public void insert(Link theLink)
{
	int key = theLink.getKey();
	int hashVal = hashFunc(key);
	hashArray[hashVal].insert(theLink);
}

哈希鍊表再遇到目標位置已有數據時可以省去再次探測的時間，而且鍊表在插入時的時間等級為O(1)，是相當有效略的做法。
但是在搜尋以及刪除時，在找到目標位置的鏈表後平均會需要搜尋一半的鏈表長度才能找到目標值<練表的搜尋時間與M成正比，
M為鍊錶的平均項數，時間等級為O(M)。如果鏈表中有許多哈希值一樣的數據，
那麼鍊表的長度就會一直增長，哈希鍊錶的效率也會跟著下降。
*/























