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
 * resource:ValidarPersonaSinAutenticarResource
 * [ValidarPersonaSinAutenticar]<br>
 * USAGE:
 * <pre>
 *        ValidarPersonaSinAutenticar client = new ValidarPersonaSinAutenticar();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author janto
 */
public class ValidarPersonaSinAutenticar {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestFullServer/webresources";

    public ValidarPersonaSinAutenticar() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("ValidarPersonaSinAutenticar");
    }

    public String ValPersona(String nombre, String correo, String telefono) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}/{2}", new Object[]{nombre, correo, telefono}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
