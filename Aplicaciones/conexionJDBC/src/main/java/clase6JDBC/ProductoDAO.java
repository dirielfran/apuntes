package clase6JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import clase4Interfaz.Producto;
import clase6JDBC.CafeStoreException;
import clase6JDBC.CalculadoraException;
import clase6JDBC.JDBC;
import clase6JDBC.ModificaException;

public class ProductoDAO  {
	/*
	 * 1) public static void inserta(Producto producto)
			Inserta un producto en la base de datos.
			Este método debe lanzar las siguientes excepciones:
			a) Una excepción genérica por si surge algún error inesperado.
			b) Una excepción descriptiva si se intenta insertar 2 productos con la misma descripción.
	 */
	
	 public static void inserta(Producto producto) throws CalculadoraException, Exception {
		 Connection conexion = null;
		 PreparedStatement sentencia= null;
		 try {
			 String sql = "insert into producto (nombre,precio, descripcion) values (?,?,?)";
			 conexion = JDBC.administradoDeConexion();
			 sentencia=conexion.prepareStatement(sql);
			 sentencia.setString(1, producto.getNombre());
			 sentencia.setDouble(2, producto.getPrecio());
			 sentencia.setString(3, producto.getDescripcion());
			 			 
			 if(existe(producto.getDescripcion()))  {
				 throw new CalculadoraException();
			 }else {
				 sentencia.execute();
			 }
			 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {
				sentencia.close();
				conexion.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	 }
	 
	private static boolean existe(String descripcion) {
		Connection con= null;
		PreparedStatement sentencia= null;
		boolean elvis= false;
		try {
			String qry ="select * from producto where descripcion = ?";
			con= JDBC.administradoDeConexion();
			sentencia= con.prepareStatement(qry);
			sentencia.setString(1, descripcion);
			
			ResultSet result = sentencia.executeQuery();
			if (result.next()) {
				elvis=true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				con.close();
				sentencia.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}return elvis;
	}
		
		/* 2) public static void modificá (Producto producto)
		 Modifica el precio y la descripción a partir de su id.
		 Este método debe lanzar las siguientes excepciones:
		 a) Una excepción genérica por si surge algún error inesperado.
		 b) Una excepción descriptiva si el producto no existe.*/
		
		public static void modifica(Producto producto) throws ModificaException, Exception {
			Connection conexion= null;
			PreparedStatement sentencia= null;
			try {
			String sql= "update producto set precio=?, descripcion=? where id =?";
			 conexion = JDBC.administradoDeConexion();
			 sentencia=conexion.prepareStatement(sql);
			 sentencia.setDouble(1, producto.getPrecio());
			 sentencia.setString(2, producto.getDescripcion());
			 sentencia.setInt(3, producto.getId());
			 sentencia.execute();
			 	if (noExiste(producto.getId())) {
			 		throw new ModificaException();
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}finally {
				try {
					sentencia.close();
					conexion.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}

		private static boolean noExiste(int id) {
			
			Connection con= null;
			PreparedStatement sentencia= null;
			boolean elvis= false;
			try {
				String qry ="select * from producto where id = ?";
				con= JDBC.administradoDeConexion();
				sentencia= con.prepareStatement(qry);
				sentencia.setInt(1, id);
				
				ResultSet result = sentencia.executeQuery();
				
				if(result.next()) {
					throw new ModificaException();
				}else {
					elvis=true;
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					con.close();
					sentencia.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return elvis;
		}
			/*3) public static void elimina (int id)
				Elimina un producto a partir de su id.
				Este método debe lanzar las siguientes excepciones:
				a) Una excepción genérica por si surge algún error inesperado.
				b) Una excepción descriptiva si el producto no existe*/
			
		public static void elimina(int id) {
			Connection conexion= null;
			PreparedStatement secuencia= null;
			
			try {
				String sql = "delete from Producto where id=?";
				conexion = JDBC.administradoDeConexion();
				secuencia = conexion.prepareStatement(sql);
				secuencia.setInt(1, id );
				
				if (noExiste(id)) {
					throw new ModificaException();
				}else {
					secuencia.execute();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					secuencia.close();
					conexion.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}
			/*
			 * 4) public static Producto getProducto(int id)
				Busca un producto a partir de su id.
				a) Una excepción genérica por si surge algún error inesperado.
				b) Una excepción descriptiva si el producto no existe.
			 */
		public static Producto getProducto(int id) {
			Producto p= new Producto();
			Connection con= null;
			PreparedStatement sentencia=null;
			try {
				String sql = "select * from Producto where id=?";
				con=JDBC.administradoDeConexion();
				sentencia= con.prepareStatement(sql);
				sentencia.setInt(1, p.getId());
				ResultSet resultado = sentencia.executeQuery();
				if (noExiste(p.getId())) {
					throw new ModificaException();
				}
			} catch (Exception e) {
				e.getMessage();
			}finally {
				try {
					sentencia.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		/*Continuar el ejercicio integrador de la clase 7.
Agregar en ProductoDao el siguiente método:
public static List<Producto> getProductos() throws CafeStoreException
El objetivo de este método es devovler una colección de objetos producto.*/
		
		public static List <Producto> getProductos() throws CafeStoreException {
			List <Producto> lista = new ArrayList<Producto>();
			Connection con= null;
			PreparedStatement sentencia=null;
			try {
				String sql = "select * from Producto";
				con=JDBC.administradoDeConexion();
				sentencia= con.prepareStatement(sql);
				ResultSet resultado = sentencia.executeQuery();
				while (resultado.next()) {
					Producto p= new Producto();
					p.setNombre(resultado.getString("nombre"));
					p.setId(resultado.getInt("id"));
					p.setPrecio(resultado.getDouble("precio"));
					p.setDescripcion(resultado.getString("descripcion"));
					lista.add(p);					
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					sentencia.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return lista;
		}
		
		
		
		
		
			
	}
	 	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

