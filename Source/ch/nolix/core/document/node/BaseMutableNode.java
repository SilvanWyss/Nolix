//package declaration
package ch.nolix.core.document.node;

//own imports
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.CharacterCatalogue;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.StringCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
public abstract class BaseMutableNode<MN extends BaseMutableNode<MN>> extends BaseNode<MN> implements IMutableNode<MN> {

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final MN addPostfixToHeader(final String postfix) {

    //Asserts that the given postfix is not blank.
    GlobalValidator.assertThat(postfix).thatIsNamed(LowerCaseCatalogue.POSTFIX).isNotBlank();

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
    GlobalValidator.assertThat(prefix).thatIsNamed(LowerCaseCatalogue.PREFIX).isNotBlank();

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

    if (setAndGetEndIndex(string, 0) != string.length() - 1) {
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
  protected final int setAndGetEndIndex(final String substring, final int startIndex) {

    var index = startIndex;

    var endIndex = -1;
    while (index < substring.length()) {

      var character = substring.charAt(index);
      var breakLoop = false;

      if (character == '(') {
        breakLoop = true;
      } else if (character == ',' || character == ')') {
        endIndex = index - 1;
        breakLoop = true;
      }

      if (breakLoop) {
        break;
      }

      index++;
    }

    if (index > startIndex) {
      this.setHeader(getStoredginStringFromEscapeString(substring.substring(startIndex, index)));
    }

    if (index == substring.length()) {
      return (index - 1);
    }

    if (endIndex != -1) {
      return endIndex;
    }

    if (index < substring.length()) {
      var node = new MutableNode();
      index = node.setAndGetEndIndex(substring, index + 1) + 1;
      this.addChildNode(node);
    }

    while (index < substring.length()) {
      switch (substring.charAt(index)) {
        case ',':
          var node = new MutableNode();
          index = node.setAndGetEndIndex(substring, index + 1) + 1;
          this.addChildNode(node);
          break;
        case ')':
          return index;
        default:
      }
    }

    throw UnrepresentingArgumentException.forArgumentAndType(substring, Node.class);
  }
}
