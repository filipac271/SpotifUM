/**
 * @file Utils.java
 * @brief Classe utilitária com métodos auxiliares para manipulação de estruturas de dados.
 */

package Application.model;

import java.util.Map;

/**
  * @brief Classe com métodos utilitários genéricos.
  */
public class Utils {
    /**
     * @brief Imprime no terminal todos os pares chave-valor de um mapa.
     * 
     * @param map O mapa a ser impresso.
     * @param <K> Tipo da chave.
     * @param <V> Tipo do valor.
     */
    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}
