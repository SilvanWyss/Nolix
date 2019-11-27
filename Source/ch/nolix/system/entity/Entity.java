//package declaration
package ch.nolix.system.entity;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.common.attributeAPI.OptionalIdentified;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.invalidArgumentExceptions.ArgumentBelongsToUnexchangeableParentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentHasAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.valueCreator.ValueCreator;
import ch.nolix.element.baseAPI.IElement;

//class
public class Entity implements IElement, OptionalIdentified {
	
	//attribute
	private EntityState state = EntityState.NEW;
	
	//optional attributes
	private long id = -1;
	private IEntitySet<Entity> parentEntitySet;
	
	//multi-attributes
	private List<Propertyoid<Entity>> properties;
	private List<BackReferenceoid<Entity>> backReferences;
	
	//method
	public final boolean belongsToDatabaseAdapter() {
		return belongsToEntitySet();
	}
	
	//method
	public final boolean belongsToEntitySet() {
		return (parentEntitySet != null);
	}
	
	//method
	public final boolean canReference(final Entity entity) {
		return getRefProperties().contains(p -> p.canReference(entity));
	}
	
	//method
	public final boolean canReferenceOtherEntities() {
		return getRefProperties().contains(p -> p.isReferenceProperty());
	}
	
	//method
	@Override
	public final List<Node> getAttributes() {
		
		final var attributes = new List<Node>();
		
		if (hasId()) {
			attributes.addAtEnd(new Node(getId()));
		}
		
		for (final var p : getRefProperties()) {
			attributes.addAtEnd(p.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	@Override
	public final long getId() {
	
		supposeHasId();
		
		return id;
	}
	
	//method
	public final IDatabaseAdapter getParentDatabaseAdapter() {
		return getParentEntitySet().getParentDatabaseAdapter();
	}
	
	//method
	public final IEntitySet<Entity> getParentEntitySet() {
		
		supposeBelongsToEntitySet();
		
		return parentEntitySet;
	}
	
	//method
	public final IContainer<BackReferenceoid<Entity>> getRefBackReferences() {
		
		extractPropertiesAndBackReferencesIfNotExtracted();
		
		return backReferences;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final IContainer<MultiReference<Entity>> getRefMultiReferences() {
		return getRefProperties().getRefOfType(MultiReference.class);
	}
	
	//method
	public final IContainer<Propertyoid<Entity>> getRefProperties() {
		
		extractPropertiesAndBackReferencesIfNotExtracted();
		
		return properties;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final IContainer<Referenceoid<Entity>> getRefReferences() {
		return getRefProperties().getRefOfType(Referenceoid.class);
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final IContainer<SingleBackReference<Entity>> getRefSingleBackReferences() {
		return getRefBackReferences().getRefOfType(SingleBackReference.class);
	}
	
	//method
	public final Node getRowSpecification() {
		
		final var rowSpecification = new Node();
		
		rowSpecification.addAttribute(new Node(getId()));
		
		for (final var p : getRefProperties()) {
			rowSpecification.addAttribute(p.getCellSpecification());
		}
		
		return rowSpecification;
	}
	
	//method
	public final EntityState getState() {
		return state;
	}
	
	//method
	@Override
	public final String getType() {
		return getClass().getSimpleName();
	}

	//method
	public final boolean hasId() {
		return (id > -1);
	}
	
	//method
	public final <E extends Entity> boolean isAllowedToReferenceBack(final Referenceoid<E> reference) {
		
		if (reference == null) {
			return false;
		}
		
		final var backReference = getRefBackReferenceForOrNull(reference);
		
		if (backReference == null) {
			return true;
		}
		
		if (backReference.canReferenceBackSeveralEntities()) {
			return true;
		}
		
		for (final var e : reference.getParentEntitySet().getRefEntities()) {
			final var r = e.getRefRefenceByHeader(reference.getHeader());
			if (r.referencesEntity() && !r.references(this)) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	public final boolean isConcerned() {
		
		//For a better performance, this implementation does not use all comfortable methods.	
		return (state == EntityState.CONCERNED);
	}
	
	//method
	public final boolean isDeleted() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntityState.DELETED);
	}
	
	//method
	public final boolean isEdited() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntityState.EDITED);
	}
	
	//method
	public final boolean isNew() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntityState.NEW);
	}
	
	//method
	public final boolean isReferenced() {
		return getParentDatabaseAdapter().getRefEntitySets().contains(es -> es.references(this));
	}
	
	//method
	public final boolean isPersisted() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntityState.PERSISTED);
	}
	
	//method
	public final boolean isRejected() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntityState.REJECTED);
	}
	
	//method
	public final boolean references(final Entity entity) {
		return getRefProperties().contains(p -> p.references(entity));
	}
	
	//method
	public final boolean references(final String header, final Entity entity) {
		return getRefProperties().contains(p -> p.hasHeader(header) && p.references(entity));
	}
	
	//method
	public final void setDeleted() {
		switch (getState()) {
			case NEW:
				throw new InvalidArgumentException(this, "is new");
			case PERSISTED:
			case EDITED:
			case CONCERNED:
				
				if (belongsToEntitySet()) {
					getParentEntitySet().deleteEntity(this);
				}
				
				state = EntityState.DELETED;
				
				break;
			
			case DELETED:
				break;
			case REJECTED:
				throw new InvalidArgumentException(this, "is rejected");
		}
	}
	
	//package-visible method
	@SuppressWarnings("unchecked")
	public final <E extends Entity> void setParentEntitySet(final IEntitySet<E> parentEntitySet) {
		
		Validator.suppose(parentEntitySet).thatIsNamed("parent EntitySet").isNotNull();
		
		supposeDoesNotBelongToEntitySet();
		
		this.parentEntitySet = (IEntitySet<Entity>)parentEntitySet;
	}
	
	//method
	public final void setId(final long id) {
		
		Validator.suppose(id).thatIsNamed(VariableNameCatalogue.ID).isPositive();
		
		supposeHasNoId();
		
		this.id = id;
	}
	
	//method
	public final void supposeCanBeSaved() {
		getRefProperties().forEach(Propertyoid::supposeCanBeSaved);
	}
	
	//method
	public final void supposeIsNew() {
		if (!isNew()) {
			throw new InvalidArgumentException(this, "is not new");
		}
	}
	
	//package-visible method
	final void setEdited() {
		switch (getState()) {
			case NEW:
				throw new InvalidArgumentException(this, "is new");
			case PERSISTED:
			case CONCERNED:
				
				state = EntityState.EDITED;
				
				if (belongsToEntitySet()) {
					getParentEntitySet().noteMutatedEntity(this);
				}
				
				break;
			case EDITED:
				break;
			case DELETED:
				throw new InvalidArgumentException(this, "is deleted");
			case REJECTED:
				throw new InvalidArgumentException(this, "is rejected");
		}
	}
	
	//TODO: Implement EntityAccessor.setPersisted(entity).
	//method
	public final void setPersisted() {
		switch (getState()) {
			case NEW:
				state = EntityState.PERSISTED;
				break;
			case PERSISTED:
				break;
			case EDITED:
				throw new InvalidArgumentException(this, "is changed");
			case CONCERNED:
				throw new InvalidArgumentException(this, "is concerned");
			case DELETED:
				throw new InvalidArgumentException(this, "is deleted");
			case REJECTED:
				throw new InvalidArgumentException(this, "is rejected");
		}
	}
	
	//package-visible method
	final void setRejected() {
		state = EntityState.REJECTED;
	}
	
	//TODO: Implement EntityAccessor.setValues(Iterable<BaseNode>, ValueCreator).
	//package-visible method
	public final void setValues(final Iterable<BaseNode> valuesInOrder, final ValueCreator valueCreator) {
		
		//Iterates the properties of the current entity and the given valuesInOrder together.
		final var propertiesIterator = getRefProperties().iterator();
		for (final var v : valuesInOrder) {
			
			final var property = propertiesIterator.next();
			
			//Enumerates the kind of the current property.
			switch (property.getPropertyKind()) {
				case DATA:
					property.internal_setValue(valueCreator.ofType(property.getValueClass()).createFromSpecification(v));					
					break;
				case OPTIONAL_DATA:
					
					if (v.containsAttributes()) {
						property.internal_setValue(valueCreator.ofType(property.getValueClass()).createFromSpecification(v));
					}
					
					break;
				case MULTI_DATA:
					
					final var valueClass = property.getValueClass();
					
					property.internal_setValues(
						v.getRefAttributes().to(a -> valueCreator.ofType(valueClass).createFromSpecification(a))						
					);
					
					break;
				case REFERENCE:		
					property.internal_setValue(v.toInt());
					break;
				case OPTIONAL_REFERENCE:
					
					if (v.containsAttributes()) {
						property.internal_setValue(v.toInt());
					}
					
					break;
				case MULTI_REFERENCE:
					
					property.internal_setValues(
						v.getRefAttributes().to(
							a -> a.toInt()
						)
					);
					
					break;
				default:
					break;
			}
		}
	}
	
	//TODO: Refactor this method.
	//package-visible method
	final void supposeCanReferenceBackAdditionally(final Entity entity, final String referencingPropertyHeader) {
		getRefBackReferences()
		.getRefSelected(br -> br.hasReferencingPropertyHeader(referencingPropertyHeader))
		.forEach(br -> br.supposeCanReferenceBackAdditionally(entity, referencingPropertyHeader));
	}
	
	//method
	private void extractProbablePropertyOrBackReferencesFromField(final Field field) {
		
		field.setAccessible(true);
		
		if (Propertyoid.class.isAssignableFrom(field.getType())) {
			try {
				
				@SuppressWarnings("unchecked")
				final var property = (Propertyoid<Entity>)(field.get(this));
				
				property.internal_setParentEntity(this);
				properties.addAtEnd(property);
			}
			catch (final IllegalArgumentException | IllegalAccessException exception) {
				throw new RuntimeException(exception);
			}
		}
		else if (BackReferenceoid.class.isAssignableFrom(field.getType())) {
			try {
				
				@SuppressWarnings("unchecked")
				final var backReference = (BackReferenceoid<Entity>)(field.get(this));
				
				backReference.setParentEntity(this);
				backReferences.addAtEnd(backReference);
			}
			catch (final IllegalArgumentException | IllegalAccessException exception) {
				throw new RuntimeException(exception);
			}
		}
	}
	
	//method
	public final void extractPropertiesAndBackReferences() {
		
		properties = new List<>();
		backReferences = new List<>();
		
		Class<?> cl = getClass();
		while (cl != null) {
			extractPropertiesAndBackReferencesFromClass(cl);		
			cl = cl.getSuperclass();
		}
	}
	
	//method
	private void extractPropertiesAndBackReferencesFromClass(final Class<?> pClass) {
		for (final var f : pClass.getDeclaredFields()) {
			extractProbablePropertyOrBackReferencesFromField(f);
		}
	}
	
	//method
	private void extractPropertiesAndBackReferencesIfNotExtracted() {
		if (!propertiesAndBackReferencesAreExtracted()) {
			extractPropertiesAndBackReferences();
		}
	}
	
	//method
	private Referenceoid<Entity> getRefRefenceByHeader(final String header) {
		return getRefReferences().getRefFirst(r -> r.hasHeader(header));
	}
	
	//method
	private <E extends Entity> BackReferenceoid<Entity> getRefBackReferenceForOrNull(final Referenceoid<E> reference) {
		return
		getRefBackReferences()
		.getRefFirstOrNull(br -> br.getReferencingPropertyHeader().equals(reference.getHeader()));
	}
	
	//method
	private boolean propertiesAndBackReferencesAreExtracted() {
		return (properties != null && backReferences != null);
	}
	
	//method
	private void supposeBelongsToEntitySet() {
		if (!belongsToEntitySet()) {
			throw new ArgumentDoesNotBelongToParentException(this, IEntitySet.class);
		}
	}
	
	//method
	private void supposeDoesNotBelongToEntitySet() {
		if (belongsToEntitySet()) {
			throw new ArgumentBelongsToUnexchangeableParentException(this, getParentEntitySet());
		}
	}
	
	//method
	private void supposeHasId() {
		if (!hasId()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ID);
		}
	}
	
	//method
	private void supposeHasNoId() {
		if (hasId()) {
			throw new ArgumentHasAttributeException(this, VariableNameCatalogue.ID);
		}
	}
}
