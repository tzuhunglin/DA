public class CaesarCipher{
	protected char[] encoder = new char[26];//型態為CHAR,長度為26的空陣列
	protected char[] decoder = new char[26];//型態為CHAR,長度為26的空陣列

	public CaesarCipher(int rotation){
		for(int k=0; k<26; k++){
			encoder[k] = (char)('A'+(k+rotation)%26);//已字元代碼的方式取得字元,字元可以直接與數字做加減
			decoder[k] = (char)('A'+(k-rotation+26)%26);
		}
	}

	public String encrypt(String message){
		return transform(message, encoder);
	}
	public String decrypt(String secret){
		return transform(secret, decoder);
	}

	private String transform(String original, char[] code){
		char[] msg = original.toCharArray();
		for(int k=0; k< msg.length; k++){
			if(Character.isUpperCase(msg[k])){
				int j = msg[k] - 'A';
				msg[k] = code[j];
			}
		}
		return new String(msg);
	}

	public static void main(String[] args){
		CaesarCipher cipher = new CaesarCipher(3);
		System.out.println("Encryption code = "+ new String(cipher.encoder));
		System.out.println("Decryption code = "+ new String(cipher.decoder));
		String message = "THIS IS A EXAMPLE OF CAESARCIPHER.";
		String coded = cipher.encrypt(message);
		System.out.println("Secret: "+coded);
		String answer = cipher.decrypt(coded);
		System.out.println("Message: "+ answer);
	}
}


/*
凱薩加密是早期的加密方式。
就是將原本字串中的字元用相對應的方式轉換成另外一個字元，而產生另一組讓人無法辨識的字串。
就像在範例中我們將原本的字串"THIS IS A EXAMPLE OF CAESARCIPHER."轉換成無法辨識的字串"WKLV LV D HADPSOH RI FDHVDUFLSKHU."
加密的方式很簡單，就是將原本的字元進行位移。在此範例中，我們使用的位移量是3。A會被轉換成D,B會被轉換成E以及T會被轉換成W，以此類推。
一組經過加密的字串可以以相同的方式進行解密還原成原本未加密的字串，所以在範例中我們也會看到相對應的解密方式。

在程式的一開始可以看到我們先定義了兩組長度為26的字元陣列encoder以及decoder分別在加密以及解密時使用。
在encoder陣列中，我們會看到[D,E,F,G,H........,A,B,C]用以把原本的大寫英文字母做位移量3的轉換。所以A會被轉換成D,B會被轉換成以及T會被轉換成W。
在decoder陣列中，我們會看到[X,Y,Z,A,B........,U,V,W]用以把原本的大寫英文字母做位移量-3的轉換。所以原本經過加密變成D的A可以在被轉換成原本的A,原本經過加密變成W的T可以在被轉換成原本的T。
在程式的第7，8行，我們使用(char)(數字)來取得字元。在JAVA中，我們可以用兩個字元做減法來取得這兩個字元的距離(結果為數字)。
像是'A'在Unicode中的編碼是65，'D'在Unicode中的編碼是68，如果我們在JAVA中進行'D'-'A'，我們會得到數字3的結果。
A是大寫英文字母中的第一個字，所以我們使用它來作為基準來加上(k(遞增的變數)+rotation(位移量)%26)。最後的%26是為了不要讓結果超出我們所要的範圍所以我們必須除26取他的餘數。
例如，當k為24時，加上位移量3，我們會得到27。這個數字已經超過我們原本26個大寫英文字母的範圍，所以我們取他除以26的餘數1讓他回到我們的範圍之中得到66(B)。
在decoder中，我們一樣以A為基準加上(k-rotation+26)%26，這邊跟encoder類似，不同在於原本的+變-以及在除以26之前我們又加了26以避免出現負數的情況。
我們已A為基準，範圍是A到Z，65到91。當複數出現時結果將小於65所以我們必須將結果在%26之前再加上26讓數字返回我們的範圍之中。

在encrypt和decrypt中他們都呼叫了，transform並分別帶入(需加密的字串,encoder)以及(需解密的字串,decoder)
在transform中，我們一開始先將字串以toCharArray()的方式轉為陣列。接者已迴圈的分式處理每一個字元將他們進行轉換。
在for迴圈中，因為我們已經將字元限定在26個大寫字母中，所以我們先判別他是不是大寫字母，是才進行處理。
處理的方式很簡單，我們先以字串的字元msg[k]減去基準A取得他們之間的距離。再將這個距離帶入encode或著decode陣列中找出需要轉換的字元並放入msg字元陣列中取代原本的字元。
轉換完成後我們再以new String(字元陣列)的方式將字元陣列轉換回字串並回傳，我們就可以得到加密後或解密後的字串了。
*/






