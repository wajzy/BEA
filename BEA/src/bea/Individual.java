package bea;

import java.util.ArrayList;

// TODO: multiple obj fn with pareto
public class Individual<GT, OT extends Comparable, FT extends Comparable> implements Comparable<Individual<GT, OT, FT>>{
    protected ArrayList<GT> genes;
    protected ArrayList<OT> objectives;
    protected FT fitness;
    
    public Individual(Individual<GT, OT, FT> existing) {
        genes = new ArrayList<>(existing.genes);
        objectives = new ArrayList<>(existing.objectives);
        fitness = existing.fitness;
    }
    
    public Individual() {
        genes = new ArrayList<>();
        objectives = new ArrayList<>();
    }
    
    public ArrayList<GT> getGenes() {
        return genes;
    }
    
    public void setGenes(ArrayList<GT> genes) {
        this.genes = genes;
    }
    
    public ArrayList<OT> getObjectives() {
        return objectives;
    }
    
    public void setObjectives(ArrayList<OT> objective) {
        this.objectives = objective;
    }

    public FT getFitness() {
        return fitness;
    }

    public void setFitness(FT fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Genes: ");
        genes.stream().forEach((g) -> {
            sb.append(g).append(" ");
        });
        sb.append("Obj.: ").append(objectives);
        sb.append(" Fit.: ").append(fitness);
        return sb.toString();
    }

    @Override
    public int compareTo(Individual<GT, OT, FT> o) {
        return fitness.compareTo(o.fitness);
    }
}
