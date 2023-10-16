//package declaration
package ch.nolix.systemapi.guiapi.visibilityapi;

//interface
public interface VisibilitySettable<VS extends VisibilitySettable<VS>> extends VisibilityRequestable {

  //method declaration
  VS setInvisible();

  //method declaration
  VS setVisible();
}
