package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

//TODO: Create interface for ColumnNodeChildNodeMapper
public final class ColumnNodeChildNodeMapper {
  private ColumnNodeChildNodeMapper() {
  }

  public static INode<?> mapColumnDtoToBackReferenceableColumnIdsNode(final ColumnDto columnDto) {
    return //
    Node.withHeaderAndChildNodes(
      NodeHeaderCatalog.BACK_REFERENCEABLE_COLUMN_IDS,
      columnDto.backReferenceableColumnIds().to(Node::withHeader));
  }

  public static INode<?> mapColumnDtoToDataTypeNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.DATA_TYPE, columnDto.dataType().name());
  }

  public static INode<?> mapColumnDtoToFieldTypeNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.FIELD_TYPE, columnDto.fieldType().name());
  }

  public static INode<?> mapColumnDtoToIdNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ID, columnDto.id());
  }

  public static INode<?> mapColumnDtoToNameNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.NAME, columnDto.name());
  }

  public static INode<?> mapColumnDtoToReferenceableTableIdsNode(final ColumnDto columnDto) {
    return //
    Node.withHeaderAndChildNodes(
      NodeHeaderCatalog.REFERENCEABLE_TABLE_IDS,
      columnDto.referenceableTableIds().to(Node::withHeader));
  }
}
