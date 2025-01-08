package com.spachecor.ejerciciofinalsgecn.model.entity;

public abstract class Entidad<T extends Entidad> implements Comparable<T>{
    public abstract Integer getId();
}
