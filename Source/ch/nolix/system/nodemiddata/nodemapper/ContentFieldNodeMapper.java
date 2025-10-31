package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;
import ch.nolix.systemapi.nodemiddata.nodemapper.IContentFieldNodeMapper;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public final class ContentFieldNodeMapper implements IContentFieldNodeMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapValueStringFieldDtoToContentFieldNode(final ValueStringFieldDto valueStringFieldDto) {
    final var nullableValue = valueStringFieldDto.nullableValueString();

    if (nullableValue != null) {
      final var nullableAdditionalValue = valueStringFieldDto.nullableAdditionalValue();

      if (nullableAdditionalValue != null) {
        return Node.withChildNode(nullableValue, nullableAdditionalValue);
      }

      return Node.fromString(nullableValue);
    }

    return Node.EMPTY_NODE;
  }
}
