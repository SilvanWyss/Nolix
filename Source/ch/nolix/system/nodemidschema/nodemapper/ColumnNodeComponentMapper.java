package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.nodemidschemaapi.databasestructureapi.NodeHeaderCatalog;

public final class ColumnNodeComponentMapper {

  private ColumnNodeComponentMapper() {
  }

  public static INode<?> mapColumnDtoToIdNode(final ColumnDto column) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ID, column.id());
  }

  public static INode<?> mapColumnDtoToNameNode(final ColumnDto column) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.NAME, column.name());
  }
}
