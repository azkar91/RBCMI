package Beans;
// Generated 03/10/2015 21:05:39 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Tecnico generated by hbm2java
 */
public class Tecnico  implements java.io.Serializable {


     private Integer idTecnico;
     private String nmTecnico;
     private String tel1tecnico;
     private String tel2tecnico;
     private String emailTecnico;
     private String cpfTecnico;
     private Set ocorrencias = new HashSet(0);

    public Tecnico() {
    }

	
    public Tecnico(String nmTecnico) {
        this.nmTecnico = nmTecnico;
    }
    public Tecnico(String nmTecnico, String tel1tecnico, String tel2tecnico, String emailTecnico, String cpfTecnico, Set ocorrencias) {
       this.nmTecnico = nmTecnico;
       this.tel1tecnico = tel1tecnico;
       this.tel2tecnico = tel2tecnico;
       this.emailTecnico = emailTecnico;
       this.cpfTecnico = cpfTecnico;
       this.ocorrencias = ocorrencias;
    }
   
    public Integer getIdTecnico() {
        return this.idTecnico;
    }
    
    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }
    public String getNmTecnico() {
        return this.nmTecnico;
    }
    
    public void setNmTecnico(String nmTecnico) {
        this.nmTecnico = nmTecnico;
    }
    public String getTel1tecnico() {
        return this.tel1tecnico;
    }
    
    public void setTel1tecnico(String tel1tecnico) {
        this.tel1tecnico = tel1tecnico;
    }
    public String getTel2tecnico() {
        return this.tel2tecnico;
    }
    
    public void setTel2tecnico(String tel2tecnico) {
        this.tel2tecnico = tel2tecnico;
    }
    public String getEmailTecnico() {
        return this.emailTecnico;
    }
    
    public void setEmailTecnico(String emailTecnico) {
        this.emailTecnico = emailTecnico;
    }
    public String getCpfTecnico() {
        return this.cpfTecnico;
    }
    
    public void setCpfTecnico(String cpfTecnico) {
        this.cpfTecnico = cpfTecnico;
    }
    public Set getOcorrencias() {
        return this.ocorrencias;
    }
    
    public void setOcorrencias(Set ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    @Override
    public String toString(){
        return this.nmTecnico;
    }


}


