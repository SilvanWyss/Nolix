//package declaration
package ch.nolix.core.document.node;

//own imports
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public abstract class BaseMutableNode<MN extends BaseMutableNode<MN>> extends BaseNode<MN> implements IMutableNode<MN> {

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final MN addPostfixToHeader(final String postfix) {

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

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final MN addPrefixToHeader(final String prefix) {

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

  //method
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

  //method
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

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final MN setChildNodes(final Iterable<? extends INode<?>> childNodes) {

    removeChildNodes();
    addChildNodes(childNodes);

    return asConcrete();
  }

  //method declaration
  /**
   * @return the current {@link BaseMutableNode}.
   */
  protected abstract MN asConcrete();

  //method
  final int setFromStringAndStartIndexAndGetEndIndex(final String string, final int startIndex) {

    var index = startIndex;

    var endIndex = -1;
    while (index < string.length()) {

      var character = string.charAt(index);
      var breakLoop = false;

      if (character == '(') {
        breakLoop = true;
      } else if (character == ',' || character == ')') {
        endIndex = index - 1;
        breakLoop = true;
      } else {
        //Does nothing and continues the current loop.
      }

      if (breakLoop) {
        break;
      }

      index++;
    }

    if (index > startIndex) {
      this.setHeader(getOriginStringFromEscapeString(string.substring(startIndex, index)));
    }

    if (index == string.length()) {
      return (index - 1);
    }

    if (endIndex != -1) {
      return endIndex;
    }

    if (index < string.length()) {
      var node = new MutableNode();
      index = node.setFromStringAndStartIndexAndGetEndIndex(string, index + 1) + 1;
      this.addChildNode(node);
    }

    while (index < string.length()) {
      switch (string.charAt(index)) {
        case ',':
          var node = new MutableNode();
          index = node.setFromStringAndStartIndexAndGetEndIndex(string, index + 1) + 1;
          this.addChildNode(node);
          break;
        case ')':
          return index;
        default:
      }
    }

    throw UnrepresentingArgumentException.forArgumentAndType(string, Node.class);
  }
}
