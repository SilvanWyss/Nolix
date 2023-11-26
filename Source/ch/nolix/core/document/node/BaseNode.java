//package declaration
package ch.nolix.core.document.node;

//Java imports
import java.util.function.Predicate;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.pair.IntPair;
import ch.nolix.core.document.xml.MutableXmlNode;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.coreapi.commontypeapi.stringutilapi.CharacterCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.documentapi.xmlapi.IMutableXmlNode;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

//class
/**
 * @author Silvan Wyss
 * @date 2017-06-24
 * @param <BN> is the type of a {@link BaseNode}.
 */
public abstract class BaseNode<BN extends BaseNode<BN>> implements INode<BN> {

  //constant
  public static final String COMMA_CODE = "$M";

  //constant
  public static final String DOLLAR_SYMBOL_CODE = "$X";

  //constant
  public static final String OPEN_BRACKET_CODE = "$O";

  //constant
  public static final String CLOSED_BRACKET_CODE = "$C";

  //static method
  /**
   * @param string
   * @return an escape {@link String} for the given string.
   */
  public static String getEscapeStringFor(final String string) {

    final var stringBuilder = new StringBuilder();

    for (var i = 0; i < string.length(); i++) {
      switch (string.charAt(i)) {
        case CharacterCatalogue.COMMA:
          stringBuilder.append(COMMA_CODE);
          break;
        case CharacterCatalogue.DOLLAR:
          stringBuilder.append(DOLLAR_SYMBOL_CODE);
          break;
        case CharacterCatalogue.OPEN_BRACKET:
          stringBuilder.append(OPEN_BRACKET_CODE);
          break;
        case CharacterCatalogue.CLOSED_BRACKET:
          stringBuilder.append(CLOSED_BRACKET_CODE);
          break;
        default:
          stringBuilder.append(string.charAt(i));
      }
    }

    return stringBuilder.toString();
  }

  //static method
  /**
   * @param escapeString
   * @return an origin {@link String} from the given escapeString.
   */
  public static String getStoredginStringFromEscapeString(final String escapeString) {

    return escapeString
      .replace(COMMA_CODE, String.valueOf(CharacterCatalogue.COMMA))
      .replace(OPEN_BRACKET_CODE, String.valueOf(CharacterCatalogue.OPEN_BRACKET))
      .replace(CLOSED_BRACKET_CODE, String.valueOf(CharacterCatalogue.CLOSED_BRACKET))

      //It is essential to replace the dollar symbol code at last.
      .replace(DOLLAR_SYMBOL_CODE, String.valueOf(CharacterCatalogue.DOLLAR));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsChildNodes() {
    return getStoredChildNodes().containsAny();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsChildNodeThat(final Predicate<INode<?>> selector) {
    return getStoredChildNodes().containsAny(selector::test);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsChildNodeWithHeader(final String header) {
    return containsChildNodeThat(a -> a.hasHeader(header));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOneChildNode() {
    return getStoredChildNodes().containsOne();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final int getChildNodeCount() {
    return getStoredChildNodes().getElementCount();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<String> getChildNodesHeaders() {
    return getStoredChildNodes().to(INode::getHeader);
  }

  //method declaration
  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredChildNodeAt1BasedIndex(final int index) {
    return getStoredChildNodes().getStoredAt1BasedIndex(index);
  }

  //method declaration
  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<BN> getStoredChildNodesWithHeader(final String header) {
    return getStoredChildNodesThat(a -> a.hasHeader(header));
  }

  //method declaration
  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<BN> getStoredChildNodesThat(final Predicate<INode<?>> selector) {
    return getStoredChildNodes().getStoredSelected(selector);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredFirstChildNode() {
    return getStoredChildNodes().getStoredFirst();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredSingleChildNode() {
    return getStoredChildNodes().getStoredOne();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredFirstChildNodeThat(Predicate<INode<?>> selector) {
    return getStoredChildNodes().getStoredFirst(selector);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredFirstChildNodeThatOrNull(Predicate<INode<?>> selector) {
    return getStoredChildNodes().getStoredFirstOrNull(selector);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredFirstChildNodeWithHeader(final String header) {
    return getStoredFirstChildNodeThat(a -> a.hasHeader(header));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean getSingleChildNodeAsBoolean() {
    return getStoredSingleChildNode().toBoolean();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final double getSingleChildNodeAsDouble() {
    return getStoredSingleChildNode().toDouble();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final int getSingleChildNodeAsInt() {
    return getStoredSingleChildNode().toInt();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final String getSingleChildNodeHeader() {
    return getStoredSingleChildNode().getHeader();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isBlank() {
    return (!hasHeader() && !containsChildNodes());
  }

  //method
  /**
   * Saves the current {@link BaseNode} to the file with the given file path.
   * 
   * @param path
   * @throws ArgumentIsNullException  if the given path is null.
   * @throws InvalidArgumentException if the given path is blank.
   * @throws InvalidArgumentException if there exists already a file system item
   *                                  with the given path.
   */
  public final void saveToFile(final String path) {

    //Calls other method.
    saveToFile(path, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY);
  }

  //method
  /**
   * Saves the current {@link BaseNode} to the file with the given path.
   * 
   * @param path
   * @param writeMode
   * @throws ArgumentIsNullException  if the given path is null.
   * @throws InvalidArgumentException if the given path is blank.
   * @throws InvalidArgumentException if the given writeMode
   *                                  flag={@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
   *                                  and there exists already a file system item
   *                                  with the given path.
   */
  public final void saveToFile(final String path, final WriteMode writeMode) {
    FileSystemAccessor.createFile(path, writeMode, toFormattedString());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean toBoolean() {
    return GlobalStringHelper.toBoolean(toString());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final double toDouble() {
    return GlobalStringHelper.toDouble(toString());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final String toFormattedString() {
    return toFormattedString(0);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final int toInt() {

    if (!hasHeader() || containsChildNodes()) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
    }

    try {
      return Integer.parseInt(getHeader());
    } catch (final NumberFormatException numberFormatException) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
    }
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final IntPair toIntPair() {

    //Asserts that the current BaseNode contains 2 child nodes.
    if (getChildNodeCount() != 2) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, IntPair.class);
    }

    return new IntPair(getStoredChildNodeAt1BasedIndex(1).toInt(), getStoredChildNodeAt1BasedIndex(2).toInt());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final String toString() {

    final var stringBuilder = new StringBuilder();

    //Handles the case that the current specification has a header.
    if (hasHeader()) {
      stringBuilder.append(getReproducingHeader());
    }

    //Handles the case that the current BaseNode contains child nodes.
    if (containsChildNodes()) {
      stringBuilder
        .append(CharacterCatalogue.OPEN_BRACKET)
        .append(getStoredChildNodes().toString())
        .append(CharacterCatalogue.CLOSED_BRACKET);
    }

    return stringBuilder.toString();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final IMutableXmlNode toXml() {

    //Creates an XmlNode.
    final var xmlNode = new MutableXmlNode()
      .setName(getHeader());

    //Iterates the child nodes of the current BaseNode.
    for (final BaseNode<?> cn : getStoredChildNodes()) {

      //Handles the case that the current child node itself does not contain child
      //nodes.
      if (!cn.containsChildNodes()) {
        xmlNode.setValue(cn.toString());

        //Handles the case that the current child node itself contains child nodes.
      } else {
        xmlNode.addChildNode(cn.toXml());
      }
    }

    return xmlNode;
  }

  //method
  /**
   * @param node
   * @return true if the current {@link BaseNode} equals the given node.
   */
  protected final boolean equalsNode(final BaseNode<?> node) {
    return hasEqualHeaderSettingLikeNode(node)
    && hasEqualChildNodeSettingLikeNode(node);
  }

  //method
  private void appendFormattedStringRepresentationOfChildNodesToStringBuilder(
    final int leadingTabulators,
    final StringBuilder stringBuilder) {

    //Handles the case that all child nodes of the current BaseNode themselves do
    //not contain child nodes.
    if (getStoredChildNodes().containsNone(INode::containsChildNodes)) {
      stringBuilder
        .append(CharacterCatalogue.OPEN_BRACKET)
        .append(getStoredChildNodes().toString())
        .append(CharacterCatalogue.CLOSED_BRACKET);

      //Handles the case that the current BaseNode contains child nodes that
      //themselves contains child nodes.
    } else {

      stringBuilder
        .append(CharacterCatalogue.OPEN_BRACKET)
        .append(CharacterCatalogue.NEW_LINE);

      //Iterates the child nodes of the current BaseNode.
      final var attributeCount = getChildNodeCount();
      var index = 1;
      for (final BaseNode<?> cn : getStoredChildNodes()) {

        stringBuilder.append(cn.toFormattedString(leadingTabulators + 1));

        if (index < attributeCount) {
          stringBuilder.append(CharacterCatalogue.COMMA);
        }

        stringBuilder.append(CharacterCatalogue.NEW_LINE);

        index++;
      }

      stringBuilder
        .append(GlobalStringHelper.createTabulators(leadingTabulators))
        .append(CharacterCatalogue.CLOSED_BRACKET);
    }
  }

  //method
  /**
   * @return a reproducing {@link String} representation of the header of the
   *         current {@link BaseNode}.
   */
  private String getReproducingHeader() {
    return getEscapeStringFor(getHeader());
  }

  //method
  private boolean hasEqualHeaderSettingLikeNode(final INode<?> node) {

    if (!hasHeader()) {
      return !node.hasHeader();
    }

    return node.hasHeader(getHeader());
  }

  //method
  private boolean hasEqualChildNodeSettingLikeNode(final INode<?> node) {

    if (getChildNodeCount() != node.getChildNodeCount()) {
      return false;
    }

    final var iterator = node.getStoredChildNodes().iterator();
    for (final var cn : getStoredChildNodes()) {
      if (!cn.equals(iterator.next())) {
        return false;
      }
    }

    return true;
  }

  //method
  /**
   * @param leadingTabulators
   * @return a formated {@link String} representation of the current
   *         {@link BaseNode} with as many leading tabulators as the given leading
   *         tabulator count says.
   */
  private String toFormattedString(final int leadingTabulators) {

    final var stringBuilder = new StringBuilder();

    stringBuilder.append(GlobalStringHelper.createTabulators(leadingTabulators));

    //Handles the case that the current specification has a header.
    if (hasHeader()) {
      stringBuilder.append(getReproducingHeader());
    }

    //Handles the case that the current BaseNode contains child nodes.
    if (containsChildNodes()) {
      appendFormattedStringRepresentationOfChildNodesToStringBuilder(leadingTabulators, stringBuilder);
    }

    return stringBuilder.toString();
  }
}
