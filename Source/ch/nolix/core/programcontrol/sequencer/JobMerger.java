//package declaration
package ch.nolix.core.programcontrol.sequencer;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PluralLowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;

//class
public final class JobMerger {

  // method
  public IAction createMergedJobForJobs(IContainer<IAction> jobs) {

    GlobalValidator.assertThat(jobs).thatIsNamed(PluralLowerCaseCatalogue.JOBS).isNotNull();

    return () -> runJobs(jobs);
  }

  // method
  private void runJobs(IContainer<IAction> jobs) {
    for (var i = 1; i <= jobs.getElementCount(); i++) {
      try {
        jobs.getStoredAt1BasedIndex(i).run();
      } catch (final Throwable error) { // NOSONAR: All Throwables must be caught here.
        throw WrapperException.forErrorMessageAndError(
            "An error occured by running the " + i + "th job of the given " + jobs.getElementCount() + " jobs.",
            error);
      }
    }
  }
}
