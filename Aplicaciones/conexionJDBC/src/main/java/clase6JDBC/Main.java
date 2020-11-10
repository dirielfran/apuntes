package clase6JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import clase4Interfaz.Producto;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection conexion = JDBC.administradoDeConexion();
		Statement sentencia= conexion.createStatement();
		try {
			
			if (conexion== null) {
				System.out.println("No estoy conectado");
			} else {
				System.out.println("Me conecte!!");
			}
			ProductoDAO dao= new ProductoDAO();
			List <Producto> lista= dao.getProductos();
			for (Producto producto : lista) {
				System.out.println(producto.getNombre());
				System.out.println(producto.getPrecio());
			}
			
//			String ricardo= "update usuarios set nombre = 'La Yalaury Tobosa' where nombre = 'Yalaury Tobosa'";
//			Boolean yala=sentencia.execute(ricardo);
//			if (yala) {
//				System.out.println("Su modificacion fue hecha con exito!");
//			} else {
//				System.out.println("Su modificacion no fue realizada");
//			}
//			String antonio= "select * from usuarios";
//			ResultSet resultado= sentencia.executeQuery(antonio); 
//			while (resultado.next()) {
//				System.out.println(resultado.getString("Nombre"));
//				
//			}
			//INSERTAR
//			Producto producto= new Producto("Xiaomi",5785.2,"tecnologico 3.0");
//			ProductoDAO dao= new ProductoDAO();
//			dao.inserta(producto);
//			dao.getProducto(producto.getId());
			
			//MODIFICA
//			Producto producto1 = new Producto(58,"IPHONE",58.2,"11ProMax");
//			ProductoDAO dao= new ProductoDAO();
//			dao.modifica(producto);
//			dao.getProducto(producto.getId());
			
			//ELIMINA
//			ProductoDAO dao = new ProductoDAO();
//			dao.elimina(18);
			//***********Arreglos HashSet
//			HashSet<Producto> hashSet = new HashSet<Producto>();
//			hashSet.add(producto1);
//			hashSet.add(producto1);
//			
//			System.out.println(hashSet);
//			hashSet.remove(producto);
//			System.out.println(hashSet);
			//***********Arreglos TreeSet
//			TreeSet<String> treeSet = new TreeSet<String>();
//			 treeSet.add("element1");
//			 treeSet.add("element2");
//			 treeSet.add("element3");
//			 System.out.println(treeSet);
//			 treeSet.remove("element2");
//			 System.out.println(treeSet);
//			//***********Arreglos TreeSet
//			 Set<String> linkedHashSet = new TreeSet<String>();
//			 linkedHashSet.add("element1");
//			 linkedHashSet.add("element2");
//			 linkedHashSet.add("element3");
//			 System.out.println(linkedHashSet);
//			 linkedHashSet.remove("element1");
//			 System.out.println(linkedHashSet);
			//***********Arreglos TreeSet
//			 List<String> arrayList = new ArrayList<String>();
//			 arrayList.add("element1");
//			 arrayList.add("element2");
//			 arrayList.add("element3");
//			 System.out.println(arrayList.get(1));
//			 System.out.println(arrayList);
//			 arrayList.remove("element3");
//			 
			 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			conexion.close();
			sentencia.close();
		}
	}

}
