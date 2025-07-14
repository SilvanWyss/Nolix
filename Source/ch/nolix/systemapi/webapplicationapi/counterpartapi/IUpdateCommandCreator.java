package ch.nolix.systemapi.webapplicationapi.counterpartapi;

import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

public interface IUpdateCommandCreator {

  IChainedNode createSetCssCommandFromWebGui(IWebGui<?> webGui);

  IChainedNode createSetEventFunctionsCommandFromWebGui(IWebGui<?> webGui);

  IChainedNode createSetIconCommandFromWebGui(IWebGui<?> webGui);

  IChainedNode createSetIconCommandForIcon(IImage icon);

  IChainedNode createSetRootHtmlElementCommandFromControl(IControl<?, ?> control);

  IChainedNode createSetRootHtmlElementCommandFromWebGui(IWebGui<?> webGui);

  IChainedNode createSetTitleCommandFromWebGui(IWebGui<?> webGui);

  IChainedNode createSetTitleCommandForTitle(String title);

  IChainedNode createSetUserInputFunctionsCommandFromWebGui(IWebGui<?> webGui);
}
