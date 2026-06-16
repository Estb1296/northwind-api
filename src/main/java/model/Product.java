package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Long Id;

    @NotBlank
    @Column(name = "ProductName", nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "SupplierId")
    private Long supplierId;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Long categoryId;

    @Column(name = "QuantityPerUnit")
    private String quantityPerUnit;

    @PositiveOrZero
    @Column(name = "UnitPrice", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @PositiveOrZero
    @Column(name = "UnitsInStock")
    private int unitsInStock;

    @PositiveOrZero
    @Column(name = "UnitsOnOrder")
    private int unitsOnOrder;

    @PositiveOrZero
    @Column(name = "ReorderLevel")
    private int reorderLevel;

    @Column(name = "Discontinued")
    private Boolean discontinued;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(int unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

}
