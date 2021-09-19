//package declaration
package ch.nolix.system.nodeintermediateschema.schemawriter;

//own imports
import ch.nolix.common.document.node.Node;
import ch.nolix.system.nodeintermediateschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;

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
		return Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.HEADER, column.getHeader());
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(final IColumnDTO column) {
		return
		parametrizedPropertyTypeNodeMapper.createParametrizedPropertyTypeNodeFrom(column.getParametrizedPropertyType());
	}
}
