import java.util.Random;

public class MoveSet {
	public Move[] moves;
	
	public MoveSet() {
		this.moves = new Move[20];
		moves[0] = new Move("Tickle", 0, 50, 0);
		moves[1] = new Move("Scratch", 0, 60, 0);
		moves[2] = new Move("Claw", 0, 70, 0);
		moves[3] = new Move("Slap", 0, 80, 0);
		moves[4] = new Move("Punch", 0, 90, 0);
		moves[5] = new Move("Kick", 0, 100, 0);
		moves[6] = new Move("Karate Chop", 0, 60, 4);
		moves[7] = new Move("Backhand", 0, 70, 4);
		moves[8] = new Move("Close Combat", 0, 80, 4);
		moves[9] = new Move("Uppercut", 0, 100, 4);
		moves[10] = new Move("Acid", 0, 60, 7);
		moves[11] = new Move("Sludge", 0, 70, 7);
		moves[12] = new Move("Poison Bomb", 0, 80, 7);
		moves[13] = new Move("Hyper Beam", 0, 110, 0);
		moves[14] = new Move("Rage", 1, 1.25, 0);
		moves[15] = new Move("Withdraw", 2, 1.25, 0);
		moves[16] = new Move("Growl", 3, 0.8, 0);
		moves[17] = new Move("Tail Whip", 4, 0.8, 0);
		moves[18] = new Move("Cloak", 5, 1, 0);
		moves[19] = new Move("Sand-Attack", 6, 1, 6);
	}
	
	public Move[] generate(Move[] moveset) {
		Random random = new Random(System.nanoTime());
		
		Move[] modify = new Move[4];
		for (int i = 0; i < 4; i++) {
			modify[i] = moveset[i];
		}
		double r1 = random.nextDouble();
		if (r1 < 0.25) {
			int move = (int) (4 * random.nextDouble());
			modify[move] = randomMove(modify);
		}
		return modify;
	}
	
	public Move[] generate() {
		int r1 = (int) (Math.random() * moves.length);
		int r2 = (int) (Math.random() * moves.length);
		while (r2 == r1) {
			r2 = (int) (Math.random() * moves.length);
		}
		int r3 = (int) (Math.random() * moves.length);
		while (r3 == r1 || r3 == r2) {
			r3 =  (int) (Math.random() * moves.length);
		}
		int r4 = (int) (Math.random() * moves.length);
		while (r4 == r1 || r4 == r2 || r4 == r3) {
			r4 = (int) (Math.random() * moves.length);
		}
		Move[] moveSet = {moves[r1], moves[r2], moves[r3], moves[r4]};
		return fix(moveSet);
	}
	
	public Move randomMove(Move[] moveSet) {
		int random = (int) (Math.random() * moves.length);
		while (moves[random].name.equals(moveSet[0].name) || moves[random].name.equals(moveSet[1].name) ||
				moves[random].name.equals(moveSet[2].name) || moves[random].name.equals(moveSet[3].name)) {
					random = (int) (Math.random() * moves.length);
				}
		return moves[random];
	}
	
	public Move[] fix(Move[] moveSet) {
		while (moveSet[0].action != 0 && moveSet[1].action != 0 
				&& moveSet[2].action != 0 && moveSet[3].action != 0) {
			moveSet[0] = randomMove(moveSet);
		}
		return moveSet;
	}
}
