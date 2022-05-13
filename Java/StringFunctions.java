import java.util.Scanner;

public class StringFunctions {

	public String[] createPassword(int amount, int length) {
		// FUNCTION:
		// Generates a given number of passwords (which include capital/small letters - numbers and symbols) of given length and returns them
		// Arguments: int amount (number of generated passwords) int length (length of each password)
		String[] passwords = new String[amount];
		
		for(int i=0;i<amount;i++) {
			String temp_pass = "";
			for(int j=0;j<length;j++) {
				int rand = (int)(Math.random() * (127-33) ) + 33; // number between 33 - 127 of ASCII Table
				char letter = (char) rand;
				if(j==0) {
					temp_pass = Character.toString(letter);
				}
				else {
					temp_pass += Character.toString(letter);
				}	
			}
			passwords[i] = temp_pass;
		}
		
		return passwords;
	}
	
	public String reverseString(String a) {
		// FUNCTION:
		// Reverses a given string and returns it
		// Arguments: String type a
		// Returns: String type reverse
		String reverse="";
		
		for(int i=0;i<a.length();i++) {
			reverse = reverse + a.charAt(a.length()-i-1);
		}
		
		return reverse;
	}
	
	public int isAnagram(String a, String b) {
		// FUNCTION:
		// Checks if String a is an anagram of String b
		// Arguments: String type a and String type b
		// Returns 1 if a is an anagram of b 
		//         0 if a in not an anagram of b
		if(a.length()!=b.length()) {
			return 0;
		}
		else {
			int countEquals = 0;
			String temp_a = a.toUpperCase();
			String temp_b = b.toUpperCase();
			for(int i=0;i<a.length();i++) {
				int count_double_letter = 0;	// flag to check if a letter appears more than one time in the string
				for(int j=0;j<b.length();j++) {
					if(temp_a.charAt(i)==temp_b.charAt(j)) {
						if(count_double_letter==0) {
							countEquals = countEquals + 1;
							count_double_letter = 1;
						}
					}
				}
			}
			if(countEquals==a.length()) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
	
	public int isPalindrome(String a) {
		// FUNCTION:
		// Checks if String a is a palindrome
		// Argument: String type a
		// Returns 1 if a is a palindrome
		int reps = a.length()/2;	// repetitions counter(we only need to loop to the middle of the word)
		String temp_string = a.toUpperCase();
		for( int i=0;i<reps;i++ ) {
			if( temp_string.charAt(i)!=temp_string.charAt(a.length()-i-1) ) {
				return 0;
			}
		}
		return 1;
	}
	
	public int countVowelsOrConsonants(String a, int mode) {
		// FUNCTION:
		// Counts the number of vowels or consonants in a string
		// Argument: String type a, int mode -> 0 for vowels 
		//									 -> 1 for consonants
		// Returns the number of vowels or consonants
		if(mode==0) {
			int vowel_count = 0;
			String vowel_map = "eyuioa";
			String temp_a = a.toLowerCase();
			for(int i=0;i<a.length();i++) {
				for(int j=0;j<vowel_map.length();j++) {
					if(temp_a.charAt(i)==vowel_map.charAt(j)) {
						vowel_count = vowel_count +1;
					}
				}
			}
			return vowel_count;
		}
		else {
			int consonant_count = 0;
			String consonant_map = "qwrtpsdfghjklzxcvbnm";
			String temp_a = a.toLowerCase();
			for(int i=0;i<a.length();i++) {
				for(int j=0;j<consonant_map.length();j++) {
					if(temp_a.charAt(i)==consonant_map.charAt(j)) {
						consonant_count = consonant_count +1;
					}
				}
			}
			return consonant_count;
		}
	}
	
	public String ceasarsCipher(String a, int n) {
		String result = "";
		for(int i=0;i<a.length();i++) {
			char cipher = (char) (a.charAt(i) + n);
			result = result + cipher;
		}
		return result;
	}
	
	public int[] countChars(String a) {
		int letters = 0;
		int numbers = 0;
		int spchar = 0;
		int spaces = 0;
		for(int i=0;i<a.length();i++) {
			if( Character.isLetter( a.charAt(i) ) ){
				letters ++;
			}
			else if( Character.isDigit( a.charAt(i) ) ) {
				numbers ++;
			}
			else if( Character.isSpaceChar( a.charAt(i) ) ) {
				spaces ++;
			}
			else {
				spchar ++;
			}
		}
		int[] arr = {letters,numbers,spchar,spaces};
		return arr;
	}
	
	public static void main(String[] args) {
		StringFunctions sf = new StringFunctions();
		int run_flag = 1;
		while(run_flag == 1) {
			System.out.println("-----------User Menu--------------\n");
			System.out.println("\t1. Reverse String\n");
			System.out.println("\t2. Check for Anagrams\n");
			System.out.println("\t3. Check for Palindrome\n");
			System.out.println("\t4. Count Vowels(0) or Consonants(1)\n");
			System.out.println("\t5. Ceasar's Cipher\n");
			System.out.println("\t6. Count the letters/spaces/numbers/special characters of a string\n");
			System.out.println("\t7. Generate passwords\n");
			System.out.println("\t8. End Application\n");
			System.out.println("----------------------------------\n");
			System.out.println("Select a Function: \n");
			Scanner myObj = new Scanner(System.in);
			int option = myObj.nextInt();
			if(option == 1) {
				System.out.println("Type the string you want to reverse: ");
				String s = myObj.nextLine(); 	// remove the \n from the previous input
				s = myObj.nextLine();
				String reverse = sf.reverseString(s);
				System.out.println("Type the initial string was: "+s+"\nAnd the reversed string is: "+reverse);
			}
			if(option == 2) {
				System.out.println("Type the strings you want to check if they are an anagram of each other\n");
				System.out.println("Type the 1st string: ");
				String s1 = myObj.nextLine();
				s1 = myObj.nextLine();
				System.out.println("Type the 2nd string: ");
				String s2 = myObj.nextLine();
				int result = sf.isAnagram(s1, s2);
				if(result==1) {
					System.out.println("The strings are an anagram of each other\n");
				}
				else {
					System.out.println("The strings are not an anagram of each other\n");
				}
			}
			if(option == 3) {
				System.out.println("Type the string you want to check if it is a palindrome: ");
				String s1 = myObj.nextLine();	// remove the \n from the previous input
				s1 = myObj.nextLine();
				int result = sf.isPalindrome(s1);
				if(result==1) {
					System.out.println("The string is a paindrome\n");
				}
				else {
					System.out.println("The string is not a palindrome\n");
				}
			}
			if(option == 4) {
				System.out.println("Type the string you want to count vowels or consonants: ");
				String s1 = myObj.nextLine();	// remove the \n from the previous input
				s1 = myObj.nextLine();
				System.out.println("Type 0 if you want to count vowels or 1 if you want to count consonants: ");
				int mode = myObj.nextInt();
				int result = sf.countVowelsOrConsonants(s1, mode);
				if(mode==0) {
					System.out.println("There were "+result+" vowels in the string\n");
				}
				else {
					System.out.println("There were "+result+" consonants in the string\n");
				}
			}
			if(option == 5) {
				System.out.println("Type the string you want to encrypt: ");
				String s1 = myObj.nextLine();	// remove the \n from the previous input
				s1 = myObj.nextLine();
				System.out.println("Type the step of the encryption: ");
				int step = myObj.nextInt();
				String result = sf.ceasarsCipher(s1, step);
				System.out.println("The initial string was: "+s1+" and the encrypted string is: "+result+"\n");
			}
			if(option == 6) {
				System.out.println("Type the string: ");
				String s1 = myObj.nextLine();	// remove the \n from the previous input
				s1 = myObj.nextLine();
				int[] arr = sf.countChars(s1);
				System.out.println("Number of letters: "+arr[0]+"\n");
				System.out.println("Number of digits: "+arr[1]+"\n");
				System.out.println("Number of special characters: "+arr[2]+"\n");
				System.out.println("Number of spaces: "+arr[3]+"\n");
			}
			if(option == 7) {
				System.out.println("Type the number of passwords to create: ");
				int amount = myObj.nextInt();
				System.out.println("Type the length of the passwords: ");
				int length = myObj.nextInt();
				String[] passwords = sf.createPassword(amount, length);
				System.out.println("The list of passwords is: ");
				for(int i=0;i<amount;i++) {
					System.out.println(passwords[i]);
				}
			}
			if(option == 8) {
				run_flag = 0;
				myObj.close();
			}
		}
	}

}
