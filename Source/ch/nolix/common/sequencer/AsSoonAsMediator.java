//package declaration
package ch.nolix.common.sequencer;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.functionapi.IBooleanGetter;
import ch.nolix.common.validator.Validator;

//class
public final class AsSoonAsMediator {
	
	//attribute
	private final IBooleanGetter condition;
	
	//visibility-reduced constructor
	AsSoonAsMediator(final IBooleanGetter condition) {
		
		Validator.assertThat(condition).thatIsNamed(VariableNameCatalogue.CONDITION).isNotNull();
		
		this.condition = condition;
	}
	
	//method
	public Future runInBackground(final IAction job) {
		
		Validator.assertThat(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull();
		
		return new Future(new JobRunner(() -> runAsSoonAsConditionIsFulfilled(job), 1));
	}
	
	//method
	private void runAsSoonAsConditionIsFulfilled(final IAction job) {
		
		Validator.assertThat(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull();
		
		Sequencer.waitUntil(condition);
		
		job.run();
	}
}
