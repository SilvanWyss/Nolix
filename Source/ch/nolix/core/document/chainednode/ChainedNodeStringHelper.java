/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.document.chainednode;

import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;

/**
 * @author Silvan Wyss
 */
public final class ChainedNodeStringHelper {
  private ChainedNodeStringHelper() {
  }

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
