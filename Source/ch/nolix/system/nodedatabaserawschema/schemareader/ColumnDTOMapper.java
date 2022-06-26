//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.objectschema.schemadto.ColumnDTO;
import ch.nolix.system.objectschema.schemadto.ParametrizedPropertyTypeDTO;

//class
final class ColumnDTOMapper {
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//static attribute
	private static final ParametrizedPropertyTypeDTOMapper parametrizedPropertyTypeDTOMapper =
	new ParametrizedPropertyTypeDTOMapper();
	
	//method
	public ColumnDTO createColumnDTOFromColumnNode(final BaseNode<?> columnNode) {
		return
		new ColumnDTO(
			getIdFromColumnNode(columnNode),
			getNameFromColumnNode(columnNode),
			createParametrizedPropertyTypeFromColumnNode(columnNode)
		);
	}
	
	//method
	private String getIdFromColumnNode(final BaseNode<?> columnNode) {
		return columnNodeSearcher.getRefIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private String getNameFromColumnNode(final BaseNode<?> columnNode) {
		return columnNodeSearcher.getRefNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private ParametrizedPropertyTypeDTO createParametrizedPropertyTypeFromColumnNode(final BaseNode<?> columnNode) {
		
		final var parametrizedPropertyTypeNode =
		columnNodeSearcher.getRefParametrizedPropertyTypeNodeFromColumnNode(columnNode);
		
		return
		parametrizedPropertyTypeDTOMapper.createParametrizedProeprtyTypeDTOFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
	}
}
