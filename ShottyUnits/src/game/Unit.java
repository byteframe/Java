package game;

import java.awt.*;

public class Unit {
    protected int xpos;
    protected int ypos;
    protected int zpos;
    protected double xvel;
    protected double yvel;
    protected double zvel;
    protected int width;
    protected int height;
    protected int depth;

    private int xmin;
    private int ymin;
    private int zmin;
    private int xmax;
    private int ymax;
    private int zmax;
    private boolean bounceable;
    private int moverate;


    public Unit() {
        xpos = 20;
        ypos = 20;
        zpos = 20;
        bounceable = true;
        moverate = 20;
    }

    public void setBounds(int x1,int y1,int z1,int x2,int y2,int z2) {
        xmin=x1;
        ymin=y1;
        zmin=z1;
        xmax=x2;
        ymax=y2;
        zmax=z2;
    }

    public void setPos(int a,int b,int c) {
        xpos=a;
        ypos=b;
        zpos=c;
    }

    public void setVel(double a,double b,double c) {
        xvel=a;
        yvel=b;
        zvel=c;
    }

    public void setXPos(int x) { xpos = x; }
    public void setYPos(int y) { ypos = y; }
    public void setZPos(int z) { zpos = z; }
    public void setXVel(double x) { xvel = x; }
    public void setYVel(double y) { yvel = y; }
    public void setZVel(double z) { zvel = z; }
    public void setXMin(int x) { xmin = x; }
    public void setYMin(int y) { ymin = y; }
    public void setZMin(int z) { zmin = z; }
    public void setXMax(int x) { xmax = x; }
    public void setYMax(int y) { ymax = y; }
    public void setZMax(int z) { zmax = z; }
    public void setWidth(int w) { width = w; }
    public void setHeight(int h) { height = h; }
    public void setDepth(int d) { depth = d; }
    public void setMoverate(int m) { moverate = m; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getDepth() { return depth; }
    public int getXPos() { return xpos; }
    public int getYPos() { return ypos; }
    public int getZPos() { return zpos; }
    public double getXVel() { return xvel; }
    public double getYVel() { return yvel; }
    public double getZVel() { return zvel; }
    public int getXMin() { return xmin; }
    public int getYMin() { return ymin; }
    public int getZMin() { return zmin; }
    public int getXMax() { return xmax; }
    public int getYMax() { return ymax; }
    public int getZMax() { return zmax; }
    public int getMoverate() { return moverate; }

    public void updatePos() {
        if(xpos<xmin || xpos>xmax )xvel=-xvel;
        if(ypos<ymin || ypos>ymax )yvel=-yvel;
        if(zpos<zmin || zpos>zmax )zvel=-zvel;

        xpos=xpos+(int)xvel;
        ypos=ypos+(int)yvel;
        zpos=zpos+(int)zvel;
    }

    public void paint(Graphics g) {
        updatePos();
    }

    public boolean isIn() {
        return false;
    }

    public void setBounceable(boolean b) {
        if (b) {
            bounceable = true;
        } else {
          bounceable = false;
        }
    }
}