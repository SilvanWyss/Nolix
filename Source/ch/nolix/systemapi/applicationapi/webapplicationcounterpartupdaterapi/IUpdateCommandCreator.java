//package declaration
package ch.nolix.systemapi.applicationapi.webapplicationcounterpartupdaterapi;

//own imports
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//interface
public interface IUpdateCommandCreator {

  //method declaration
  IChainedNode createSetCssCommandFromWebGui(IWebGui<?> webGui);

  //method declaration
  IChainedNode createSetEventFunctionsCommandFromWebGui(IWebGui<?> webGui);

  //method declaration
  IChainedNode createSetIconCommandFromWebGui(IWebGui<?> webGui);

  //method declaration
  IChainedNode createSetIconCommandForIcon(IImage icon);

  //method declaration
  IChainedNode createSetRootHtmlElementCommandFromControl(IControl<?, ?> control);

  //method declaration
  IChainedNode createSetRootHtmlElementCommandFromWebGui(IWebGui<?> webGui);

  //method declaration
  IChainedNode createSetTitleCommandFromWebGui(IWebGui<?> webGui);

  //method declaration
  IChainedNode createSetTitleCommandForTitle(String title);

  //method declaration
  IChainedNode createSetUserInputFunctionsCommandFromWebGui(IWebGui<?> webGui);
}
