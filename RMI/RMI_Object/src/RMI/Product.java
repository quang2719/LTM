package RMI;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 20151107L;

    private String id;
    private String code;
    private double importPrice;
    private double exportPrice;

    // Constructor không tham số
    public Product() {
    }

    // Constructor đầy đủ tham số
    public Product(String id, String code, double importPrice, double exportPrice) {
        this.id = id;
        this.code = code;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
    }

    // Getter và Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    @Override
    public String toString() {
        return "Product{id='" + id + "', code='" + code + "', importPrice=" + importPrice + ", exportPrice=" + exportPrice + "}";
    }
}
