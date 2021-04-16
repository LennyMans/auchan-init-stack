package init.stack.api.verif;

import init.stack.api.exceptions.PizzaNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PizzaVerifId {

    // endpoint A /pizzaCreation -- Ok  + json  (status=pizza created successfully ) )si existe pas, error ou 201 si existe deja + reponse body "pizza already existe dans un json)

    // -- CONS
    public static final int REF_INT_OFFSET_ID = 1;
    public static final int REF_INT_OFFSET_FLAVOR = 2;
    public static final int REF_INT_OFFSET_SIZE = 3;
    public static final int REF_INT_OFFSET_SPICY = 4;

    public static final String REF_STRING_REGEX_ID = "^id=([0-9 a-z A-Z -]{1,})$";


    // -- CONS REPONSE KEY
    public static String REF_STRING_CONS_REPONSE_KEY_STATUS = "REF_STRING_CONS_REPONSE_KEY_STATUS";
    public static String REF_STRING_CONS_REPONSE_KEY_DATA = "REF_STRING_CONS_REPONSE_KEY_DATA";
    public static String REF_STRING_CONS_REPONSE_DATA_KEY_ID = "REF_STRING_CONS_REPONSE_KEY_ID";

    public static String REF_STRING_CONS_REPONSE_KEY_EXCEPTION = "REF_STRING_CONS_REPONSE_KEY_EXCEPTION";


    // -- CONS REPONSE VALUE
    public static String REF_STRING_VERIF_VALUE_NOTSET = "REF_STRING_VERIF_VALUE_NOTSET";
    public static String REF_STRING_VERIF_VALUE_OK = "REF_STRING_VERIF_OK";
    public static String REF_STRING_VERIF_VALUE_NOK = "REF_STRING_VERIF_NOK";


    // -- WORKER -------------------------------------------------------------------------------------------------------

    public HashMap<String,Object> verify_Frame(String ref_String_ParameterFrame){

        // -- LOG
        System.out.println("FRAME ANALYSED=" + ref_String_ParameterFrame);

        // -- INIT REPONSE
        HashMap<String,Object> ref_HashMap_Response = new HashMap();
        ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_STATUS, REF_STRING_VERIF_VALUE_NOTSET);
        ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_DATA, REF_STRING_VERIF_VALUE_NOTSET);
        ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_EXCEPTION, REF_STRING_VERIF_VALUE_NOTSET);


        // -- INIT MATCH
        Pattern ref_Pattern = Pattern.compile(REF_STRING_REGEX_ID);
        Matcher ref_Matcher = ref_Pattern.matcher((CharSequence) ref_String_ParameterFrame);
        boolean ref_Boolean_IsMatch = ref_Matcher.find();


        // -- CASE REGEX OK
        if(ref_Boolean_IsMatch == Boolean.TRUE){

            // -- Init response
            ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_STATUS, REF_STRING_VERIF_VALUE_OK);

            // -- Init data
            HashMap<String, String> ref_HashMap_Data = new HashMap<>();
            ref_HashMap_Data.put(REF_STRING_CONS_REPONSE_DATA_KEY_ID, ref_Matcher.group(REF_INT_OFFSET_ID));

            ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_DATA, ref_HashMap_Data);



            // -- CASE REGEX NOK
        }else{

            // -- Init response
            ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_STATUS, REF_STRING_VERIF_VALUE_NOK);
            ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_EXCEPTION, new PizzaNotFoundException("Wrong parameter format"));

        }


        // -- Commit
        return ref_HashMap_Response;

    }

}
