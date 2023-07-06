package com.example.amei.Modelos;

public class ScheduleService {

    private Integer id;
    private Integer schedule_id;
    private Integer service_id;
    private String amount_service;
    private Integer product_id;
    private String amount_product;
    private Integer completion;
    private String discount;

    public ScheduleService() {
    }

    public ScheduleService(Integer id, Integer schedule_id, Integer service_id, String amount_service, Integer product_id, String amount_product, Integer completion, String discount) {
        this.id = id;
        this.schedule_id = schedule_id;
        this.service_id = service_id;
        this.amount_service = amount_service;
        this.product_id = product_id;
        this.amount_product = amount_product;
        this.completion = completion;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Integer schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getAmount_service() {
        return amount_service;
    }

    public void setAmount_service(String amount_service) {
        this.amount_service = amount_service;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getAmount_product() {
        return amount_product;
    }

    public void setAmount_product(String amount_product) {
        this.amount_product = amount_product;
    }

    public Integer getCompletion() {
        return completion;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String queryForInsert(){
        return this.schedule_id + ", " +
                this.service_id + ", '" +
                this.amount_service + "', " +
                this.product_id + ", '" +
                this.amount_product + "', " +
                this.completion + ", '" +
                this.discount + "'";
    }

    public String queryForUpdate(){
        return "UPDATE schedule_service SET schedule_id = " + this.schedule_id +
                ",  service_id = " + this.service_id +
                ", amount_service = '" + this.amount_service +
                "', product_id = " + this.product_id +
                ", amount_product = '" + this.amount_product +
                "', completion = " + this.completion +
                ", discount = '" + this.discount +
                "' WHERE id = " + this.id;
    }
}
