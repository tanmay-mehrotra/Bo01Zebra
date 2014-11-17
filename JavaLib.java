//package javaFunctions;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class JavaLib {
	
	/*static String bexp="";
	
	public static void main(String[] args) {
		
		
		String a[]={"I","M","D"};
		TruthTable t=new TruthTable("( a and b )");
		t.buildTT();
		System.out.println(t);
		Kmap k =new Kmap(t);
		k.buildKmap();
		System.out.println(k);
		
		
		

	} */

		
	
	
	
	/*********************************
	 * BoolZebra--input() function
	 **********************************/
	@SuppressWarnings("resource")
	public static String  input()
	{  
		System.out.println("Enter Bexp");
		Scanner sc = new Scanner(System.in);
		String inputStr=sc.nextLine();
		return inputStr;
	}
	
	
	
	
	/*********************************
	 * BoolZebra--bexpValidate() 
	 * This validates the input Bexp
	 **********************************/	
	public static boolean  bexpValidate(String bexp)
	{
		if(!isBalanced(bexp)){
			System.out.println("Paranthesis are not balanced");
			return false;
		}
		return true;
	}

	
	

	/*************************************************
	 * checking balanced paran-helper function for validate()
	 **************************************************/
	public  static boolean isBalanced(String expression)
	{  
		final char LEFT_NORMAL  = '(';
		final char RIGHT_NORMAL = ')';
		Stack<Character> store = new Stack<Character>( ); 
		int i;                             
		boolean failed = false;            

		for (i = 0; !failed && (i < expression.length( )); i++)
		{
			switch (expression.charAt(i))
			{
			case LEFT_NORMAL:
				store.push(expression.charAt(i));
				break;

			case RIGHT_NORMAL:
				if (store.isEmpty( ) || (store.pop( ) != LEFT_NORMAL))
					failed = true;
				break;
				
			}//closing switch
		}	//closing for loop      

		return (store.isEmpty( ) && !failed);
		
	}


	
	/*********************************
	 * BoolZebra--countVariables() 
	 **********************************/	
	public  static int countVariables(String bexp)
	{
		Set <String> noOfVar =new LinkedHashSet<String>();
		String splitBexp[]=bexp.split(" ");
		for(String i:splitBexp){
			if(i.matches("[a-zA-Z]")){
				noOfVar.add(i);
			}   	
		}
		return noOfVar.size();
	}

	
	/****************************************
	 * Boolzebra nameOfVariables(bexp)--return 
	 * names of variables in a Bexp
	 *****************************************/
	public static String[] nameOfVariables(String bexp){

		Set <String> noOfVar =new LinkedHashSet<String>();
		String splitBexp[]=bexp.split(" ");
		
		for(String i:splitBexp){
			if(i.matches("[a-zA-Z]")){
				noOfVar.add(i);
			}   	
		}
		String nameOfVar[]=noOfVar.toArray(new String[noOfVar.size()]);
		return nameOfVar;
	}

	
	/***********************************************
	 *Helper function
	 * replaceVarWithValues--replace variables in a bexp with 
	 * 0's and 1's and as java doesnt not consider 1 as true and 
	 * 0 as false so we replace 0 with (0>1) and 1 with (1>0). 
	 **************************************************/
	
 /*****************************************
  * compare function bit	
  b=	compare(k_user, b_exp);	
  *****************************************/
	public static boolean compare1(Kmap k_user, String bexp){
		
	TruthTable t= new TruthTable(bexp);
	t=t.buildTT();
	
	Kmap k_comp= new Kmap(t);
	k_comp=k_comp.buildKmap();
	
	int flag=0;
	String wrong[][]=new String[k_user.arrayKmap.length][k_user.arrayKmap[0].length];
	
	int k_user_row=k_user.rowSize();
	int k_user_col=k_user.columnSize();
	
	int k_comp_row=k_comp.rowSize();
	int k_comp_col=k_comp.columnSize();
	
	//System.out.println(k_user_row+""+k_user_col+""+k_comp_row+""+k_comp_col);
	
	if(k_user_row!=k_comp_row){
		System.out.println("Incomptaible dimension..cannot compare");
		  return false; 
		}
	
	else if(k_user_col!=k_comp_col){
		System.out.println("Incompatible dimension...cannot compare");
		   return false;
		}
	
	else {
		
		for(int r=0;r<k_user_row;r++){
			for(int c=0;c<k_user_col;c++){
				
				if (!(k_user.arrayKmap[r][c]==k_comp.arrayKmap[r][c])){
					System.out.println("Wrong value entered by user at [" +r+ "]"+"["+c+"]+of the Kmap");
					wrong[r][c]="(w)";
					flag=1;
				}
				else
					wrong[r][c]="";
			}	
		}//end of outer loop
	}//end of else
	k_user.setWrong(wrong);
	k_user.displayKmap();
	
	if (flag==1)
	    return false;
	else 
		return true;
 }

	 /*****************************************
	  * compare function bit	
	  b=	compare(TruthTable, b_exp);	
	  *****************************************/
	public static boolean compare1(TruthTable tt, String bexp){
		
		
		int flag=0;
		String wrong[]=new String[tt.rows];
		TruthTable t_comp=	new TruthTable(bexp);
		t_comp=t_comp.buildTT();
		
		boolean result[]=t_comp.getResultColumn();
		
		if(tt.ttsize()!=t_comp.ttsize()){
			System.out.println("Incampatible Size..cannot comapre");
			return false;
		}
		
		for(int i=0;i<result.length;i++){
			if(result[i]!=tt.getResultColumn()[i]){
				wrong[i]="(w)"; 
				flag=1;
			}
			else
				wrong[i]="";
		}
		
		tt.setWrong(wrong);
		tt.displayTT();
		
		if(flag==1)
			return false;
		else
			return true;
		
	}
		
	
	
	public static String replaceVarWithValues(String[] splitBexp,boolean a[]){

		Set <String> noOfVar =new LinkedHashSet<String>();

		for(String i:splitBexp){
			if(i.matches("[a-zA-Z]")){
				noOfVar.add(i);
			}   	
		}


		String varArray[]=noOfVar.toArray(new String[noOfVar.size()]);

		for(int i=0;i<varArray.length;i++){

			for(int j=0;j<splitBexp.length;j++){

				if(splitBexp[j].equalsIgnoreCase(varArray[i])){

					if(a[i]==true){
						splitBexp[j]="( 1>0 )";
					}
					else if (a[i]==false){
						splitBexp[j]="( 0>1 )";	
					}

				}//outer if

			}//inner for

		}//outer for


		String convertedBexp="";

		for(int i=0;i<splitBexp.length;i++){
			convertedBexp+=splitBexp[i]+" ";
		}
		return convertedBexp;	
	}

	
	/*******************************************
	 * calcResult--helper function
	 * @return result for truthtable
	 *********************************************/
	public static boolean calcResult(String bexp, boolean a[]){

		String splitBexp[]=bexp.split(" ");
		
		
		String convertedBexp=replaceVarWithValues(splitBexp, a);
		
		boolean b=evaluateBexp.evaluate(convertedBexp);
		
	return b;
	}

 /***********************************
  * BoolZebra--output function
  ********************************/
	public void output(String s){
		System.out.println(s);
	}
	
}

