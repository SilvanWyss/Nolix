package ch.nolix.core.document.node;

import java.util.Optional;
import java.util.function.Predicate;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.document.xml.MutableXmlNode;
import ch.nolix.core.environment.filesystem.GlobalFileSystemAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.documentapi.xmlapi.IMutableXmlNode;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

/**
 * @author Silvan Wyss
 * @version 2017-06-24
 * @param <BN> is the type of a {@link BaseNode}.
 */
public abstract class BaseNode<BN extends BaseNode<BN>> implements INode<BN> {

  public static final String COMMA_CODE = "$M";

  public static final String DOLLAR_SYMBOL_CODE = "$X";

  public static final String OPEN_BRACKET_CODE = "$O";

  public static final String CLOSED_BRACKET_CODE = "$C";

  private static final IStringTool STRING_TOOL = new StringTool();

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

  /**
   * @param escapeString
   * @return the origin {@link String} from the given escapeString.
   * @throws NullPointerException if the given escapeString is null.
   */
  public static String getOriginStringFromEscapeString(final String escapeString) {

    return //
    escapeString
      .replace(COMMA_CODE, String.valueOf(CharacterCatalogue.COMMA))
      .replace(OPEN_BRACKET_CODE, String.valueOf(CharacterCatalogue.OPEN_BRACKET))
      .replace(CLOSED_BRACKET_CODE, String.valueOf(CharacterCatalogue.CLOSED_BRACKET))

      //It is essential to replace the dollar symbol code at last.
      .replace(DOLLAR_SYMBOL_CODE, String.valueOf(CharacterCatalogue.DOLLAR));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsChildNodes() {
    return getStoredChildNodes().containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsChildNodeThat(final Predicate<INode<?>> selector) {
    return getStoredChildNodes().containsAny(selector::test);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsChildNodeWithHeader(final String header) {
    return containsChildNodeThat(a -> a.hasHeader(header));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOneChildNode() {
    return getStoredChildNodes().containsOne();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getChildNodeCount() {
    return getStoredChildNodes().getCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<String> getChildNodesHeaders() {
    return getStoredChildNodes().to(INode::getHeader);
  }

  @Override
  public Optional<BN> getOptionalStoredFirstChildNodeThat(Predicate<INode<?>> selector) {
    return getStoredChildNodes().getOptionalStoredFirst(selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredChildNodeAt1BasedIndex(final int index) {
    return getStoredChildNodes().getStoredAt1BasedIndex(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<BN> getStoredChildNodesWithHeader(final String header) {
    return getStoredChildNodesThat(a -> a.hasHeader(header));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<BN> getStoredChildNodesThat(final Predicate<INode<?>> selector) {
    return getStoredChildNodes().getStoredSelected(selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredFirstChildNode() {
    return getStoredChildNodes().getStoredFirst();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredSingleChildNode() {
    return getStoredChildNodes().getStoredOne();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredFirstChildNodeThat(Predicate<INode<?>> selector) {
    return getStoredChildNodes().getStoredFirst(selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final BN getStoredFirstChildNodeWithHeader(final String header) {
    return getStoredFirstChildNodeThat(a -> a.hasHeader(header));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean getSingleChildNodeAsBoolean() {
    return getStoredSingleChildNode().toBoolean();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final double getSingleChildNodeAsDouble() {
    return getStoredSingleChildNode().toDouble();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getSingleChildNodeAsInt() {
    return getStoredSingleChildNode().toInt();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getSingleChildNodeHeader() {
    return getStoredSingleChildNode().getHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isBlank() {
    return (!hasHeader() && !containsChildNodes());
  }

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
    GlobalFileSystemAccessor.createFile(path, writeMode, toFormattedString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean toBoolean() {
    return STRING_TOOL.toBoolean(toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final double toDouble() {
    return STRING_TOOL.toDouble(toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String toFormattedString() {
    return toFormattedString(0);
  }

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

  /**
   * {@inheritDoc}
   */
  @Override
  public final IMutableXmlNode toXml() {

    //Creates an XmlNode.
    final var xmlNode = new MutableXmlNode().setName(getHeader());

    //Iterates the child nodes of the current BaseNode.
    for (final BaseNode<?> cn : getStoredChildNodes()) {

      //Handles the case that the current child node itself does not contain child nodes.
      if (!cn.containsChildNodes()) {
        xmlNode.setValue(cn.toString());

        //Handles the case that the current child node itself contains child nodes.
      } else {
        xmlNode.addChildNode(cn.toXml());
      }
    }

    return xmlNode;
  }

  /**
   * @param node
   * @return true if the current {@link BaseNode} equals the given node.
   */
  protected final boolean equalsNode(final BaseNode<?> node) {
    return hasEqualHeaderSettingLikeNode(node)
    && hasEqualChildNodeSettingLikeNode(node);
  }

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
        .append(STRING_TOOL.createTabs(leadingTabulators))
        .append(CharacterCatalogue.CLOSED_BRACKET);
    }
  }

  /**
   * @return a reproducing {@link String} representation of the header of the
   *         current {@link BaseNode}.
   */
  private String getReproducingHeader() {
    return getEscapeStringFor(getHeader());
  }

  private boolean hasEqualHeaderSettingLikeNode(final INode<?> node) {

    if (!hasHeader()) {
      return !node.hasHeader();
    }

    return node.hasHeader(getHeader());
  }

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

  /**
   * @param leadingTabulators
   * @return a formated {@link String} representation of the current
   *         {@link BaseNode} with as many leading tabulators as the given leading
   *         tabulator count says.
   */
  private String toFormattedString(final int leadingTabulators) {

    final var stringBuilder = new StringBuilder();

    stringBuilder.append(STRING_TOOL.createTabs(leadingTabulators));

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
