package uet.oop.bomberman.entities;

public abstract class ThreadAnimated extends All_Entity {
	protected int _animate = 0;
	protected final int MAX_ANIMATE =9999;
	
	protected void animate() {
		if(_animate < MAX_ANIMATE)
			_animate++;
		else
			_animate = 0;
	}
}
