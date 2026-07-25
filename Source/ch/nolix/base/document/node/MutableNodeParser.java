/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.node;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;

/**
 * @author Silvan Wyss
 */
public final class MutableNodeParser {
  private MutableNodeParser() {
  }

  public static void resetMutableNodeFromString(final IMutableNode<?> mutableNode, final String string) {
    mutableNode.reset();

    if (setMutableNodeFromStringAndStartIndexAndGetEndIndex(mutableNode, string, 0) != string.length() - 1) {
      mutableNode.reset();

      throw UnrepresentingArgumentException.forArgumentAndType(string, ImmutableNode.class);
    }
  }

  private static int setMutableNodeFromStringAndStartIndexAndGetEndIndex(
    final IMutableNode<?> mutableNode,
    final String string,
    final int startIndex) {
    final var headerLength = getHeaderLengthFromStringAndStartIndex(string, startIndex);

    if (headerLength > 0) {
      final var header = //
      AbstractNode.getOriginStringFromEscapeString(string.substring(startIndex, startIndex + headerLength));

      mutableNode.setHeader(header);
    }

    var index = startIndex + headerLength;

    if (index == string.length()) {
      return (index - 1);
    }

    final var character = string.charAt(index);

    if (character == ',' || character == ')') {
      return index - 1;
    }

    if (index < string.length()) {
      var node = MutableNode.createEmpty();
      index = setMutableNodeFromStringAndStartIndexAndGetEndIndex(node, string, index + 1) + 1;
      mutableNode.addChildNode(node);
    }

    while (index < string.length()) {
      switch (string.charAt(index)) {
        case ',':
          var node = MutableNode.createEmpty();
          index = setMutableNodeFromStringAndStartIndexAndGetEndIndex(node, string, index + 1) + 1;
          mutableNode.addChildNode(node);
          break;
        case ')':
          return index;
        default:
      }
    }

    throw UnrepresentingArgumentException.forArgumentAndType(string, ImmutableNode.class);
  }

  private static int getHeaderLengthFromStringAndStartIndex(final String string, final int startIndex) {
    for (var index = startIndex; index < string.length(); index++) {
      final var character = string.charAt(index);

      if (character == '(' || character == ',' || character == ')') {
        return (index - startIndex);
      }
    }

    return (string.length() - startIndex);
  }
}
