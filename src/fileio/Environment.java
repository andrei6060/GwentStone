package fileio;


public class Environment extends Card {
     public Environment(final CardInput cardInput) {
          this.setMana(cardInput.getMana());
          this.setColors(cardInput.getColors());
          this.setName(cardInput.getName());
          this.setDescription(cardInput.getDescription());
     }
     public Environment(final Environment card) {
          this.setMana(card.getMana());
          this.setColors(card.getColors());
          this.setName(card.getName());
          this.setDescription(card.getDescription());
     }

     /**
      * Metoda ce va fi implementata de fiecare tip de carte Environment in parte
      */
     public Table ability(final Table table, final int nr) {
          return table;
     }
}
