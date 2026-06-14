/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.job;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 */
public interface IJobTool {
  Runnable createConcatenatedJobFromJobs(IWellOrderContainer<Runnable> jobs);
}
