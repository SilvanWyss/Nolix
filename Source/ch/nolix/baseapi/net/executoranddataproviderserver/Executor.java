/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.executoranddataproviderserver;

import ch.nolix.baseapi.document.chainednode.ChainedNode;

/**
 * @author Silvan Wyss
 */
public interface Executor {
  void runCommand(ChainedNode command);

  void runCommands(ChainedNode... commands);

  void runCommands(Iterable<? extends ChainedNode> commands);
}
