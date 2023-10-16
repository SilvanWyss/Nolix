//package declaration
package ch.nolix.system.discretegeometry;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.main.Element;

//class
/**
 * A {@link Discrete2DPoint} is a point with 2 discrete coordinates. A
 * {@link Discrete2DPoint} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2019-05-19
 */
public final class Discrete2DPoint extends Element {

  //static method
  /**
   * Creates a new {@link Discrete2DPoint} from the given specification.
   * 
   * @param specification
   * @return a new {@link Discrete2DPoint} from the given specification.
   */
  public static Discrete2DPoint fromSpecification(final INode<?> specification) {
    return new Discrete2DPoint(
        specification.getStoredChildNodeAt1BasedIndex(1).toInt(),
        specification.getStoredChildNodeAt1BasedIndex(2).toInt());
  }

  //attribute
  private final int x;

  //attribute
  private final int y;

  //method
  /**
   * Creates a new {@link Discrete2DPoint} with the given x and y.
   * 
   * @param x
   * @param y
   */
  public Discrete2DPoint(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {
    return LinkedList.withElement(Node.withHeader(x), Node.withHeader(y));
  }

  //method
  /**
   * @return the x-coordinate of the current {@link Discrete2DPoint}.
   */
  public int getX() {
    return x;
  }

  /**
   * @return the y-coordinate of the current {@link Discrete2DPoint}.
   */
  //method
  public int getY() {
    return y;
  }
}
