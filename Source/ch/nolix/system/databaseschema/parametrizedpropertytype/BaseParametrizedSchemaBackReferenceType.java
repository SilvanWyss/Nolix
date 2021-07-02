//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//class
public abstract class BaseParametrizedSchemaBackReferenceType extends ParametrizedPropertyType<IEntitySet> {
	
	//attribute
	private final IEntitySet backReferencedEntitySet;
	
	//constructor
	public BaseParametrizedSchemaBackReferenceType(final IEntitySet backReferencedEntitySet) {
		
		super(IEntitySet.class);
		
		Validator.assertThat(backReferencedEntitySet).thatIsNamed("back-referenced EntitySet").isNotNull();
		
		this.backReferencedEntitySet = backReferencedEntitySet;
	}
	
	//method
	@Override
	public final boolean isAnyBackReferenceType() {
		return true;
	}
	
	//method
	@Override
	public final boolean isAnyControlType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyReferenceType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyValueType() {
		return false;
	}
	
	//method
	@Override
	public final boolean references(final ITable<?, ?, ?> table) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBack(final IColumn<?, ?> column) {
		return (backReferencedEntitySet == column);
	}
}
