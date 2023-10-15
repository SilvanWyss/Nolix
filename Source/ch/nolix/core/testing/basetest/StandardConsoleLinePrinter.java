//package declaration
package ch.nolix.core.testing.basetest;

//class
/**
 * A {@link StandardConsoleLinePrinter} uses the standard output stream only and
 * not additionally the error output stream. The reason is that several output
 * stream cannot be synchronized. Several output streams are flushed independent
 * from each other. So the order of the output of several output streams in the
 * console is not guaranteed.
 * 
 * @date 2020-03-14
 * @author Silvan Wyss
 */
public final class StandardConsoleLinePrinter implements ILinePrinter {

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public void printEmptyLine() {
    System.out.println(); // NOSONAR: This is a logger.
    System.out.flush(); // NOSONAR: This is a logger.
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public void printErrorLine(final String errorLine) {
    System.out.println(errorLine); // NOSONAR: This is a logger.
    System.out.flush(); // NOSONAR: This is a logger.
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public void printErrorLines(final Iterable<String> errorLines) {
    errorLines.forEach(this::printErrorLine);
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public void printInfoLine(final String infoLine) {
    System.out.println(infoLine); // NOSONAR: This is a logger.
    System.out.flush(); // NOSONAR: This is a logger.
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public void printInfoLines(final Iterable<String> infoLines) {
    infoLines.forEach(this::printInfoLine);
  }
}
