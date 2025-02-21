package ch.nolix.systemapi.noderawdataapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.rawdataapi.modelapi.StringContentFieldDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public interface IContentFieldNodeMapper {

  /**
   * @param stringContentFieldDto
   * @return a bew content field node from the given stringContentFieldDto.
   * @throws RuntimeException if the given stringContentFieldDto is null.
   */
  INode<?> mapStringContentFieldDtoToContentFieldNode(StringContentFieldDto stringContentFieldDto);
}
