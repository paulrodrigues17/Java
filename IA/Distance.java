

class Distance implements Comparable<Distance> {
	int indice;
	int dist;

	Distance(int i, int x1, int y1, int x2, int y2) {
		indice = i;
		dist = (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}

	@Override
	public int compareTo(Distance other) {
		if (other.dist < dist)
			return +1;
		if (other.dist > dist)
			return -1;
		return 0;
	}
}