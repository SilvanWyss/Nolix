//package declaration
package ch.nolix.core.programcontrol.sequencer;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IAction;
import ch.nolix.core.functionapi.IBooleanGetter;

//class
public final class AsSoonAsMediator {
	
	//attribute
	private final IBooleanGetter condition;
	
	//constructor
	AsSoonAsMediator(final IBooleanGetter condition) {
		
		Validator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();
		
		this.condition = condition;
	}
	
	//method
	public Future runInBackground(final IAction job) {
		
		Validator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();
		
		return new Future(new JobRunner(() -> runAsSoonAsConditionIsFulfilled(job), 1));
	}
	
	//method
	private void runAsSoonAsConditionIsFulfilled(final IAction job) {
		
		Validator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();
		
		Sequencer.waitUntil(condition);
		
		job.run();
	}
}
