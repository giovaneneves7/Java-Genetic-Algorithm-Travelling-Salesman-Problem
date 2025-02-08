package com.github.giovaneneves7.travellingsalesmanproblem.populations;

import com.github.giovaneneves7.travellingsalesmanproblem.chromosomes.TSPChromosome;
import com.github.giovaneneves7.travellingsalesmanproblem.genes.TSPGene;
import com.github.giovaneneves7.travellingsalesmanproblem.utils.TSPUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Giovane Neves
 */
public class TSPPopulation {
 
    private List<TSPChromosome> population;
    private final int initialSize;
    
    public TSPPopulation(
            final TSPGene[] points,
            final int initialSize
    ){
        
        this.population = init(points, initialSize);
        this.initialSize = initialSize;
    }
    
    /**
     * Get the first generation of the population
     * 
     * @return The first generation of the population.
     */
    public TSPChromosome getAlpha(){
        
        return this.population.get(0);
        
    }
    
    private List<TSPChromosome> init(final TSPGene[] points, final int initialSize){
    
        final List<TSPChromosome> eden = new ArrayList<>();
        for(int i = 0; i < initialSize; i++){
            final TSPChromosome chromosome = TSPChromosome.create(points);
            eden.add(chromosome);
        }
        
        return eden;
        
    }
    
    public void update(){
        
        doCrossOver();
        doMutation();
        doSpawn();
        doSelection();
        
    }
    /**
     * Do the crossover
     */
    private void doCrossOver(){
        
        final List<TSPChromosome> newPopulation = new ArrayList<>();
        
        for(final TSPChromosome chromosome : this.population){
            
            TSPChromosome partner = getCrossOverPartner(chromosome);
            newPopulation.addAll(Arrays.asList(chromosome.crossOver(partner)));
            
        }
        this.population.addAll(newPopulation);
          
    }
    
    /**
     * 
     */
    private void doMutation(){
        
       final List<TSPChromosome> newPopulation = new ArrayList<>();
       for(int i = 0; i < this.population.size()/10; i++){
           
           TSPChromosome mutation = this.population.get(TSPUtils.randomIndex(this.population.size())).mutate();
           newPopulation.add(mutation);
        } 
        this.population.addAll(newPopulation);
       
    }
    
    private void doSpawn(){
        
        IntStream.range(0, 1000).forEach(e -> TSPChromosome.create(TSPUtils.CITIES));
        
    }
    
    private void doSelection(){
        
        this.population.sort(Comparator.comparingDouble(TSPChromosome::calculateDistance));
        this.population.stream().limit(this.initialSize).collect(Collectors.toList());
    }
    
    private TSPChromosome getCrossOverPartner(final TSPChromosome chromosome){
        
        TSPChromosome partner = this.population.get(TSPUtils.randomIndex(this.population.size()));
        while(chromosome == partner){
            partner = this.population.get(TSPUtils.randomIndex(this.population.size()));
        }
        
        return partner;
    }
    
}
