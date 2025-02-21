package ch.nolix.systemapi.noderawdataapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.rawdataapi.modelapi.MultiReferenceEntryDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public interface IMultiReferenceEntryNodeMapper {

  /**
   * @param multiReferenceEntryDto
   * @return a new multi reference entry node from the given
   *         multiReferenceEntryDto.
   * @throws RuntimeException if the given multiReferenceEntryDto is null.
   */
  INode<?> mapMultiReferenceEntryDtoToMultiReferenceEntryNode(MultiReferenceEntryDto multiReferenceEntryDto);
}
