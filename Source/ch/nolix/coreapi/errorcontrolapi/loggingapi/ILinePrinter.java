package ch.nolix.coreapi.errorcontrolapi.loggingapi;

public interface ILinePrinter {

  void printEmptyLine();

  void printErrorLine(final String errorLine);

  void printErrorLines(final Iterable<String> errorLines);

  void printInfoLine(final String infoLine);

  void printInfoLines(final Iterable<String> infoLines);
}
