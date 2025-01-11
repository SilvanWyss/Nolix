package ch.nolix.system.noderawdata.datawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalog;
import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;

public final class EntityHeadNodeMapper {

  public Node createEntityHeadNode(final TableViewDto tableView, final EntityCreationDto newEntity) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalog.ENTITY_HEAD, tableView.id(), newEntity.id());
  }
}
