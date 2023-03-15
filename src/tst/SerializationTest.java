import assignment1.Vehicle;
import assignment5.Game;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.Validate;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SerializationTest
{
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testVehicleSerialization() throws IOException
    {
        Vehicle v = new Vehicle("12345", "Hyundai", "Sonata", 2020, 20000, 20, true);
        byte[] data = SerializationUtils.serialize(v);
        FileUtils.writeByteArrayToFile(new File("hyundai.ser"), data);
        
    }

    @Test
    public void testVehicleDeserialization() throws IOException
    {
        byte[] data = FileUtils.readFileToByteArray(new File("hyundai.ser"));
        Vehicle hyundai = SerializationUtils.deserialize(data);
        Assert.assertEquals("Hyundai", hyundai.getMake());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidate()
    {
        Validate.isTrue(false);
    }

    @Test
    public void testJSONSerialization() throws JsonProcessingException, IOException
    {
        Game blood = new Game("Blood", "FPS", 10, 50);
        String json = mapper.writeValueAsString(blood);
        mapper.writeValue(new File("blood.json"), blood);
        System.out.println(json);
        File bloodFile = new File("blood.json");
        Assert.assertTrue(bloodFile.exists());
        Game g = mapper.readValue(json, Game.class);
        Assert.assertEquals("Blood", g.getName());
        Assert.assertEquals("FPS", g.getGenre());
        Assert.assertEquals(10.0, g.getRating(), 0.1);
        Assert.assertEquals(50, g.getPrice(), 0.1);
    }

    @Test
    public void testArraySerialization() throws IOException
    {
        Game[] games = new Game[3];
        Game blood = new Game("Blood", "FPS", 10, 50);
        Game duke3d = new Game("Duke Nukem 3d", "FPS", 10, 50);
        Game bloodStained = new Game("BloodStained", "Action", 10, 50);
        games[0] = blood;
        games[1] = duke3d;
        games[2] = bloodStained;
        mapper.writeValue(new File("drimsgames.json"), games);
        Game[] drims = mapper.readValue(new File("drimsgames.json"), Game[].class);
        System.out.println(Arrays.toString(drims));
        Assert.assertEquals(3, drims.length);
    }
}
