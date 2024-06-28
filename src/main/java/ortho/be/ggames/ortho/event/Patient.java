package ortho.be.ggames.ortho.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Patient {
    private String name;
    private String forename;
    private String id;

    public Patient() {
    }

    @JsonCreator
    public Patient(@JsonProperty("name") String name, @JsonProperty("forename") String forename, @JsonProperty("id") String id) {
        this.name = name;
        this.forename = forename;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getForename() {
        return forename;
    }
    
    public void setForname(String forename) {
        this.forename = forename;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Nom : %s%nPrénom : %s%nID : %s%n", name, forename, id);
    }
}
