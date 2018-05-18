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
 * Jersey REST client generated for REST resource:BorrarContactoResource
 * [BorrarContacto]<br>
 * USAGE:
 * <pre>
 *        BorradoServicio client = new BorradoServicio();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author janto
 */
public class BorradoServicio {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestFullServer/webresources";

    public BorradoServicio() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("BorrarContacto");
    }

    public void putXml(String nombre, String idA, String token) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{nombre, idA})).request().header(HttpHeaders.AUTHORIZATION, token).delete();
    }

    public void close() {
        client.close();
    }
    
}
