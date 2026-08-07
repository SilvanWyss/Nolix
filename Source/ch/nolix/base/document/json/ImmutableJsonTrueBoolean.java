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
public final class ImmutableJsonTrueBoolean // NOSONAR: ImmutableJsonTrueBoolean is a singleton.
implements JsonBoolean {
  public static final ImmutableJsonTrueBoolean INSTANCE = new ImmutableJsonTrueBoolean();

  private ImmutableJsonTrueBoolean() {
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
    return true;
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

    return indentation + StringCatalog.LOWER_CASE_TRUE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> toNode() {
    return ImmutableNode.withHeader(StringCatalog.PASCAL_CASE_TRUE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return StringCatalog.LOWER_CASE_TRUE;
  }
}
