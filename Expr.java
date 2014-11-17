import java.io.*;
import java.util.*;
abstract class Expr extends Node {
        protected int line;
       
        public Expr(int line) {
                this.line = line;
        }

	public abstract void accept(Visitor v);
	public abstract Type accept(TypeVisitor v);
        public int getLine() {
                return line;
        }

}

