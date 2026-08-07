/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.json.JsonBoolean;
import ch.nolix.baseapi.document.json.JsonValueType;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
public final class ImmutableJsonFalseBoolean // NOSONAR: ImmutableJsonFalseBoolean is a singleton.
implements JsonBoolean {
  public static final ImmutableJsonFalseBoolean INSTANCE = new ImmutableJsonFalseBoolean();

  private ImmutableJsonFalseBoolean() {
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
  public boolean getBoolean() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JsonValueType getType() {
    return JsonValueType.BOOLEAN;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toFormattedStringWithIndentationLevelAndIndentationSymbol(
    final int indentationLevel,
    final String indentationSymbol,
    final boolean startMultiLinerWithIndentation) {
    final var indentation = indentationSymbol.repeat(indentationLevel);

    return indentation + StringCatalog.LOWER_CASE_FALSE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> toNode() {
    return ImmutableNode.withHeader(StringCatalog.PASCAL_CASE_FALSE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return StringCatalog.LOWER_CASE_FALSE;
  }
}
