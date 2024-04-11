/**
 * Runs all threads
 */

 public class BridgeRunner {

	public static void main(String[] args) {

		// TODO - check command line inputs
		if (args.length !=2){
			System.out.println("Usage: javac BridgeRunner <bridge limit> <num cars>");
			return;
		}

		int bridgeLimit = Integer.parseInt(args[0]);
		int numCars =Integer.parseInt(args[1]);


		// check if the number is positive
		if (bridgeLimit <=0 || numCars <=0){
			System.out.println("Error: bridge limit and/or num cars must be positive.");
			return;
		}


		// TODO - instantiate the bridge
		OneLaneBridge bridge = new OneLaneBridge(bridgeLimit);

		// TODO - allocate space for threads
		Thread[] carThreads = new Thread[numCars];


		// TODO - start then join the threads
		for (int i =0; i < numCars; i++){
			Car car = new Car(i,bridge);
			carThreads[i] =new Thread(car);
			carThreads[i].start();
		}

		for (int i = 0; i < numCars; i++) {
			try {
                carThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

		System.out.println("All cars have crossed!!");

	}

}