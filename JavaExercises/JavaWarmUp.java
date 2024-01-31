package assn02;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.text.DecimalFormat;

// Here is a starter code that you may optionally use for this assignment.
// TODO: You need to complete these sections

public class JavaWarmUp {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String[] categoriesList = {"phone", "laptop", "smart_watch"};

        int n = s.nextInt();
        // MM/DD/YY, HH:MM, Name, Price, Quantity, Rating, Duration

        // create corresponding size arrays
        String[] dateT = new String[n];
        String[] timeT = new String[n];
        String[] categoryT = new String[n];
        double[] Assembling_fee = new double[n];
        int[] quantityT = new int[n];
        double[] Assembling_Time  = new double[n];
        double[] Energy_and_Device_Cost  = new double[n];

		// TODO: Fill in the above arrays with data entered from the console.
		// Your code starts here:
        for(int i=0; i < n; i++){
            dateT[i] = s.next();
            timeT[i] = s.next();
            categoryT[i] = s.next();
            Assembling_fee[i] = s.nextDouble();
            quantityT[i] = s.nextInt();
            Assembling_Time[i] = s.nextDouble();
            Energy_and_Device_Cost[i] = s.nextDouble();
        }

        // Your code ends here.

        // Find items with highest and lowest price per unit
        int highestItemIndex = getMaxPriceIndex(Assembling_fee);
        int lowestItemIndex = getMinPriceIndex(Assembling_fee);

		// TODO: Print items with highest and lowest price per unit.
		// Your code starts here:
        System.out.println(dateT[highestItemIndex]);
        System.out.println(timeT[highestItemIndex]);
        System.out.println(categoryT[highestItemIndex]);
        System.out.println(Assembling_fee[highestItemIndex]);
        System.out.println(dateT[lowestItemIndex]);
        System.out.println(timeT[lowestItemIndex]);
        System.out.println(categoryT[lowestItemIndex]);
        System.out.println(Assembling_fee[lowestItemIndex]);
        // Your code ends here.

        // Calculate the average price, rating and duration of sales by category.
        // Maintain following category-wise stats in Arrays
        int[] numOfCategoriesC = new int[categoriesList.length];// so numOfCategoriesC[0] = # of categories of type categoriesList[0]
        double[] totPriceC = new double[categoriesList.length]; // total price of each category = sum(price x qty)
        int[] totQuantityC = new int[categoriesList.length];    // total qty of each category = sum (qty)
        double[] totAssembling_TimeC = new double[categoriesList.length]; // total Rating of each category = sum(price x qty)
        double[] totEnergy_and_Device_CostC = new double[categoriesList.length]; // total Duration of each category = sum(price x qty)


		// TODO: set the value of catIndex for each i to be such that categoryT[i] == categoriesList[i].
		// Your code starts here:
        int catIndex = 0;
        for (int i = 0; i < categoriesList.length; i++){
            for (String string : categoryT) {
                if (Objects.equals(string, categoriesList[i])) {
                    numOfCategoriesC[i] += 1;
                }
            }
        }
		// Your code ends here.

		// TODO: Calculate & Print Category-wise Statistics
        DecimalFormat df = new DecimalFormat("00.00");
		// Your code starts here:
        for (int i = 0; i < categoriesList.length; i++){;
            for(int j = 0; j < categoryT.length; j++){
                if (categoriesList[i].equals(categoryT[j])){
                    totQuantityC[i] += quantityT[j];
                    totPriceC[i] += (Assembling_fee[j]*quantityT[j]);
                    totAssembling_TimeC[i] += Assembling_Time[j];
                    totEnergy_and_Device_CostC[i] += Energy_and_Device_Cost[j];
                }
            }
            System.out.println(categoriesList[i]);
            System.out.println(totQuantityC[i]);
            System.out.println( String.format("%.2f", totPriceC[i]/totQuantityC[i]));
            System.out.println(String.format("%.2f", ((totPriceC[i] - (totEnergy_and_Device_CostC[i] + (totAssembling_TimeC[i]*16)))/totQuantityC[i])));
        }
		// Your code ends here.
    }

    // TODO: Find index of item with the highest price per unit.
    static int getMaxPriceIndex(double[] priceT){
		// Your code starts here:
        double max = priceT[0];
        int ind = 0;
        for(int i = 1; i < priceT.length; i++){
            if (priceT[i] > max){
                max = priceT[i];
                ind = i;
            }
        }
        return(ind); // modify this as well
		// Your code ends here.
    }

    // TODO: Find index of item with the lowest price per unit.
    static int getMinPriceIndex(double[] priceT){
		// Your code starts here:
        double min = priceT[0];
        int ind = 0;
        for(int i = 1; i < priceT.length; i++){
            if (priceT[i] < min){
                min = priceT[i];
                ind = i;
            }
        }
        return(ind); // modify this as well
		// Your code ends here.
    }
}
