//package declaration
package ch.nolix.system.entity;

//Java import
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.common.attributeAPI.Headered;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.reflectionHelper.ReflectionHelper;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementAPI.IElement;

//class
public abstract class Property<V> implements Headered, IElement {
	
	//optional attribute
	private Entity parentEntity;
	
	//method
	public final boolean belongsToDatabaseAdapter() {
		return (belongsToEntity() && getParentEntity().belongsToDatabaseAdapter());
	}
	
	//method
	public final boolean belongsToEntity() {
		return (parentEntity != null);
	}
	
	//method
	public final boolean belongsToEntitySet() {
		return (belongsToEntity() && getParentEntity().belongsToEntitySet());
	}
	
	//method declaration
	public abstract boolean canReference(Entity entity);
	
	//method
	public abstract boolean canReferenceEntity();
	
	//method
	@Override
	public final LinkedList<Node> getAttributes() {
		return
		internalGetValues()
		.to(v -> Node.fromString(v.toString()));
	}
	
	//method
	/**
	 * @return the cell specification of the current {@link Property}.
	 */
	public abstract Node getCellSpecification();
	
	//method
	@Override
	public final String getHeader() {
		return ReflectionHelper.getFieldName(getParentEntity(), this);
	}
	
	//method
	public final IDatabaseAdapter getParentDatabaseAdapter() {
		return getParentEntitySet().getParentDatabaseAdapter();
	}
	
	//method
	public final Entity getParentEntity() {
		
		supposeBelongsToEntity();
		
		return parentEntity;
	}
	
	//method
	public final IEntitySet<Entity> getParentEntitySet() {
		return getParentEntity().getParentEntitySet();
	}
	
	//method
	public abstract PropertyKind getPropertyKind();
	
	//method
	@Override
	public final String getType() {
		return ReflectionHelper.getFieldName(getParentEntity(), this);
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final Class<V> getValueClass() {
		
		final var actualClass =
		ReflectionHelper.getRefField(getParentEntity(), this).getGenericType();
		
		return
		(Class<V>)
		((ParameterizedType)actualClass).getActualTypeArguments()[0];
	}
	
	//method
	public final String getValueType() {
		return getValueClass().getSimpleName();
	}
	
	//method declaration
	public abstract boolean references(final Entity entity);
	
	//method declaration
	public abstract void supposeCanBeSaved();
	
	//method declaration
	protected abstract void internalClear();
	
	//method declaration
	protected abstract LinkedList<Object> internalGetValues();
	
	//method
	protected void internalNoteUpdate() {
		if (belongsToEntity() && !parentEntity.isNew()) {
			parentEntity.setEdited();
		}
	}
	
	//method declaration
	protected abstract void internalSetValue(Object value);
	
	//method declaration
	protected abstract void internalSetValues(IContainer<Object> values);
	
	//method
	protected void internalSetParentEntity(final Entity parentEntity) {
		
		Validator
		.assertThat(parentEntity)
		.thatIsNamed("parent entity")
		.isNotNull();
		
		supposeBelongsToNoEntity();
		
		this.parentEntity = parentEntity;
	}
	
	//method
	private void supposeBelongsToEntity() {
		if (!belongsToEntity()) {
			throw new InvalidArgumentException(
				this,
				"does not belong to a entity"
			);
		}
	}
	
	//method
	private void supposeBelongsToNoEntity() {
		if (belongsToEntity()) {
			throw new InvalidArgumentException(
				this,
				"belongs to an entity"
			);
		}
	}
}
