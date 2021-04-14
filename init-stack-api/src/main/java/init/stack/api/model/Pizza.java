package init.stack.api.model;

import javax.persistence.Entity;

@Entity
public class Pizza {
    private long ref_Long_PizzaId;
    private String ref_String_PizzaFlavor;
    private String ref_String_PizzaSize;
    private Boolean ref_Boolean_PizzaSpicy;

    // -- CONSTRUCTOR
    public Pizza () {

    }

    public Pizza(String ref_String_PizzaFlavor, String ref_String_PizzaSize, Boolean ref_Boolean_PizzaSpicy) {
        this.ref_String_PizzaFlavor = ref_String_PizzaFlavor;
        this.ref_String_PizzaSize = ref_String_PizzaSize;
        this.ref_Boolean_PizzaSpicy = ref_Boolean_PizzaSpicy;
    }
}
