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
 * Jersey REST client generated for REST resource:ValidarAgendaResource
 * [ValidarAgenda]<br>
 * USAGE:
 * <pre>
 *        ValidarAgendaServicio client = new ValidarAgendaServicio();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author janto
 */
public class ValidarAgendaServicio {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestFullServer/webresources";

    public ValidarAgendaServicio() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("ValidarAgenda");
    }

    public String ValAgenda(String idA) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idA != null) {
            resource = resource.queryParam("idA", idA);
        }
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
