package eu.dreamix.spc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class SimulatorInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
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
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}