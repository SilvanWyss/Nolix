//package declaration
package ch.nolix.techapi.serverdashboardapi;

import ch.nolix.coreapi.programcontrolapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//interface
public interface IWebApplicationSheet {

  //method declaration
  IApplicationInstanceTarget getApplicationInstanceTarget();

  //method declaration
  IImage getApplicationLogo();

  //method declaration
  boolean hasApplicationLogo();
}
