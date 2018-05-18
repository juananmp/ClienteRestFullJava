/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;

/**
 * Jersey REST client generated for REST resource:EnviarAgendaResource
 * [EnviarAgenda]<br>
 * USAGE:
 * <pre>
 *        EnviarAgendaServicio client = new EnviarAgendaServicio();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author janto
 */
public class EnviarAgendaServicio {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestFullServer/webresources";

    public EnviarAgendaServicio() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("EnviarAgenda");
    }

    public <T> T getXml(Class<T> responseType, String token, String idA) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.queryParam("idAgenda", idA);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
