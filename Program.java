import java.io.*;
import java.util.*;
abstract class Program {
	public abstract void accept(Visitor v);
	public abstract Type accept(TypeVisitor v);
}

