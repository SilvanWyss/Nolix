//package declaration
package ch.nolix.system.schemaDataTypes;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.EntitySet;

//class
public abstract class BaseSchemaReferenceType extends SchemaDataType<EntitySet> {
	
	//attribute
	private final EntitySet referencedEntitySet;
	
	//constructor
	public BaseSchemaReferenceType(final EntitySet referencedEntitySet) {
		
		super(EntitySet.class);
		
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
	public final boolean references(final EntitySet entitySet) {
		return (referencedEntitySet == entitySet);
	}
	
	//method
	@Override
	public final boolean referencesBack(final EntitySet entitySet) {
		return false;
	}
}
