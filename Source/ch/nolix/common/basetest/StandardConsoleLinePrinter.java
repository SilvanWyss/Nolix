//package declaration
package ch.nolix.common.basetest;

//class
/**
 * A {@link StandardConsoleLinePrinter} uses the standard output stream only
 * and not additionally the error output stream.
 * The reason is that several output stream cannot be synchronized.
 * Several output streams are flushed independent from each other.
 * So the order of the output of several output streams in the console is not guaranteed.
 * 
 * @month 2020-03
 * @author Silvan Wyss
 * @lines 40
 */
public final class StandardConsoleLinePrinter implements ILinePrinter {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printEmptyLine() {
		System.out.println();
		System.out.flush();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printErrorLine(final String errorLine) {
		System.out.println(errorLine);
		System.out.flush();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printInfoLine(final String infoLine) {
		System.out.println(infoLine);
		System.out.flush();
	}
}
