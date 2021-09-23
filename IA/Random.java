class Random {
	int indice;
	int[] array;

	Random(int i, int n, int[] arr) {
		indice = i;
		array = new int[n];

		for (int j = 0; j < n; j++) {
			array[j] = arr[j];
		}

	}
}