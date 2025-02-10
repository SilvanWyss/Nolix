package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class AsSoonAsMediator {

  private final BooleanSupplier condition;

  AsSoonAsMediator(final BooleanSupplier condition) {

    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalog.CONDITION).isNotNull();

    this.condition = condition;
  }

  public Future runInBackground(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    return new Future(new JobRunner(() -> runAsSoonAsConditionIsFulfilled(job), 1));
  }

  private void runAsSoonAsConditionIsFulfilled(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    GlobalFlowController.waitUntil(condition);

    job.run();
  }
}
