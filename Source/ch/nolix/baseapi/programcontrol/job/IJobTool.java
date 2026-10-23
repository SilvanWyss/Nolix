/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.job;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IJobTool {
  /**
   * @param jobs
   * @return a new job that runs the given jobs one after another
   */
  Runnable createConcatenatedJobFromJobs(ExtendedIterable<Runnable> jobs);
}
