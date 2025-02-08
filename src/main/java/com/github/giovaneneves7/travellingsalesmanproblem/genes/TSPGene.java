package com.github.giovaneneves7.travellingsalesmanproblem.genes;

/**
 * TSPGene's class
 * @author Giovane Neves
 */
public class TSPGene {
    
    private final int x;
    private final int y;

    public TSPGene(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between two points.
     * 
     * @param next The next point
     * @return The distance between two points.
     */
    public double distance(final TSPGene next){
    
        
        return Math.sqrt(Math.pow(getX() - next.getX(), 2) + Math.pow(getY() - next.getY(), 2));
        
    }
    
    // Boilerplate code
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "TSPGene{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.x;
        hash = 47 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TSPGene other = (TSPGene) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }
    
    
    
}
