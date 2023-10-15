//package declaration
package ch.nolix.core.programcontrol.sequencer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IBooleanGetter;

//class
public final class AsSoonAsMediator {

  // attribute
  private final IBooleanGetter condition;

  // constructor
  AsSoonAsMediator(final IBooleanGetter condition) {

    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();

    this.condition = condition;
  }

  // method
  public Future runInBackground(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    return new Future(new JobRunner(() -> runAsSoonAsConditionIsFulfilled(job), 1));
  }

  // method
  private void runAsSoonAsConditionIsFulfilled(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    GlobalSequencer.waitUntil(condition);

    job.run();
  }
}
