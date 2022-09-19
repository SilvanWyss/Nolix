//package declaration
package ch.nolix.systemapi.guiapi.canvasuniversalapi;

//own imports
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.structureproperty.BackgroundType;

//interface
public interface IBackground extends Specified {
	
	//method declaration
	IColor getColor();
	
	//method declaration
	IColorGradient getColorGradient();
	
	//method declaration
	IImage getImage();
	
	//method declaration
	ImageApplication getImageApplication();
	
	//method declaration
	BackgroundType getType();
	
	//method declaration
	ICSSProperty toCSSProperty();
}
