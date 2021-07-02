//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public abstract class ParametrizedPropertyType<DT> {
	
	//attribute
	private final Class<DT> dataType;
	
	//constructor
	public ParametrizedPropertyType(final Class<DT> dataTye) {
		
		Validator.assertThat(dataTye).thatIsNamed("data type").isNotNull();
		
		this.dataType = dataTye;
	}
	
	//method
	public final Class<DT> getDataType() {
		return dataType;
	}
	
	//method declaration
	public abstract PropertyType getPropertyType();
	
	//method declaration
	public abstract boolean isAnyBackReferenceType();
	
	//method declaration
	public abstract boolean isAnyControlType();
	
	//method declaration
	public abstract boolean isAnyReferenceType();
	
	//method declaration
	public abstract boolean isAnyValueType();
	
	//method declaration
	public abstract boolean references(IEntitySet entitySet);
	
	//method declaration
	public abstract boolean referencesBack(IEntitySet entitySet);
}
