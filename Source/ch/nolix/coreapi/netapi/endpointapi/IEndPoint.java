package ch.nolix.coreapi.netapi.endpointapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;

public interface IEndPoint extends IBaseEndPoint {

  boolean hasReceiver();

  void sendMessage(String message);

  void setReceiver(Consumer<String> receiver);
}
