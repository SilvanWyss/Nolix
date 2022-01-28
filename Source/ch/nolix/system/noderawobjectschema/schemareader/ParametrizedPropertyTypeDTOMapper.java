//package declaration
package ch.nolix.system.noderawobjectschema.schemareader;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.noderawobjectschema.structure.ParametrizedPropertyTypeNodeSearcher;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedBackReferenceTypeDTO;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDTO;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedValueTypeDTO;
import ch.nolix.system.objectschema.schemadto.ParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public class ParametrizedPropertyTypeDTOMapper {
	
	//static attribute
	private static final ParametrizedPropertyTypeNodeSearcher parametrizedPropertyTypeNodeSearcher =
	new ParametrizedPropertyTypeNodeSearcher();
	
	//method
	public ParametrizedPropertyTypeDTO createParametrizedProeprtyTypeDTOFromParametrizedPropertyTypeNode(
		final BaseNode parametrizedPropertyTypeNode
	) {
		
		final var propertyType = getPropertyTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode);
		
		switch (propertyType.getBaseType()) {
			case BASE_VALUE:
				return
				createBaseParametrizedValueTypeDTOFromParametrizedPropertyTypeNode(
					parametrizedPropertyTypeNode,
					propertyType
				);
			case BASE_REFERENCE:
				return
				createBaseParametrizedReferenceTypeDTOFromParametrizedPropertyTypeNode(
					parametrizedPropertyTypeNode,
					propertyType
				);
			case BASE_BACK_REFERENCE:
				return
				createBaseParametrizedBackReferenceTypeDTOFromParametrizedPropertyTypeNode(
					parametrizedPropertyTypeNode,
					propertyType
				);
			default:
				throw new InvalidArgumentException(propertyType);
		}
	}
	
	//method
	private ParametrizedPropertyTypeDTO createBaseParametrizedBackReferenceTypeDTOFromParametrizedPropertyTypeNode(
		final BaseNode parametrizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParametrizedBackReferenceTypeDTO(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode),
			getBackReferencedColumnIdFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
	
	//method
	private ParametrizedPropertyTypeDTO createBaseParametrizedReferenceTypeDTOFromParametrizedPropertyTypeNode(
		final BaseNode parametrizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParametrizedReferenceTypeDTO(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode),
			getReferencedTableIdFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
	
	//method
	private BaseParametrizedValueTypeDTO createBaseParametrizedValueTypeDTOFromParametrizedPropertyTypeNode(
		final BaseNode parametrizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParametrizedValueTypeDTO(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
	
	//method
	private String getBackReferencedColumnIdFromParametrizedPropertyTypeNode(
		final BaseNode parametrizedPropertyTypeNode
	) {
		
		final var backReferencedColumnNode =
		parametrizedPropertyTypeNodeSearcher.getRefBackReferencedColumnIdNodeFromPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return backReferencedColumnNode.getOneAttributeHeader();
	}
	
	//method
	private String getDataTypeFromParametrizedPropertyTypeNode(final BaseNode parametrizedPropertyTypeNode) {
		
		final var dataTypeNode =
		parametrizedPropertyTypeNodeSearcher.getRefDataTypeNodeFromParametriedPropertyTypeNode(parametrizedPropertyTypeNode);
		
		return dataTypeNode.getOneAttributeHeader();
	}
	
	//method
	private PropertyType getPropertyTypeFromParametrizedPropertyTypeNode(final BaseNode parametrizedPropertyTypeNode) {
		
		final var propertyTypeNode =
		parametrizedPropertyTypeNodeSearcher.getRefPropertyTypeNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return PropertyType.fromSpecification(propertyTypeNode);
	}
	
	//method
	private String getReferencedTableIdFromParametrizedPropertyTypeNode(final BaseNode parametrizedPropertyTypeNode) {
		
		final var referencedTableIdNode =
		parametrizedPropertyTypeNodeSearcher.getRefReferencedTableIdNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return referencedTableIdNode.getOneAttributeHeader();
	}
}
