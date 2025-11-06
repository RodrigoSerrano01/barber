package br.com.v1.barber.enumerator;

public enum ServiceTime {

    ONEHOUR(1),
    HALFANHOUR(2);

    private final int value;

    ServiceTime(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}


