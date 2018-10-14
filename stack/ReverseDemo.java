import java.io.*;
class StackX
{
	private int maxSize;
	private char[] stackArray;
	private int top;

	public StackX(int max)
	{
		maxSize = max;
		stackArray = new char[maxSize];
		top =-1;
	}

	public void push(char j)
	{
		stackArray[++top] = j;
	}

	public char pop()
	{
		return stackArray[top--];
	}

	public char peek()
	{
		return stackArray[top];
	}

	public boolean isEmpty()
	{
		return (top == -1);
	}
}

class Reverser
{
	private String input;
	private String output;

	public Reverser(String in)
	{
		input = in;
	}

	public String doRev()
	{
		int stackSize = input.length();
		StackX theStack = new StackX(stackSize);

		for(int j=0; j<input.length(); j++)
		{
			char ch = input.charAt(j);
			theStack.push(ch);
		}

		output = "";
		while(!theStack.isEmpty())
		{
			char ch = theStack.pop();
			output = output + ch;
		}

		return output;
	}
}

class ReverseDemo
{
	public static void main(String[] args) throws IOException
	{
		String input, output;
		while(true)
		{
			System.out.print("Enter a string: ");
			System.out.flush();
			input = getString();
			if(input.equals(""))
			{
				break;
			}
			Reverser theReverser = new Reverser(input);
			output = theReverser.doRev();
			System.out.println("Reversed: " + output);
		}
	}

	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s; 
	}
}

/*
堆疊的最主要的特性就是先進後出和後進先出並且一次只能訪問一個數據，這樣的特性在很多時候都能派上用場，尤其是在逆序時。
想像一下，當我們把1 2 3 4 5 這五個數字一去放入堆疊中。全部放入後再依序把他們拿出來，我們拿出的順序會是什麼，就是5 4 3 2 1。
堆疊的特性自然而然地幫助我們完成了逆序的工作。

在這裡的練習中，我們將使用堆疊作為我們逆序的工具將我們所輸入的字串在逆序之後輸出，一開始的程式如下。

public static void main(String[] args) throws IOException
{
	String input, output;
	while(true)
	{
		System.out.print("Enter a string: ");
		System.out.flush();
		input = getString();
		if(input.equals(""))
		{
			break;
		}
		Reverser theReverser = new Reverser(input);
		output = theReverser.doRev();
		System.out.println("Reversed: " + output);
	}
}

在範例的一開始，我們會先要求使用者輸入字串，在檢查字串不為空字串時我們就將字串帶入Reverse類別進行逆序的工作，如果字串為空值，我們將停止迴圈。
Reverse類別的公用很簡單，在呼叫完成後唯一能做的事就是執行doRev將字串倒轉。

public String doRev()
{
	int stackSize = input.length();
	StackX theStack = new StackX(stackSize);

	for(int j=0; j<input.length(); j++)
	{
		char ch = input.charAt(j);
		theStack.push(ch);
	}

	output = "";
	while(!theStack.isEmpty())
	{
		char ch = theStack.pop();
		output = output + ch;
	}

	return output;
}

在doRev函式中的一開始，我們就以字串的長度呼叫一組堆疊。接著，我們在for迴圈中，將字串轉以字元陣列的方式一個一個的插入堆疊。
戴將所有自插入堆疊時，我們再將這些字元一個一個從堆疊中取出放入變數output。此時，output的內容就是完全逆序的字串。
*/

















