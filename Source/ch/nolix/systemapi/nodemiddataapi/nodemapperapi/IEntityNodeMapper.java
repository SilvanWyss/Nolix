package ch.nolix.systemapi.nodemiddataapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.middataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.middataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public interface IEntityNodeMapper {

  /**
   * @param entityCreationDto
   * @param tableView
   * @param saveStamp
   * @return a new entity node from the given entityCreationDto.
   * @throws RuntimeException if the given entityCreationDto is null.
   */
  INode<?> mapEntityCreationDtoToEntityNode(
    EntityCreationDto entityCreationDto,
    TableSchemaViewDto tableView,
    long saveStamp);
}
