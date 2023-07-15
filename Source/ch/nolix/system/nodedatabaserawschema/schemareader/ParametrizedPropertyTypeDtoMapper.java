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
	
	//constant
	private static final ParametrizedPropertyTypeNodeSearcher PARAMETRIZED_PROPERTY_TYPE_NODE_SEARCHER =
	new ParametrizedPropertyTypeNodeSearcher();
	
	//method
	public ParametrizedPropertyTypeDto createParametrizedProeprtyTypeDtoFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var propertyType = getPropertyTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode);
		
		switch (propertyType.getBaseType()) {
			case BASE_VALUE:
				return
				createBaseParametrizedValueTypeDtoFromParametrizedPropertyTypeNode(
					parametrizedPropertyTypeNode,
					propertyType
				);
			case BASE_REFERENCE:
				return
				createBaseParametrizedReferenceTypeDtoFromParametrizedPropertyTypeNode(
					parametrizedPropertyTypeNode,
					propertyType
				);
			case BASE_BACK_REFERENCE:
				return
				createBaseParametrizedBackReferenceTypeDtoFromParametrizedPropertyTypeNode(
					parametrizedPropertyTypeNode,
					propertyType
				);
			default:
				throw InvalidArgumentException.forArgument(propertyType);
		}
	}
	
	//method
	private ParametrizedPropertyTypeDto createBaseParametrizedBackReferenceTypeDtoFromParametrizedPropertyTypeNode(
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
	private ParametrizedPropertyTypeDto createBaseParametrizedReferenceTypeDtoFromParametrizedPropertyTypeNode(
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
	private BaseParametrizedValueTypeDto createBaseParametrizedValueTypeDtoFromParametrizedPropertyTypeNode(
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
		PARAMETRIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredBackReferencedColumnIdNodeFromPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return backReferencedColumnNode.getSingleChildNodeHeader();
	}
	
	//method
	private DataType getDataTypeFromParametrizedPropertyTypeNode(final IMutableNode<?> parametrizedPropertyTypeNode) {
		
		final var dataTypeNode =
		PARAMETRIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredDataTypeNodeFromParametriedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
	}
	
	//method
	private PropertyType getPropertyTypeFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var propertyTypeNode =
		PARAMETRIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredPropertyTypeNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return PropertyType.fromSpecification(propertyTypeNode);
	}
	
	//method
	private String getReferencedTableIdFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var referencedTableIdNode =
		PARAMETRIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredReferencedTableIdNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return referencedTableIdNode.getSingleChildNodeHeader();
	}
}
