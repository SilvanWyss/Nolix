package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

public final class ColumnNodeComponentMapper {
  private ColumnNodeComponentMapper() {
  }

  public static INode<?> mapColumnDtoToDataTypeNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.DATA_TYPE, columnDto.contentModel().dataType().name());
  }

  public static INode<?> mapColumnDtoToFieldTypeNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.FIELD_TYPE, columnDto.contentModel().fieldType().name());
  }

  public static INode<?> mapColumnDtoToIdNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ID, columnDto.id());
  }

  public static INode<?> mapColumnDtoToNameNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.NAME, columnDto.name());
  }
}
