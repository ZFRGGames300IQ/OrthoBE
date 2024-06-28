package ortho.be.ggames.ortho.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FullPatient {
    private String name;
    private String forename;
    private String birthday;
    private String adress;
    private String phone;
    private String id;

    public FullPatient() {
    }

    @JsonCreator
    public FullPatient(@JsonProperty("name") String name, @JsonProperty("forename") String forename, @JsonProperty("birthday") String birthday, @JsonProperty("adress") String adress, @JsonProperty("phone") String phone, @JsonProperty("id") String id) {
        this.name = name;
        this.forename = forename;
        this.birthday = birthday;
        this.adress = adress;
        this.phone = phone;
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
    
    public String getBirthday() {
        return birthday;
    }
    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public String getAdress() {
        return adress;
    }
    
    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
