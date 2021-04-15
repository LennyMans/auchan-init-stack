package init.stack.api.model;



public class Pizza {

    private String ref_String_PizzaId = "No Set";
    private String ref_String_PizzaFlavor = "No Set";
    private String ref_String_PizzaSize = "No Set";
    private String ref_String_PizzaSpicy = "No Set";


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
