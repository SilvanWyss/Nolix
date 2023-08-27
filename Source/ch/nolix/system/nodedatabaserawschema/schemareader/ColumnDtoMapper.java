//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.system.objectschema.schemadto.ParameterizedPropertyTypeDto;

//class
final class ColumnDtoMapper {
	
	//constant
	private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();
	
	//constant
	private static final ParameterizedPropertyTypeDtoMapper PARAMETRIZED_PROPERTY_TYPE_DTO_MAPPER =
	new ParameterizedPropertyTypeDtoMapper();
	
	//method
	public ColumnDto createColumnDtoFromColumnNode(final IMutableNode<?> columnNode) {
		return
		new ColumnDto(
			getIdFromColumnNode(columnNode),
			getNameFromColumnNode(columnNode),
			createParametrizedPropertyTypeFromColumnNode(columnNode)
		);
	}
	
	//method
	private String getIdFromColumnNode(final IMutableNode<?> columnNode) {
		return COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private String getNameFromColumnNode(final IMutableNode<?> columnNode) {
		return COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
	}
	
	//method
	private ParameterizedPropertyTypeDto createParametrizedPropertyTypeFromColumnNode(final IMutableNode<?> columnNode) {
		
		final var parametrizedPropertyTypeNode =
		COLUMN_NODE_SEARCHER.getStoredParametrizedPropertyTypeNodeFromColumnNode(columnNode);
		
		return
		PARAMETRIZED_PROPERTY_TYPE_DTO_MAPPER.createParametrizedProeprtyTypeDtoFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
	}
}
