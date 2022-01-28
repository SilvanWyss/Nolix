//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;

//class
public abstract class ParametrizedPropertyType<DT> implements IParametrizedPropertyType<SchemaImplementation, DT> {
	
	//attribute
	private final Class<DT> dataType;
	
	//constructor
	public ParametrizedPropertyType(final Class<DT> dataTye) {
		
		Validator.assertThat(dataTye).thatIsNamed("data type").isNotNull();
		
		this.dataType = dataTye;
	}
	
	//method
	@Override
	public final Class<DT> getDataType() {
		return dataType;
	}
}
