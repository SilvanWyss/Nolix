package ch.nolix.systemapi.nodemiddata.nodemapper;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.StringValueFieldDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public interface IContentFieldNodeMapper {

  /**
   * @param stringValueFieldDto
   * @return a bew content field node from the given stringContentFieldDto.
   * @throws RuntimeException if the given stringContentFieldDto is null.
   */
  INode<?> mapStringContentFieldDtoToContentFieldNode(StringValueFieldDto stringValueFieldDto);
}
