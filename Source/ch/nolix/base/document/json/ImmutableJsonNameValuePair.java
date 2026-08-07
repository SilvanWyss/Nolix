/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.json.JsonNameValuePair;
import ch.nolix.baseapi.document.json.JsonValue;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class ImmutableJsonNameValuePair implements JsonNameValuePair {
  private final String name;

  private final JsonValue value;

  /**
   * Creates a new {@link ImmutableJsonNameValuePair} with the given name and
   * value
   * 
   * @param name
   * @param value
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given value is null
   */
  private ImmutableJsonNameValuePair(final String name, final JsonValue value) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableNameCatalog.NAME).isNotBlank();
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableNameCatalog.VALUE).isNotNull();

    this.name = name;
    this.value = value;
  }

  /**
   * @param name
   * @param value
   * @return a new {@link ImmutableJsonNameValuePair} with the given name and
   *         value
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given value is null
   */
  public static ImmutableJsonNameValuePair withNameAndValue(final String name, final JsonValue value) {
    return new ImmutableJsonNameValuePair(name, value);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean formattedStringWillHaveMultipleLines() {
    return value.formattedStringWillHaveMultipleLines();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JsonValue getStoredValue() {
    return value;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String toFormattedStringWithIndentationLevelAndIndentationSymbol(
    final int indentationLevel,
    final String indentationSymbol,
    final boolean startMultiLinerWithIndentation) {
    final var indentation = indentationSymbol.repeat(indentationLevel);
    final var incrementedIndentationLevel = indentationLevel + 1;

    final var formattedValueString = //
    value.toFormattedStringWithIndentationLevelAndIndentationSymbol(
      incrementedIndentationLevel,
      indentationSymbol,
      startMultiLinerWithIndentation);

    // Handle the case that the formatted String of the current ImmutableJsonNameValuePair has multiple lines.
    if (value.formattedStringWillHaveMultipleLines()) {
      return //
      indentation
      + StringCatalog.DOUBLE_QUOTE
      + name
      + StringCatalog.DOUBLE_QUOTE
      + StringCatalog.COLON
      + StringCatalog.NEW_LINE
      + formattedValueString;
    }

    // Handle the case that the formatted String of the current ImmutableJsonNameValuePair is a single line.
    return //
    indentation
    + StringCatalog.DOUBLE_QUOTE
    + name
    + StringCatalog.DOUBLE_QUOTE
    + JsonStringPartCatalog.NAME_VALUE_PAIR_FLAT_MIDDLE
    + formattedValueString;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> toNode() {
    return ImmutableNode.withHeaderAndChildNode(name, value.toNode());
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return //
    StringCatalog.DOUBLE_QUOTE
    + name
    + StringCatalog.DOUBLE_QUOTE
    + JsonStringPartCatalog.NAME_VALUE_PAIR_FLAT_MIDDLE
    + value;
  }
}
