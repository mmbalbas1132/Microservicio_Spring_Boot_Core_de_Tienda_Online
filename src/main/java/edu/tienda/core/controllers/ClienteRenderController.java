package edu.tienda.core.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ClienteRenderController {
    @GetMapping(value = "/clientes-html", produces = MediaType.TEXT_HTML_VALUE)
    public String getClientesHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Clientes</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1>Clientes</h1>");
        sb.append("<ul>");
        sb.append("<li>Manuel</li>");
        sb.append("<li>Noelia</li>");
        sb.append("<li>Aitana</li>");
        sb.append("</ul>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
