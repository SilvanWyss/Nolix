//package declaration
package ch.nolix.common.testing.basetest;

//interface
public interface ILinePrinter {
	
	//method declaration
	void printEmptyLine();
	
	//method declaration
	void printErrorLine(final String errorLine);
	
	//method
	default void printErrorLines(final Iterable<String> errorLines) {
		errorLines.forEach(this::printErrorLine);
	}
	
	//method declaration
	void printInfoLine(final String infoLine);
	
	//method
	default void printInfoLines(final Iterable<String> infoLines) {
		infoLines.forEach(this::printInfoLine);
	}
}
