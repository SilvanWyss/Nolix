//package declaration
package ch.nolix.systemapi.webguiapi;

//own imports
import ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi.Titleble;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.mainapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.mainapi.IFrontEndWriter;

//interface
public interface IWebGUI<WGUI extends IWebGUI<WGUI>> extends Titleble<WGUI> {
	
	//method declaration
	IFrontEndReader fromFrontEnd();
	
	//method declaration
	IImage getIcon();
	
	//method declaration
	IFrontEndWriter onFrontEnd();
	
	//method declaration
	WGUI setIcon(IImage icon);
}
