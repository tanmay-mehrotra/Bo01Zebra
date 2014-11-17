import java.io.*;
import java.util.*;
abstract class StatementNode extends Node{
	public abstract void accept(Visitor v);
	public abstract Type accept(TypeVisitor v);
}

