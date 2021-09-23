class Intersecao {
	int ind1;
	int ind2;
	int ind3;
	int ind4;
	boolean interseta;

	Intersecao(int d1, int x1, int y1, int d2, int x2, int y2, int d3, int x3, int y3, int d4, int x4, int y4) {
		ind1 = d1;
		ind2 = d2;
		ind3 = d3;
		ind4 = d4;
		int d123 = (x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1);
		int d124 = (x4 - x1) * (y2 - y1) - (y4 - y1) * (x2 - x1);
		int d341 = (x1 - x3) * (y4 - y3) - (y1 - y3) * (x4 - x3);
		int d342 = (x2 - x3) * (y4 - y3) - (y2 - y3) * (x4 - x3);

		if (ind1 == ind3 || ind1 == ind4 || ind2 == ind3 || ind2 == ind4)
			interseta = false;
		else if (d123 * d124 < 0 && d341 * d342 < 0)
			interseta = true;
		else if (d123 == 0 && in_box(x1, y1, x2, y2, x3, y3))
			interseta = true;
		else if (d124 == 0 && in_box(x1, y1, x2, y2, x4, y4))
			interseta = true;
		else if (d341 == 0 && in_box(x3, y3, x4, y4, x1, y1))
			interseta = true;
		else if (d342 == 0 && in_box(x3, y3, x4, y4, x2, y2))
			interseta = true;
		else
			interseta = false;

	}

	public static boolean in_box(int x1, int y1, int x2, int y2, int x3, int y3) {
		return (Math.min(x1, x2) <= x3 && x3 <= Math.max(x1, x2)) && (Math.min(y1, y2) <= y3 && y3 <= Math.max(y1, y2));
	}
}