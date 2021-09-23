
/*
 * José Ferrão    up201805386
 * Paul Rodrigues up201805428
 * Grupo 12
 */

import java.util.*;

class trabalho {
	static int n;
	static int m1;
	static int m2;
	static Point p[];
	static boolean[][] adj;
	static int flag = 0;
	static Random[] random;
	static int count = 0;
	static int[] respostaA = new int[n];
	static int[] respostaB = new int[n];
	static int[] respostaC = new int[n];
	static int[] respostaD = new int[n];
	static int opt;

	public static void ex1() {
		m2 = 0 - m1; // negative range
		int indice = 0;

		System.out.println("Pontos Gerados: ");

		for (int i = 0; i < n; i++) {
			int x = (int) (m2 + Math.random() * (m1 + 1 - m2));
			int y = (int) (m2 + Math.random() * (m1 + 1 - m2));

			for (int j = 0; j < indice; j++) {
				while (p[j].x == x && p[j].y == y) {
					x = (int) (m2 + Math.random() * (m1 + 1 - m2));
					y = (int) (m2 + Math.random() * (m1 + 1 - m2));
				}
			}

			p[i] = new Point(indice, x, y);

			System.out.println("indice: " + indice + " (" + x + "," + y + ")");

			indice++;

		}
	}

	public static int[] ex2a() {
		int[] array = new int[n]; // array com permutação aleatória
		boolean[] visited = new boolean[n]; // ver se foi visitado ou não
		int i = 0;
		while (i < n) {
			int random = (int) (0 - n + Math.random() * (n + n));
			if (random >= 0 && random < n && !visited[random]) { // range
				array[i] = p[random].indice; // associar o indice ao array que vai ser imprimido
				i++; // ir para a próxima posição do array
				visited[random] = true; // marcar como visitado
			}
		}
		return array;
	}

	public static int[] ex2b() {
		int[] array = new int[n]; // ordem visitada dos pontos
		boolean[] visited = new boolean[n]; // pontos já visitados
		int random = -1;

		while (random < 0 || random >= n) { // random entre 0 e n
			random = (int) (Math.random() * n);
		}

		array[0] = random; // 1º ponto escolhido aleatoriamente
		int aux = random;
		visited[random] = true;

		for (int i = 1; i < n; i++) {
			Distance[] calcular = new Distance[n];

			for (int j = 0; j < n; j++) // inicialização do array da distancia a cada current point em "infinito"
				calcular[j] = new Distance(aux, Integer.MAX_VALUE, 1, 1, 1);

			for (int j = 0; j < n; j++) { // cálculo da distancia a cada ponto ainda não visitado
				if (!visited[j]) { // ordenar de forma decrescente o array pela distância
					calcular[j] = new Distance(p[j].indice, p[aux].x, p[aux].y, p[j].x, p[j].y);
				}

			}

			Arrays.sort(calcular);

			array[i] = calcular[0].indice; // guardar
			visited[calcular[0].indice] = true;
			aux = array[i];

		}

		return array;
	}

	public static int pos(int[] array, int tmp) {
		int valor = 0;

		for (int i = 0; i < array.length; i++) {
			if (tmp == array[i])
				valor = i;
		}

		return valor;
	}

	public static int sum(int num) {
		if (num == 0)
			return 0;
		return num + sum(num - 1);
	}

	public static int percorrer(int[] array, int conflitos) {
		for (int i = 0; i < n - 1; i++) {
			Intersecao[] intersecao = new Intersecao[n - 1];

			for (int j = i; j < n - 2; j++) {
				intersecao[j] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1], p[array[i + 1]].x,
						p[array[i + 1]].y, array[j + 1], p[array[j + 1]].x, p[array[j + 1]].y, array[j + 2],
						p[array[j + 2]].x, p[array[j + 2]].y);

				if (intersecao[j].interseta) {
					conflitos++;
				}
			}

			intersecao[n - 2 - i] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1],
					p[array[i + 1]].x, p[array[i + 1]].y, array[n - 1], p[array[n - 1]].x, p[array[n - 1]].y, array[0],
					p[array[0]].x, p[array[0]].y);

			if (intersecao[n - 2 - i].interseta) {
				conflitos++;
			}
		}

		return conflitos;
	}

	public static void recursividadeA(int[] cand) {
		int[] array = cand;
		int auxPerimetro = Integer.MAX_VALUE;
		int[] minPerimetro = array;

		// for (int i = 0; i < n; i++)
		// 	System.out.print(array[i] + " ");
		// System.out.println();
		int conflitos = 0;

		for (int i = 0; i < n - 1; i++) {
			Intersecao[] intersecao = new Intersecao[n - 1];

			for (int j = i; j < n - 2; j++) {
				intersecao[j] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1], p[array[i + 1]].x,
						p[array[i + 1]].y, array[j + 1], p[array[j + 1]].x, p[array[j + 1]].y, array[j + 2],
						p[array[j + 2]].x, p[array[j + 2]].y);

				//print_intersecao(intersecao, j);

				if (intersecao[j].interseta) {
					int tmp1 = intersecao[j].ind1;
					int tmp2 = intersecao[j].ind2;
					int tmp3 = intersecao[j].ind3;
					int tmp4 = intersecao[j].ind4;
					int[] arr = new int[n];
					adj = new boolean[n][n];
					int soma = 0;

					interseta(array, arr, tmp2, tmp3);
					trocas(array, arr, tmp2, tmp3); // -> {i,k} , {j,l}

					adjacentes(array);

					atualizacao(tmp1, tmp2, tmp3, tmp4);

					if (ciclo()) { // CICLO -> {i,l} , {j,k}
						for (int k = 0; k < n; k++)
							arr[k] = array[k];

						trocas(array, arr, tmp2, tmp4);

						// for (int k = 0; k < n; k++)
						// 	System.out.print(arr[k] + " ");
						// System.out.println();
					}

					// else { // valido
					// 	for (int k = 0; k < n; k++)
					// 		System.out.print(arr[k] + " ");
					// 	System.out.println();
					// }

					conflitos = percorrer(arr, 0);
					// System.out.println("conflitos: " + conflitos);

					for (int k = 0; k < n - 1; k++)
						soma += distancia(p[arr[k]].x, p[arr[k]].y, p[arr[k + 1]].x, p[arr[k + 1]].y);

					soma += distancia(p[arr[n - 1]].x, p[arr[n - 1]].y, p[arr[0]].x, p[arr[0]].y);

					if (auxPerimetro > soma) {
						auxPerimetro = soma;
						minPerimetro = arr;
					}
				}
			}

			// ultima posiçao com a primeira
			intersecao[n - 2 - i] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1],
					p[array[i + 1]].x, p[array[i + 1]].y, array[n - 1], p[array[n - 1]].x, p[array[n - 1]].y, array[0],
					p[array[0]].x, p[array[0]].y);

			//print_intersecao(intersecao, n - 2 - i);

			if (intersecao[n - 2 - i].interseta) {
				int tmp1 = intersecao[n - 2 - i].ind1;
				int tmp2 = intersecao[n - 2 - i].ind2;
				int tmp3 = intersecao[n - 2 - i].ind3;
				int tmp4 = intersecao[n - 2 - i].ind4;
				int[] arr = new int[n];
				adj = new boolean[n][n];
				int soma = 0;

				interseta(array, arr, tmp2, tmp3);
				trocas(array, arr, tmp2, tmp3); // -> {i,k} , {j,l}

				adjacentes(array);

				atualizacao(tmp1, tmp2, tmp3, tmp4);

				if (ciclo()) { // CICLO -> {i,l} , {k,j}
					for (int k = 0; k < n; k++)
						arr[k] = array[k];

					trocas(array, arr, tmp2, tmp4);

					// for (int k = 0; k < n; k++)
					// 	System.out.print(arr[k] + " ");
					// System.out.println();

				}

				// else { // valido {i,k} , {j,l}
				// 	for (int k = 0; k < n; k++)
				// 		System.out.print(arr[k] + " ");
				// 	System.out.println();
				// }

				conflitos = percorrer(arr, 0);
				// System.out.println("conflitos: " + conflitos);

				for (int k = 0; k < n - 1; k++)
					soma += distancia(p[arr[k]].x, p[arr[k]].y, p[arr[k + 1]].x, p[arr[k + 1]].y);

				soma += distancia(p[arr[n - 1]].x, p[arr[n - 1]].y, p[arr[0]].x, p[arr[0]].y);

				if (auxPerimetro > soma) {
					auxPerimetro = soma;
					minPerimetro = arr;
				}
			}
		}

		if (Arrays.equals(array, minPerimetro)) {
			respostaA = minPerimetro;
		} else
			recursividadeA(minPerimetro);
	}

	public static void recursividadeB(int[] cand) {
		int[] array = cand;
		int[] first = array;

		// for (int i = 0; i < n; i++)
		// 	System.out.print(array[i] + " ");
		// System.out.println();

		int conflitos = 0;

		for (int i = 0; i < n - 1; i++) {
			Intersecao[] intersecao = new Intersecao[n - 1];

			for (int j = i; j < n - 2; j++) {
				intersecao[j] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1], p[array[i + 1]].x,
						p[array[i + 1]].y, array[j + 1], p[array[j + 1]].x, p[array[j + 1]].y, array[j + 2],
						p[array[j + 2]].x, p[array[j + 2]].y);

				//print_intersecao(intersecao, j);

				if (intersecao[j].interseta) {
					int tmp1 = intersecao[j].ind1;
					int tmp2 = intersecao[j].ind2;
					int tmp3 = intersecao[j].ind3;
					int tmp4 = intersecao[j].ind4;
					int[] arr = new int[n];
					adj = new boolean[n][n];

					interseta(array, arr, tmp2, tmp3);
					trocas(array, arr, tmp2, tmp3); // -> {i,k} , {j,l}

					adjacentes(array);

					atualizacao(tmp1, tmp2, tmp3, tmp4);

					if (ciclo()) { // CICLO -> {i,l} , {j,k}
						for (int k = 0; k < n; k++)
							arr[k] = array[k];

						trocas(array, arr, tmp2, tmp4);

						// for (int k = 0; k < n; k++)
						// 	System.out.print(arr[k] + " ");
						// System.out.println();
					}

					// else { // valido
					// 	for (int k = 0; k < n; k++)
					// 		System.out.print(arr[k] + " ");
					// 	System.out.println();
					// }

					conflitos = percorrer(arr, 0);
					// System.out.println("conflitos: " + conflitos);

					if (flag == 0) { // 1º Candidato
						first = arr;
						flag = 1;
					}
				}
			}

			// ultima posiçao com a primeira
			intersecao[n - 2 - i] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1],
					p[array[i + 1]].x, p[array[i + 1]].y, array[n - 1], p[array[n - 1]].x, p[array[n - 1]].y, array[0],
					p[array[0]].x, p[array[0]].y);

			//print_intersecao(intersecao, n - 2 - i);

			if (intersecao[n - 2 - i].interseta) {
				int tmp1 = intersecao[n - 2 - i].ind1;
				int tmp2 = intersecao[n - 2 - i].ind2;
				int tmp3 = intersecao[n - 2 - i].ind3;
				int tmp4 = intersecao[n - 2 - i].ind4;
				int[] arr = new int[n];
				adj = new boolean[n][n];

				interseta(array, arr, tmp2, tmp3);
				trocas(array, arr, tmp2, tmp3); // -> {i,k} , {j,l}

				adjacentes(array);

				atualizacao(tmp1, tmp2, tmp3, tmp4);

				if (ciclo()) { // CICLO -> {i,l} , {k,j}
					for (int k = 0; k < n; k++)
						arr[k] = array[k];

					trocas(array, arr, tmp2, tmp4);

					// for (int k = 0; k < n; k++)
					// 	System.out.print(arr[k] + " ");
					// System.out.println();

				}

				// else { // valido {i,k} , {j,l}
				// 	for (int k = 0; k < n; k++)
				// 		System.out.print(arr[k] + " ");
				// 	System.out.println();
				// }

				conflitos = percorrer(arr, 0);
				// System.out.println("conflitos: " + conflitos);

				if (flag == 0) { // 1º Candidato
					first = arr;
					flag = 1;
				}
			}

		}

		if (flag == 1) {
			if (percorrer(first, 0) > 0) {
				flag = 0;
				recursividadeB(first);
			} else {
				respostaB = first;
			}
		}
	}

	public static void recursividadeC(int[] cand) {
		int[] array = cand;
		int auxConflitos = Integer.MAX_VALUE;
		int[] minConflitos = array;

		// for (int i = 0; i < n; i++)
		// 	System.out.print(array[i] + " ");
		// System.out.println();
		int conflitos = 0;

		for (int i = 0; i < n - 1; i++) {
			Intersecao[] intersecao = new Intersecao[n - 1];

			for (int j = i; j < n - 2; j++) {
				intersecao[j] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1], p[array[i + 1]].x,
						p[array[i + 1]].y, array[j + 1], p[array[j + 1]].x, p[array[j + 1]].y, array[j + 2],
						p[array[j + 2]].x, p[array[j + 2]].y);

				//print_intersecao(intersecao, j);

				if (intersecao[j].interseta) {
					int tmp1 = intersecao[j].ind1;
					int tmp2 = intersecao[j].ind2;
					int tmp3 = intersecao[j].ind3;
					int tmp4 = intersecao[j].ind4;
					int[] arr = new int[n];
					adj = new boolean[n][n];

					interseta(array, arr, tmp2, tmp3);
					trocas(array, arr, tmp2, tmp3); // -> {i,k} , {j,l}

					adjacentes(array);

					atualizacao(tmp1, tmp2, tmp3, tmp4);

					if (ciclo()) { // CICLO -> {i,l} , {j,k}
						for (int k = 0; k < n; k++)
							arr[k] = array[k];

						trocas(array, arr, tmp2, tmp4);

						// for (int k = 0; k < n; k++)
						// 	System.out.print(arr[k] + " ");
						// System.out.println();
					}

					// else { // valido
					// 	for (int k = 0; k < n; k++)
					// 		System.out.print(arr[k] + " ");
					// 	System.out.println();
					// }

					conflitos = percorrer(arr, 0);
					// System.out.println("conflitos: " + conflitos);
					if (conflitos == 0) {
						respostaC = arr;
						// System.out.println("Solucao: ");
						// for (int k = 0; k < n; k++)
						// 	System.out.print(arr[k] + " ");
						// System.out.println();
						break;
					}

					else {
						if (auxConflitos > conflitos) {
							auxConflitos = conflitos;
							minConflitos = arr;
						}
					}
				}
			}

			// ultima posiçao com a primeira
			intersecao[n - 2 - i] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1],
					p[array[i + 1]].x, p[array[i + 1]].y, array[n - 1], p[array[n - 1]].x, p[array[n - 1]].y, array[0],
					p[array[0]].x, p[array[0]].y);

			//print_intersecao(intersecao, n - 2 - i);

			if (intersecao[n - 2 - i].interseta) {
				int tmp1 = intersecao[n - 2 - i].ind1;
				int tmp2 = intersecao[n - 2 - i].ind2;
				int tmp3 = intersecao[n - 2 - i].ind3;
				int tmp4 = intersecao[n - 2 - i].ind4;
				int[] arr = new int[n];
				adj = new boolean[n][n];

				interseta(array, arr, tmp2, tmp3);
				trocas(array, arr, tmp2, tmp3); // -> {i,k} , {j,l}

				adjacentes(array);

				atualizacao(tmp1, tmp2, tmp3, tmp4);

				if (ciclo()) { // CICLO -> {i,l} , {k,j}
					for (int k = 0; k < n; k++)
						arr[k] = array[k];

					trocas(array, arr, tmp2, tmp4);

					// for (int k = 0; k < n; k++)
					// 	System.out.print(arr[k] + " ");
					// System.out.println();

				}

				// else { // valido {i,k} , {j,l}
				// 	for (int k = 0; k < n; k++)
				// 		System.out.print(arr[k] + " ");
				// 	System.out.println();
				// }

				conflitos = percorrer(arr, 0);
				// System.out.println("conflitos: " + conflitos);

				if (conflitos == 0) {
					respostaC = arr;
					// System.out.println("Solucao: ");
					// for (int k = 0; k < n; k++)
					// 	System.out.print(arr[k] + " ");
					// System.out.println();
					break;
				} else {
					if (auxConflitos > conflitos) {
						auxConflitos = conflitos;
						minConflitos = arr;
					}
				}
			}
		}
		if (Arrays.equals(array, minConflitos))
			respostaC = array;
		else
			recursividadeC(minConflitos);
	}

	public static void recursividadeD(int[] cand) {
		int[] array = cand;

		if (percorrer(array, 0) == 0) {
			respostaD = array;
			return;
		}

		random = new Random[sum(n - 1)];
		random[0] = new Random(0, n, array);

		// for (int i = 0; i < n; i++)
		// 	System.out.print(array[i] + " ");
		// System.out.println();

		int conflitos = 0;
		count = 0;

		for (int i = 0; i < n - 1; i++) {
			Intersecao[] intersecao = new Intersecao[n - 1];

			for (int j = i; j < n - 2; j++) {
				intersecao[j] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1], p[array[i + 1]].x,
						p[array[i + 1]].y, array[j + 1], p[array[j + 1]].x, p[array[j + 1]].y, array[j + 2],
						p[array[j + 2]].x, p[array[j + 2]].y);

				//print_intersecao(intersecao, j);

				if (intersecao[j].interseta) {
					int tmp1 = intersecao[j].ind1;
					int tmp2 = intersecao[j].ind2;
					int tmp3 = intersecao[j].ind3;
					int tmp4 = intersecao[j].ind4;
					int[] arr = new int[n];
					adj = new boolean[n][n];

					interseta(array, arr, tmp2, tmp3);
					trocas(array, arr, tmp2, tmp3); // -> {i,k} , {j,l}

					adjacentes(array);

					atualizacao(tmp1, tmp2, tmp3, tmp4);

					if (ciclo()) { // CICLO -> {i,l} , {j,k}
						for (int k = 0; k < n; k++)
							arr[k] = array[k];

						trocas(array, arr, tmp2, tmp4);

						// for (int k = 0; k < n; k++)
						// 	System.out.print(arr[k] + " ");
						// System.out.println();
					}

					// else { // valido
					// 	for (int k = 0; k < n; k++)
					// 		System.out.print(arr[k] + " ");
					// 	System.out.println();
					// }

					conflitos = percorrer(arr, 0);
					// System.out.println("conflitos: " + conflitos);

					random[count] = new Random(count, n, arr);
					count++;
				}
			}

			// ultima posiçao com a primeira
			intersecao[n - 2 - i] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1],
					p[array[i + 1]].x, p[array[i + 1]].y, array[n - 1], p[array[n - 1]].x, p[array[n - 1]].y, array[0],
					p[array[0]].x, p[array[0]].y);

			//print_intersecao(intersecao, n - 2 - i);

			if (intersecao[n - 2 - i].interseta) {
				int tmp1 = intersecao[n - 2 - i].ind1;
				int tmp2 = intersecao[n - 2 - i].ind2;
				int tmp3 = intersecao[n - 2 - i].ind3;
				int tmp4 = intersecao[n - 2 - i].ind4;
				int[] arr = new int[n];
				adj = new boolean[n][n];

				interseta(array, arr, tmp2, tmp3);
				trocas(array, arr, tmp2, tmp3); // -> {i,k} , {j,l}

				adjacentes(array);

				atualizacao(tmp1, tmp2, tmp3, tmp4);

				if (ciclo()) { // CICLO -> {i,l} , {k,j}
					for (int k = 0; k < n; k++)
						arr[k] = array[k];

					trocas(array, arr, tmp2, tmp4);

					// for (int k = 0; k < n; k++)
					// 	System.out.print(arr[k] + " ");
					// System.out.println();

				}

				// else { // valido {i,k} , {j,l}
				// 	for (int k = 0; k < n; k++)
				// 		System.out.print(arr[k] + " ");
				// 	System.out.println();
				// }

				conflitos = percorrer(arr, 0);
				// System.out.println("conflitos: " + conflitos);

				random[count] = new Random(count, n, arr);
				count++;
			}
		}

		if (Arrays.equals(random[0].array, array))
			respostaD = array;
		else {
			recursividadeD(escolhaRandom(count, random));
		}
	}

	public static void ex3() {
		int[] array = ex2b();
		random = new Random[sum(n - 1)];
		random[0] = new Random(0, n, array);
		int[] minConflitos = array;
		int[] minPerimetro = array;
		int auxConflitos = Integer.MAX_VALUE;
		int auxPerimetro = Integer.MAX_VALUE;
		int[] first = array;

		// for (int i = 0; i < n; i++)
		// 	System.out.print(array[i] + " ");
		// System.out.println();

		int conflitos = 0;

		for (int i = 0; i < n - 1; i++) {
			Intersecao[] intersecao = new Intersecao[n - 1];

			for (int j = i; j < n - 2; j++) {
				intersecao[j] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1], p[array[i + 1]].x,
						p[array[i + 1]].y, array[j + 1], p[array[j + 1]].x, p[array[j + 1]].y, array[j + 2],
						p[array[j + 2]].x, p[array[j + 2]].y);

				// print_intersecao(intersecao, j);

				if (intersecao[j].interseta) {
					int tmp1 = intersecao[j].ind1;
					int tmp2 = intersecao[j].ind2;
					int tmp3 = intersecao[j].ind3;
					int tmp4 = intersecao[j].ind4;
					int[] arr = new int[n];
					adj = new boolean[n][n];
					int soma = 0;

					interseta(array, arr, tmp2, tmp3);
					trocas(array, arr, tmp2, tmp3); // -> {i,k} , {j,l}

					adjacentes(array);

					atualizacao(tmp1, tmp2, tmp3, tmp4);

					if (ciclo()) { // CICLO -> {i,l} , {j,k}
						for (int k = 0; k < n; k++)
							arr[k] = array[k];

						trocas(array, arr, tmp2, tmp4);

						// for (int k = 0; k < n; k++)
						// 	System.out.print(arr[k] + " ");
						// System.out.println();
					}

					else { // valido
						// for (int k = 0; k < n; k++)
						// 	System.out.print(arr[k] + " ");
						// System.out.println();
					}

					conflitos = percorrer(arr, 0);
					//System.out.println("conflitos: " + conflitos);

					random[count] = new Random(count, n, arr);
					count++;

					for (int k = 0; k < n - 1; k++)
						soma += distancia(p[arr[k]].x, p[arr[k]].y, p[arr[k + 1]].x, p[arr[k + 1]].y);

					soma += distancia(p[arr[n - 1]].x, p[arr[n - 1]].y, p[arr[0]].x, p[arr[0]].y);

					if (conflitos == 0)
						respostaC = arr;
					else {
						if (auxConflitos > conflitos) {
							auxConflitos = conflitos;
							minConflitos = arr;
						}
					}

					if (auxPerimetro > soma) {
						auxPerimetro = soma;
						minPerimetro = arr;
					}

					if (flag == 0) { // 1º Candidato
						first = arr;
						flag = 1;
					}
				}
			}

			// ultima posiçao com a primeira
			intersecao[n - 2 - i] = new Intersecao(array[i], p[array[i]].x, p[array[i]].y, array[i + 1],
					p[array[i + 1]].x, p[array[i + 1]].y, array[n - 1], p[array[n - 1]].x, p[array[n - 1]].y, array[0],
					p[array[0]].x, p[array[0]].y);

			// print_intersecao(intersecao, n - 2 - i);

			if (intersecao[n - 2 - i].interseta) {
				int tmp1 = intersecao[n - 2 - i].ind1;
				int tmp2 = intersecao[n - 2 - i].ind2;
				int tmp3 = intersecao[n - 2 - i].ind3;
				int tmp4 = intersecao[n - 2 - i].ind4;
				int[] arr = new int[n];
				adj = new boolean[n][n];
				int soma = 0;

				interseta(array, arr, tmp2, tmp3);
				trocas(array, arr, tmp2, tmp3); // -> {i,k} , {j,l}

				adjacentes(array);

				atualizacao(tmp1, tmp2, tmp3, tmp4);

				if (ciclo()) { // CICLO -> {i,l} , {k,j}
					for (int k = 0; k < n; k++)
						arr[k] = array[k];

					trocas(array, arr, tmp2, tmp4);

					// for (int k = 0; k < n; k++)
					// 	System.out.print(arr[k] + " ");
					// System.out.println();

				}

				// else { // valido {i,k} , {j,l}
				// 	for (int k = 0; k < n; k++)
				// 		System.out.print(arr[k] + " ");
				// 	System.out.println();
				// }

				conflitos = percorrer(arr, 0);
				//System.out.println("conflitos: " + conflitos);

				random[count] = new Random(count, n, arr);
				count++;

				if (conflitos == 0)
					respostaC = arr;
				else {
					if (auxConflitos > conflitos) {
						auxConflitos = conflitos;
						minConflitos = arr;
					}
				}
				respostaC = arr;

				for (int k = 0; k < n - 1; k++)
					soma += distancia(p[arr[k]].x, p[arr[k]].y, p[arr[k + 1]].x, p[arr[k + 1]].y);

				soma += distancia(p[arr[n - 1]].x, p[arr[n - 1]].y, p[arr[0]].x, p[arr[0]].y);

				// if (conflitos == 0)
				// respostaC = arr;

				if (auxConflitos > conflitos) {
					auxConflitos = conflitos;
					minConflitos = arr;
				}

				if (auxPerimetro > soma) {
					auxPerimetro = soma;
					minPerimetro = arr;
				}

				if (flag == 0) { // 1º Candidato
					first = arr;
					flag = 1;
				}
			}
		}

		if (flag == 0)
			respostaA = respostaB = respostaC = respostaD = array;

		if (opt == 0 || opt == 1) {
			if (percorrer(minPerimetro, 0) == 0)
				respostaA = minPerimetro;
			else
				recursividadeA(minPerimetro);
		}
		if (opt == 0 || opt == 2) {
			if (flag == 1 && percorrer(first, 0) > 0) {
				flag = 0;
				recursividadeB(first);
			} 
			else {
				respostaB = first;

			}
		}

		if (opt == 0 || opt == 3) {
			if (Arrays.equals(array, minConflitos))
				respostaC = array;

			if (auxConflitos == 0)
				respostaC = minConflitos;

			else if (!Arrays.equals(array, minConflitos))
				recursividadeC(minConflitos);
		}
		if (opt == 0 || opt == 4) {
			if (Arrays.equals(random[0].array, array))
				respostaD = array;
			else {
				recursividadeD(escolhaRandom(count, random));
			}
		}
	}

	public static void print_intersecao(Intersecao[] intersecao, int pos) {
		System.out.println(intersecao[pos].ind1 + "," + intersecao[pos].ind2 + " " + intersecao[pos].ind3 + ","
				+ intersecao[pos].ind4);
		System.out.print(intersecao[pos].interseta + " ");
		System.out.println();
	}

	public static void interseta(int[] array, int[] arr, int tmp2, int tmp3) {
		int l;

		l = pos(array, tmp3);
		for (int i = 0; i < n; i++) {
			if (i > pos(array, tmp2) && i < pos(array, tmp3)) {
				arr[l - 1] = array[i];
				l--;
			} else
				arr[i] = array[i];
		}
	}

	public static void trocas(int[] array, int[] arr, int tmp2, int tmp3) {
		int a = pos(array, tmp2);
		int b = pos(array, tmp3);

		arr[a] = tmp3;
		arr[b] = tmp2;
	}

	public static void adjacentes(int[] array) { // arestas
		for (int i = 0; i < n - 1; i++) {
			if (i == 0) {
				adj[array[i]][array[i + 1]] = adj[array[i + 1]][array[i]] = adj[array[0]][array[n
						- 1]] = adj[array[n - 1]][array[0]] = true;
			} else {
				adj[array[i]][array[i + 1]] = adj[array[i + 1]][array[i]] = true;
			}
		}
	}

	public static void atualizacao(int tmp1, int tmp2, int tmp3, int tmp4) { // permutação das arestas
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (adj[i][j]) {
					if (i == tmp1 && j == tmp2) {
						adj[i][j] = adj[j][i] = false;
						adj[i][tmp3] = adj[tmp3][i] = true;
						adj[j][tmp4] = adj[tmp4][j] = true;
					} else if (i == tmp3 && j == tmp4) {
						adj[i][j] = adj[j][i] = false;
						adj[i][tmp1] = adj[tmp1][i] = true;
						adj[j][tmp2] = adj[tmp2][j] = true;
					}
				}
			}
		}
	}

	public static boolean ciclo() {
		for (int i = 0; i < n; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (adj[i][j]) {
					count++;
				}
			}
			if (count != 2)
				return true;
		}

		return false;
	}

	public static int distancia(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}

	public static int[] escolhaRandom(int count, Random[] intersecao) {
		int random = (int) (Math.random() * (count - 0)) + 0;

		return intersecao[random].array;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("n = ");
		n = in.nextInt(); // número de pontos
		if (n < 1)
			return;
		System.out.print("m = ");
		m1 = in.nextInt(); // range
		p = new Point[n];
		System.out.print("option = ");
		opt = in.nextInt();

		ex1();

		int[] array = new int[n];
		// array = ex2a();
		// System.out.print("permutacao = ");
		// for (int i = 0; i < n; i++)
		// 	System.out.print(array[i] + " ");
		// System.out.println();

		// array = ex2b();
		// System.out.print("nearest-neighbour first = ");
		// for (int i = 0; i < n; i++)
		// 	System.out.print(array[i] + " ");
		// System.out.println();
		
		if (n > 1) {
				ex3();

			if (opt == 0 || opt == 1) {
				System.out.print("Menor Perimetro: ");
				for (int i = 0; i < n; i++) {
					System.out.print(respostaA[i] + " ");
				}
				System.out.println();
			}

			if (opt == 0 || opt == 2) {
				System.out.print("1o Candidato: ");
				for (int i = 0; i < n; i++) {
					System.out.print(respostaB[i] + " ");
				}
				System.out.println();
			}

			if (opt == 0 || opt == 3) {
				System.out.print("Menos Conflitos: ");
				for (int i = 0; i < n; i++) {
					System.out.print(respostaC[i] + " ");
				}
				System.out.println();
			}

			if (opt == 0 || opt == 4) {
				System.out.print("Candidato random: ");
				for (int i = 0; i < n; i++) {
					System.out.print(respostaD[i] + " ");
				}
				System.out.println();
			}
		}
	}
}