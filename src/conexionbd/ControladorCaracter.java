/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

/**
 *
 * @author Usuario
 */
public class ControladorCaracter {
    
    
    public boolean verificarCedula(String numCedula) {
		boolean k = true;
		//try y catch por si la longitud es menor a 10 digitos
                System.out.println("Entra a cca");
		try {
			
		int [] v = new int[10];
		for(int i=0; i<v.length; i++){
			v[i] = Integer.parseInt(numCedula.charAt(i) + "");
		}
		int impar = 0;
		int par = 0;
		for(int i=0; i<v.length; i += 2){
			if( ( v[i] * 2 ) > 9 ) {
				v[i] = ((v[i] * 2) -9 );
			} else {
				v[i] = (v[i] * 2);
			}
			impar += v[i];
		}
		for(int i=1; i<(v.length-1); i+=2 ){
			par += v[i];
		}
		int suma = impar +par;
		int d10 = Integer.parseInt(String.valueOf(suma+10).substring(0, 1) + "0") - suma;
		if(d10 == 10){
			d10 = 0;
		} else {
			d10 = d10;
		}
		if(d10 == v[9]) {
			k=true;
		} else {
			k=false;
		}
		return k;
		}catch(Exception e){
                    System.out.println("Entra a excepcion dentro de cca");
			k = false;
			return k;
		}
		
	}
    
    public boolean comprobarCaracteres(String palabra) throws Throwable{
        ControladorCaracter controlador = new ControladorCaracter();
        
        for(int i=0; i<palabra.length();i++){
            String letra = palabra.charAt(i)+"";
            try {
                if(controlador.comprobacionInterna(letra)==true){
                    return false;
                }
            } catch (Throwable e) {
                return false;
            }
        }
        return true;
        
    }
    
    public boolean comprobacionInterna(String letra) throws Throwable{
        int v;
        try {
            
            v = Integer.parseInt(letra);
            
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
}
