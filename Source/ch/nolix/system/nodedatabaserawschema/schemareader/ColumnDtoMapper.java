//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.system.objectschema.schemadto.ParametrizedPropertyTypeDto;

//class
final class ColumnDtoMapper {
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//static attribute
	private static final ParametrizedPropertyTypeDtoMapper parametrizedPropertyTypeDtoMapper =
	new ParametrizedPropertyTypeDtoMapper();
	
	//method
	public ColumnDto createColumnDTOFromColumnNode(final IMutableNode<?> columnNode) {
		return
		new ColumnDto(
			getIdFromColumnNode(columnNode),
			getNameFromColumnNode(columnNode),
			createParametrizedPropertyTypeFromColumnNode(columnNode)
		);
	}
	
	//method
	private String getIdFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNodeSearcher.getOriIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private String getNameFromColumnNode(final IMutableNode<?> columnNode) {
		return columnNodeSearcher.getOriNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private ParametrizedPropertyTypeDto createParametrizedPropertyTypeFromColumnNode(final IMutableNode<?> columnNode) {
		
		final var parametrizedPropertyTypeNode =
		columnNodeSearcher.getOriParametrizedPropertyTypeNodeFromColumnNode(columnNode);
		
		return
		parametrizedPropertyTypeDtoMapper.createParametrizedProeprtyTypeDTOFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
	}
}
