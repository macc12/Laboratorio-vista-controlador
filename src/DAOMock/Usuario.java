package DAOMock;

/**
 *
 * @author Marco
 */
public class Usuario {
    int id;
    String pass;
    String tipo;

    public Usuario(int id, String pass, String tipo) {
        this.id=id;
        this.pass = pass;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
