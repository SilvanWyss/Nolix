/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.job;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IJobTool {
  Runnable createConcatenatedJobFromJobs(ExtendedIterable<Runnable> jobs);
}
