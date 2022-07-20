//package declaration
package ch.nolix.systemapi.guiapi.canvasuniversalapi;

//own imports
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.structureproperty.BackgroundType;

//interface
public interface IBackground {
	
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
	IBackground setColor(IColor color);
	
	//method declaration
	IBackground setColorGradient(IColorGradient backgroundColorGradient);
	
	//method declaration
	IBackground setImage(IImage image);
	
	//method declaration
	IBackground setImage(IImage image, ImageApplication imageApplication);
}
