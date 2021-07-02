//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//class
public abstract class BaseParametrizedSchemaReferenceType extends ParametrizedPropertyType<IEntitySet> {
	
	//attribute
	private final IEntitySet referencedEntitySet;
	
	//constructor
	public BaseParametrizedSchemaReferenceType(final IEntitySet referencedEntitySet) {
		
		super(IEntitySet.class);
		
		Validator.assertThat(referencedEntitySet).thatIsNamed("referenced EntitySet").isNotNull();
		
		this.referencedEntitySet = referencedEntitySet;
	}
	
	//method
	@Override
	public final boolean isAnyBackReferenceType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyControlType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyReferenceType() {
		return true;
	}
	
	//method
	@Override
	public final boolean isAnyValueType() {
		return false;
	}
	
	//method
	@Override
	public final boolean references(final ITable<?, ?, ?> table) {
		return (referencedEntitySet == table);
	}
	
	//method
	@Override
	public final boolean referencesBack(final IColumn<?, ?> column) {
		return false;
	}
}
