//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.ParameterizedPropertyTypeNodeSearcher;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedBackReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.system.objectschema.schemadto.ParameterizedPropertyTypeDto;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public class ParameterizedPropertyTypeDtoMapper {
	
	//constant
	private static final ParameterizedPropertyTypeNodeSearcher PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER =
	new ParameterizedPropertyTypeNodeSearcher();
	
	//method
	public ParameterizedPropertyTypeDto createParametrizedProeprtyTypeDtoFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var propertyType = getPropertyTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode);
		
		return
		switch (propertyType.getBaseType()) {
			case BASE_VALUE ->
				createBaseParametrizedValueTypeDtoFromParametrizedPropertyTypeNode(
					parametrizedPropertyTypeNode,
					propertyType
				);
			case BASE_REFERENCE ->
				createBaseParametrizedReferenceTypeDtoFromParametrizedPropertyTypeNode(
					parametrizedPropertyTypeNode,
					propertyType
				);
			case BASE_BACK_REFERENCE ->
				createBaseParametrizedBackReferenceTypeDtoFromParametrizedPropertyTypeNode(
					parametrizedPropertyTypeNode,
					propertyType
				);
			default ->
				throw InvalidArgumentException.forArgument(propertyType);
		};
	}
	
	//method
	private ParameterizedPropertyTypeDto createBaseParametrizedBackReferenceTypeDtoFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParameterizedBackReferenceTypeDto(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode),
			getBackReferencedColumnIdFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
	
	//method
	private ParameterizedPropertyTypeDto createBaseParametrizedReferenceTypeDtoFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParameterizedReferenceTypeDto(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode),
			getReferencedTableIdFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
	
	//method
	private BaseParameterizedValueTypeDto createBaseParametrizedValueTypeDtoFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParameterizedValueTypeDto(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parametrizedPropertyTypeNode)
		);
	}
	
	//method
	private String getBackReferencedColumnIdFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var backReferencedColumnNode =
		PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredBackReferencedColumnIdNodeFromPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return backReferencedColumnNode.getSingleChildNodeHeader();
	}
	
	//method
	private DataType getDataTypeFromParametrizedPropertyTypeNode(final IMutableNode<?> parametrizedPropertyTypeNode) {
		
		final var dataTypeNode =
		PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredDataTypeNodeFromParametriedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
	}
	
	//method
	private PropertyType getPropertyTypeFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var propertyTypeNode =
		PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredPropertyTypeNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return PropertyType.fromSpecification(propertyTypeNode);
	}
	
	//method
	private String getReferencedTableIdFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parametrizedPropertyTypeNode
	) {
		
		final var referencedTableIdNode =
		PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredReferencedTableIdNodeFromParametrizedPropertyTypeNode(
			parametrizedPropertyTypeNode
		);
		
		return referencedTableIdNode.getSingleChildNodeHeader();
	}
}
