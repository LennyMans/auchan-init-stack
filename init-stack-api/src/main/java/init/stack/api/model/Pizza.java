package init.stack.api.model;


public class Pizza {
    private String ref_String_PizzaId;
    private String ref_String_PizzaFlavor;
    private String ref_String_PizzaSize;
    private String ref_String_PizzaSpicy;

    // -- CONSTRUCTOR
    public Pizza () {

    }

    public Pizza(String ref_String_PizzaFlavor, String ref_String_PizzaSize, String ref_String_PizzaSpicy) {
        this.ref_String_PizzaFlavor = ref_String_PizzaFlavor;
        this.ref_String_PizzaSize = ref_String_PizzaSize;
        this.ref_String_PizzaSpicy = ref_String_PizzaSpicy;
    }

    // -- GETTERS
    public String getId() {
        return ref_String_PizzaId;
    }


    public String getPizzaFlavor() {
        return ref_String_PizzaFlavor;
    }


    public String getPizzaSize() {
        return ref_String_PizzaSize;
    }


    public String getPizzaSpicy() {
        return ref_String_PizzaSpicy;
    }

    // -- SETTERS
    public void setId(String id) {
        this.ref_String_PizzaId = id;
    }

    public void setPizzaFlavor(String ref_String_PizzaFlavor) {
        this.ref_String_PizzaFlavor = ref_String_PizzaFlavor;
    }

    public void setPizzaSize(String ref_String_PizzaSize) {
        this.ref_String_PizzaSize = ref_String_PizzaSize;
    }

    public void setPizzaSpicy(String ref_String_PizzaSpicy) {
        this.ref_String_PizzaSpicy = ref_String_PizzaSpicy;
    }
}
