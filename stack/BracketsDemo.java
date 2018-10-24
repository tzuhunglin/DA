import java.io.*;

class StackX
{
	private int maxSize;
	private char[] stackArray;
	private int top;

	public StackX(int s)
	{
		maxSize = s;
		stackArray = new char[maxSize];
		top = -1;
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

class BracketChecker
{
	private String input;

	public BracketChecker(String in)
	{
		input = in;
	}

	public void check()
	{
		int stackSize = input.length();
		StackX theStack = new StackX(stackSize);

		for(int j=0; j<input.length(); j++)
		{
			char ch = input.charAt(j);
			switch(ch)
			{
				case '{':
				case '[':
				case '(':
					theStack.push(ch);
					break;
				case '}':
				case ']':
				case ')':
					if(!theStack.isEmpty())
					{
						char chx = theStack.pop();
						if( (ch == '}' && chx != '{' ) || 
							(ch == ']' && chx != '[' ) || 
							(ch == ')' && chx != '(' ) )
						{
							System.out.println("Error: "+ch+" at "+j);
						}
					}else{
						System.out.println("Error: "+ch+" at "+j);	
					}
					break;
				default :
					break;
			}
		}
		if(!theStack.isEmpty())
		{
			System.out.println("Error: missing right delimiter");
		}
	}
}

class BracketsDemo
{
	public static void main(String[] args) throws IOException
	{
		String input;
		while(true)
		{
			System.out.print("Enter string containing delimiters: ");
			System.out.flush();
			input = getString();
			if(input.equals(""))
			{
				break;
			}

			BracketChecker theChecker = new BracketChecker(input);
			theChecker.check();
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
堆疊先進後出、後進先出以及只能查找最新進入堆疊的數據的特性可以被運用在許多不同的地方，尤其是作為工程師的工具使用。
在這個範例中，我們會使用堆疊作為檢測工具，檢測一段文字中是否有匹配對稱的括號組合。

public void check()
{
	int stackSize = input.length();
	StackX theStack = new StackX(stackSize);
	for(int j=0; j<input.length(); j++)
	{
		char ch = input.charAt(j);
		switch(ch)
		{
			case '{':
			case '[':
			case '(':
				theStack.push(ch);
				break;
			case '}':
			case ']':
			case ')':
				if(!theStack.isEmpty())
				{
					char chx = theStack.pop();
					if( (ch == '}' && chx != '{' ) || 
						(ch == ']' && chx != '[' ) || 
						(ch == ')' && chx != '(' ) )
					{
						System.out.println("Error: "+ch+" at "+j);
					}
				}else{
					System.out.println("Error: "+ch+" at "+j);	
				}
				break;
			default :
				break;
		}
	}
	if(!theStack.isEmpty())
	{
		System.out.println("Error: missing right delimiter");
	}
}

在此段程式碼中，我們可以看到，一開始我們先將字串轉為字元陣列以供檢測。我們主要檢測的目標一共有三個()、[]以及{}。
檢測的過程很簡單，只要我們發現了左括號，我們就把他放入堆疊中，只要我們發現了右括號，我們就在堆疊中取出最上層的左括號，
看看兩個括號是否匹配。如果不匹配變跳出訊息。

*/

























