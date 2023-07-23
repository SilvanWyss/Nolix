//package declaration
package ch.nolix.system.nodedatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedValueTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDto;

//class
public final class ParametrizedPropertyTypeNodeMapper {
	
	//method
	public Node createParametrizedPropertyTypeNodeFrom(final IParametrizedPropertyTypeDto parametrizedPropertyType) {
		return
		switch (parametrizedPropertyType.getPropertyType().getBaseType()) {
			case BASE_VALUE ->
				createParametrizedPropertyTypeNodeFrom((IBaseParametrizedValueTypeDto)parametrizedPropertyType);
			case BASE_REFERENCE ->
				createParametrizedPropertyTypeNodeFrom((IBaseParametrizedReferenceTypeDto)parametrizedPropertyType);
			case BASE_BACK_REFERENCE ->
				createParametrizedPropertyTypeNodeFrom((IBaseParametrizedBackReferenceTypeDto)parametrizedPropertyType);
			default ->
				throw InvalidArgumentException.forArgument(parametrizedPropertyType);
		};
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(
		IBaseParametrizedBackReferenceTypeDto baseParametrizedBackReferenceType
	) {
		return
		Node.withHeaderAndChildNode(
			SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE,
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
	private Node createParametrizedPropertyTypeNodeFrom(IBaseParametrizedReferenceTypeDto baseParametrizedReferenceType) {
		return
		Node.withHeaderAndChildNode(
			SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE,
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
	private Node createParametrizedPropertyTypeNodeFrom(final IBaseParametrizedValueTypeDto baseParametrizedValueType) {
		return
		Node.withHeaderAndChildNode(
			SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE,
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
