//package declaration
package ch.nolix.common.baseTest;

//interface
public interface ILinePrinter {
	
	//method
	public abstract void printEmptyLine();
	
	public abstract void printErrorLine(final String errorLine);
	
	//method declaration
	public abstract void printInfoLine(final String infoLine);
	
	//method
	public default void printErrorLines(final Iterable<String> errorLines) {
		errorLines.forEach(this::printErrorLine);
	}
	
	//method
	public default void printInfoLines(final Iterable<String> infoLines) {
		infoLines.forEach(this::printInfoLine);
	}
}
