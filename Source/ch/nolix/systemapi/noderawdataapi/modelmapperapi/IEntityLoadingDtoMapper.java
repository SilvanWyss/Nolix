package ch.nolix.systemapi.noderawdataapi.modelmapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-07
 */
public interface IEntityLoadingDtoMapper {

  /**
   * @param entityNode
   * @param tableView
   * @return a new {@link EntityLoadingDto} from the given entityNode.
   * @throws RuntimeException if the given entityNode is null.
   */
  EntityLoadingDto mapEntityNodeToEntityLoadingDto(IMutableNode<?> entityNode, TableSchemaViewDto tableView);
}
