package init.stack.api.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import init.stack.api.model.Pizza;
import init.stack.api.verif.PizzaVerifId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@RestController
public class PizzaController {

    // -- Checkers
    private static PizzaVerifId ref_PizzaVerifId = new PizzaVerifId();

    // -- GET
    @GetMapping("/pizzas")
    public ResponseEntity<JsonNode> getAllPizza() {

        ////////////////////////////////////////////////////////////
        // -- Retrieve arraylist pizza in mongo
        ArrayList<Pizza> ref_ArrayListPizza = new ArrayList<>();
        Pizza a = new Pizza();
        Pizza b = new Pizza();
        Pizza c = new Pizza();
        Pizza d = new Pizza();

        ref_ArrayListPizza.add(a);
        ref_ArrayListPizza.add(b);
        ref_ArrayListPizza.add(c);
        ref_ArrayListPizza.add(d);

        ////////////////////////////////////////////////////////////

        // -- Init response
        StringBuilder ref_StringBuilder_PizzaListJson = new StringBuilder();

        String ref_String_ToFormat = "{\"id\":\"%s\", \"flavor\":\"%s\",\"size\":\"%s\",\"spicy\":\"%s\"}";
        String ref_String_Start = "[";
        String ref_String_Comma = ",";
        String ref_String_End = "]";

        // -- Start build
        ref_StringBuilder_PizzaListJson.append(ref_String_Start);

           for(int ref_Int_Offset_A = 0; ref_Int_Offset_A < ref_ArrayListPizza.size(); ref_Int_Offset_A++) {

                // -- Retrieve pizza unit
                Pizza ref_Pizza =  ref_ArrayListPizza.get(ref_Int_Offset_A);

                // -- Build node
                String ref_String_JsonObject
                        = String.format(ref_String_ToFormat
                        , ref_Pizza.getId()
                        , ref_Pizza.getPizzaFlavor()
                        , ref_Pizza.getPizzaSize()
                        , ref_Pizza.getPizzaSpicy());

                // -- Add node
                ref_StringBuilder_PizzaListJson.append(ref_String_JsonObject);

                // -- Check comma
                   if(ref_Int_Offset_A != ref_ArrayListPizza.size()-1) {

                       ref_StringBuilder_PizzaListJson.append(ref_String_Comma);
                   }
            };

        // -- Finish build
        ref_StringBuilder_PizzaListJson.append(ref_String_End);

        // -- Build response
        String ref_String_FinalJson = ref_StringBuilder_PizzaListJson.toString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode ref_JsonNode = null;

        try {

            ref_JsonNode = mapper.readTree(ref_String_FinalJson);

        } catch (JsonProcessingException ref_JsonProcessingException) {

            // -- Log
            ref_JsonProcessingException.printStackTrace();
        }

        // -- Commit
        return ResponseEntity.ok(ref_JsonNode);

    }

    @GetMapping("/pizza")
    public ResponseEntity<JsonNode> getPizzaById(HttpServletRequest ref_HttpServletRequest) {

    // -- Get query String
    String ref_String_QueryString = ref_HttpServletRequest.getQueryString();

    // -- Execute
    HashMap<String,Object> ref_HashMap_Executed = ref_PizzaVerifId.verify_Frame(ref_String_QueryString);

    // -- Check
    if(((String)ref_HashMap_Executed.get(PizzaVerifId.REF_STRING_CONS_REPONSE_KEY_STATUS))
            .equals(PizzaVerifId.REF_STRING_VERIF_VALUE_OK)){

        // -- Retrieve data
        HashMap<String,String> ref_HashMap_Data
                =(HashMap<String,String>) ref_HashMap_Executed
                        .get(PizzaVerifId.REF_STRING_CONS_REPONSE_KEY_DATA);

        // -- Extract
        String ref_String_Id = ref_HashMap_Data.get(PizzaVerifId.REF_STRING_CONS_REPONSE_DATA_KEY_ID);

        // -- Log
        System.out.println("ref_String_Id=" + ref_String_Id);

        ////////////////////////////////////////////////////////////////////
        // -- Get pizza of mongo
        Pizza ref_Pizza = new Pizza(); // Recupere de mongo
        ref_Pizza.setPizzaFlavor("cheese");
        ref_Pizza.setPizzaSize("l");
        ref_Pizza.setPizzaSpicy("true");
        ////////////////////////////////////////////////////////////////////

        // -- retrieve by db
        if(ref_Pizza == null) {

            // -- Init response
            String ref_String_Message = "{\"message\":\"Unknown pizza\"}";

            // -- Build response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode ref_JsonNode = null;

            try {

                ref_JsonNode = mapper.readTree(ref_String_Message);

            } catch (JsonProcessingException ref_JsonProcessingException) {

                // -- Log
                ref_JsonProcessingException.printStackTrace();
            }

            // -- Commit
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ref_JsonNode);

        }

        // -- Init response
        String ref_String_ToFormat = "{\"flavor\":\"%s\",\"size\":\"%s\",\"spicy\":\"%s\"}";

        // -- Build response
        String ref_String_FinalJson = String.format(ref_String_ToFormat, ref_Pizza.getPizzaFlavor(), ref_Pizza.getPizzaSize(), ref_Pizza.getPizzaSpicy());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode ref_JsonNode = null;

        try {

            ref_JsonNode = mapper.readTree(ref_String_FinalJson);

        } catch (JsonProcessingException ref_JsonProcessingException) {

            // -- Log
            ref_JsonProcessingException.printStackTrace();
        }

        // -- Commit
        return ResponseEntity.ok(ref_JsonNode);


    }else{

        System.out.println("STP E");
        // -- Retrieve exception
        Exception ref_Exception = (Exception) ref_HashMap_Executed.get(PizzaVerifId.REF_STRING_CONS_REPONSE_KEY_EXCEPTION);

        // -- Init response
        String ref_String_ToFormat = "{\"message\":\"%s\"}";

        // -- Build response
        String ref_String_FinalString = String.format(ref_String_ToFormat, ref_Exception.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode ref_JsonNode = null;

        try {

            ref_JsonNode = mapper.readTree(ref_String_FinalString);

        } catch (JsonProcessingException ref_JsonProcessingException) {

            // -- Log
            ref_JsonProcessingException.printStackTrace();
        }

        // -- Commit
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ref_JsonNode);

    }

    }

    // -- POST
    @GetMapping("/pizzaCreation")
    public ResponseEntity<JsonNode> createPizza(HttpServletRequest ref_HttpServletRequest) {

        // -- Create pizza
        Pizza ref_CreatePizza = new Pizza();

        /////////////////////////////////////////////////////////
        // -- Persist in mongo
        /////////////////////////////////////////////////////////

        // -- Retrieve id of mongo
        String ref_String_Id= "1231321";

        // -- Build response
        String ref_String_ToFormat = "{\"id\":\"%s\"}";
        String ref_String_FinalJson = String.format(ref_String_ToFormat, ref_String_Id);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode ref_JsonNode = null;

        try {

            ref_JsonNode = mapper.readTree(ref_String_FinalJson);

        } catch (JsonProcessingException ref_JsonProcessingException) {

            // -- Log
            ref_JsonProcessingException.printStackTrace();
        }

        // -- Commit
        return ResponseEntity.ok(ref_JsonNode);

    }

    @PostMapping("/pizzaDecoration")
    public Pizza decoratePizza(@RequestBody Pizza ref_DecoratePizza) {
        return ref_DecoratePizza;
    }

}
