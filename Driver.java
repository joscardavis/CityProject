  /*  33113
   *  Solves each problem with a bunch of sorting, percentiles, and math
   */

  import java.io.File;
  import java.io.FileNotFoundException;
  import java.util.Scanner;
  import java.util.Arrays;
  import java.util.Comparator;

  public class Driver {
      public static void main(String[] args) {
          System.out.println(problemone(importData()));

          overview(problemtwo(importData()));
          graph(problemtwo(importData()));
          System.out.println();
          System.out.println("x = percentile smoking, y = percentile asthmatics");
      }

      public static void overview(double[] thingy) {
          int x = 0;
          for (double w : thingy) {
              System.out.println(x + "-" + (x + 1) + " Percentile smoking averages " + w + " percentile in asthmatics");
              x++;
          }

      }

      public static void graph(double[] thingy) {
          for (int i = 100; i >= 0; i -= 2) {
              System.out.print(i + "\t");
              for (double w : thingy) {
                  if (w < i && w > i - 2) {
                      System.out.print("x");
                  } else {
                      System.out.print(" ");
                  }
              }
              System.out.println();
          }
          System.out.print("\t");
          System.out.print("0    5    ");
          for (int i = 10; i <= 100; i += 5) {
              System.out.print(i + "   ");
          }
      }

      public static City[] importData() {
          City[] cities = new City[0];

          try {
              File file = new File("G:\\My Drive\\Comp Sci\\AP\\HealthyCities.csv");
              Scanner scanner = new Scanner(file);

              int lineCount = 0;
              while (scanner.hasNextLine()) {
                  String line = scanner.nextLine();

                  if (lineCount == 0) {
                      lineCount++;
                      continue;
                  }

                  String[] fields = line.split(",");

                  String name = fields[1];
                  double asthma = Double.parseDouble(fields[5]);
                  double smoke = Double.parseDouble(fields[6]);

                  City city = new City(name, asthma, smoke);

                  cities = Arrays.copyOf(cities, cities.length + 1);
                  cities[cities.length - 1] = city;

                  lineCount++;
              }

              scanner.close();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
          return cities;
      }


      /*  Sorts the array by the percentage of smokers, identifying the city with the highest percent
       *  @returns the name of the most smoker-filled city
       */
      public static String problemone(City[] arr) {
          Arrays.sort(arr, Comparator.comparingDouble(City::getSmokers).reversed());
          City mostSmokerFilledCity = arr[0];
          return mostSmokerFilledCity.getName();
      }

      /*  Sorts the array by the percentage of smokers, and then gives the city objects smoker percentiles based on this.
       *  Then sorts the array by percentage of asthmatics, and applies asthma percentiles
       *  determines the average difference between percentile smokers/percentile asthmatics for every 5%
       *  example: 0-5% percentile smokers: average of 30th percentile in asthmatics, 5-10% percentile smokers: average of 33 percentile in asthmatics, etc.
       *  @returns a city array with every smoking percentile's average asthma percentile
       */
      public static double[] problemtwo(City[] arr) {
          Arrays.sort(arr, Comparator.comparingDouble(City::getSmokers).reversed());
          for (int i = 0; i < arr.length; i++) {
              double percentile = (double) i / arr.length * 100;
              arr[i].setSmokePer(percentile);
          }

          Arrays.sort(arr, Comparator.comparingDouble(City::getAsthmatics).reversed());
          for (int i = 0; i < arr.length; i++) {
              double percentile = (double) i / arr.length * 100;
              arr[i].setAsthmaPer(percentile);
          }

          double[] smoke = new double[100];
          for (int i = 0; i < 100; i++) {
              double endPercentile = i + 1;
              double averageDifference = calculateAverageDifference(arr, i, endPercentile);
              smoke[i] = averageDifference;
          }

          return smoke;
      }

      private static double calculateAverageDifference(City[] arr, double startPercentile, double endPercentile) {
          int count = 0;
          double sumDifference = 0;

          for (City city : arr) {
              double smokerPercentile = city.getSmokePer();
              double asthmaPercentile = city.getAsthmaPer();

              if (smokerPercentile >= startPercentile) {
                  if (smokerPercentile < endPercentile) {
                      count++;
                      sumDifference += asthmaPercentile;
                  }
              }
          }
          return count > 0 ? sumDifference / count : 0;
      }

  }
