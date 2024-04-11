public class OneLaneBridge extends Bridge {
    private int bridgeLimit;

    public OneLaneBridge(int bridgeLimit) {
        super();  
        this.bridgeLimit = bridgeLimit;
    }

    /**
     * a car arrives at the bridge.
     */
    @Override
    public synchronized void arrive(Car car) throws InterruptedException {
        // Check if the car can enter the bridge
        while (bridge.size() >= bridgeLimit || direction != car.getDirection()) {
            wait(); // Wait if the bridge is full or if the direction is different
        }

        // If the bridge is empty, allow the car to set the direction
        if (bridge.isEmpty()) {
            direction = car.getDirection();
        }
        
        car.setEntryTime(currentTime);
        bridge.add(car);
        System.out.println("Bridge (dir=" + direction + "): " + bridge);

        currentTime++;
    }

    /**
     * a car exits the bridge.
     */
    @Override
    public synchronized void exit(Car car) throws InterruptedException {
        // Check if it's the car's turn to exit
        while (bridge.indexOf(car) != 0) {
            wait(); // Wait if it's not the first car on the list
        }

        // Remove the car from the bridge
        bridge.remove(car);
        System.out.println("Bridge (dir=" + direction + "): " + bridge);
        
        if (bridge.isEmpty()) {
            direction = !direction;
        }
        notifyAll();
    }
}