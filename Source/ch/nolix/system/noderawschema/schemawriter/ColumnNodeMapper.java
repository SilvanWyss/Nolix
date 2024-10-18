package ch.nolix.system.noderawschema.schemawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

public final class ColumnNodeMapper {

  private static final ParameterizedFieldTypeNodeMapper PARAMETERIZED_FIELD_TYPE_NODE_MAPPER = //
  new ParameterizedFieldTypeNodeMapper();

  public Node createColumnNodeFrom(final IColumnDto column) {
    return Node.withHeaderAndChildNode(
      StructureHeaderCatalogue.COLUMN,
      createIdNodeFrom(column),
      createNameNodeFrom(column),
      createParameterizedFieldTypeNodeFrom(column));
  }

  private Node createIdNodeFrom(final IColumnDto column) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.ID, column.getId());
  }

  private Node createNameNodeFrom(final IColumnDto column) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.NAME, column.getName());
  }

  private Node createParameterizedFieldTypeNodeFrom(final IColumnDto column) {
    return PARAMETERIZED_FIELD_TYPE_NODE_MAPPER.createParameterizedFieldTypeNodeFrom(
      column.getParameterizedFieldType());
  }
}
