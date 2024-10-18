package ch.nolix.coreapi.netapi.endpoint3api;

import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;

public interface IController {

  void runCommand(IChainedNode command);

  void runCommands(IChainedNode command, IChainedNode... commands);

  void runCommands(Iterable<? extends IChainedNode> commands);
}
