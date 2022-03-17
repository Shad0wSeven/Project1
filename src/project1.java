import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class project1 {
	static int xs = 0;
	static int ys = 0;
	static int xf = 0;
	static int yf = 0;
	static int a;
	static int b;
	static int c;
	static boolean coordbased = false;
	static boolean coordbasedout = false;
	static Queue<Integer> xq = new LinkedList<Integer>();
	static Queue<Integer> yq = new LinkedList<Integer>();
	static int[][] map;
	// static ArrayList<Integer> path = new ArrayList<Integer>();

	static int finalparams = 0;

	public static void drawMap(int x, int y) {
		int mx = 1000;
		int my = 1000;
		int mv = 1000;
		// System.out.println(x + " " + y);
		if (map[x][y] == 1) {
			// System.out.println(mapcopy);
			return;
		}
		if (y + 1 < map.length) {
			if (map[x][y + 1] < mv && map[x][y + 1] > 0) {
				mv = map[x][y + 1];
				mx = x;
				my = y + 1;
			}
		}

		if (x + 1 < map[0].length) {
			if (map[x + 1][y] < mv && map[x + 1][y] > 0) {
				mv = map[x + 1][y];
				mx = x + 1;
				my = y;
			}
		}

		if (x > 0) {
			if (map[x - 1][y] < mv && map[x - 1][y] > 0) {
				mv = map[x - 1][y];
				mx = x - 1;
				my = y;
				// System.out.println(map[x-1][y]);
			}
		}

		if (y > 0) {
			if (map[x][y - 1] < mv && map[x][y - 1] > 0) {
				mv = map[x][y - 1];
				mx = x;
				my = y - 1;
				// System.out.println(map[x][y-1]);
			}
		}

		map[x][y] = -200;
		drawMap(mx, my);
	}

	public static void printMap() {
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				if (map[i][j] == 1) {
					System.out.print("K");
				} else if (map[i][j] == -200) {
					System.out.print("+");
				} else if (map[i][j] == -3) {
					System.out.print("C");
				} else if (map[i][j] == -1) {
					System.out.print("@");
				} else {
					System.out.print(".");
				}
			}
			System.out.println("");
		}
	}

	public static void pushDP(int x, int y) {
		// System.out.println(x + " " + y);
		if (map[y][x] == -3) {
			// System.out.println("FOUND");
			xq.clear();
			yq.clear();
			// for(int i = 0; i < a; i++) {
			// for(int j = 0; j < b; j++) {
			// System.out.print(map[i][j] + " ");
			// }
			// System.out.println("");
			// }
		} else {
			// push up
			if (y + 1 < map[0].length) {
				if (map[x][y + 1] > map[x][y] + 1 && map[x][y + 1] > 0) {
					map[x][y + 1] = map[x][y] + 1;
					xq.add(x);
					yq.add(y + 1);

				}
			}

			if (x + 1 < map.length) {
				if (map[x + 1][y] > map[x][y] + 1 && map[x + 1][y] > 0) {
					map[x + 1][y] = map[x][y] + 1;
					xq.add(x + 1);
					yq.add(y);

				}
			}

			if (x > 0) {
				if (map[x - 1][y] > map[x][y] + 1 && map[x - 1][y] > 0) {
					map[x - 1][y] = map[x][y] + 1;
					xq.add(x - 1);
					yq.add(y);
				}
			}

			if (y > 0) {
				if (map[x][y - 1] > map[x][y] + 1 && map[x][y - 1] > 0) {
					map[x][y - 1] = map[x][y] + 1;
					xq.add(x);
					yq.add(y - 1);
				}
			}
		}

		// System.out.println(x + ":" + y);
	}

	public static void printCoordMap() {
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				if (map[i][j] == 1) {
					System.out.println("K" + " " + i + " " + j);
				} else if (map[i][j] == -200) {
					System.out.println("+" + " " + i + " " + j);
				} else if (map[i][j] == -3) {
					System.out.println("C" + " " + i + " " + j);
				} else if (map[i][j] == -1) {
					System.out.println("@" + " " + i + " " + j);
				} else {
					System.out.println("." + " " + i + " " + j);
				}
			}
		}
	}

	public static void project1main(File f) {

		map = new int[1000][1000];
		coordbased = false;
		coordbasedout = false;

		if (coordbased) {

			try {
				Scanner input = new Scanner(f);

				a = input.nextInt();
				b = input.nextInt();
				c = input.nextInt();

				for (int i = 0; i < a; i++) {
					for (int j = 0; j < b; j++) {
						map[i][j] = 1000;
					}
				}
				while (input.hasNextLine()) {

					char x = input.next().charAt(0);
					int i = input.nextInt();
					int j = input.nextInt();
					int z = 0;
					if (x == 'C') {
						yf = i;

						xf = j;
						z = -3;
					}
					if (x == 'K') {
						ys = i;
						xs = j;
						z = 1;
					}

					if (x == '.') {
						z = 1000;
					}
					if (x == '@') {
						z = -1;
					}
					map[i][j] = z;

				}
				input.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		} else {

			try {

				Scanner input = new Scanner(f);
				a = input.nextInt();
				b = input.nextInt();
				c = input.nextInt();
				// initialize array

				for (int i = 0; i < a; i++) {
					String v = input.next();
					for (int j = 0; j < b; j++) {
						char l = v.charAt(j);
						int z = 0;
						if (v.charAt(j) == 'C') {
							yf = i;
							xf = j;
							z = -3;
						}

						if (v.charAt(j) == 'K') {
							ys = i;
							xs = j;
							z = 1;
						}

						if (v.charAt(j) == '.') {
							z = 1000;
						}
						if (v.charAt(j) == '@') {
							z = -1;
						}
						map[i][j] = z;
					}
				}
				input.close();

			} catch (Exception e) {

				System.out.println(e);
				System.exit(1);
			}

		}

		xq.add(ys);
		yq.add(xs);
		while (xq.size() != 0) {
			pushDP(xq.remove(), yq.remove());
		}
		drawMap(yf, xf);
		map[yf][xf] = -3;
		if (coordbasedout) {
			printCoordMap();
		} else {
			printMap();
		}
	}

	public static void main(String args[]) {

		File x = new File("./src/map.txt");

		project1main(x);
	}

}
