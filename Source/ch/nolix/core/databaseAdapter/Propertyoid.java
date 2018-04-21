//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class Propertyoid<V> {
	
	//attribute
	private Entity entity;
	
	//method
	@SuppressWarnings("unchecked")
	public final Class<V> getValueClass() {
		return (Class<V>)getClass().getGenericInterfaces()[0];
	}
	
	//method
	public final PropertyKind getPropertyKind() {
		return getPropertyType().getPropertyKind();
	}
	
	//abstract method
	public abstract PropertyoidType<V> getPropertyType() ;
	
	//method
	public final boolean isDataProperty() {
		return getPropertyType().isDataType();
	}
	
	//method
	public final boolean isReferenceProperty() {
		return getPropertyType().isReferenceType();
	}
	
	//method
	protected void noteChange() {
		if (belongsToEntity()) {
			entity.setEdited();
		}
	}
	
	//method
	protected void setEntity(final Entity entity) {
		
		Validator
		.suppose(entity)
		.thatIsOfType(Entity.class)
		.isNotNull();
		
		supposeBelongsToNoEntity();
		
		this.entity = entity;
	}
	
	//method
	private boolean belongsToEntity() {
		return (entity != null);
	}
	
	//method
	private void supposeBelongsToNoEntity() {
		if (belongsToEntity()) {
			throw new InvalidStateException(
				this,
				"belongs already to an entity"
			);
		}
	}
}
