package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.nodemidschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschemaapi.nodemapperapi.IContentModelNodeMapper;

public final class ColumnNodeComponentMapper {

  private static final IContentModelNodeMapper CONTENT_MODEL_NODE_MAPPER = new ContentModelNodeMapper();

  private ColumnNodeComponentMapper() {
  }

  public static INode<?> mapColunDtoToContentModelNode(final ColumnDto column) {
    return CONTENT_MODEL_NODE_MAPPER.mapContentModelDtoToNode(column.contentModel());
  }

  public static INode<?> mapColumnDtoToIdNode(final ColumnDto column) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ID, column.id());
  }

  public static INode<?> mapColumnDtoToNameNode(final ColumnDto column) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.NAME, column.name());
  }
}
