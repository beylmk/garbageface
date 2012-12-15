package follow.tutorial.nativeapp.model;

import follow.tutorial.nativeapp.model.components.mathVector;

public interface MoverStrategy {
	void move(Droid a);
}

class Attract implements MoverStrategy {
	public void move(Droid a) {
		a.setVelocity(a.getTouchPosition().minus(a.getPosition()));
		a.setVelocity(a.getVelocity().scalarMult(.03));
		a.setPosition(a.getPosition().plus(a.getVelocity()));
	}
}

class Repulse implements MoverStrategy {
	public void move(Droid a) {
		a.setVelocity(a.getPosition().minus(a.getTouchPosition()));
		a.setVelocity(a.getVelocity().scalarMult(.03));
		a.setPosition(a.getPosition().plus(a.getVelocity()));
	}
}

class Teleport implements MoverStrategy {
	public void move(Droid a) {
		a.setPosition(a.getTouchPosition());
	}
}

class Orbital implements MoverStrategy {
	public void move (Droid a) {
		double scaling = .00000000000002;
		mathVector direction = a.getTouchPosition().minus(a.getPosition());
		direction.scalarMult(scaling);

		a.setVelocity(a.getVelocity().plus(direction));
		a.limitSpeed();
		a.setPosition(a.getPosition().plus(a.getVelocity()));
	}
}