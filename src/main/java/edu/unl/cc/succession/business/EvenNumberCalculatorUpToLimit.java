package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * Representa el cálculo de la Serie números Pares hasta Limite N
 * S = 2 + 4 + 6 + 8 + ... N
 * @author wduck (Wilman Chamba Z.)
 */

public class EvenNumberCalculatorUpToLimit implements Successionable {

    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public EvenNumberCalculatorUpToLimit(Number limit) {
        this(0, limit);
    }

    public EvenNumberCalculatorUpToLimit(Number start, Number limit) {
        if (start.intValue() < 0){
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        if (limit.intValue() < 0){
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        currentTerm = (start.intValue() % 2 != 0) ? start.intValue() + 1 : start.intValue();
        setLimit(limit);
        printableTerms = new StringBuilder("S = ");
    }


    @Override
    public Number nextTerm(Number currentTerm) {
        return currentTerm.intValue()+2;
    }

    @Override
    public void setLimit(Number limit) {
        this.limit =  (limit.intValue() % 2 != 0) ? limit.intValue() - 1 : limit.intValue();
    }

    @Override
    public Number calculate() {
        Long result = 0l;
        this.currentTerm = nextTerm(this.currentTerm).intValue();
        while (this.currentTerm <= this.limit){
            this.printableTerms.append(this.currentTerm).append(" + ");
            result = result + this.currentTerm;
            this.currentTerm = nextTerm(this.currentTerm).intValue();
        }
        return result;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}
