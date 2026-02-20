/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.job;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IJobTool {
  Runnable createConcatenatedJobFromJobs(IContainer<Runnable> jobs);
}
