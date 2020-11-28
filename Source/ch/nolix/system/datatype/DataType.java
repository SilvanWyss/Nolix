//package declaration
package ch.nolix.system.datatype;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.schemadatatype.SchemaDataType;

//class
public abstract class DataType<C> {
	
	//attribute
	private final Class<C> contentClass;
	
	//constructor
	public DataType(final Class<C> contentClass) {
		
		Validator.assertThat(contentClass).thatIsNamed("content class").isNotNull();
		
		this.contentClass = contentClass;
	}
	
	//method declaration
	public abstract boolean canReference(Entity entity);
	
	//method declaration
	public abstract PropertyKind getPropertyKind();
	
	//method
	public final Class<C> getRefContentClass() {
		return contentClass;
	}
	
	//method declaration
	public abstract boolean isAnyBackReferenceType();
	
	//method declaration
	public abstract boolean isAnyReferenceType();
	
	//method declaration
	public abstract boolean isAnyTechnicalType();
	
	//method declaration
	public abstract boolean isAnyValueType();
	
	//method declaration
	public abstract SchemaDataType<?> toSchemaDataType(
		final IContainer<ch.nolix.system.databaseschemaadapter.EntitySet> schemaEntitySets
	);
}
