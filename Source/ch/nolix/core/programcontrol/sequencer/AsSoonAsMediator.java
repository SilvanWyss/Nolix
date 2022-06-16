//package declaration
package ch.nolix.core.programcontrol.sequencer;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IAction;
import ch.nolix.core.functionuniversalapi.IBooleanGetter;
import ch.nolix.core.name.LowerCaseCatalogue;

//class
public final class AsSoonAsMediator {
	
	//attribute
	private final IBooleanGetter condition;
	
	//constructor
	AsSoonAsMediator(final IBooleanGetter condition) {
		
		GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();
		
		this.condition = condition;
	}
	
	//method
	public Future runInBackground(final IAction job) {
		
		GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();
		
		return new Future(new JobRunner(() -> runAsSoonAsConditionIsFulfilled(job), 1));
	}
	
	//method
	private void runAsSoonAsConditionIsFulfilled(final IAction job) {
		
		GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();
		
		GlobalSequencer.waitUntil(condition);
		
		job.run();
	}
}
