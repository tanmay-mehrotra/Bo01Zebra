import java.io.*;
import java.util.*;
abstract class Literal extends Expr{

        protected int line;
       
        public Literal(int line1) {
                super(line1);
		line=line1;
        }

	public abstract void accept(Visitor v);
//	public abstract Type accept(TypeVisitor v);
        public int getLine() {
                return line;
        }


}
