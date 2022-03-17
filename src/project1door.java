import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class project1door {
	private static int xs = -1;
	private static int ys = -1;
	private static int xf = -1;
	private static int yf = -1;
	private static int yfs = -1;
	private static int xfs = -1;
	private static int a;
	private static int b;
	private static int c;
	private static int xss = -1;
	private static int yss = -1;
	private static boolean coordbased = false;
	private static boolean coordbasedout = false;
	private static boolean time = false;
	private static Queue<Integer> xq = new LinkedList<Integer>();
	private static Queue<Integer> yq = new LinkedList<Integer>();
	private static int[][] map;
	// static ArrayList<Integer> path = new ArrayList<Integer>();

	static int finalparams = 0;

	public static void drawMap(int x, int y) {
		int mx = 1000;
		int my = 1000;
		int mv = 1000;
		// System.out.println(x + " " + y);m
		if (map[x][y] == 1) { return; }
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
				} else if (map[i][j] == -4) {
					System.out.print("|");
				} else {
					System.out.print(".");
				}
			}
			System.out.println("");
		}
	}

	public static void pushDP(int x, int y) {
		// System.out.println(x + ":" + y);


		if (map[y][x] == -3 || map[y][x] == -4) {
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
				// System.out.println(map[x][y + 1] + "::" + (map[x][y] + 1));
				if (map[x][y + 1] > map[x][y] + 1 && map[x][y + 1] > 0) {
					// System.out.println("RIGHT");
					map[x][y + 1] = map[x][y] + 1;
					xq.add(x);
					yq.add(y + 1);

				}
			}

			if (x + 1 < map.length) {
	
				if (map[x + 1][y] > map[x][y] + 1 && map[x + 1][y] > 0) {
					// System.out.println("DOWN");
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
		// System.out.println(xq.size());

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
				} else if (map[i][j] == -4) {
					System.out.println("|" + " " + i + " " + j);
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

		if (coordbased) {

			try {
				Scanner input = new Scanner(f);

				a = input.nextInt();
				b = input.nextInt();
				c = input.nextInt();

				if (c == 2) {
					a = a * 2;
				}

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

					if (x == '|') {
						yfs = i;

						xfs = j;
						z = -3;
					}
					if (x == 'K') {
						if (ys == -1) {
							ys = i;
							xs = j;
						} else {
							yss = i;
							xss = j;
						}

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

				if (c == 2) {
					a = a * 2;
				}

				// initialize array

				for (int i = 0; i < a; i++) {
					String v = input.next();
					for (int j = 0; j < b; j++) {
						char l = v.charAt(j);
						int z = 0;
						if (v.charAt(j) == 'C') {

							z = -3;

							yf = i;
							xf = j;
						}
						if (v.charAt(j) == '|') {
							z = -4;
							yfs = i;
							xfs = j;

						}

						if (v.charAt(j) == 'K') {
							if (ys == -1) {
								ys = i;
								xs = j;
							} else {
								yss = i;
								xss = j;
							}

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

		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}
		while (xq.size() != 0) {
			pushDP(xq.remove(), yq.remove());
		}

		if (c == 2) {
			// System.out.println("asdf");
			xq.add(yss);
			yq.add(xss);
			while (xq.size() != 0) {
				pushDP(xq.remove(), yq.remove());
			}
		}



		drawMap(yf, xf);
		map[yf][xf] = -3;

		if (c == 2) {
			drawMap(yfs, xfs);
			map[yfs][xfs] = -4;
		}


		if (coordbasedout) {
			printCoordMap();
		} else {
			printMap();
		}
	}

	public static void main(String args[]) {
		if(args.length > 0) {
			// System.out.println(args[0]);
			if(args[0].equalsIgnoreCase("--Help")) {
				System.out.println("Welcome to Ayush's Pathfinder v1.0 \nThis program find the Cake for Kirby! \n\nCommand Line Arguments: \n--Stack: Uses Stack implementation (Default Off) \n--Queue: Uses Queue Implementation (Default On) \n--Opt: Finds optimal path (Default On) \n--Incoordinate: Uses Coordinate System to find path (Default Off) --Time: Prints runtime of program (Default Off) \n--Help: Prints this message");
				System.exit(0);
			}
			
		}

		File x = new File("./src/map.txt");
		long startTime = System.nanoTime();
		project1main(x);
		long endTime = System.nanoTime();
		long totalTime = (endTime - startTime)/1000000;

		System.out.println(totalTime);
	}

}
