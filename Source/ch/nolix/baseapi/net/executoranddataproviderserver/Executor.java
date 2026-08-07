/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.executoranddataproviderserver;

import ch.nolix.baseapi.document.chainednode.IChainedNode;

/**
 * @author Silvan Wyss
 */
public interface Executor {
  void runCommand(IChainedNode command);

  void runCommands(IChainedNode... commands);

  void runCommands(Iterable<? extends IChainedNode> commands);
}
