//package declaration
package ch.nolix.system.schemaDataType;

//own import
import ch.nolix.common.validator.Validator;

//class
public abstract class BaseSchemaReferenceType extends SchemaDataType<IEntitySet> {
	
	//attribute
	private final IEntitySet referencedEntitySet;
	
	//constructor
	public BaseSchemaReferenceType(final IEntitySet referencedEntitySet) {
		
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
	public final boolean references(final IEntitySet entitySet) {
		return (referencedEntitySet == entitySet);
	}
	
	//method
	@Override
	public final boolean referencesBack(final IEntitySet entitySet) {
		return false;
	}
}
