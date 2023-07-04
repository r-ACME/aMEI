package com.example.amei.Modelos;

public class Client {

    private Integer id;
    private Integer person_id;
    private Integer company_id;
    private String type;
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Integer active) {
        if (active != 1) {
            this.active = false;
        }
        this.active = true;
    }

    public String queryForInsert(){
        return this.person_id + ", " + this.company_id + ", '" + this.type + "', " + this.active;
    }
}
