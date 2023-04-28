package common;

public class HedgeAlgebraUtil {
	private static final String[] G = { "DUST", "CLEAN" };
	private static final String[] H1 = { "MORE", "VERY" };
	public static final String[] H2 = { "POSSIBLE", "LESS" };

	private static final int MAX = 100;

	public static double numberToNumber(String omega) {
		int omegaN = 0;
		try {
			omegaN = Integer.parseInt(omega);
		} catch (Exception e) {
		}
		double vMaxLV = langToNumber("VERY CLEAN");

		return (omegaN * vMaxLV) * 1.0 / MAX;
	}

	public static double langToNumber(String hx) {
		String[] values = hx.split(" ");
		double alpha = fmH(H2[0]) + fmH(H2[1]);
		double beta = fmH(H1[0]) + fmH(H1[1]);
		double fm = 0;
		if (values[0].equalsIgnoreCase(H2[0])) {
			fm = (fmH(H2[0]) + fmH(H2[1])) * fmC(values[1]);
		} else if (values[0].equalsIgnoreCase(H2[1])) {
			fm = fmH(H2[1]) * fmC(values[1]);
		} else if (values[0].equalsIgnoreCase(H1[1])) {
			fm = (fmH(H1[0]) + fmH(H1[1])) * fmC(values[1]);
		} else if (values[0].equalsIgnoreCase(H1[0])) {
			fm = fmH(H1[1]) * fmC(values[1]);
		}
		return v(values[1])
				+ Sign(hx)
						* (fm - 0.5
								* (1 - Sign(hx) * Sign(H2[1] + hx)
										* (beta - alpha))
								* (fmH(values[0]) * fmC(values[1])));
	}

	private static double v(String c) {
		double W = ProcessUtil.getMiddleValue();
		double alpha = fmH(H2[0]) + fmH(H2[1]);
		if (c.equalsIgnoreCase(G[0])) {
			return (W - alpha * fmC(c));
		} else {
			return (W + alpha * fmC(c));
		}
	}

	private static double fmC(String c) {
		double W = ProcessUtil.getMiddleValue();
		if (c.equalsIgnoreCase(G[0])) {
			return W;
		} else {
			return (1 - W);
		}
	}

	private static double fmH(String h) {
		if (h.equalsIgnoreCase(H1[0])) {
			return ProcessUtil.getMoreValue();
		} else if (h.equalsIgnoreCase(H1[1])) {
			return ProcessUtil.getVeryValue();
		} else if (h.equalsIgnoreCase(H2[0])) {
			return ProcessUtil.getPossibleValue();
		} else if (h.equalsIgnoreCase(H2[1])) {
			return ProcessUtil.getLessValue();
		}

		return 0;
	}

	private static int Sign(String x) {
		int result = 1;
		if (x.contains(G[0])) {
			result = -1;
		} else if (x.contains(G[1])) {
			result = 1;
		}
		if (x.split(" ").length >= 2) {
			String values[] = x.split(" ");
			int index = values.length - 2;
			int oldH = result;
			while (index >= 0) {
				int h = -1;

				if (values[index].equalsIgnoreCase(H1[0])
						|| values[index].equalsIgnoreCase(H1[1])) {
					h = 1;
				}
				if (oldH * h < 0) {
					result = -result;
				}
				oldH = h;
				index--;
			}
		}

		return result;
	}
}
