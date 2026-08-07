/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.json.JsonNull;
import ch.nolix.baseapi.document.json.JsonValueType;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
public final class ImmutableJsonNull // NOSONAR: ImmutableJsonNull is a singleton.
implements JsonNull {
  public static final ImmutableJsonNull INSTANCE = new ImmutableJsonNull();

  private ImmutableJsonNull() {
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
  public JsonValueType getType() {
    return JsonValueType.NULL;
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

    return indentation + StringCatalog.LOWER_CASE_NULL;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> toNode() {
    return ImmutableNode.withHeader(StringCatalog.LOWER_CASE_NULL);
  }
}
