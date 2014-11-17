/****************************************
 * Kmap Library 
 * The library contains java function supporting 
 * creation, Graphic display and  flipping of kmaps.
 * The Library is created to support  BoolZebra 
 * programming langauge.
 * 
 * Author: Team BoO1Zebra
 *               (ds3161,sc3545,nr2445,rp2614,tm2635)
 *               Columbia University
 *               NY-10027
 * ******************************************/
//package javaFunctions;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Kmap {
	
	boolean[][]arrayKmap;
	int rows;
	int column;
	boolean flipVertical_1=false;
	boolean flipHorizontal_1=false;
	String kmaptype;
	String bexp=""; 
	String wrong[][];
	boolean[] resultColumn; 
	int num_vars;
	
	
	public void fillKmap(int r,int c,boolean value){
		  arrayKmap[r][c]=value; 
	   }	
	
	
	public int rowSize(){
		return rows;
	}
	
	public int columnSize(){
		return column;
	}
	
	
	 public Kmap(){}
	 
	 //normal constructor taking r and c 
	 public Kmap(int r,int c){
		
		 if(r>4 || r<2 || c<2 || c>4){
				
				System.out.println("No. of variables are worng. Can be either 2 ,3 or 4");
				System.out.println("Exiting System....");
				System.exit(0);
				
			}
		 
		 if(r==2 && c==2)
			 num_vars=2;
		 if(r==2 && c==4)
			 num_vars=3;
		 if(r==4 && c==4)
			 num_vars=4;
		 
		 
		 
		 rows=r;
		 column=c;
		 arrayKmap=new boolean[r][c];
		 wrong=new String[rows][column];
		 
		 for(int i=0;i<r;i++){
			 for(int j=0;j<c;j++){
				 wrong[i][j]="";
			 }
		 }
		 
	 }
	 
	 
	 //constructor accepting TT as an agrument
	 public Kmap(TruthTable t){
		 
		 int noofvar=t.num_vars;
		 num_vars=noofvar;
		 
		 if(noofvar>4 || noofvar<2){
				
				System.out.println("No. of variables are worng. Can be either 2 ,3 or 4");
				System.out.println("Exiting System....");
				System.exit(0);
				
			}
		 
		 
		 //System.out.println("t.bexp"+t.bexp);
		 
		 this.bexp=t.bexp;
		 
		 switch(noofvar){
			case 2:
				arrayKmap = new boolean[2][2];
				rows = 2;
				column = 2;
				break;
			case 3:
				arrayKmap = new boolean[2][4];
				rows = 2;
				column = 4;
				break;
			case 4:
				arrayKmap = new boolean[4][4];
				rows = 4;
				column = 4;
				break;
			}	
		    
		    wrong=new String[rows][column];
		    resultColumn = t.getResultColumn();
		    
		    for(int i=0;i<rows;i++){
				 for(int j=0;j<column;j++){
					 wrong[i][j]="";
				 }
			 }
		    
	 }
	 
	 
	 
	 
	 //constructor accepting bexp as an agrument
	 public Kmap(String bexp){

       int noofvar=JavaLib.countVariables(bexp);
       num_vars=noofvar;
       if(noofvar>4 || noofvar<2){
   		
			System.out.println("No. of variables are worng. Can be either 2 ,3 or 4");
			System.out.println("Exiting System....");
			System.exit(0);
			
		}
       
        this.bexp=bexp;
	  
		 switch(noofvar){
			case 2:
				arrayKmap = new boolean[2][2];
				rows = 2;
				column = 2;
				break;
			case 3:
				arrayKmap = new boolean[2][4];
				rows = 2;
				column = 4;
				break;
			case 4:
				arrayKmap = new boolean[4][4];
				rows = 4;
				column = 4;
				break;
			}	
		 
		  TruthTable t= new TruthTable(bexp);
		  t=t.buildTT();
		 
		    resultColumn = t.getResultColumn();
		    wrong=new String[rows][column];
		    
		    for(int i=0;i<rows;i++){
				 for(int j=0;j<column;j++){
					 wrong[i][j]="";
				 }
			 }
		 
	 }
	 
	 
	
   @SuppressWarnings("resource")
	public boolean inputResult() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter values for kmap");
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
	
	
	public  Kmap buildKmap()
	{
		Kmap k=null;
		
		int x = 0;
		for (int i = 0 ; i < rows ; i++)
		{
			for (int j = 0 ; j < column ; j++)
			{
				arrayKmap[i][j] = resultColumn[x++]; 
			}
		}
		System.out.println("no of var "+num_vars);
		
		if (num_vars != 2)
		{
			k = correctOrder1(this);
				
			if (num_vars == 4)
			{
				k = correctOrder2(this);		
			}
                   return k;
		}
		
		return this;
	}
	
	
	public  Kmap correctOrder1(Kmap k)
	{
		boolean[][] original = k.arrayKmap;
		for (int j = 0 ; j < k.rows ; j++)
		{
			boolean temp = original[j][k.column-2];
			original[j][k.column - 2] = original[j][k.column-1];
			original[j][k.column-1] = temp;
		}
		k.arrayKmap = original;
		return k;
	}
	
	
	public  Kmap correctOrder2(Kmap k)
	{
		boolean[][] original = k.arrayKmap;
		for (int j = 0 ; j < k.column ; j++)
		{
			boolean temp = original[k.rows-2][j];
			original[k.rows-2][j] = original[k.rows-1][j];
			original[k.rows-1][j] = temp;
		}
		k.arrayKmap = original;
		return k;
		
	}
	
	
	public void setWrong(String [][]wrong){
		this.wrong=wrong;
	}
	
	
	@Override
	public String toString(){
		displayKmap();
		return "";
	}
	
	
	
	public void displayKmap()
	{	
		
		if(bexp==null || bexp.equals(""))
		{
			if(rows==2 && column==2){
				bexp="( A and B )";
			}
			else if(rows==2 &&column==4){
			   bexp="( ( A and B ) or C )";
		   }
			else if (rows==4 && column==4){
				bexp="( ( A and B ) or ( C and D ) )";
			}		
		}
		
		//System.out.println("bexp "+ bexp);
		
		int countofvar=JavaLib.countVariables(bexp);
		String vararray[]=JavaLib.nameOfVariables(bexp);
		Object[][] data=new Object[arrayKmap.length][arrayKmap[0].length+1];
		int l=vararray.length;
		List<String> var=new LinkedList<String>();
	
	   if(flipVertical_1==true){	
		
		kmaptype="KMap_Vertical";
		
		if(countofvar==1){
			var.add("");
			var.add(vararray[0]+"'");
			
		}
		else if (countofvar==2){
			var.add("");var.add(vararray[l-1]+"'");var.add(vararray[l-1]);
			data[0][0]=vararray[0]+"'";data[1][0]=vararray[0];
		}
		
		else if(countofvar==3 ){
			var.add("");var.add(vararray[l-2]+"'"+vararray[l-1]);var.add(vararray[l-2]+"'"+vararray[l-1]+"'");
			var.add(vararray[l-2]+vararray[l-1]+"'");var.add(vararray[l-2]+vararray[l-1]);
			data[0][0]=vararray[0]+"'";data[1][0]=vararray[0];
		}
		
		else if (countofvar==4){
			var.add("");var.add(vararray[l-2]+"'"+vararray[l-1]);var.add(vararray[l-2]+"'"+vararray[l-1]+"'");
			var.add(vararray[l-2]+vararray[l-1]+"'");var.add(vararray[l-2]+vararray[l-1]);
			data[0][0]=vararray[0]+"'"+vararray[1]+"'";data[1][0]=vararray[0]+"'"+vararray[1];
			data[2][0]=vararray[0]+vararray[1];data[3][0]=vararray[0]+vararray[1]+"'";
		
	    }
	}
	
	else if (flipHorizontal_1==true){
		
		kmaptype="KMap_Horizontal";
		
		
		if(countofvar==1){
			var.add("");
			var.add(vararray[0]+"'");
			
		}
		else if (countofvar==2){
			var.add("");var.add(vararray[l-1]+"'");var.add(vararray[l-1]);
			data[0][0]=vararray[0]+"'";data[1][0]=vararray[0];
		}
		
		else if(countofvar==3 ){
			var.add("");var.add(vararray[l-2]+"'"+vararray[l-1]+"'");var.add(vararray[l-2]+"'"+vararray[l-1]);
			var.add(vararray[l-2]+vararray[l-1]);var.add(vararray[l-2]+vararray[l-1]+"'");
			data[0][0]=vararray[0]+"'";data[1][0]=vararray[0];
		}
		
		else if (countofvar==4){
			var.add("");var.add(vararray[l-2]+"'"+vararray[l-1]+"'");var.add(vararray[l-2]+"'"+vararray[l-1]);
			var.add(vararray[l-2]+vararray[l-1]);var.add(vararray[l-2]+vararray[l-1]+"'");
			data[0][0]=vararray[0]+vararray[1]+"'";data[1][0]=vararray[0]+"'"+vararray[1]+"'";
			data[2][0]=vararray[0]+vararray[1];data[3][0]=vararray[0]+"'"+vararray[1];
	    }
		
	}
	
	else{
		
		kmaptype="KMap";
		
		if(countofvar==1){
			var.add("");
			var.add(vararray[0]+"'");		
		}
		else if (countofvar==2){
			var.add("");var.add(vararray[l-1]+"'");var.add(vararray[l-1]);
			data[0][0]=vararray[0]+"'";data[1][0]=vararray[0];
		}
		
		else if(countofvar==3 ){
			var.add("");var.add(vararray[l-2]+"'"+vararray[l-1]+"'");var.add(vararray[l-2]+"'"+vararray[l-1]);
			var.add(vararray[l-2]+vararray[l-1]);var.add(vararray[l-2]+vararray[l-1]+"'");
			data[0][0]=vararray[0]+"'";data[1][0]=vararray[0];
		}
		
		else if (countofvar==4){
			var.add("");var.add(vararray[l-2]+"'"+vararray[l-1]+"'");var.add(vararray[l-2]+"'"+vararray[l-1]);
			var.add(vararray[l-2]+vararray[l-1]);var.add(vararray[l-2]+vararray[l-1]+"'");
			data[0][0]=vararray[0]+"'"+vararray[1]+"'";data[1][0]=vararray[0]+"'"+vararray[1];
			data[2][0]=vararray[0]+vararray[1];data[3][0]=vararray[0]+vararray[1]+"'";
		}
		
	 }	

		String variables[]=new String[var.size()];
	    var.toArray(variables);
		
		for (int i = 0 ; i < rows ; i++)
		{   	
			for (int j = 1 ; j < column+1 ; j++)
			{
				data[i][j]=""+arrayKmap[i][j-1]+wrong[i][j-1]; 
			}
		}
		
		
		KMapFrame.funct(variables, data, kmaptype);
		
	}
	
	
	
	
	
	public Kmap flipVertical()
	{  
		flipVertical_1=true;
		
		boolean[][] original = arrayKmap;
		for (int j = 0 ; j < rows; j++)
		{
			boolean temp1 = original[j][0];
			boolean temp2 = original[j][column-2];
			
			original[j][0] = original[j][1];
			original[j][1] = temp1;
			if (column>2)
			{
				original[j][column-2] = original[j][column-1];
				original[j][column-1] = temp2;
			}
	
			
		}
		return this;
	}
	
	public Kmap flipHorizontal()
	{  
		
		flipHorizontal_1=true;
		boolean[][] original = arrayKmap;
		for (int j = 0 ; j < column ; j++)
		{
			boolean temp1 = original[0][j];
			boolean temp2 = original[rows-1][j];
			
			original[0][j] = original[1][j];
			original[1][j] = temp1;
			
			if (rows>2)
			{
				original[rows-1][j] = original[rows-2][j];
				original[rows-2][j] = temp2;
			}
		
		}
		return this;
		
	}
	
}
