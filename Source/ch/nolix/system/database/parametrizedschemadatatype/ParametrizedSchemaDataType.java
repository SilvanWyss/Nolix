//package declaration
package ch.nolix.system.database.parametrizedschemadatatype;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public abstract class ParametrizedSchemaDataType<C> {
	
	//attribute
	private final Class<C> contentClass;
	
	//constructor
	public ParametrizedSchemaDataType(final Class<C> contentClass) {
		
		Validator.assertThat(contentClass).thatIsNamed("content class").isNotNull();
		
		this.contentClass = contentClass;
	}
	
	//method declaration
	public abstract PropertyType getPropertyKind();
	
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
