package servlets;
import java.io.*;
import java.util.TreeMap;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;  
import utilidades.Comprobaciones;
  
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {  
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("serial")
	private TreeMap<String,String> usuarios = new TreeMap<String,String>() {{
		put("adrian.morales", "alumno");
		put("daniel.pernesiu", "alumno2");
		put("andrei.f", "alumno3");
	}}; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Para mostrar el formulario vacio, ponemos todos los atributos del mismo vacios
		request.setAttribute("salidaIncorrecta", "");
		request.setAttribute("contadorVisitas","");
		
		response.setContentType("text/html;charset=UTF-8");
		String vista = "/login.jsp";
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
        dispatcher.forward(request, response); 
	}
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  
    try{  
	  	String uidPag=request.getParameter("uidPage");
		String nombre= request.getParameter("user");
		String pass=request.getParameter("pass");	
		Cookie cookies[]=request.getCookies();//Recogemos todas las cookies existentes de la pagina
		//Si venimos de Login...
		if(uidPag.equals("1")) {
			//Comprobamos si el usuario existe 
			if(Comprobaciones.comprobarUsuarios(usuarios,nombre,pass)) {
				//Si no existen cookies o no hay cookies con el nombre introducido por el usuario...
			    if(cookies==null || Comprobaciones.comprobarCookies(cookies, nombre)<0) {
			    	String profesion="";	
			    	String contadorVisitas="1";
			    	request.setAttribute("contadorVisitas",contadorVisitas);
			    	//Creamos la cookie 
				    Cookie ck=new Cookie(nombre,nombre + "&" + profesion + "&" + contadorVisitas);
				    ck.setMaxAge(60 * 60 * 24 * 365 * 10);
				    //Añadimos la cookie 
				    response.addCookie(ck); 
				    
				    //Mandamos la pagina a Bienvenida,donde el usuario elegira su profesion
				    response.setContentType("text/html;charset=UTF-8");
				    String vista = "/bienvenida.jsp";
			    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
			        dispatcher.forward(request, response); 
	 
			    }
			    else {
		    		//Cogemos la cookie que tenga como nombre el introducido por el usuario
		    		Cookie ck=cookies[Comprobaciones.comprobarCookies(cookies, nombre)];
		    		ck.setMaxAge(60 * 60 * 24 * 365 * 10);
		    		//Dividimos cada parte del Valor de la cookie y lo metemos en un array
		    		String valor[]=ck.getValue().split("&");
		    		//Nos quedamos con la profesion
		    		String profesion=valor[1];
		    		request.setAttribute("profesion", profesion);
		    		//Nos quedamos con el contador de visitas del usuario
		    		String contadorVisitas=valor[2];
		    		request.setAttribute("contadorVisitas",contadorVisitas);
		    		response.setContentType("text/html;charset=UTF-8");
				    String vista = "/bienvenida.jsp";
			    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
			        dispatcher.forward(request, response); 
			    }
			}
			//Si no existe el usuario,volvemos a mostrar el formulario mostrando un mensaje de error
			else {
				request.setAttribute("salidaIncorrecta", "La contraseña y la contraseña son incorrectas");
				response.setContentType("text/html;charset=UTF-8");
				String vista = "/login.jsp";
		    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
		        dispatcher.forward(request, response); 
			}
		}
		//Si venimos de Bienvenida...
		else {
			//Buscamos la cookie que coincide con el nombre
			Cookie ck=cookies[Comprobaciones.comprobarCookies(cookies, nombre)];
			ck.setMaxAge(60 * 60 * 24 * 365 * 10);
			//Cogemos el contador de Visitas que tiene la cookie y le sumamos 1
			String contadorVisitas=(ck.getValue().split("&"))[2];
			contadorVisitas=Integer.toString(Integer.parseInt(contadorVisitas)+1);
			request.setAttribute("contadorVisitas",contadorVisitas);
			//Guardamos toda la informacion en la cookie
			String profesion=request.getParameter("profesion");
    		ck.setValue(nombre + "&" + profesion + "&" +contadorVisitas);
    		//Añadimos la cookie
    		response.addCookie(ck);
    		request.setAttribute("contadorVisitas","");
			request.setAttribute("salidaIncorrecta", "");

			//Volvemos a la pagina de Login
			response.setContentType("text/html;charset=UTF-8");
			String vista = "/login.jsp";
	    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
	        dispatcher.forward(request, response); 
		}
    }catch(Exception e){System.out.println(e);}  
  }
}	