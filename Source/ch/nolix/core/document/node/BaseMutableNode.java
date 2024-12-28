package ch.nolix.core.document.node;

import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public abstract class BaseMutableNode<N extends BaseMutableNode<N>> extends AbstractNode<N> implements IMutableNode<N> {

  /**
   * {@inheritDoc}
   */
  @Override
  public final N addPostfixToHeader(final String postfix) {

    //Asserts that the given postfix is not blank.
    GlobalValidator.assertThat(postfix).thatIsNamed(LowerCaseVariableCatalogue.POSTFIX).isNotBlank();

    //Handles the case that the current BaseMutableNode does not have a header.
    if (!hasHeader()) {
      setHeader(postfix);

      //Handles the case that the current BaseMutableNode has a header.
    } else {
      setHeader(getHeader() + postfix);
    }

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N addPrefixToHeader(final String prefix) {

    //Asserts that the given prefix is not blank.
    GlobalValidator.assertThat(prefix).thatIsNamed(LowerCaseVariableCatalogue.PREFIX).isNotBlank();

    //Handles the case that the current BaseMutableNode does not have a header.
    if (!hasHeader()) {
      setHeader(prefix);

      //Handles the case that the current BaseMutableNode has a header.
    } else {
      setHeader(prefix + getHeader());
    }

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void resetFromFile(final String filePath) {
    resetFromString(
      new FileAccessor(filePath)
        .readFile()
        .replace(String.valueOf(CharacterCatalogue.TABULATOR), StringCatalogue.EMPTY_STRING)
        .replace(String.valueOf(CharacterCatalogue.NEW_LINE), StringCatalogue.EMPTY_STRING));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void resetFromString(final String string) {

    reset();

    if (setFromStringAndStartIndexAndGetEndIndex(string, 0) != string.length() - 1) {

      reset();

      throw UnrepresentingArgumentException.forArgumentAndType(string, Node.class);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N setChildNodes(final Iterable<? extends INode<?>> childNodes) {

    removeChildNodes();
    addChildNodes(childNodes);

    return asConcrete();
  }

  /**
   * @return the current {@link BaseMutableNode}.
   */
  protected abstract N asConcrete();

  final int setFromStringAndStartIndexAndGetEndIndex(final String string, final int startIndex) {

    final var headerLength = getHeaderLengthFromStringAndStartIndex(string, startIndex);

    if (headerLength > 0) {
      setHeader(getOriginStringFromEscapeString(string.substring(startIndex, startIndex + headerLength)));
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
      index = node.setFromStringAndStartIndexAndGetEndIndex(string, index + 1) + 1;
      addChildNode(node);
    }

    while (index < string.length()) {
      switch (string.charAt(index)) {
        case ',':
          var node = MutableNode.createEmpty();
          index = node.setFromStringAndStartIndexAndGetEndIndex(string, index + 1) + 1;
          addChildNode(node);
          break;
        case ')':
          return index;
        default:
      }
    }

    throw UnrepresentingArgumentException.forArgumentAndType(string, Node.class);
  }

  private int getHeaderLengthFromStringAndStartIndex(final String string, final int startIndex) {

    for (var index = startIndex; index < string.length(); index++) {

      final var character = string.charAt(index);

      if (character == '(' || character == ',' || character == ')') {
        return (index - startIndex);
      }
    }

    return (string.length() - startIndex);
  }
}
