/****************************************************
This class provides Provides helper method evaluate(String s) 
*for evaluating Bexp for a single instance of truth table 
*
 * Author: Team BoO1Zebra
 *               (ds3161,sc3545,nr2445,rp2614,tm2635)
 *               Columbia University
 *               NY-10027
 * ******************************************************/
//package javaFunctions;
import java.util.Stack;
import java.util.Scanner;
import java.util.regex.Pattern;


public class evaluateBexp
{

	@SuppressWarnings("resource")
	public static boolean evaluate(String s) {
		
		s = s.replace(">=","G");
		s = s.replace("<=","L");
		s = s.replace("!=","N");
		s = s.replace("||","O");
		s = s.replace("&&","A");
		s = s.replace("==","E");
		s=s.replace(" and ", "A");
		s=s.replace(" AND ", "A");
		s=s.replace(" or ", "O");
		s=s.replace(" OR ", "O");
		s=s.replace("NAND", "K");
		s=s.replace("nand", "K");
		s=s.replace(" XOR ", "X");
		s=s.replace(" xor ", "X");
		s=s.replace(" NOR ", "M");
		s=s.replace(" nor ", "M");
		s=s.replace("not", "!");
		s=s.replace("NOT", "!");
		s=s.replace(" XNOR ", "B");
		s=s.replace(" xnor ", "B");
		
		//System.out.println(s);
		Scanner input = new Scanner(s);
		Stack<Integer> numbers = new Stack<Integer>( );
		Stack<Character> operations = new Stack<Character>( );
		Stack<Boolean> booleans = new Stack<Boolean>( );
		
		String next;
		char first;

		while (input.hasNext( )) {
			if (input.hasNext(UNSIGNED_INT)) {
				next = input.findInLine(UNSIGNED_INT);
				numbers.push(new Integer(next));
			}
			else {
				next = input.findInLine(CHARACTER);
				first = next.charAt(0);

				switch (first) {
				case '>': // Greater Than
				case 'G': // >= Greater Than or Equal to.
				case '<': // Less Than
				case 'L': // Less Than or Equal to.
				case 'E': // == Equal
				case 'N': // != Not Equal
				case '!': // Not
				case 'A': // && And
				case 'O': // || Or
				case 'K':// NAND
				case 'M'://NOR
				case 'X'://XOR
				case 'B':	//XNOR
					operations.push(first);
					break;
				case ')': // Right parenthesis
					evaluateStackTops(numbers, operations,booleans);
					break;
				case '(': // Left parenthesis
					break;
				default : // Illegal character
					throw new IllegalArgumentException("Illegal character");
				}
			}
		}
		if (booleans.size( ) != 1)
			throw new IllegalArgumentException("Illegal input expression" );

		return booleans.pop( );
	}


	public static boolean evalBoolean(int oper1, int oper2, char bool) {
		switch (bool) {
		case '>': // Greater Than
			return (oper1 > oper2);
		case 'G': // >= Greater Than or Equal to.
			return (oper1 >= oper2);
		case '<': // Less Than
			return (oper1 < oper2);
		case 'L': // Less Than or Equal to.
			return (oper1 <= oper2);
		case 'E': // == Equal
			return (oper1 == oper2);
		case 'N': // != Not Equal
		{  
			return (oper1 != oper2);}
		default : // This statement should be unreachable since only the aforementioned values get sent to the method...
			throw new IllegalArgumentException("Illegal operation");
		}
	}

	public static void evaluateStackTops(Stack<Integer> numbers, Stack<Character> operations,Stack<Boolean> booleans) {
		int operand1, operand2;
		boolean bool1,bool2;
		
		if(operations.size()==0){
			return;
		}
		
		if (operations.size( ) < 1)
			throw new IllegalArgumentException("Illegal expression (Operations Stack Underflow)");
		char oper = operations.pop();

		switch (oper) {
		case '>': // Greater Than
		case 'G': // >= Greater Than or Equal to.
		case '<': // Less Than
		case 'L': // Less Than or Equal to.
		case 'E': // == Equal
		case 'N': // != Not Equal
			if (numbers.size( ) < 2)
				throw new IllegalArgumentException("Illegal expression (Numbers Stack Underflow)");
			operand2 = numbers.pop( );
			operand1 = numbers.pop( );
			booleans.push(evalBoolean(operand1,operand2,oper));
			break;

		case '!':
			if (booleans.size( ) < 1)
				throw new IllegalArgumentException("Illegal expression (Booleans Stack Underflow)");
			booleans.push (!(booleans.pop( )));
			break;

		case 'A':
		case 'O':
		case 'K':// NAND
		case 'M'://NOR
		case 'X'://XOR
		case 'B'://XNOR	
			if (booleans.size( ) < 2)
				throw new IllegalArgumentException("Illegal expression (Booleans Stack Underflow)");
			bool2 = booleans.pop( );
			bool1 = booleans.pop( );
			if (oper == 'A')
				booleans.push ( (bool1 && bool2));
			else if(oper=='O'){
				booleans.push ( (bool1 || bool2));}
			else if(oper=='K')
				booleans.push ( !(bool1 && bool2));
			else if(oper=='M')
				booleans.push ( !(bool1 || bool2));
			else if(oper=='X')
				booleans.push ( (bool1 && !bool2) || (!bool1 && bool2));
			else if(oper=='B')
				booleans.push (!(( (bool1 && !bool2) || (!bool1 && bool2))));
			break;
		default :
			throw new IllegalArgumentException("Illegal operation (Unknown Character)");
		}
	}

	public static final Pattern CHARACTER = Pattern.compile("\\S.*?");
	public static final Pattern UNSIGNED_INT = Pattern.compile("\\d+.*?");
}
