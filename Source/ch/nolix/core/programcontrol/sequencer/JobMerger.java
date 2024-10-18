package ch.nolix.core.programcontrol.sequencer;

import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;

public final class JobMerger {

  public Runnable createMergedJobForJobs(IContainer<Runnable> jobs) {

    GlobalValidator.assertThat(jobs).thatIsNamed(PluralLowerCaseVariableCatalogue.JOBS).isNotNull();

    return () -> runJobs(jobs);
  }

  private void runJobs(IContainer<Runnable> jobs) {
    for (var i = 1; i <= jobs.getCount(); i++) {
      try {
        jobs.getStoredAt1BasedIndex(i).run();
      } catch (final Throwable error) { //NOSONAR: All Throwables must be caught here.
        throw WrapperException.forErrorMessageAndError(
          "An error occured by running the " + i + "th job of the given " + jobs.getCount() + " jobs.",
          error);
      }
    }
  }
}
