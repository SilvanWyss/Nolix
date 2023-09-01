//package declaration
package ch.nolix.tech.relationaldoc.dataevaluator;

//own imports
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
	public boolean canSetName(final IAbstractableField abstractableField, final String name) {
		return
		canSetName(name)
		&& abstractableField != null
		&& abstractableField.getStoredParentObject().getStoredFields().containsNone(f -> f.hasName(name))
		&& canSetNameBecauseOfSubTypesOfParentObject(abstractableField, name);
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
}