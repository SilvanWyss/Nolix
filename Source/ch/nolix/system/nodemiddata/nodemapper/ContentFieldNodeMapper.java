package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middataapi.modelapi.StringValueFieldDto;
import ch.nolix.systemapi.nodemiddataapi.nodemapperapi.IContentFieldNodeMapper;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public final class ContentFieldNodeMapper implements IContentFieldNodeMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapStringContentFieldDtoToContentFieldNode(final StringValueFieldDto stringValueFieldDto) {

    final var nullableValue = stringValueFieldDto.nullableValue();

    if (nullableValue != null) {
      return Node.fromString(nullableValue);
    }

    return Node.EMPTY_NODE;
  }
}
