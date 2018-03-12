package theComputationOfTheRootsOfAnyEquation;

import javax.swing.JLabel;

public class FormationOfSolutions {
	
	private String beginning_linear_equations, beginning_quadratic_equations, beginning_cubic_equations,
	beginning_equations_of_fourth_pow, textPNG;
	JLabel solutionDescription;
	String[] roots = new String[4];
	String[][] coefficientsString = new String[5][2];
	
	ComplexBigDecimal[] coefficientsRealNegate = new ComplexBigDecimal[5];
	ComplexBigDecimal[] coefficientsImagNegate = new ComplexBigDecimal[5];
	
	private ComplexBigDecimal[] coefficients = new ComplexBigDecimal[5];

	boolean forwardIsNotEmpty;
	boolean isOutput;
	
	public FormationOfSolutions() {}
	
	public FormationOfSolutions(String... coefficientsString) {
		for (int i = 0; i < 5; i++) {
			coefficients[i] = new ComplexBigDecimal(coefficientsString[i]);
			}
		setIsLatexExponentialView();
		inputCoefficientsForSolution();
		calculation();
	}
	
	public void setIsLatexExponentialView() {
		for (int i = 0; i < 4; i++) {
			if (Interface.calculation.getOutput()[i] != null) {
			roots[i] = Interface.calculation.getOutput()[i].formattedString(true, Interface.isNormalView, false, Main.ACCURACY_ROUND, Interface.slider.getValue());	
			}
		}
	}
	
	private String output(ComplexBigDecimal a) {
		String s = a.formattedString(isOutput, Interface.isNormalView, false, Main.ACCURACY_ROUND, Interface.slider.getValue());
		return s;
	}
	
	public String getTextPNG() {
		return textPNG;
	}
	
	public String getSolutionDescription() {
		return solutionDescription.getText();
	}
	
	public void inputCoefficientsForSolution() {
		isOutput = false;
		for (int i = 0; i < 5; i++) {
			coefficientsRealNegate[i] = new ComplexBigDecimal(coefficients[i].getReal().negate(), coefficients[i].getImag());
			coefficientsImagNegate[i] = new ComplexBigDecimal(coefficients[i].getReal(), coefficients[i].getImag().negate());
			if (!coefficients[i].compareToZero()) {
			coefficientsString[i][0] = coefficients[i].compareToNotZero() ? (forwardIsNotEmpty ? " + " : "") + "(" + output(coefficients[i]) + ")"
							: coefficients[i].compareImagToZero() ?
									coefficients[i].compareRealTo() > 0
									? (forwardIsNotEmpty ? " + "
											: "")
											+ output(coefficients[i])
											: (forwardIsNotEmpty ? " - " : "")
											+ (forwardIsNotEmpty ? output(coefficientsRealNegate[i]) : output(coefficients[i]))
							: coefficients[i].compareImagTo() > 0
											? (forwardIsNotEmpty ? " + " : "") + output(coefficients[i])
											: (forwardIsNotEmpty ? " - " : "") + (forwardIsNotEmpty ? output(coefficientsImagNegate[i]) : output(coefficients[i])) + "<html>  * x<sup><small>2</small></sup>";
			coefficientsString[i][1] = coefficientsString[i][0].split("\\<")[0];
			forwardIsNotEmpty|= !coefficients[i].compareRealToZero() || !coefficients[i].compareImagToZero();
			}
			else {
			coefficientsString[i][0] = "";
			coefficientsString[i][1] = "";
			}
		}
		isOutput = true;
		beginning_linear_equations = "<html><font size = 5>Ниже представлено решение линейного уравнения<br>"
				+ "Вид уравнения: Ax + B = 0<br><br>" + "Иcходное уравнение: "
				+ coefficientsString[3][0]
				+ coefficientsString[4][0] + " = 0<br><br>";
		beginning_quadratic_equations = "<html><font size = 5>Ниже представлено решение квадратного уравнения<br>"
				+ "Вид уравнения: Ax&sup2 + Bx + c = 0<br><br>" + "Исходное уравнение: "
				+ coefficientsString[2][0]
				+ coefficientsString[3][0]
				+ coefficientsString[4][0] + " = 0<br><br>";
		beginning_cubic_equations = "<html><font size = 5>Ниже представлено решение кубического уравнения по методу Кардано<br>"
				+ "Вид уравнения: Ax&sup3 + Bx&sup2 + Cx + D = 0<br><br>" + "Исходное уравнение: "
				+ coefficientsString[1][0]
				+ coefficientsString[2][0]
				+ coefficientsString[3][0]
				+ coefficientsString[3][0] + " = 0<br><br>";
		beginning_equations_of_fourth_pow = "<html><font size = 5>  Ниже представлено решение уравнения четвертой степени по методу Феррари<br>"
				+ "Вид уравнения: A * x<font size=3><sup>4</sup></font> + B * x<font size=3><sup>3</sup></font> + C * x<font size=3><sup>2</sup></font> + "
				+ "D * x + E = 0 <br><br>" + "Исходное уравнение: " + coefficientsString[0][0]
				+ coefficientsString[1][0]
				+ coefficientsString[2][0]
				+ coefficientsString[3][0]
				+ coefficientsString[4][0] + " = 0<html><br><br>";
	}
	
	public void calculation() {
		final int n = Interface.box.getSelectedIndex();
		if (coefficients[0].compareToZero()) {
			if (coefficients[1].compareToZero()) {
				if (coefficients[2].compareToZero()) {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							if (n == 0) {
								solutionDescription = new JLabel(beginning_linear_equations
										+ "<html><font size = 5>0 * x = 0, =&gt x принадлежит области всех действительных чисел");
								textPNG = "Ниже \\ представлено \\ решение \\ линейного \\ уравнения: \\\\ Вид \\ уравнения: Ax + B = 0 \\\\"
										+ "Иcходное \\ уравнение: " + "0x + 0 = 0" + " \\\\"
										+ "0 \\ всегда = 0, \\Rightarrow x \\ принадлежит \\ области \\ всех \\ действительных \\ чисел";
							}
							if (n == 1) {
								solutionDescription = new JLabel(beginning_quadratic_equations
										+ "<html><font size = 5>0 * x&sup2 + 0 * x = 0, =&gt x принадлежит области всех действительных чисел");
								textPNG = "Ниже \\ представлено \\ решение \\ квадратного \\ уравнения: \\\\ Вид \\ уравнения: Ax^2 + Bx + C = 0 \\\\"
										+ " Иcходное \\ уравнение: " + "0x^2 + 0x + 0 = 0" + "\\\\"
										+ "0 \\ всегда = 0, \\Rightarrow x \\ принадлежит \\ области \\ всех \\ действительных \\ чисел";
							}
							if (n == 2) {
								solutionDescription = new JLabel(beginning_cubic_equations
										+ "<html><font size = 5>0 * x&sup3 + 0 * x&sup2 + 0 * x = 0, =&gt x принадлежит области всех действительных чисел");
								textPNG = "Ниже \\ представлено \\ решение \\ кубического \\ уравнения: \\\\ Вид \\ уравнения: Ax^3 + Bx^2 + Cx + D = 0 \\\\"
										+ " Иcходное \\ уравнение: " + "0x^3 + 0x^2 + 0x + 0 = 0" + "\\\\"
										+ "0 \\ всегда = 0, \\Rightarrow x \\ принадлежит \\ области \\ всех \\ действительных \\ чисел";
							}
							if (n == 3) {
								solutionDescription = new JLabel(beginning_equations_of_fourth_pow
										+ "<html><font size = 5>0 * x<sup><small><font size = 3>4</font></small></sup>"
										+ " + 0 * x&sup3 + 0 * x&sup2 + 0 * x = 0, =&gt x принадлежит области всех действительных чисел");
								textPNG = "Ниже \\ представлено \\ решение \\ уравнения \\ четвертой \\ степени: \\\\ Вид \\ уравнения: Ax^4 + Bx^3 + Cx^2 + Dx + E = 0 \\\\"
										+ " Иcходное \\ уравнение: " + "0x^4 + 0x^3 + 0x^2 + 0x + 0 = 0" + "\\\\"
										+ "0 \\ всегда = 0, \\Rightarrow x \\ принадлежит \\ области \\ всех \\ действительных \\ чисел";
							}
						} else {
							solutionDescription = new JLabel(
									"<html><font size = 5>Число, отличное от нуля, <br>не может быть равно нулю, =&gt x не существует");
							textPNG = "Число, \\ отличное \\ от \\ нуля, \\ не \\ может \\ быть \\ равно \\ нулю, \\Rightarrow \\\\ x \\ принадлежит \\ множеству \\ всех \\ комплексных \\ чисел";
						}
					} else {
						the_solution_of_the_linear_equation();
					}
				} else {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							solutionDescription = new JLabel(beginning_quadratic_equations + "<html><font size = 5>  "
									+ coefficientsString[2][0] + "* x&sup2 = 0, =&gt x = 0");
							textPNG = "Ниже \\ представлено \\ решение \\ квадратного \\ уравнения: \\\\ Вид \\ уравнения: Ax^2 + Bx + C = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[2][1]
									+ "x^2" + " = 0 \\\\ " + coefficientsString[2][1] + "x^2 = 0, \\Rightarrow x = 0";
						} else {
							solutionDescription = new JLabel(
									beginning_quadratic_equations + "<html><font size = 5>x = &radic<>-C/A = " + roots[0]);
							textPNG = "Ниже \\ представлено \\ решение \\ квадратного \\ уравнения: \\\\ Вид \\ уравнения: Ax^2 + Bx + C = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[2][1]
									+ "x^2" + coefficientsString[4][0] + " = 0 \\\\ " + "x = \\sqrt{-\\frac{C}{A}} = " + roots[0];
						}
					} else {
						if (coefficients[4].compareToZero()) {
							solutionDescription = new JLabel(beginning_quadratic_equations
									+ "<html><font size = 5>Сначала выносим x скобки:<br><br>"
									+ coefficientsString[2][0]
									+ coefficientsString[3][0] + " = x * ("
									+ coefficientsString[2][1] + " * x"
									+ coefficientsString[3][0] + ")<br><br>"
									+ "Очевидно, что x<sub><small>1</small></sub> = 0<br><br>"
									+ "Второй корень находим, решая линейное уравнение <br><br>"
									+ "x<sub><small>2</small></sub> = -B/A = " + roots[0]);
							textPNG = "Ниже \\ представлено \\ решение \\ квадратного \\ уравнения: \\\\ Вид \\ уравнения: Ax^2 + Bx + C = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[2][1]
									+ "x^2" + coefficientsString[3][1] + "x"
									+ coefficientsString[4][0] + " = 0 \\\\ " + "Сначала \\ выносим \\ x \\ скобки: \\\\ "
									+ coefficientsString[2][1] + "x^2"
									+ coefficientsString[3][1] + "x" + " = ("
									+ coefficientsString[2][1] + "x"
									+ coefficientsString[3][1] + ")x = 0 \\\\ "
									+ "Очевидно, \\ что \\ x_{1} = 0 \\\\ "
									+ "Второй \\ корень \\ находим, \\ решая \\ линейное \\ уравнение: \\\\"
									+ "x_{2} = -\\frac{B}{A} = " + roots[0];
						} else {
							the_solution_of_the_quadratic_equation();
						}
					}
				}
			} else {
				if (coefficients[2].compareToZero()) {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							solutionDescription = new JLabel(beginning_cubic_equations + "<html><font size = 5>  "
									+ coefficientsString[1][0] + " = 0, =&gt x = 0");
							textPNG = "Ниже \\ представлено \\ решение \\ кубического \\ уравнения: \\\\ Вид \\ уравнения: Ax^3 + Bx^2 + Cx + D = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[1][1]
									+ "x^3" + " = 0 \\\\ " + coefficientsString[1][1] + " = 0, \\Rightarrow x = 0";
						} else {
							String inter,inter_latex;
								inter = "x<sub><small>1</small></sub> = " + roots[0] + "<br><br>"
										+ "x<sub><small>2</small></sub> = " + roots[1] + "<br><br>"
										+ "x<sub><small>3</small></sub> = " + roots[2];
								inter_latex = "x_{1} = " + roots[0] + " \\\\ x_{2} = " + roots[1] + " \\\\ x_{3} = " + roots[2];
							solutionDescription = new JLabel(beginning_cubic_equations
									+ "<html><font size = 5>x = <sup><small>3</small></sup><font size = 7>&radic</font>-D/A = "
									+ inter);
							textPNG = "Ниже \\ представлено \\ решение \\ кубического \\ уравнения: \\\\ Вид \\ уравнения: Ax^3 + Bx^2 + Cx + D = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[1][1]
									+ "x^3" + coefficientsString[4][0] + " = 0 \\\\"
									+ "x = \\sqrt[3]{-\\frac{D}{A}} \\\\" + inter_latex;
						}
					} else {
						if (coefficients[4].compareToZero()) {
							String inter,inter_latex;
								inter = "Остальные корни находим, решая неполное квадратное уравнение: "
										+ "<html>x<sub><small>2,3</small></sub><font size = 5> = &radic<>-C/A<br><br>"
										+ "x<sub><small>2</small></sub> = " + roots[0]
										+ "<br><br>x<sub><small>3</small></sub> = " + roots[1];
								inter_latex = "Остальные \\ корни \\ находим, \\\\ решая \\ неполное \\ квадратное \\ уравнение \\ "
										+ coefficientsString[1][1] + "x^2" + 
										coefficientsString[3][1] + " = 0 \\\\ "
										+ "x_{2,3} = \\sqrt{-\\frac{C}{A}} \\\\ "
										+ "x_{2} = " + roots[0] + " \\\\ x_{3} = " + roots[1];
							solutionDescription = new JLabel(beginning_cubic_equations
									+ "<html><font size = 5>Сначала выносим x скобки:<br><br>"
									+ coefficientsString[1][0]
									+ coefficientsString[3][0] + " = x * ("
									+ coefficientsString[1][1]
									+ " * x<sup><small>2</small></sup><> "
									+ coefficientsString[3][1] + ")<br><br>"
									+ "Отсюда видно, что x<sub><small>1</small></sub> = 0<br>" + inter);
							textPNG = "Ниже \\ представлено \\ решение \\ неполного \\ кубического \\ уравнения: \\\\ Вид \\ уравнения: Ax^3 + Bx^2 + Cx + D = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[1][1]
									+ "x^3" + coefficientsString[3][1] + "x = 0 \\\\ "
									+ "Сначала \\ выносим \\ x \\ скобки: \\\\"
									+ coefficientsString[1][1]
									+ "x^3" + coefficientsString[3][1] + "x = ("
									+ coefficientsString[1][1] + "x^2"
									+ coefficientsString[3][1] + ")x \\\\ "
									+ "Отcюда \\ видно, \\ что \\ x_{1} = 0 \\\\ " + inter_latex;
						} else {
							the_solution_of_the_cubic_equation();
						}
					}
				} else {
					if (coefficients[2].compareToZero()) {
						if (coefficients[3].compareToZero()) {
							solutionDescription = new JLabel(beginning_cubic_equations
									+ "<html><font size = 5>Сначала выносим x&sup2 за скобки:<br><br>"
									+ coefficientsString[1][0]
									+ coefficientsString[2][0] + " = x&sup2 * ("
									+ coefficientsString[1][1] + "x"
									+ coefficientsString[2][1] + ")<br><br>"
									+ "Отсюда видно, что x<sub><small>1</small></sub> = 0<br><br>"
									+ "Второй корень находим, решая линейное уравнение "
									+ coefficientsString[1][0] + " * x"
									+ coefficientsString[2][1] + " = 0<br><br>"
									+ "x<sub><small>2</small></sub> = -B/A = " + roots[0]);
							textPNG = "Ниже \\ представлено \\ решение \\ кубического \\ уравнения: \\\\ Вид \\ уравнения: Ax^3 + Bx^2 + Cx + D = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[1][1]
									+ "x^3" + coefficientsString[2][1] + "x^2"
									+ " = 0 \\\\ Сначала \\ выносим \\ x^2 \\ за \\ скобки: " + coefficientsString[1][1] + "x^3"
									+ coefficientsString[2][1] + "x^2" + " = (" + coefficientsString[1][1] + "x"
									+ coefficientsString[2][1] + ")x^2 = 0 \\\\ "
									+ "Отcюда \\ видно, \\ что \\ x_{1} = 0 \\\\ "
									+ "Второй \\ корень \\ находим, \\ решая \\ линейное \\ уравнение: \\\\ "
									+ "x_{2} = -\\frac{B}{A} = " + roots[0];
						} else {
							the_solution_of_the_cubic_equation();
						}
					} else {
						if (coefficients[4].compareToZero()) {
							String inter,inter_latex;
							if (Interface.calculation.D.compareToZero()) {
							inter = "";	
							inter_latex = "";
							}
							else {
							inter = "x<sub><small>2</small></sub> = (-B - <font size= 5>&radic</font>D)/2 = " + roots[1] + "</html>";	
							inter_latex = "x_{2} = \\frac{-B - \\sqrt{D}}{2A} = " + roots[1];
							}
							String view_x = Interface.calculation.D.compareToZero()?"x":"x_{1}";
							solutionDescription = new JLabel(beginning_cubic_equations
									+ "<html><font size = 5>Сначала выносим x скобки:<br><br>"
									+ coefficientsString[1][0]
									+ coefficientsString[2][0]
									+ coefficientsString[3][0] + " = x * ("
									+ coefficientsString[1][1] + "x&sup2"
									+ coefficientsString[2][1] + "x"
									+ coefficientsString[3][1] + ")<br><br>"
									+ "Отсюда видно, что x<sub><small>1</small></sub> = 0<br><br>"
									+ "Остальные корни находим, решая квадратное уравнение:<br><br> "
									+ "Находим дискриминант:<br><br>" + "D = B&sup2 - 4AC = " + output(Interface.calculation.D) + "<html><br><br>"
									+ "x<sub><small>2</small></sub> = (-B + <font size= 5>&radic</font>D)/2 = " + roots[0]
									+ "<html><br><br>" + inter);
							textPNG = "Ниже \\ представлено \\ решение \\ кубического \\ уравнения: \\\\ Вид \\ уравнения: Ax^3 + Bx^2 + Cx + D = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[1][1]
									+ "x^3" + coefficientsString[2][1] + "x^2"
									+ coefficientsString[3][1] + "x"
									+ " = 0 \\\\ Сначала \\ выносим \\ x \\ скобки: \\\\ " + coefficientsString[1][1] + "x^3"
									+ coefficientsString[2][1] + "x^2"
									+ coefficientsString[3][1] + "x = ("
									+ coefficientsString[1][1] + "x^2"
									+ coefficientsString[2][1] + "x"
									+ coefficientsString[3][1] + ")x = 0 \\\\ "
									+ "Отcюда \\ видно, \\ что \\ x_{1} = 0 \\\\ "
									+ "Остальные \\ корни \\ находим, \\ решая \\ квадратное \\ уравнение: \\\\ "
									+ "Находим \\ дискриминант: D = B^2 - 4AC = " + output(Interface.calculation.D) + "\\\\"
									+ view_x + " = \\frac{-B + \\sqrt{D}}{2A} = " + roots[0] + "\\\\" + inter_latex;
						} else {
							the_solution_of_the_cubic_equation();
						}
					}
				}
			}
		} else {
			if (coefficients[1].compareToZero()) {
				if (coefficients[2].compareToZero()) {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							solutionDescription = new JLabel(
									beginning_equations_of_fourth_pow + "<html><font size = 5>  " + coefficientsString[0][0]
											+ " * x<sup><small>4</small></sup> = 0, =&gt x = 0");
							textPNG = "Ниже \\ представлено \\ решение \\ уравнения \\ четвертой \\ степени: \\\\ Вид \\ уравнения: Ax^4 + Bx^3 + Cx^2 + Dx + E = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[0][1] + "x^4 = 0" + "\\\\"
									+ coefficientsString[0][1] + "x^4 = 0 \\Rightarrow x = 0 " ;
						} else {
							solutionDescription = new JLabel(beginning_equations_of_fourth_pow
									+ "<html><font size = 5>x = <sup><small>4</small></sup><font size = 7>&radic</font>-E/A<br><br>"
									+ "x = " + roots[0]);
							textPNG = "Ниже \\ представлено \\ решение \\ уравнения \\ четвертой \\ степени: \\\\ Вид \\ уравнения: Ax^4 + Bx^3 + Cx^2 + Dx + E = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[0][1] + "x^4" + 
									coefficientsString[4][0] + " = 0" + "\\\\ "
									+ "x = \\sqrt[4]{-\\frac{E}{A}} \\\\ "
									+ "x_{1} = " + roots[0] + " \\\\ x_{2} = " + roots[1] + " \\\\ x_{3} = " + roots[2] + " \\\\ x_{4} = " + roots[3];
						}
					} else {
						if (coefficients[4].compareToZero()) {
							solutionDescription = new JLabel(beginning_equations_of_fourth_pow
									+ "<html><font size = 5>Сначала выносим x скобки:<br><br>" + coefficientsString[0][0]
									+ "x<sup><small><font size = 3>4</font></small></sup>"
									+ coefficientsString[3][0] + "x" + " = x * (" + coefficientsString[0][0]
									+ "x&sup3" + coefficientsString[3][0] + ")<br><br>"
									+ "Отсюда видно, что x<sub><small>1</small></sub> = 0<br><br>"
									+ "Второй корень находим, решая неполное кубическое уравнение:<br><br> "
									+ "<html><font size = 5>x<sub><small>2</small></sub> = <sup><small>3</small></sup><font size = 7>&radic</font>-E/A<br><br>"
									+ "x = " + roots[0]);
							textPNG = "Ниже \\ представлено \\ решение \\ уравнения \\ четвертой \\ степени: \\\\ Вид \\ уравнения: Ax^4 + Bx^3 + Cx^2 + Dx + E = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[0][1] + "x^4"
									+ coefficientsString[3][1] + "x" + " = 0" + "\\\\ "
									+ "Сначала \\ выносим \\ x \\ скобки: " + coefficientsString[0][1] + "x^4"
									+ coefficientsString[3][1] + "x = ("
									+ coefficientsString[0][1] + "x^3"
									+ coefficientsString[3][1] + ")x = 0 \\\\ "
									+ "Отcюда \\ видно, \\ что \\ x_{1} = 0 \\\\ "
									+ "Остальные \\ корни \\ находим, \\ решая \\ неполное \\ кубическое \\ уравнение: \\\\ "
									+ "x_{2} = " + roots[0] + " \\\\ x_{3} = " + roots[1] + " \\\\ x_{4} = " + roots[2];
						} else {
							the_solution_of_equation_of_fourth_power();
						}
					}
				} else {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							solutionDescription = new JLabel(beginning_equations_of_fourth_pow
									+ "<html><font size = 5>Сначала выносим x&sup2 за скобки:<br><br>" + coefficientsString[0][0]
									+ "x<sup><small><font size = 3>4</font></small></sup>"
									+ coefficientsString[2][0] + "x&sup2" + " = x&sup2 * ("
									+ coefficientsString[0][0] + "x&sup2" + coefficientsString[2][0] + ")<br><br>"
									+ "Отсюда видно, что x<sub><small>1</small></sub> = 0<br><br>"
									+ "Второй корень находим, решая неполное квадратное уравнение " + coefficientsString[0][0] + " * x&sup2"
									+ coefficientsString[2][0] + " = 0<br><br>"
									+ "<html><font size = 5>x<sub><small>2</small></sub> = &radic<>-C/A = " + roots[0]);
							textPNG = "Ниже \\ представлено \\ решение \\ уравнения \\ четвертой \\ степени: \\\\ Вид \\ уравнения: Ax^4 + Bx^3 + Cx^2 + Dx + E = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[0][1] + "x^4"
									+ coefficientsString[2][1] + "x^2 = 0" + "\\\\ "
									+ "Сначала \\ выносим \\ x^2 \\ за \\ скобки: \\\\ "
									+ coefficientsString[0][1] + "x^4"
									+ coefficientsString[2][1] + "x^2 = ("
									+ coefficientsString[0][1] + "x^2"
									+ coefficientsString[2][1] + ")x^2 = 0 \\\\ "
									+ "Отcюда \\ видно, \\ что \\ x_{1} = 0 \\\\ "
									+ "Остальные \\ корни \\ находим, \\ решая \\ неполное \\ квадратное \\ уравнение: \\\\ "
									+ "x_{2} = " + roots[0] + " \\\\ x_{3} = " + roots[1];
						} else {
							the_solution_of_equation_of_fourth_power();
						}
					} else {
						the_solution_of_equation_of_fourth_power();
					}
				}
			} else {
				if (coefficients[2].compareToZero()) {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							solutionDescription = new JLabel(beginning_equations_of_fourth_pow
									+ "<html><font size = 5>Сначала выносим x&sup3 за скобки:<br><br>" + coefficientsString[0][0]
									+ "x<sup><small><font size = 3>4</font></small></sup>"
									+ coefficientsString[1][0] + "x&sup3" + " = x&sup3 * ("
									+ coefficientsString[0][0] + "x" + coefficientsString[1][0] + ")<br><br>"
									+ "Отсюда видно, что x<sub><small>1</small></sub> = 0<br><br>"
									+ "Второй корень находим, решая линейное уравнение " + coefficientsString[0][0] + " * x"
									+ coefficientsString[1][0] + " = 0<br><br>"
									+ "x<sub><small>2</small></sub> = -B/A = " + roots[0]);
							textPNG = "Ниже \\ представлено \\ решение \\ уравнения \\ четвертой \\ степени: \\\\ Вид \\ уравнения: Ax^4 + Bx^3 + Cx^2 + Dx + E = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[0][1] + "x^4" + coefficientsString[1][1]
									+ "x^3 = 0" + "\\\\ "
									+ "Сначала \\ выносим \\ x^3 \\ за \\ скобки: \\\\ " + coefficientsString[0][1] + "x^4" 
									+ coefficientsString[1][1] + "x^3 = ("
									+ coefficientsString[0][1] + "x" + coefficientsString[1][1]
									+ ")x^3 = 0 \\\\ "
									+ "Отcюда \\ видно, \\ что \\ x_{1} = 0 \\\\ "
									+ "Остальные \\ корни \\ находим, \\ решая \\ линейное \\ уравнение: \\\\ "
									+ "x_{2} = -\\frac{B}{A} = " + roots[0];
						} else {
							the_solution_of_equation_of_fourth_power();
						}
					} else {
						if (coefficients[4].compareToZero()) {
							the_solution_of_equation_of_fourth_power();
						} else {
							the_solution_of_equation_of_fourth_power();
						}
					}
				} else {
					if (coefficients[3].compareToZero()) {
						if (coefficients[4].compareToZero()) {
							String inter,inter_latex;
							if (Interface.calculation.D.compareToZero()) {
							inter = "";	
							inter_latex = "";
							}
							else {
							inter = "x<sub><small>2</small></sub> = (-B - <font size= 5>&radic</font>D)/2 = " + roots[1] + "</html>";	
							inter_latex = "x_{2} = \\frac{-B - \\sqrt{D}}{2A} = " + roots[1];
							}
							String view_x = Interface.calculation.D.compareToZero()?"x":"x_{1}";
							solutionDescription = new JLabel(beginning_equations_of_fourth_pow
									+ "<html><font size = 5>Сначала выносим x&sup2 скобки:<br><br>" + coefficientsString[0][0]
									+ "x<sup><small><font size = 3>4</font></small></sup>"
									+ coefficientsString[1][0] + "x&sup3"
									+ coefficientsString[2][0] + "x&sup2" + " = x&sup2 * ("
									+ coefficientsString[0][0] + "x&sup2" + coefficientsString[1][0] + "x"
									+ coefficientsString[2][0] + ")<br><br>"
									+ "Отсюда видно, что x<sub><small>1</small></sub> = 0<br><br>"
									+ "Остальные корни находим, решая квадратное уравнение:<br><br> "
									+ "Находим дискриминант:<br><br> D = B&sup2 - 4AC = " + output(Interface.calculation.D) + "<html><br><br>"
									+ "x<sub><small>2</small></sub> = (-B + <font size= 5>&radic</font>D)/2 = " + roots[0]
									+ "<html><br><br>" + inter);
							textPNG = "Ниже \\ представлено \\ решение \\ уравнения \\ четвертой \\ степени: \\\\ Вид \\ уравнения: Ax^4 + Bx^3 + Cx^2 + Dx + E = 0 \\\\"
									+ " Иcходное \\ уравнение: " + coefficientsString[0][1] + "x^4" + coefficientsString[1][1]
									+ "x^3" + coefficientsString[2][1] + "x^2 = 0" + "\\\\ "
									+ "Сначала \\ выносим \\ x^2 \\ за \\ скобки: \\\\ "
									+ coefficientsString[0][1] + "x^4" + coefficientsString[1][1]
									+ "x^3" + coefficientsString[2][1] + "x^2 = ("
									+ coefficientsString[0][1] + "x^2" + coefficientsString[1][1]
									+ "x" + coefficientsString[2][1] + ")x^2 = 0 \\\\ "
									+ "Отcюда \\ видно, \\ что \\ x_{1} = 0 \\\\ "
									+ "Остальные \\ корни \\ находим, \\ решая \\ квадратное \\ уравнение: \\\\ "
									+ "Находим \\ дискриминант: D = B^2 - 4AC = " + output(Interface.calculation.D) + " \\\\ "
									+ view_x + " = \\frac{-B + \\sqrt{D}}{2A} = " + roots[0] + "\\\\" + inter_latex;
						} else {
							the_solution_of_equation_of_fourth_power();
						}
					} else {
						if (coefficients[4].compareToZero()) {
							the_solution_of_the_cubic_equation();
						} else {
							the_solution_of_equation_of_fourth_power();
						}
					}
				}
			}
		}
	}
	
	private String correct_equation_view(String... n) {
		boolean isNormalView = Interface.isNormalView;
		if (n.length > 2) {
		n[n.length-3] = (n[n.length-3].isEmpty())?"":isNormalView?n[n.length-3] + "x^2":n[n.length-3] + " * x^2";
		if (n.length > 3) {
		n[n.length-4] = (n[n.length-4].isEmpty())?"":isNormalView?n[n.length-4] + "x^3":n[n.length-4] + " * x^3";	
		if (n.length > 4) {
		n[n.length-5] = (n[n.length-5].isEmpty())?"":isNormalView?n[n.length-5] + "x^4":n[n.length-5] + " * x^4";
		}
		}
		}
		n[n.length-2] = (n[n.length-2].isEmpty())?"":isNormalView?n[n.length-2] + "x":n[n.length-2] + " * x";
		String equation = n.length > 4?n[n.length-5] + n[n.length-4] + n[n.length-3] + n[n.length-2] + n[n.length-1]
		:n.length > 3?n[n.length-4] + n[n.length-3] + n[n.length-2] + n[n.length-1]
		:n.length > 2?n[n.length-3] + n[n.length-2] + n[n.length-1]
		:n[n.length-2] + n[n.length-1];
		return equation;
	}
	private void the_solution_of_the_linear_equation() {
		solutionDescription = new JLabel(beginning_linear_equations + "x = -B/A = " + roots[0]);
		textPNG = "Ниже \\ представлено \\ решение \\ линейного \\ уравнения \\ \\\\"
				+ "Вид \\ уравнения: \\ Ax + B = 0 \\\\ Иcходное \\ уравнение: " + correct_equation_view(coefficientsString[3][1], coefficientsString[4][1]) + " = 0\\\\"
				+ "x = -B/A = " + roots[0];
	}
	private void the_solution_of_the_quadratic_equation() {
		String inter,inter_latex;
		if (Interface.calculation.D.compareToZero()) {
		inter = "";	
		inter_latex = "";
		}
		else {
		inter = "x<sub><small>2</small></sub> = (-B - <font size= 5>&radic</font>D)/2 = " + roots[1] + "</html>";	
		inter_latex = "x_{2} = \\frac{-B - \\sqrt{D}}{2A} = " + roots[1];
		}
		String view_x = Interface.calculation.D.compareToZero()?"x":"x_{1}";
		solutionDescription = new JLabel(beginning_quadratic_equations + "Находим дискриминант:<br><br>"
				+ "D = B&sup2 - 4AC = " + output(Interface.calculation.D) + "<html><br><br>"
				+ "x<sub><small>1</small></sub> = (-B + <font size= 5>&radic</font>D)/2 = " + roots[0] + "<html><br><br>" + inter);
		textPNG = "Ниже \\ представлено \\ решение \\ квадратного \\ уравнения \\ \\\\"
				+ "Вид \\ уравнения: \\ Ax^2 + Bx + C = 0 \\\\ Иcходное \\ уравнение: " + correct_equation_view(coefficientsString[2][1],coefficientsString[3][1],coefficientsString[4][1])
				+ " = 0\\\\" + "Находим \\ дискриминант: D = B^2 - 4AC = " + output(Interface.calculation.D) + "\\\\"
				+ view_x + " = \\frac{-B + \\sqrt{D}}{2A} = " + roots[0] + "\\\\" + inter_latex;
	}

	private void the_solution_of_the_cubic_equation() {
		solutionDescription = new JLabel(beginning_cubic_equations
				+ "<html> Сначала находим значение двух промежуточных чисел: P и Q<br><br>"
				+ "<p><font size=5 face=Arial>P = (3AC - B&sup2)/3A&sup2 = "
				+ output(Interface.calculation.p) + "<html><br><br>"
				+ "<html><p><font size=5 face=Arial>  Q = (2B&sup3 - 9ABC + 27A&sup2<>D)/27A&sup3 = "
				+ output(Interface.calculation.q) + "<html><br><br>" + "Далее определяем S:<br><br>"
				+ "S = (P/3)&sup3 + (Q/2)&sup2<br><br> = " + output(Interface.calculation.Q)
				+ "Затем находим α и β:<br>"
				+ "α = <sup><small>3</small></sup><font size= 7>&radic</font>(-Q/2 + &radic<>S) = "
				+ output(Interface.calculation.α)
				+ "<br>β = <sup><small>3</small></sup><font size= 7>&radic</font>(-Q/2 - &radic<>S) = "
				+ output(Interface.calculation.β[Interface.calculation.right_β])
				+ "<br><br>Предпоследним шагом находим y<sub><small>1</small></sub>, y<sub><small>2</small></sub> и y<sub><small>3</small></sub>:<br><br>"
				+ "y<sub><small>1</small></sub> = α + β = " + output(Interface.calculation.Y[0]) + "<br><br>"
				+ "y<sub><small>2</small></sub> = -(α + β)/2 + (α - β)*&radic<>3/2 * i = "
				+ output(Interface.calculation.Y[1]) + "<br><br>"
				+ "y<sub><small>3</small></sub> = -(α + β)/2 - (α - β)*&radic<>3/2 * i = "
				+ output(Interface.calculation.Y[2]) + "<br><br>"
				+ "И наконец - находим корни уравнения:<br><br>"
				+ "  x<sub><small>1</small></sub> = -B/3A + y<sub><small>1</small></sub> = " + roots[0] + "<html><br><br>"
				+ "  x<sub><small >2</small></sub> = -B/3A + y<sub><small>2</small></sub> = " + roots[1] + "<html><br><br>"
				+ "  x<sub><small>3</small></sub> = -B/3A + y<sub><small>3</small></sub> = " + roots[2] + "</html>");
		textPNG = "Ниже \\ представлено \\ решение \\ кубического \\ уравнения \\ по \\ методу \\ Кардано \\\\"
				+ "Вид \\ уравнения: Ax^3 + Bx^2 + Cx + D = 0 \\\\ Иcходное \\ уравнение : " + correct_equation_view(coefficientsString[1][1],coefficientsString[2][1],coefficientsString[3][1],coefficientsString[4][0])
				+ " = 0 \\\\"
				+ "Сначала \\ находим \\ значение \\ двух \\ промежуточных \\ чисел: P \\ и \\ Q: \\\\ P = \\frac{3AC - B^2}{3A^2} = "
				+ output(Interface.calculation.p) + "\\\\" + "Q = \\frac{2B^3 - 9ABC + 27A^2D}{27A^3} = "
				+ output(Interface.calculation.q) + "\\\\ \\\\" + "Далее \\ определяем \\ S: \\\\"
				+ "S = \\frac{P^3}{27} + \\frac{Q^2}{4} = " + output(Interface.calculation.Q) + "\\\\"
				+ "Затем \\ находим \\ α \\ и  \\ β: \\\\ α = \\sqrt{-Q/2 + \\sqrt{S}} = "
				+ output(Interface.calculation.α) + "\\\\" + "β \\ = \\sqrt{-Q/2 - \\sqrt{S}} = "
				+ output(Interface.calculation.β[Interface.calculation.right_β]) + "\\\\"
				+ "Предпоследним \\ шагом \\ находим \\ y_{1}, \\ y_{2} \\ и \\ y_{3}: \\\\" + "y_{1} = α + β = "
				+ output(Interface.calculation.Y[0]) + "\\\\"
				+ "y_{2} = -(α + β) + \\frac{\\sqrt{3}(α - β)}{2} * i = " + output(Interface.calculation.Y[1])
				+ "\\\\" + "y_{3} = -(α + β) - \\frac{\\sqrt{3}(α - β)}{2} * i = "
				+ output(Interface.calculation.Y[2]) + "\\\\"
				+ "И \\ наконец \\ - \\ находим \\ корни \\ уравнения: \\\\" + "x_{1} = -\\frac{B}{3A} + y_{1} = " + roots[0]
				+ "\\\\ x_{2} = -\\frac{B}{3A} + y_{2} = " + roots[1] + "\\\\ x_{3} = -\\frac{B}{3A} + y_{3} = " + roots[2];
	}

	private void the_solution_of_equation_of_fourth_power() {
		String tmp = beginning_equations_of_fourth_pow
				+ "<html> Сначала находим значение трёх промежуточных чисел: α, β и γ<br><br>"
				+ "<p><font size=5 face=Arial>α = -3B&sup2/8A&sup2 + C/A = "
				+ output(Interface.calculation.α) + "<html><br><br>"
				+ "<html><p><font size=5 face=Arial>  β = B&sup3/8A&sup2 - BC/2A&sup2 + D/A = "
				+ (Interface.calculation.β[0].compareToZero()?"0":output(Interface.calculation.β[0])) + "<html><br><br> "
				+ "γ = -3B<font size=4><sup>4</sup></font>/256A<font size=4><sup>4</sup></font> + B&sup2/16A&sup3 - BD/4A&sup2 + E/A = "
				+ output(Interface.calculation.γ) + "<html><br><br>";
		textPNG = "Ниже \\ представлено \\ решение \\ уравнения \\ четвертой \\ степени \\ по \\ методу \\ Феррари \\\\"
				+ "Вид \\ уравнения: \\ Ax^4 + Bx^3 + Cx^2 + Dx + E = 0 \\\\" + "Иcходное \\ уравнение : " + correct_equation_view(coefficientsString[0][1],coefficientsString[1][1],coefficientsString[2][1],coefficientsString[3][1],coefficientsString[4][1])
				+ " = 0\\\\ Сначала \\ находим \\ значение \\ трёх \\ промежуточных \\ чисел: \\ α, \\ β \\ и \\ γ \\\\"
				+ "α = -\\frac{3B^2}{8A^2} + \\frac{C}{A} = " + output(Interface.calculation.α)
				+ "\\\\ β = \\frac{B^3}{8A^2} - \\frac{BC}{2A^2} + \\frac{D}{A} = "
				+ (Interface.calculation.β[0].compareToZero()?"0":output(Interface.calculation.β[0])) + "\\\\"
				+ "γ = -\\frac{3B^4}{256A^4} + \\frac{B^2}{16A^3} - \\frac{BD}{4A^2} + \\frac{E}{A} = "
				+ output(Interface.calculation.γ);
		if (Interface.calculation.β[0].compareToZero()) {
			solutionDescription = new JLabel(
					tmp + "  β = 0, =&gt, решив биквадратное уравнение u<font size=4><sup>4</sup></font> + αu&sup2 + γ<br>"
							+ "  и подставив x = u - B/4A, мы найдем 4 корня:<br><br>"
							+ "  x<sub><small>1</small></sub> = -B/4A + <font size= 7>&radic</font>((-α + &radic<>(α&sup2 - 4γ))/2) = "
							+ roots[0] + "<html><br><br>"
							+ "  x<sub><small>2</small></sub> = -B/4A - <font size= 7>&radic</font>((-α + &radic<>(α&sup2 - 4γ))/2) = "
							+ roots[1] + "<html><br><br>"
							+ "  x<sub><small>3</small></sub> = -B/4A + <font size= 7>&radic</font>((-α - &radic<>(α&sup2 - 4γ))/2) = "
							+ roots[2] + "<html><br><br>"
							+ "  x<sub><small>4</small></sub> = -B/4A - <font size= 7>&radic</font>((-α - &radic<>(α&sup2 - 4γ))/2) = "
							+ roots[3] + "<html><br><br>");
			textPNG += "\\\\ β = 0 \\Rightarrow решив \\ биквадратное \\ уравнение \\ u^4 + αu^2 + γ = 0 \\\\ и \\ подставив \\ x = u - \\frac{B}{4A}, мы \\ найдем \\ 4 \\ корня: \\\\"
					+ "x_{1} = -\\frac{B}{4A} + \\sqrt{\\frac{-α + \\sqrt{α^2 - 4γ}}{2}} = " + roots[0] + "\\\\"
					+ "x_{2} = -\\frac{B}{4A} - \\sqrt{\\frac{-α + \\sqrt{α^2 - 4γ}}{2}} = " + roots[1] + "\\\\"
					+ "x_{3} = -\\frac{B}{4A} + \\sqrt{\\frac{-α - \\sqrt{α^2 - 4γ}}{2}} = " + roots[2] + "\\\\"
					+ "x_{4} = -\\frac{B}{4A} - \\sqrt{\\frac{-α - \\sqrt{α^2 - 4γ}}{2}} = " + roots[3];
		} else {
			tmp += "<html>  β &#8800 0 =&gt" + "  находим значение следующих трёх промежуточных чисел: P, Q и R<br><br>"
					+ "  P = -α&sup2/12 - γ = " + output(Interface.calculation.p) + "<html><br><br>"
					+ "  Q = -α&sup3/108 + αγ/3 - β&sup2/8 = " + output(Interface.calculation.q)
					+ "<html><br><br>" + "  R = -Q/2 + <font size= 7>&radic</font>(Q&sup2/4 + P&sup3/27) = "
					+ output(Interface.calculation.R) + "<br><br>" + "  Затем находим U:<br><br>"
					+ "  U = <sup><small>3</small></sup><font size= 7>&radic</font>R = "
					+ output(Interface.calculation.U) + "<br>"
					+ "  (любой корень подойдёт, поэтому выбираем первый корень)<br><br>";
			textPNG += " \\\\ β \\neq 0 \\Rightarrow находим \\ значение \\ следующих \\ трёх \\ промежуточных \\ чисел: P, \\ Q \\ и \\ R: \\\\"
					+ "P = -\\frac{α^2}{12} - γ = " + output(Interface.calculation.p) + "\\\\"
					+ "Q = -\\frac{α^3}{108} + \\frac{αγ}{3} - \\frac{β^2}{8} = "
					+ output(Interface.calculation.q) + "\\\\"
					+ "R = -\\frac{Q}{2} + \\sqrt{\\frac{Q^2}{4} + \\frac{P^3}{27}} = "
					+ output(Interface.calculation.R) + "\\\\" + "Затем \\ находим \\ U: \\\\"
					+ "U = \\sqrt[3]{R} = " + output(Interface.calculation.U)
					+ "\\\\ (любой \\ корень \\ подойдёт, \\ поэтому \\ выбираем \\ первый \\ корень)";
			if (Interface.calculation.U.compareToZero()) {
				solutionDescription = new JLabel(tmp + "<html>  U = 0 =&gt находим Y<br><br>"
						+ "  Y = -5α/6 + U - <sup><small>3</small></sup><font size= 5>&radic</font>Q = "
						+ output(Interface.calculation.Y[0]) + "<br><br>" + "  Далее находим W:<br><br>"
						+ "  W = <font size= 7>&radic</font>(α + 2Y) = " + output(Interface.calculation.W)
						+ "<br><br>" + "  И наконец - находим корни уравнения:<br><br>"
						+ "  x<sub><small>1</small></sub> = -B/4A + (W + <font size= 7>&radic</font>(-(3α + 2Y + 2β/W)))/2 = "
						+ roots[0] + "<html><br>"
						+ "  x<sub><small>2</small></sub> = -B/4A + (W - <font size= 7>&radic</font>(-(3α + 2Y + 2β/W)))/2 = "
						+ roots[1] + "<html><br>"
						+ "  x<sub><small>3</small></sub> = -B/4A + (-W + <font size= 7>&radic</font>(-(3α + 2Y - 2β/W)))/2 = "
						+ roots[2] + "<html><br>"
						+ "  x<sub><small>4</small></sub> = -B/4A + (-W - <font size= 7>&radic</font>(-(3α + 2Y - 2β/W)))/2 = "
						+ roots[3]);
				textPNG += "\\\\ U = 0 \\Rightarrow находим \\ Y \\\\ Y = -\\frac{5α}{6} + U - \\sqrt[3]{Q} = "
						+ output(Interface.calculation.Y[0]) + "\\\\"
						+ "Далее \\ находим \\ W: \\\\ W = \\sqrt{α + 2Y} = "
						+ output(Interface.calculation.W) + "\\\\"
						+ "И \\ наконец \\ - \\ находим \\ корни \\ уравнения: \\\\"
						+ "x_{1} = -\\frac{B}{4A} + \\frac{W + \\sqrt{-3α - 2Y - \\frac{2β}{W}}}{2} = " + roots[0] + "\\\\"
						+ "x_{2} = -\\frac{B}{4A} + \\frac{W - \\sqrt{-3α - 2Y - \\frac{2β}{W}}}{2} = " + roots[1] + "\\\\"
						+ "x_{3} = -\\frac{B}{4A} + \\frac{-W + \\sqrt{-3α - 2Y + \\frac{2β}{W}}}{2} = " + roots[2] + "\\\\"
						+ "x_{4} = -\\frac{B}{4A} + \\frac{-W - \\sqrt{-3α - 2Y + \\frac{2β}{W}}}{2} = " + roots[3];
			}
			else {
				solutionDescription = new JLabel(tmp + "<html>  U &#8800 0 =&gt находим Y<br><br>"
						+ "  Y = -5α/6 + U - P/3U = " + output(Interface.calculation.Y[0]) + "<br><br>"
						+ "  Далее находим W:<br><br>" + "  W = <font size= 7>&radic</font>(α + 2Y) = "
						+ output(Interface.calculation.W) + "<br><br>"
						+ "  И наконец - находим корни уравнения:<br><br>"
						+ "  x<sub><small>1</small></sub> = -B/4A + (W + <font size= 7>&radic</font>(-(3α + 2Y + 2β/W)))/2 = "
						+ roots[0] + "<html><br>"
						+ "  x<sub><small>2</small></sub> = -B/4A + (W - <font size= 7>&radic</font>(-(3α + 2Y + 2β/W)))/2 = "
						+ roots[1] + "<html><br>"
						+ "  x<sub><small>3</small></sub> = -B/4A + (-W + <font size= 7>&radic</font>(-(3α + 2Y - 2β/W)))/2 = "
						+ roots[2] + "<html><br>"
						+ "  x<sub><small>4</small></sub> = -B/4A + (-W - <font size= 7>&radic</font>(-(3α + 2Y - 2β/W)))/2 = "
						+ roots[3]);
				textPNG += "\\\\ U \\neq 0 \\Rightarrow находим \\ Y \\\\ Y = -\\frac{5α}{6} + U - \\frac{P}{3U} = "
						+ output(Interface.calculation.Y[0]) + "\\\\"
						+ "Далее \\ находим \\ W: \\\\ W = \\sqrt{α + 2Y} = "
						+ output(Interface.calculation.W) + "\\\\"
						+ "И \\ наконец \\ - \\ находим \\ корни \\ уравнения: \\\\"
						+ "x_{1} = -\\frac{B}{4A} + \\frac{W + \\sqrt{-3α - 2Y - \\frac{2β}{W}}}{2} = " + roots[0] + "\\\\"
						+ "x_{2} = -\\frac{B}{4A} + \\frac{W - \\sqrt{-3α - 2Y - \\frac{2β}{W}}}{2} = " + roots[1] + "\\\\"
						+ "x_{3} = -\\frac{B}{4A} + \\frac{-W + \\sqrt{-3α - 2Y + \\frac{2β}{W}}}{2} = " + roots[2] + "\\\\"
						+ "x_{4} = -\\frac{B}{4A} + \\frac{-W - \\sqrt{-3α - 2Y + \\frac{2β}{W}}}{2} = " + roots[3];
			}
		}
	}
}
