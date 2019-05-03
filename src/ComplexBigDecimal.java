import java.math.BigDecimal;

import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;
import org.apfloat.Apfloat;

public class ComplexBigDecimal {

	private BigDecimal real;
	private BigDecimal imag;
	private boolean isSimpleExponentialView = true;
	private boolean isOutput = true;
	private int accuracyView;
	
	public ComplexBigDecimal() {
		this.real = BigDecimal.ZERO;
		this.imag = BigDecimal.ZERO;
	}
	
	public ComplexBigDecimal(BigDecimal real, BigDecimal imag) {
		this.real = real;
		this.imag = imag;
	}
	
	public ComplexBigDecimal(String text) {
		String coef, a, b;
		String[] real_and_imag, i;
		if (text != null) {
		coef = text.replaceAll(" ", "");
		try {
			if (coef.isEmpty()) {
			real = BigDecimal.ZERO;
			imag = BigDecimal.ZERO;	
			}
			else {
			if (coef.indexOf("i") != -1) {
				if (coef.substring(1).indexOf("-") != -1) {
					if (coef.charAt(0) == '-') {
						real_and_imag = coef.substring(1).split("-");
						i = real_and_imag[1].split("i");
						a = "-" + real_and_imag[0];
						if (real_and_imag[1].charAt(0) != 'i') {
							b = "-" + i[0];
						} else {
							b = "-1";
						}
					} else {
						real_and_imag = coef.split("-");
						i = real_and_imag[1].split("i");
						a = real_and_imag[0];
						if (real_and_imag[1].charAt(0) != 'i') {
							b = "-" + i[0];
						} else {
							b = "-1";
						}
					}
				} else if (coef.indexOf("+") != -1) {
					if (coef.substring(0, 1) == "-") {
						real_and_imag = coef.split("\\+");
						i = real_and_imag[1].split("i");
						a = "-" + real_and_imag[0];
						if (real_and_imag[1].charAt(0) != 'i') {
							b = i[0];
						} else {
							b = "1";
						}
					} else {
						real_and_imag = coef.split("\\+");
						i = real_and_imag[1].split("i");
						a = real_and_imag[0];
						if (real_and_imag[1].charAt(0) != 'i') {
							b = i[0];
						} else {
							b = "1";
						}
					}
				} else {
					i = coef.split("i");
					a = "0";
					if (coef.charAt(0) == '-') {
						if (coef.charAt(1) == 'i') {
							b = "-1";
						} else {
							b = i[0];
						}
					} else {
						if (coef.charAt(0) == 'i') {
							b = "1";
						} else {
							b = i[0];
						}
					}
				}
			} else {
				a = coef;
				b = "0";
			}
		real = new BigDecimal(a);
		imag = new BigDecimal(b);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		else {
		real = BigDecimal.ZERO;
		imag = BigDecimal.ZERO;
		}
	}
	
	public BigDecimal getReal() {
		return real;
	}
	
	public BigDecimal getImag() {
		return imag;
	}
	
	public void setReal(BigDecimal a) {
		real = a;
	}
	
	public void setImag(BigDecimal a) {
		imag = a;
	}
	
	public ComplexBigDecimal multiply(ComplexBigDecimal a) {
		ComplexBigDecimal result = new ComplexBigDecimal();
		result.real = real.multiply(a.real).subtract(imag.multiply(a.imag));
		result.imag = real.multiply(a.imag).add(imag.multiply(a.real));
		return result;
	}
	
	public ComplexBigDecimal multiply(BigDecimal a) {
		ComplexBigDecimal result = new ComplexBigDecimal();
		result.real = real.multiply(a);
		result.imag = imag.multiply(a);
		return result;
	}
	
	public ComplexBigDecimal divide(ComplexBigDecimal a, int accuracyCalculation) {
		ComplexBigDecimal result = new ComplexBigDecimal();
		final BigDecimal temp = a.real.multiply(a.real).add(a.imag.multiply(a.imag));
		result.real = real.multiply(a.real).add(imag.multiply(a.imag)).divide(temp, accuracyCalculation, BigDecimal.ROUND_CEILING);
		result.imag = imag.multiply(a.real).subtract(real.multiply(a.imag)).divide(temp, accuracyCalculation, BigDecimal.ROUND_CEILING);
		if (result.real.compareTo(BigDecimal.ZERO) == 0) {
			result.real = BigDecimal.ZERO;
		}
		if (result.imag.compareTo(BigDecimal.ZERO) == 0) {
			result.imag = BigDecimal.ZERO;
		}
		return result;
	}
	
	public ComplexBigDecimal divide(BigDecimal a, int accuracyCalculation) {
		ComplexBigDecimal result = new ComplexBigDecimal();
		result.real = real.divide(a, accuracyCalculation, BigDecimal.ROUND_CEILING);
		result.imag = imag.divide(a, accuracyCalculation, BigDecimal.ROUND_CEILING);
		return result;
	}
	
	public ComplexBigDecimal subtract(ComplexBigDecimal a) {
		ComplexBigDecimal result = new ComplexBigDecimal();
		result.real = real.subtract(a.real);
		result.imag = imag.subtract(a.imag);
		return result;
	}
	
	public ComplexBigDecimal add(ComplexBigDecimal a) {
		ComplexBigDecimal result = new ComplexBigDecimal();
		result.real = real.add(a.real);
		result.imag = imag.add(a.imag);
		return result;
	}
	
	public ComplexBigDecimal negate() {
		ComplexBigDecimal result = new ComplexBigDecimal();
		result.real = real.negate();
		result.imag = imag.negate();
		return result;
	}
	
	/**
	 * Равно ли число нулю
	 */
	
	public boolean isEqualToZero() {
		return real.compareTo(BigDecimal.ZERO) == 0 && imag.compareTo(BigDecimal.ZERO) == 0;
	}
	
	/**
	 * Не равно ли число нулю
	 */
	
	public boolean isNotEqualToZero() {
		return !isEqualRealToZero() && !isEqualImagToZero();
	}
	
	/**
	 * Равна ли действительная часть числа нулю
	 */
	
	public boolean isEqualRealToZero() {
		return real.compareTo(BigDecimal.ZERO) == 0;
	}
	
	/**
	 * Равна ли мнимая часть числа нулю
	 */
	
	public boolean isEqualImagToZero() {
		return imag.compareTo(BigDecimal.ZERO) == 0;
	}
	
	/**
	 * Равно ли число данному
	 * @param a - данное число типа ComplexBigDecimal
	 */
	
	public boolean isEqualTo(ComplexBigDecimal a) {
		return real.compareTo(a.getReal()) == 0 && imag.compareTo(a.getImag()) == 0;
	}
	
	/**
	 * Сравнение действительной части числа с нулём
	 * @return -1, если Re(z) < 0 <br> 0, если Re(z) == 0 <br> 1, если Re(z) > 0
	 */
	
	public int compareRealToZero() { 
		return real.compareTo(BigDecimal.ZERO); 
	}

	/**
	 * Сравнение мнимой части числа с нулём
	 * @return -1, если Im(z) < 0 <br> 0, если Im(z) == 0 <br> 1, если Im(z) > 0
	 */
	
	public int compareImagToZero() {
		return imag.compareTo(BigDecimal.ZERO);
	} 
	
	/**
	 * Возведение числа z в степень n
	 * @param n степень
	 * @return <html>z<font size= 4><sup><small>n</small></sup> </html>
	 */
	
	public ComplexBigDecimal pow(int n) {
		ComplexBigDecimal result = new ComplexBigDecimal(this.real, this.imag);
		for (int i = 1; i < n; i++) {
			result = result.multiply(this);
		}
		return result;
	}
	
	/**
	 * Извлечение всех корней степени n из числа z
	 * @param n степень корня
	 * @return <html>n комплексных корней вида<font size= 5><sup><small>n</small></sup>&#8730|x|*(cos((arg(x) + 2*&#960*k)/n) + i * sin((arg(x) + 2*&#960*k)/n))</html>
	 */
	
	public ComplexBigDecimal[] root(int n, int accuracyCalculation) {
		ComplexBigDecimal[] output = new ComplexBigDecimal[n];
		final Apcomplex input = new Apcomplex(new Apfloat(real, Main.ACCURACY_CALCULATION), new Apfloat(imag, Main.ACCURACY_CALCULATION));
		for (int i = 0; i < n; i++) {
			output[i] = new ComplexBigDecimal();
			output[i].setReal(new BigDecimal(ApcomplexMath.allRoots(input, n)[i].real().toString()).setScale(accuracyCalculation, BigDecimal.ROUND_CEILING));
			output[i].setImag(new BigDecimal(ApcomplexMath.allRoots(input, n)[i].imag().toString()).setScale(accuracyCalculation, BigDecimal.ROUND_CEILING));
		}
		return output;
	}
	
	public ComplexBigDecimal setScale(int scale, int type_of_rounding) {
		real = real.setScale(scale, type_of_rounding);
		imag = imag.setScale(scale, type_of_rounding);
		return this;
	}
	
	public String formattedString(boolean isOutput, boolean isNormalView, boolean isSimpleExponentialView, int accuracyRound, int accuracyView) {
		String output;
		this.isSimpleExponentialView = isSimpleExponentialView;
		this.isOutput = isOutput;
		this.accuracyView = accuracyView;
		if (isNormalView) {
			if (getExponent(real.abs()) > -accuracyRound && real.compareTo(BigDecimal.ZERO) != 0) {
				if (imag.compareTo(BigDecimal.ZERO) > 0 && getExponent(imag.abs()) > -accuracyRound) {
					if (getCoefficient(imag).equals("1")) {
						output = getCoefficient(real) + " + i";
					}
					else {
						output = getCoefficient(real) + " + " + getCoefficient(imag) + "i";
					}
				} else if (imag.compareTo(BigDecimal.ZERO) < 0 && getExponent(imag.abs()) > -accuracyRound) {
					if (getCoefficient(imag).equals("-1")) {
						output = getCoefficient(real) + " - i";	
					}
					else {
						output = "" + getCoefficient(real) + " - " + getCoefficient(imag.negate()) + "i";
					}
				} else {
						output = getCoefficient(real);
				}
			} else {
				if (getExponent(imag.abs()) > -accuracyRound) {
					if (getCoefficient(imag).equals("1")) {
						output = "i";	
					} 
					else if (getCoefficient(imag).equals("-1")) {
						output = "-i";	
					} 
					else {
						output = getCoefficient(imag) + "i";
					}
				} else {
						output = "0";
				}
			}
			return output;
		} else {
			if (getExponent(real.abs()) > -accuracyRound) {
				if (imag.compareTo(BigDecimal.ZERO) > 0 && getExponent(imag.abs()) > -accuracyRound) {
					output = "" + setExponential(real) + " + " + setExponential(imag) + " * i";
				} else if (imag.compareTo(BigDecimal.ZERO) < 0 && getExponent(imag.abs()) > -accuracyRound) {
					output = "" + setExponential(real) + " - " + setExponential(imag.negate()) + " * i";
				} else {
					output = "" + setExponential(real);
				}
			} else {
				if (getExponent(imag.abs()) > -accuracyRound) {
					output = "" + setExponential(imag) + " * i";
				} else {
					output = "0";
				}
			}
			return output;
		}
	}

	private String getCoefficient(BigDecimal a) {
		String a_round;
		int exp = getExponent(a);
		if (isOutput) {
			final BigDecimal a_tmp = a.setScale(a.scale() - getCountZeroToRoundNormal(a), BigDecimal.ROUND_CEILING);
			a_round = exp != 0 ? (exp < 0 ? roundMin(a) : "" + roundNormal(a))
				: a_tmp.scale() > accuracyView ? "" + a_tmp.setScale(accuracyView, BigDecimal.ROUND_CEILING)
						: "" + a_tmp;
		} else {
			a_round = a.toPlainString();
		}
		return a_round;
	}

	private String roundMin(BigDecimal a) {
		String s;
		s = a.setScale(accuracyView + getCountZeroToRoundMin(a), BigDecimal.ROUND_CEILING).toPlainString();
		return s;
	}

	private int getCountZeroToRoundMin(BigDecimal a) {
		int countZero = 0;
		if (a.compareTo(BigDecimal.ZERO) > 0) {
			final String s = "" + a.toPlainString();
			final char[] c = s.toCharArray();
			int j = 2;
			while (c[j] == '0') {
				countZero++;
				j++;
			}
			return countZero;
		}
		if (a.compareTo(BigDecimal.ZERO) < 0) {
			final String s = "" + a.toPlainString();
			final char[] c = s.toCharArray();
			int j = 3;
			while (c[j] == '0') {
				countZero++;
				j++;
			}
			return countZero;
		} else {
			return 0;
		}
	}

	private String setExponential(BigDecimal a) {
		if (a.compareTo(BigDecimal.ZERO) != 0) {
			String a_exp;
			final int e = getExponent(a);
			if (e != 0) {
				if (isSimpleExponentialView) {
					a_exp = a.divide(BigDecimal.valueOf(Math.pow(10, e)), accuracyView, BigDecimal.ROUND_CEILING) + " * 10 ^ " + e;
				} else {
					a_exp = a.divide(BigDecimal.valueOf(Math.pow(10, e)), accuracyView, BigDecimal.ROUND_CEILING) + " * 10 ^{" + e + "}";
				}
			} else {
				final BigDecimal inter = a.setScale(a.scale() - getCountZeroToRoundNormal(a), BigDecimal.ROUND_CEILING);
				if (inter.scale() > accuracyView) {
					return "" + inter.setScale(accuracyView, BigDecimal.ROUND_CEILING);
				} else {
					return "" + inter;
				}
			}
			return a_exp;
		} else {
			return "0";
		}
	}

	private BigDecimal roundNormal(BigDecimal a) {
		BigDecimal result;
		if (a.compareTo(BigDecimal.ZERO) != 0) {
			if (getCountZeroToRoundNormal(a) < 20) {
				result = a.setScale(accuracyView - getCountZeroToRoundNormal(a), BigDecimal.ROUND_CEILING);
				return result;
			} else {
				final BigDecimal inter = a.setScale(a.scale() - getCountZeroToRoundNormal(a), BigDecimal.ROUND_CEILING);
				if (inter.scale() > accuracyView) {
					return inter.setScale(accuracyView, BigDecimal.ROUND_CEILING);
				} else {
					return inter;
				}
			}
		} else {
			return BigDecimal.ZERO;
		}
	}

	private int getCountZeroToRoundNormal(BigDecimal a) {
		int countZero = 0;
		if (a.compareTo(BigDecimal.ZERO) != 0) {
			final String s = "" + a.toPlainString();
			final char[] c = s.toCharArray();
			int j = 1;
			if (c[c.length - 1] == '0') {
				while (c[c.length - j] == '0') {
					countZero++;
					j++;
				}
				return countZero;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	private int getExponent(BigDecimal a) {
		if (a.compareTo(BigDecimal.ZERO) != 0) {
			int exp = 0;
			try {
				if (a.abs().doubleValue() >= 10) {
					while(a.doubleValue() >= 10 || a.doubleValue() <= -10) {
						a = a.divide(BigDecimal.TEN, 30, BigDecimal.ROUND_CEILING);
						exp++;
					};
				}
				if (a.abs().doubleValue() < 1) {
					while(a.abs().doubleValue() < 1) {
						a = a.multiply(BigDecimal.TEN);
						exp--;
					};
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
			return exp;
		} else {
			return 0;
		}
	}
}