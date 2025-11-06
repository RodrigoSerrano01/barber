package br.com.v1.barber.enumerator;

public enum ServiceTime {

    ONEHOUR(2),
    HALFANHOUR(1);

    private final int value;

    ServiceTime(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}


