package ch.nolix.system.noderawdata.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalog;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IEntityHeadNodeMapper;
import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public final class EntityHeadNodeMapper implements IEntityHeadNodeMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapEntityCreationDtoToEntityHeadNode(
    final EntityCreationDto entityCreationDto,
    final TableViewDto tableView) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalog.ENTITY_HEAD, tableView.id(), entityCreationDto.id());
  }
}
