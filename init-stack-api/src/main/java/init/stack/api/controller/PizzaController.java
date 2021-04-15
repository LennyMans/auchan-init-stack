package init.stack.api.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import init.stack.api.model.Pizza;
import init.stack.api.verif.PizzaVerifDecoration;
import init.stack.api.verif.PizzaVerifId;
import org.bson.Document;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;


@RestController
public class PizzaController {

    // -- VARS
    private static PizzaVerifId ref_PizzaVerifId = new PizzaVerifId();
    private static PizzaVerifDecoration ref_PizzaVerifDecoration = new PizzaVerifDecoration();


    // -- GET ----------------------------------------------------------------------------------------------------------
    @GetMapping("/pizzas")
    public ResponseEntity<JsonNode> getAllPizza() {

        // -- Get pizza of mongo
        ArrayList<Pizza> ref_ArrayListPizza = new ArrayList<>();

        // -- Init mongo
        MongoCredential credential = MongoCredential.createCredential("userpizza", "auchan-init-stack", "1234".toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();
        MongoClient ref_MongoClient = new MongoClient(new ServerAddress("127.0.0.1", 27017), Arrays.asList(credential), options);

        MongoDatabase ref_MongoDatabaseDatabase = ref_MongoClient.getDatabase("auchan-init-stack");
        MongoCollection ref_MongoCollection = ref_MongoDatabaseDatabase.getCollection("pizza");

        FindIterable<Document> ref_FindIterable =  ref_MongoCollection.find();
        Iterator<Document> ref_MongoCursor = ref_FindIterable.iterator();

        while(ref_MongoCursor.hasNext() == Boolean.TRUE) {

            // -- Get Document
            Document ref_Doc = ref_MongoCursor.next();

            System.out.println( ref_Doc.get("_id"));
            System.out.println( ref_Doc.get("flavor"));
            System.out.println( ref_Doc.get("size"));
            System.out.println( ref_Doc.get("spicy"));

            // -- Build pizza
            Pizza ref_Pizza = new Pizza();
            ref_Pizza.setId(ref_Doc.get("_id").toString());
            ref_Pizza.setPizzaFlavor( ref_Doc.get("flavor").toString());
            ref_Pizza.setPizzaSize( ref_Doc.get("size").toString());
            ref_Pizza.setPizzaSpicy(ref_Doc.get("spicy").toString());

            // -- Push pizza
            ref_ArrayListPizza.add(ref_Pizza);

        }


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

        // -- Init mongo
        MongoCredential credential = MongoCredential.createCredential("userpizza", "auchan-init-stack", "1234".toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();
        MongoClient ref_MongoClient = new MongoClient(new ServerAddress("127.0.0.1", 27017), Arrays.asList(credential), options);

        MongoDatabase ref_MongoDatabaseDatabase = ref_MongoClient.getDatabase("auchan-init-stack");
        MongoCollection ref_MongoCollection = ref_MongoDatabaseDatabase.getCollection("pizza");

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", ref_String_Id);
        FindIterable<Document> ref_FindIterable =  ref_MongoCollection.find(whereQuery);
        Iterator<Document> ref_MongoCursor = ref_FindIterable.iterator();

        while(ref_MongoCursor.hasNext() == Boolean.TRUE) {

            // -- Get Document
            Document ref_Doc = ref_MongoCursor.next();

            System.out.println( ref_Doc.get("_id"));
            System.out.println( ref_Doc.get("flavor"));
            System.out.println( ref_Doc.get("size"));
            System.out.println( ref_Doc.get("spicy"));

            ref_Pizza.setId(ref_Doc.get("_id").toString());
            ref_Pizza.setPizzaFlavor( ref_Doc.get("flavor").toString());
            ref_Pizza.setPizzaSize( ref_Doc.get("size").toString());
            ref_Pizza.setPizzaSpicy(ref_Doc.get("spicy").toString());

        }



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

    @GetMapping("/pizzaCreation")
    public ResponseEntity<JsonNode> createPizza(HttpServletRequest ref_HttpServletRequest) {

        // -- Create ID
        String ref_String_id_Pizza = UUID.randomUUID().toString();

        // -- Init mongo
        MongoCredential credential = MongoCredential.createCredential("userpizza", "auchan-init-stack", "1234".toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();
        MongoClient ref_MongoClient = new MongoClient(new ServerAddress("127.0.0.1", 27017), Arrays.asList(credential), options);

        MongoDatabase ref_MongoDatabaseDatabase = ref_MongoClient.getDatabase("auchan-init-stack");
        MongoCollection ref_MongoCollection = ref_MongoDatabaseDatabase.getCollection("pizza");

        // -- Commit
        Document ref_Document = new Document();

        ref_Document.append("_id", ref_String_id_Pizza);
        ref_Document.append("flavor", "Not set");
        ref_Document.append("size", "Not set");
        ref_Document.append("spicy", "Not set");

        // -- Insert in mongo
        ref_MongoCollection.insertOne(ref_Document);

        // -- Build response
        String ref_String_ToFormat = "{\"id\":\"%s\"}";
        String ref_String_FinalJson = String.format(ref_String_ToFormat, ref_String_id_Pizza);
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


    // -- POST ----------------------------------------------------------------------------------------------------------
    @PostMapping("/pizzaDecoration")
    public ResponseEntity<JsonNode> decoratePizza(HttpServletRequest ref_HttpServletRequest) {

        // -- Get query String
        String ref_String_QueryString = ref_HttpServletRequest.getQueryString();

        // -- Execute
        HashMap<String,Object> ref_HashMap_Executed = ref_PizzaVerifDecoration.verify_Frame(ref_String_QueryString);

        // -- Check
        if(((String)ref_HashMap_Executed.get(PizzaVerifDecoration.REF_STRING_CONS_REPONSE_KEY_STATUS))
                .equals(PizzaVerifDecoration.REF_STRING_VERIF_VALUE_OK)){

            // -- Retrieve data
            HashMap<String,String> ref_HashMap_Data
                    =(HashMap<String,String>) ref_HashMap_Executed
                    .get(PizzaVerifDecoration.REF_STRING_CONS_REPONSE_KEY_DATA);

            // -- Extract
            String ref_String_Id = ref_HashMap_Data.get(PizzaVerifDecoration.REF_STRING_CONS_REPONSE_DATA_KEY_ID);
            String ref_String_Flavor = ref_HashMap_Data.get(PizzaVerifDecoration.REF_STRING_CONS_REPONSE_DATA_KEY_FLAVOR);
            String ref_String_Size = ref_HashMap_Data.get(PizzaVerifDecoration.REF_STRING_CONS_REPONSE_DATA_KEY_SIZE);
            String ref_String_Spicy = ref_HashMap_Data.get(PizzaVerifDecoration.REF_STRING_CONS_REPONSE_DATA_KEY_SPICY);

            // -- Bind to document
            BasicDBObject  ref_Document_Updated= new BasicDBObject();
                ref_Document_Updated.append("flavor",ref_String_Flavor);
                ref_Document_Updated.append("size", ref_String_Size);
                ref_Document_Updated.append("spicy", ref_String_Spicy);

            // -- Log
            System.out.println("ref_String_Id=" + ref_String_Id);

            // -- Init mongo
            MongoCredential credential = MongoCredential.createCredential("userpizza", "auchan-init-stack", "1234".toCharArray());
            MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();
            MongoClient ref_MongoClient = new MongoClient(new ServerAddress("127.0.0.1", 27017), Arrays.asList(credential), options);

            MongoDatabase ref_MongoDatabaseDatabase = ref_MongoClient.getDatabase("auchan-init-stack");
            MongoCollection ref_MongoCollection = ref_MongoDatabaseDatabase.getCollection("pizza");

            // -- Find
            UpdateResult ref_UpdateResult = ref_MongoCollection.updateOne(Filters.eq("_id", ref_String_Id), combine(set("flavor",ref_String_Flavor), set("size", ref_String_Size), set("spicy", ref_String_Spicy)));

            // -- Build response
            String ref_String_FinalJson
                    = ( ref_UpdateResult.wasAcknowledged() == Boolean.TRUE)
                    ? "{\"message\":\"PizzaUpdated\"}"
                    : "{\"message\":\"Unknown pizza\"}";

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

}
