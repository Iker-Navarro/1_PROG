package ix;

/**
 * Clase que comienza la estructua de excepciones
 */

class RCException extends Exception
{
    void depura(String psError)
    {
        System.out.println("Error: " + psError);
    }


    RCException(String psError)
    {
        super(psError);
        depura(psError);
    }
}

/**
 * @since 1.0
 * @author Roberto Canales
 * @see <a href="https://www.adictosaltrabajo.com/">www.adictosaltrabajo.com</a>
 */

public class Ejemplojavadoc {
	
	/**Constantes públicas de gestión de errores*/
    public static final int ERROR   = 0;
    public static final int LOG     = 1;
    public static final int INFO    = 2;
    
    /**Constructor por defecto*/
    public Ejemplojavadoc() {
    }

    void depura(String sError)
    {
        System.out.println("ejemplojavadoc: " + sError);
    }

    /**
     * 
     * @param args Array de argumentos
     */
    public static void main(String[] args) {

        Ejemplojavadoc objetoAuxiliar = new Ejemplojavadoc();

        try
        {
                objetoAuxiliar.ejecuta();
        }
        catch(RCException e)
        {
            objetoAuxiliar.depura("Excepcion = " + e.getMessage());
        }
    }

    /**
     * Punto de entrada de la aplicación
     * @return true
     * @throws RCException Se genera una excepción genérica.
     */
    public boolean ejecuta() throws RCException
    {
        int error = 0;

        if(error == 0)
        {
            throw new RCException("Invocamos excepciones");
        }

        return true;
    }
}
