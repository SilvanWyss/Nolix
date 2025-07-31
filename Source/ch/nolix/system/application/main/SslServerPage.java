package ch.nolix.system.application.main;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.environment.runningjar.RunningJar;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.application.mainresource.ResourcePathCatalog;

public final class SslServerPage {

  private static final String REQUIRE_JS_SCRIPT = RunningJar.getResource(ResourcePathCatalog.REQUIRE_JS);

  private static final String NOLIX_SCRIPT = RunningJar.getResource(ResourcePathCatalog.NOLIX_JS);

  private final String domain;

  private final int port;

  private SslServerPage(final String domain, final int port) {

    Validator.assertThat(domain).thatIsNamed(LowerCaseVariableCatalog.DOMAIN).isNotBlank();
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableCatalog.PORT).isPort();

    this.domain = domain;
    this.port = port;
  }

  public static SslServerPage forDomainAndPort(final String domain, final int port) {
    return new SslServerPage(domain, port);
  }

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

  public String getDomain() {
    return domain;
  }

  public int getPort() {
    return port;
  }

  private String getServerDomainInQuotes() {
    return StringTool.getInSingleQuotes(getDomain());
  }

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
