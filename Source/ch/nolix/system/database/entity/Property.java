//package declaration
package ch.nolix.system.database.entity;

//Java imports
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.reflectionhelper.GlobalReflectionHelper;
import ch.nolix.element.elementapi.IElement;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public abstract class Property<V> implements Named, IElement<Property<V>> {
	
	//optional attribute
	private Entity parentEntity;
	
	//method declaration
	public abstract void assertCanBeSaved();
	
	//method
	public final boolean belongsToEntity() {
		return (parentEntity != null);
	}
	
	//method declaration
	public abstract boolean canBeSeveral();
	
	//method declaration
	public abstract boolean canReference(Entity entity);
	
	//method
	public abstract boolean canReferenceEntity();
	
	//method
	@Override
	public final void fillUpAttributesInto(final LinkedList<Node> list) {
		for (final var v : internalGetValues()) {
			list.addAtEnd(Node.fromString(v.toString()));
		}
	}
	
	//method
	public final Node getCellSpecification() {
		
		final var attributes = getAttributes();
		
		if (attributes.getElementCount() < 2) {
			return Node.withHeader(attributes.toString());
		}
		
		return Node.withAttributes(attributes);
	}
	
	//method
	@Override
	public final String getName() {
		return GlobalReflectionHelper.getFieldName(getParentEntity(), this);
	}
	
	//method
	public final IDatabaseAdapter getParentDatabaseAdapter() {
		return getParentEntitySet().getParentDatabaseAdapter();
	}
	
	//method
	public final Entity getParentEntity() {
		
		assertBelongsToEntity();
		
		return parentEntity;
	}
	
	//method
	public final IEntitySet<Entity> getParentEntitySet() {
		return getParentEntity().getParentEntitySet();
	}
	
	//method
	public abstract PropertyType getPropertyKind();
	
	//method
	public final Node getSpecificationAsAttribute() {
		return getSpecificationAs(getName());
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final Class<V> getValueClass() {
		
		final var valueClass = GlobalReflectionHelper.getRefField(getParentEntity(), this).getGenericType();
		
		return (Class<V>)((ParameterizedType)valueClass).getActualTypeArguments()[0];
	}
	
	//method
	public final String getValueType() {
		return getValueClass().getSimpleName();
	}
	
	//method
	public final boolean isAtMostOne() {
		return !canBeSeveral();
	}
	
	//method declaration
	public abstract boolean references(Entity entity);
	
	//method declaration
	protected abstract void internalClear();
	
	//method declaration
	protected abstract LinkedList<Object> internalGetValues();
	
	//method
	protected final void internalNoteUpdate() {
		if (belongsToEntity()) {
			
			if (!parentEntity.isNew()) {
				parentEntity.setEdited();
			}
			
			parentEntity.noteUpdate();
		}
	}
	
	//method declaration
	protected abstract void internalSetValue(Object value);
	
	//method declaration
	protected abstract void internalSetValues(IContainer<Object> values);
	
	//method
	protected final void internalSetParentEntity(final Entity parentEntity) {
		
		Validator.assertThat(parentEntity).thatIsNamed("parent entity").isNotNull();
		
		assertDoesNotBelongToNoEntity();
		
		this.parentEntity = parentEntity;
	}
	
	//method declaration
	protected abstract void internalValidateSchema();
	
	//method
	private void assertBelongsToEntity() {
		if (!belongsToEntity()) {			
			throw new ArgumentDoesNotBelongToParentException(this, Entity.class);
		}
	}
	
	//method
	private void assertDoesNotBelongToNoEntity() {
		if (belongsToEntity()) {
			throw new ArgumentBelongsToParentException(this, getParentEntity());
		}
	}
}
