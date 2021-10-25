package parser;

import org.json.JSONException;
import org.json.JSONObject;

import model.User;
import model.VolunteerUser;

public class ParserLog {



    public User logPars(String gson) throws JSONException {
        JSONObject jsobject = new JSONObject(gson);
        VolunteerUser volunteerUser = null;
        String Tipo =  jsobject.getString("Tipo");
        String email = jsobject.getString("email");
        String nome = jsobject.getString("nome");
        String latitudine = jsobject.getString("Latitudine");
        String longitudine = jsobject.getString("Longitudine");
        String cognome = jsobject.getString("Cognome");
        int id = jsobject.getInt("ID");
        System.out.println(nome + "\n" + latitudine + "\n" + longitudine + "\n" + Tipo + "\n");

        switch (Tipo){
            case "Volontario":
                volunteerUser = new VolunteerUser(id,email ,nome,cognome,Tipo,latitudine,longitudine);
               break;

            default:
                break;


        }
        return volunteerUser;
    }

}
