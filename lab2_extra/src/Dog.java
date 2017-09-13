public class Dog extends Heart{
    private int speed;
    private int weight;

    public Dog (int heartbeat, int maxHeartbeat, int minHeartbeat, int speed, int weight) {
        super (heartbeat, maxHeartbeat, minHeartbeat);
        this.speed = speed;
        this.weight = weight;
        System.out.println("Dog is constructed");
    }

    public void move (int time) {
        int distance = time*speed;
        System.out.println("This dog moved " + distance + " meters in " + time + " seconds");
    }

    public void print(){
        printHeartInfo();
        System.out.println("\tweight: " + weight + "\tspeed: " + speed);
    }

    private void printHeartInfo(){
        System.out.println("heartbeat: " + checkHeartbeat()
                + "\tmax_heartbeat: " + getMaxHeartbeat()
                + "\tmin_heartbeat: " + getMinHeartbeat()
        );
    }

    public int checkHeartbeat () {
        return super.checkHeartbeat();
    }

    public int getMaxHeartbeat () {
        return super.getMaxHeartbeat();
    }

    public int getMinHeartbeat () {
        return super.getMinHeartbeat();
    }

    public int getSpeed () {
        return speed;
    }

    public int getWeight () {
        return weight;
    }

    public void boostHeartbeat () {
        super.boostHeartbeat();
    }

    public void decreaseHeartbeat() {
        super.decreaseHeartbeat();
    }
}
