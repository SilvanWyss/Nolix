//package declaration
package ch.nolix.system.nodedatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ParameterizedPropertyTypeNodeMapper {
	
	//method
	public Node createParametrizedPropertyTypeNodeFrom(final IParameterizedPropertyTypeDto parameterizedPropertyType) {
		return
		switch (parameterizedPropertyType.getPropertyType().getBaseType()) {
			case BASE_VALUE ->
				createParametrizedPropertyTypeNodeFrom((IBaseParameterizedValueTypeDto)parameterizedPropertyType);
			case BASE_REFERENCE ->
				createParametrizedPropertyTypeNodeFrom((IBaseParameterizedReferenceTypeDto)parameterizedPropertyType);
			case BASE_BACK_REFERENCE ->
				createParametrizedPropertyTypeNodeFrom((IBaseParameterizedBackReferenceTypeDto)parameterizedPropertyType);
			default ->
				throw InvalidArgumentException.forArgument(parameterizedPropertyType);
		};
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(
		IBaseParameterizedBackReferenceTypeDto baseParametrizedBackReferenceType
	) {
		return
		Node.withHeaderAndChildNode(
			SubNodeHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE,
			Node.withHeaderAndChildNode(
				SubNodeHeaderCatalogue.PROPERTY_TYPE,
				baseParametrizedBackReferenceType.getPropertyType().toString()
			),
			Node.withHeaderAndChildNode(
				SubNodeHeaderCatalogue.DATA_TYPE,
				baseParametrizedBackReferenceType.getDataType().name()
			),
			Node.withHeaderAndChildNode(
				SubNodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID,
				baseParametrizedBackReferenceType.getBackReferencedColumnId()
			)
		);
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(IBaseParameterizedReferenceTypeDto baseParametrizedReferenceType) {
		return
		Node.withHeaderAndChildNode(
			SubNodeHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE,
			Node.withHeaderAndChildNode(
				SubNodeHeaderCatalogue.PROPERTY_TYPE,
				baseParametrizedReferenceType.getPropertyType().toString()
			),
			Node.withHeaderAndChildNode(
				SubNodeHeaderCatalogue.DATA_TYPE,
				baseParametrizedReferenceType.getDataType().name()
			),
			Node.withHeaderAndChildNode(
				SubNodeHeaderCatalogue.REFERENCED_TABLE_ID,
				baseParametrizedReferenceType.getReferencedTableId()
			)
		);
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(final IBaseParameterizedValueTypeDto baseParametrizedValueType) {
		return
		Node.withHeaderAndChildNode(
			SubNodeHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE,
			Node.withHeaderAndChildNode(
				SubNodeHeaderCatalogue.PROPERTY_TYPE,
				baseParametrizedValueType.getPropertyType().toString()
			),
			Node.withHeaderAndChildNode(
				SubNodeHeaderCatalogue.DATA_TYPE,
				baseParametrizedValueType.getDataType().name()
			)
		);
	}
}
