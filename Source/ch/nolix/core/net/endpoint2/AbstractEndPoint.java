package ch.nolix.core.net.endpoint2;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.baseendpoint.AbstractBaseEndPoint;
import ch.nolix.coreapi.net.endpoint2.IEndPoint;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractEndPoint extends AbstractBaseEndPoint implements IEndPoint {
  private static final long REPLIER_GETTING_DELAY_IN_MILLISECONDS = 5000;

  private Function<String, String> replier;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasReplier() {
    return (replier != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setReplier(final UnaryOperator<String> replier) {
    //Asserts that the given replier is not null.
    Validator.assertThat(replier).thatIsNamed("replier").isNotNull();

    //Sets the replier of this end point.
    this.replier = replier;
  }

  /**
   * @throws ClosedArgumentException if the current {@link AbstractEndPoint} is
   *                                 closed.
   */
  protected final void assertIsOpen() {
    if (isClosed()) {
      throw ClosedArgumentException.forArgument(this);
    }
  }

  /**
   * @return the replier of this end point.
   * @throws ArgumentDoesNotHaveAttributeException if this end point does not have
   *                                               a replier.
   */
  protected final Function<String, String> getStoredReplier() {
    final long startTimeInMilliseconds = System.currentTimeMillis();

    //This loop suffers from being optimized away by the compiler or the JVM.
    while (!hasReplier()) {
      //The following statement, that is actually unnecessary,
      //makes that the current loop is not optimized away.
      System.out.flush(); //NOSONAR: This statement is used to keep the loop.

      if (System.currentTimeMillis() - startTimeInMilliseconds > REPLIER_GETTING_DELAY_IN_MILLISECONDS) {
        throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "replier");
      }
    }

    return replier;
  }
}
