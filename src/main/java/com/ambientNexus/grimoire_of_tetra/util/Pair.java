package com.ambientNexus.grimoire_of_tetra.util;


import java.util.Objects;

public class Pair<F, S> {
	F first;
	S second;

	protected Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public static <F, S> Pair<F, S> of(F first, S second) {
		return new Pair<>(first, second);
	}

	public F getFirst() {
		return this.first;
	}

	public S getSecond() {
		return this.second;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Pair<?, ?> other)) {
			return false;
		} else {
			return Objects.equals(this.first, other.first) && Objects.equals(this.second, other.second);
		}
	}

	public int hashCode() {
		return this.nullHash(this.first) * 31 ^ this.nullHash(this.second);
	}

	int nullHash(Object o) {
		return o == null ? 0 : o.hashCode();
	}

	public String toString() {
		return "(" + this.first + ", " + this.second + ")";
	}

}
