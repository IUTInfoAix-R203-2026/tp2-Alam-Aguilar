package fr.univ_amu.iut.exercice5;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercice 5 - Grille du démineur.
 *
 * <p>Prend en entrée une grille de caractères {@code ' '} et {@code '*'} (où {@code '*'} représente
 * une mine) et produit la même grille annotée : chaque case vide reçoit le nombre de mines dans ses
 * 8 cases voisines (ou reste un espace si aucune mine n'est adjacente).
 *
 * <p>Exemple :
 *
 * <pre>
 *     +-----+     +-----+
 *     | * * |     |1*3*1|
 *     |  *  |  →  |13*31|
 *     |  *  |     | 2*2 |
 *     |     |     | 111 |
 *     +-----+     +-----+
 * </pre>
 *
 * <p>Cet exercice mêle :
 *
 * <ul>
 *   <li>tableaux 2D irréguliers (chaque ligne est une {@link String})
 *   <li>gestion des bornes (cases au bord de la grille)
 *   <li>validation d'entrée (null, symboles, lignes de longueurs différentes)
 *   <li>{@code ApprovalTests} pour une grille de grande taille (voir les tests)
 * </ul>
 */
public class GrilleDemineur {

  private final List<String> grille;

  /**
   * Construit une grille à partir de sa représentation textuelle.
   *
   * @param grilleInitiale lignes de la grille
   * @throws IllegalArgumentException si la grille est {@code null}, contient un caractère autre que
   *     {@code ' '} ou {@code '*'}, ou si les lignes ont des longueurs différentes
   */
  public GrilleDemineur(List<String> grilleInitiale) {
    // TODO exercice 5 : valider l'entrée puis stocker la grille.
    this.grille = grilleInitiale == null ? List.of() : List.copyOf(grilleInitiale);
    if (grilleInitiale == null) throw new IllegalArgumentException();

    for (String string : grilleInitiale) {
      String firstLine = grilleInitiale.get(0);
      String line = string;

      if (string.length() != firstLine.length()) {
        throw new IllegalArgumentException();
      }
      for (char caractere : line.toCharArray()) {
        if (caractere == ' ' || caractere == '*') continue;
        else throw new IllegalArgumentException();
      }
    }
  }

  private int countMinesAdj(int NbLigne, int NbColonnes) {
    int count = 0;

    // if ((grille.get(NbLigne - 1).charAt(NbColonnes) ) == '*'
    // || grille.get(NbLigne + 1).charAt(NbColonnes)
    // || grille.get(NbLigne).charAt(NbColonnes - 1)
    // || grille.get(NbLigne).charAt(NbColonnes + 1)
    // || grille.get(NbLigne - 1).charAt(NbColonnes + 1)
    // || grille.get(NbLigne - 1).charAt(NbColonnes - 1)
    // || grille.get(NbLigne + 1).charAt(NbColonnes + 1)
    // || grille.get(NbLigne + 1).charAt(NbColonnes - 1)) == '*')

    for (int line = -1; line <= 1; ++line) { // on parcours les voisin de la case courant
      for (int col = -1; col <= 1; ++col) { // on parcours les voisin de la case courant
        if (line == 0 && col == 0) // on considere pas la case courant
        continue;
        else {
          int linei = NbLigne + line;
          int coli = NbColonnes + col;

          if (linei >= grille.size() || linei < 0) { // les cas dehors les bornes
            continue;
          } else {
            String lineAdjacent = grille.get(linei);
            if (coli >= lineAdjacent.length() || coli < 0) { // les cas dehors les bornes
              continue;
            } else if (lineAdjacent.charAt(coli) == '*') {
              count++;
            }
          }
        }
      }
    }

    return count;
  }

  /**
   * Retourne la grille annotée : chaque case vide est remplacée par le nombre de mines adjacentes
   * (ou un espace si aucune), chaque mine reste un {@code '*'}.
   */
  public List<String> getRepresentationAnnotee() {
    List<String> resultat = new ArrayList<>(grille.size());
    // TODO exercice 5 : remplir resultat avec une ligne annotée par ligne d'entrée.
    //
    // Pour chaque case (ligne, col) :
    // - si c'est une mine ('*'), laisser '*'
    // - sinon compter les mines dans les 8 cases voisines (en gérant les bords)
    // - si le compte est > 0, écrire ce chiffre
    // - si le compte est 0, écrire un espace
    //
    // Astuce : une méthode privée compterMinesAdjacentes(int, int) facilite
    // la gestion des bords et rend le code testable.

    // for (String chaine : resultat) {
    // if (chaine == "*") {
    // return resultat;
    // } else
    // minesTotal = countMinesAdj();
    // if (minesTotal > 0)
    // chaine = minesTotal;
    // else if (minesTotal == 0)
    // chaine = " ";
    // }

    for (int i = 0; i < grille.size(); ++i) {
      String ligne = grille.get(i);
      StringBuilder annotee = new StringBuilder(ligne.length());
      for (int j = 0; j < ligne.length(); ++j) {
        char caractere = ligne.charAt(j);
        if (caractere == '*') {
          annotee.append('*');
        } else {
          int nBMines = countMinesAdj(i, j);
          if (nBMines > 0) annotee.append(nBMines); // Integer.toString(nBMines);
          else annotee.append(' ');
        }
      }
      resultat.add(annotee.toString());
    }

    return resultat;
  }
}
