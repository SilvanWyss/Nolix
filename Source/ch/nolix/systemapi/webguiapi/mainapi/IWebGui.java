//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.mutationapi.Clearable;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.elementapi.styleapi.IStyleElement;
import ch.nolix.systemapi.guiapi.canvasapi.ICanvas;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;
import ch.nolix.systemapi.guiapi.mainapi.IGui;

//interface
public interface IWebGui<WG extends IWebGui<WG>>
extends Clearable, ICanvas<WG>, IGui<WG>, IStyleElement<WG> {
	
	//method declaration
	ICss getCss();
	
	//method declaration
	IHtmlElement getHtml();
	
	//method declaration
	IContainer<IHtmlElementEvent> getHtmlElementEventRegistrations();
	
	//method declaration
	IControl<?, ?> getStoredControlByInternalId(String internalId);
	
	//method declaration
	IContainer<IControl<?, ?>> getStoredControls();
	
	//method declaration
	IContainer<ILayer<?>> getStoredLayers();
	
	//method declaration
	ILayer<?> getStoredTopLayer();
	
	//method declaration
	boolean hasRemoveLayerAction();
	
	//method declaration
	WG pushLayer(ILayer<?> layer);
	
	//method declaration
	WG pushLayerWithRootControl(IControl<?, ?> rootControl);
	
	//method declaration
	void removeLayer(ILayer<?> layer);
	
	//method declaration
	WG setFrontEndReaderAndFrontEndWriter(IFrontEndReader frontEndReader, IFrontEndWriter frontEndWriter);
	
	//method declaration
	WG setRemoveLayerAction(IAction removeLayerAction);
}
