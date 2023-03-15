package Assignment3;

import assignment1.Car;
import assignment1.Truck;
import assignment1.Vehicle;
import assignment2.Inventory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Dealership
{

    private Inventory inventory;

    public Dealership()
    {
        inventory = new Inventory();
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public int getSize()
    {
        return inventory.getSize();
    }


    /**
     * Populate dealership with vehicles using CSV file from a link
     *
     * @param link CSV file with vehicle information
     */
    public void loadInventoryFromWeb(String link)
    {
        try
        {
            URL url = new URL(link);
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            for (line = br.readLine(); line != null; line = br.readLine())
            {
                    if (line.isEmpty())
                    {
                        continue;
                    }
                    inventory.add(createVehicleWithLine(line));
            }
            String str = IOUtils.toString(url.openStream(), "UTF-8");
        }
        catch (MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NullPointerException npe)
        {
            npe.printStackTrace();
        }
        catch (IOException e)
        {
            // exception translation
            throw new IllegalArgumentException("You messed up!~");
        }

    }

    public Vehicle createVehicleWithLine(String line)
    {
        String[] words = line.split(",");
        String vin = words[1];
        String make = words[2];
        String model = words[3];
        int year = Integer.parseInt(words[4]);
        double price = Double.parseDouble(words[5]);
        double mpg = Double.parseDouble(words[6]);
        boolean is4WD = Boolean.parseBoolean(words[7]);
        if (words[0].equals("Car"))
        {
            boolean isConvert = Boolean.parseBoolean(words[8]);
            return new Car(vin, make, model, year, price, mpg, is4WD, isConvert);
        }
        else
        {
            boolean sideStep = Boolean.parseBoolean(words[8]);
            double tow = Double.parseDouble(words[9]);
            return new Truck(vin, make, model, year, price, mpg, is4WD, sideStep, tow);
        }

    }

    public Vehicle getCheapestVehicle()
    {
        return inventory.findCheapestVehicle();
    }

    public Vehicle getMostExpensiveVehicle()
    {
        return inventory.findMostExpensiveVehicle();
    }

    public void storeInventoryToJSONFile(String fileName) throws IOException
    {
        String link = "https://gist.githubusercontent.com/tacksoo/4b67ad84945d36415b62cf35a3fd1b61/raw/199e1caabd5dc04273962e2677c45d5a75f4b279/cars.csv";
        loadInventoryFromWeb(link);
        ObjectMapper mapper = new ObjectMapper();
        Inventory inv = getInventory();
        String json = mapper.writeValueAsString(inv.getVehicles().get(0));
        String json2 = mapper.writeValueAsString(inv.getVehicles().get(1));
        String json3 = mapper.writeValueAsString(inv.getVehicles().get(2));
        FileUtils.writeStringToFile(new File(fileName),json + "\n","UTF-8");
        FileUtils.writeStringToFile(new File(fileName),json2 + "\n","UTF-8", true);
        FileUtils.writeStringToFile(new File(fileName),json3 + "\n","UTF-8", true);
    }

    //load inventory from the web then store it in a json file
    public void loadInventoryFromJSONFile(String fileName) throws IOException
    {
        String link = "https://gist.githubusercontent.com/tacksoo/4b67ad84945d36415b62cf35a3fd1b61/raw/199e1caabd5dc04273962e2677c45d5a75f4b279/cars.csv";
        loadInventoryFromWeb(link);
        ObjectMapper mapper = new ObjectMapper();
        Inventory inv = getInventory();
        String json = mapper.writeValueAsString(inv.getVehicles().get(0));
        String json2 = mapper.writeValueAsString(inv.getVehicles().get(1));
        String json3 = mapper.writeValueAsString(inv.getVehicles().get(2));
        FileUtils.writeStringToFile(new File(fileName),json + "\n","UTF-8");
        FileUtils.writeStringToFile(new File(fileName),json2 + "\n","UTF-8", true);
        FileUtils.writeStringToFile(new File(fileName),json3 + "\n","UTF-8", true);
        List<String> lines = FileUtils.readLines(new File(fileName), "UTF-8");
        inv = new Inventory();

        for (String line : lines)
        {
            inv.add(mapper.readValue(line, Car.class));
        }

    }

}
