//package declaration
package ch.nolix.coreapi.netapi.endpoint3api;

//own imports
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;

//interface
public interface IController {

  //method declaration
  void runCommand(IChainedNode command);

  //method declaration
  void runCommands(IChainedNode command, IChainedNode... commands);

  //method declaration
  void runCommands(Iterable<? extends IChainedNode> commands);
}
