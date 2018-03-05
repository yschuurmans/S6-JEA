package Logic;

import javax.servlet.http.HttpServletRequest;

public class HttpLogic {
    public static String getResourceUrl(HttpServletRequest request, String resourcePath) {
        return String.format("{0}{1}",  getBaseUrl(request), resourcePath);
    }

    public static String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + serverName + serverPort + contextPath;
    }
}
