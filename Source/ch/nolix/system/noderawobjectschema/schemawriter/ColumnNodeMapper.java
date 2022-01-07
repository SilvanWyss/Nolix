//package declaration
package ch.nolix.system.noderawobjectschema.schemawriter;

//own imports
import ch.nolix.common.document.node.Node;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

//class
public final class ColumnNodeMapper {
	
	//static attribute
	private static final ParametrizedPropertyTypeNodeMapper parametrizedPropertyTypeNodeMapper =
	new ParametrizedPropertyTypeNodeMapper();
	
	//method
	public Node createColumnNodeFrom(final IColumnDTO column) {
		
		final var node = new Node();
		
		node
		.setHeader(SubNodeHeaderCatalogue.COLUMN)
		.addAttribute(createHeaderNodeFrom(column))
		.addAttribute(createParametrizedPropertyTypeNodeFrom(column));
		
		return node;
	}
	
	//method
	private Node createHeaderNodeFrom(final IColumnDTO column) {
		return Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.HEADER, column.getName());
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(final IColumnDTO column) {
		return
		parametrizedPropertyTypeNodeMapper.createParametrizedPropertyTypeNodeFrom(column.getParametrizedPropertyType());
	}
}
