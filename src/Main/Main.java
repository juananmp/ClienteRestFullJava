/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Objetos.AgendaObject;
import Objetos.Contacto;
import Objetos.ListaContacto;
import Objetos.MostrarAgenda;
import Objetos.NuevaAgendaObj;
import Objetos.PersonaObj;
import Objetos.UsuarioObj;
import Rest.BorrarContacto;
import Servicios.BorradoServicio;
import Servicios.ContactoServicio;
import Servicios.CreateUser;
import Servicios.EnviarAgendaServicio;
import Servicios.GetIdUser;
import Servicios.MostrarAgendaServicio;
import Servicios.NuevaAgendaServicio;
import Servicios.TokenLogin;
import Servicios.UpdateServicio;
import Servicios.ValidarAgendaServicio;
import Servicios.ValidarPersonaServicio;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author janto
 */
public class Main {

    public static String token = "";
    public static String idUsuario = "";
    public static String idAgenda = "";
    public static String idContacto = "";
    Scanner sn = new Scanner(System.in);

    public static void main(String[] args) {
        FirStep();
    }

    public static void FirStep() {

        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        Scanner sn = new Scanner(System.in);
        while (!salir) {

            System.out.println(" 1. Iniciar sesion");
            System.out.println(" 2. Registro usuario");
            System.out.println(" 5. Salir");
            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                        login();
                        // IniciarSesion();
                        break;
                    case 2:
                        CrearCuenta();
                        break;

                    case 3:
                        System.out.println("Ha salido satisfactoriamente");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no válida");

                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }

        }
    }

    public static void SecondStep() {
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        Scanner sn = new Scanner(System.in);
        while (!salir) {

            System.out.println("Menu:");
            System.out.println(" 1. Dar de alta contacto");
            System.out.println(" 2. Validar Agenda");
            System.out.println(" 3. Validar Persona");
            System.out.println(" 4. Devolver Agenda");
            System.out.println(" 5. Devolver Contacto");
            System.out.println(" 6. Modificar Contacto");
            System.out.println(" 7. Borrar Contacto");
            System.out.println(" 8. Volver a Menu Login");
            System.out.println(" 9. Atras");

            System.out.println("Escribe una de las opciones");
            opcion = sn.nextInt();
            switch (opcion) {
                case 1:
                    CrearContacto();

                    break;
                case 2:
                    validarAgenda();
                    break;

                case 3:
                    ValidarPersona();

                    break;
                case 4:
                    System.out.println("HOLS??");
                    DevolverAgenda();
                    break;
                case 5:
                    DevolverContacto();
                    break;
                case 6:
                   UpdateContacto();
                    break;
                case 7:
                    EliminarContacto();
                    // opcionDM=false;
                    //  ListaContactos();
                    break;
                case 8:
                    ListaAgendas();
                    break;
                case 9:
                    FirStep();
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no válida");
            }

        }

    }

    public static void login() {
        System.out.print("      Nombre: ");
        String nombre = "";
        Scanner entradaEscanerA = new Scanner(System.in);
        nombre = entradaEscanerA.nextLine();
        System.out.print("      Password: ");
        String pass = "";
        Scanner entradaEscanerB = new Scanner(System.in);
        pass = entradaEscanerB.nextLine();
        UsuarioObj usu = new UsuarioObj();
        usu.setUser(nombre);
        usu.setPassword(pass);

        //cojo token del usuario y se lo paso
        TokenLogin tk = new TokenLogin();
        token = tk.putXml(usu);

        System.out.println("token------>" + token);
        if (!token.isEmpty() && token != null && token != "") {
            GetIdUser id = new GetIdUser();
            System.out.println(id);
            idUsuario = id.postXml(usu);

            ListaAgendas();
        } else {
            System.out.println("No valido");
            FirStep();
        }

    }

    public static void UpdateContacto(){
        
        UpdateServicio up = new UpdateServicio();
         boolean correoC = false;
        boolean telefonoC = false;
        PersonaObj p = new PersonaObj();
        System.out.print("Pon id contacto: ");
        Scanner entradaEscanerID = new Scanner(System.in);
        String idContacto2 = entradaEscanerID.nextLine();
        System.out.print("      Nombre: ");
        String nombre = "";
        Scanner entradaEscanerA = new Scanner(System.in);
        nombre = entradaEscanerA.nextLine();
        p.setName(nombre);

        while (!correoC) {
            System.out.print("      Correo: ");
            String correo = "";
            Scanner entradaEscanerB = new Scanner(System.in);
            correo = entradaEscanerB.nextLine();
            if (correo.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                p.setEmail(correo);
                correoC = true;
            } else {
                System.out.println("Correo no correcto");
            }
        }

        while (!telefonoC) {
            System.out.print("      Nº Telefono: ");
            String numeroTelefono = "";
            Scanner entradaEscanerC = new Scanner(System.in);
            numeroTelefono = entradaEscanerC.nextLine();

            p.setTelephone(Integer.parseInt(numeroTelefono));
            telefonoC = true;

        }
        ListaContacto ls = new ListaContacto();
        ls.getIdContacto().add(idContacto2);
        ls.getPersona().add(p);
        up.putXml(ls, token);
        
        
    }
    
    public static void CrearCuenta() {
        System.out.print("      Nombre: ");
        String nombre = "";
        Scanner entradaEscanerA = new Scanner(System.in);
        nombre = entradaEscanerA.nextLine();
        System.out.print("      Password: ");
        String pass = "";
        Scanner entradaEscanerB = new Scanner(System.in);
        pass = entradaEscanerB.nextLine();
        UsuarioObj usu = new UsuarioObj();
        usu.setUser(nombre);
        usu.setPassword(pass);

        CreateUser cr = new CreateUser();
        cr.putXml(usu);

    }

    public static void validarAgenda() {
        ValidarAgendaServicio val = new ValidarAgendaServicio();
        try {
            System.out.println("Validacion: " + val.ValAgenda(idAgenda));
        } catch (Exception e) {
            System.out.println("Validacion: NO VALIDO");
        }
    }

    private static void ListaAgendas() {
        System.out.println("---Lista de Agendas---");
        MostrarAgendaServicio pet = new MostrarAgendaServicio();
        MostrarAgenda lista = new MostrarAgenda();
        lista = pet.getXml(MostrarAgenda.class, token);
        Iterator<Map.Entry<String, Integer>> entries = lista.getAgenda().entrySet().iterator();
        int i = 1;
        ArrayList<Integer> numeracion = new ArrayList<Integer>();
        while (entries.hasNext()) {
            Map.Entry<String, Integer> entry = entries.next();
            System.out.println(i + " " + entry.getKey());
            numeracion.add(entry.getValue());
            i++;
        }
        System.out.println(i + " Crear Agenda");
        int entradaTeclado = 0;

        System.out.print("Introducir número:");
        Scanner entradaEscaner = new Scanner(System.in);
        try {
            entradaTeclado = entradaEscaner.nextInt();
            if (i == entradaTeclado) {
                System.out.println("Entro¿?¿?");
                CrearAgenda();
            } else {
                idAgenda = Integer.toString(numeracion.get(entradaTeclado - 1));
                SecondStep();
            }
        } catch (Exception e) {
            //CrearAgenda();
            System.out.println(e.toString());

        }

    }

    public static void CrearAgenda() {
        System.err.println("Entro2¿?");
        System.out.print("Introducir nombre de la Agenda:");
        Scanner entradaEscaner = new Scanner(System.in);
        String entradaTeclado = entradaEscaner.nextLine();
        NuevaAgendaObj dt = new NuevaAgendaObj();
        dt.getNuevaAgendaObj().add(idUsuario);
        dt.getNuevaAgendaObj().add(entradaTeclado);

        NuevaAgendaServicio nuevaA = new NuevaAgendaServicio();
        nuevaA.putXml(dt, token);
        ListaAgendas();
    }

    public static void CrearContacto() {
        boolean correoC = false;
        boolean telefonoC = false;
        PersonaObj p = new PersonaObj();

        System.out.print("      Nombre: ");
        String nombre = "";
        Scanner entradaEscanerA = new Scanner(System.in);
        nombre = entradaEscanerA.nextLine();
        p.setName(nombre);

        while (!correoC) {
            System.out.print("      Correo: ");
            String correo = "";
            Scanner entradaEscanerB = new Scanner(System.in);
            correo = entradaEscanerB.nextLine();
            if (correo.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                p.setEmail(correo);
                correoC = true;
            } else {
                System.out.println("Correo no correcto");
            }
        }

        while (!telefonoC) {
            System.out.print("      Nº Telefono: ");
            String numeroTelefono = "";
            Scanner entradaEscanerC = new Scanner(System.in);
            numeroTelefono = entradaEscanerC.nextLine();

            p.setTelephone(Integer.parseInt(numeroTelefono));
            telefonoC = true;

        }
        Contacto pA = new Contacto();
        pA.setPo(p);
        int idAgenda2 = Integer.parseInt(idAgenda);
        pA.setId_agenda(idAgenda2);
        ContactoServicio ca = new ContactoServicio();
        ca.insertarPersona(pA, token);

    }

    public static void ValidarPersona() {
        boolean correoC = false;
        boolean telefonoC = false;
        PersonaObj p = new PersonaObj();
        String nombre = "";
        String correo = "";
        String numeroTelefono = "";
        System.out.print("Nombre: ");
        Scanner entradaEscanerA = new Scanner(System.in);
        nombre = entradaEscanerA.nextLine();

        while (!correoC) {
            System.out.print("Correo: ");
            correo = "";
            Scanner entradaEscanerB = new Scanner(System.in);
            correo = entradaEscanerB.nextLine();
            if (correo.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                p.setEmail(correo);
                correoC = true;
            } else {
                System.out.println("Correo no correcto");
            }
        }

        while (!telefonoC) {
            System.out.print("Nº Telefono: ");
            numeroTelefono = "";
            Scanner entradaEscanerC = new Scanner(System.in);
            numeroTelefono = entradaEscanerC.nextLine();

            telefonoC = true;

        }
        Contacto pA = new Contacto();
        ValidarPersonaServicio va = new ValidarPersonaServicio();
        System.out.println("Validacion: " + va.ValPersona(correo, numeroTelefono, nombre));

    }

    private static void DevolverContacto() {
        System.out.print("Introducir nombre del contacto:");
        Scanner entradaEscaner = new Scanner(System.in);
        String entradaTeclado = entradaEscaner.nextLine();
        ContactoServicio mc = new ContactoServicio();
        System.out.println("Contacto: " + mc.enviarPersona(PersonaObj.class, entradaTeclado, idAgenda, token));

    }

    private static void EliminarContacto() {
        System.out.print("Introducir nombre del contacto:");
        Scanner entradaEscaner = new Scanner(System.in);
        String entradaTeclado = entradaEscaner.nextLine();
        BorradoServicio bo = new BorradoServicio();
        System.out.println(idAgenda + "iddddddddddddddddddd");
        bo.putXml(entradaTeclado, idAgenda, token);
        SecondStep();
    }

    private static void DevolverAgenda() {
        EnviarAgendaServicio msa = new EnviarAgendaServicio();
        System.out.println(msa.getXml(AgendaObject.class, token,idAgenda));
    }

    public static boolean esNunero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }

    }
}
