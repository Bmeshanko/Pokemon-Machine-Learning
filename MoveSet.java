public class MoveSet {
	public Move[] moves;
	
	public MoveSet() {
		this.moves = new Move[20];
		moves[0] = new Move("Tickle", 0, 50);
		moves[1] = new Move("Scratch", 0, 60);
		moves[2] = new Move("Claw", 0, 70);
		moves[3] = new Move("Slap", 0, 80);
		moves[4] = new Move("Punch", 0, 90);
		moves[5] = new Move("Kick", 0, 100);
		moves[6] = new Move("Karate Chop", 0, 60);
		moves[7] = new Move("Backhand", 0, 70);
		moves[8] = new Move("Close Combat", 0, 80);
		moves[9] = new Move("Uppercut", 0, 100);
		moves[10] = new Move("Acid", 0, 60);
		moves[11] = new Move("Sludge", 0, 70);
		moves[12] = new Move("Poison Bomb", 0, 80);
		moves[13] = new Move("Hyper Bean", 0, 150);
		moves[14] = new Move("Rage", 1, 100);
		moves[15] = new Move("Withdraw", 2, 100);
		moves[16] = new Move("Growl", 3, 100);
		moves[17] = new Move("Tail Whip", 4, 100);
		moves[18] = new Move("Cloak", 5, 100);
		moves[19] = new Move("Sand-Attack", 6, 100);
	}
	
	public Move[] generate(Move[] moveset) {
		double random = Math.random();
		if (random < 0.25) {
			int move = (int) (4 * Math.random());
			moveset[move] = randomMove(moveset);
		}
		return moveset;
	}
	
	public Move[] generate() {
		int r1 = (int) (Math.random() * moves.length);
		int r2 = (int) (Math.random() * moves.length);
		while (r2 != r1) {
			r2 = (int) (Math.random() * moves.length);
		}
		int r3 = (int) (Math.random() * moves.length);
		while (r3 != r1 && r3 != r2) {
			r3 =  (int) (Math.random() * moves.length);
		}
		int r4 = (int) (Math.random() * moves.length);
		while (r4 != r1 && r4 != r2 && r4 != r3) {
			r4 = (int) (Math.random() * moves.length);
		}
		Move[] moveSet = {moves[r1], moves[r2], moves[r3], moves[r4]};
		return moveSet;
	}
	
	public Move randomMove(Move[] moveset) {
		int random = (int) (Math.random() * moves.length);
		while (moves[random].equals(moveset[0]) || moves[random].equals(moveset[1]) ||
				moves[random].equals(moveset[2]) || moves[random].equals(moveset[3])) {
					random = (int) (Math.random() * moves.length);
				}
		return moves[random];
	}
}
