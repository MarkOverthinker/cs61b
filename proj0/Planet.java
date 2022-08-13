public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.mass = p.mass;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.yyPos = p.yyPos;
        this.xxPos = p.xxPos;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return p.equals(this) ? 0 : Math.sqrt(Math.pow(this.xxPos-p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p) {
        return p.equals(this) ? 0 : G * this.mass * p.mass / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        return p.equals(this) ? 0 : calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return p.equals(this) ? 0 : calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double ret = 0;
        for (Planet p : ps) {
            if (p != null)
            ret += calcForceExertedByX(p);
        }
        return  ret;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double ret = 0;
        for (Planet p : ps) {
            if (p != null)
            ret += calcForceExertedByY(p);
        }
        return  ret;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx/mass;
        double ay = fy/mass;
        xxVel += ax *dt;
        yyVel += ay *dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
