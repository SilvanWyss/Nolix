package ch.nolix.systemapi.noderawdataapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public interface IEntityHeadNodeMapper {

  /**
   * @param entityCreationDto
   * @param tableView
   * @return a new {@link INode} from the given entityCreationDto.
   * @throws RuntimeException if the given entityCreationDto is null.
   */
  INode<?> mapEntityCreationDtoToEntityHeadNode(EntityCreationDto entityCreationDto, TableViewDto tableView);
}
