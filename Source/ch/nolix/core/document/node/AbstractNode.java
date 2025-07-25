package ch.nolix.core.document.node;

import java.util.Optional;
import java.util.function.Predicate;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.document.xml.MutableXmlNode;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.document.node.INodeComparator;
import ch.nolix.coreapi.document.xml.IMutableXmlNode;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

/**
 * @author Silvan Wyss
 * @version 2017-06-24
 * @param <N> is the type of a {@link AbstractNode}.
 */
public abstract class AbstractNode<N extends AbstractNode<N>> implements INode<N> {

  public static final String COMMA_CODE = "$M";

  public static final String DOLLAR_SYMBOL_CODE = "$X";

  public static final String OPEN_BRACKET_CODE = "$O";

  public static final String CLOSED_BRACKET_CODE = "$C";

  private static final INodeComparator NODE_COMPARATOR = new NodeComparator();

  /**
   * @param string
   * @return an escape {@link String} for the given string.
   */
  public static String getEscapeStringFor(final String string) {

    final var stringBuilder = new StringBuilder();

    for (var i = 0; i < string.length(); i++) {
      switch (string.charAt(i)) {
        case CharacterCatalog.COMMA:
          stringBuilder.append(COMMA_CODE);
          break;
        case CharacterCatalog.DOLLAR:
          stringBuilder.append(DOLLAR_SYMBOL_CODE);
          break;
        case CharacterCatalog.OPEN_BRACKET:
          stringBuilder.append(OPEN_BRACKET_CODE);
          break;
        case CharacterCatalog.CLOSED_BRACKET:
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
      .replace(COMMA_CODE, String.valueOf(CharacterCatalog.COMMA))
      .replace(OPEN_BRACKET_CODE, String.valueOf(CharacterCatalog.OPEN_BRACKET))
      .replace(CLOSED_BRACKET_CODE, String.valueOf(CharacterCatalog.CLOSED_BRACKET))

      //It is essential to replace the dollar symbol code at last.
      .replace(DOLLAR_SYMBOL_CODE, String.valueOf(CharacterCatalog.DOLLAR));
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
  public final boolean equals(Object object) {
    return //
    object instanceof final INode<?> node
    && NODE_COMPARATOR.areEqual(this, node);
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
  public Optional<N> getOptionalStoredFirstChildNodeThat(Predicate<INode<?>> selector) {
    return getStoredChildNodes().getOptionalStoredFirst(selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N getStoredChildNodeAtOneBasedIndex(final int index) {
    return getStoredChildNodes().getStoredAtOneBasedIndex(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<N> getStoredChildNodesWithHeader(final String header) {
    return getStoredChildNodesThat(a -> a.hasHeader(header));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<N> getStoredChildNodesThat(final Predicate<INode<?>> selector) {
    return getStoredChildNodes().getStoredSelected(selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N getStoredFirstChildNode() {
    return getStoredChildNodes().getStoredFirst();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N getStoredSingleChildNode() {
    return getStoredChildNodes().getStoredOne();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N getStoredFirstChildNodeThat(Predicate<INode<?>> selector) {
    return getStoredChildNodes().getStoredFirst(selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N getStoredFirstChildNodeWithHeader(final String header) {
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
  public final int hashCode() {
    return toString().hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isBlank() {
    return (!hasHeader() && !containsChildNodes());
  }

  /**
   * Saves the current {@link AbstractNode} to the file with the given file path.
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
   * Saves the current {@link AbstractNode} to the file with the given path.
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

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean toBoolean() {
    return StringTool.toBoolean(toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final double toDouble() {
    return StringTool.toDouble(toString());
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
        .append(CharacterCatalog.OPEN_BRACKET)
        .append(getStoredChildNodes().toString())
        .append(CharacterCatalog.CLOSED_BRACKET);
    }

    return stringBuilder.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IMutableXmlNode toXml() {

    //Creates an XmlNode.
    final var xmlNode = MutableXmlNode.createBlankMutableXmlNode().setName(getHeader());

    //Iterates the child nodes of the current BaseNode.
    for (final AbstractNode<?> cn : getStoredChildNodes()) {

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

  private void appendFormattedStringRepresentationOfChildNodesToStringBuilder(
    final int leadingTabulators,
    final StringBuilder stringBuilder) {

    //Handles the case that all child nodes of the current BaseNode themselves do
    //not contain child nodes.
    if (getStoredChildNodes().containsNone(INode::containsChildNodes)) {
      stringBuilder
        .append(CharacterCatalog.OPEN_BRACKET)
        .append(getStoredChildNodes().toString())
        .append(CharacterCatalog.CLOSED_BRACKET);

      //Handles the case that the current BaseNode contains child nodes that
      //themselves contains child nodes.
    } else {

      stringBuilder
        .append(CharacterCatalog.OPEN_BRACKET)
        .append(CharacterCatalog.NEW_LINE);

      //Iterates the child nodes of the current BaseNode.
      final var attributeCount = getChildNodeCount();
      var index = 1;
      for (final AbstractNode<?> cn : getStoredChildNodes()) {

        stringBuilder.append(cn.toFormattedString(leadingTabulators + 1));

        if (index < attributeCount) {
          stringBuilder.append(CharacterCatalog.COMMA);
        }

        stringBuilder.append(CharacterCatalog.NEW_LINE);

        index++;
      }

      stringBuilder
        .append(StringTool.createTabs(leadingTabulators))
        .append(CharacterCatalog.CLOSED_BRACKET);
    }
  }

  /**
   * @return a reproducing {@link String} representation of the header of the
   *         current {@link AbstractNode}.
   */
  private String getReproducingHeader() {
    return getEscapeStringFor(getHeader());
  }

  /**
   * @param leadingTabulators
   * @return a formated {@link String} representation of the current
   *         {@link AbstractNode} with as many leading tabulators as the given
   *         leading tabulator count says.
   */
  private String toFormattedString(final int leadingTabulators) {

    final var stringBuilder = new StringBuilder();

    stringBuilder.append(StringTool.createTabs(leadingTabulators));

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
