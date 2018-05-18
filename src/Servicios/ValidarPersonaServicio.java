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
 * Jersey REST client generated for REST resource:ValidarPersonaResource
 * [ValidarPersona]<br>
 * USAGE:
 * <pre>
 *        ValidarPersonaServicio client = new ValidarPersonaServicio();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author janto
 */
public class ValidarPersonaServicio {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestFullServer/webresources";

    public ValidarPersonaServicio() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("ValidarPersona");
    }

    public String ValPersona(String correo, String telefono, String nombre) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (correo != null) {
            resource = resource.queryParam("correo", correo);
        }
        if (telefono != null) {
            resource = resource.queryParam("telefono", telefono);
        }
        if (nombre != null) {
            resource = resource.queryParam("nombre", nombre);
        }
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
