//package declaration
package ch.nolix.system.noderawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//class
public final class ColumnNodeMapper {

  //constant
  private static final ParameterizedFieldTypeNodeMapper PARAMETERIZED_FIELD_TYPE_NODE_MAPPER = //
  new ParameterizedFieldTypeNodeMapper();

  //method
  public Node createColumnNodeFrom(final IColumnDto column) {
    return Node.withHeaderAndChildNode(
      StructureHeaderCatalogue.COLUMN,
      createIdNodeFrom(column),
      createNameNodeFrom(column),
      createParameterizedFieldTypeNodeFrom(column));
  }

  //method
  private Node createIdNodeFrom(final IColumnDto column) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.ID, column.getId());
  }

  //method
  private Node createNameNodeFrom(final IColumnDto column) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.NAME, column.getName());
  }

  //method
  private Node createParameterizedFieldTypeNodeFrom(final IColumnDto column) {
    return PARAMETERIZED_FIELD_TYPE_NODE_MAPPER.createParameterizedFieldTypeNodeFrom(
      column.getParameterizedFieldType());
  }
}
