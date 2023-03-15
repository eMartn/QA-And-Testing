import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class BMITest
    {

        @Test
       // @Parameters({"0,2,0", "100, 2, 25.0", "200, 2, 50.0", "400, 2, 100.0"})
        //@FileParameters("bmi.txt")
        @Parameters(method = "paramsForBMI")
        public void testBMI(double mass, double height, double bmi)
        {
            Assert.assertEquals(bmi, getBMI(mass, height),0.1);

        }

        private Object[] paramsForBMI()
        {
            return new Object[] {
                    new Object[] {0,2,0},
                    new Object[] {100,2,25.0},
                    new Object[] {200,2,50.0}
            };
        }

        /**
         * Method that calculates BMI
         * @param mass this needs to be in kilograms.
         * @param height this needs to be in meters.
         * @return some value greater than 0.
         */
        public double getBMI(double mass, double height)
        {
            return mass / Math.pow(height, 2);
        }
    }
