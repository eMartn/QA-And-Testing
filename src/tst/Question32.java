import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Question32
	{
		public static final String LINK = "https://www.cheapshark.com/api/1.0/deals?storeID=7&upperPrice=15";
		
		@Test
		public void dealRatingTest() throws IOException
			{
			
				ObjectMapper mapper = new ObjectMapper();
				JsonNode node = mapper.readTree(new URL(LINK));
				
				ArrayList<Double> ratings = new ArrayList<>();
				
				for (int i = 0; i < node.size(); i++)
				{
					ratings.add(node.get(i).get("dealRating").asDouble());
				}
				
				for (int i = 0; i < ratings.size() -1; i++)
				{
					Assert.assertTrue(ratings.get(i)  >=  ratings.get(i + 1));
				}
			
			}
	}
