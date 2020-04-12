//package declaration
package ch.nolix.system.schemaDataTypes;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.EntitySet;
import ch.nolix.system.entity.PropertyKind;

//class
public abstract class SchemaDataType<C> {
	
	//attribute
	private final Class<C> contentClass;
	
	//constructor
	public SchemaDataType(final Class<C> contentClass) {
		
		Validator.assertThat(contentClass).thatIsNamed("content class").isNotNull();
		
		this.contentClass = contentClass;
	}
	
	//method declaration
	public abstract PropertyKind getPropertyKind();
	
	//method
	public final Class<C> getRefContentClass() {
		return contentClass;
	}
	
	//method declaration
	public abstract boolean isAnyBackReferenceType();
	
	//method declaration
	public abstract boolean isAnyControlType();
	
	//method declaration
	public abstract boolean isAnyReferenceType();
	
	//method declaration
	public abstract boolean isAnyValueType();
	
	//method declaration
	public abstract boolean references(EntitySet entitySet);
	
	//method declaration
	public abstract boolean referencesBack(EntitySet entitySet);
}
