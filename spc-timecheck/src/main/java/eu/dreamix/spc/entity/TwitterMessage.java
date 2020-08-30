package eu.dreamix.spc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class TwitterMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_str;
    
    @NotNull
    private String keyword;
    
    //created time
    private String created_at;

    //tweet text
    private String text;
    
    private String source;
    
    private String truncated;
    
    private String lang;
    
    public TwitterMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_str() {
        return id_str;
    }
    
    public void setId_str(Long id_str) {
        this.id_str = id_str;
    }
    
    public String getKeyword() {
        return keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    public String getTruncated() {
        return truncated;
    }

    public void setTruncated(String truncated) {
        this.truncated = truncated;
    }
    
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}