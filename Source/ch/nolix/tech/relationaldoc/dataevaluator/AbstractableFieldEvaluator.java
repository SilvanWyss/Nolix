//package declaration
package ch.nolix.tech.relationaldoc.dataevaluator;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractReferenceContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteReferenceContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteValueContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IContent;

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
	public boolean canSetContent(final IAbstractableField abstractableField, final IContent content) {
		
		if (abstractableField == null) {
			return false;
		}
		
		if (abstractableField.isAbstract()) {
			return canSetContentWhenIsAbstract(abstractableField, content);
		}
		
		return canSetContentWhenIsConcrete(abstractableField, content);
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
	private boolean canSetContentWhenIsAbstract(final IAbstractableField abstractableField, final IContent content) {
		return
		abstractableField.getCardinality() != Cardinality.TO_ONE
		&& (content instanceof IAbstractValueContent || content instanceof IAbstractReferenceContent);
	}
	
	//method
	private boolean canSetContentWhenIsConcrete(final IAbstractableField abstractableField, final IContent content) {
		
		if (abstractableField.getCardinality() == Cardinality.TO_ONE) {
			
			if (content instanceof IConcreteValueContent concreteValueContent) {
				return concreteValueContent.getStoredConcreteParameterizedValueContent().getStoredValues().containsAny();
			}
			
			if (content instanceof IConcreteReferenceContent concreteReferenceContent) {
				return concreteReferenceContent.getStoredReferencedObjects().containsAny();
			}
		}
		
		return (content instanceof IConcreteValueContent || content instanceof IConcreteReferenceContent);
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
