/******************************************
 * TruthTable  Library 
 * The library contains java function supporting 
 * creation, Graphic display of TruthTables.
 * The Library is created to support  BoolZebra 
 * programming langauge.
 * 
 * Author: Team BoO1Zebra
 *               (ds3161,sc3545,nr2445,rp2614,tm2635)
 *               Columbia University
 *               NY-10027
 * ******************************************/
//package javaFunctions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


import javax.swing.JFrame;

public class TruthTable {

	boolean [][] truthTable;
	int num_vars;
	int rows;
	int column;
	String bexp;
	String wrong[];



	public TruthTable(String bexp){
        
		this(JavaLib.countVariables(bexp));
		this.bexp=bexp;
		wrong=new String[rows];
		for(int i=0;i<rows;i++){
			wrong[i]="";
		}
	}



	public TruthTable (int n)
	{   
		
		if(n>4 || n<2){
		
			System.out.println("No. of variables are worng. Can be either 2 ,3 or 4");
			System.out.println("Exiting System....");
			System.exit(0);
			
		}
		
		num_vars=n;
		double drow = Math.pow(2.0, n);
		column = n + 1;
		rows = (int)drow;
		truthTable = new boolean[rows][column];
		ArrayList<String> values = new ArrayList<String>();

		for (int i = 0 ; i != (1<<n) ; i++) 
		{

			String s = Integer.toBinaryString(i);
			while (s.length() != n)  {
				s = '0'+s;
			}

			values.add(s);
		}


		Iterator<String> iterate = values.iterator();

		for (int i = 0 ; i < rows ; i++)
		{
			String temp = iterate.next()+"";
			for (int j = 0 ; j < column-1 ; j++)
			{
				if(temp.charAt(j) == '0')
					truthTable[i][j] = false;
				else if(temp.charAt(j) == '1')
					truthTable[i][j] = true;

			}

		}

		wrong=new String[rows];
		for(int i=0;i<rows;i++){
			wrong[i]="";
		}
	}


	public void setWrong(String []wrong){
		this.wrong=wrong;
	}



	@Override
	public String toString(){
		displayTT();
		return "";
	}


	public boolean[] getResultColumn()
	{
		boolean[] ans = new boolean[rows];
		for (int i = 0 ; i < rows ; i++)
		{
			ans[i] = truthTable[i][column-1];
		}
		return ans;
	}



	public void fillResult(int i){
		truthTable[i][column-1] = inputResult();
	}


	public int ttsize(){	
		return rows;
	}


	@SuppressWarnings("resource")
	public boolean inputResult() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter bit for the Result Column");
		String inp = sc.next();
		if (inp.equalsIgnoreCase("T") && inp.equalsIgnoreCase("F"))
		{
			System.out.println("Wrong input");
			System.exit(0);
		}
		if (inp.equalsIgnoreCase("T"))
			return true;
		else 
			return false;
	}


	
	public void setSingleResultColumn(int index, boolean value) 
    { 
     truthTable[index][column-1] = value;
    }



	/********************************************
	 * createTTfromBexp--takes bexp as an input and 
	 * return the resulting TT
	 *********************************************/
	public TruthTable buildTT(){

		for (int i = 0 ; i < rows ; i++)
		{
			truthTable[i][column-1] = JavaLib.calcResult(bexp, truthTable[i]);
		}
		return this;		 
	}



	public void displayTT()
	{	

		int m=rows;
		int n=column;

		if(bexp==null || bexp.equals(""))
		{
			if(n==3){
				bexp="( A and B )";
			}
			else if(n==4){
				bexp="( ( A and B ) or C )";
			}
			else if (n==5){
				bexp="( ( A and B ) or ( C and D ) )";
			}
		}

		String variables[]=JavaLib.nameOfVariables(bexp);
		String var[]=new String[variables.length+1];

		for(int i=0;i<variables.length;i++){
			var[i]=variables[i];
		}
		var[var.length-1]="Results";

		Object[][] data=new Object[m][n];

		for (int i = 0 ; i < m ; i++)
		{
			for (int j = 0 ; j < n ; j++)
			{
				data[i][j]=""+truthTable[i][j]+wrong[i]; 
			}
		}

		TruthTableFrame gui=new TruthTableFrame(var,data);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.setTitle("TruthTable");

	}



	public TruthTable atTheRate( boolean val)
	{
		ArrayList<Integer>indexes = new ArrayList<Integer>(); 
		boolean[] result = getResultColumn();
		for (int i = 0 ; i < rows ; i ++) {
			if(result[i] == val)
				indexes.add(i);
		}
		TruthTable ans = new TruthTable(column-1);
		//System.out.println("size of index "+indexes.size());

		boolean[][] arrayAns = new boolean[indexes.size()][column];

		ans.rows = indexes.size();
		ans.column=column;
		ans.bexp=bexp;
		Iterator<Integer> iterate = indexes.iterator();

		for (int i  = 0 ; i < indexes.size() ; i++)
		{
			int inx = iterate.next();
			for (int j = 0 ; j < column ; j ++)
			{
				//System.out.println(inx);
				//System.out.println(truthTable[inx][j]);
				arrayAns[i][j] = truthTable[inx][j];
			}
		}
		ans.truthTable = arrayAns;
		//printing the formatted table
		/*for(int i=0;i<ans.rows;i++){
			for(int j=0;j<ans.column;j++){
				System.out.print(ans.truthTable[i][j]);
			}
			System.out.println();
		}

		System.out.println("value of rows "+ans.rows);
		System.out.println("value of col "+ans.column);*/
		return ans;		

	}




	public String dotOperator(String [] vars)
	{

		/*List<String> variables= new LinkedList<String>();
		String vars[];

		if(bexp==null || bexp==""){
			if(column==3){
				variables.add("a");variables.add("b");
			}
			else if(column==4){
				variables.add("a");variables.add("b");variables.add("c");
			}
			else if(column==5){
				variables.add("a");variables.add("b");variables.add("c");variables.add("d");
			}

			vars=new String[variables.size()];
			variables.toArray(vars);
		}
		else {
			vars=JavaLib.nameOfVariables(bexp);
		}*/

		if (column-1 != vars.length)
		{
			System.out.println("Incorrect use of dot operator");
			System.exit(0);
		}

		StringBuilder minterm = new StringBuilder( "");
		StringBuilder maxterm=new StringBuilder( "");
		String result=new String( "");

		for (int i = 0 ; i < rows ; i++)
		{
		
			if(truthTable[i][column-1]==true)
			{

				for (int j = 0 ; j < column-1 ; j++)
				{	
					if (truthTable[i][j] == true)
						minterm .append(vars[j]+" ");
					else
						minterm .append( vars[j]+"\' ");
				}
             minterm.append(" + ");
             
			}//outer if

			else
			{  
				maxterm.append("( ");
				
				for (int j = 0 ; j < column-1 ; j++)
				{	

					if(j!=column-2){

						if (truthTable[i][j] == false)
							maxterm.append(vars[j]+" + ");
						else
							maxterm.append(vars[j]+"\' + ");
					}
					else{

						if (truthTable[i][j] == false)
							maxterm.append(vars[j]);
						else
							maxterm.append(vars[j]+"\'");

					}//end of inner else

				}	//end of for
			maxterm.append(" ) . ");
			
			}//end of outer else
			
		}//end of outer for
	
		//remove the last +
		
	if(minterm.length()==0){
		minterm=new StringBuilder("No Minterms");
	}else{	
		if(minterm.charAt(minterm.length()-2)=='+')
			minterm.setCharAt(minterm.length()-2,' ');
	}
	if(maxterm.length()==0){
		maxterm=new StringBuilder("No Maxterm");
	}
	else{
		if(maxterm.charAt(maxterm.length()-2)=='.')
			maxterm.setCharAt(maxterm.length()-2,' ');
	}
		result="Sum of product : "+minterm+"\n"+"Product of Sum : "+maxterm;
		
		return result;
		
	}//end function
	
}//end of class
