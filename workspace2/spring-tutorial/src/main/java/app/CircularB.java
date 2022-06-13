package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class CircularB {
	
	private CircularA circularA;
	
	@Autowired
	public CircularB(CircularA circularA) {
		this.circularA = circularA;
	}

	public void run() {
		circularA.run();
	}
}
