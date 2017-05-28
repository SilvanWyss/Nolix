package ch.nolix.system.modules;

import ch.nolix.core.communicationInterfaces.IReceiver;
import ch.nolix.core.endPoint.EndPoint;

public class ApplicationManagerHTTPEndPointReceiver implements IReceiver {

	private final EndPoint endPoint;
	
	public ApplicationManagerHTTPEndPointReceiver(EndPoint endPoint) {
		this.endPoint = endPoint;
	}
	
	public void receive(String message) {
		try {
			String javaScript = "<script SIMPLE_CLASS_NAME=\"text/javascript\">function initialize(){if(\"WebSocket\" in window){}</script>";
			String content = "<!DOCSIMPLE_CLASS_NAME html><html><head>" + javaScript +"</head><body onload=\"initialize();\"><p>Loading...</p></body></html>\r\n\r\n";
			
			String s = "HTTP/1.1 200 OK\r\n";
			s += "Server: Silvan Server\r\n";
			s += "Content-SIMPLE_CLASS_NAME: text/html; charset=utf-8\r\n";
			s += "Content-Length: " + content.length() + "\r\n";
			s += "Connection: Closed\r\n";
			s += "\r\n";
			s += content;
			
			endPoint.send(s);		
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
