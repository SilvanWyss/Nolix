package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;
import ch.nolix.systemapi.nodemiddata.nodemapper.IEntityNodeMapper;
import ch.nolix.systemapi.nodemiddata.nodemapper.IFieldNodeMapper;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

/**
 * @author Silvan Wyss
 */
public final class EntityNodeMapper implements IEntityNodeMapper {
  private static final IFieldNodeMapper FIELD_NODE_MAPPER = new FieldNodeMapper();

  @Override
  /**
   * {@inheritDoc}
   */
  public INode<?> mapEntityCreationDtoToEntityNode(
    final EntityCreationDto newEntity,
    final TableViewDto tableView,
    final long saveStamp) {
    final var entityHeader = NodeHeaderCatalog.ENTITY;
    final var fieldNodes = FIELD_NODE_MAPPER.mapEntityCreationDtoToFieldNodes(newEntity, saveStamp, tableView);

    return Node.withHeaderAndChildNodes(entityHeader, fieldNodes);
  }
}
