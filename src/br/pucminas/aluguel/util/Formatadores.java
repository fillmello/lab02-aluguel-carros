package br.pucminas.aluguel.util;

public final class Formatadores {
    private Formatadores() { }
    public static String digitos(String valor) { return valor == null ? "" : valor.replaceAll("\\D", ""); }
    public static String cpfNumerico(String valor) { return digitos(valor); }
    public static String cpf(String valor) {
        String digits = digitos(valor);
        return digits.length() == 11 ? digits.substring(0, 3) + "." + digits.substring(3, 6) + "." + digits.substring(6, 9) + "-" + digits.substring(9) : valor;
    }
    public static String rg(String valor) {
        if (valor == null) return "";
        String normalized = valor.trim().toUpperCase();
        String digits = digitos(normalized);
        return digits.length() == 9 ? digits.substring(0, 2) + "." + digits.substring(2, 5) + "." + digits.substring(5, 8) + "-" + digits.substring(8) : normalized;
    }
}
