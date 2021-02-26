//package declaration
package ch.nolix.system.database.schemadatatype;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;
//own imports
import ch.nolix.common.validator.Validator;

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
	public abstract DataType getPropertyKind();
	
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
	public abstract boolean references(IEntitySet entitySet);
	
	//method declaration
	public abstract boolean referencesBack(IEntitySet entitySet);
}
