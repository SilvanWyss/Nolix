//package declaration
package ch.nolix.techapi.relationaldocapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentNameable;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.techapi.relationaldocapi.baseapi.Abstractable;

//interface
public interface IAbstractableField extends Abstractable<IAbstractableField>, FluentNameable<IAbstractableField> {
	
	//method declaration
	Cardinality getCardinality();
	
	//method declaration
	IAbstractableField setCardinality(Cardinality cardinality);
}
