package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.middataapi.modelapi.StringContentFieldDto;
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
  public INode<?> mapStringContentFieldDtoToContentFieldNode(final StringContentFieldDto stringContentFieldDto) {

    final var optionalContentString = stringContentFieldDto.optionalContentString();

    if (optionalContentString != null) {
      return Node.fromString(optionalContentString);
    }

    return Node.EMPTY_NODE;
  }
}
