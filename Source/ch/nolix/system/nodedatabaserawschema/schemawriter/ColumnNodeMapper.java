//package declaration
package ch.nolix.system.nodedatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//class
public final class ColumnNodeMapper {
	
	//static attribute
	private static final ParametrizedPropertyTypeNodeMapper parametrizedPropertyTypeNodeMapper =
	new ParametrizedPropertyTypeNodeMapper();
	
	//method
	public Node createColumnNodeFrom(final IColumnDto column) {
		return
		Node.withHeaderAndChildNode(
			SubNodeHeaderCatalogue.COLUMN,
			createIdNodeFrom(column),
			createNameNodeFrom(column),
			createParametrizedPropertyTypeNodeFrom(column)
		);
	}
	
	//method
	private Node createIdNodeFrom(final IColumnDto column) {
		return Node.withHeaderAndChildNode(SubNodeHeaderCatalogue.ID, column.getId());
	}
	
	//method
	private Node createNameNodeFrom(final IColumnDto column) {
		return Node.withHeaderAndChildNode(SubNodeHeaderCatalogue.NAME, column.getName());
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(final IColumnDto column) {
		return
		parametrizedPropertyTypeNodeMapper.createParametrizedPropertyTypeNodeFrom(column.getParametrizedPropertyType());
	}
}
