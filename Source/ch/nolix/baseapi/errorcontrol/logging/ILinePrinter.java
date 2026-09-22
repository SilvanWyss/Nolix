/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.errorcontrol.logging;

/**
 * @author Silvan Wyss
 */
public interface ILinePrinter {
  void printEmptyLine();

  void printErrorLine(String errorLine);

  void printErrorLines(Iterable<String> errorLines);

  void printInfoLine(String infoLine);

  void printInfoLines(Iterable<String> infoLines);
}
