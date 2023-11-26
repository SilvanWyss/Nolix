//package declaration
package ch.nolix.systemapi.guiapi.backgroundapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.elementapi.specificationapi.Specified;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;

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
  IContainer<ICssProperty> toCssProperties();
}
