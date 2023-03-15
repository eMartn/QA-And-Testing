import assignment1.Truck;
import assignment1.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MyFirstTest
{
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testWeather() throws IOException
    {
        String str = "https://api.darksky.net/forecast/3c5084c558861c1610447b49a45f4eb4/34.0773898,-83.898985";
        String json = IOUtils.toString(new URL(str).openStream(), "UTF-8");
        System.out.println(json);
        // get temperature
        // from jackson databind library
        JsonNode node = mapper.readTree(json);
        String temp = node.get("currently").get("temperature").asText();
        System.out.println(temp);
        String summary = node.get("daily").get("summary").asText();
        System.out.println(summary);
    }

    @Test
    public void testSerialize() throws JsonProcessingException, IOException
    {
        Vehicle myTruck = new Truck("BUNI", "Chevrolet", "Silverado", 2020, 50000, 7,
                                   false, true, 5);
        String json = mapper.writeValueAsString(myTruck);
        mapper.writeValue(new File("myTruck.json"), myTruck); //serialize to a file
        System.out.println(json);
    }

    @Test
    public void testDeserialize() throws IOException
    {
        String json = FileUtils.readFileToString(new File("myTruck.json"), "UTF-8");
        Truck v = mapper.readValue(json, Truck.class);
        System.out.println(v);
    }
}
