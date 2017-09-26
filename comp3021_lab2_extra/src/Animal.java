public class Animal{
    private int weight;
    private int numOfLegs;
    private Heart heart;

    public Animal (int heartbeat, int maxHeartbeat, int minHeartbeat, int weight, int numOfLegs) {
        this.heart = new Heart(heartbeat, maxHeartbeat, minHeartbeat);
        this.weight = weight;
        this.numOfLegs = numOfLegs;
        System.out.println("Animal is constructed");
    }

    public void grow (int weight) {
        this.weight += weight;
        System.out.println("This animal growed for " + weight + " kg and its weight now is " + this.weight + " kg");
    }

    public void print() {
        System.out.println("heartbeat: " + checkHeartbeat()
                + "\tmax_heartbeat: " + getMaxHeartbeat()
                + "\tmin_heartbeat: " + getMinHeartbeat()
                + "\tweight: " + weight
                + "\tnum_of_legs: " + numOfLegs
        );
    }

    //Getters
    public int checkHeartbeat () {
        return heart.checkHeartbeat();
    }

    public int getMaxHeartbeat () {
        return heart.getMaxHeartbeat();
    }

    public int getMinHeartbeat () {
        return heart.getMinHeartbeat();
    }

    public int getWeight() {
        return weight;
    }

    public int getNumOfLegs() {
        return numOfLegs;
    }

}
