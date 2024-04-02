public class EncapsulamentoAndErros {

  public static void ErroOpcaoInvalida() {
    System.out.println(Main.RED + "Opcao invalida! Tente novamente." + Main.RESET_COLOR);
  }

  public static String encapsularData(String input) {
    StringBuilder result = new StringBuilder();
    int posicaoNaString = 0;

    // Remove chars não numéricos e coloca '/' depois do segundo e quarto caracteres
    for (char c : input.toCharArray()) {
      if (Character.isDigit(c)) {
        result.append(c);
        posicaoNaString++;
        if (posicaoNaString == 2 || posicaoNaString == 4) {
          result.append('/');
        }
      }
    }

    // Verifica se o tamanho da string é 10 (excluindo '\0')
    if (result.length() != 10) {
      System.out.println(Main.RED + "ERRO: Formato de data invalido" + Main.RESET_COLOR);
      return "";
    }

    return result.toString();
  }

  public static String encapsularHorario(String input) {
    StringBuilder result = new StringBuilder();

    // Remove não numéricos
    for (char c : input.toCharArray()) {
      if (Character.isDigit(c)) {
        result.append(c);
      }
    }

    // Adiciona 'h' após os primeiros dois números
    if (result.length() >= 2) {
      result.insert(2, 'h');
    }

    // Verifica se o tamanho da string é 5 (excluindo '\0')
    if (result.length() != 5) {
      System.out.println(Main.RED + "ERRO: Formato de horario invalido" + Main.RESET_COLOR);
      return "";
    }

    return result.toString();
  }

  public static boolean analisarEntradaBool(String entrada) {
    // Define quais strings sao true e false
    String[] trueValues = { "sim", "s", "yes", "y", "true", "t", "v" };
    String[] falseValues = { "nao", "n", "no", "false", "f" };

    // Testa se a entrada eh algum dos valores acima
    for (String trueValue : trueValues) {
      if (entrada.equals(trueValue)) {
        return true;
      }
    }
    for (String falseValue : falseValues) {
      if (entrada.equals(falseValue)) {
        return false;
      }
    }

    // Retorna false caso os valores nao batam
    return false;
  }
}
