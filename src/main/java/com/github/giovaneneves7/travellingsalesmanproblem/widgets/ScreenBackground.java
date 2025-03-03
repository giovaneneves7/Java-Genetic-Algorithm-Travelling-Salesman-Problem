package com.github.giovaneneves7.travellingsalesmanproblem.widgets;

import com.github.giovaneneves7.travellingsalesmanproblem.genes.TSPGene;
import com.github.giovaneneves7.travellingsalesmanproblem.populations.TSPPopulation;
import com.github.giovaneneves7.travellingsalesmanproblem.utils.TSPUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.Timer;

/**
 *
 * @author Giovane Neves
 */
public class ScreenBackground extends javax.swing.JPanel {

    /**
     * The population 
     */
    private final TSPPopulation population;
    
    /**
     * The population generation number.
     */
    private final AtomicInteger generation;
    
    /**
     * Creates new form ScreenBackground
     */
    public ScreenBackground() {
        initComponents();
        this.population = new TSPPopulation(TSPUtils.CITIES, 1000);
        this.generation = new AtomicInteger(0);
        // Repaints the screen every 5 milliseconds
        Timer timer = new Timer(5, (e) -> {
           this.population.update();
           repaint();
        });
        timer.start();
    }
    
    @Override
    public void paintComponent(final Graphics graphics){
        
        super.paintComponent(graphics);
        final Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.CYAN);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString("Generation: ".concat(String.valueOf(this.generation.incrementAndGet())), 350, 15);
        g.drawString("Shortest Path: " + String.format("%.2f", this.population.getAlpha().calculateDistance()), 15, 30);
        drawBestChromosome(g);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void drawBestChromosome(Graphics2D g) {
        List<TSPGene> chromosome = this.population.getAlpha().getChromosome();
        g.setColor(Color.white);
        for(int i = 0; i < chromosome.size() - 1; i++){
            TSPGene gene = chromosome.get(i);
            TSPGene next = chromosome.get(i + 1);
            g.drawLine(gene.getX(), gene.getY(), next.getX(), next.getY());
        }
        g.setColor(Color.red);
        for(final TSPGene gene : chromosome){
            g.fillOval(gene.getX(), gene.getY(), 5, 5);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
