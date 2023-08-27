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
		final IMutableNode<?> parameterizedPropertyTypeNode
	) {
		
		final var propertyType = getPropertyTypeFromParametrizedPropertyTypeNode(parameterizedPropertyTypeNode);
		
		return
		switch (propertyType.getBaseType()) {
			case BASE_VALUE ->
				createBaseParametrizedValueTypeDtoFromParametrizedPropertyTypeNode(
					parameterizedPropertyTypeNode,
					propertyType
				);
			case BASE_REFERENCE ->
				createBaseParametrizedReferenceTypeDtoFromParametrizedPropertyTypeNode(
					parameterizedPropertyTypeNode,
					propertyType
				);
			case BASE_BACK_REFERENCE ->
				createBaseParametrizedBackReferenceTypeDtoFromParametrizedPropertyTypeNode(
					parameterizedPropertyTypeNode,
					propertyType
				);
			default ->
				throw InvalidArgumentException.forArgument(propertyType);
		};
	}
	
	//method
	private ParameterizedPropertyTypeDto createBaseParametrizedBackReferenceTypeDtoFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parameterizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParameterizedBackReferenceTypeDto(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parameterizedPropertyTypeNode),
			getBackReferencedColumnIdFromParametrizedPropertyTypeNode(parameterizedPropertyTypeNode)
		);
	}
	
	//method
	private ParameterizedPropertyTypeDto createBaseParametrizedReferenceTypeDtoFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parameterizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParameterizedReferenceTypeDto(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parameterizedPropertyTypeNode),
			getReferencedTableIdFromParametrizedPropertyTypeNode(parameterizedPropertyTypeNode)
		);
	}
	
	//method
	private BaseParameterizedValueTypeDto createBaseParametrizedValueTypeDtoFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parameterizedPropertyTypeNode,
		final PropertyType propertyType
	) {
		return
		new BaseParameterizedValueTypeDto(
			propertyType,
			getDataTypeFromParametrizedPropertyTypeNode(parameterizedPropertyTypeNode)
		);
	}
	
	//method
	private String getBackReferencedColumnIdFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parameterizedPropertyTypeNode
	) {
		
		final var backReferencedColumnNode =
		PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredBackReferencedColumnIdNodeFromPropertyTypeNode(
			parameterizedPropertyTypeNode
		);
		
		return backReferencedColumnNode.getSingleChildNodeHeader();
	}
	
	//method
	private DataType getDataTypeFromParametrizedPropertyTypeNode(final IMutableNode<?> parameterizedPropertyTypeNode) {
		
		final var dataTypeNode =
		PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredDataTypeNodeFromParametriedPropertyTypeNode(
			parameterizedPropertyTypeNode
		);
		
		return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
	}
	
	//method
	private PropertyType getPropertyTypeFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parameterizedPropertyTypeNode
	) {
		
		final var propertyTypeNode =
		PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredPropertyTypeNodeFromParametrizedPropertyTypeNode(
			parameterizedPropertyTypeNode
		);
		
		return PropertyType.fromSpecification(propertyTypeNode);
	}
	
	//method
	private String getReferencedTableIdFromParametrizedPropertyTypeNode(
		final IMutableNode<?> parameterizedPropertyTypeNode
	) {
		
		final var referencedTableIdNode =
		PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredReferencedTableIdNodeFromParametrizedPropertyTypeNode(
			parameterizedPropertyTypeNode
		);
		
		return referencedTableIdNode.getSingleChildNodeHeader();
	}
}
