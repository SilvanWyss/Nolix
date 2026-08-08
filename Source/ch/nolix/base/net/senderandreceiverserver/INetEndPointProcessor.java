/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

interface INetEndPointProcessor {
  void sendRawMessage(final String rawMessage);
}
