/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

interface INetEndPointProcessor {
  /**
   * Sends the given rawMessage.
   * 
   * @param rawMessage
   */
  void sendRawMessage(final String rawMessage);
}
