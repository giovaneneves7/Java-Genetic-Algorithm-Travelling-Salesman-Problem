package com.github.giovaneneves7.travellingsalesmanproblem.utils;

import com.github.giovaneneves7.travellingsalesmanproblem.genes.TSPGene;
import com.github.giovaneneves7.travellingsalesmanproblem.widgets.ScreenBackground;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author Giovane Neves
 */
public class TSPUtils {
 
    private static final Random R = new Random(10000);
    public final static TSPGene[] CITIES = generateData(100); 
    
    
    private TSPUtils(){
        throw new RuntimeException("Error!");
    }
    
    /**
     * Initialize the populaton
     * 
     * @param numDataPoints The number of data points.
     * @return An array of TSPGene. 
     */
    private static TSPGene[] generateData(final int numDataPoints){
        
        final TSPGene[] data = new TSPGene[numDataPoints];
        for(int i = 0; i < numDataPoints; i++){
            data[i] = new TSPGene(
                    TSPUtils.randomIndex(400), 
                    TSPUtils.randomIndex(300)
            );
        }
        
        return data;
    }
    
    /**
     * Get a random index
     * 
     * @param limit The limit of the index.
     * @return The random index.
     */
    public static int randomIndex(final int limit){
        
        return R.nextInt(limit);
        
    }
    
    /**
     * Split the list by the half
     * 
     * @param <T> The list type
     * @param list The data list
     * @return A array of list of the type T.
     */
    public static<T> List<T>[] split(final List<T> list){
        
        final List<T> first = new ArrayList<>();
        final List<T> second = new ArrayList<>();
        final int size = list.size();
        
        IntStream.range(0, size).forEach(i -> {
            
            if(i < ((size  + 1) / 2)){
                first.add(list.get(i));
            } else {
                second.add(list.get(i));
            }
            
        });
        
        return (List<T>[]) new List[] {first, second};
        
    }
}
