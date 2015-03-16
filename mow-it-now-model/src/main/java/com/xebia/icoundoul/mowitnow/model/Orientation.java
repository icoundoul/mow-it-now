package com.xebia.icoundoul.mowitnow.model;

/**
 * An orientation defined with a cardinal point.
 */
public enum Orientation {

	North("N"),
	South("S"),
	East("E"),
	West("W");

	private String orientationCode;

	private Orientation(String orientationCode) {
		this.orientationCode = orientationCode;
	}

	public String getOrientationCode() {
		return orientationCode;
	}

	public static Orientation getOrientationByCode(String code) {
		for (Orientation orientataion : Orientation.values()) {
			if (orientataion.getOrientationCode().equals(code)) {
				return orientataion;
			}
		}
		throw new IllegalArgumentException("the given code doesn't match any orientation:" + code);
	}
}
