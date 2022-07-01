//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.ParametrizedPropertyTypeNodeSearcher;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedBackReferenceTypeDTO;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDTO;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedValueTypeDTO;
import ch.nolix.system.objectschema.schemadto.ParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public class ParametrizedPropertyTypeDTOMapper {
	
	//static attribute
	private static final ParametrizedPropertyTypeNodeSearcher parametrizedPropertyTypeNodeSearcher =
	new ParametrizedPropertyTypeNodeSearcher();
	
	//method
	public ParametrizedPropertyTypeDTO createParametrizedProeprtyTypeDTOFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
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
				throw InvalidArgumentException.forArgument(propertyType);
		}
	}
	
	//method
	private ParametrizedPropertyTypeDTO createBaseParametrizedBackReferenceTypeDTOFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode,
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
		final IMutableNode<?> parametrizedPropertyTypeNode,
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
		final IMutableNode<?> parametrizedPropertyTypeNode,
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
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var backReferencedColumnNode =
		parametrizedPropertyTypeNodeSearcher.getRefBackReferencedColumnIdNodeFromPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return backReferencedColumnNode.getSingleChildNodeHeader();
	}
	
	//method
	private DataType getDataTypeFromParametrizedPropertyTypeNode(final IMutableNode<?> parametrizedPropertyTypeNode) {
		
		final var dataTypeNode =
		parametrizedPropertyTypeNodeSearcher.getRefDataTypeNodeFromParametriedPropertyTypeNode(parametrizedPropertyTypeNode);
		
		return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
	}
	
	//method
	private PropertyType getPropertyTypeFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var propertyTypeNode =
		parametrizedPropertyTypeNodeSearcher.getRefPropertyTypeNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return PropertyType.fromSpecification(propertyTypeNode);
	}
	
	//method
	private String getReferencedTableIdFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var referencedTableIdNode =
		parametrizedPropertyTypeNodeSearcher.getRefReferencedTableIdNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return referencedTableIdNode.getSingleChildNodeHeader();
	}
}
