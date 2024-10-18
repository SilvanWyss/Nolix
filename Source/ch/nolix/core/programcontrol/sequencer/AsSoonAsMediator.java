package ch.nolix.core.programcontrol.sequencer;

import java.util.function.BooleanSupplier;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class AsSoonAsMediator {

  private final BooleanSupplier condition;

  AsSoonAsMediator(final BooleanSupplier condition) {

    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalogue.CONDITION).isNotNull();

    this.condition = condition;
  }

  public Future runInBackground(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    return new Future(new JobRunner(() -> runAsSoonAsConditionIsFulfilled(job), 1));
  }

  private void runAsSoonAsConditionIsFulfilled(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    GlobalSequencer.waitUntil(condition);

    job.run();
  }
}
