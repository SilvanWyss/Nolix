//package declaration
package ch.nolix.system.nodedatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;

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
		.addAttribute(createIdNodeFrom(column))
		.addAttribute(createNameNodeFrom(column))
		.addAttribute(createParametrizedPropertyTypeNodeFrom(column));
		
		return node;
	}
	
	//method
	private Node createIdNodeFrom(final IColumnDTO column) {
		return Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.ID, column.getId());
	}
	
	//method
	private Node createNameNodeFrom(final IColumnDTO column) {
		return Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.NAME, column.getName());
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(final IColumnDTO column) {
		return
		parametrizedPropertyTypeNodeMapper.createParametrizedPropertyTypeNodeFrom(column.getParametrizedPropertyType());
	}
}
