//package declaration
package ch.nolix.system.schemaDataType;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.EntitySet;

//class
public abstract class BaseSchemaBackReferenceType extends SchemaDataType<EntitySet> {
	
	//attribute
	private final EntitySet backReferencedEntitySet;
	
	//constructor
	public BaseSchemaBackReferenceType(final EntitySet backReferencedEntitySet) {
		
		super(EntitySet.class);
		
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
	public final boolean references(final EntitySet entitySet) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBack(final EntitySet entitySet) {
		return (backReferencedEntitySet == entitySet);
	}
}
