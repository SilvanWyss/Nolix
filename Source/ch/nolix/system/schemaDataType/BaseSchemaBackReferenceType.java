//package declaration
package ch.nolix.system.schemaDataType;

//own import
import ch.nolix.common.validator.Validator;

//class
public abstract class BaseSchemaBackReferenceType extends SchemaDataType<IEntitySet> {
	
	//attribute
	private final IEntitySet backReferencedEntitySet;
	
	//constructor
	public BaseSchemaBackReferenceType(final IEntitySet backReferencedEntitySet) {
		
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
	public final boolean references(final IEntitySet entitySet) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBack(final IEntitySet entitySet) {
		return (backReferencedEntitySet == entitySet);
	}
}
