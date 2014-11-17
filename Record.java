import java.util.*;

public class Record {
	Type t;
	Symbol sym;
	int size;
	
     public Record() {  
	           
        }
       
        public Record(Symbol symbol, Type type,int size) {              
                this.sym = symbol;
                this.t = type;
		this.size=size;
        }
       
        public Symbol getSymbol() {
                return sym;
        }
        public void setSymbol(Symbol symbol) {
                this.sym = symbol;
        }
        public Type getType() {
                return t;
        }
        public void setType(Type type) {
                this.t = type;
        }

        public int getSize() {
                return size;
        }
        public void setSize(int size) {
                this.size = size;
        }

}
