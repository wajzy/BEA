/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import bea.Individual;
import bea.Optimizer;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author wajzy
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Function<Function<Object, Double>, ArrayList<Double>> initFn = (ip) -> {
            ArrayList<Double> genes = new ArrayList<>();
            for(int i=0; i<2; i++) {
                genes.add(ip.apply(null));
            }
            return genes;
        };
        Consumer<Individual<Double, Double, Double>> objFn1 = (ind) -> {
            ArrayList<Double> genes = ind.getGenes();
            double result = 10. * genes.size();
            DoubleSummaryStatistics dss = genes.stream().collect(
                    Collectors.summarizingDouble(
                            g -> g*g - 10.*Math.cos(2.*Math.PI*g)));
            result += dss.getSum();
            ArrayList<Double> objs = ind.getObjectives();
            if(objs.isEmpty()) {
                objs.add(result);
            } else {
                objs.set(0, result);
            }
            ind.setObjectives(objs);
        };
        Function<Object, Double> geneFn = (gp) -> {
            return new Random().nextDouble()*5.12*2. - 5.12;
        };
        
//        Optimizer<Double, Double, Double, Function<Object, Double>, Object> opt1 = 
//                new Optimizer<>(4, initFn, geneFn, objFn1, 3, geneFn, null, 3, 500);
//        
//        opt1.optimize();
//        System.out.println(opt1);
        
        // ------------------
        
        Consumer<Individual<Double, Double, Integer>> objFn2 = (ind) -> {
            ArrayList<Double> genes = ind.getGenes();
            ArrayList<Double> objs = ind.getObjectives();
            for(int i=0; i<genes.size(); i++) {
                double result = genes.get(i)*genes.get(i);
                if(objs.size() <= i) {
                    objs.add(result);
                } else {
                    objs.set(i, result);
                }
            }
            ind.setObjectives(objs);
        };
        
        Optimizer<Double, Double, Integer, Function<Object, Double>, Object> opt2 = 
                new Optimizer<>(10, initFn, geneFn, objFn2, 3, geneFn, null, 3, 10);
        
        opt2.optimize();
        System.out.println(opt2);
    }
}
