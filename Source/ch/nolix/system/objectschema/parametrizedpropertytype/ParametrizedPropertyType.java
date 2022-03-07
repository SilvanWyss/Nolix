//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;

//class
public abstract class ParametrizedPropertyType<DT> implements IParametrizedPropertyType<SchemaImplementation, DT> {
	
	//attribute
	private final DataType dataType;
	
	//constructor
	public ParametrizedPropertyType(final DataType dataTye) {
		
		Validator.assertThat(dataTye).thatIsNamed(DataType.class).isNotNull();
		
		this.dataType = dataTye;
	}
	
	//method
	@Override
	public final DataType getDataType() {
		return dataType;
	}
}
