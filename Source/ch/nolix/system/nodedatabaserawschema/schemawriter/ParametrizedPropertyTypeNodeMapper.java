//package declaration
package ch.nolix.system.nodedatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedValueTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public final class ParametrizedPropertyTypeNodeMapper {
	
	//method
	public Node createParametrizedPropertyTypeNodeFrom(final IParametrizedPropertyTypeDTO parametrizedPropertyType) {
		switch (parametrizedPropertyType.getPropertyType().getBaseType()) {
			case BASE_VALUE:
				return createParametrizedPropertyTypeNodeFrom((IBaseParametrizedValueTypeDTO)parametrizedPropertyType);
			case BASE_REFERENCE:
				return createParametrizedPropertyTypeNodeFrom((IBaseParametrizedReferenceTypeDTO)parametrizedPropertyType);
			case BASE_BACK_REFERENCE:
				return createParametrizedPropertyTypeNodeFrom((IBaseParametrizedBackReferenceTypeDTO)parametrizedPropertyType);
			default:
				throw InvalidArgumentException.forArgument(parametrizedPropertyType);
		}
	}
	
	//method
	private Node createParametrizedPropertyTypeNodeFrom(
		IBaseParametrizedBackReferenceTypeDTO baseParametrizedBackReferenceType
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
	private Node createParametrizedPropertyTypeNodeFrom(IBaseParametrizedReferenceTypeDTO baseParametrizedReferenceType) {
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
	private Node createParametrizedPropertyTypeNodeFrom(final IBaseParametrizedValueTypeDTO baseParametrizedValueType) {
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
