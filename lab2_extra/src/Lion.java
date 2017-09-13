public class Lion extends Animal {
    private int speed;

    public Lion (int heartbeat, int maxHeartbeat, int minHeartbeat, int weight, int numOfLegs, int speed) {
        super(heartbeat, maxHeartbeat, minHeartbeat, weight, numOfLegs);
        this.speed = speed;
        System.out.println("The lion is constructed");
    }

    public void move (int time) {
        int distance = time*speed;
        System.out.println("This lion moved " + distance + " meters in " + time + " seconds");
    }

    public void grow (int weight) {
        super.grow(weight);
    }

    public void print() {
        System.out.println("heartbeat: " + checkHeartbeat()
                + "\tmax_heartbeat: " + getMaxHeartbeat()
                + "\tmin_heartbeat: " + getMinHeartbeat()
                + "\tweight: " + getWeight()
                + "\tnum_of_legs: " + getNumOfLegs()
        );
    }
}
