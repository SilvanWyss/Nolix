/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.programcontrol.job;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IJobTool {
  Runnable createConcatenatedJobFromJobs(IContainer<Runnable> jobs);
}
