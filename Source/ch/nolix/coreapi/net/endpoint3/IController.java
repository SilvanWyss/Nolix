/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.endpoint3;

import ch.nolix.coreapi.document.chainednode.IChainedNode;

/**
 * @author Silvan Wyss
 */
public interface IController {
  void runCommand(IChainedNode command);

  void runCommands(IChainedNode... commands);

  void runCommands(Iterable<? extends IChainedNode> commands);
}
