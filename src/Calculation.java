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
	
	final BigDecimal TWO = new BigDecimal("2");
	final BigDecimal THREE = new BigDecimal("3");
	final BigDecimal FOUR = new BigDecimal("4");
	final BigDecimal FIVE = new BigDecimal("5");
	final BigDecimal SIX = new BigDecimal("6");
	final BigDecimal EIGHT = new BigDecimal("8");
	final BigDecimal NINE = new BigDecimal("9");
	final BigDecimal TWELVE = new BigDecimal("12");
	final BigDecimal SIXTEEN = new BigDecimal("16");
	final BigDecimal TWENTY_SEVEN = new BigDecimal("27");
	final BigDecimal ONE_HUNDRED_EIGHT = new BigDecimal("108");
	final BigDecimal TWO_HUNDRED_FIFTY_SIX = new BigDecimal("256");
	
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
			roots[i] = output[i].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);	
			}
		}
		return this;
	}
	
	public void calculation() {
		if (coefficients[0].isEqualToZero()) {
			if (coefficients[1].isEqualToZero()) {
				if (coefficients[2].isEqualToZero()) {
					if (coefficients[3].isEqualToZero()) {
						if (coefficients[4].isEqualToZero()) {
							roots[0] = "x принадлежит множеству всех комплексных чисел";
						} else {
							roots[0] = "x не существует";
							}
					} else {
						if (coefficients[4].isEqualToZero()) {
							roots[0] = "0";
						} else {
							linear_equation(coefficients[3], coefficients[4], false);
						}
					}
				} else {
					if (coefficients[3].isEqualToZero()) {
						if (coefficients[4].isEqualToZero()) {
							roots[0] = "0";
						} else {
							incomplete_quadratic_equation(coefficients[2], coefficients[4], false);
						}
					} else {
						if (coefficients[4].isEqualToZero()) {
							linear_equation(coefficients[2], coefficients[3], true);
						} else {
							complete_quadratic_equation(coefficients[2], coefficients[3], coefficients[4], false);
						}
					}
				}
			} else {
				if (coefficients[2].isEqualToZero()) {
					if (coefficients[3].isEqualToZero()) {
						if (coefficients[4].isEqualToZero()) {
							roots[0] = "0";
						} else {
							incomplete_cubic_equation(coefficients[1], coefficients[4], false);
						}
					} else {
						if (coefficients[4].isEqualToZero()) {
							incomplete_quadratic_equation(coefficients[1], coefficients[3], true);
						} else {
							complete_cubic_equation(coefficients[1], coefficients[2], coefficients[3], coefficients[4], false);
						}
					}
				} else {
					if (coefficients[3].isEqualToZero()) {
						if (coefficients[4].isEqualToZero()) {
							linear_equation(coefficients[1], coefficients[2], true);
						} else {
							complete_cubic_equation(coefficients[1], coefficients[2], coefficients[3], coefficients[4], false);
						}
					} else {
						if (coefficients[4].isEqualToZero()) {
							complete_quadratic_equation(coefficients[1], coefficients[2], coefficients[3], true);
						} else {
							complete_cubic_equation(coefficients[1], coefficients[2], coefficients[3], coefficients[4], false);
						}
					}
				}
			}
		} else {
			if (coefficients[1].isEqualToZero()) {
				if (coefficients[2].isEqualToZero()) {
					if (coefficients[3].isEqualToZero()) {
						if (coefficients[4].isEqualToZero()) {
							roots[0] = "0";
						} else {
							incomplete_equation_of_fourth_pow(coefficients[0], coefficients[4]);
						}
					} else {
						if (coefficients[4].isEqualToZero()) {
							incomplete_cubic_equation(coefficients[0], coefficients[3], true);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					}
				} else {
					if (coefficients[3].isEqualToZero()) {
						if (coefficients[4].isEqualToZero()) {
							incomplete_quadratic_equation(coefficients[0], coefficients[2], true);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					} else {
						complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
					}
				}
			} else {
				if (coefficients[2].isEqualToZero()) {
					if (coefficients[3].isEqualToZero()) {
						if (coefficients[4].isEqualToZero()) {
							linear_equation(coefficients[0], coefficients[1], true);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					} else {
						if (coefficients[4].isEqualToZero()) {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					}
				} else {
					if (coefficients[3].isEqualToZero()) {
						if (coefficients[4].isEqualToZero()) {
							complete_quadratic_equation(coefficients[0], coefficients[1], coefficients[2], true);
						} else {
							complete_equation_of_fourth_pow(coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4]);
						}
					} else {
						if (coefficients[4].isEqualToZero()) {
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
		roots[0] = "" + output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
		roots[1] = zero ? "0" : "";
	}
	
	private void complete_quadratic_equation(ComplexBigDecimal a, ComplexBigDecimal b, ComplexBigDecimal c, boolean zero) {
		D = b.pow(2).subtract(a.multiply(c).multiply(FOUR));
		final ComplexBigDecimal D_root = D.root(2, Main.ACCURACY_CALCULATION)[0];
		output[0] = D_root.subtract(b).divide(a.multiply(TWO), Main.ACCURACY_CALCULATION);
		output[1] = D_root.negate().subtract(b).divide(a.multiply(TWO), Main.ACCURACY_CALCULATION);
		roots[0] = output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
	if (D.isEqualToZero()) {
		roots[1] = zero?"0":"";
		}
	else {
		roots[1] = output[1].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
		roots[2] = zero?"0":"";
	}
	}

	private void incomplete_quadratic_equation(ComplexBigDecimal a, ComplexBigDecimal c, boolean zero) {
		output[0] = c.negate().divide(a, Main.ACCURACY_CALCULATION).root(2, Main.ACCURACY_CALCULATION)[0];
		roots[0] = output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
		output[1] = c.negate().divide(a, Main.ACCURACY_CALCULATION).root(2, Main.ACCURACY_CALCULATION)[1];
		roots[1] = output[1].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
		if (zero) {
		roots[2] = "0";
		}
	}

	private void incomplete_cubic_equation(ComplexBigDecimal a, ComplexBigDecimal b, boolean zero) {
		final ComplexBigDecimal x = b.negate().divide(a, Main.ACCURACY_CALCULATION);
		output[0] = x.root(3, Main.ACCURACY_CALCULATION)[0];
		roots[0] = output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
		if (!output[0].isEqualImagToZero()) {
			output[1] = b.negate().divide(a, Main.ACCURACY_CALCULATION).root(3, Main.ACCURACY_CALCULATION)[1];
			roots[1] = output[1].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
			output[2] = b.negate().divide(a, Main.ACCURACY_CALCULATION).root(3, Main.ACCURACY_CALCULATION)[2];
			roots[2] = output[2].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
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
	
	private void complete_cubic_equation(ComplexBigDecimal a, ComplexBigDecimal b, ComplexBigDecimal c, ComplexBigDecimal d, boolean zero) {
			p = (a.multiply(c).multiply(THREE).subtract(b.pow(2))).divide(a.pow(2).multiply(THREE), Main.ACCURACY_CALCULATION);
			final ComplexBigDecimal q_in1 = (b.pow(3).multiply(TWO)).subtract(a.multiply(b).multiply(NINE).multiply(c)).add(a.pow(2).multiply(d).multiply(TWENTY_SEVEN));
			q = q_in1.divide(a.pow(3).multiply(TWENTY_SEVEN), Main.ACCURACY_CALCULATION);
			Q = (p.pow(3).divide(TWENTY_SEVEN, Main.ACCURACY_CALCULATION)).add(q.pow(2).divide(FOUR, Main.ACCURACY_CALCULATION));
			α = (q.negate().divide(TWO, Main.ACCURACY_CALCULATION).add(Q.root(2, Main.ACCURACY_CALCULATION)[0])).root(3, Main.ACCURACY_CALCULATION)[0];
			β = new ComplexBigDecimal[3];
			for (int i = 0; i < 3; i ++) {
				β[i] = new ComplexBigDecimal();
				β[i] = (q.negate().divide(TWO, Main.ACCURACY_CALCULATION).subtract(Q.root(2, Main.ACCURACY_CALCULATION)[0])).root(3, Main.ACCURACY_CALCULATION)[i];
			}
			Y = new ComplexBigDecimal[3];
			ComplexBigDecimal j1 = p.negate().divide(THREE, Main.ACCURACY_CALCULATION).setScale(Main.ACCURACY_ROUND-100, BigDecimal.ROUND_DOWN);
			ComplexBigDecimal[] j = new ComplexBigDecimal[3];
			boolean tmp;
			for (int i = 0; i < 3; i++) {
				j[i] = α.multiply(β[i]).setScale(Main.ACCURACY_ROUND-100, BigDecimal.ROUND_DOWN);
				tmp = j[i].isEqualTo(j1);
				if (tmp) {
					right_β = i;
					Y[0] = α.add(β[i]);
					ComplexBigDecimal IMAG_HALF_SQRT_THREE = new ComplexBigDecimal(BigDecimal.ZERO, new BigDecimal("0.8660254037844386467637231707529361834714026269051903140279034897"
							+ "25966508454400018540573093378624287837813070707703351514984972547499476239405827756047186824264046615951152791033987410050542337461632507656171633"
							+ "451661443325336127334460918985613523565830183930794009524993268689929694733825173753288025378309174064803050473801093595162541572914761979916498894"
							+ "912254144357231916458673612081992293927698833979031909176833055421586890447189158051044152762450835011760355572144347995478182898543584249036449746"
							+ "648242141510393204301994369348768791158658915697996491503919351438526956684781656051853632009624553384115599644187820570711008371376051186497135415"
							+ "529949229737993832144448898073918979195114427426451788016926404032190986172330529844861436432632076911332349210010597742077639220590643267253517595"
							+ "825008344647207740423035638571999881463417314788719180947555063574319373488272991225894275487689506940332480955981111478555277621461861596098869131"
							+ "280815734421016426858341469324805958524869418197747969072878835926686816562955449827712312417393598802617998884596161785110152651420192957707485536"
							+ "214779603353101254760087981592936383179987641831715540075332926855323664269312961130291110255201840135148752399364039730829050208526340970009547866"
							+ "731087971946835124660211345517184906231860055592630542134455149860155601050031753588187291202601923777598639966898807453053947492772111663002009425"
							+ "651815780572443423640794644081622593632533322692438799581288321436055620421034008381758550051471590357757595480821230453519704064608451758746480682"
							+ "002069835215520626816163515461288663980146188298872768547734557871070212115390996163808700953212256227438758431348052666847108106802697302122827070"
							+ "06426650390681672492836820335198867111490598052146276725080070297023977357726727420363586882813118327458332011650300663287203505391842923422615"));
					Y[1] = ((α.negate().subtract(β[i])).divide(TWO, Main.ACCURACY_CALCULATION)).add((α.subtract(β[i])).multiply(IMAG_HALF_SQRT_THREE));
					Y[2] = ((α.negate().subtract(β[i])).divide(TWO, Main.ACCURACY_CALCULATION)).subtract((α.subtract(β[i])).multiply(IMAG_HALF_SQRT_THREE));
					break;
				}
			}
			for (int i = 0; i < 3; i++) {
			output[i] = Y[i].subtract(b.divide(a.multiply(THREE), Main.ACCURACY_CALCULATION));
			roots[i] = output[i].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
			}
		if (zero) {
		roots[3] = "0";
		}
	}
	
	private void incomplete_equation_of_fourth_pow(ComplexBigDecimal e, ComplexBigDecimal c) {
		final ComplexBigDecimal x = c.negate().divide(e, Main.ACCURACY_CALCULATION);
		output[0] = x.root(4, Main.ACCURACY_CALCULATION)[0];
		roots[0] = output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
		if (x.root(4, Main.ACCURACY_CALCULATION)[0].isEqualTo(x.root(4, Main.ACCURACY_CALCULATION)[1])) {
			roots[1] = "";
			roots[2] = "";
			roots[3] = "";
		} else {
			for (int i = 1; i < 4; i++) {
			output[i] = x.root(4, Main.ACCURACY_CALCULATION)[i];
			roots[i] = output[i].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
			}
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
		ComplexBigDecimal prefix, postfix_x12, postfix_x34, x_in1, x_in2 = new ComplexBigDecimal();
		prefix = f.divide(e.multiply(FOUR), Main.ACCURACY_CALCULATION).negate();
		if (β[0].isEqualToZero()) {
			final ComplexBigDecimal biquadratic = α.pow(2).subtract(γ.multiply(FOUR)).root(2, Main.ACCURACY_CALCULATION)[0];
			postfix_x12 = α.negate().divide(TWO, Main.ACCURACY_ROUND)
					.add(biquadratic.divide(TWO, Main.ACCURACY_ROUND)).root(2, Main.ACCURACY_CALCULATION)[0];
			postfix_x34 = α.negate().divide(TWO, Main.ACCURACY_ROUND).subtract(biquadratic.divide(TWO, Main.ACCURACY_ROUND)).root(2, Main.ACCURACY_CALCULATION)[0];
			output[0] = prefix.add(postfix_x12);
			output[1] = prefix.subtract(postfix_x12);
			output[2] = prefix.add(postfix_x34);
			output[3] = prefix.subtract(postfix_x34);
			roots[0] = output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
			roots[1] = output[1].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
			roots[2] = output[2].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
			roots[3] = output[3].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
		} else {
			p = α.pow(2).negate().divide(TWELVE, Main.ACCURACY_ROUND).subtract(γ);
			final ComplexBigDecimal Q_in1 = α.pow(3).divide(ONE_HUNDRED_EIGHT, Main.ACCURACY_ROUND);
			final ComplexBigDecimal Q_in2 = α.multiply(γ).divide(THREE, Main.ACCURACY_ROUND);
			final ComplexBigDecimal Q_in3 = β[0].pow(2).divide(EIGHT, Main.ACCURACY_ROUND);
			q = Q_in1.negate().add(Q_in2).subtract(Q_in3);
			R = q.negate().divide(TWO, Main.ACCURACY_ROUND).add(q.pow(2).divide(FOUR, Main.ACCURACY_ROUND)
					.add(p.pow(3).divide(TWENTY_SEVEN, Main.ACCURACY_ROUND)).root(2, Main.ACCURACY_CALCULATION)[0]);
			U = R.root(3, Main.ACCURACY_CALCULATION)[0];
			if (U.isEqualToZero()) {
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
			roots[0] = output[0].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
			roots[1] = output[1].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
			roots[2] = output[2].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
			roots[3] = output[3].formattedString(true, Interface.isNormalView, true, Main.ACCURACY_ROUND, Interface.sliderValue);
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