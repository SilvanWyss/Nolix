package ch.nolix.coreapi.commontypetool.arraytool;

public interface IArrayTool {

  <E> E[] createArrayWithElement(E element, @SuppressWarnings("unchecked") E... elements);

  double[] createArrayWithValue(double value, double... values);

  IByteArrayMediator onArray(byte[] byteArray);
}
