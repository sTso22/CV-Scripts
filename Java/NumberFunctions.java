import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class NumberFunctions {

	boolean isOdd(int num) {
		// FUNCTION:
		// Check if a number is odd
		if(num%2 != 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean isEven(int num) {
		// FUNCTION:
		// Check if a number is even
		if(num%2 == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean isPrime(int num) {
		// FUNCTION:
		// Check if a number is prime
		for(int i=1;i<=num;i++) {
			if(num%i == 0 && i!=1 && i!=num) {
				return false;
			}
		}
		return true;
	}
	
	int nthFibonacci(int n) {
		// FUNCTION:
		// Calculates the nth number of the Fibonacci sequence
		if(n == 0) {
			return 0;
		}
		else if(n == 1) {
			return 1;
		}
		else {
			return nthFibonacci(n-1)+nthFibonacci(n-2);
		}
	}
	
	void shapeCalculator() {
		// FUNCTION:
		// Calculates the surface area of 3 2-d shapes (Triangles/Squares/Circles)
		System.out.println("Choose a shape to calculate it's surface area:");
		System.out.println("1. Triangle");
		System.out.println("2. Square");
		System.out.println("3. Circle");
		Scanner sc = new Scanner(System.in);
		int shape = sc.nextInt();
		if(shape == 1) {	// triangle
			//surface using semiperimeter and heron's formula
			System.out.println("Enter the length of the 3 sides of the triangle: ");
			int[] sides = new int[3];
			for(int i=0;i<3;i++) {
				sides[i] = sc.nextInt();
			}
			int sum = sides[0]+sides[1]+sides[2];
			double area = Math.sqrt( sum*(sum-sides[0])*(sum-sides[1])*(sum-sides[2]) );
			System.out.println("The Surface Area of the triangle with sides:"+sides[0]+", "+sides[1]+", "+sides[2]+" is: "+area);
		}
		if(shape == 2) {	// square
			// surface using side*side
			System.out.println("Enter the length of the sides of the square: ");
			int side = sc.nextInt();
			int area = side*side;
			System.out.println("The Surface Area of the square with sides: "+side+" is: "+area);
		}
		if(shape == 3) {	// circle
			System.out.println("Enter the length of the radius of the circle: ");
			int rad = sc.nextInt();
			double area = Math.PI*rad*rad;
			System.out.println("the Surface Area of the circle with radius: "+rad+" is: "+area);
		}
	}
	
	void arrayMean(ArrayList<Double> arr) {
		// FUNCTION:
		// Calculates the mean of a given double array
		double mean = 0;
		for(int i=0;i<arr.size();i++) {
			mean += arr.get(i);
		}
		mean = mean/arr.size();
		System.out.println("The mean of the array is: "+mean);
	}
	
	void minCoins(double amount) {
		// FUNCTION:
		// Calculate the minimum number of coins that sum up to the given amount
		int[] coins = new int[8];
		// 0-> 2coin			// 4-> 0.1coin
		// 1-> 1coin			// 5-> 0.05coin
		// 2-> 0.5coin			// 6-> 0.02coin
		// 3-> 0.2coin			// 7-> 0.01coin
		for(int i=0;i<8;i++) {
			coins[i] = 0;
		}
		coins[0] = (int)amount/2;
		coins[1] = (int)amount%2;
		
		//------------- using bigdecimals to correct rounding error of double numbers ---------------------
		
		java.math.BigDecimal decimals = new java.math.BigDecimal(amount - (2*coins[0] + 1*coins[1]));
		decimals = decimals.round(new java.math.MathContext(2, RoundingMode.HALF_EVEN));
		decimals = decimals.multiply(new java.math.BigDecimal(100));
		//System.out.println(decimals);	// check if the decimal number is correct
		int compare_result = decimals.compareTo(new java.math.BigDecimal(50));
		if(compare_result == 0 || compare_result == 1) {	// decimals equal or greater than 50
			coins[2] = 1;
			decimals = decimals.subtract(new java.math.BigDecimal(50));	
		}
		java.math.BigDecimal temp = decimals;
		temp = temp.divide(new java.math.BigDecimal(20));	// calculate the .20 coins amount
		coins[3] = temp.intValue();
		temp = decimals;
		temp = temp.subtract(new java.math.BigDecimal(20*coins[3]));
		temp = temp.divide(new java.math.BigDecimal(10));	// calculate the .10 coins amount
		coins[4] = temp.intValue();
		
		temp = decimals;
		temp = temp.subtract(new java.math.BigDecimal(20*coins[3] + 10*coins[4]));	// get the .0x value
		compare_result = temp.compareTo(new java.math.BigDecimal(5));
		if(compare_result == 0 || compare_result == 1) {	// decimals equal or greater than 5
			coins[5] = 1;									
			temp = temp.subtract(new java.math.BigDecimal(5));	
		}
		coins[6] = temp.divide(new java.math.BigDecimal(2)).intValue();	// calculate the .02 coins amount
		compare_result = temp.compareTo(new java.math.BigDecimal(0));
		if(compare_result == 1) {	
			coins[7] = temp.subtract(new java.math.BigDecimal(2*coins[6])).intValue(); // calculate the .01 coins amount
		}
		
		/*-------------------- implementation with double numbers -------------------
		double decimals = amount;
		decimals = decimals - (2*coins[0] + 1*coins[1]);
		decimals = Math.ceil(decimals*100);
		
		if(decimals>=50) {
			coins[2] = 1;
			decimals = decimals - 50;	
		}
		coins[3] = (int)decimals/20;
		coins[4] = (int)(decimals- 20*coins[3])/10;
		
		double decimals2 = decimals - (20*coins[3] + 10*coins[4]);
		decimals2 = Math.ceil(decimals2);
		
		if(decimals2>=5) {
			coins[5] = 1;
			decimals2 = decimals2 - 5;
		}
		coins[6] = (int)decimals2/2;
		if(decimals2!=0) {
			coins[7] = (int)decimals2 - 2*coins[6];
		}
		----------------------------------------------------------------------------*/
		
		System.out.println("The minimum coins to make the: "+amount+" is:");
		System.out.println(coins[0]+" - 2 Euro coins");
		System.out.println(coins[1]+" - 1 Euro coins");
		System.out.println(coins[2]+" - 50 Euro Cent coins");
		System.out.println(coins[3]+" - 20 Euro Cent coins");
		System.out.println(coins[4]+" - 10 Euro Cent coins");
		System.out.println(coins[5]+" - 5 Euro Cent coins");
		System.out.println(coins[6]+" - 2 Euro Cent coins");
		System.out.println(coins[7]+" - 1 Euro Cent coins");
	}
	
	void formatSecondsToFullTime(int seconds) {
		// FUNCTION:
		// Convert seconds to Hours:Minutes:Seconds format
		// Arguments: int seconds
		
		int hours = seconds/60;
		int minutes = (seconds - hours*60)/60;
		int sec = seconds - hours*60 - minutes*60;
		System.out.println(seconds+" seconds are in Full-Time format(Hours:Minutes:Seconds): "+hours+":"+minutes+":"+sec);
	}
	
	String decimalToBinary(int num) {
		// FUNCTION:
		// Convert a number to binary 
		// Arguments: int number
		
		if(num == 0) {
			return "0";
		}
		String binary = "";
		while(num>0) {
			int remainder = num % 2;
			binary = binary + Integer.toString(remainder);
			num = num / 2;
		}
		String temp = "";	// reverse the binary string to get the correct binary number
		for(int i=0;i<binary.length();i++) {
			temp = temp + binary.charAt(binary.length()-i-1);
		}
		return temp;
	}
	
	void addBinaries(int a, int b) {
		// FUNCTION:
		// Add two numbers after they are converted to binary
		// Arguments: int a and b
		
		NumberFunctions nf1 = new NumberFunctions();
		int bin1 = Integer.parseInt(nf1.decimalToBinary(a));
		int bin2 = Integer.parseInt(nf1.decimalToBinary(b));
		
		System.out.println("The number "+a+" in binary is: "+bin1);
		System.out.println("The number "+b+" in binary is: "+bin2);
		
		String result = "";
		int carry = 0;
		int temp;
		while(bin1!=0 || bin2!=0) {	// get one by one digits from the right side of the numbers
			temp = (bin1 % 10 + bin2 % 10 + carry) % 2;
		    result = temp + result;

		    carry = (bin1 % 10 + bin2 % 10 + carry) / 2;
		    bin1 = bin1 / 10;
		    bin2 = bin2 / 10;
		}
		if (carry != 0) {
			result = carry + result;
		}
		System.out.println("The addition of the binary numbers is: "+result);
	}
	
	String decimalToHex(int number) {
		// FUNCTION:
		// Convert a decimal number to hexadecimal
		// Arguments: int number
		
		String result = "";
		int remainder;
		char hex_map[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		while(number>0)
	    {
	      remainder = number%16; 
	      result = hex_map[remainder] + result; 
	      number = number/16;
	    }
		
		return result;
	}
	
	void addHexNumbers(int a, int b) {
		// FUNCTION:
		// Addition of two hexadecimal numbers
		// Arguments: String a, b
		
		String hex1 = decimalToHex(a);
		String hex2 = decimalToHex(b);
		
		System.out.println("The number "+a+" in Hexadecimal format is: "+hex1);
		System.out.println("The number "+b+" in Hexadecimal format is: "+hex2);
		
		int h1l = hex1.length();	// length of the hex string
		int h2l = hex2.length();
		
		String result = "";
		int carry = 0;
		char hex_map[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
		int bin_map[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		
		if(h1l<h2l) {	// swap hex strings in order to always have the longest string to hex1
			String temp_hex = hex1;
			hex1 = hex2;
			hex2 = temp_hex;
			h1l = hex1.length();	// update lengths
			h2l = hex2.length();
		}
		int sum;	// variable to store the adding process
		int d1 = 0, d2 = 0;	// variables to store the decimal values 
		int index = 0;	// index of the hex_map
		int j = 0;	// index for the looping of the hex2 
		for (int i=h1l-1;i>=h1l-h2l;i--) {
			index = new String(hex_map).indexOf(hex1.charAt(i));	// find the index of the letter in the hex map
			d1 = bin_map[index];	// get the decimal value of the hexadecimal letter from the binary map

			index = new String(hex_map).indexOf(hex2.charAt(h2l-j-1));	// find the index of the letter in the hex map
			d2 = bin_map[index];	// get the decimal value of the hexadecimal letter from the binary map

			sum = d1 + d2 + carry;;
			carry = 0;
			if(sum>16) {
				carry = 1;
				sum = sum - 16;
			}
			result = hex_map[sum] + result;
			j++;
		}
		for(int i=h1l-h2l-1;i>=0;i--) {	// extra iteration when hex1 and hex2 haven't equal lengths and for extra carry 
			index = new String(hex_map).indexOf(hex1.charAt(i));	// find the index of the letter in the hex map
			d1 = bin_map[index];	// get the decimal value of the hexadecimal letter from the binary map
			sum = d1 + carry;
			carry = 0;
			if(sum>16) {
				carry = 1;
				sum = sum - 16;
			}
			result = hex_map[sum] + result;
			if(carry==1) {
				result = 1 + result;
			}
		}
		
		System.out.println("The result of the addition of "+hex2+" and "+hex1+" is: "+result);
	}
	
	public static void main(String[] args) {
		int app_flag = 0;
		Scanner sc = new Scanner(System.in);
		while(app_flag == 0) {
			System.out.println("---------User Menu----------");
			System.out.println("1. Check if a number is Odd");
			System.out.println("2. Check if a number is Even");
			System.out.println("3. Check if a number is a Prime Number");
			System.out.println("4. Find the nth Fibonacci number");
			System.out.println("5. Calculate the surface of a shape");
			System.out.println("6. Calculate the mean of an Array");
			System.out.println("7. Calculate the minimum amount of coins for a given float number");
			System.out.println("8. Convert seconds to Full-Time format");
			System.out.println("9. Binary Numbers Manipulation");
			System.out.println("10. Hexadecimal Numbers Manipulation");
			System.out.println("11. End Application");
			System.out.println("----------------------------");
			System.out.println("Select the function: ");

			int choice = sc.nextInt();
			
			NumberFunctions nf = new NumberFunctions();
			
			if(choice == 1) {
				System.out.println("Type the number you want to check if it is odd: ");
				int num = sc.nextInt();
				boolean result = nf.isOdd(num);
				if(result) {
					System.out.println(num+" is an odd number");
				}
				else {
					System.out.println(num+" is not an odd number");
				}
			}
			if(choice == 2) {
				System.out.println("Type the number you want to check if it is even: ");
				int num = sc.nextInt();
				boolean result = nf.isEven(num);
				if(result) {
					System.out.println(num+" is an even number");
				}
				else {
					System.out.println(num+" is not an even number");
				}
			}
			if(choice == 3) {
				System.out.println("Type the number you want to check if it is prime: ");
				int num = sc.nextInt();
				boolean result = nf.isPrime(num);
				if(result) {
					System.out.println(num+" is a prime number");
				}
				else {
					System.out.println(num+" is not a prime number");
				}
			}
			if(choice == 4) {
				System.out.println("Type the nth Fibonacci number you want to calculate: ");
				int num = sc.nextInt();
				int fib = nf.nthFibonacci(num);
				System.out.println("The "+num+"th Fibonacci number is: "+fib);
			}
			if(choice == 5) {
				nf.shapeCalculator();
			}
			if(choice == 6) {
				System.out.println("Enter the numbers of the array(Enter -1 to end insertion): ");
				int arr_flag = 0;
				ArrayList<Double> arr = new ArrayList<Double>();
				while (arr_flag == 0) {
					double temp = sc.nextDouble();
					if(temp != -1) {
						arr.add(temp);
					}
					else {
						arr_flag = 1;
					}
				}
				System.out.println("The array contains the values: ");
				for(int i=0;i<arr.size();i++) {
					System.out.print(arr.get(i)+" ");
				}
				System.out.println("\n");
				nf.arrayMean(arr);
			}
			if(choice == 7) {
				System.out.println("Enter the cash amount you want to calculate to coins: ");
				double amount = sc.nextDouble();
				nf.minCoins(amount);
			}
			if(choice == 8) {
				System.out.println("Enter the time in seconds: ");
				int sec = sc.nextInt();
				nf.formatSecondsToFullTime(sec);
			}
			if(choice == 9) {
				System.out.println("Enter the first number: ");
				int num = sc.nextInt();
				System.out.println("Enter the second number: ");
				int num2 = sc.nextInt();
				nf.addBinaries(num, num2);
			}
			if(choice == 10) {
				System.out.println("Enter the first number: ");
				int num = sc.nextInt();
				System.out.println("Enter the second number: ");
				int num2 = sc.nextInt();
				nf.addHexNumbers(num, num2);
			}
			if(choice == 11) {
				app_flag = 1;
				sc.close();
			}
		}
		
	}

}
