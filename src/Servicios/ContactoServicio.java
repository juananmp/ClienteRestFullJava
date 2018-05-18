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
 * Jersey REST client generated for REST resource:ContactoServicioResource
 * [contactoServicio]<br>
 * USAGE:
 * <pre>
 *        ContactoServicio client = new ContactoServicio();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author janto
 */
public class ContactoServicio {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestFullServer/webresources";

    public ContactoServicio() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("contactoServicio");
    }

    public <T> T enviarPersona(Class<T> responseType, String nombre, String id, String token) throws ClientErrorException {
        WebTarget resource = webTarget;
         if (id != null) {
            resource = resource.queryParam("id", id);
        }
        if (nombre != null) {
            resource = resource.queryParam("nombre", nombre);
        }
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token).get(responseType);
    }

    public void insertarPersona(Object requestEntity, String token) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void close() {
        client.close();
    }
    
}
