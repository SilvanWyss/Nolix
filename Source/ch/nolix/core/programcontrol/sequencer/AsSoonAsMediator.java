//package declaration
package ch.nolix.core.programcontrol.sequencer;

import java.util.function.BooleanSupplier;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
public final class AsSoonAsMediator {

  //attribute
  private final BooleanSupplier condition;

  //constructor
  AsSoonAsMediator(final BooleanSupplier condition) {

    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();

    this.condition = condition;
  }

  //method
  public Future runInBackground(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    return new Future(new JobRunner(() -> runAsSoonAsConditionIsFulfilled(job), 1));
  }

  //method
  private void runAsSoonAsConditionIsFulfilled(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    GlobalSequencer.waitUntil(condition);

    job.run();
  }
}
