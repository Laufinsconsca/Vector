package theComputationOfTheRootsOfAnyEquation;

import java.math.BigDecimal;

public class Calculation {
	
	public int right_β; // верная итерация с β, нужна для отображения решения
	
	private String[] roots = new String[4];
	private ComplexBigDecimal[] coefficients = new ComplexBigDecimal[5];
	private ComplexBigDecimal[] output = new ComplexBigDecimal[4];
	
	public ComplexBigDecimal D = new ComplexBigDecimal();
	public ComplexBigDecimal α = new ComplexBigDecimal();
	public ComplexBigDecimal[] β = new ComplexBigDecimal[3];
	public ComplexBigDecimal γ = new ComplexBigDecimal();
	public ComplexBigDecimal p = new ComplexBigDecimal();
	public ComplexBigDecimal q = new ComplexBigDecimal();
	public ComplexBigDecimal Q = new ComplexBigDecimal();
	public ComplexBigDecimal R = new ComplexBigDecimal();
	public ComplexBigDecimal U = new ComplexBigDecimal();
	public ComplexBigDecimal[] Y = new ComplexBigDecimal[3];
	public ComplexBigDecimal W = new ComplexBigDecimal();
	
	final BigDecimal TWO = BigDecimal.valueOf(2);
	final BigDecimal THREE = BigDecimal.valueOf(3);
	final BigDecimal FOUR = BigDecimal.valueOf(4);
	final BigDecimal FIVE = BigDecimal.valueOf(5);
	final BigDecimal SIX = BigDecimal.valueOf(6);
	final BigDecimal EIGHT = BigDecimal.valueOf(8);
	final BigDecimal NINE = BigDecimal.valueOf(9);
	final BigDecimal TWELVE = BigDecimal.valueOf(12);
	final BigDecimal SIXTEEN = BigDecimal.valueOf(16);
	final BigDecimal TWENTY_SEVEN = BigDecimal.valueOf(27);
	final BigDecimal ONE_HUNDRED_EIGHT = BigDecimal.valueOf(108);
	final BigDecimal TWO_HUNDRED_FIFTY_SIX = BigDecimal.valueOf(256);
	
	public Calculation() {}	
	
	public Calculation(String... coefficientsString) { 
	for (int i = 0; i < 5; i++) {
	coefficients[i] = new ComplexBigDecimal(coefficientsString[i]);
	}
	calculation();
	}
	
	public Calculation setOutputValuesIfCalculationTrue() {
		for (int i = 0; i < 4; i++) {
			if (output[i] != null) {
			roots[i] = output[i].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());	
			}
		}
		return this;
	}
	
	public void calculation() {
		if (coefficients[0].compareToZero()) {
			if (coefficients[1].compareToZero()) {
				if (coefficients[2].compareToZero()) {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							roots[0] = "x принадлежит множеству всех действительных чисел";
						} else {
							roots[0] = "x принадлежит множеству всех комплексных чисел";
							}
					} else {
						if (coefficients[4].compareToZero()) {
							roots[0] = "0";
						} else {
							linear_equation(coefficients[3], coefficients[4], false);
						}
					}
				} else {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							roots[0] = "0";
						} else {
							incomplete_quadratic_equation(coefficients[2], coefficients[4], false);
						}
					} else {
						if (coefficients[4].compareToZero()) {
							linear_equation(coefficients[2], coefficients[3], true);
						} else {
							complete_quadratic_equation(coefficients[2], coefficients[3], coefficients[4], false);
						}
					}
				}
			} else {
				if (coefficients[2].compareToZero()) {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							roots[0] = "0";
						} else {
							incomplete_cubic_equation(coefficients[1], coefficients[4], false);
						}
					} else {
						if (coefficients[4].compareToZero()) {
							incomplete_quadratic_equation(coefficients[1], coefficients[3], true);
						} else {
							complete_cubic_equation(coefficients[1], coefficients[2], coefficients[3], coefficients[4], false);
						}
					}
				} else {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							linear_equation(coefficients[1], coefficients[2], true);
						} else {
							complete_cubic_equation(coefficients[1], coefficients[2], coefficients[3], coefficients[4], false);
						}
					} else {
						if (coefficients[4].compareToZero()) {
							complete_quadratic_equation(coefficients[1], coefficients[2], coefficients[3], true);
						} else {
							complete_cubic_equation(coefficients[1], coefficients[2], coefficients[3], coefficients[4], false);
						}
					}
				}
			}
		} else {
			if (coefficients[1].compareToZero()) {
				if (coefficients[2].compareToZero()) {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							roots[0] = "0";
						} else {
							incomplete_equation_of_fourth_pow(coefficients[0], coefficients[4]);
						}
					} else {
						if (coefficients[4].compareToZero()) {
							incomplete_cubic_equation(coefficients[0], coefficients[3], true);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					}
				} else {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							incomplete_quadratic_equation(coefficients[0], coefficients[2], true);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					} else {
						complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
					}
				}
			} else {
				if (coefficients[2].compareToZero()) {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							linear_equation(coefficients[0], coefficients[1], true);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					} else {
						if (coefficients[4].compareToZero()) {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					}
				} else {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							complete_quadratic_equation(coefficients[0], coefficients[1], coefficients[2], true);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					} else {
						if (coefficients[4].compareToZero()) {
							complete_cubic_equation(coefficients[0], coefficients[1], coefficients[2], coefficients[3], true);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					}
				}
			}
		}
	}
	
	private void linear_equation(ComplexBigDecimal a, ComplexBigDecimal b, boolean zero) {
		output[0] = b.negate().divide(a, Main.ACCURACY_ROUND);
		roots[0] = "" + output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
		roots[1] = zero ? "0" : "";
	}
	
	private void complete_quadratic_equation(ComplexBigDecimal a, ComplexBigDecimal b, ComplexBigDecimal c, boolean zero) {
		D = b.pow(2).subtract(a.multiply(c).multiply(FOUR));
		final ComplexBigDecimal D_root = D.root(2, Main.ACCURACY_CALCULATION)[0];
		output[0] = D_root.subtract(b).divide(a.multiply(TWO), Main.ACCURACY_CALCULATION);
		output[1] = D_root.negate().subtract(b).divide(a.multiply(TWO), Main.ACCURACY_CALCULATION);
		roots[0] = output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
	if (D.compareToZero()) {
		roots[1] = zero?"0":"";
		}
	else {
		roots[1] = output[1].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
		roots[2] = zero?"0":"";
	}
	}

	private void incomplete_quadratic_equation(ComplexBigDecimal a, ComplexBigDecimal c, boolean zero) {
		final ComplexBigDecimal x1 = c.negate().divide(a, Main.ACCURACY_CALCULATION).root(2, Main.ACCURACY_CALCULATION)[0];
		roots[0] = x1.formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
		final ComplexBigDecimal x2 = c.negate().divide(a, Main.ACCURACY_CALCULATION).root(2, Main.ACCURACY_CALCULATION)[1];
		roots[1] = x2.formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
		if (zero) {
		roots[2] = "0";
		}
	}

	private void incomplete_cubic_equation(ComplexBigDecimal a, ComplexBigDecimal b, boolean zero) {
		final ComplexBigDecimal x = b.negate().divide(a, Main.ACCURACY_CALCULATION);
		final ComplexBigDecimal x1 = x.root(3, Main.ACCURACY_CALCULATION)[0];
		roots[0] = x1.formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
		if (!x1.compareImagToZero()) {
			final ComplexBigDecimal x2 = b.negate().divide(a, Main.ACCURACY_CALCULATION).root(3, Main.ACCURACY_CALCULATION)[1];
			roots[1] = x2.formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
			final ComplexBigDecimal x3 = b.negate().divide(a, Main.ACCURACY_CALCULATION).root(3, Main.ACCURACY_CALCULATION)[2];
			roots[2] = x3.formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
			if (zero) {
				roots[3] = "0";
			} else {
				roots[3] = "";
			}
		} else {
			roots[1] = zero?"0":"";
			roots[2] = "";
			roots[3] = "";
		}
	}
	
	boolean tmp;
	
	private void complete_cubic_equation(ComplexBigDecimal a, ComplexBigDecimal b, ComplexBigDecimal c, ComplexBigDecimal d, boolean zero) {
			p = (a.multiply(c).multiply(THREE).subtract(b.pow(2))).divide(a.pow(2).multiply(THREE), Main.ACCURACY_CALCULATION);
			final ComplexBigDecimal q_in1 = (b.pow(3).multiply(TWO)).subtract(a.multiply(b).multiply(NINE).multiply(c)).add(a.pow(2).multiply(d).multiply(TWENTY_SEVEN));
			q = q_in1.divide(a.pow(3).multiply(TWENTY_SEVEN), Main.ACCURACY_CALCULATION);
			Q = (p.pow(3).divide(TWENTY_SEVEN, Main.ACCURACY_CALCULATION)).add(q.pow(2).divide(FOUR, Main.ACCURACY_CALCULATION));
			α = (q.negate().divide(TWO, Main.ACCURACY_CALCULATION).add(Q.root(2, Main.ACCURACY_CALCULATION)[0])).root(3, Main.ACCURACY_CALCULATION)[1];
			β = new ComplexBigDecimal[3];
			for (int i = 0; i < 3; i ++) {
				β[i] = new ComplexBigDecimal();
				β[i] = (q.negate().divide(TWO, Main.ACCURACY_CALCULATION).subtract(Q.root(2, Main.ACCURACY_CALCULATION)[0])).root(3, Main.ACCURACY_CALCULATION)[i];
			}
			Y = new ComplexBigDecimal[3];
			ComplexBigDecimal j1 = p.negate().divide(THREE, Main.ACCURACY_CALCULATION).setScale(Main.ACCURACY_ROUND-100, BigDecimal.ROUND_DOWN);
			ComplexBigDecimal[] j = new ComplexBigDecimal[3];
			for (int i = 0; i < 3; i ++) {
				j[i] = α.multiply(β[i]).setScale(Main.ACCURACY_ROUND-100, BigDecimal.ROUND_DOWN);
				tmp = j[i].compareTo(j1);
				if (tmp) {
					right_β = i;
					Y[0] = α.add(β[i]);
					ComplexBigDecimal SQRT_THREE = new ComplexBigDecimal(new BigDecimal(0.75), BigDecimal.ZERO).root(2, Main.ACCURACY_CALCULATION)[0];
					ComplexBigDecimal IMAG_SQRT_THREE = new ComplexBigDecimal(BigDecimal.ZERO, SQRT_THREE.getReal());
					Y[1] = ((α.negate().subtract(β[i])).divide(TWO, Main.ACCURACY_CALCULATION)).add((α.subtract(β[i])).multiply(IMAG_SQRT_THREE));
					Y[2] = ((α.negate().subtract(β[i])).divide(TWO, Main.ACCURACY_CALCULATION)).subtract((α.subtract(β[i])).multiply(IMAG_SQRT_THREE));
					break;
				}
			}
			for (int i = 0; i < 3; i++) {
			output[i] = Y[i].subtract(b.divide(a.multiply(THREE), Main.ACCURACY_CALCULATION));
			roots[i] = output[i].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
			}
		if (zero) {
		roots[3] = "0";
		}
	}
	
	private void incomplete_equation_of_fourth_pow(ComplexBigDecimal e, ComplexBigDecimal c) {
		final ComplexBigDecimal x = c.negate().divide(e, Main.ACCURACY_CALCULATION);
		roots[0] = x.formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
		if (x.root(4, Main.ACCURACY_CALCULATION)[0].compareTo(x.root(4, Main.ACCURACY_CALCULATION)[1])) {
			roots[1] = "";
			roots[2] = "";
			roots[3] = "";
		} else {
			roots[1] = x.root(4, Main.ACCURACY_CALCULATION)[1].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
			roots[2] = x.root(4, Main.ACCURACY_CALCULATION)[2].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
			roots[3] = x.root(4, Main.ACCURACY_CALCULATION)[3].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
		}
	}

	private void complete_equation_of_fourth_pow(ComplexBigDecimal e, ComplexBigDecimal f, ComplexBigDecimal a, ComplexBigDecimal b, ComplexBigDecimal c) {
			final ComplexBigDecimal a_in1 = f.pow(2).multiply(THREE).divide(e.pow(2).multiply(EIGHT), Main.ACCURACY_CALCULATION);
			final ComplexBigDecimal a_in2 = a.divide(e, Main.ACCURACY_CALCULATION).subtract(a_in1);
			final ComplexBigDecimal b_in1 = f.pow(3).divide(e.pow(3).multiply(EIGHT), Main.ACCURACY_CALCULATION);
			final ComplexBigDecimal b_in2 = f.multiply(a).divide(e.pow(2).multiply(TWO), Main.ACCURACY_CALCULATION);
			final ComplexBigDecimal b_in3 = b_in1.subtract(b_in2).add(b.divide(e, Main.ACCURACY_CALCULATION));
			final ComplexBigDecimal c_in1 = f.pow(4).multiply(THREE).divide(e.pow(4).multiply(TWO_HUNDRED_FIFTY_SIX), Main.ACCURACY_CALCULATION);
			final ComplexBigDecimal c_in2 = f.pow(2).multiply(a).divide(e.pow(3).multiply(SIXTEEN), Main.ACCURACY_CALCULATION);
			final ComplexBigDecimal c_in3 = f.multiply(b).divide(e.pow(2).multiply(FOUR), Main.ACCURACY_CALCULATION);
			final ComplexBigDecimal c_in4 = c_in1.negate().add(c_in2).subtract(c_in3).add(c.divide(e, Main.ACCURACY_CALCULATION));
			α = a_in2;
			β[0] = b_in3;
			γ = c_in4;
			ComplexBigDecimal prefix = new ComplexBigDecimal(), postfix_x12 = new ComplexBigDecimal(), postfix_x34 = new ComplexBigDecimal(), x_in1 = new ComplexBigDecimal(), x_in2 = new ComplexBigDecimal();
			prefix = f.divide(e.multiply(FOUR), Main.ACCURACY_CALCULATION).negate();
			if (β[0].compareToZero()) {
				final ComplexBigDecimal biquadratic = α.pow(2).subtract(γ.multiply(FOUR)).root(2, Main.ACCURACY_CALCULATION)[0];
				postfix_x12 = α.negate().divide(TWO, Main.ACCURACY_ROUND)
						.add(biquadratic.divide(TWO, Main.ACCURACY_ROUND)).root(2, Main.ACCURACY_CALCULATION)[0];
				postfix_x34 = α.negate().divide(TWO, Main.ACCURACY_ROUND).subtract(biquadratic.divide(TWO, Main.ACCURACY_ROUND)).root(2, Main.ACCURACY_CALCULATION)[0];
				output[0] = prefix.add(postfix_x12);
				output[1] = prefix.subtract(postfix_x12);
				output[2] = prefix.add(postfix_x34);
				output[3] = prefix.subtract(postfix_x34);
				roots[0] = output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
				roots[1] = output[1].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
				roots[2] = output[2].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
				roots[3] = output[3].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
			} else {
				p = α.pow(2).negate().divide(TWELVE, Main.ACCURACY_ROUND)
						.subtract(γ);
				final ComplexBigDecimal Q_in1 = α.pow(3).divide(ONE_HUNDRED_EIGHT, Main.ACCURACY_ROUND);
				final ComplexBigDecimal Q_in2 = α.multiply(γ).divide(THREE, Main.ACCURACY_ROUND);
				final ComplexBigDecimal Q_in3 = β[0].pow(2).divide(EIGHT, Main.ACCURACY_ROUND);
				q = Q_in1.negate().add(Q_in2).subtract(Q_in3);
				R = q.negate().divide(TWO, Main.ACCURACY_ROUND).add(q.pow(2).divide(FOUR, Main.ACCURACY_ROUND)
						.add(p.pow(3).divide(TWENTY_SEVEN, Main.ACCURACY_ROUND)).root(2, Main.ACCURACY_CALCULATION)[0]);
				U = R.root(3, Main.ACCURACY_CALCULATION)[0];
				if (U.compareToZero()) {
					Y[0] = α.negate().multiply(FIVE).divide(SIX, Main.ACCURACY_ROUND).add(U)
							.subtract(q.root(3, Main.ACCURACY_CALCULATION)[0]);
				}
				else {
					Y[0] = α.negate().multiply(FIVE).divide(SIX, Main.ACCURACY_ROUND).add(U)
							.subtract(p.divide(U.multiply(THREE), Main.ACCURACY_CALCULATION));
				}
				W = α.add(Y[0].multiply(TWO)).root(2, Main.ACCURACY_CALCULATION)[0];
				x_in1 = α.negate().multiply(THREE).subtract(Y[0].multiply(TWO))
						.subtract(β[0].multiply(TWO).divide(W, Main.ACCURACY_CALCULATION)).root(2, Main.ACCURACY_CALCULATION)[0];
				x_in2 = α.negate().multiply(THREE).subtract(Y[0].multiply(TWO))
						.add(β[0].multiply(TWO).divide(W, Main.ACCURACY_CALCULATION)).root(2, Main.ACCURACY_CALCULATION)[0];
				output[0] = prefix.add(W.divide(TWO, Main.ACCURACY_ROUND))
						.add(x_in1.divide(TWO, Main.ACCURACY_ROUND));
				output[1] = prefix.add(W.divide(TWO, Main.ACCURACY_ROUND))
						.subtract(x_in1.divide(TWO, Main.ACCURACY_ROUND));
				output[2] = prefix.subtract(W.divide(TWO, Main.ACCURACY_ROUND))
						.add(x_in2.divide(TWO, Main.ACCURACY_ROUND));
				output[3] = prefix.subtract(W.divide(TWO, Main.ACCURACY_ROUND))
						.subtract(x_in2.divide(TWO, Main.ACCURACY_ROUND));
				roots[0] = output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
				roots[1] = output[1].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
				roots[2] = output[2].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
				roots[3] = output[3].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.slider.getValue());
			}
		}
	
	public String[] getRoots() {
		return roots;
	}
	
	public boolean getRootsIsNull() {
		boolean isNull = true;
		for (int i = 0; i < 3; i++) {
		isNull&= output[i] == null;
		}
		return isNull;
	}
	
	public ComplexBigDecimal[] getOutput() {
		return output;
	}
}
