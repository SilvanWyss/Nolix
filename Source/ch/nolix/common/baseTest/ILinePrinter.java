//package declaration
package ch.nolix.common.baseTest;

//interface
public interface ILinePrinter {
	
	//method declaration
	public abstract void printEmptyLine();
	
	//method declaration
	public abstract void printErrorLine(final String errorLine);
	
	//method
	public default void printErrorLines(final Iterable<String> errorLines) {
		errorLines.forEach(this::printErrorLine);
	}
	
	//method declaration
	public abstract void printInfoLine(final String infoLine);
	
	//method
	public default void printInfoLines(final Iterable<String> infoLines) {
		infoLines.forEach(this::printInfoLine);
	}
}
