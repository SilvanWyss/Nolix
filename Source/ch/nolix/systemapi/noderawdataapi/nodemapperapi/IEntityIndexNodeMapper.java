package ch.nolix.systemapi.noderawdataapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public interface IEntityIndexNodeMapper {

  /**
   * @param entityCreationDto
   * @param tableView
   * @return a new index {@link INode} from the given entityCreationDto.
   * @throws RuntimeException if the given entityCreationDto is null.
   */
  INode<?> mapEntityCreationDtoToEntityIndexNode(EntityCreationDto entityCreationDto, TableSchemaViewDto tableView);
}
