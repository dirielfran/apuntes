    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <meta charset="UTF-8">
                <title>Ejemplo JavaScript</title>
                <script type="text/javascript">
                    alert("Hola Mundo");
                    document.write("<h1>Hola Mundo desde JavaScript</h1>");
                </script>
        </head>
        <body>
            
            Link a <a href="page1.html">page1</a>

        </body>
    </html>

    --------------------------------------------------------------------------------------------
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Saludos desde JavaScrip</title>
            <script type="text/javascript" src="funciones.js"></script>
        </head>
        <body onload="saluda(); escribeMensaje();">
            <!--El texto HTML de elemento h1 se escribe de manera 
            dinamica-->
            <h1 id="mensajeHtml"></h1>
        </body>
    </html>

    ----------------------------------------------------------------------------------------------
    /**
     * @author ubaldo
     */
    function saluda() {
        alert("Hola Mundo");
    }

    function escribeMensaje() {
        //Se recupera el elemento con getElementById
        document.getElementById("mensajeHtml").innerHTML = "Saludo desde archivo de JavaScript";
    }

*****************************************************************************Funciones Basicas

    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Ejercicio JavaScript2</title>
            <script type="text/javascript" src="funciones.js"></script>
        </head>
        <body>
            <h1>Ejercicio JavaScript2</h1>
            <a id="link" href="http://www.google.com.mx">Ir a google</a>
            <br>
            <a id="linkSearch" href="http://www.google.com.mx">Buscar en google</a>
        </body>
    </html>

-----------------------------------------------------------------------------------------------

    /**
     * @author ubaldo
     */
    window.onload = iniciaDatos;

    /**
     * Funcion que se manda llamar
     * al cargar la pagina HTML
     */
    function iniciaDatos() {
        document.getElementById("link").onclick = validaSalir;
        document.getElementById("linkSearch").onclick = busqueda;
    }

    /**
     * Funcion que valida si el usuario quiere salir del sitio o no
     */
    function validaSalir() {
        if (confirm("Desea salir del sitio?")) {
            alert("Nos vamos a google");
            return true; //regresamos verdadero para salir
        }
        else {
            alert("Nos quedamos en el sitio");
            return false;//regresamos falso para quedarnos 

        }
    }

    /**
     * Funcion que pide una cadena a buscar en google
     */
    function busqueda() {
        //Con la funcion prompt capturamos informacion del usuario
        var respuesta = prompt("Escribe la cadena a buscar:", "");
        //si hubo una respuesta concatenamos la cadena a buscar
        //al link de google
        if (respuesta) {
            alert("Tu respuesta fue:" + respuesta);
            //el operador this nos sirve para referenciar 
            //al elemento que provoco el evento, en este caso
            //el elemento con identificador "linkSearch"
            //y concatenamos la respuesta como un parametro 
            //de una peticion get
            var nuevoLink = this + "search?q=" + respuesta;
            alert("Nuevo link:" + nuevoLink);
            //redireccionamos el link
            window.location = nuevoLink;
            //regresamos false, sino nos lleva al link originalmente
            //registrado en el elemento "linkSearch"
            return false;
        }
        else {
            alert("No proporcionaste ninguna cadena a buscar");
            return false;
        }
    }

********************************************************RollOver Cambio de imagen


    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Ejemplo de Botones Rollover</title>
            <link rel="stylesheet" type="text/css" href="estilos.css">
        </head>
        <body>
            <h1>Ejemplo de Botones Rollover</h1>

            <a href="resultado.html"
               onmouseover="document.boton.src = 'boton_on.jpg'"
               onmouseout="document.boton.src = 'boton_off.jpg'">
                <img src="boton_off.jpg" name="boton" alt="boton"/>
            </a>

            <br/>

            <a href="mejorado.html">Ejemplo mejorado</a>
        </body>
    </html>

    ------------------------------------------------------------------------------------

    /**
     * @author ubaldo
     */
    window.onload = cargarImagenes;

    /*
     * Funcion que carga todas las imagenes que funcionan
     * como un link, en particular las que estan envueltas
     * con un tag "a"
     */
    function cargarImagenes() {
        for (var i = 0; i < document.images.length; i++) {
            if (document.images[i].parentNode.tagName == "A") {
                configuraRollover(document.images[i]);
            }
        }
    }

    /**
     * Esta funcion se ejecuta al iniciar la pagina, y se utiliza
     * para asociar las imagenes de rollover a los links
     * que funcionaran como tal
     * @param {Object} imagen - La imagen que funciona como rollover
     */
    function configuraRollover(imagen) {
        imagen.imagenOff = new Image();
        imagen.imagenOff.src = "boton_off.jpg";
        imagen.onmouseout = cambiaOff;

        imagen.imagenOn = new Image();
        imagen.imagenOn.src = "boton_on.jpg";
        imagen.onmouseover = cambiaOn;
    }

    /**
     * Estas funciones se ejecutan segun el evento que se dispare
     * pero no es al iniciar la pagina, sino dependen del 
     * boton que se presione, son conocidas como handlers
     */
    //Se asocio al evento onmouseout
    function cambiaOff() {
        this.src = this.imagenOff.src;//tomamos los valores ya asociados
    }

    //Se asocio al evento onmouseover
    function cambiaOn() {
        this.src = this.imagenOn.src;//tomamos los valores ya asociados
    }





    --------------------------------------------------------------------------------
    body {
        background:#ffffff;
    }

    img{
        border:none;
    }


******************************************************************Validacion de Formulario HTML

    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Ejercicio Formulario</title>
            <link rel="stylesheet" type="text/css" href="recursos/estilos.css">
            <script type="text/javascript" src="recursos/funciones.js"></script>
        </head>
        <body>
            <form name="form1" action="/ManejoFormulariosHTML/Servlet"
                  method="post" onsubmit="return validarForma(this);">

                <input type="hidden" name="oculto" value="valorOculto">

                <table width="200" id="one-column-emphasis">
                    <caption>
                        Formulario Registro de Datos
                    </caption>
                    <tr>
                        <td class="oce-first">
                            Usuario: (*)
                        </td>
                        <td>
                            <input class="default" type="text"
                                   name="usuario" value="Escribir usuario"
                                   onfocus="this.select();"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="oce-first">
                            Password: (*)
                        </td>
                        <td>
                            <input class="default" type="password"
                                   name="password" onfocus="this.select();">
                        </td>
                    </tr>
                    <tr>
                        <td class="oce-first">
                            Tecnolog&iacute;as de Inter&eacute;s: (*)
                        </td>
                        <td>
                            Java <input type="checkbox" name="tecnologia" value="java">
                            &nbsp;&nbsp;&nbsp;
                            .Net <input type="checkbox" name="tecnologia" value="net">
                            &nbsp;&nbsp;&nbsp;
                            Php <input type="checkbox" name="tecnologia" value="php">
                        </td>
                    </tr>
                    <tr>
                        <td class="oce-first">
                            G&eacute;nero: (*)
                        </td>
                        <td>
                            Hombre<input type="radio" name="genero" value="H">
                            &nbsp;&nbsp;&nbsp;<!--genera espacios entre los elementos de manera horizontal -->
                            Mujer <input type="radio" name="genero" value="M">
                        </td>
                    </tr>
                    <tr>
                        <td class="oce-first">
                            Ocupaci&oacute;n: (*)
                        </td>
                        <td>
                            <select name="ocupacion" class="default">
                                <option value="">Seleccionar</option>
                                <option value="1">Profesor</option>
                                <option value="2">Ingeniero</option>
                                <option value="3">Jubilado</option>
                                <option value="4">Otro</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="oce-first">
                            M&uacute;sica Favorita: 
                        </td>
                        <td>
                            <select name="musica" multiple class="default">
                                <option value="rock">Rock</option>
                                <option value="pop">Pop</option>
                                <option value="salsa">Salsa</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="oce-first">
                            Comentarios:
                        </td>
                        <td>
                            <textarea name="comentarios" cols="30" rows="2"
                                      class="default" onfocus="this.select();">
                                Escribir un texto</textarea>
                        </td>
                    </tr>
                    <tr style="text-align:center;">
                        <td>
                            <input type="reset" value="Limpiar" class="default">
                        </td>
                        <td>
                            <input type="submit" value="Enviar" class="default">
                        </td>
                    </tr>
                </table>
            </form>
        </body>
    </html>

    --------------------------------------------------------------------------------------------------


        /**
         * @author ubaldo
         */
        /**
         * Funcion para validar los elementos requeridos (*)
         * @param {Object} forma
         */
        function validarForma(forma) {
            //Validamos el usuario
            var usuario = forma.usuario;
            if (usuario.value == "" || usuario.value == "Escribir usuario") {
                alert("Debe proporcionar un nombre de usuario");
                usuario.focus();
                usuario.select();
                return false;
            }

            //Validamos el password
            var password = forma.password;
            if (password.value == "" || password.value.length < 3) {
                alert("Debe proporcionar un password al menos de 3 caracteres");
                password.focus();
                password.select();
                return false;
            }

            //Validamos las tecnologias de interes
            var tecnologias = forma.tecnologia;
            var checkSeleccionado = false;

            //revisamos si se selecciono algun checkbox
            for (i = 0; i < tecnologias.length; i++) {
                if (tecnologias[i].checked) {
                    checkSeleccionado = true;
                }
            }
            if (!checkSeleccionado) {
                alert("Debe proporcionar una Tecnologia");
                return false;
            }

            //Validamos el Genero
            var generos = forma.genero;
            var radioSeleccionado = false;

            //revisamos si se selecciono algun radiobutton
            for (i = 0; i < generos.length; i++) {
                if (generos[i].checked) {
                    radioSeleccionado = true;
                }
            }
            if (!radioSeleccionado) {
                alert("Debe seleccionar el Genero");
                return false;
            }


            //Validamos la ocupacion
            var ocupacion = forma.ocupacion;
            if (ocupacion.value == "") {
                alert("Debe seleccionar una ocupacion");
                return false;
            }


            //Formulario validado
            alert("Formulario valido, enviando datos...");
            return true;
        }





************************************************Funcion para darle formato a decimales

    <script type="text/javascript"> 

        function formatoImporteJS(pImporte){
            if(!pImporte || isNaN(pImporte.toString().toFloat()))
                pImporte='0';
            return pImporte.toString().toFloat().toFixed(2).toString();
        }
        
    </script>


    <ria:simplegridcolumn label='PACTADO' object="${item.cantPactada}" width="150" id="cantPactada" style="text-align:center;paddind:10px;" formatter="formatoImporteJS"/>


**************************************************************************************




************************************************Dar y quitar visivilidad a un elemento
    <script type="text/javascript">
        var capa;
        function cambio(idCapa){
            //Recuperamos la capa segun el elemento disponible
            if(document.layers) capa = eval("document."+idCapa);
            if(document.all) capa =eval(idCapa+".style");
            if(document.getElementById) capa =eval('document.getElementById("'+idCapa+'").style');
            //Si esta visible la capa la ocultamos, o viceversa
            if((capa.visibility=="hidden")||(capa.visibility=="hide")){
                capa.visibility=(document.layers)?"show":"visible";
            }else{
                capa.visibility=(document.layers)?"hide":"hidden";
            }
        }
    </script>
**************************************************************************************



