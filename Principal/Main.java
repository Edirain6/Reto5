
package Principal;
import Vistas.*;
import Modelo.*;
import Controlador.*;



public class Main {

    public static void main(String[] args) {
        
        Conexion conexion = new Conexion();
        conexion.getConnection();
        
        ListarModeloDB listas = new ListarModeloDB();
        listas.getListaGerentes();
        listas.getListaOperarios();
        listas.getListaDomiciliarios();        
        
        Login login_usuario = new Login();
        login_usuario.setVisible(true);
        
        
    }
}
