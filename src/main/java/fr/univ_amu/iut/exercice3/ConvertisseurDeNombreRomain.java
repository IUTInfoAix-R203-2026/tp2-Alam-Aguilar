package fr.univ_amu.iut.exercice3;

/**
 * Exercice 3 - Convertisseur de chiffres romains en nombres arabes.
 *
 * <p>Règles :
 *
 * <ul>
 *   <li>Les symboles valides sont {@code I=1, V=5, X=10, L=50, C=100, D=500, M=1000}
 *   <li>Lus de gauche à droite, les symboles s'additionnent : {@code VI = 5 + 1 = 6}
 *   <li>Un symbole placé avant un symbole de valeur supérieure se soustrait : {@code IV = 5 - 1 =
 *       4}
 *   <li>Les seules soustractions valides sont :
 *       <ul>
 *         <li>I avant V ou X ({@code IV = 4}, {@code IX = 9})
 *         <li>X avant L ou C ({@code XL = 40}, {@code XC = 90})
 *         <li>C avant D ou M ({@code CD = 400}, {@code CM = 900})
 *       </ul>
 *       Toute autre soustraction doit lever {@link IllegalArgumentException}.
 * </ul>
 *
 * <p>Conseils TDD : commencez par gérer uniquement {@code I}, puis {@code II} / {@code III} (fake
 * it), puis {@code V} (triangulation), puis {@code VI} (addition de deux symboles différents), puis
 * {@code IV} (introduction de la soustraction). À ce moment-là, <b>extrayez une méthode</b> pour
 * calculer la valeur d'un symbole - vous en aurez besoin pour les symboles suivants.
 */
public class ConvertisseurDeNombreRomain {

  /**
   * Convertit une chaîne de chiffres romains en valeur entière.
   *
   * @param chiffreRomain chaîne composée de symboles romains (par exemple {@code "XLIX"})
   * @return la valeur entière correspondante
   * @throws IllegalArgumentException si la chaîne contient un symbole invalide ou une soustraction
   *     interdite
   */
  public int enNombreArabe(String chiffreRomain) {
    int total = 0;
    // TODO exercice 3 : remplir total en parcourant la chaîne.
    //
    // Activez les tests un par un. Commencez par "I" = 1 (fake it en
    // retournant 1 en dur), puis "II" = 2 et "III" = 3 (boucle de comptage
    // d'occurrences de I), puis "V" = 5 (switch sur le symbole).
    //
    // Quand vous arrivez à "IV" = 4 : extrayez une méthode valeurDe(char)
    // pour factoriser, puis ajoutez la logique de soustraction.
    //
    // Pour les exceptions : une soustraction est valide seulement pour
    // I avant V/X, X avant L/C, C avant D/M. Tout le reste est invalide.

    // if (chiffreRomain == "I") {
    // return 1;
    // } else if (chiffreRomain == "II") {
    // return 2;
    // } else if (chiffreRomain == "III") {
    // return 3;
    // } else if (chiffreRomain == "V") {
    // return 5;
    // }
    // return total;

    // switch (chiffreRomain) {
    // case "I":
    // total = 1;
    // break;
    // case "II":
    // total = 2;
    // break;
    // case "III":
    // total = 3;
    // break;
    // case "V":
    // total = 5;
    // break;
    // case "VI":
    // total = 6;
    // break;
    // case "X":
    // total = 10;
    // break;
    // case "L":
    // total = 50;
    // break;
    // case "C":
    // total = 100;
    // break;
    // case "D":
    // total = 500;
    // break;
    // case "M":
    // total = 1000;
    // break;
    // default:
    // return total;
    // }
    // return total;

    // Soustractions valides uniquement :
    // I avant V/X ;
    // X avant L/C ;
    // C avant D/M.
    // Toute autre combinaison (IL, IC, VX, ...) est invalide et doit lever
    // IllegalArgumentException.

    // if (chiffreRomain == "Z" | chiffreRomain == "IL") {
    // throw new IllegalArgumentException();
    // }

    if (chiffreRomain.length() > 1) {

      for (int i = 0; i < chiffreRomain.length(); i++) {

        if ((i + 1) < chiffreRomain.length()) {

          if ((valeurDe(chiffreRomain.charAt(i))) < (valeurDe(chiffreRomain.charAt(i + 1)))) {

            // Soustractions valides uniquement :
            // I avant V/X, X avant L/C, C avant D/M. Tout le reste est invalide.

            char plus_petit = chiffreRomain.charAt(i);
            char plus_grand = chiffreRomain.charAt(i + 1);
            boolean est_soustraction_valide =
                (((plus_petit == 'I') && (plus_grand == 'V' || plus_grand == 'X'))
                    || ((plus_petit == 'X') && (plus_grand == 'L' || plus_grand == 'C'))
                    || ((plus_petit == 'C') && (plus_grand == 'D' || plus_grand == 'M')));

            if (!est_soustraction_valide) {
              throw new IllegalArgumentException(
                  plus_petit + " et " + plus_grand + " ne sont pas une soustraction valide !");
            }
            total += ((valeurDe(plus_grand)) - (valeurDe(plus_petit)));
            i++;
          } else {
            total += (valeurDe(chiffreRomain.charAt(i)));
          }

        } else if ((i + 1) >= chiffreRomain.length()) {
          total += (valeurDe(chiffreRomain.charAt(i)));
        }
      }
      return total;

    } else { // (chiffreRomain.length() <= 1) cad: un seul caractere
      return total = valeurDe(chiffreRomain.charAt(0)); // return le valeur du caractere
    }
  }

  public int valeurDe(char symbole) {
    switch (symbole) {
      case 'I':
        return 1;
      case 'V':
        return 5;
      case 'X':
        return 10;
      case 'L':
        return 50;
      case 'C':
        return 100;
      case 'D':
        return 500;
      case 'M':
        return 1000;
      default:
        throw new IllegalArgumentException(symbole + "n'est pas valide !");
    }
  }
}
