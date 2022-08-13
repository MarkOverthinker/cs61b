public class NBody {
    static public double readRadius(String path) {
        In in = new In(path);
        in.readInt();
        double second = in.readDouble();
        return second;
    }

    static public Planet[] readPlanets(String path) {
        In in = new In(path);
        int num = in.readInt();
        Planet[] planets = new Planet[num];
        in.readDouble();
        for (int i = 0; i < num; ++i) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                                    in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dT = Double.parseDouble(args[1]);
        double time = 0;
        String filename = args[2];
        double R = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int num = planets.length;
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-R, R);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg", 2*R, 2*R);
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.show();
        while (time != T) {
            double[] xfs = new double[num];
            double[] yfs = new double[num];
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg", 2*R, 2*R);
            for (int i = 0; i < num; ++i) {
                xfs[i] = planets[i].calcNetForceExertedByX(planets);
                yfs[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < num; ++i) {
                planets[i].update(dT, xfs[i], yfs[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause( 10);
            time += dT;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
