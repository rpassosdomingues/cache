package src;

public class Comanda {
    private String id;
    private String customerName;
    private String orderDetails;
    private boolean isCompleted;
    private String cardapioDisponivel; // Added field for cardapioDisponivel

    // Default constructor
    public Comanda() {
        this.id = "";
        this.customerName = "";
        this.orderDetails = "";
        this.isCompleted = false;
        this.cardapioDisponivel = ""; // Initialize cardapioDisponivel
    }

    // Constructor with parameters
    public Comanda(String id, String customerName, String orderDetails, String cardapioDisponivel) {
        this.id = id;
        this.customerName = customerName;
        this.orderDetails = orderDetails;
        this.isCompleted = false;
        this.cardapioDisponivel = cardapioDisponivel;
    }

    // Getter and Setter methods for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter methods for customerName
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter and Setter methods for orderDetails
    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    // Getter and Setter methods for isCompleted
    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Getter and Setter methods for cardapioDisponivel
    public String getCardapioDisponivel() {
        return cardapioDisponivel;
    }

    public void setCardapioDisponivel(String cardapioDisponivel) {
        this.cardapioDisponivel = cardapioDisponivel;
    }

    // Method to mark the order as completed
    public void completeOrder() {
        this.isCompleted = true;
    }

    // Method to return a string representation of the Comanda object
    @Override
    public String toString() {
        return "Comanda{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderDetails='" + orderDetails + '\'' +
                ", isCompleted=" + isCompleted +
                ", cardapioDisponivel='" + cardapioDisponivel + '\'' +
                '}';
    }
}
