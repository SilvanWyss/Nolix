//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
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
	public ColumnDTO createColumnDTOFromColumnNode(final IMutableNode<?> columnNode) {
		return
		new ColumnDTO(
			getIdFromColumnNode(columnNode),
			getNameFromColumnNode(columnNode),
			createParametrizedPropertyTypeFromColumnNode(columnNode)
		);
	}
	
	//method
	private String getIdFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNodeSearcher.getRefIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private String getNameFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNodeSearcher.getRefNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private ParametrizedPropertyTypeDTO createParametrizedPropertyTypeFromColumnNode(final IMutableNode<?> columnNode) {
		
		final var parametrizedPropertyTypeNode =
		columnNodeSearcher.getRefParametrizedPropertyTypeNodeFromColumnNode(columnNode);
		
		return
		parametrizedPropertyTypeDTOMapper.createParametrizedProeprtyTypeDTOFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
	}
}
