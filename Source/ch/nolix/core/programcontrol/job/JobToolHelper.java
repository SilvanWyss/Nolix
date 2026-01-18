/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.programcontrol.job;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public final class JobToolHelper {
  private JobToolHelper() {
  }

  public static void runJobs(final IContainer<Runnable> jobs) {
    for (var i = 1; i <= jobs.getCount(); i++) {
      try {
        jobs.getStoredAtOneBasedIndex(i).run();
      } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.
        throw //
        WrapperException.forErrorMessageAndError(
          "An error occured by running the " + i + "th job of the given " + jobs.getCount() + " jobs.",
          error);
      }
    }
  }
}
