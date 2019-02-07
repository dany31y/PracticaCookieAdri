package utilidades;

import java.util.TreeMap;

import javax.servlet.http.Cookie;

import java.util.Set;

public class Comprobaciones {
	public static boolean comprobarUsuarios(TreeMap<String,String> usuarios,String user,String pass) {
		boolean correcto=false;
		Set<String> keys = usuarios.keySet();
		for(String key: keys){
			if(key.equals(user)) {
				if(usuarios.get(user).equals(pass)) {
					correcto=true;
				}
			}
		}
		return correcto;
	}
	//Va a devolver la posicion de la Cookie. -1 si no existe
	public static int comprobarCookies(Cookie cookies [],String user) {
		int contador=-1;
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals(user)) {
					contador=i;
				}
			}
		}
		return contador;
	}
}
