/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.jobpool;

import java.util.Optional;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.baseapi.programcontrol.future.IFuture;

/**
 * A {@link JobPool} runs jobs in the background. A {@link JobPool} uses an
 * optimal number of {@link Worker}s to run several jobs efficiently.
 * 
 * @author Silvan Wyss
 */
public final class JobPool {
  private static final int MAX_WORKER_COUNT = 100;

  private final LinkedList<Worker> workers = LinkedList.createEmpty();

  private final LinkedList<JobWrapper> jobWrappers = LinkedList.createEmpty();

  /**
   * Enqueues the given job to the current {@link JobPool}.
   * 
   * @param job
   * @return a {@link IFuture} for the given job.
   * @throws RuntimeException if the given job is null.
   */
  public IFuture enqueue(final Runnable job) {
    final var jobWrapper = JobWrapper.withJob(job);

    jobWrappers.addAtEnd(jobWrapper);
    createNewWorkerIfNeeded();

    return Future.forJobWrapper(jobWrapper);
  }

  /**
   * @return true if the current {@link JobPool} contains waiting jobs, false
   *         otherwise.
   */
  public boolean containsWaitingJobs() {
    return jobWrappers.containsMatching(JobWrapper::isFresh);
  }

  /**
   * @return true if the current {@link JobPool} is idle, false otherwise
   */
  public boolean isIdle() {
    return jobWrappers.containsAny();
  }

  synchronized Optional<JobWrapper> removeAndGetOptionalRefNextFreshJobWrapper() {
    final var nextFreshJobWrapper = jobWrappers.getOptionalStoredFirst(JobWrapper::isFresh);

    if (nextFreshJobWrapper.isEmpty()) {
      return Optional.empty();
    }

    jobWrappers.removeStrictlyFirstOccurrenceOf(nextFreshJobWrapper.get());
    return Optional.of(nextFreshJobWrapper.get());
  }

  synchronized void removeWorker(final Worker worker) {
    workers.removeStrictlyFirstOccurrenceOf(worker);
  }

  private synchronized void createNewWorkerIfNeeded() {
    if (newWorkerIsNeeded()) {
      final var worker = Worker.forJobPool(this);

      workers.addAtEnd(worker);
    }
  }

  private int getWorkerCount() {
    return workers.getCount();
  }

  private synchronized boolean newWorkerIsNeeded() {
    final var workerCount = getWorkerCount();

    return //
    jobWrappers.containsAny()
    && workerCount < MAX_WORKER_COUNT
    && 10 * workerCount < jobWrappers.getCount();
  }
}
