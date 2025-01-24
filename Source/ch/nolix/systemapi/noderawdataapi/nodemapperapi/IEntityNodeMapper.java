package ch.nolix.systemapi.noderawdataapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public interface IEntityNodeMapper {

  /**
   * @param entityCreationDto
   * @param tableView
   * @param saveStamp
   * @return a new {@link INode} from the given entityCreationDto.
   * @throws RuntimeException if the given entityCreationDto is null.
   */
  INode<?> mapEntityCreationDtoToEntityNode(
    EntityCreationDto entityCreationDto,
    TableViewDto tableView,
    long saveStamp);
}
