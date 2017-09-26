public class Heart {
    private int heartbeat;
    private final int maxHeartbeat;
    private final int minHeartbeat;

    public Heart (int heartbeat, int maxHeartbeat, int minHeartbeat) {
        this.heartbeat = heartbeat;
        this.maxHeartbeat = maxHeartbeat;
        this.minHeartbeat = minHeartbeat;
        System.out.println("Heart is constructed");
    }

    public void boostHeartbeat () {
        int tmp = heartbeat + 10;
        heartbeat = tmp > maxHeartbeat ? maxHeartbeat: tmp;
        System.out.println("After boosting heartbeat, the current heartbeat is" + heartbeat);
    }

    public void decreaseHeartbeat () {
        int tmp = heartbeat - 10;
        heartbeat = tmp < minHeartbeat ? minHeartbeat: tmp;
        System.out.println("After decreasing heartbeat, the current heartbeat is" + heartbeat);
    }

    public int checkHeartbeat() {
        return heartbeat;
    }

    public int getMaxHeartbeat () {
        return maxHeartbeat;
    }

    public int getMinHeartbeat () {
        return minHeartbeat;
    }
}
