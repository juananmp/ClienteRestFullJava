/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST
 * resource:ValidarAgendaSinAutenticarResource [ValidarAgendaSinAutenticar]<br>
 * USAGE:
 * <pre>
 *        ValidarAgendaSinAutenticar client = new ValidarAgendaSinAutenticar();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author janto
 */
public class ValidarAgendaSinAutenticar {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestFullServer/webresources";

    public ValidarAgendaSinAutenticar() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("ValidarAgendaSinAutenticar");
    }

    public String ValAgenda(String agenda) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (agenda != null) {
            resource = resource.queryParam("agenda", agenda);
        }
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
