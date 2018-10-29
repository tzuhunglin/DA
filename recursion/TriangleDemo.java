import java.io.*;

class TriangleDemo
{
	static int theNumber;

	public static void main(String[] args) throws IOException
	{
		System.out.print("Enter a number: ");
		theNumber = getInt();
		int theAnswer = triangle(theNumber);
		System.out.println("Triangle ="+theAnswer);
	}

	public static int triangle(int n)
	{
		if(n==1)
		{
			return 1;
		}
		else
		{
			return (n + triangle(n-1));
		}
	}

	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
}

/*
遞迴就是由一個函式不斷的呼叫自己，而在每一次呼叫自己的過程中把參數的範圍縮得更小，
直到參數符合停止條件時才停止呼叫自己開始返回。

遞迴除了執行方式特別之外還有一個好處，就是是簡潔的程式碼。
因為遞迴的執行方式是由一個韓式不斷的呼叫自己，所以一個函式就可以重複使用。

本篇遞迴範例我們所要計算的是三角數字，三角數字就是一串由1,3,6,10,15,21,28,36.....
所組成的序列。或著我們可以看做
1	Ｘ
2	ＸＸ
3	ＸＸＸ
4	ＸＸＸＸ
5	ＸＸＸＸＸ
6	ＸＸＸＸＸＸ
7	ＸＸＸＸＸＸＸ
8	ＸＸＸＸＸＸＸＸ
在第一階時，共有1個Ｘ。而在第二階時，Ｘ的數量為3(1+2=3)，在第三階時則有6個X(1+2+3=6)，
依此類推。三角數字的公式為(N^2+N)/2。

public static int triangle(int n)
{
	if(n==1)
	{
		return 1;
	}
	else
	{
		return (n + triangle(n-1));
	}
}

triangle是我們執行計算三角數字的函式，內容相當精簡。當n為1時就返回1，
不為1時就繼續執行triangle(n-1)，通過不段的縮減參數n直到1，才開始返回n+triangle(n-1)。
所以如果一開始輸入的參數n為4，n在這一連串的triangle函式中一開始會是4>3>2>1
而當n到達1時便開始返回。

如果我們輸入的數字是4，那一共會執行四層triangle。
當到達第四層triagle時，此時的n已經在每一層的triangle函式中遞減至1，
所以會直接返回數字1到第三層triangle，
這時第三層的參數n為2會加上第四層返回的數字1(等於3)再一起返回至第三層triangle。
依此類推一直返回至當處第一個呼叫triangle的位置。最後的結果會是1+2+3+4=10，(4^2+4)/2=10。

遞迴在某些狀況下效率可能不比迴圈好，就像本篇範例，如果用while迴圈來跑可能會更快。
而且遞迴在每一次呼叫自己時系統都有一定的消耗，當呼叫次數太多時絕對有可能造成程式的崩潰。
但是遞迴的確地從概念上把問題變得更簡單，也在某些情況下把程式碼變得更精簡易懂。

/*










