package db.repositorio.financiero.enums;

public enum CuentaTipo {
    AHORRO("AHORRO"), CORRIENTE("CORRIENTE");

    private final String descripcion;

    CuentaTipo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {return descripcion;}

}
