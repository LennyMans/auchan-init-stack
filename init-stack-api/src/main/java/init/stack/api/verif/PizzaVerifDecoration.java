package init.stack.api.verif;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PizzaVerifDecoration {

    // -- REFS
    static String ref_String_Regex_Endpoint_B_Key = "^(flavor|size|spicy)=.*$";
    static String ref_String_Regex_Endpoint_B_Value_Flavor = "^(cheese|meat|vegan|vegetarian)$";
    static String ref_String_Regex_Endpoint_B_Value_Size = "^(m|l|xl)$";
    static String ref_String_Regex_Endpoint_B_Value_Spicy = "^(true|false)$";


    public static void main (String [] arg) {
        // endpoint B /pizzaDecoration
        // -- KEY=flavor-size-spicy  -- verify regex sur la clef -- si ok, on verify valeur, si NOK, retourn reponse pas bon
            // flavor=(fromage-meat-vegan-vegetarian) -- verify regex valeur est ok, si ok ... si pas ok return error
            // size=(m,l,xl) -- verify valeur, si pas ok...
            // spicy (true or false) ... verify valeur ...


        // -- Exemple
        String ref_1 = "flavo=123";
        String ref_2 = "flavor=cheese";
        String ref_3 = "size=l";


        //verify(ref_1);
        //verify(ref_2);
        //verify(ref_3);


        HashMap<String, String> ref_HashMap_Regex_List = new HashMap<>();
        ref_HashMap_Regex_List.put("flavor", ref_String_Regex_Endpoint_B_Value_Flavor);
        ref_HashMap_Regex_List.put("size", ref_String_Regex_Endpoint_B_Value_Size);
        ref_HashMap_Regex_List.put("spicy", ref_String_Regex_Endpoint_B_Value_Spicy);

        String ref_String_refKey = "flavor";

        String ref_String_regexCorres = ref_HashMap_Regex_List.get(ref_String_refKey);

        verify(ref_String_regexCorres);

    }

    public static void verify(String ref_String_ToVerify) {

        Pattern ref_Pattern_Regex_Endpoint_B_Key = Pattern.compile(ref_String_Regex_Endpoint_B_Key);
        Matcher ref_Matcher_Regex_Endpoint_B_Key;

        ref_Matcher_Regex_Endpoint_B_Key = ref_Pattern_Regex_Endpoint_B_Key.matcher(ref_String_ToVerify);


        boolean ref_Boolean_Match = ref_Matcher_Regex_Endpoint_B_Key.find();

        System.out.println("frame analysed=" + ref_String_ToVerify);
        System.out.println("is match=" + ref_Boolean_Match);

        if(ref_Boolean_Match){

            System.out.println("group=" + ref_Matcher_Regex_Endpoint_B_Key.group(1));

        }

    }

}
