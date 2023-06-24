//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.ParametrizedPropertyTypeNodeSearcher;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedBackReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedValueTypeDto;
import ch.nolix.system.objectschema.schemadto.ParametrizedPropertyTypeDto;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public class ParametrizedPropertyTypeDtoMapper {
	
	//static attribute
	private static final ParametrizedPropertyTypeNodeSearcher parametrizedPropertyTypeNodeSearcher =
	new ParametrizedPropertyTypeNodeSearcher();
	
	//method
	public ParametrizedPropertyTypeDto createParametrizedProeprtyTypeDTOFromParametrizedPropertyTypeNode(
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
	private ParametrizedPropertyTypeDto createBaseParametrizedBackReferenceTypeDTOFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParametrizedBackReferenceTypeDto(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode),
			getBackReferencedColumnIdFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
	
	//method
	private ParametrizedPropertyTypeDto createBaseParametrizedReferenceTypeDTOFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParametrizedReferenceTypeDto(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode),
			getReferencedTableIdFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
	
	//method
	private BaseParametrizedValueTypeDto createBaseParametrizedValueTypeDTOFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParametrizedValueTypeDto(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
	
	//method
	private String getBackReferencedColumnIdFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var backReferencedColumnNode =
		parametrizedPropertyTypeNodeSearcher.getOriBackReferencedColumnIdNodeFromPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return backReferencedColumnNode.getSingleChildNodeHeader();
	}
	
	//method
	private DataType getDataTypeFromParametrizedPropertyTypeNode(final IMutableNode<?> parametrizedPropertyTypeNode) {
		
		final var dataTypeNode =
		parametrizedPropertyTypeNodeSearcher.getOriDataTypeNodeFromParametriedPropertyTypeNode(parametrizedPropertyTypeNode);
		
		return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
	}
	
	//method
	private PropertyType getPropertyTypeFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var propertyTypeNode =
		parametrizedPropertyTypeNodeSearcher.getOriPropertyTypeNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return PropertyType.fromSpecification(propertyTypeNode);
	}
	
	//method
	private String getReferencedTableIdFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var referencedTableIdNode =
		parametrizedPropertyTypeNodeSearcher.getOriReferencedTableIdNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return referencedTableIdNode.getSingleChildNodeHeader();
	}
}
