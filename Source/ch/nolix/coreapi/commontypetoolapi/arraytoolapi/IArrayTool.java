//package declaration
package ch.nolix.coreapi.commontypetoolapi.arraytoolapi;

//interface
public interface IArrayTool {

  //method declaration
  <E> E[] createArrayWithElement(E element, @SuppressWarnings("unchecked") E... elements);

  //method declaration
  double[] createArrayWithValue(double value, double... values);

  //method declaration
  IByteArrayMediator onArray(byte[] byteArray);
}
