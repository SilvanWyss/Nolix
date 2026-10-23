/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.executoranddataproviderserver;

import ch.nolix.baseapi.document.chainednode.ChainedNode;

/**
 * @author Silvan Wyss
 */
public interface Executor {
  /**
   * Runs the given command.
   * 
   * @param command
   */
  void runCommand(ChainedNode command);

  /**
   * Runs the given commands.
   * 
   * @param commands
   */
  void runCommands(ChainedNode... commands);

  /**
   * Runs the given commands.
   * 
   * @param commands
   */
  void runCommands(Iterable<? extends ChainedNode> commands);
}
