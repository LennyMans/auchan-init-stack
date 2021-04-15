package init.stack.api.verif;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PizzaVerifDecoration {

    // -- CONS
    public static final int REF_INT_OFFSET_ID = 1;
    public static final int REF_INT_OFFSET_FLAVOR = 2;
    public static final int REF_INT_OFFSET_SIZE = 3;
    public static final int REF_INT_OFFSET_SPICY = 4;

    public static final String REF_STRING_REGEX_DECORATEPIZZA = "^id=([0-9]{1,})&flavor=(cheese|meat|vegan|vegetarian)&size=(l|m|xl)&spicy=(true|false)$";


    // -- CONS REPONSE KEY
    public static String REF_STRING_CONS_REPONSE_KEY_STATUS = "REF_STRING_CONS_REPONSE_KEY_STATUS";
    public static String REF_STRING_CONS_REPONSE_KEY_DATA = "REF_STRING_CONS_REPONSE_KEY_DATA";
    public static String REF_STRING_CONS_REPONSE_DATA_KEY_ID = "REF_STRING_CONS_REPONSE_KEY_ID";
    public static String REF_STRING_CONS_REPONSE_DATA_KEY_FLAVOR = "REF_STRING_CONS_REPONSE_KEY_FLAVOR";
    public static String REF_STRING_CONS_REPONSE_DATA_KEY_SIZE = "REF_STRING_CONS_REPONSE_KEY_SIZE";
    public static String REF_STRING_CONS_REPONSE_DATA_KEY_SPICY = "REF_STRING_CONS_REPONSE_KEY_SPICY";

    public static String REF_STRING_CONS_REPONSE_KEY_EXCEPTION = "REF_STRING_CONS_REPONSE_KEY_EXCEPTION";


    // -- CONS REPONSE VALUE
    public static String REF_STRING_VERIF_VALUE_NOTSET = "REF_STRING_VERIF_VALUE_NOTSET";
    public static String REF_STRING_VERIF_VALUE_OK = "REF_STRING_VERIF_OK";
    public static String REF_STRING_VERIF_VALUE_NOK = "REF_STRING_VERIF_NOK";



    // -- ENTRY POINT --------------------------------------------------------------------------------------------------

    public static void main (String [] ref_Array_String_Arg){

        // -- Ini test
        String ref_String_A = "id=1234&flavor=vegetarian&size=l&spicy=true";

        // -- Instance
        PizzaVerifDecoration ref_PizzaVerifDecoration = new PizzaVerifDecoration();
        ref_PizzaVerifDecoration.execute(ref_String_A);

    }



    // -- WORKER -------------------------------------------------------------------------------------------------------

    public void execute(String ref_String_FrameToCheck){

        // -- Execute
        HashMap<String,Object> ref_HashMap_Executed = this.verify_Frame(ref_String_FrameToCheck);

        // -- Check
        if(((String)ref_HashMap_Executed.get(REF_STRING_CONS_REPONSE_KEY_STATUS)).equals(REF_STRING_VERIF_VALUE_OK)){

            // -- Retrieve data
            HashMap<String,String> ref_HashMap_Data =(HashMap<String,String>) ref_HashMap_Executed.get(REF_STRING_CONS_REPONSE_KEY_DATA);

            // -- Extract
            String ref_String_Id = ref_HashMap_Data.get(REF_STRING_CONS_REPONSE_DATA_KEY_ID);
            String ref_String_Flavor = ref_HashMap_Data.get(REF_STRING_CONS_REPONSE_DATA_KEY_FLAVOR);
            String ref_String_Size = ref_HashMap_Data.get(REF_STRING_CONS_REPONSE_DATA_KEY_SIZE);
            String ref_String_Spicy = ref_HashMap_Data.get(REF_STRING_CONS_REPONSE_DATA_KEY_SPICY);

            // -- Log
            System.out.println("ref_String_Id=" + ref_String_Id);
            System.out.println("ref_String_Flavor=" + ref_String_Flavor);
            System.out.println("ref_String_Size=" + ref_String_Size);
            System.out.println("ref_String_Spicy=" + ref_String_Spicy);


            // PERSIT EN DB ET RENVOIT REPONSE


        }else{

            // RENVOIT REPONSE


            // -- Retrieve exception
            Exception ref_Exception = (Exception) ref_HashMap_Executed.get(REF_STRING_CONS_REPONSE_KEY_EXCEPTION);


            System.out.println(ref_Exception.getMessage());

        }



    }

    private HashMap<String,Object> verify_Frame(String ref_String_ParameterFrame){

        // -- LOG
        System.out.println("FRAME ANALYSED=" + ref_String_ParameterFrame);

        // -- INIT REPONSE
        HashMap<String,Object> ref_HashMap_Response = new HashMap();
        ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_STATUS, REF_STRING_VERIF_VALUE_NOTSET);
        ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_DATA, REF_STRING_VERIF_VALUE_NOTSET);
        ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_EXCEPTION, REF_STRING_VERIF_VALUE_NOTSET);


        // -- INIT MATCH
        Pattern ref_Pattern = Pattern.compile(REF_STRING_REGEX_DECORATEPIZZA);
        Matcher ref_Matcher = ref_Pattern.matcher(ref_String_ParameterFrame);
        boolean ref_Boolean_IsMatch = ref_Matcher.find();


        // -- CASE REGEX OK
        if(ref_Boolean_IsMatch == Boolean.TRUE){

            // -- Init response
            ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_STATUS, REF_STRING_VERIF_VALUE_OK);

            // -- Init data
            HashMap<String, String> ref_HashMap_Data = new HashMap<>();
            ref_HashMap_Data.put(REF_STRING_CONS_REPONSE_DATA_KEY_ID, ref_Matcher.group(REF_INT_OFFSET_ID));
            ref_HashMap_Data.put(REF_STRING_CONS_REPONSE_DATA_KEY_FLAVOR, ref_Matcher.group(REF_INT_OFFSET_FLAVOR));
            ref_HashMap_Data.put(REF_STRING_CONS_REPONSE_DATA_KEY_SIZE, ref_Matcher.group(REF_INT_OFFSET_SIZE));
            ref_HashMap_Data.put(REF_STRING_CONS_REPONSE_DATA_KEY_SPICY, ref_Matcher.group(REF_INT_OFFSET_SPICY));

            ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_DATA, ref_HashMap_Data);



            // -- CASE REGEX NOK
        }else{

            // -- Init response
            ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_STATUS, REF_STRING_VERIF_VALUE_NOK);
            ref_HashMap_Response.put(REF_STRING_CONS_REPONSE_KEY_EXCEPTION, new Exception());

        }


        // -- Commit
        return ref_HashMap_Response;

    }

}
