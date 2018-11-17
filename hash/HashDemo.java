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

	public HashTable(int size)
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
				System.out.print(hashArray[j].getKey()+" ");
			}
			else
			{
				System.out.print("** ");
			}
		}
		System.out.print("** ");
	}

	public int hashFunc(int key)
	{
		return key % arraySize;//將原本的大範圍數字除以哈西表長度
	}

	public void insert(DataItem item)
	{
		int key = item.getKey();
		int hashVal = hashFunc(key);//將原本的數據值轉為哈希值

		while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1)//當哈希表陣列位置中已經有其他數據
		{
			++hashVal;//將原本的哈希值加1
			hashVal %= arraySize;//在進行取餘操作
		}
		hashArray[hashVal] = item;//將數據填入陣列中
	}

	public DataItem delete(int key)
	{
		int hashVal = hashFunc(key);//將原本的數據值轉為哈希值

		while(hashArray[hashVal] != null)//當目標位置不為空
		{
			if(hashArray[hashVal].getKey()==key)//找到目標值
			{
				DataItem temp = hashArray[hashVal];//將目標值放入變數temp
				hashArray[hashVal] = nonItem;//將他的位置設為nonItem
				return temp;//回傳要刪除的數據
			}
			++hashVal;//沒找到目標值就將哈希值加1往後找
			hashVal %= arraySize;
		}
		return null;//沒找到目標值回傳null
	}

	public DataItem find(int key)
	{
		int hashVal = hashFunc(key);//將原本的數據值轉為哈希值

		while(hashArray[hashVal] != null)//當陣列目標位置中不為空
		{
			if(hashArray[hashVal].getKey() ==key)//找到目標值
			{
				return hashArray[hashVal];//回傳目標值
			}
			++hashVal;//沒找到目標值就將哈希值加1往後找
			hashVal %= arraySize;
		}
		return null;//沒找到目標值回傳null
	}

}

class HashDemo
{
	public static void main(String[] args) throws IOException
	{
		DataItem aDataItem;
		int aKey, size, n, keysPerCell;

		System.out.print("Enter size of hass table: ");
		size = getInt();
		System.out.print("Enter initial number of items: ");
		n = getInt();
		keysPerCell = 10;

		HashTable theHashTable = new HashTable(size);

		for(int j=0; j<n; j++)
		{
			aKey = (int)(java.lang.Math.random()* keysPerCell * size);
			aDataItem = new DataItem(aKey);
			theHashTable.insert(aDataItem);
		}

		while(true)
		{
			System.out.print("Enter first letter of ");
			System.out.print("show, insert, delete, or find");
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
					theHashTable.insert(aDataItem);
					break;
				case 'd':
					System.out.print("Enter key value to delete: ");
					aKey = getInt();
					theHashTable.delete(aKey);
					break;
				case 'f':
					System.out.print("Enter key value to find: ");
					aKey = getInt();
					aDataItem = theHashTable.find(aKey);
					if(aDataItem != null)
					{
						System.out.print("Found "+ aKey);
					}
					else
					{
						System.out.println("Could not find " + aKey);
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
哈希表是一種資料結構，他可以快速的搜尋跟新增數據。無論哈希表中有多少的數據，新增和刪除數據都只需要常量的時間，0(1)的時間等級。
哈希表不但運行數度非常快，他在實現上也非常容易。

哈希表當然也有他的缺點，因為他是使用陣列來實現，所以在一開始就限制了他所包含數據的最大數量，而且在哈希表接近填滿時，
它的性能會下降得非常嚴重。在使用哈希表時最好的方式就是在一開始就知道他將來會包含多少數據。
而且，哈希表也沒有辦法有效的按順率遍歷所有數據，不過如果不需要這項功能的話，哈希表在效率和方便性上佔有很大的優勢。


哈希表的基本實現方式就是透過哈希化將代表數據的大範圍的數字轉換成小範圍的數字，並且以這個值為索引位置直接存入陣列中。
這個大範圍數字是由數據資料轉換而來的，例如我們要建立一個英文字典，我們要幫每個單字編碼，
而且盡量的讓他們不重複，我們可能會用乘冪的方式來將單字轉換成數字，假設我們每個單字包含小寫英文字母和空白，
共有27個字，例如dog 會變成 4*27^2+15*27^1+7*27^0 = 3328。使用這種方式，每個單字基本上都可以得到一個獨一無二的索引值，
但是如果有一個單字他超級長，並且包含了很多後段的單字，例如xyz，那麼他所轉換出來的索引值就會非常長，
長到超出陣列長度的極限以致於無法直接放入陣列中。

這時候我們就需要將這些大範圍索引值進行哈希化，縮小他們的範圍，讓他們可以被放入陣列中。
哈希化的執行方式就是將這個大範圍的數字進行取餘操作，例如現在有一個陣列長度為500，索引值由0到499，
我們讓他除以10取餘數可以把他們的索引值壓縮到變成只有0-9，壓縮率為50：1。在建立哈希表時，
一般來說會將哈希表的陣列長度設為原本資料量的兩倍，而哈希表陣列的長度也當作取餘操作中的被除數。

public int hashFunc(int key)
{
	return key % arraySize;//將原本的大範圍數字除以哈西表長度取餘數
}

函式中的參數代表著大範圍的索引值，我們用它來除哈希錶的長度取得哈希值。

在原始的範圍中，每個數字都代表著一組英文字母的排列，他可能是單字，也可能不是單字。在哈希化之後，這些小範圍的每個數字，
可能包含著多個單字，或著也可能沒有單字。當遇到哈希轉換後多個單字擁有相同的索引數字，
有需多種方法可以解決，而這裡將用線性探測法來解決。

public void insert(DataItem item)
{
	int key = item.getKey();
	int hashVal = hashFunc(key);//將原本的數據值轉為哈希值

	while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1)//當哈希表陣列位置中已經有其他數據
	{
		++hashVal;//將原本的哈希值加1
		hashVal %= arraySize;//在進行取餘操作
	}
	hashArray[hashVal] = item;//將數據填入陣列中
}

線性探測法非常簡單，就是當一個索引值轉換後的單字如果在陣列中遇到他的位置已經被其他單字佔走了，
那他就插入他的下一個位置。假如一個字的索引值在轉換後是1000，但是1000在陣列中已經被填入了，
那他就往1001看看是否可以插入，如果1001也有單字了，那就往1002看，依此類推。

線性探測法雖然簡單，但是當太多的數據擁有重複或著是相近的哈希值時哈希表內部會造成一部份的擁擠和一部分的稀疏，
就是在哈希表內某部分連續的位置上都有相同哈希值或相近哈希值的數據。如果要找到這些數據，
在一開始找到原始哈希值得位置後還需要一個一個比對往後找到目標值，他除了要跨過跟自己相同哈希值得數據，
很有可能也要跨過其他相近哈希值的數據，在這種狀況下哈希表的效率也會隨之下降。

當哈希表內部太滿時效率也回下降，這時候可能就會需要擴展咖西表的陣列，就是把原本哈希表內的數據放到一個新的哈希表陣列。
但是新的哈希表的長度一定會比原本哈希表的長度更長，而哈希值的計算又跟哈希表陣列的長度有關，
所以原本哈希表內的數據不可以按照原本的位置放入新的哈希表陣列，而是要依照新的哈希表陣列長度重新計算哈希值再放入表中。
哈希表的擴展的長度通常是原本哈西表長度的兩倍多一點，因為哈希表的長度最好是質數才可以有效地分散數據。


在不同的狀況下哈希函式的計算方式會有所不同，一個好的哈希函式除了可以盡可能地把數據分散在哈希表中不同的位置裡，
最好還是不要太複雜的運算，因為在哈希表的操作中永遠都要先執行哈希函式。



*/
































