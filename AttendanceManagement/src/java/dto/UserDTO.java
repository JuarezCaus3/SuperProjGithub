package dto;

import java.io.Serializable;

public class UserDTO implements Serializable{

    protected long id;
    protected String password;    
    protected String name;
    protected String email;

    public UserDTO() {
    }    
    
    public UserDTO(long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }
    
    public void reset() {
        setPassword(null);
        setName(null);
        setEmail(null);
    }        

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

}
