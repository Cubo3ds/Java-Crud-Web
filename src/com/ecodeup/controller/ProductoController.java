package com.ecodeup.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecodeup.dao.ProductoDAO;
import com.ecodeup.model.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "administra peticiones para la tabla productos", urlPatterns = { "/productos" })
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion=request.getParameter("opcion");//estraer el parametro que viene de la vista index
		if(opcion.equals("crear")) {
			System.out.println("Usted ha presionado la opcion crear");
			RequestDispatcher requestDispatcher= request.getRequestDispatcher("/views/crear.jsp");//ir al archivo crear.jsp
			requestDispatcher.forward(request, response);//ir a la vista crea.jsp
		}else if(opcion.equals("listar")){
			
			ProductoDAO productoDAO= new ProductoDAO();
			List<Producto> lista =new ArrayList<>();//creamos la lista
			
			try {
				
				lista=productoDAO.obtenerProductos();
				for (Producto producto : lista) {//para conocer si se estan obteniendo los productos desde consola
					System.out.println(producto);
				}
				request.setAttribute("lista", lista);//enviar el objeto a la vista
				
				RequestDispatcher requestDispatcher= request.getRequestDispatcher("/views/listar.jsp");//ir al archivo crear.jsp
				requestDispatcher.forward(request, response);//ir a la vista crea.jsp
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Usted ha presiona la opcion listar");
			
		}else if(opcion.equals("meditar")) {
			
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println("Usted ha seleccionado editar una opcion"+id);
			ProductoDAO productoDAO=new ProductoDAO();
			Producto p=new Producto();
			try {
				p=productoDAO.obtenerProducto(id);
				System.out.println(p);
				request.setAttribute("producto",p);
				RequestDispatcher requestDispatcher= request.getRequestDispatcher("/views/editar.jsp");//ir al archivo crear.jsp
				requestDispatcher.forward(request, response);//ir a la vista crea.jsp
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(opcion.equals("eliminar")) {
			
			ProductoDAO productoDAO=new ProductoDAO();
			int id=Integer.parseInt(request.getParameter("id"));
			try {
				productoDAO.eliminar(id);
				System.out.println("Registro eliminado satisfactoriamente...");
				RequestDispatcher requestDispatcher= request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);//ir a la vista crea.jsp
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion=request.getParameter("opcion");//extraer el parametro que viene de la vista crear
		Date fechaActual=new Date();
		
		if (opcion.equals("guardar")) {//funcion de guardar proveniente de la vista crear hacia nuestro controlador
			
			ProductoDAO productoDAO=new ProductoDAO();//clase DAO creada en los recursos del sistema
			Producto producto=new Producto();//clase Producto creada en los recursos del sistema en model
			producto.setNombre(request.getParameter("nombre"));//pasar los valores a lo que estan en nuestra vista crear.jsp
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));//se realiza un parseo de double a string al elemento cantidad
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));//Aqui obtenemos la fecha y con el objeto de tipo sql asignamosla fecha actual
				
			try {
				productoDAO.guardar(producto);
				System.out.println("Registro guardado satisfactoriamente...");
				RequestDispatcher requestDispatcher= request.getRequestDispatcher("/index.jsp");//ir al archivo crear.jsp
				requestDispatcher.forward(request, response);//ir a la vista crea.jsp
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(opcion.equals("editar")) {
			Producto producto=new Producto();
			ProductoDAO productoDAO=new ProductoDAO();
			
			producto.setId(Integer.parseInt( request.getParameter("id")));
			producto.setNombre(request.getParameter("nombre"));//pasar los valores a lo que estan en nuestra vista crear.jsp
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));//se realiza un parseo de double a string al elemento cantidad
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));//Aqui obtenemos la fecha y con el objeto de tipo sql asignamosla fecha actual
			try {
				productoDAO.editar(producto);
				System.out.println("Registro editado satisfactoriamente...");
				RequestDispatcher requestDispatcher= request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);//ir a la vista crea.jsp
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		//doGet(request, response);
	}

}
