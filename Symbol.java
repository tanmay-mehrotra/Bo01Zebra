import java.util.*;

public class Symbol {
	String s;
	static Map<String,Symbol> table=new HashMap<String,Symbol>();
	public Symbol (String l) {
		s=l;
	}
	public String toString() { return s;}
	
	public static Symbol getSymbol(String n) {
		Symbol sym=table.get(n);
		if (sym==null)
		{
			sym=new Symbol(n);
			table.put(n,sym);
		}
		return sym;
		
	}
	
	public boolean equal(Object o) {
		if (o==null || !(o instanceof Symbol)) {
			return false;
		}
		else {
			Symbol sym=(Symbol)o;
			return sym.s.equals(this.s);
		}
	}

	
}
