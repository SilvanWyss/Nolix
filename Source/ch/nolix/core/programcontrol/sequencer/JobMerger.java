//package declaration
package ch.nolix.core.programcontrol.sequencer;

import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PluralLowerCaseCatalogue;
import ch.nolix.coreapi.functionuniversalapi.IAction;

//class
public final class JobMerger {
	
	//static attribute
	public static final JobMerger INSTANCE = new JobMerger();
	
	//constructor
	private JobMerger() {}
	
	//method
	public IAction createMergedJobForJobs(final IAction... jobs) {
		
		GlobalValidator.assertThat(jobs).thatIsNamed(PluralLowerCaseCatalogue.JOBS).isNotNull();
		
		return () -> runJobs(jobs);
	}
	
	//method
	private void runJobs(final IAction... jobs) {
		for (var i = 0; i < jobs.length; i++) {
			try {
				jobs[i].run();
			} catch (final Throwable error) {
				throw
				WrapperException.forErrorMessageAndError(
					"An error occured by running the " + (i + 1) + "th job of the given " + jobs.length + " jobs.",
					error
				);
			}
		}
	}
}
