package ch.nolix.coreapi.net.endpoint3;

import ch.nolix.coreapi.document.chainednode.IChainedNode;

public interface IController {

  void runCommand(IChainedNode command);

  void runCommands(IChainedNode command, IChainedNode... commands);

  void runCommands(Iterable<? extends IChainedNode> commands);
}
