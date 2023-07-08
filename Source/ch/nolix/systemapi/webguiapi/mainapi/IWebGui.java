//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.mutationapi.Clearable;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.systemapi.elementapi.styleapi.IStyleElement;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.ICanvas;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;
import ch.nolix.systemapi.guiapi.mainapi.IGui;

//interface
public interface IWebGui<WG extends IWebGui<WG>>
extends Clearable, ICanvas<WG>, IGui<WG>, IStyleElement<WG> {
	
	//method declaration
	@Override
	IFrontEndReader fromFrontEnd();
	
	//method declaration
	IWebGuiContent getContent();
	
	//method declaration
	ICss<?, ?> getCss();
	
	//method declaration
	IContainer<IHtmlElementEvent> getHtmlElementEventRegistrations();
	
	//method declaration
	@Override
	IImage getIcon();
	
	//method declaration
	IControl<?, ?> getOriControlByInternalId(String fixedId);
	
	//method declaration
	IContainer<IControl<?, ?>> getOriControls();
	
	//method declaration
	IContainer<ILayer<?>> getOriLayers();
	
	//method declaration
	ILayer<?> getOriTopLayer();
	
	//method declaration
	boolean hasRemoveLayerAction();
	
	//method declaration
	@Override
	IFrontEndWriter onFrontEnd();
	
	//method declaration
	WG pushLayer(ILayer<?> layer);
	
	//method declaration
	WG pushLayerWithRootControl(IControl<?, ?> rootControl);
	
	//method declaration
	void removeLayer(ILayer<?> layer);
	
	//method declaration
	WG setFrontEndReaderAndFrontEndWriter(IFrontEndReader frontEndReader, IFrontEndWriter frontEndWriter);
	
	//method declaration
	@Override
	WG setIcon(IImage icon);
	
	//method declaration
	WG setRemoveLayerAction(IAction removeLayerAction);
}
