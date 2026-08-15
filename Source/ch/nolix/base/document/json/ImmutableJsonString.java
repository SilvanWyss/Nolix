/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.json.JsonString;
import ch.nolix.baseapi.document.json.JsonValueType;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
public final class ImmutableJsonString implements JsonString {
  private final String string;

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * Creates a new {@link ImmutableJsonString} with the given string.
   * 
   * @param string
   * @throws RuntimeException if the given string is null
   */
  private ImmutableJsonString(final String string) {
    if (string == null) {
      throw ArgumentIsNullException.forArgumentType(String.class);
    }

    this.string = string;
  }

  /**
   * @param string
   * @return a new {@link ImmutableJsonString} with the given string
   * @throws RuntimeException if the given string is null
   */
  public static ImmutableJsonString withString(final String string) {
    return new ImmutableJsonString(string);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean formattedStringWillHaveMultipleLines() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getString() {
    return string;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JsonValueType getType() {
    return JsonValueType.STRING;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toFormattedStringWithIndentationLevelAndIndentationSymbol(
    final int indentationLevel,
    final String indentationSymbol,
    final boolean startMultiLinerWithIndentation) {
    return StringCatalog.DOUBLE_QUOTE + string + StringCatalog.DOUBLE_QUOTE;
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> toNode() {
    return ImmutableNode.withHeader(string);
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return StringCatalog.DOUBLE_QUOTE + string + StringCatalog.DOUBLE_QUOTE;
  }
}
