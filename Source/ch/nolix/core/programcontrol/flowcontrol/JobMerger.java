package ch.nolix.core.programcontrol.flowcontrol;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.PluralLowerCaseVariableCatalog;

public final class JobMerger {
  public Runnable createMergedJobForJobs(IContainer<Runnable> jobs) {
    Validator.assertThat(jobs).thatIsNamed(PluralLowerCaseVariableCatalog.JOBS).isNotNull();

    return () -> runJobs(jobs);
  }

  private void runJobs(IContainer<Runnable> jobs) {
    for (var i = 1; i <= jobs.getCount(); i++) {
      try {
        jobs.getStoredAtOneBasedIndex(i).run();
      } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.
        throw WrapperException.forErrorMessageAndError(
          "An error occured by running the " + i + "th job of the given " + jobs.getCount() + " jobs.",
          error);
      }
    }
  }
}
