package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * Wilson Palma, Emilio Flores, Fernando Patiño, Francis Valdiviezo, Odalis Rosillo (Encargada)
 * Representa el cálculo de la Serie números Pares hasta Limite N
 * S = 1^(1/2) + 3^(1/4) + 5^(1/6) + 7^(1/8) + 11^(1/10) + 13^(1/12) ... + N):
 
 */

public class EvenRootPairNumberCalculatorUpToLimit implements Successionable {

    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public EvenRootPairNumberCalculatorUpToLimit(Number limit) {
        this(0, limit);
    }

    public EvenRootPairNumberCalculatorUpToLimit(Number start, Number limit) {
        if (start.intValue() < 0){
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        if (limit.intValue() < 0){
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        
        // Primera serie
        if(!esPrimo(start.intValue())){
            currentTerm = calcularSiguientePrimo(start.intValue());
        }
        else{
            currentTerm = start.intValue();
        }
        
        setLimit(limit);
        printableTerms = new StringBuilder("S = ");
    }
    
    private static boolean esPrimo(int n) {
        if (n < 0 || n == 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Calcula el siguiente número primo
    private static int calcularSiguientePrimo(int numero) {
        int siguiente = numero + 1;
        while (!esPrimo(siguiente)) {
            siguiente++;
        }
        return siguiente;
    }
    
    @Override
    public Number nextTerm(Number currentTerm) {
        return calcularSiguientePrimo(currentTerm.intValue());
    }

    @Override
    public void setLimit(Number limit) {
        this.limit = limit.intValue();        
    }
    
    private Double termToReal(Number currentTerm, Number par){
        return Math.pow(currentTerm.doubleValue(), 1 / par.doubleValue());
    }

    @Override
    public Number calculate() {
        int par = 0;
        double result = 0;
        while (result + termToReal(nextTerm(this.currentTerm), par+2) <= this.limit){
            //Number[] nextTerm = nextTerm(this.currentTerm);
            this.currentTerm = (int)nextTerm(this.currentTerm);//new Integer[] {nextTerm[0].intValue(), nextTerm[1].intValue()};
            par+=2;
            this.printableTerms.append(this.currentTerm + "^(1/" + par + ")").append(" + ");
            result = result + termToReal(this.currentTerm, par);
        }
        return result;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}
