<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar producto</title>
</head>
<body>
	<h1>Editar un producto</h1>
	<form action="productos" method="post">
	<c:set var="producto" value="${producto}"></c:set><!-- para extraer los valores del controlador -->
	<input type="hidden" name="opcion" value="editar"><!-- identificar al formulario para saber que tipo de formulario es -->
	<input type="hidden" name="id" value="${producto.id}"><!-- Campo oculto para poder realizar la actualizacion en el servlet -->
		<table border="1">
			<tr>
			<td>Nombre:</td>
			<td><input type="text" name="nombre" size="50" value="${producto.nombre}"></td>
			</tr>
			<tr>
			<td>Cantidad:</td>
			<td><input type="text" name="cantidad" size="50" value="${producto.cantidad}"></td>
			</tr>
			<tr>
			<td>Precio:</td>
			<td><input type="text" name="precio" size="50" value="${producto.precio}"></td>
			</tr>			
		</table>
		<input type="submit" value="Guardar" >
	
	</form>
</body>
</html>