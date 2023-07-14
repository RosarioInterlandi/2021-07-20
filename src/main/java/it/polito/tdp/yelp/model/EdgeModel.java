package it.polito.tdp.yelp.model;

public class EdgeModel {
	private User u1;
	private User u2;
	private int peso;
	public EdgeModel(User u1, User u2, int peso) {
		super();
		this.u1 = u1;
		this.u2 = u2;
		this.peso = peso;
	}
	public User getU1() {
		return u1;
	}
	public User getU2() {
		return u2;
	}
	public int getPeso() {
		return peso;
	}
	
	
}
