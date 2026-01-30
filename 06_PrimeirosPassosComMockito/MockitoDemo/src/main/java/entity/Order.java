package entity;

import java.time.LocalDateTime;

public class Order {
    private String id;
    private String productName;
    private Long amount;
    private LocalDateTime creationDate;

    public Order() {
    }

    public Order(String id, String productName, Long amount, LocalDateTime creationDate) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
