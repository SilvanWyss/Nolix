/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.errorcontrol.logging;

/**
 * @author Silvan Wyss
 */
public interface ILinePrinter {
  /**
   * Prints an empty line.
   */
  void printEmptyLine();

  /**
   * Prints the given errorLine.
   * 
   * @param errorLine
   */
  void printErrorLine(String errorLine);

  /**
   * Prints the given errorLines.
   * 
   * @param errorLines
   */
  void printErrorLines(Iterable<String> errorLines);

  /**
   * Prints the given infoLine.
   * 
   * @param infoLine
   */
  void printInfoLine(String infoLine);

  /**
   * Prints the given infoLines.
   * 
   * @param infoLines
   */
  void printInfoLines(Iterable<String> infoLines);
}
