******************************************************************************Seccion 4 clase 24
****************************************************JSTL<fmt:formatDate> Aplicar formato a fecha
	14.-Aplicar formato a objeto Date por medio de JSTL (Seccion 4.24 Udemy)  
		14.1.- Se importa JSTL/fmt a la pagina .jsp
			<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		14.2.- Se añade tag JSTL --><fmt:formatDate
		14.3.- En el atributo value se incluye por medio expresing languaje el atributo
			de nuestra clase pelicula
		14.4.- por medio del atributo pattern se le da formato a la fecha
			<td><fmt:formatDate value="${pelicula.fechaEstreno}" pattern="dd-MM-yyyy"/></td>



******************************************************************************Seccion 4 clase 25
***************************************************JSTL<c:choose> Aplicar condionales a la vista
	15.- Se utiliza el tag JSTL de condicional para el elemento status de la tabla (Seccion 4.25 Udemy)
	 	15.1.- Se utliza el tag JSTL choose
		15.2.- Dentro del choose, se utiliza el tag JSTL when
		15.3.- En el  tag when se utiliza el atributo test, donde con expresionlanguaje se coloca la condicion
		15.4.- se coloca las acciones al ser verdadero dentro del tag when
		15.5.- En caso de que no se cumpla la condicion se utiliza el tag JSTL otherwise
		15.6.- Se colocan las acciones al no cumplirse la condicion
		<td><c:choose>
			<c:when test="${ pelicula.estatus=='Activa'}">
				<span class="label label-success">ACTIVA</span>
			</c:when>
			<c:otherwise>
				<span class="label label-danger">INACTIVA</span>
			</c:otherwise>
		</c:choose>
		</td>



******************************************************************************Seccion 4 clase 29
********************************************************************Generar codigo html dinamico

	18.- Generar lista de peliculas de forma dinamica
		18.1.- Se ubica el sector de la pagina jsp donde se listan las peliculas
		18.2.- Se deja el codigo de una sola pelicula y se borra el resto de peliculas
		18.3.- Se configura en la pagina home.jsp los tag de JSTL 
			<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		18.4.- Se empaqueta el codigo de la pelicula en un foreach de JSTL agregandole por medio 	
			de expression laguaje la lista de peliculas en el atributo items y se agrega el nombre 	de la variable
			<c:forEach items="${ peliculas }" var="pelicula">
		18.5.- Se añaden los atributos a la pelicula por medio de espression laguaje
		<h4>${ pelicula.titulo }</h4>
		<c:forEach items="${ peliculas }" var="pelicula">
			<div class="col-xs-12 col-sm-6 col-md-3">
				<img class="img-rounded" src="${urlPublic}/images/${pelicula.imagen}" 						
				alt="Generic placeholder image" width="150" height="200">
				<h4>${ pelicula.titulo }</h4>
				<h4>
					<span class="label label-default">${ pelicula.clasificacion }</span> 			
					<span class="label label-default">${ pelicula.duracion } min</span> 			
					<span class="label label-default">${ pelicula.genero }</span>
				</h4>
				<p>
				<a class="btn btn-sm btn-primary" href="#" role="button">Consulta Horarios &raquo;</a>
				</p>
			</div>
		</c:forEach>



******************************************************************************Seccion 4 clase 31
***************************************<jsp:include> Separar el codigo html del menu y el footer

	19.- Separar el codigo html del menu y el footer
		19.1.- Se corta menu del header de la pagina home.jsp
		19.2.- Se crea folder includes en el folder views
			19.2.1.- Se crea un archivo JSP nuevo
			19.2.3.- Se copia lo cortado en el paso anterior
		19.3.- Se incluye el tag en la pagina home.jsp
			<jsp:include page="includes/menu.jsp"></jsp:include>
		19.4- Se corta footer  de la pagina home.jsp	
		19.5- Se crea un archivo JSP nuevo		 
		19.6.- Se pega lo cortado en el paso anterior
		19.7.- Se incluye el tag en la pagina home.jsp
			<jsp:include page="includes/piePagina.jsp"></jsp:include>



******************************************************************************Seccion 6 clase 34
***************************************************************************Darle formato a fecha

	21.- configurar url consultar horarios
		21.1.- Se crea una variable de instancia SimpleDateFormat en la clase HomeController
			private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 	21.2.- Se agrega en el metodo mostrarPrincipal otro atributo al modelo la fecha del dia
			model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));



******************************************************************************Seccion 6 clase 37
**************************************************************Metodo para crear fechas dinamicas
	23.1.- SE CREA PAQUETE --> net.itinajero.app.util
		23.2.- Se crea clase de utilitarios con metodo para generear n fechas
		public class Utileria {
			
			/**
			*Metodo que regresa una lista de String con las n fechas siguientes
			*/
			public static List<String> getNextDays(int count){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				//fecha desde hoy
				Date start = new Date();
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH,count); //Proximos N dias desde hoy
				Date endDate = cal.getTime();
				
				GregorianCalendar gcal = new GregorianCalendar();
				gcal.setTime(start);
				List<String> nextDays = new ArrayList<String>();
				while(!gcal.getTime().after(endDate)) {
					Date d = gcal.getTime();
					gcal.add(Calendar.DATE, 1);
					nextDays.add(sdf.format(d));
				}
				return nextDays;
			}
			
		}
		23.3.- En el metodo mostrarPrincipal del homeController se agrega una lista que contiene las fechas.
			List<String> listaFechas = Utileria.getNextDays(4);
		23.4.- Se agrega la lista al modelo
			model.addAttribute("fechas", listaFechas);
		23.5.- Se modifica el home.jsp colocando un for each para que recorra las fechas
		<c:forEach items="${fechas }" var="fecha">
			<option value="${fecha}">${fecha }</option>
		</c:forEach>	



******************************************************************************Seccion 6 clase 40
*******************************************************************************Ejercicio de JSTL
	25.- Se debe modificar el select de la fecha para que ponga la fecha seleccionada como por defecto
		<select id="fecha" name="fecha"	class="form-control">
			<c:forEach items="${fechas }" var="fecha">
				<c:choose>
					<c:when test="${fechaBusqueda eq fecha}">
						<option value="${fecha}" selected="selected">${fecha }</option>
					</c:when>
					<c:otherwise>
						<option value="${fecha}">${fecha }</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>





*************************************************************Objeto tinymcs para editor de texto
27.5.- Se agrega codigo javaScript para utilizar plugin tinymcs para crear editor de texto		
	<script src="${urlPublic}/tinymce/tinymce.min.js"></script>
	<script>
  		tinymce.init({
      		selector: '#detalle',
      		plugins: "textcolor, table lists code",
      		toolbar: " undo redo | bold italic | alignleft aligncenter alignright alignjustify \n\
      		   	 | bullist numlist outdent indent | forecolor backcolor table code"
  		});
	</script>

	<div class="form-group">
      <label for="detalle">Detalles</label>             
      <textarea class="form-control" name="detalle" id="detalle" rows="10"></textarea>
    </div>  