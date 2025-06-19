package ch.nolix.systemapi.nodemiddataapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.middataapi.schemaviewapi.DatabaseViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public interface IMultiReferenceEntryNodeMapper {

  /**
   * @param multiReferenceEntryDto
   * @param databaseView
   * @return a new multi reference entry node from the given
   *         multiReferenceEntryDto using the given databaseView.
   * @throws RuntimeException if the given multiReferenceEntryDto is null.
   */
  INode<?> mapMultiReferenceEntryDtoToMultiReferenceEntryNode(
    MultiReferenceEntryDto multiReferenceEntryDto,
    DatabaseViewDto databaseView);
}
