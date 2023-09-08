//package declaration
package ch.nolix.tech.relationaldoc.dataevaluator;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;

//class
public final class AbstractableFieldEvaluator {
	
	//method
	public boolean canBeSetAsAbstract(final IAbstractableField abstractableField) {
		return
		abstractableField != null
		&& !abstractableField.inheritsFromBaseField()
		&& canBeSetAsAbstractBecauseOfParentObject(abstractableField);
	}
	
	//method
	public boolean canBeSetAsConcrete(final IAbstractableField abstractableField) {
		return
		abstractableField != null
		&& canBeSetAsConcreteWhenIsNotNull(abstractableField);
	}
	
	//method
	public boolean canBeSetForReferences(final IAbstractableField abstractableField) {
		
		if (abstractableField != null) {
			
			if (abstractableField.isForReferences()) {
				return true;
			}
			
			final var realisingFields = getStoredRealisingFields(abstractableField);
			
			if (realisingFields.isEmpty()) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	public boolean canBeSetForValues(final IAbstractableField abstractableField) {
		return
		abstractableField != null
		&& canBeSetForValuesWhenIsNotNull(abstractableField);
	}
	
	//method
	public boolean canSetCardinality(final IAbstractableField abstractableField, final Cardinality cardinality) {
		
		if (abstractableField != null && cardinality != null) {
			
			if (abstractableField.getCardinality() == cardinality || cardinality == Cardinality.TO_MANY) {
				return true;
			}
			
			if (abstractableField.isAbstract()) {
				final var realisingFields = getStoredRealisingFields(abstractableField);
				
				if (realisingFields.isEmpty()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	//method
	public boolean canSetName(final IAbstractableField abstractableField, final String name) {
		return
		canSetName(name)
		&& abstractableField != null
		&& abstractableField.getStoredParentObject().getStoredFields().containsNone(f -> f.hasName(name))
		&& canSetNameBecauseOfSubTypesOfParentObject(abstractableField, name);
	}
	
	//method
	public IContainer<? extends IAbstractableField> getStoredRealisingFields(final IAbstractableField field) {
		
		if (field == null || field.isConcrete()) {
			return new ImmutableList<>();
		}
		
		return getStoredRealisingFieldsWhenIsAbstract(field);
	}
	
	//method
	private boolean canBeSetAsAbstractBecauseOfParentObject(final IAbstractableField abstractableField) {
		return
		abstractableField != null
		&& abstractableField.getStoredParentObject().isAbstract();
	}
	
	//method
	private boolean canBeSetAsConcreteWhenIsNotNull(final IAbstractableField abstractableField) {
		
		final var realisingFields = getStoredRealisingFields(abstractableField);
		
		//TODO: return realisingFields.containsOnly(IAbstractableField::isEmpty);
		return true;
	}
	
	//method
	private boolean canBeSetForValuesWhenIsAbstractAndForReferences(final IAbstractableField abstractableField) {
		
		final var realisingFields = getStoredRealisingFields(abstractableField);
		
		//TODO: return realisingFields.containsOnly(IAbstractableField::isEmpty);
		return true;
	}

	//method
	private boolean canBeSetForValuesWhenIsConcreteAndForReferences(final IAbstractableField abstractableField) {
		return !abstractableField.inheritsFromBaseField();
	}
	
	//method
	private boolean canBeSetForValuesWhenIsForReferences(final IAbstractableField abstractableField) {
		
		if (abstractableField.isAbstract()) {
			return canBeSetForValuesWhenIsAbstractAndForReferences(abstractableField);
		}
		
		return canBeSetForValuesWhenIsConcreteAndForReferences(abstractableField);
	}
	
	//method
	private boolean canBeSetForValuesWhenIsForValues(final IAbstractableField abstractableField) {
		return true;
	}
	
	//method
	private boolean canBeSetForValuesWhenIsNotNull(final IAbstractableField abstractableField) {
		
		if (abstractableField.isForValues()) {
			return canBeSetForValuesWhenIsForValues(abstractableField);
		}
		
		return canBeSetForValuesWhenIsForReferences(abstractableField);
	}
	
	//method
	private boolean canSetName(final String name) {
		return
		name != null
		&& !name.isBlank();
	}
	
	//method
	private boolean canSetNameBecauseOfSubTypesOfParentObject(
		final IAbstractableField abstractableField,
		final String name
	) {
		return
		abstractableField != null
		&&
		abstractableField.getStoredParentObject().getStoredSubTypes().containsNone(cst -> cst.hasName(name));
	}
	
	//method
	private IContainer<? extends IAbstractableField> getStoredRealisingFieldsWhenIsAbstract(
		final IAbstractableField field
	) {
		return
		field
		.getStoredParentObject()
		.getStoredSubTypes()
		.toFromGroups(st -> st.getStoredDeclaredFields().getStoredSelected(df -> df.hasSameNameAs(field)));
	}
}
