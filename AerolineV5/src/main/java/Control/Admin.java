/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Logic.Plane;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer.Vanilla.std;

/**
 *
 * @author anton
 */
public class Admin {
    
    void addNewPlane(String std) throws JsonProcessingException {
        
      //Creating the ObjectMapper object
      ObjectMapper mapper = new ObjectMapper();
      //Converting the Object to JSONString
      String jsonString = mapper.writeValueAsString(std);
      System.out.println(jsonString);
    }
    
    String listPlanes(Plane plane){
        return null;
    }
    
}
