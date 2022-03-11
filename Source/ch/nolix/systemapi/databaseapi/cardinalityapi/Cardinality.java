//package declaration
package ch.nolix.systemapi.databaseapi.cardinalityapi;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;

//enum
public enum Cardinality {
	TO_ONE(BaseCardinality.SINGLE),
	TO_ONE_OR_NONE(BaseCardinality.SINGLE),
	TO_MANY(BaseCardinality.MULTI);
	
	//attribute
	private final BaseCardinality baseCardinality;
	
	//constructor
	Cardinality(final BaseCardinality baseCardinality) {
		
		Validator.assertThat(baseCardinality).thatIsNamed(BaseCardinality.class).isNotNull();
		
		this.baseCardinality = baseCardinality;
	}
	
	//method
	public final BaseCardinality getBaseCardinality() {
		return baseCardinality;
	}
}
