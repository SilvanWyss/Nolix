package ch.nolix.system.noderawschema.schemawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;

public final class ColumnNodeMapper {

  private static final ParameterizedFieldTypeNodeMapper PARAMETERIZED_FIELD_TYPE_NODE_MAPPER = //
  new ParameterizedFieldTypeNodeMapper();

  public Node createColumnNodeFrom(final ColumnDto column) {
    return Node.withHeaderAndChildNode(
      StructureHeaderCatalogue.COLUMN,
      createIdNodeFrom(column),
      createNameNodeFrom(column),
      createParameterizedFieldTypeNodeFrom(column));
  }

  private Node createIdNodeFrom(final ColumnDto column) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.ID, column.id());
  }

  private Node createNameNodeFrom(final ColumnDto column) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.NAME, column.name());
  }

  private Node createParameterizedFieldTypeNodeFrom(final ColumnDto column) {
    return PARAMETERIZED_FIELD_TYPE_NODE_MAPPER.createParameterizedFieldTypeNodeFrom(column.contentModel());
  }
}
