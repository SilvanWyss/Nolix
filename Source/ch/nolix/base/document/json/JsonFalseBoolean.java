/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.json.JsonBoolean;
import ch.nolix.baseapi.document.json.JsonValueType;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
public final class JsonFalseBoolean //NOSONAR: JsonFalseBoolean is a singleton.
implements JsonBoolean {
  public static final JsonFalseBoolean INSTANCE = new JsonFalseBoolean();

  private JsonFalseBoolean() {
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
  public String toFormattedStringWithIndentationLevel(int indentationLevel) {
    final var indentation = StringTool.createTabs(indentationLevel);

    return indentation + StringCatalog.LOWER_CASE_FALSE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> toNode() {
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
