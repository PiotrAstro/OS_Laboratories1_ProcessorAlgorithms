package MathForProject;

import java.util.Random;

public class MathForProject {
    public static double getRandomWithStandardDistribution(double min, double mostAtPoint, double max, double whatRangefor70Percent) {
        return getRandomWithStandardDistribution(min, mostAtPoint, max, whatRangefor70Percent, new Random());
    }

    public static double getRandomWithStandardDistribution(double min, double mostAtPoint, double max, double whatRangefor70Percent, Random random) {
        double result = sigmoidInverseFunction(random.nextDouble());
        result = result * (whatRangefor70Percent / 4);

        result += mostAtPoint;
        if(result > max) {
            result = max;
        } else if(result < min) {
            result = min;
        }

        return result;
    }

    public static double sigmoidInverseFunction(double x) {
        if(x <= 0 || x >= 1) {
            return 0;
        }
        else {
            return -Math.log(1/x - 1);
        }
    }
}
