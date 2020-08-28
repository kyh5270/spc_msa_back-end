package eu.dreamix.spc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class SimulatorInput {

    @NotNull
    private String keyword;
    
    public SimulatorInput() {
    }

    public String getKeyword() {
        return keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
}