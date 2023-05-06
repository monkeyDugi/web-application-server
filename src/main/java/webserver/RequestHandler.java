package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import java.nio.file.Files;
import java.util.Map;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    @Override
    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(), connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);

            String reuqestUrl = br.readLine();
            String path = HttpRequestUtils.parsePath(reuqestUrl);

            if ("/user/create".equals(path)) {
                String parameters = HttpRequestUtils.parseParameter(reuqestUrl);
                Map<String, String> queryStrings = HttpRequestUtils.parseQueryString(parameters);

                User user = new User(queryStrings);

                DataOutputStream dos = new DataOutputStream(out);
                response200Header(dos, 0);

                log.info(user.toString());
                return;
            }

            handleWebappPage(out, path);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void handleWebappPage(OutputStream out, String path) throws IOException {
        File file = new File("./webapp" + path);
        byte[] body = Files.readAllBytes(file.toPath());

        DataOutputStream dos = new DataOutputStream(out);
        response200Header(dos, body.length);
        responseBody(dos, body);
    }

    private void handleGetSignUp() {

    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
