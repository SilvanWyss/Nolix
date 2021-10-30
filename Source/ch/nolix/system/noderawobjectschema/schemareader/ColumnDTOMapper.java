//package declaration
package ch.nolix.system.noderawobjectschema.schemareader;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.noderawobjectschema.structure.ColumnNodeSearcher;
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
	public ColumnDTO createColumnDTOFromColumnNode(final BaseNode columnNode) {
		return
		new ColumnDTO(getHeaderFromColumnNode(columnNode), createParametrizedPropertyTypeFromColumnNode(columnNode));
	}
	
	//method
	private String getHeaderFromColumnNode(final BaseNode columnNode) {
		return columnNodeSearcher.getHeaderNodeFromColumnNode(columnNode).getOneAttributeHeader();
	}
	
	//method
	private ParametrizedPropertyTypeDTO createParametrizedPropertyTypeFromColumnNode(final BaseNode columnNode) {
		
		final var parametrizedPropertyTypeNode = columnNodeSearcher.getParametrizedPropertyTypeNodeFromColumnNode(columnNode);
		
		return
		parametrizedPropertyTypeDTOMapper.createParametrizedProeprtyTypeDTOFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
	}
}
