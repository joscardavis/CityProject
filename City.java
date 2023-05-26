public class City {
    // name of city
    private final String call;
    // % asthmatics
    private final double asthma;
    // % smokers
    private final double smoke;
    // percentile of this city by smokers
    private double smokePer;
    // percentile of this city by asthmatics
    private double asthmaPer;

    /* Explicit and only constructor method.
     * @param asthma the percent of asthmatics in the city
     * @param smokers the percent of smokers in the city
     * @param name the name of the city
     */
    public City(String name, double asthmatics, double smokers) {
        this.call = name;
        this.asthma = asthmatics;
        this.smoke = smokers;
    }
    public String getName(){
      return call;
    }
    /* An accessor method to retrieve the percentage of smokers.
     * @return the percentage of smokers in the city
     */
    public double getSmokers() {
        return smoke;
    }

    /* An accessor method to retrieve the percentage of asthmatics.
     * @return the percentage of asthmatics in the city
     */
    public double getAsthmatics() {
        return asthma;
    }

    /* Sets the percentile of smokers for this city.
     * @param percentile the percentile to be set
     */
    public void setSmokePer(double percentile) {
        smokePer = percentile;
    }

    /* Sets the percentile of asthmatics for this city.
     * @param percentile the percentile to be set
     */
    public void setAsthmaPer(double percentile) {
        asthmaPer = percentile;
    }

    /* An accessor method to retrieve the percentile of asthmatics.
     * @return the percentile of asthmatics in the city
     */
    public double getAsthmaPer() {
        return asthmaPer;
    }

    /* An accessor method to retrieve the percentile of smokers.
     * @return the percentile of smokers in the city
     */
    public double getSmokePer() {
        return smokePer;
    }
}
