//package declaration
package ch.nolix.system.application.main;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.environment.runningjar.RunningJar;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.application.mainresource.ResourcePathCatalogue;

//class
public final class SslServerPage {

  //constant
  private static final String REQUIRE_JS_SCRIPT = RunningJar.getResource(ResourcePathCatalogue.REQUIRE_JS);

  //constant
  private static final String NOLIX_SCRIPT = RunningJar.getResource(ResourcePathCatalogue.NOLIX_JS);

  //constant
  private static final IStringTool STRING_TOOL = new StringTool();

  //attribute
  private final String domain;

  //attribute
  private final int port;

  //constructor
  private SslServerPage(final String domain, final int port) {

    GlobalValidator.assertThat(domain).thatIsNamed(LowerCaseVariableCatalogue.DOMAIN).isNotBlank();
    GlobalValidator.assertThat(port).thatIsNamed(LowerCaseVariableCatalogue.PORT).isPort();

    this.domain = domain;
    this.port = port;
  }

  //static method
  public static SslServerPage forDomainAndPort(final String domain, final int port) {
    return new SslServerPage(domain, port);
  }

  //method
  @Override
  public String toString() {
    return "<!DOCTYPE html>\n"
    + "<html>\n"
    + "<head>\n"

    //The 'data:,' link avoids that a browser requests a favorite icon.
    + "<link id=\"icon\" rel=\"icon\" href=\"data:,\">\n"

    + "<script>\n"
    + REQUIRE_JS_SCRIPT
    + "</script>\n"
    + "<script>\n"
    + NOLIX_SCRIPT
    + "</script>\n"
    + getStartScript()
    + "<title\n>"
    + "Nolix\n"
    + "</title>\n"
    + "</head>\n"
    + "<body>\n"
    + "</body>\n"
    + "</html>\n";
  }

  //method
  public String getDomain() {
    return domain;
  }

  //method
  public int getPort() {
    return port;
  }

  //method
  private String getServerDomainInQuotes() {
    return STRING_TOOL.getInSingleQuotes(getDomain());
  }

  //method
  private String getStartScript() {
    return "<script>\n"
    + "require(['System/Application/WebApplication/FrontendWebClient'], function (FrontendWebClient_) {"
    + "var client = FrontendWebClient_.FrontendWebClient.toIpAndPortAndApplicationFromURLOnSecureWebSocket("
    + getServerDomainInQuotes()
    + ", "
    + getPort()
    + ");"
    + "});\n"
    + "</script>\n";
  }
}
