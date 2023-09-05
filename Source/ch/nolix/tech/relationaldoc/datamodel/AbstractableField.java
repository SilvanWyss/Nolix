//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.datamodelapi.entityproperty.ContentType;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.OptionalReference;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractableFieldEvaluator;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractableFieldValidator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IContent;

//class
public final class AbstractableField extends Entity implements IAbstractableField {
	
	//constant
	public static final boolean DEFAULT_ABSTRACT_FLAG = false;
	
	//constant
	public static final Cardinality DEFAULT_CARDINALITY = Cardinality.TO_ONE;
	
	//constant
	private static final AbstractableFieldEvaluator ABSTRACTABLE_FIELD_EVALUATOR = new AbstractableFieldEvaluator();
	
	//constant
	private static final AbstractableFieldValidator ABSTRACTABLE_FIELD_VALIDATOR = new AbstractableFieldValidator();
	
	//attribute
	private final BackReference<AbstractableObject> parentObject =
	BackReference.forEntityAndBackReferencedPropertyName(AbstractableObject.class, "declaredFields");
	
	//attribute
	private final Value<String> name = new Value<>();
	
	//attribute
	private final Value<Boolean> abstractFlag = Value.withInitialValue(DEFAULT_ABSTRACT_FLAG);
	
	//attribute
	private final Value<String> cardinality = Value.withInitialValue(DEFAULT_CARDINALITY.toString());
	
	//TODO: Make Reference able to reference base types.
	//attribute
	private final OptionalReference<AbstractValueContent> abstractValueContent =
	OptionalReference.forEntity(AbstractValueContent.class);
	
	//TODO: Make Reference able to reference base types.
	//attribute
	private final OptionalReference<AbstractReferenceContent> abstractReferenceContent =
	OptionalReference.forEntity(AbstractReferenceContent.class);
	
	//TODO: Make Reference able to reference base types.
	//attribute
	private final OptionalReference<ConcreteValueContent> concreteValueContent =
	OptionalReference.forEntity(ConcreteValueContent.class);
	
	//TODO: Make Reference able to reference base types.
	//attribute
	private final OptionalReference<ConcreteReferenceContent> concreteReferenceContent =
	OptionalReference.forEntity(ConcreteReferenceContent.class);
	
	//method
	@Override
	public Cardinality getCardinality() {
		
		if (inheritsFromBaseField()) {
			return getStoredBaseField().getCardinality();
		}
		
		return Cardinality.valueOf(cardinality.getStoredValue());
	}
	
	//method
	@Override
	public ContentType getContentType() {
		
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public String getName() {
		
		if (inheritsFromBaseField()) {
			return getStoredBaseField().getName();
		}
		
		return name.getStoredValue();
	}
	
	//method
	@Override
	public IAbstractableField getStoredBaseField() {
		
		//TODO: Use a BackReference for a better performance.
		return
		getStoredParentObject()
		.getStoredDirectBaseTypes()
		.toFromGroups(IAbstractableObject::getStoredFields)
		.getStoredFirst(f -> f.hasSameNameAs(this));
	}
	
	//method
	@Override
	public IContent getStoredContent() {
		
		if (abstractValueContent.containsAny()) {
			return abstractValueContent.getReferencedEntity();
		}
		
		if (abstractReferenceContent.containsAny()) {
			return abstractValueContent.getReferencedEntity();
		}
		
		if (concreteValueContent.containsAny()) {
			return concreteValueContent.getReferencedEntity();
		}
		
		return concreteReferenceContent.getReferencedEntity();
	}
	
	//method
	@Override
	public IAbstractableObject getStoredParentObject() {
		return parentObject.getBackReferencedEntity();
	}
	
	//method
	@Override
	public boolean inheritsFromBaseField() {
		
		//TODO: Use a BackReference for a better performance.
		return
		getStoredParentObject()
		.getStoredDirectBaseTypes()
		.containsAny(bt -> bt.getStoredFields().containsAny(f -> f.hasSameNameAs(this)));
	}
	
	//method
	@Override
	public boolean isAbstract() {
		return abstractFlag.getStoredValue();
	}
	
	//method
	@Override
	public IAbstractableField setAsAbstract() {
		
		ABSTRACTABLE_FIELD_VALIDATOR.assertCanSetAsAbstract(this);
		
		//TODO: Update content.
		
		abstractFlag.setValue(true);
		
		return this;
	}
	
	//method
	@Override
	public IAbstractableField setAsConcrete() {
		
		ABSTRACTABLE_FIELD_VALIDATOR.assertCanSetAsConcrete(this);
		
		//TODO: Update content.
		
		abstractFlag.setValue(false);
		
		return this;
	}
	
	//method
	@Override
	public IAbstractableField setCardinality(final Cardinality cardinality) {
		
		//TODO: Implement.
		return this;
	}
	
	//method
	@Override
	public IAbstractableField setContent(final IContent content) {
		
		ABSTRACTABLE_FIELD_VALIDATOR.assertCanSetContent(this, content);
		
		updateContentOfRealisingFields(content);
		
		this.setContent(content);
		
		return this;
	}

	//method
	@Override
	public IAbstractableField setName(final String name) {
		
		if (inheritsFromBaseField()) {
			getStoredBaseField().setName(name);
		} else {
			
			ABSTRACTABLE_FIELD_VALIDATOR.assertCanSetName(this, name);
			
			this.name.setValue(name);
		}
		
		return this;
	}
	
	//method
	private void updateContentOfRealisingFields(final IContent content) {
		
		final var isAbstractValueContent = content instanceof IAbstractValueContent;
		
		final var realisingFields = ABSTRACTABLE_FIELD_EVALUATOR.getStoredRealisingFields(this);
		
		for (final var rf : realisingFields) {
			if (isAbstractValueContent) {
				rf.setContent(new ConcreteValueContent());
			} else {
				rf.setContent(new ConcreteReferenceContent());
			}
		}
	}
}
