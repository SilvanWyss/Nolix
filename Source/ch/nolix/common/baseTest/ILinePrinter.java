//package declaration
package ch.nolix.common.baseTest;

//own import
import ch.nolix.common.constants.StringCatalogue;

//interface
public interface ILinePrinter {
	
	//method
	public default void printEmptyLine() {
		printLine(StringCatalogue.EMPTY_STRING);
	}
	
	//method declaration
	public abstract void printLine(final String line);
	
	//method
	public default void printLines(final Iterable<String> lines) {
		lines.forEach(this::printLine);
	}
}
