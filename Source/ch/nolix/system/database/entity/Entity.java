//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Identified;
import ch.nolix.common.attributeapi.mandatoryattributeapi.ShortDescripted;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentBelongsToUnexchangeableParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.valuecreator.ValueCreator;
import ch.nolix.element.elementapi.IElement;

//class
public abstract class Entity implements IElement, Identified, ShortDescripted {
	
	//static attribute
	private static long latestCreatedId = -1;
	
	//static method
	private static long createNextId() {
		
		var lId = System.currentTimeMillis();
		
		if (lId == latestCreatedId) {
			lId++;
		}
				
		latestCreatedId = lId;
		
		return lId;
	}
	
	//attributes
	private long id = createNextId();
	private EntityState state = EntityState.NEW;
	
	//optional attribute
	private IEntitySet<Entity> parentEntitySet;
	
	//multi-attributes
	private LinkedList<Property<?>> properties;
	private LinkedList<IAction> noteUpdateActions = new LinkedList<>();
	
	//method
	public final void addNoteUpdateAction(final IAction noteUpdateAction) {
		noteUpdateActions.addAtEnd(noteUpdateAction);
	}
	
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
	public final boolean canReferenceEntities() {
		return getRefProperties().contains(Property::canReferenceEntity);
	}
	
	//method
	public final void extractProperties() {
		extractPropertiesIfNotExtracted();
	}
	
	//method
	@Override
	public final void fillUpAttributesInto(final LinkedList<Node> list) {
		
		list.addAtEnd(Node.withHeader(getId()));
		
		fillUpPropertiesInto(list);
	}
	
	//method
	@Override
	public final long getId() {
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
	@SuppressWarnings("rawtypes")
	public final IContainer<BaseBackReference> getRefBackReferences() {
		return getRefProperties().getRefOfType(BaseBackReference.class);
	}
	
	//method
	@SuppressWarnings("rawtypes")
	public final IContainer<MultiReference> getRefMultiReferences() {
		return getRefProperties().getRefOfType(MultiReference.class);
	}
	
	//method
	public final IContainer<Property<?>> getRefProperties() {
		
		extractPropertiesIfNotExtracted();
		
		return properties;
	}
	
	//method
	@SuppressWarnings("rawtypes")
	public final IContainer<BaseReference> getRefReferences() {
		return getRefProperties().getRefOfType(BaseReference.class);
	}
	
	//method
	@SuppressWarnings({"rawtypes"})
	public final IContainer<SingleBackReference> getRefSingleBackReferences() {
		return getRefProperties().getRefOfType(SingleBackReference.class);
	}
	
	//method
	public final Node getRowSpecification() {
		
		final var rowSpecification = new Node();
		
		rowSpecification.addAttribute(Node.withHeader(getId()));
		
		for (final var p : getRefProperties()) {
			rowSpecification.addAttribute(p.getCellSpecification());
		}
		
		return rowSpecification;
	}
	
	//method
	@Override
	public String getShortDescription() {
		return getIdAsString();
	}
	
	//method
	public final EntityState getState() {
		return state;
	}
	
	//method
	public final <E extends Entity> boolean isAllowedToReferenceBack(final BaseReference<E> reference) {
		
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
	
	//method
	@SuppressWarnings("unchecked")
	public final <E extends Entity> void setParentEntitySet(final IEntitySet<E> parentEntitySet) {
		
		Validator.assertThat(parentEntitySet).thatIsNamed("parent EntitySet").isNotNull();
		
		supposeDoesNotBelongToEntitySet();
		
		this.parentEntitySet = (IEntitySet<Entity>)parentEntitySet;
	}
	
	//method
	public final void supposeCanBeSaved() {
		getRefProperties().forEach(Property::assertCanBeSaved);
	}
	
	//method
	public final void supposeIsNew() {
		if (!isNew()) {
			throw new InvalidArgumentException(this, "is not new");
		}
	}
	
	//visibility-reduced method
	void extractPropertiesWhenNotExtracted() {
		
		properties = new PropertyExtractor().getRefPropertiesOf(this);
		
		setParentEntityToProperties();
		
		validatePropertySchema();
	}
	
	//visibility-reduced method
	final void noteUpdate() {
		noteUpdateActions.forEach(IAction::run);
	}
	
	//visibility-reduced method
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
	
	//visibility-reduced method
	final void setId(final long id) {
		
		Validator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isPositive();
		
		this.id = id;
	}
	
	//visibility-reduced method
	final void setPersisted() {
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
	
	//visibility-reduced method
	final void setRejected() {
		state = EntityState.REJECTED;
	}
	
	//method
	final void setValues(final Iterable<BaseNode> valuesInOrder, final ValueCreator<BaseNode> valueCreator) {
		
		//Iterates the properties of the current entity and the given valuesInOrder together.
		final var propertiesIterator = getRefProperties().iterator();
		for (final var v : valuesInOrder) {
			
			final var property = propertiesIterator.next();
			
			//Enumerates the kind of the current property.
			switch (property.getPropertyKind()) {
				case VALUE:
					property.internalSetValue(valueCreator.ofType(property.getValueClass()).createFrom(v));					
					break;
				case OPTIONAL_VALUE:
					
					if (v.containsAttributes()) {
						property.internalSetValue(valueCreator.ofType(property.getValueClass()).createFrom(v));
					}
					
					break;
				case MULTI_VALUE:
					
					final var valueClass = property.getValueClass();
					
					property.internalSetValues(
						v.getRefAttributes().to(a -> valueCreator.ofType(valueClass).createFrom(a))						
					);
					
					break;
				case REFERENCE:		
					property.internalSetValue(v.toLong());
					break;
				case OPTIONAL_REFERENCE:
					
					if (v.containsAttributes()) {
						property.internalSetValue(v.toLong());
					}
					
					break;
				case MULTI_REFERENCE:
					
					property.internalSetValues(v.getRefAttributes().to(BaseNode::toLong));
					
					break;
				default:
					break;
			}
		}
	}
	
	//TODO: Refactor this method.
	//visibility-reduced method
	final void supposeCanReferenceBackAdditionally(final Entity entity, final String referencingPropertyHeader) {
		getRefBackReferences()
		.getRefSelected(br -> br.hasReferencingPropertyHeader(referencingPropertyHeader))
		.forEach(br -> br.supposeCanReferenceBackAdditionally(entity, referencingPropertyHeader));
	}
	
	//method
	private void extractPropertiesIfNotExtracted() {
		if (!propertiesAreExtracted()) {
			extractPropertiesWhenNotExtracted();
		}
	}
	
	//method
	private void fillUpPropertiesInto(final LinkedList<Node> list) {
		for (final var p : getRefProperties()) {
			list.addAtEnd(p.getSpecification());
		}
	}
	
	//method
	@SuppressWarnings("unchecked")
	private BaseReference<Entity> getRefRefenceByHeader(final String header) {
		return getRefReferences().getRefFirst(r -> r.hasHeader(header));
	}
	
	//method
	@SuppressWarnings("unchecked")
	private <E extends Entity> BaseBackReference<Entity> getRefBackReferenceForOrNull(final BaseReference<E> reference) {
		return
		getRefBackReferences()
		.getRefFirstOrNull(br -> br.getReferencingPropertyHeader().equals(reference.getHeader()));
	}
	
	//method
	private boolean propertiesAreExtracted() {
		return (properties != null);
	}
	
	//method
	private void setParentEntityToProperties() {
		for (final var p : properties) {
			p.internalSetParentEntity(this);
		}
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
	private void validatePropertySchema() {
		getRefProperties().forEach(Property::internalValidateSchema);
	}
}
