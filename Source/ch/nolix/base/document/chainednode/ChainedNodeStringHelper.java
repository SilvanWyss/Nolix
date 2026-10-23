/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.chainednode;

import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;

/**
 * @author Silvan Wyss
 */
public final class ChainedNodeStringHelper {
  private ChainedNodeStringHelper() {
  }

  /**
   * @param startIndex
   * @param headerLength
   * @param taskAfterSetHeader
   * @return the next index from the given startIndex, headerLength and
   *         taskAfterSetHeader
   */
  public static int calculateNextIndexFromStartIndexAndHeaderLengthAndTaskAfterSetHeader(
    final int startIndex,
    final int headerLength,
    final TaskAfterSetHeader taskAfterSetHeader) {
    if (taskAfterSetHeader == TaskAfterSetHeader.MAP_CHILD_NODES_AND_POTENTIAL_NEXT_NODE
    || taskAfterSetHeader == TaskAfterSetHeader.MAP_NEXT_NODE) {
      return startIndex + headerLength + 1;
    }

    return startIndex + headerLength;
  }

  /**
   * @param string
   * @param startIndex
   * @return the length of the header and the task after setting the header of the
   *         chained node the given string represents from the given startIndex on
   */
  public static HeaderLengthAndTaskAfterSetHeaderParameter getHeaderLengthAndTaskAfterSetHeader(
    final String string,
    final int startIndex) {
    var nextIndex = startIndex;
    while (nextIndex < string.length()) {
      switch (string.charAt(nextIndex)) {
        case CharacterCatalog.OPEN_BRACKET:
          return new HeaderLengthAndTaskAfterSetHeaderParameter(
            nextIndex - startIndex,
            TaskAfterSetHeader.MAP_CHILD_NODES_AND_POTENTIAL_NEXT_NODE);
        case CharacterCatalog.COMMA:
          return new HeaderLengthAndTaskAfterSetHeaderParameter(nextIndex - startIndex, TaskAfterSetHeader.DO_NOTHING);
        case CharacterCatalog.CLOSED_BRACKET:
          return new HeaderLengthAndTaskAfterSetHeaderParameter(nextIndex - startIndex, TaskAfterSetHeader.DO_NOTHING);
        case CharacterCatalog.DOT:
          return new HeaderLengthAndTaskAfterSetHeaderParameter(nextIndex - startIndex,
            TaskAfterSetHeader.MAP_NEXT_NODE);
        default:
          nextIndex++;
      }
    }

    return new HeaderLengthAndTaskAfterSetHeaderParameter(nextIndex - startIndex, TaskAfterSetHeader.DO_NOTHING);
  }
}
