package models.useful;

public class Pair<P1, P2> {

	P1 val1;
	P2 val2;

	public Pair<P1, P2> getValue() {
		return this;
	}
	
	public P1 getVal1() {
		return this.val1;
	}
	
	public P2 getVal2() {
		return this.val2;
	}
	
	public Pair<P1, P2> setPair(P1 val1, P2 val2){
//		Pair<P1, P2> p = new Pair<P1, P2>();
		this.val1 = val1;
		this.val2 = val2;
		return this;
	}
	

	@Override
	public String toString() {
		return "Pair [val1=" + val1 + ", val2=" + val2.toString() + "]";
	}
}
