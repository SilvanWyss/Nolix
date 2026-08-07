/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;
import ch.nolix.systemapi.nodemiddata.nodemapper.IContentFieldNodeMapper;

/**
 * @author Silvan Wyss
 */
public final class ContentFieldNodeMapper implements IContentFieldNodeMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> mapValueStringFieldDtoToContentFieldNode(final ValueStringFieldDto valueStringFieldDto) {
    final var nullableValue = valueStringFieldDto.nullableValueString();

    if (nullableValue != null) {
      final var nullableAdditionalValue = valueStringFieldDto.nullableAdditionalValue();

      if (nullableAdditionalValue != null) {
        return ImmutableNode.withChildNodes(nullableValue, nullableAdditionalValue);
      }

      return ImmutableNode.fromString(nullableValue);
    }

    return ImmutableNode.EMPTY_NODE;
  }
}
