/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.application.main;

import ch.nolix.core.environment.runningjar.RunningJar;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.application.mainresource.ResourcePathCatalog;

record ServerHttpMessage(String serverIP, int serverPort) {
  private static final String REQUIRE_JS_SCRIPT = RunningJar.getResource(ResourcePathCatalog.REQUIRE_JS);

  private static final String NOLIX_SCRIPT = RunningJar.getResource(ResourcePathCatalog.NOLIX_JS);

  public ServerHttpMessage( //NOSONAR: This constructor does more than the default one.
    final String serverIP,
    final int serverPort) {
    Validator.assertThat(serverIP).thatIsNamed("server IP").isNotBlank();
    Validator.assertThat(serverPort).thatIsNamed("server port").isBetween(0, 65535);

    this.serverIP = serverIP;
    this.serverPort = serverPort;
  }

  public String getServerIP() {
    return serverIP;
  }

  public String getServerIpInQuotes() {
    return "'" + getServerIP() + "'";
  }

  public int getServerPort() {
    return serverPort;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "HTTP/1.1 200 OK\r\n"
    + "Content-Type: text/html; charset=UTF-8\r\n"
    + "\r\n"
    + "<!DOCTYPE html>\n"
    + "<html>\n"
    + "<head>\n"

    //The 'data:,' link avoids that a browser requests a favorite icon.
    + "<link id=\"icon\" rel=\"icon\" href=\"data:,\">\n"

    + "<script>\n"
    + REQUIRE_JS_SCRIPT
    + "</script>\n"
    + "<script>\n"
    + NOLIX_SCRIPT +
    "</script>\n"
    + getMainScript()
    + "<title\n>"
    + "Nolix\n"
    + "</title>\n"
    + "</head>\n"
    + "<body>\n"
    + "</body>\n"
    + "</html>\r\n";
  }

  private String getMainScript() {
    return "<script>\n"
    + "require(['System/Application/WebApplication/FrontendWebClient'], function (FrontendWebClient_) {"
    + "var client = FrontendWebClient_.FrontendWebClient.toIpAndPortAndApplicationFromURL("
    + getServerIpInQuotes()
    + ", "
    + getServerPort()
    + ");"
    + "});\n"
    + "</script>\n";
  }
}
