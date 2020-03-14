//package declaration
package ch.nolix.common.baseTest;

//class
public final class StandardConsoleLinePrinter implements ILinePrinter {
	
	//method
	@Override
	public void printEmptyLine() {
		System.out.println();
	}
	
	//method
	@Override
	public void printErrorLine(final String errorLine) {
		System.err.println(errorLine);
	}
	
	//method
	@Override
	public void printInfoLine(final String infoLine) {
		System.out.println(infoLine);
	}
}
