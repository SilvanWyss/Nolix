//package declaration
package ch.nolix.tech.relationaldoc.dataevaluator;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.tech.relationaldoc.datamodel.AbstractableField;
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
		&& getStoredRealisingFields(abstractableField).isEmpty();
	}
	
	//method
	public boolean canSetCardinality(final AbstractableField abstractableField, final Cardinality cardinality) {
		
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
