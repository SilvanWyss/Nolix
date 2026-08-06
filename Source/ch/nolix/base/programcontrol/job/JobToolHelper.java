/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.job;

import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public final class JobToolHelper {
  private JobToolHelper() {
  }

  public static void runJobs(final ExtendedIterable<Runnable> jobs) {
    for (var i = 1; i <= jobs.getCount(); i++) {
      try {
        jobs.getStoredAtOneBasedIndex(i).run();
      } catch (final Throwable error) { // NOSONAR: All errors must be caught.
        throw //
        WrapperException.forErrorMessageAndError(
          "An error occured by running the " + i + "th job of the given " + jobs.getCount() + " jobs.",
          error);
      }
    }
  }
}
