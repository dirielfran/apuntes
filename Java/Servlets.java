
**************************************************************************************Sobreescritura Metodo doGet

//anotacion que indica el nombre y la url a la que responde
@WebServlet(name="HolaMundo", urlPatterns={"/HolaMundo"})
//La clase debe extendert de HttpServlet
public class HolaMundo extends HttpServlet{
//Sobreescribe el metodo doGet
@Override
//utiliza como parametros un objeto requeste y uno response 
protected void doGet(HttpServletRequestrequest, HttpServletResponseresponse) throws ServletException, IOException{
	response.setContentType("text/html;charset=UTF-8");
	//Utiliza el objeto PrintWriter para la impresion 
	try(PrintWriter out=response.getWriter()) {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>ServletHolaMundo</title>"); 
		out.println("</head>");out.println("<body>");
		out.println("<h1>ServletHolaMundo, la aplicacionse llama: "+request.getContextPath() +"</h1>");
		out.println("</body>");out.println("</html>");
	}	
}
*****************************************************************************************************************


**************************************************************Sobreescritura Metodo doPost con parametros
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //Leemos los parametros del formulario
        //por default el formulario es de tipo Get
        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");

        System.out.println("usuario:" + usuario);
        System.out.println("password:" + password);

        PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("El parametro usuario es: " + usuario);
        out.println("<br>");
        out.println("El parametro password es: " + password);
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
**********************************************************************************************************



**********************************************************************Procesando un arrego getParameterValues 
//El elemento tecnologiapuede tener varios
//valores, por ello lo procesamos como un arreglo, se utiliza el metodo getParameterValues
String[] tecnologias=request.getParameterValues("tecnologia");
//El componente de musica, indica que se pueden
//seleccionar multipleselementos, por ello
//lo procesamos como un arreglo
String[] musica=request.getParameterValues("musica");
*************************************************************************************************************



*********************************************************************Manejando Formularios con cliclos for
/* TODO output your page here*/
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Resultado Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Parametros procesados por el Servlet:</h1>");
        out.println("<table border='1'>");

        out.println("<tr>");
        out.println("<td>");
        out.println("Usuario");
        out.println("</td>");
        out.println("<td>");
        out.println(usuario);
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("Password");
        out.println("</td>");
        out.println("<td>");
        out.println(password);
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("Tecnologias");
        out.println("</td>");
        out.println("<td>");
        for (String tecnologia : tecnologias) {
            out.println(tecnologia);
            out.println(" / ");
        }
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("G&eacute;nero");
        out.println("</td>");
        out.println("<td>");
        out.println(genero);
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("Ocupaci&oacute;n");
        out.println("</td>");
        out.println("<td>");
        out.println(ocupacion);
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("M&uacute;sica Favorita");
        out.println("</td>");
        out.println("<td>");
        if (musica != null) {
            for (String m : musica) {
                out.println(m);
                out.println(" / ");
            }
        }
        out.println("</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println("Comentarios");
        out.println("</td>");
        out.println("<td>");
        out.println(comentario);
        out.println("</td>");
        out.println("</tr>");

        out.println("<table>");

        out.println("</body>");
        out.println("</html>");
***********************************************************************************************




****************************************************Metodos para procezar Peticiones HTTP
Metodos de la clase HttpServletRequest:
getHeader()
getHeaders()
getHeadersNames()
Metodos especializados:
getCookies()
getAuthType()
getRemoteUser()
getContentLength()
getContentType()
getDateHeader()
getIntHeader()
Metodos con informacion del contexto:
getMethod()
getRequestURI()
getQueryString()
getProtocol()


//Tipo de metodo que utilizo el cliente para llamar al Servlet
StringmetodoHttp=request.getMethod();

//La uri
Stringuri=request.getRequestURI();

//El Host que se utilizo
out.println("Host: "+request.getHeader("Host"));

//El navegador
out.println("Navegador: "+request.getHeader("User-Agent"));

//Procesamos todos los cabeceros
//estos pueden variar segunel navegador que se utilice
Enumeration cabeceros =request.getHeaderNames();
while(cabeceros.hasMoreElements()) {
	String nombreCabecero=(String) cabeceros.nextElement();
	out.println("<b>"+nombreCabecero+"</b>: ");
	out.println(request.getHeader(nombreCabecero));
	out.println("<br>");
}

//Verificamos el tipo de navegador y personalizamos
//el mensaje a enviar
if(tipoNavegador != null && tipoNavegador.contains("Trident")) {
	titulo ="Navegador Internet Explorer";
	mensaje ="Estas navegando con Internet Explorer";
} else if(tipoNavegador != null && tipoNavegador.contains("Firefox")) {
	titulo ="Navegador Firefox";
	mensaje ="Estas navegando con Firefox";
} else if( tipoNavegador != null && tipoNavegador.contains("Chrome")) {
	titulo ="Navegador Chrome";
	mensaje ="Estas navegando con Chrome";
}
*******************************************************************************************************



*****************************************************************************Codigos de estado 
//Codigo de estado del servidor hacia el cliente
//Se utilizan normalmente las contantes SC_OK, SC_NOT_FOUND,etc. Tambien se pueden utilizar el codigo de error
response.setStatus(int codigo)
//Codigo de error, utilizado para mostrar el error en un documento HTML, normalmente los navegadores
response.sendError(int codigo, String mensaje)
//Se redirige el cliente a otra pagina, el codigo de estado es el 302
response.sendRedirect(String url)
Ejemplo 4redirecionamiento--------------------------------------------------------------------
    protected void doGet(HttpServletRequestrequest, HttpServletResponseresponse)throwsServletException, IOException{
        //Ya no utilizamos el objeto printwriterpara mandar la respuesta
        //Utilizamos el redireccionamiento
        String url=null;
        String tipoNavegador=request.getHeader("User-Agent");
        System.out.println("tipoNavegador"+tipoNavegador);
        if (tipoNavegador != null && tipoNavegador.contains("Trident")) {
            url="http://www.microsoft.com";
        } else if(tipoNavegador != null && tipoNavegador.contains("Firefox")) {
            url="http://www.firefox.com";
        } else if(tipoNavegador != null && tipoNavegador.contains("Chrome")) {
            url="http://www.google.com";
        }
        //Redireccionamos al URL seleccionado 
        response.sendRedirect(url);
    }


***Codigos mas comunes  -->  

//Listado de codigosde error
//http://docstore.mik.ua/orelly/java-ent/servlet/appc_01.htmout
200: La respuesta fue correcta
204: Sin contenido
301: El documento solicitado ha cambiado de ubicacion
302: El navegador se mueve al nuevo url de manera automatica
401: Sin autorizacion
404: Recurso no encontrado
500: Error interno del servidor

Ejemplo 401: Sin autorizacion---------------------------------------------------------------------
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        //Simulando valores
        String usuarioCorrecto = "Juan";
        String passCorrecto = "123";

        String pUsuario = request.getParameter("usuario");
        String pPassword = request.getParameter("password");

        if (usuarioCorrecto.equals(pUsuario) && passCorrecto.equals(pPassword)) {
            out.println("<h1>");
            out.println("Datos correctos");
            out.println("<br>Usuario:" + pUsuario);
            out.println("<br>Password:" + pPassword);
            out.println("</h1>");
        } else {
            //Respondemos al navegador con un codigo de estado de No Autorizado
            response.sendError(response.SC_UNAUTHORIZED, "Las credenciales son incorrectas");
        }

        //Listado de codigos de error
        //http://docstore.mik.ua/orelly/java-ent/servlet/appc_01.htm
        out.close();
    }

*****************************************************************************************************


********************************************************************************Cabeceros de respuesta
//Metodos para establecer valores de los headers
response.setHeader(String nombreCabecero, String valorCabecero)
//Ponen un cabecero y si ya existe lo reemplaza
response.setDateHeader y response.setIntHeader
//Agregan nuevos valores en lugar de reemplazarlos
response.addHeader, addDateHeader, addIntHeader
***Metodos mas comunes para establecer valores de los headers
//tipo de respuesta enviar hacia el cliente
setContentType(Se utiliza la tabla MIME para definir su valor)
//Especifica el largo del contenido
setContentLenght
addCookie
sendRedirect

Ejemplo----------------------------------------------------------------------------------------------
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {
            //Especificamos el tipo del documento de respuesta (MIME)
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=excelEjemplo.xls");
            //Para un uso más profesional de excel usar: https://poi.apache.org/
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires", -1);

            //Desplegamos el contenido en un excel
            //simplemente modificando el contentType
            out.println("\tValores");
            out.println("\t1");
            out.println("\t2");
            out.println("Total\t=SUMA(B2:B3)");
        }
    }

    --------------------------------------------------------------------------------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try(PrintWriter out = response.getWriter()){
                response.setContentType("text/html;charset=UTF-8");
                //Indica al cliente que se refresca cada segundo
                response.setHeader("refresh", "1");//dado en segundos
                 Date fecha = new Date();
                //API para utilizar la clase SimpleDateFormat
                //https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
                //SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'a las' HH:mm:ss");
                SimpleDateFormat formateador = new SimpleDateFormat("'Hora actualizada' HH:mm:ss");
                String fechaConFormato = formateador.format(fecha);
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet FechaServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Fecha actualizada: " + fechaConFormato + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
    }
******************************************************************************************************



****************************************************************************************************Cookies
***Creacion de un objeto Cookie
Cookie c = new Cookie("usuario","Juan");
c.getName();
c.getValue();

***Metodos para leer todas las cookies en una peticion HTTP
//Todos los valores se almacenan en cadenas
Cookies[] cookies = request.getCookies();

***metodo para añadir una cookie en la respuesta
response.addCookie(c);

***Metodos mas comunes
//Especifica de donde proviene o se va almacenar la cookie
getDomain()/setDomain()
//Especifica el tiempo a espirar de la cookies(Segundos)
getMaxAge()/setMaxAge()
//Obtiene el nombre de la cookie, para colocar el nombre se debe utilizar el constructor
getName()
//Especifica el valor asociado a una cookie
getValue()/setValue()

Ejemplo-----------------------------------------------------------------------------------------------------
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Suponemos que el usuario está visitando por primera vez nuestro sitio
        boolean nuevoUsuario = true;

        //Obtenemos todas las cookies
        Cookie[] cookies = request.getCookies();

        //Buscamos si ya existe una cookie creada con anterioridad
        //llamada visitanteRecurrente
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visitanteRecurrente")
                        && c.getValue().equals("si")) {
                    //En caso de que ya exista una cookie indicando
                    //que ya existe un usuario registrado con anterioridad
                    //ponemos la bandera en falso y salimos del ciclo
                    nuevoUsuario = false;
                    break;
                }
            }//fin del for
        }//fin del if

        //Revisamos si el usuario es un nuevo visitante
        String mensaje = null;
        if (nuevoUsuario) {
            //En caso de ser un usuario nuevo creamos el objeto Cookie
            Cookie visitanteCookie = new Cookie("visitanteRecurrente", "si");
            //Agregamos la cookie en la respuesta
            response.addCookie(visitanteCookie);
            mensaje = "Gracias por visitar nuestro sitio";
        } else {
            mensaje = "Gracias por visitar NUEVAMENTE nuestro sitio";
        }

        //Escribimos la salida
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //Omitimos código HTML para simplificar el código
        out.println("Mensaje:" + mensaje);
    }

Ejemplo contador Visitas----------------------------------------------------------------------------------
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaramos la variable contador
        int contador = 0;

        //Si existe, obtenemos el contador de visitas actual
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("contadorVisitas")) {
                    try {
                        //Obtenemos el valor del contador de visitas actual
                        contador = Integer.parseInt(c.getValue());
                    } catch (NumberFormatException e) {
                        //En caso de error, reiniciamos el contador de visitas
                        contador = 0;
                    }
                }
            }//fin del for
        }//fin del if

        //incrementamos el contador de visitas
        //y lo agregamos la cookie en la respuesta
        contador++;
        Cookie c = new Cookie("contadorVisitas", Integer.toString(contador));
        //La cookie se almacenará en el cliente por 1 hora (3600 seg)
        c.setMaxAge(3600);
        response.addCookie(c);

        //Mandamos el mensaje al navegador
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Contador de visitas de cada cliente: " + contador);
    }
***********************************************************************************************************


*****************************************************************************************Manejo de HttpSession
***Manejo de sesiones con servlets:
request.getSession()
//Se utiliza para obtener la sesion que se creo a partir de la peticion del cliente
 sesion.getAttribute()
//Permite obtener un atributo previamente agregado a la sesion del cliente
sesion.setAttribute()
//Permite agregar un atributo
sesion.removeAttribute()
//Permite eliminar un atributo
sesion.invalidate()
//Invalida la sesion actual del cliente
sesion.isNew()
//Permite saber si la sesion ha sido recien creada
sesion.getCreationTime()
//Permite conocer la hora y la fecha de cuando se creo la sesion
sesion.LastAccesedTime()
//Permite conocer la ultima vez que la sesion fue accedida por el cliente
sesion.getMaxInactiveInterval()
//Permite conocer el tiempo de inactividad(segundos) necesarios para que la sesion se destruya
sesion.setMaxInactiveInterval()
//Permite modificar el tiempo de inactividad necesario para que se destruya la sesion

Ejemplo-------------------------------------------------------------------------------------------------------
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    response.setContentType("text/html");
    HttpSession sesion=request.getSession();
    String titulo =null;
    //Pedimos el atributo, y verificamos si existe
    Integer contadorVisitas=(Integer) sesion.getAttribute("contadorVisitas");
    //Si es igual a nulo, quiere decir que es la primera
    //vez que accedemos al recurso
    if ( contadorVisitas == null ) {
        contadorVisitas = new Integer(1); 
        titulo ="Bienvenido por primera vez...";
    else{
        //si es distinto de nulo, incrementamos el contador
        titulo ="Bienvenido Nuevamente...";
        contadorVisitas += 1;
    }
    //En cualquier caso, agregamos el valor del contador
    //a la sesion
    sesion.setAttribute("contadorVisitas", contadorVisitas);
    //Mostramos los valores en el cliente
    PrintWriter out=response.getWriter();
    out.println("T&iacute;tulo: "+titulo);
    out.println("<br>");
    out.println("No. Accesos al recurso: "+contadorVisitas);
    out.println("<br>");
    out.println("ID de la sesi&oacute;n: "+sesion.getId());
}
Ejemplo 2----------------------------------------------------------------------------------------------------
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    response.setContentType("text/html;charset=UTF-8");
    //Procesamos el nuevo articulo
    String articuloNuevo=request.getParameter("articulo");
    //Creamos o recuperamos la sesionhttp
    HttpSession sesion=request.getSession();
    //Recuperamos la lista de articulosprevios
    //si es que existen en la sesion
    List<String> articulos = (List<String>) sesion.getAttribute("articulos");
    //Verificamos si la lista de articulos existe
    if(articulos == null) {
        //si no existen, inicializamos la lista
        //y la agregamos a la sesion
        articulos = new ArrayList<>();
        sesion.setAttribute("articulos", articulos);
    }
    //Ya tenemos la lista de articulos lista para trabajar
    //Agregamos el nuevo articulo
    //y lo agregamos por paso por referencia a la lista de
    //articulos
    if( articuloNuevo != null && !articuloNuevo.trim().equals("") ) {
        articulos.add(articuloNuevo);
    }
    try(PrintWriter out=response.getWriter()) {
        //Mostramos los articulos totales al cliente
        out.println("<h1>Lista de Art&iacute;culos</h1>");
        out.println("<br>");
        //Iteramos todos los articulos, incluyendo el nuevo
        for(String articulo : articulos) {
            out.print("<LI>"+articulo +"</LI>");
        }
        //Agregamos un link para regresar al inicio
        out.println("<br>");
        out.println("<a href='/EjemploCarritoCompras'>regresar</a>");
        out.close();
    }
}
**************************************************************************************************************
