/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import java.math.BigDecimal;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.json.JsonNumber;
import ch.nolix.baseapi.document.json.JsonValue;
import ch.nolix.baseapi.document.json.JsonValueType;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class ImmutableJsonNumber implements JsonNumber {
  private final BigDecimal number;

  private ImmutableJsonNumber(final BigDecimal number) {
    Validator.assertThat(number).thatIsNamed(LowerCaseVariableNameCatalog.NUMBER).isNotNull();

    this.number = number;
  }

  public static ImmutableJsonNumber withNumber(final BigDecimal number) {
    return new ImmutableJsonNumber(number);
  }

  public static JsonValue withNumber(final int number) {
    final var numberBigDecimal = BigDecimal.valueOf(number);

    return new ImmutableJsonNumber(numberBigDecimal);
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
  public BigDecimal getNumber() {
    return number;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JsonValueType getType() {
    return JsonValueType.NUMBER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toFormattedStringWithIndentationLevelAndIndentationSymbol(
    final int indentationLevel,
    final String indentationSymbol,
    final boolean startMultiLinerWithIndentation) {
    return number.toString();
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> toNode() {
    return ImmutableNode.withHeader(number.toString());
  }
}
