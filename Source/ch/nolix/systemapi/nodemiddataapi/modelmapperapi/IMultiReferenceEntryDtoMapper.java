package ch.nolix.systemapi.nodemiddataapi.modelmapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public interface IMultiReferenceEntryDtoMapper {

  /**
   * @param multiReferenceEntryNode
   * @param tableName
   * @param entityId
   * @param multiReferenceColumnName
   * @param databaseView
   * @return a new {@link MultiReferenceEntryDto} from the given
   *         multiReferenceEntryNode.
   * @throws RuntimeException if the given multiReferenceEntryNode is null.
   */
  MultiReferenceEntryDto mapMultiReferenceEntryNodeToMultiReferenceEntryDto(
    IMutableNode<?> multiReferenceEntryNode,
    String tableName,
    String entityId,
    String multiReferenceColumnName,
    DatabaseViewDto databaseView);
}
