//package declaration
package ch.nolix.core.testing.basetest;

//interface
public interface ILinePrinter {

  // method declaration
  void printEmptyLine();

  // method declaration
  void printErrorLine(final String errorLine);

  // method declaration
  void printErrorLines(final Iterable<String> errorLines);

  // method declaration
  void printInfoLine(final String infoLine);

  // method declaration
  void printInfoLines(final Iterable<String> infoLines);
}
