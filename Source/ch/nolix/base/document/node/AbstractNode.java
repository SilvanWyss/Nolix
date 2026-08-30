/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.node;

import java.util.Optional;
import java.util.function.Predicate;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.base.document.xml.MutableXmlNode;
import ch.nolix.base.environment.filesystem.FileSystemAccessor;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.EscapeSymbolCodeCatalog;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.document.xml.IMutableXmlNode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.programcontrol.processproperty.WriteMode;

/**
 * @author Silvan Wyss
 * @param <N> the type of a {@link AbstractNode}.
 */
public abstract class AbstractNode<N extends AbstractNode<N>> implements Node<N> {

  private static final NodeComparator NODE_COMPARATOR = new NodeComparator();

  /**
   * @param string
   * @return an escape {@link String} for the given string.
   */
  public static String getEscapeStringFor(final String string) {
    final var stringBuilder = new StringBuilder();

    for (var i = 0; i < string.length(); i++) {
      switch (string.charAt(i)) {
        case CharacterCatalog.OPEN_BRACKET:
          stringBuilder.append(EscapeSymbolCodeCatalog.OPEN_BRACKET);
          break;
        case CharacterCatalog.CLOSED_BRACKET:
          stringBuilder.append(EscapeSymbolCodeCatalog.CLOSED_BRACKET);
          break;
        case CharacterCatalog.COMMA:
          stringBuilder.append(EscapeSymbolCodeCatalog.COMMA);
          break;
        case CharacterCatalog.DOLLAR:
          stringBuilder.append(EscapeSymbolCodeCatalog.DOLLAR);
          break;
        case CharacterCatalog.SPACE:
          stringBuilder.append(EscapeSymbolCodeCatalog.SPACE);
          break;
        default:
          stringBuilder.append(string.charAt(i));
      }
    }

    return stringBuilder.toString();
  }

  /**
   * @param escapeString
   * @return the origin {@link String} from the given escapeString
   * @throws NullPointerException if the given escapeString is null
   */
  public static String getOriginStringFromEscapeString(final String escapeString) {
    return //
    escapeString
      .replace(EscapeSymbolCodeCatalog.OPEN_BRACKET, String.valueOf(CharacterCatalog.OPEN_BRACKET))
      .replace(EscapeSymbolCodeCatalog.CLOSED_BRACKET, String.valueOf(CharacterCatalog.CLOSED_BRACKET))
      .replace(EscapeSymbolCodeCatalog.COMMA, String.valueOf(CharacterCatalog.COMMA))
      .replace(EscapeSymbolCodeCatalog.SPACE, String.valueOf(CharacterCatalog.SPACE))

      // There is essential to replace the dollar symbol code at last.
      .replace(EscapeSymbolCodeCatalog.DOLLAR, String.valueOf(CharacterCatalog.DOLLAR));
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
  public final boolean containsChildNode(final Predicate<Node<?>> selector) {
    return getStoredChildNodes().containsMatching(selector::test);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsChildNodeWithHeader(final String header) {
    return containsChildNode(a -> a.hasHeader(header));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean equals(Object object) {
    return //
    object instanceof final Node<?> node
    && NODE_COMPARATOR.areEqual(this, node);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean formattedStringWillHaveMultipleLines() {
    final var childNodeCount = getChildNodeCount();

    return //
    switch (childNodeCount) {
      case 0 ->
        false;
      case 1 ->
        getStoredFirstChildNode().containsChildNodes();
      default ->
        true;
    };
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
  public final int getChildNodeCount(final Predicate<Node<?>> selector) {
    return getStoredChildNodes(selector).getCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<String> getChildNodesHeaders() {
    return getStoredChildNodes().to(Node::getHeader);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<N> getOptionalStoredFirstChildNode(Predicate<Node<?>> selector) {
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
  public final ExtendedIterable<N> getStoredChildNodesWithHeader(final String header) {
    return getStoredChildNodes(a -> a.hasHeader(header));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<N> getStoredChildNodes(final Predicate<Node<?>> selector) {
    return getStoredChildNodes().getStoredSelected(selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N getStoredFirstChildNode() {
    return getStoredChildNodes().getStoredFirstNonNull();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N getStoredSingleChildNode() {
    return getStoredChildNodes().getStoredSingle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N getStoredFirstChildNode(Predicate<Node<?>> selector) {
    return getStoredChildNodes().getStoredFirst(selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N getStoredFirstChildNodeWithHeader(final String header) {
    return getStoredFirstChildNode(a -> a.hasHeader(header));
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
   * @throws RuntimeException if the given path is null
   * @throws RuntimeException if the given path is blank
   * @throws RuntimeException if there exists already a file system item with the
   *                          given path.
   */
  public final void saveToFile(final String path) {
    // Calls other method.
    saveToFile(path, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY);
  }

  /**
   * Saves the current {@link AbstractNode} to the file with the given path.
   * 
   * @param path
   * @param writeMode
   * @throws RuntimeException if the given path is null
   * @throws RuntimeException if the given path is blank
   * @throws RuntimeException if the given writeMode
   *                          flag={@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
   *                          and there exists already a file system item with the
   *                          given path.
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
  public final String toFormattedStringWithIndentationLevelAndIndentationSymbol(
    final int indentationLevel,
    final String indentationSymbol,
    final boolean startMultiLinerWithIndentation) {
    final var stringBuilder = new StringBuilder();

    // Handles the case that the current specification has a header.
    if (hasHeader()) {
      final var indentation = indentationSymbol.repeat(indentationLevel);

      stringBuilder.append(indentation + getReproducingHeader());
    }

    // Handles the case that the current BaseNode contains child nodes.
    if (containsChildNodes()) {
      appendFormattedStringRepresentationOfChildNodesToStringBuilder(
        indentationLevel,
        indentationSymbol,
        stringBuilder);
    }

    return stringBuilder.toString();
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
    } catch (final NumberFormatException _) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String toString() {
    final var stringBuilder = new StringBuilder();

    if (hasHeader()) {
      stringBuilder.append(getReproducingHeader());
    }

    if (containsChildNodes()) {
      stringBuilder
        .append(CharacterCatalog.OPEN_BRACKET)
        .append(getStoredChildNodes())
        .append(CharacterCatalog.CLOSED_BRACKET);
    }

    return stringBuilder.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IMutableXmlNode toXml() {
    // Creates an XmlNode.
    final var xmlNode = MutableXmlNode.createBlankMutableXmlNode().setName(getHeader());

    // Iterates the child nodes of the current BaseNode.
    for (final AbstractNode<?> cn : getStoredChildNodes()) {
      // Handles the case that the current child node itself does not contain child nodes.
      if (!cn.containsChildNodes()) {
        xmlNode.setValue(cn.toString());

        // Handles the case that the current child node itself contains child nodes.
      } else {
        xmlNode.addChildNode(cn.toXml());
      }
    }

    return xmlNode;
  }

  private void appendFormattedStringRepresentationOfChildNodesToStringBuilder(
    final int leadingTabulators,
    final String indentationSymbol,
    final StringBuilder stringBuilder) {
    // Handles the case that all child nodes of the current BaseNode themselves do
    // not contain child nodes.
    if (getStoredChildNodes().containsNoMatching(Node::containsChildNodes)) {
      stringBuilder
        .append(CharacterCatalog.OPEN_BRACKET)
        .append(getStoredChildNodes().toString())
        .append(CharacterCatalog.CLOSED_BRACKET);

      // Handles the case that the current BaseNode contains child nodes that
      // themselves contains child nodes.
    } else {
      stringBuilder
        .append(CharacterCatalog.OPEN_BRACKET)
        .append(CharacterCatalog.NEW_LINE);

      // Iterates the child nodes of the current BaseNode.
      final var attributeCount = getChildNodeCount();
      var index = 1;
      for (final AbstractNode<?> cn : getStoredChildNodes()) {
        stringBuilder.append(
          cn.toFormattedStringWithIndentationLevelAndIndentationSymbol(leadingTabulators + 1, indentationSymbol));

        if (index < attributeCount) {
          stringBuilder.append(CharacterCatalog.COMMA);
        }

        stringBuilder.append(CharacterCatalog.NEW_LINE);

        index++;
      }

      stringBuilder
        .append(indentationSymbol.repeat(leadingTabulators))
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
}
